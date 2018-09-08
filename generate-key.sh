#!/usr/bin/env bash

# This is interactive.
# This creates project/my-release-key.jks, which will be protected by a password.
# This has output these when I ran them (I have use the same password for <my-alias>:
#
# Generating my-release-key.jks...
# Enter keystore password:  
# Re-enter new password: 
# What is your first and last name?
#   [Unknown]:  VO Minh Thu
# What is the name of your organizational unit?
#   [Unknown]:  Software development
# What is the name of your organization?
#   [Unknown]:  Hypered SPRL
# What is the name of your City or Locality?
#   [Unknown]:  Jambes
# What is the name of your State or Province?
#   [Unknown]:  Namur
# What is the two-letter country code for this unit?
#   [Unknown]:  be
# Is CN=VO Minh Thu, OU=Software development, O=Hypered SPRL, L=Jambes, ST=Namur, C=be correct?
#   [no]:  yes
# 
# Generating 2,048 bit RSA key pair and self-signed certificate (SHA256withRSA) with a validity of 10,000 days
# 	for: CN=VO Minh Thu, OU=Software development, O=Hypered SPRL, L=Jambes, ST=Namur, C=be
# Enter key password for <my-alias>
# 	(RETURN if same as keystore password):  
# [Storing my-release-key.jks]
# 
# Warning:
# The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore my-release-key.jks -destkeystore my-release-key.jks -deststoretype pkcs12".

set -xeuo pipefail

docker run \
  -it \
  --rm \
  -v $(pwd)/../hypered-now/project:/project \
  images.reesd.com/reesd/android \
  sh -c "cd /project ; ./generate-key.sh"
