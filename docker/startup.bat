echo off

echo ---env setting---
set build_version=test-0.0.1-SNAPSHOT
set deploy_port=8085
set build_name=myapp
echo %build_version%
echo %deploy_port%
echo %build_name%

:: echo ---copy to dest---
:: copy target\%build_version%.jar %build_version%.jar

echo ---start docker---
:: docker stop $(docker ps -aq)
:: docker rm $(docker ps -aq)
:: docker rmi $(docker images -q)
:: docker build -t %build_name% .
:: docker run -d -p %deploy_port%:%deploy_port% --name %build_name% %build_name%
docker-compose up -d