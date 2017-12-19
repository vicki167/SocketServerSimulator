package com.cavaliersoftware.sample.client;

import com.cavaliersoftware.sample.server.ClientDataHandler;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/19/17
 * Time: 12:22 PM
 * <p>
 */
public class ClientApplicationTest implements MaskListener {

    private int data;
    private int innerData;

    @Test
    public void alertListeners() throws Exception {
        ClientApplication app = new ClientApplication();
        // make sure no exception gets thrown
        app.addMaskListener(  null  );
        app.addMaskListener(  this  );
        app.addMaskListener( event -> {
            innerData = event.getBitMask(); // as a rule, i dislike lambdas.  however, this makes this test much easier to read
        } );
        app.alertListeners( new MaskEvent( 0x01 ) );
        assertEquals( 0x01, data );
        assertEquals( 0x01, innerData );
    }

    @Override
    public void bitChanged( MaskEvent event ) {
        this.data = event.getBitMask();
    }
}