package com.cavaliersoftware.sample.client;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/19/17
 * Time: 11:54 AM
 * <p>
 */
public class CSVMaskListenerTest {

    @Test
    public void shouldWriteFileBasedOnData() throws Exception {
        // create temp file and have it close on exit
        File file = File.createTempFile( this.getClass().getName(), ".csv" );
        file.deleteOnExit();
        // create listener
        CSVMaskListener listener = new CSVMaskListener( file );
        // trigger the first event
        MaskEvent event = new MaskEvent();
        event.setBitMask( 0x0FF );
        listener.bitChanged( event );
        // trigger the second event
        event.setBitMask( 0x003 );
        listener.bitChanged( event );
        // trigger the second event
        event.setBitMask( 0x000 );
        listener.bitChanged( event );
        // close the file
        event.setBitMask( Integer.MIN_VALUE );
        listener.bitChanged( event );


        // verify the data was written
        BufferedReader reader = new BufferedReader( new FileReader( file ) );
        int i = 0;
        while ( reader.ready() ) {
            String line = reader.readLine();
            if ( i == 0 ) {
                assertEquals( "time,world,antarctica,africa,asia,australia,europe,north america, south america", line );
            } else if ( i == 1 ) {
                assertTrue( line.contains( ",1,1,1,1,1,1,1" ) );
            } else if ( i == 2 ) {
                assertTrue( line.contains( ",1,1,0,0,0,0,0" ) );
            } else if ( i == 3 ) {
                assertTrue( line.contains( ",0,0,0,0,0,0,0" ) );
            }
            i++; // increment counter
        }
        assertEquals( 4, i );
        reader.close();


    }

}