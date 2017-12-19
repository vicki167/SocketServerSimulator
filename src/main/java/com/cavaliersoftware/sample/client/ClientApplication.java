package com.cavaliersoftware.sample.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
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
public class ClientApplication extends WindowAdapter {

    private static String HOST_NAME = "127.0.0.1";
    private static int PORT = 9999;

    private List<MaskListener> listeners;
    private ClientFrame frame;
    private boolean running = true;
    private int mask = -1;
    private SocketChannel client = null;
    private Socket socket;


    public ClientApplication() {
        super();
        listeners = new ArrayList<MaskListener>();
    }

    public void connectAndListen() {
        try {

            int result = -1;


            socket = new Socket( HOST_NAME, PORT );
            BufferedInputStream is = new BufferedInputStream( socket.getInputStream() );
            while ( running ) {
                InetSocketAddress hostAddress = new InetSocketAddress( HOST_NAME, PORT );
                if ( is.available() > -1 ) { // in our example, we are only dealing with one byte, so read one and handle
                    // read the one byte
                    result = is.read();
                }

                // alert the UI (or any other listeners)
                if ( mask != result ) {
                    mask = result;
                    MaskEvent event = new MaskEvent();
                    event.setBitMask( result );
                    for ( MaskListener listener : listeners ) {
                        listener.bitChanged( event );
                    }
                }
                if ( client != null ) {
                    client.close();
                }
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
        frame.addWindowListener( application );
        application.connectAndListen();

    }

    /**
     * Closes down the client socket when the window is in the process of being closed.
     *
     * @param e
     */
    @Override
    public void windowClosing( WindowEvent e ) {
        super.windowClosing( e );
        System.out.println( "Closing client based on Window Closing" );
        running = false;
        if ( client != null ) {
            try {
                client.close();
            } catch ( IOException e1 ) {
                // ok to ignore
            }
        }
        if ( socket != null ) {
            try {
                socket.close();
            } catch ( IOException e1 ) {
                // ok to ignore
            }
        }
    }
}
