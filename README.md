# Projekt-Seminar-Microservices 

## 0. Setup

UserService port 9001
DepartmentService port 9002
RegistryService port 8761
GatewayService port 9191

UserService und DepartmentService registrieren sich bei RegistryService und GatewayService bildet physische Addresse von UserService und DepartmentService zu logische Namen ab.

#### 0.1 ``UserService``

* UserService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/01%20User%20Service%20Config.png)


* UserService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/01%20User%20Service%20Config%201.png)


* UserService structure

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/02%20User%20Service%20Structure.png)

* UserService structure

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/02%20User%20Service%20Structure%201.png)

* UserService ``data.sql``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/03%20DataSQL.png)

* UserService ``schema.sql``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/03%20SchemaSQL.png)


* User model

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/04%20User%20Model.png)

* ``UserRepository``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/04%20User%20Repo.png)

* UserController 1

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/05%20UserController%201.png)

* UserControler 2

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/05%20UserController%202.png)

* ``ShutdownController``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/05%20User%20ShutdownController.png)

* ``ServiceFactoryController``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/05%20User%20ServiceFactoryController.png)

* ResponseDTO

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/ResponseDTO%20User%26Department.png)

* ``UserServiceApplication1``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/UserServiceApplication1.png)

* ``UserService`` ruft ``DepartmentService`` auf

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/User%20Service/UserServiceApplication2.png)

* UserService controller package

[``RegistryServiceApplication``](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/user-service/src/main/java/com/projektseminarmicroservices/user/service/controller)


#### 0.2 ``DepartmentService``

* DepartmentService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Deparment%20Service%20Config.png)

#### 0.3 ``RegistryService``

* RegistryService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Deparment%20Service%20Config.png

#### 0.4 ``GatewayService``

* GatewayService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Gateway%20Service%20config.png)

* GatewayService ``application.yml`` (weiter)

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Gateway%20Service%20config%201.png)

## Testing API - Demo Client durch [Insomnia](https://insomnia.rest/)

* API Request Collection

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/01%20Testing%20Services%20Collection.png)

* GatewayService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Gateway%20Service%20config%201.png)

## 1. Registry/Discovery-Service

Dieser Service erlaubt es, dynamisch erstellte Services zu entdecken. Diese müssen sich zuvor hier registriert haben 

* Eureka Page http://localhost:8761

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/00%20Eureka%20Services.png)

* RegistryService ``application.yml``

![image](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/Documentation/09%20Registry%20Service%20Config.png)

* ``@EnableEurekaServer`` Annotation

[``RegistryServiceApplication``](https://github.com/namphuong2217/Projekt-Seminar-Microservices/blob/main/registry-service/src/main/java/com/projektseminarmicroservices/registry/service/RegistryServiceApplication.java)

## 2. Gateway-Service:             

Dieser Service ist der Zugangspunkt für alle Klienten und routet den Verkehr zu den entsprechenden Services der Infrastruktur 


## 3. Dynamisch-Generierter Service:Dieser Service registriert sich nach Erstellung beim Registry/Discovery Service und ist so auffindbar. 

#### 3.1 ``UserService``
Dieser Service enthält auch eine Methode oder vergleichbare Möglichkeit, um ihn wieder zu beenden. Was der Service macht, bleibt jedem selbst überlassen. Es reicht ein Einfaches „hello world“ mit Service ID zum Unterscheiden der verschiedenen Instanzen. 

#### 3.2 ``DepartmentService``

[``DepartmentService``](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/department-service/src/main/java/com/projektseminarmicroservices/departmentservice)

Analog zu ``UserService``

## 4. Service-Factory             

Dieser Service enthält Methoden für die dynamische Erstellung eines Service
