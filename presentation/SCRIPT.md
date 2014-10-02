
### Basic Docker commands

##### Pull image from index.docker.io

    docker pull ubuntu:latest

##### List all images

    docker images
    docker images --tree

##### Start a container

    docker run ubuntu echo "Hello World"

##### List containers

    docker ps --all

##### Interactive container

    docker run --interactive --tty ubuntu /bin/bash
    # echo "Hello World" > /somefile.txt
    # exit
    docker ps --all # Container died
    # Re-attach container. Re-use same disk
    docker start --attach --interactive a9f2bd405f9e
    docker diff

##### Create new image
Base the new image on a container. Will create a new layer on top of the base image

    docker commit a9f2bd405f9e myimage:mytag

##### Create image with node.js

    docker run --name node-build --interactive --tty ubuntu /bin/bash
    # apt-get update
    # apt-get install nodejs npm
    # exit
    docker commit node-build ubuntu-nodejs

### Dockerfile
Each RUN-command creates a new layer. Layers are re-used

    FROM ubuntu
    RUN apt-get update
    RUN apt-get install -y nodejs npm

    docker build --tag="ubuntu-nodejs"
    docker build

###### Using it

    docker run --interactive --tty ubuntu-nodejs /bin/bash
    # nodejs --version

##### Copy files
Copy a file from the host to the image

    FROM ubuntu-nodejs
    ADD ./src /var/apps/nodejs/

##### Startup
Command to run when starting the container

    FROM ubuntu-nodejs
    ADD ./src /var/apps/nodejs/
    ENTRYPOINT ["nodejs", "/var/apps/nodejs/index.js"]

##### User
Run command as user

    FROM ubuntu-nodejs
    ADD ./src /var/apps/nodejs/
    RUN cd /var/apps/nodejs/; npm install
    ENTRYPOINT ["nodejs", "/var/apps/nodejs/index.js"]
    USER daemon

##### Ports
Specify which ports the container should make available

    FROM ubuntu-nodejs
    ADD ./src /var/apps/nodejs/
    RUN cd /var/apps/nodejs/; npm install
    ENTRYPOINT ["nodejs", "/var/apps/nodejs/index.js"]
    USER daemon
    EXPOSE 8888

    docker build -t node-app .

##### Publish ports
Ports are not automatically published

    docker run -p 8888:8888 ubuntu-nodejs

##### Volumes

    FROM mongo:2.4.10
    ENTRYPOINT mongod
    VOLUME /data/db

    docker build -t ubuntu-mongo .

    docker run --volume /tmp/data:/data/db ubuntu-mongo


##### Link
Using --link to get environment properties

    docker run -t -i --link="mongodb:mongodb" ubuntu bash
    # env | grep MONGODB

Show two slides

##### Linking with application

    docker run --publish 8888:8888
               --link="mongodb:mongodb"
               --detach ubuntu-nodejs
    docker ps

