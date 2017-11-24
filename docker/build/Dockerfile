FROM openjdk:8

MAINTAINER eijenson

ENV DEBIAN_FRONTEND noninteractive

# Install dependencies
RUN dpkg --add-architecture i386 && \
    apt-get update && apt-get install -yq libc6:i386 libstdc++6:i386 zlib1g:i386 libncurses5:i386 --no-install-recommends && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Download and untar SDK
ENV ANDROID_SDK_URL https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip
RUN curl -L -O "${ANDROID_SDK_URL}" && \
		unzip -d /usr/local/android-sdk sdk-tools-linux-3859397.zip && \
		rm sdk-tools-linux-3859397.zip
ENV ANDROID_HOME /usr/local/android-sdk
ENV PATH ${ANDROID_HOME}/tools:${ANDROID_HOME}/tools/bin:$PATH

# Install Android SDK components
RUN echo y | sdkmanager "platform-tools" 
RUN echo y | sdkmanager "extras;google;m2repository" 
RUN echo y | sdkmanager "extras;android;m2repository" 
RUN echo y | sdkmanager "platforms;android-25" 
RUN echo y | sdkmanager "build-tools;27.0.0"
RUN echo y | sdkmanager "platforms;android-27"

# Set Enviroment
ENV PATH ${ANDROID_HOME}/platform-tools:$PATH
ENV PATH ${ANDROID_HOME}/build-tools/27.0.0:$PATH

# Accept Licenses
RUN (while sleep 1; do echo "y"; done) | sdkmanager --licenses

# Set Android Project
COPY . /project
WORKDIR /project
RUN rm local.properties
RUN ./gradlew assembledebug clean
RUN mkdir ./app/build && mkdir ./app/build/outputs
VOLUME ./app/build/outputs

ENV TERM dumb

