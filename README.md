## Docker Network
## Create network
### > docker network create ta-net


# Travelagency-api

## To create image based on Dockerfile
### > docker build -t travelagency-api .

## To Create container
### > docker run --net ta-net --name ta-api -d -p 7070:7070 travelagency-api
 
 
## Mysql

### docker run --net ta-net --name ta-mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql

### docker cp ..\test_full.sql <ta-mysql>:/tmp/

### mysql -uroot -p travelagency < /tmp/test_full.sql
