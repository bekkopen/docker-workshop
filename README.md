docker-workshop
===============


To get started:

#### 1. Clone this repo:
```git clone https://github.com/bekkopen/docker-workshop.git```

#### 2. Install vagrant
Install vagrant from https://www.vagrantup.com/ and fetch our Docker-ready 
vagrant image by running 
<code>
cd docker-workshop
vagrant up
</code>

#### 3. Pull docker base image
Inside the vagrant box, pull the ubuntu base image for Docker:
<code>
vagrant ssh
cd /vagrant
docker run ubuntu echo "hello world"
</code>


