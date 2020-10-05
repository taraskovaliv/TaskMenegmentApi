# TaskMenegmentApi [![Build Status](https://travis-ci.com/taraskovaliv/TaskMenegmentApi.svg?branch=master)](https://travis-ci.com/taraskovaliv/TaskMenegmentApi)

## 1. Start the project locally
#### Clone project using command:
```
git clone https://github.com/taraskovaliv/TaskMenegmentApi.git
```
### Way 1   
#### 1.1. Required to install
- [x] [MariaDB](https://mariadb.org/download/) 
#### 1.2. How to run
1. Add properties of db (url, username, password):
- [x] To `liquibase.propeperties`
- [x] To `spring_config.xml`
2. Run `local.dockerfile`
### Way 2
1. Update `docker-compose` using command: 
```
docker-compose up -d
```
## 2. Work with swagger UI in our project
1. Run TaskMenegmentApi project (look up paragraph [How to run]( #1-start-the-project-locally) ).
2. Use the following link to open Swagger UI: [Swagger](http://localhost:8080/swagger#/default)