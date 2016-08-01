call mvn clean:clean
call mvn package -Dmaven.test.skip=true -U

@pause