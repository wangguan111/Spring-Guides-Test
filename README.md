# 1:
# deploy depends_on services in docker for dev debug env:
docker-compose up
# deploy all services in docker:
cd docker

docker-compose up
# deploy xx service in docker:
cd xx

docker-compose up


# 2:
# docker mode
# default profile:

# debug mode
# dev profile:
# database, message, sql and cloud
--spring.profiles.active=dev


# 3:
# test url:
# #-----------web------------#
# greeting
http://localhost:8081/greeting
# consuming
http://localhost:8081/consuming
# scheduled
http://localhost:8081/scheduledTasks
# uploadingFiles
http://localhost:8081/
# actuator
http://localhost:9081/actuator/health
# multiModule
http://localhost:8081/multi
#  async
http://localhost:8081/async
# caching
http://localhost:8081/caching


# #-----------DataBase------------#
# jdbc
http://localhost:8082/jdbc/string

http://localhost:8082/jdbc/string1

http://localhost:8082/jdbc/object

http://localhost:8082/jdbc/list

http://localhost:8082/jdbc/count
# neo4j
http://localhost:8082/neo4j
# transactions
http://localhost:8082/book/all

http://localhost:8082/book/big

http://localhost:8082/book/null
# jpa
http://localhost:8082/jpa/id

http://localhost:8082/jpa/name
# mongodb
http://localhost:8082/mongodb/first

http://localhost:8082/mongodb/last
# bath
http://localhost:8082/bath

http://localhost:8082/bath?name=JILL
# cross
http://localhost:8082/greeting

http://localhost:8082/greeting-config


# #-----------Message------------#
# redis
http://localhost:8083/redis
# rabbitmq
http://localhost:8083/rabbitmq
# jms
http://localhost:8083/jms
# stomp web socket
http://localhost:8083/


# #-----------configuration-service------------#
# configuration service
http://localhost:8084
# get
http://localhost:8084/config/spring-cloud.properties
# #-----------eureka-service------------#
# eureka service
http://localhost:8085


# #-----------eureka-client------------#
# eureka client provider
http://localhost:8086/service


#-----------cloud------------#
# eureka client consume
http://localhost:8087/feign

http://localhost:8087/rest
# configuration client
http://localhost:8087/message
# refresh configuration client
Post http://localhost:9087/actuator/refresh


# #-----------sql------------#
# mysql
Post http://localhost:8088/mysql/add

Post http://localhost:8088/mysql/add?name=name&email=mail

http://localhost:8088/mysql/all


# #-----------gate------------#
# gate
http://localhost:8089/get

http://localhost:8089/delay/3
--header 'Host: www.hystrix.com'