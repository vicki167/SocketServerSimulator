package com.cavaliersoftware.sample.util;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 9:34 AM
 * <p>
 */
public class MaskUtility {

    public boolean isBitOn( int bitMask, int bitNumber ) {
        return ( bitMask & ( 1 << bitNumber ) ) != 0;
    }

    public int turnBitOn( int bitMask, int bitNumber ) {
        return bitMask | (1 << bitNumber);
    }
}
