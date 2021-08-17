FROM openjdk:11
ARG JAR_FILE=target/proposta-0.0.1.jar
ENV HOST_DB=postgres
ENV PORT_DB=5432
ENV HOST_SISTEMA_ANALISE=analise
ENV PORT_SISTEMA_ANALISE=9999
ENV HOST_SISTEMA_CARTOES=contas
ENV PORT_SISTEMA_CARTOES=8888
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
