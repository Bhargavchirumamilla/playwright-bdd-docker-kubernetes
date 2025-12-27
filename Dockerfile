FROM mcr.microsoft.com/playwright/java:v1.56.0-jammy

ARG DEBIAN_FRONTEND=noninteractive
ENV DEBIAN_FRONTEND=noninteractive
ENV DISPLAY=:99

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    xvfb \
    fluxbox \
    x11vnc \
    novnc \
    websockify \
    tzdata && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
COPY testng.xml .

RUN mvn clean compile -DskipTests

EXPOSE 6080

# 🔑 IMPORTANT: exits after tests → Job completes
CMD bash -c "\
  Xvfb :99 -screen 0 1920x1080x24 & \
  fluxbox & \
  x11vnc -display :99 -rfbport 5900 -nopw -forever -shared & \
  websockify --web=/usr/share/novnc/ 6080 localhost:5900 & \
  mvn test"
