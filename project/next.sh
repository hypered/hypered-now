#!/usr/bin/env bash

# After ./compile.sh as run, create a releasable .apk.
# This is interactive.

set -xeuo pipefail

rm -f hypered-now-unsigned-aligned.apk

/usr/local/android-sdk/build-tools/26.0.2/zipalign -v -p 4 \
  application/build/outputs/apk/release/application-release-unsigned.apk \
  hypered-now-unsigned-aligned.apk

/usr/local/android-sdk/build-tools/26.0.2/apksigner sign \
  --ks my-release-key.jks \
  --out hypered-now.apk \
  hypered-now-unsigned-aligned.apk

/usr/local/android-sdk/build-tools/26.0.2/apksigner verify hypered-now.apk
