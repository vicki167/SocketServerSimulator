package com.cavaliersoftware.sample.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 9:36 AM
 * <p>
 */
public class MaskUtilityTest {

    @Test
    public void isBitOn() throws Exception {
        MaskUtility util = new MaskUtility();
        // 0000:0001 = 1
        assertTrue( util.isBitOn( 0x01, 0 ) );
        // 0000:0010 = 2
        assertTrue( util.isBitOn( 0x02, 1 ) );
        // 0000:0100 = 4
        assertTrue( util.isBitOn( 0x04, 2 ) );
        // 0000:1000 = 8
        assertTrue( util.isBitOn( 0x08, 3 ) );
        // 0001:0000 = 16
        assertTrue( util.isBitOn( 0x10, 4 ) );
        // 0010:0000 = 32
        assertTrue( util.isBitOn( 0x20, 5 ) );
        // 0100:0000 = 64
        assertTrue( util.isBitOn( 0x40, 6 ) );
        // 1000:0000 = 128
        assertTrue( util.isBitOn( 0x80, 7 ) );
    }

    @Test
    public void turnBitOn() throws Exception {
        MaskUtility util = new MaskUtility();
        // test first and last bit on an empty mask
        // 0000:0001 = 1
        int result = util.turnBitOn( 0x00, 0 );
        assertTrue( util.isBitOn( result, 0 ) );
        assertFalse( util.isBitOn( result, 1 ) );
        assertFalse( util.isBitOn( result, 2 ) );
        assertFalse( util.isBitOn( result, 3 ) );
        assertFalse( util.isBitOn( result, 4 ) );
        assertFalse( util.isBitOn( result, 5 ) );
        assertFalse( util.isBitOn( result, 6 ) );
        assertFalse( util.isBitOn( result, 7 ) );
        // 1000:0000 = 128
        result = util.turnBitOn( 0x00, 7 );
        assertTrue( util.isBitOn( result, 7 ) );
        assertFalse( util.isBitOn( result, 0 ) );
        assertFalse( util.isBitOn( result, 1 ) );
        assertFalse( util.isBitOn( result, 2 ) );
        assertFalse( util.isBitOn( result, 3 ) );
        assertFalse( util.isBitOn( result, 4 ) );
        assertFalse( util.isBitOn( result, 5 ) );
        assertFalse( util.isBitOn( result, 6 ) );

        // test first and last on a full mask
        // 1111:1111
        result = util.turnBitOn( 0xFF, 7 );
        assertTrue( util.isBitOn( result, 0 ) );
        assertTrue( util.isBitOn( result, 1 ) );
        assertTrue( util.isBitOn( result, 2 ) );
        assertTrue( util.isBitOn( result, 3 ) );
        assertTrue( util.isBitOn( result, 4 ) );
        assertTrue( util.isBitOn( result, 5 ) );
        assertTrue( util.isBitOn( result, 6 ) );
        assertTrue( util.isBitOn( result, 7 ) );
        // 1111:1111
        result = util.turnBitOn( 0xFF, 0 );
        assertTrue( util.isBitOn( result, 0 ) );
        assertTrue( util.isBitOn( result, 1 ) );
        assertTrue( util.isBitOn( result, 2 ) );
        assertTrue( util.isBitOn( result, 3 ) );
        assertTrue( util.isBitOn( result, 4 ) );
        assertTrue( util.isBitOn( result, 5 ) );
        assertTrue( util.isBitOn( result, 6 ) );
        assertTrue( util.isBitOn( result, 7 ) );
    }
}