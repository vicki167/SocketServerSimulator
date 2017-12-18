package com.cavaliersoftware.sample.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.BitSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/16/17
 * Time: 3:59 AM
 * <p>
 */
public class ServerApplication {

    private static int PORT_NUMBER = 9999;
    private ThreadPoolExecutor executor;
    private boolean running = true;

    public ServerApplication() {
        super();
        executor = new ThreadPoolExecutor( 5, 10, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>( 10 ) );
    }



    public static void main( String[] args ) {
        PORT_NUMBER = 9999;
        if ( args.length > 0  ) {
            PORT_NUMBER = Integer.parseInt( args[ 0 ] );
        }
        // create the actual application
        ServerApplication application = new ServerApplication();
        application.start();

    }

    private void start() {
        System.out.println( "Starting Server to listen for client requests...." );
        try {
            ServerSocket serverSocket = new ServerSocket( PORT_NUMBER );
            while ( running ) { // come up with a better way to stop
                Socket clientSocket = serverSocket.accept();
                System.out.println( "-- Accepted client connection" );
                ClientDataHandler handler = new ClientDataHandler( clientSocket );
                executor.execute( handler );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace(); // todo - logging later
        }

    }
}
