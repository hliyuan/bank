# 使用 OpenJDK 21 作为基础镜像
FROM openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 将构建的 Jar 包复制到镜像中
COPY target/bank-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用端口（与 application.properties 中的 server.port 一致）
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]