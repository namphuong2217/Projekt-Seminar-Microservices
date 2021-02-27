# Projekt-Seminar-Microservices

### Registry/Discovery-Service:   Dieser Service erlaubt es, dynamisch erstellte Services zu entdecken. Diese müssen sich zuvor hier registriert haben 

### Gateway-Service:              Dieser Service ist der Zugangspunkt für alle Klienten und routet den Verkehr zu den entsprechenden Services der Infrastruktur 


### Dynamisch-Generierter Service:Dieser Service registriert sich nach Erstellung beim Registry/Discovery Service und ist so auffindbar. 

#### ``UserService``
Dieser Service enthält auch eine Methode oder vergleichbare Möglichkeit, um ihn wieder zu beenden. Was der Service macht, bleibt jedem selbst überlassen. Es reicht ein Einfaches „hello world“ mit Service ID zum Unterscheiden der verschiedenen Instanzen. 

#### ``DepartmentService``

[``DepartmentService``](https://github.com/namphuong2217/Projekt-Seminar-Microservices/tree/main/department-service/src/main/java/com/projektseminarmicroservices/departmentservice)

Analog zu ``UserService``

### Service-Factory:              Dieser Service enthält Methoden für die dynamische Erstellung eines Service
