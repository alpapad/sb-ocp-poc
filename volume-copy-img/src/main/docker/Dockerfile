FROM alpine:latest

USER root

RUN mkdir /data

ADD entrypoint.sh /data/entrypoint.sh


RUN mkdir -p /data/in
RUN mkdir -p /data/out

RUN chown -R 1001:0 /data && chmod -R g=u /data && chmod +x /data/entrypoint.sh

USER 1001

ENTRYPOINT ["/data/entrypoint.sh"]
CMD ["/data/entrypoint.sh"]