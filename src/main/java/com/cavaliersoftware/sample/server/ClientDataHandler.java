package com.cavaliersoftware.sample.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/16/17
 * Time: 5:04 AM
 * <p>
 */
public class ClientDataHandler implements Runnable {

    private Socket client;

    public ClientDataHandler( Socket client ) {
        super();
        this.client = client;
    }

    public void run() {
        int data = 0x0006;

        try {
            System.out.println( "-- Sending data" );
            OutputStream outputStream = client.getOutputStream();
            
            outputStream.write( data );
            outputStream.flush(); // does nothing, but keep here in case we upgrade
            outputStream.close();
        } catch ( IOException ex ) {
            ex.printStackTrace(); // todo - logging later
        }

    }

}
