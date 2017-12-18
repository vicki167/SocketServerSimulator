package com.cavaliersoftware.sample.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/16/17
 * Time: 4:43 AM
 * <p>
 */
public class ClientApplication {

    private static String HOST_NAME = "127.0.0.1";
    private static int PORT = 9999;

    private List<MaskListener> listeners;
    private ClientFrame frame;
    private boolean running = true;
    private int mask = -1;


    public ClientApplication() {
        super();
        listeners = new ArrayList<MaskListener>(  );
    }

    public void connectAndListen() {
        try {

            int result = -1;

            SocketChannel client = null;
            InetSocketAddress hostAddress = new InetSocketAddress( HOST_NAME, PORT );
            client = SocketChannel.open( hostAddress );
            while ( running ) {
                // using java.nio package
                ByteBuffer byteData = ByteBuffer.allocate( 1 );
                System.out.println( String.format( "Read %s bytes.", client.read( byteData ) ) );
                result = byteData.get( 0 );

                // alert the UI (or any listeners)
                if ( mask != result ) {
                    mask = result;
                    MaskEvent event = new MaskEvent();
                    event.setBitMask( result );
                    for ( MaskListener listener : listeners ) {
                        listener.bitChanged( event );
                    }
                }
            }
            if ( client != null ) {
                client.close();
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void addMaskListener( MaskListener listener ) {
        listeners.add( listener );
    }

    public static void main( String[] args ) {
        if ( args.length > 0 ) {
            HOST_NAME = args[ 0 ];
        }
        if ( args.length > 1 ) {
            PORT = Integer.parseInt( args[ 1 ] );
        }

        System.out.println( "Starting client" );

        // create the UI
        ClientFrame frame = new ClientFrame();
        // show the UI
        frame.showIt();

        // create the Application
        ClientApplication application = new ClientApplication();
        application.addMaskListener( frame );
        application.connectAndListen();

        System.out.println( "Stopping client" );
    }


}
