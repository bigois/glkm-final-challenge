# Define a imagem do Amazon Corretto visto que a OpenJDK está depreciada
FROM amazoncorretto:17

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o .JAR do projeto gerado pelo Spring Boot para o container
COPY payment-api.jar app.jar

# Expõe a porta de comunicação
EXPOSE 8084

# Inicia a aplicação no container
CMD ["java", "-jar", "app.jar"]
