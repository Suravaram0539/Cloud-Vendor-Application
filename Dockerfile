FROM amazoncorretto:22

LABEL version="1.0"

EXPOSE 8080:8080

WORKDIR /app

COPY target/CloudVendorRESTAPI-0.0.1-SNAPSHOT.jar /app/cloud-vendor.jar

ENTRYPOINT ["java", "-jar", "cloud-vendor.jar"]