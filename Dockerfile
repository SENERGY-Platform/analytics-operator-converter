FROM maven:3.5-jdk-8-onbuild
CMD ["java","-jar","/usr/src/app/target/operator-converter-jar-with-dependencies.jar"]
