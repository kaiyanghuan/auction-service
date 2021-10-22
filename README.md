# auction-service

## How to run this instance locally?
1. Open your choice of terminal
2. Navigate to the folder the docker-compose.yml is saved in
3. Run "docker-compose up -d" without quotation marks
4. Run "docker-compose ps" without quotation marks to ensure service is running
5. Connect to mariaDB using HeidiSQL, connect to a DB at port 6033 (a custom port we defined inside docker-compose.yaml)
    1. Username = root
    2. Password = mariadb
6. Add at least 1 user in the users table
7. Login to the user using Postman and continue doing all other commands to test the application

## How to host this service on Alibaba servers?