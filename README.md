# Projekt-Seminar-Microservices 

## 0. Setup

UserService random port http://localhost:{randomPort}/users/** ([Link to User Controllers](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service/src/main/java/com/projektseminarmicroservices/user/service/controller))

DepartmentService port 9002 with API endpoint  http://localhost:9002/departments/** ([Link to Department Controller](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/department-service/src/main/java/com/projektseminarmicroservices/departmentservice/controller/DepartmentController.java))

Registry-, DiscoveryService port 8761 ([Link to Registry Service](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/registry-service))

GatewayService port 9191 ([Link to Gateway Service](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/gateway-service))

UserService und DepartmentService registrieren sich bei RegistryService und GatewayService bildet physische Addresse von UserService und DepartmentService zu logischer Namen ab.


## 1. Gateway-Service           

# *AUFGABE: Dieser Service ist der Zugangspunkt für alle Klienten und routet den Verkehr zu den entsprechenden Services der Infrastruktur*

* GatewayService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Gateway%20Service%20config.png)

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Gateway%20Service%20config%201.png)

* ``GatewayServiceApplication`` with ``@EnableEurekaClient`` annotation

[GatewayServiceApplication](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/gateway-service/src/main/java/com/projektseminarmicroservices/gateway/service/GatewayServiceApplication.java)

## 2. Registry/Discovery-Service

"AUFGABE: Dieser Service erlaubt es, dynamisch erstellte Services zu entdecken. Diese müssen sich zuvor hier registriert haben."

* Eureka Page http://localhost:8761

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/00%20Eureka%20Services.png)

* RegistryService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Registry%20Service%20Config.png)

* ``RegistryServiceApplication`` with ``@EnableEurekaServer`` annotation

[``RegistryServiceApplication``](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/registry-service/src/main/java/com/projektseminarmicroservices/registry/service/RegistryServiceApplication.java)


## 3. Service-Factory und Dynamisch-Generierter Service

#### 3.0 ``UserService``


"AUFGABE: Was der Service macht, bleibt jedem selbst überlassen. "

``UserService`` kann folgende zurückgeben:

1. Ein Service ID zum Unterscheiden der verschiedenen Instanzen durch GET Request ("**/users/service-id")
2. Registriere ein Nutzer durch POST Request ("**/users/register")
3. Abfrage eines Nutzers durch ID durch GET Request ("**/users/{id}")
4. Abfrage eines Nutzers und dazu gehörigen Department-Namen durch GET Request ("**/users/department/{id}")

(mehr darunter)


* UserService ``application.yml`` 

UserService hat "random port" und ein identifizierter ID.

"AUFGABE: Dieser Service registriert sich nach Erstellung beim Registry/Discovery Service und ist so auffindbar."

UserService ist an einer Datenbank verknüpft (``userservicedb`` Postgresql) und benutzt JPA-Hibernate.

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/01%20User%20Service%20Config.png)

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/01%20User%20Service%20Config%201.png)

* UserService structure

UserService hat "global exception handler", um Exception zu handeln. ([Link to ExceptionHandler](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service/src/main/java/com/projektseminarmicroservices/user/service/exception))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/02%20User%20Service%20Structure.png)
![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/02%20User%20Service%20Structure%201.png)

* User model

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/04%20User%20Model.png)

* UserService ``data.sql`` 

Loading initialized data for testing.

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/03%20DataSQL.png)

* UserService ``schema.sql`` 
 
Controling data schema.

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/03%20SchemaSQL.png)

* ResponseDTO to return ``User`` and ``Department`` information from database

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/ResponseDTO%20User%26Department.png)

* Department DTO 

[Department DTO in UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/user-service/src/main/java/com/projektseminarmicroservices/user/service/DTO/Department.java)

* ``UserRepository``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/04%20User%20Repo.png)

* UserController 

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/05%20UserController%201.png)

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/05%20UserController%202.png)

#### 3.1 Service-Factory      

"AUFGABE:  Dieser Service enthält Methoden für die dynamische Erstellung eines Service."

* ``ServiceFactoryController``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/05%20User%20ServiceFactoryController.png)


#### 3.2 Möglichkeit, um ein Service wieder zu beenden

"AUFGABE: Dieser Service enthält auch eine Methode oder vergleichbare Möglichkeit, um ihn wieder zu beenden."

* ``ShutdownController``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/05%20User%20ShutdownController.png)


* ``UserServiceApplication`` with ``@EnableEurekaClient`` Annotation

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/UserServiceApplication1.png)

* ``UserService`` ruft ``DepartmentService`` auf (Die zwei Services kommunizieren miteinander)

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/UserServiceApplication2.png)

* UserService controller package

[``User Controllers``](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service/src/main/java/com/projektseminarmicroservices/user/service/controller)

#### 3.3 ``DepartmentService``

Analog zu ``UserService`` ([Link to Department Service](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/department-service))

* DepartmentService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Deparment%20Service%20Config.png)

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/Screenshot%20from%202021-02-28%2013-08-19.png)


## 4. Testing API - Demo Client durch [Insomnia](https://insomnia.rest/)

"AUFGABE: Einfacher Demo-Client."

* API Request Collection

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/01%20Testing%20Services%20Collection.png)

* UserService GET Request via localhost:9191 ([GatewayService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/gateway-service)): return a service instance ID ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/01%20GET%20User%20ServiceId.png)

* GET a user with id as parameter ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/02%20GET%20User%20Gateway.png)

*  GET a user with id as parameter exception ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/02%20GET%20User%20Gateway%20Fail.png)

* POST register a user ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/03%20POST%20User%20Gateway.png)

* POST register a user exception ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/03%20POST%20User%20Gateway%20Fail%202.png)

* POST register a user exception ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/03%20POST%20User%20Gateway%20Fail.png)

* POST save a department ([DepartmentService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/department-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/04%20POST%20Department%20Gateway.png)

* GET retrieve a department ([DepartmentService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/department-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/05%20GET%20Department%20Gateway.png)

* GET user information with department information ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service) & [DepartmentService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/department-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/06%20GET%20ResponseDTO.png)

* GET ServiceFactory ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/07%20GET%20User%20createService.png)

* POST end a service ([UserService](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service))

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/08%20POST%20User%20shutdownContext.png)

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/08%20ShutdownContext%20UserService%201.png)

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/08%20ShutdownContext%20UserService%202.png)


