# deploy docker in dev env:
docker-compose up
# deploy docker in jenkins:
cd docker

./startup.sh

# test url:
# greeting
http://localhost:8081/greeting
# consuming
http://localhost:8081/consuming
# scheduled
http://localhost:8081/scheduledTasks
# dataAccess
http://localhost:8081/dataAccess/string

http://localhost:8081/dataAccess/string1

http://localhost:8081/dataAccess/object

http://localhost:8081/dataAccess/list

http://localhost:8081/dataAccess/count
# uploadingFiles
http://localhost:8081/
# redis
http://localhost:8081/redis
# rabbitmq
http://localhost:8081/rabbitmq
# neo4j
http://localhost:8081/neo4j
# actuator
http://localhost:9081/actuator/health
# jms
http://localhost:8081/jms
# multiModule
http://localhost:8081/multi
# bath
http://localhost:8081/bath

http://localhost:8081/bath?name=JILL
# transactions
http://localhost:8081/book/all

http://localhost:8081/book/big

http://localhost:8081/book/null
# jpa
http://localhost:8081/jpa/id

http://localhost:8081/jpa/name
# mongodb
http://localhost:8081/mongodb/first

http://localhost:8081/mongodb/last
#  async
http://localhost:8081/async
# stomp web socket
http://localhost:8082/
# caching
http://localhost:8081/caching
# cross
http://localhost:8083/greeting

http://localhost:8083/greeting-config
# configuration client
http://localhost:8085/message
# configuration service
http://localhost:8084
# refresh configuration client
Post http://localhost:9085/actuator/refresh
# eureka service
http://localhost:8086
# eureka client provider
http://localhost:8087/service
# eureka client consume
http://localhost:8081/feign

http://localhost:8081/rest