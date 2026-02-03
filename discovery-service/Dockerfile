# --- STAGE 1: Build ---
FROM maven:3.9.4-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# 1. Copy Maven wrapper and pom (Pre-fetch dependencies)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 2. Fix potential Windows line endings and permissions
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# 3. Go offline to cache dependencies (improves rebuild speed)
RUN ./mvnw dependency:go-offline

# 4. Copy source and build the JAR
COPY src ./src
RUN ./mvnw clean package -DskipTests

# --- STAGE 2: Runtime ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# 5. Copy only the built JAR from the builder stage
# Note: Ensure your pom.xml generates a standard jar name or use a wildcard
COPY --from=builder /app/target/*.jar app.jar

# 6. Run the application
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]