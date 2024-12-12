FROM eclipse-temurin:23-noble

WORKDIR /src

COPY mvnw .
COPY pom.xml .

COPY .mvn .mvn
COPY src src

#make mvnw executable
RUN chmod a+x mvnw package -Dmaven.test.skip=true
#/src/target/revision-0.01-SPANSJHOT.jar

FROM eclipse-temurin:23-jre-noble

WORKDIR /app

COPY --from=builder /src/target/revision-0.01-SPANSJHOT.jar app.jar

ENV PORT=8080
ENV MY_API_PASS_KEY=kyz789

EXPOSE 8080

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar