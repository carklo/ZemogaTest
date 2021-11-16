FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.10_9_openj9-0.24.0
ENV STAGE_NAME certification
WORKDIR /usr/app
COPY build/libs/Zemoga Test-1.0.jar .
COPY extras/newrelic/ newrelic/
CMD java -javaagent:newrelic/newrelic.jar -Dnewrelic.environment=$STAGE_NAME -Xms768m -Xmx768m -jar -Dhttps.protocols=TLSv1.2 Zemoga Test-1.0.jar
