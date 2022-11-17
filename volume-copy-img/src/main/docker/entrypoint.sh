#!/bin/sh

while [ ! -f /data/wait.txt ]; do sleep 1; done


cp -R /data/in/ /data/out/

exit 0;