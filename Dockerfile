FROM openjdk:8-jdk-alpine
ENV app_name "Content analyzer API"
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.merpar.contentanalyzer.ContentAnalyzerApplication"]