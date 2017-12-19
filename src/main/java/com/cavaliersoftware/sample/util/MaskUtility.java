package com.cavaliersoftware.sample.util;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 9:34 AM
 * <p>
 */
public class MaskUtility {

    /**
     * Detects if the specified bit is on.
     * @param bitMask the mask to evaluate
     * @param bitNumber the bit number to inspect
     * @return  true if the bit is on, false otherwise
     */
    public boolean isBitOn( int bitMask, int bitNumber ) {
        return ( bitMask & ( 1 << bitNumber ) ) != 0;
    }

    /**
     * Turns the specified bit on or off and returns the resulting mask.  If the bit is outside of the range of 0-7,
     * do nothing
     * @param bitMask the mask to evaluate
     * @param bitNumber the bit number to inspect
     * @return the bitmask with the newly flipped bit
     */
    public int turnBitOn( int bitMask, int bitNumber ) {
        if ( bitNumber < 8 && bitNumber >=0 ) {
            return bitMask | (1 << bitNumber);
        } else {
            return bitMask;
        }
    }
}
