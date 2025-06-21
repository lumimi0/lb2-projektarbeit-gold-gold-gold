# Multi-stage Build
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Gradle Wrapper Dateien komplett kopieren
COPY gradlew gradlew.bat ./
COPY gradle/ gradle/

# Gradle Wrapper ausführbar machen und wrapper herunterladen
RUN chmod +x gradlew
RUN ./gradlew --version

# Build-Dateien kopieren
COPY build.gradle settings.gradle ./

# Dependencies laden (für bessere Cache-Nutzung)
RUN ./gradlew dependencies --no-daemon || true

# Source Code kopieren
COPY src/ src/

# Application bauen
RUN ./gradlew bootJar --no-daemon

# Production Stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# JAR-Datei kopieren
COPY --from=builder /app/build/libs/*.jar app.jar

# Non-root user für Sicherheit
RUN addgroup --system --gid 1001 spring && \
    adduser --system --uid 1001 --gid 1001 spring
USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]