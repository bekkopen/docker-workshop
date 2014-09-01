FROM ubuntu:14.04

RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
RUN echo 'deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen' | sudo tee /etc/apt/sources.list.d/mongodb.list
RUN apt-get update
RUN apt-get --yes install mongodb-org

VOLUME /data/db
EXPOSE 27017

ENTRYPOINT mongod


#
# USAGE:
# cd /vagrant/mongodb
# docker build --tag mongodb .
# docker run --detach --name mongodb -P mongodb
#
