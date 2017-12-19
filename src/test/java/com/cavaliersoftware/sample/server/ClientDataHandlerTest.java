package com.cavaliersoftware.sample.server;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 6:41 PM
 * <p>
 */
public class ClientDataHandlerTest {

    @Test
    public void run() throws Exception {
        // mock data points   (should yield 1100:0011 or 0x0C3 )
        double[] point1 = new double[] { -85.0, 12.0 }; // antarctica
        double[] point2 = new double[] { 40.0, -120.0 }; // n. america
        double[] point3 = new double[] { -10.0, -60.0 }; // s. america
        List<double[]> points = new ArrayList<double[]>(  );
        int data = 0x0C3;
        points.add( point1 );
        points.add( point2 );
        points.add( point3 );
        // mock the earthquake query
        EarthquakeQuery mockQuery = Mockito.mock( EarthquakeQuery.class );
        when( mockQuery.getEarthquakePoints() ).thenReturn( points );
        // mock the output stream
        OutputStream outputStream = Mockito.mock( OutputStream.class );
        // mock the client socket
        Socket socket = Mockito.mock( Socket.class );
        when( socket.getOutputStream() ).thenReturn( outputStream );

        // kick off the Thread for the data handler
        ClientDataHandler handler = new ClientDataHandler( socket, mockQuery );
        Thread t = new Thread( handler );
        t.start();

        // kill it after a quarter of a second and test that we got
        Thread.sleep( 250 );
        t.interrupt();

        // verify what we got back from the outputstream
        verify( outputStream, times( 1 ) ).write( data );
    }

}