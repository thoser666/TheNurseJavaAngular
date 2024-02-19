FROM ubuntu:latest
LABEL authors="Steffen"

ENTRYPOINT ["top", "-b"]

ARG USER_HOME_DIR="/root"


