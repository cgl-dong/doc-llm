# 使用官方OpenJDK运行环境作为基础镜像
FROM openjdk:8-jre-alpine

# 设置维护者信息
LABEL maintainer="cgl"

# 设置时区
ENV TZ=Asia/Shanghai
RUN apk add --no-cache tzdata \
    && cp /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

# 创建应用目录
RUN mkdir -p /app

# 设置工作目录
WORKDIR /app

# 复制jar文件到容器中
COPY zyplayer-doc.jar /app/app.jar

# 暴露端口
EXPOSE 8080

# 添加健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# 运行应用
ENTRYPOINT ["java", "-jar", "app.jar"]
