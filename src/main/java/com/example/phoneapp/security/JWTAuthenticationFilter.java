package com.example.phoneapp.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.example.phoneapp.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.phoneapp.security.SecurityConstants.HEADER_STRING;
import static com.example.phoneapp.security.SecurityConstants.SECRET;
import static com.example.phoneapp.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.example.phoneapp.entity.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			AppUser creds = new ObjectMapper().readValue(req.getInputStream(), AppUser.class);
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					creds.getUsername(), creds.getPassword(), new ArrayList<>()));
			return auth;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));

		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		res.setStatus(HttpServletResponse.SC_OK);
		res.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		res.addHeader("Access-Control-Allow-Headers", "Authorization");
		res.addHeader("Access-Control-Expose-Headers", "Authorization, content-type");
		res.getWriter().flush();
		res.getWriter().close();
		// res.addHeader("Access-Control-Allow-Credentials", "true");

		/*
		 * String responseToClient = "{  \"name\":\"Felipe\",\r\n" +
		 * "   \"surname\":\"Cepeda\" }"; res.getWriter().write(responseToClient);
		 */
	}

}
