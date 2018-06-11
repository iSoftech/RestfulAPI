# Bayzat Benefits Restful API
A Simple Restful API that exposes reationship between Company, Employee and Dependant

This project is built with Spring Boot Web, JPA, HATEOAS and Test Framework.

API Blueprint has been designed to test the specification of this API and is temporarily hosted at Apiary to mock the behavior: https://bayzatbenefitsapi.docs.apiary.io

<strong>Improvisation:</strong>
- Lombok API can be implemented to avoid redundant boiler plate codes from Entity Model.
- Pagination, Filter and Sort can be implemented on top of findAll methods to fetch limited amount of data.
- Resource Assemblers can be introduced to leverage HATEOAS Links.
- It can be extended to support External Databases.
- It can be secured using OAuth Authentication.
