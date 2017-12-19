# Socket Server Simulator

Simple socket server for generating and sending binary data.  The USGS Earthquake
Web Service is queried and a bit mask is computed denoting if an earthquake has
occurred for each continent. The client makes a connection to the server and listens for any updates
and updates a UI and a CSV file based on the data received.  Because the notification mechanism uses
listeners, it is possible to have other "things" updated based on the data such as a 
listener that prints to the console or one that turns on LEDs attached to the GPIO pins of a
RaspberryPi.

The server runs on port 9999 by default.  This may be changed by passing in a port
number as an argument to the application.

The client tries by default to connect to port 9999 on the loopback address.  This may 
be changed by passing in the hostname and port number as the first two arguments to 
the application.

Some design notes :
* I did not worry about setting up logging.  Instead, I just printed to System.out
* I chose not to read some of the configurable fields from a properties file.  That would be
an important thing to add for a "real" application.
* I did not use the Java NIO package to construct this demo.  Instead, I went wtih the older 
style comms.  I can update if desired.  Paul had mentioned working with older versions of the JDK, so
I wanted to have some "old school" code in the example
* The unit testing could be more complete and test more error conditions
* Some methods were left public even if their access could be more restrictive for this sample.  This is
done to ease testing and maintain an API that could be used in the future.

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