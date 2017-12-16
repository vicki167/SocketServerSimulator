package com.cavaliersoftware.sample.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.BitSet;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/16/17
 * Time: 3:59 AM
 * <p>
 */
public class ServerApplication {

    public static void main( String[] args ) {
        int data = 0x0006; // 0000:0110

//        BitSet bitSet = new BitSet( 8 );
//        bitSet.set( 1, 3 );
//
//        System.out.println( bitSet );

        // create a socket server
        int portNumber = 9999;
        if ( args.length > 0  ) {
            portNumber = Integer.parseInt( args[ 0 ] );
        }
        // start a socket server
        System.out.println( "Starting Server to listen for client requests...." );
        try {
            ServerSocket serverSocket = new ServerSocket( portNumber );
            while ( true ) { // come up with a better way to stop
                Socket clientSocket = serverSocket.accept();
                System.out.println( "-- Accepted client connection" );
                // todo - use Thread Pool Executor
                ClientDataHandler handler = new ClientDataHandler( clientSocket );
                new Thread( handler ).run();
            }
        } catch ( Exception ex ) {
            ex.printStackTrace(); // todo - logging later
        }

    }
}
