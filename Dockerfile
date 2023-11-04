FROM dastan69/java11-3.9.3-dep
COPY src home/apiframework/src
COPY index.html home/apiframework/index.html
COPY pom.xml home/apiframework/pom.xml
COPY testng.xml home/apiframework/testng.xml
WORKDIR home/apiframework
ENTRYPOINT mvn clean test
