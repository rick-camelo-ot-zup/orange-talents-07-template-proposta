FROM openjdk:11
ARG JAR_FILE=target/proposta-0.0.1.jar
ENV HOST_DB=postgres:5432/db_propostas
ENV URL_SISTEMA_ANALISE=http://analise:9999/api/solicitacao
ENV URL_SISTEMA_CARTOES=http://contas:8888/api/cartoes
ENV KEYCLOAK_ISSUER_URI=http://keycloak:8080/auth/realms/proposta
ENV KEYCLOAK_JWKS_URI=http://keycloak:8080/auth/realms/proposta/protocol/openid-connect/certs
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
