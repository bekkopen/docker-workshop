docker-workshop
===============


To get started:

#### 1. Clone this repo:
```git clone https://github.com/bekkopen/docker-workshop.git```

#### 2. Install VirtualBox
Install VirtualBox from https://www.virtualbox.org/wiki/Downloads

#### 3. Install vagrant
Install vagrant from https://www.vagrantup.com/ and fetch our Docker-ready 
vagrant image by running 
```
cd docker-workshop
vagrant up
```

#### 4. Pull docker base image
Inside the vagrant box, pull the ubuntu base image for Docker:
```
vagrant ssh
cd /vagrant
docker run ubuntu:latest echo "hello world"
```

#### 5. Check out the presentation 
http://bekkopen.github.io/docker-workshop

#### Troubleshooting

##### Installing Vagrant or VirtualBox
If you are having trouble installing VirtualBox, Vagrant or the Vagrant Image, you can follow the official installation instructions on https://docs.docker.com/ 

##### Problems running vagrant up on Windows
Make sure virtualization is turned on in you BIOS

##### Port collision
If you already have mongodb running on your local machine, there will be a port collision when trying to run `vagrant up`. See this note from Torgeir on how to solve this: https://github.com/bekkopen/docker-workshop/issues/1#event-162609858


# License
The MIT License (MIT)

Copyright (c) 2014 Tobias Gulbrandsen Waaler, Stian Mathiassen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
