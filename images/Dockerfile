# Almost a copy of
# https://raw.githubusercontent.com/elye/demo_android_docker/master/scripts/PackSdkDockerfile
FROM openjdk:8

ENV SDK_URL="https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip" \
    ANDROID_HOME="/usr/local/android-sdk" \
    ANDROID_VERSION=26 \
    ANDROID_BUILD_TOOLS_VERSION=26.0.2

RUN mkdir "$ANDROID_HOME"
RUN useradd -s /bin/bash -m android
RUN chown android:android "$ANDROID_HOME"

USER android

# Download Android SDK
RUN mkdir /home/android/.android \
    && touch /home/android/.android/repositories.cfg \
    && cd "$ANDROID_HOME" \
    && curl -s -o sdk.zip $SDK_URL \
    && unzip sdk.zip \
    && rm sdk.zip \
    && yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses

# Install Android Build Tool and Libraries
RUN $ANDROID_HOME/tools/bin/sdkmanager --update
RUN $ANDROID_HOME/tools/bin/sdkmanager "build-tools;${ANDROID_BUILD_TOOLS_VERSION}" \
    "platforms;android-${ANDROID_VERSION}" \
    "platform-tools"
