#!/bin/bash
#
# This script exports the environment variables expected by ansible
# from a downloaded credentials file.
#
# usage:
#   eval $(credentials.sh)
#
# 
#

# arguments
FILE=~/Documents/credentials.csv
USER="k8s-provisioner"

awk 'BEGIN { FS = "," } /'$USER'/ { printf "export AWS_ACCESS_KEY_ID=%s\n", $2; printf "export AWS_SECRET_ACCESS_KEY=%s\n", $3; }' $FILE


