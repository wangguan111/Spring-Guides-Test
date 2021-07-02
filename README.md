# deploy docker in dev env:
docker-compose up
# deploy docker in jenkins:
cd docker
./startup.sh

# test url:
# greeting
http://localhost:8085/greeting
# consuming
http://localhost:8085/consuming
# scheduled
http://localhost:8085/scheduledTasks
# dataAccess
http://localhost:8085/dataAccess/string

http://localhost:8085/dataAccess/string1

http://localhost:8085/dataAccess/object

http://localhost:8085/dataAccess/list

http://localhost:8085/dataAccess/count
# uploadingFiles
http://localhost:8085/
# redis
http://localhost:8085/redis
# rabbitmq
http://localhost:8085/rabbitmq
# neo4j
http://localhost:8085/neo4j
# actuator
http://localhost:8081/actuator/health
# jms
http://localhost:8085/jms
# multiModule
http://localhost:8085/multi
# bath
http://localhost:8085/bath

http://localhost:8085/bath?name=JILL
# transactions
http://localhost:8085/book/all

http://localhost:8085/book/big

http://localhost:8085/book/null
# jpa
http://localhost:8085/jpa/id

http://localhost:8085/jpa/name
# mongodb
http://localhost:8085/mongodb/first

http://localhost:8085/mongodb/last
#  async
http://localhost:8085/async
# stomp web socket
http://localhost:8086/
# caching
http://localhost:8085/caching
# cross
http://localhost:8087/greeting

http://localhost:8087/greeting-config
# configuration client
http://localhost:8082/message
# configuration service
http://localhost:8084
# refresh configuration client
Post http://localhost:8083/actuator/refresh