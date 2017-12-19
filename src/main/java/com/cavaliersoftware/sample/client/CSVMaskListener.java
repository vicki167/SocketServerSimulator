package com.cavaliersoftware.sample.client;

import com.cavaliersoftware.sample.util.GeoUtility;
import com.cavaliersoftware.sample.util.MaskUtility;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/19/17
 * Time: 11:37 AM
 * <p>
 * A listener that writes the data to a CSV file.
 */
public class CSVMaskListener implements MaskListener {


    private BufferedWriter writer;
    private MaskUtility maskUtility;

    /**
     * Creates the listener using the file passed in as a parameter
     * @param outputFile  the file where the data should be written
     * @throws IOException thrown if any errors are encountered creating or writing the file
     */
    public CSVMaskListener( File outputFile ) throws IOException {
        super();
        System.out.println( String.format( "Using file : %s", outputFile.getAbsolutePath() ));
        maskUtility = new MaskUtility();
        //  create a BufferedWriter
        writer = new BufferedWriter( new FileWriter( outputFile ) );
        // write the header
        writer.write( "time,world,antarctica,africa,asia,australia,europe,north america, south america" );
        writer.newLine();
    }

    /**
     * Handles a MaskEvent.  If the event is equal to the minimum integer value, the writer will be flushed and then closed.
     * @param event  the event to process
     */
    @Override
    public void bitChanged( MaskEvent event ) {
        try {
            if ( event.getBitMask() == Integer.MIN_VALUE ) {
                writer.flush();
                writer.close();
            } else {
                StringBuilder builder = new StringBuilder(  );
                int mask = event.getBitMask();
                builder.append( System.currentTimeMillis() );
                builder.append( "," );
                builder.append( getCharacterForBit( mask, GeoUtility.WORLD ) );
                builder.append( "," );
                builder.append( getCharacterForBit( mask, GeoUtility.ANTARCTICA ) );
                builder.append( "," );
                builder.append( getCharacterForBit( mask, GeoUtility.AFRICA ) );
                builder.append( "," );
                builder.append( getCharacterForBit( mask, GeoUtility.ASIA ) );
                builder.append( "," );
                builder.append( getCharacterForBit( mask, GeoUtility.AUSTRALIA ) );
                builder.append( "," );
                builder.append( getCharacterForBit( mask, GeoUtility.EUROPE ) );
                builder.append( "," );
                builder.append( getCharacterForBit( mask, GeoUtility.NORTH_AMERICA ) );
                builder.append( "," );
                builder.append( getCharacterForBit( mask, GeoUtility.SOUTH_AMERICA ) );
                writeData( builder.toString() );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private void writeData( String data ) throws IOException {
        writer.write( data );
        writer.newLine();
        writer.flush(); // go ahead and flush the data since we do not expect this to bec alled too often
    }

    /**
     * Returns a character based on if the bit is on or off in the mask.
     * @param mask the bitmask to evaluate
     * @param bitNum the number of the bit to check
     * @return  a "1" if the bit is on and a "0" if the bit is off
     */
    public String getCharacterForBit( int mask, int bitNum ) {
        return maskUtility.isBitOn( mask, bitNum ) ? "1" : "0";
    }
}
