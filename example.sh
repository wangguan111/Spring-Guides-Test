## switch to the directory where the script is located
cd $(dirname $0)
cd ../initial

## mvn clean and compile
mvn clean compile

## last command success return 0, otherwise it is not 0
ret=$?

## re != 0 exit, else remove target
if [ $ret -ne 0 ]; then
exit $ret
fi
rm -rf target

./gradlew compileJava
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi
rm -rf build

cd ../complete
mvn clean package
rm -rf target

./gradlew build
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi

rm -rf build
exit
