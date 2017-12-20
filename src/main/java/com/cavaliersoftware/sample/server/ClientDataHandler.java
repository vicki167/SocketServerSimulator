package com.cavaliersoftware.sample.server;

import com.cavaliersoftware.sample.util.GeoUtility;
import com.cavaliersoftware.sample.util.MaskUtility;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import static com.cavaliersoftware.sample.util.GeoUtility.WORLD;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/16/17
 * Time: 5:04 AM
 * <p>
 */
public class ClientDataHandler implements Runnable {

    private Socket client;
    private EarthquakeQuery earthquakeQuery;
    private boolean running = true;
    private MaskUtility maskUtility;
    private GeoUtility geoUtility;

    public ClientDataHandler( Socket client, EarthquakeQuery earthquakeQuery  ) {
        super();
        this.client = client;
        this.earthquakeQuery = earthquakeQuery;
        this.maskUtility = new MaskUtility();
        this.geoUtility = new GeoUtility();
    }

    public void run() {

        try {

            System.out.println( "-- Opening output stream to client socket" );
            // get the output stream from the socket
            OutputStream outputStream = client.getOutputStream();

            // every 60s, send some data if necessary
            while ( running ) {
                int data = 0x00;
                // get the latest earthquake data
                List<double[]> points = earthquakeQuery.getEarthquakePoints();
                // set the first flag for the world if we have any data
                if ( points.size() > 0 ) {
                    data = maskUtility.turnBitOn( data, WORLD );
                }
                // set the bit flag for the appropriate continents
                for ( double[] point : points ) {
                    // break out of the loop if all the bits are flipped
                    if ( data == 0x0FF ) {
                        break;
                    }
                    data = maskUtility.turnBitOn( data, geoUtility.getBitForPoint( point ) );
                }
                System.out.println( String.format( "-- Sending data to the client on Thread %s", Thread.currentThread().getName() ) );
                outputStream.write( data );
                // pause the Thread to wait before checking and sending again
                try {
                    Thread.sleep( 60000 );   // 60 seconds
                } catch ( InterruptedException e ) {
                    // end loop if we get cancelled
                    running = false;
                }
            }
        } catch ( IOException ex ) {
            System.out.println( String.format( "-- Error on Thread %s :",  Thread.currentThread().getName() ) );
            System.out.println( "---- " + ex.getMessage() );
        } finally {
            try {
                client.close();
            } catch ( IOException e ) {
                // ok to ignore
            }
        }
    }

}
