## What
A basic image to run spring-boot application under docker and/or kubernetes

## How

This base images takes care of bootraping a "sane" sping-boot application by adding shell startup scripts and logback configurations per environment. Logback configurations assume we are using the `base-logging` module, which in turn defines our special logback config.

The shell scripts will expose environment vairiables letting the application know where and how it should run. For this example, we add a spring profile (`ocp` for kubernetes, `docker` for docker envs), we set the logging config accordingly, and start java with `best practice` paramaters for dockerized java apps...

## Why
Exposes a standardized OS for all spring apps by taking care of parameters which are related to the environment the app will be running (so the java code does not have to handle those). 