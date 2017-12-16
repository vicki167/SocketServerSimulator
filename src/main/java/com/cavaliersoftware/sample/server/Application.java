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
public class Application {

    public static void main( String[] args ) {
        System.out.println( "Hello, World!" );
        int data = 0x0006; // 0000:0110

        BitSet bitSet = new BitSet( 8 );
        bitSet.set( 1, 3 );

        System.out.println( bitSet );

        // create a socket server
        int portNumber = 9999;
        if ( args.length > 0  ) {
            portNumber = Integer.parseInt( args[ 0 ] );
        }
        try {
            ServerSocket serverSocket = new ServerSocket( portNumber );
            Socket clientSocket = serverSocket.accept();
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write( data );
            outputStream.flush(); // does nothing, but keep here in case we upgrade
            outputStream.close();
        } catch ( Exception ex ) {
            ex.printStackTrace(); // todo - logging later
        }

    }
}
