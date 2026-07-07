FROM maven:3.8.6-openjdk-8 AS build                                                                                                                                                                                               
WORKDIR /app                                                                                                                                                                                                                      
COPY pom.xml .                                                                                                                                                                                                                    
RUN mvn dependency:go-offline -B                                                                                                                                                                                                  
COPY src ./src                                                                                                                                                                                                                    
RUN mvn clean package -DskipTests                                                                                                                                                                                                 
   
FROM openjdk:8-jre-slim                                                                                                                                                                                                           
WORKDIR /app                                                                                                                                                                                                                    
COPY --from=build /app/target/*.war app.war                                                                                                                                                                                     
EXPOSE 8083                                                                                                                                                                                                                       
ENTRYPOINT ["java", "-jar", "app.war"]
