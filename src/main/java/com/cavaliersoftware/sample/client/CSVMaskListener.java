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
 */
public class CSVMaskListener implements MaskListener {


    private BufferedWriter writer;
    private MaskUtility maskUtility;

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
    }

    public String getCharacterForBit( int mask, int bitNum ) {
        return maskUtility.isBitOn( mask, bitNum ) ? "1" : "0";
    }
}
