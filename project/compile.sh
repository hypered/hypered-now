#!/usr/bin/env bash

# This generates the .gradle directory and download a lot of stuff (but they
# are not on the shared volume).
# This also generates the build/ and application/build/ directories.

set -xeuo pipefail

./gradlew clean
./gradlew assembleRelease

echo This should have generated
echo application/build/outputs/apk/release/application-release-unsigned.apk
