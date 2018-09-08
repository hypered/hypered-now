#!/usr/bin/env bash

set -xeuo pipefail

docker run \
  --privileged \
  -it \
  --rm \
  -v $(pwd)/../hypered-now/project:/project \
  -v /dev/bus/usb:/dev/bus/usb \
  images.reesd.com/reesd/android \
  bash
