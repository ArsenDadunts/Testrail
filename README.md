# Testrail API
This repository contains bindings on how to access [TestRail's API (v2)](http://docs.gurock.com/testrail-api2/start). 

## project_structure

    ├── README.md
    ├── pom.xml
    ├── properties
    │   └── config.properties
    └── src
        └── test
            ├── java
            │   └── testrail
            │           ├── clients
            │           │   └── APIClient.java
            │           ├── common
            │           │   ├── Utils.java
            │           │   └── Constants.java
            │           ├── exceptions
            │           │   └── APIException.java
            │           ├── executors
            │           │   ├── Case_Fields.java
            │           │   ├── Case_Types.java
            │           │   ├── Cases.java
            │           │   ├── Milestones.java
            │           │   ├── Priorities.java
            │           │   ├── Reports.java
            │           │   ├── Results.java
            │           │   ├── Runs.java
            │           │   ├── Sections.java
            │           │   ├── Suites.java
            │           │   ├── Templates.java
            │           │   └── Tests.java
            │           ├── payloads
            │           │   ├── CasePayload.java
            │           │   ├── ProjectPayload.java
            │           │   ├── ResultPayload.java
            │           │   ├── RunPayload.java
            │           │   └── SectionPayload.java     
            │           └── __tests__          
            └── resources
                └── images