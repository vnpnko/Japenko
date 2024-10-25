FROM debian:buster

# Install required packages and Java 22
RUN apt-get update -y && \
    apt-get install -y wget libxext-dev libxrender-dev libxtst-dev && \
    dpkg --add-architecture i386 && \
    apt-get update -y && \
    apt-get install -y libc6:i386 libxext6:i386 libxrender1:i386 libxtst6:i386 && \
    wget https://download.java.net/java/GA/jdk22.0.1/c7ec1332f7bb44aeba2eb341ae18aca4/8/GPL/openjdk-22.0.1_linux-x64_bin.tar.gz && \
    tar xvf openjdk-22.0.1_linux-x64_bin.tar.gz && \
    mv jdk-22.0.1 /usr/local/ && \
    ln -s /usr/local/jdk-22.0.1/bin/java /usr/bin/java && \
    ln -s /usr/local/jdk-22.0.1/bin/javac /usr/bin/javac

# Add the application's JAR file
COPY out/artifacts/bomberman_jar/bomberman.jar /app/myapp.jar

# Run the application
CMD ["java", "-jar", "/app/myapp.jar"]
