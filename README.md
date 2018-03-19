bikes-server
===========
This is my side project developed in spare time. (still in progress)

This web application aims to provide platform for users to view and manage their favourite city bikes stations.
There are a few features available - e.g., logging in, getting list of all bike stations in particular city or country, 
adding and viewing favourite stations - just to name the most important ones.


All data is being downloaded from <https://www.nextbike.net/>
 
This app provides json API through web socket connection.


I've added travis CI integration for master branch, building last commit status can be found here: [![Build Status](https://travis-ci.org/pgrudev/bikes-server.svg?branch=master)](https://travis-ci.org/pgrudev/bikes-server)

Technologies used:
---------------
  * **akka** actors - client sessions
  * **netty** for managing web-socket connection
  * **spring boot** - dependency injection, configuration
  * **spring security** - implementation of encryption and decryption methods 
  * **junit** - testing 
  * **log4j2** - logging
 