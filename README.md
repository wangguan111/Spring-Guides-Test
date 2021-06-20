# deploy:
docker-compose up

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
http://localhost:9001/actuator/health
# jms
http://localhost:8085/jms
# multiModule
http://localhost:8085/multi
# bath
http://localhost:8085/bath

http://localhost:8085/bath?name=JILL
#transactions
http://localhost:8085/book/all

http://localhost:8085/book/big

http://localhost:8085/book/null
