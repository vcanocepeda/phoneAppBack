Exercise 1 

The service will accept HTTP GET requests at 

http://localhost:8080/phone

and will fetch the data from an embedded h2 database, that we have previously filled with a collection of phones at the init phase. 

If you invoke it with a "fromFile" query string parameter set to true you will obtain the data from a file inside the resources folder.

http://localhost:8080/phone?fromFile=true

The response in both cases will be a JSON Dummy list of Phone Entity

{
    "phoneList": [
        { "id": 1, "name": "Samsung", "description": "Samsung Galaxy S7", "image": "/src/main/resources/galaxy_s7-img.jpg", "price": 650 },
        { "id": 2, "name": "Huawei", "description": "Huawei P20", "image": "/src/main/resources/huawei_p20-img.jpg", "price": 430 }, 
        ...
    ]
}

Exercise 2

The service will accept HTTP POST requests at

http://localhost:8080/customerOrder

The payload or message body should have this sample format:
{
   "name":"Angel",
   "surname":"Marquina",
   "email":"angelMarquina@gmail.com",
    "phoneList": [
        {
            "id": 1,
            "name": "Samsung",
            "description": "Samsung Galaxy S7",
            "image": "/src/main/resources/galaxy_s7-img.jpg",
            "price": 650
        }
    ]
}

The application will look for the phone ids that we have sent, and will throw an exception if it's not able to find them in dummy phone data list that we have inserted in the h2 database at the init phase.

The response will calculate the total prices of the order adding the price of every phone in the list. From the above sample would be:

{
    "orderPrice": 650
}

We have logged the final order to the console.


Questions

How would you improve the system? How would you avoid your order API to be overflow?

We should use an architecture based on a container orchestration engine like Kubernetes, Docker Swarm, or Amazon ECS. In that way we could also get rid of API overflow with a configuration that supports more than one instance per service, using the load balancer to split requests between those instances.

