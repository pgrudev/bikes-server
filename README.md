bikes-server
===========
This is my side project developed in spare time. (still in progress)

This web application aims to provide platform for users to view and manage their favourite city bikes stations.
There are a few features available - e.g., logging in, getting list of all bike stations in particular city or country, 
adding and viewing favourite stations - just to name the most important ones.


All data is being downloaded from <https://www.nextbike.net/>
 
This app provides json API through web socket connection.


I've added Travis CI integration, build status can be found here:
 

 master: [![Build Status](https://travis-ci.org/pgrudev/bikes-server.svg?branch=master)](https://travis-ci.org/pgrudev/bikes-server)
 develop: [![Build Status](https://travis-ci.org/pgrudev/bikes-server.svg?branch=develop)](https://travis-ci.org/pgrudev/bikes-server)

Technologies used:
---------------
  * **java 8** development language
  * **gradle** dependency management, builds
  * **akka** actors - client sessions
  * **netty** for managing web-socket connection
  * **spring boot** - dependency injection, configuration
  * **spring security** - implementation of encryption and decryption methods 
  * **junit** - testing framework
  * **log4j2** - logging
  * **gson** - json handling
 