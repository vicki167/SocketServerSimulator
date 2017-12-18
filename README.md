# Socket Server Simulator

Simple socket server for generating and sending binary data.  The USGS Earthquake
Web Service is queries and a bit mask is computed denoting if an earthquake has
occurred for each continent. 

The server runs on port 9999 by default.  This may be changed by passing in a port
number as an argument to the application.

The client tries by default to connect to port 9999 on the loopback address.  This may 
be changed by passing in the hostname and port number as the first two arguments to 
the application.

## Build instructions

'gradle install'

## Executing

### Running the server

* Execute with 'gradle run'
* Load in your favorite IDE and run the ServerApplication class 
* Build the application and then execute 'build/install/SocketServerSimulator/bin/SocketServerSimulator' 


### Running the client

* Load in your favorite IDE and run the ClientApplication class 
* Build the application and then execute 'java -cp build/libs/SocketServerSimulator-1.0-SNAPSHOT.jar com.cavaliersoftware.sample.client.ClientApplication'