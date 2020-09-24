# TaskMenegmentApi [![Build Status](https://travis-ci.com/taraskovaliv/TaskMenegmentApi.svg?branch=master)](https://travis-ci.com/taraskovaliv/TaskMenegmentApi)

## 1. Start the project locally
### Way 1   
#### 1.1. Required to install
- [x] [MariaDB](https://mariadb.org/download/) 
#### 1.2. How to run
1. Add properties of db (url, username, password):
- [x] To `liquibase.propeperties`
- [x] To `hibernate.cfg.xml`
2. Run `local.dockerfile`
### Way 2
1. Update `docker-compose` using command: 
```
docker-compose up -d
```
