package com.cavaliersoftware.sample.client;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/19/17
 * Time: 9:46 AM
 * <p>
 */
public class MaskEventTest {

    @Test
    public void getBitMask() throws Exception {
        MaskEvent event = new MaskEvent();
        event.setBitMask( 0x03 );
        assertEquals( 0x03, event.getBitMask() );
        event.setBitMask( 0x10 );
        assertEquals( 0x10, event.getBitMask() );
    }

}