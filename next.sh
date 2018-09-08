#!/usr/bin/env bash

set -xeuo pipefail

docker run \
  -it \
  --rm \
  -v $(pwd)/../hypered-now/project:/project \
  images.reesd.com/reesd/android \
  sh -c "cd /project ; ./next.sh"
