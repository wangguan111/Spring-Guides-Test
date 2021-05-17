echo off

echo ---env setting---
build_version=test
deploy_port=8085
build_name=myapp
echo $build_version
echo $deploy_port
echo $build_name

# echo ---copy to dest---
# cp target/$build_version.jar $build_version.jar

echo ---start docker---
# docker stop $(docker ps -aq)
# docker rm $(docker ps -aq)
# docker rmi $(docker images -q)
# docker build -t $build_name .
# ocker run -d -p $deploy_port:$deploy_port --name $build_name $build_name
docker-compose up -d