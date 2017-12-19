package com.cavaliersoftware.sample.util;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 2:03 PM
 * <p>
 *  A utility class for dealing with GeoLocation tasks.  This is just a very rough approximation where
 *  bounding boxes are used to estimate continents.  This would need to be updated to use a full featured geo library
 */
public class GeoUtility {

    public static int WORLD = 0;
    public static int ANTARCTICA = 1;
    public static int AFRICA = 2;
    public static int ASIA = 3;
    public static int AUSTRALIA = 4;
    public static int EUROPE = 5;
    public static int NORTH_AMERICA = 6;
    public static int SOUTH_AMERICA = 7;

    public GeoUtility() {
        super();
    }

    /**
     * Determines if the point is in Antarctica
     * @param point the coordinate to evaluate
     * @return true if the point is in Antarctica, false otherwise
     */
    public boolean isInAntarctica( double[] point ) {
        // Antartica is rougly below -65 lat
        return point[ 0 ] < -65.0;
    }

    /**
     * Determines if the point is in North America
     * @param point the coordinate to evaluate
     * @return true if the point is in North America, false otherwise
     */
    public boolean isInNorthAmerica( double[] point ) {
        // NorthAmerica is roughly a bounding box between latitudes of 10 and 90 and lon -20 and -180
        return ( 10.0 < point[0]  && point[0] < 90.0 ) && ( -20.0 > point[1] && point[1] > -180.0 ) ;
    }

    /**
     * Determines if the point is in South America
     * @param point the coordinate to evaluate
     * @return true if the point is in South America, false otherwise
     */
    public boolean isInSouthAmerica( double[] point ) {
        // SouthAmerica is roughly a bounding box between latitudes of 10 and -60 and lon -10 and -80
        return ( -60.0 < point[0]  && point[0] < 10.0 ) && ( -0.0 > point[1] && point[1] > -80.0 ) ;

    }

    /**
     * Determines if the point is in Australia
     * @param point the coordinate to evaluate
     * @return true if the point is in Australia, false otherwise
     */
    public boolean isInAustralia( double[] point ) {
        // Australia is roughly a bounding box between latitudes of -10 and -50 and lon 100 and 160
        return ( -10.0 > point[0]  && point[0] > -50.0 ) && ( 100.0 < point[1] && point[1] < 160.0 ) ;
    }

    /**
     * Determines if the point is in Africa
     * @param point the coordinate to evaluate
     * @return true if the point is in Africa, false otherwise
     */
    public boolean isInAfrica( double[] point ) {
        // Africa is roughly a bounding box between latitudes of 40 and -40 and lon -20 and 60
        return ( 40.0 > point[0]  && point[0] > -40.0 ) && ( -20.0 < point[1] && point[1] < 60.0 ) ;
    }

    /**
     * Determines if the point is in Asia
     * @param point the coordinate to evaluate
     * @return true if the point is in Asia, false otherwise
     */
    public boolean isInAsia( double[] point ) {
        // Asia is roughly a bounding box between latitudes of 90 and -10 and lon 60 and 180
        return ( 90.0 > point[0]  && point[0] > -10.0 ) && ( 60.0 < point[1] && point[1] < 180.0 ) ;

    }

    /**
     * Determines if the point is in Europe
     * @param point the coordinate to evaluate
     * @return true if the point is in Europe, false otherwise
     */
    public boolean isInEurope( double[] point ) {
        // Europe is roughly a bounding box between latitudes of 90 and 35 and lon 20 and 60
        return ( 90.0 > point[0]  && point[0] > 35.0 ) && ( 20.0 < point[1] && point[1] < 60.0 ) ;
    }

    /**
     * Returns the bit number corresponding to continent for the point in question. It will return the value for the
     * whole world if the point does not lie in any of the 7 continents.
     * @param point the coordinate to evaluate
     * @return the int representing which continent the point is in
     */
    public int getBitForPoint( double[] point ) {
        int returnVal = WORLD; // since this is a flag for the world, it is fine to return 0 if nothing else is identified
        if ( isInAntarctica( point ) ) {
            returnVal = ANTARCTICA;
        } else if ( isInAfrica( point ) ) {
            returnVal = AFRICA;
        } else if ( isInAsia( point ) ) {
            returnVal = ASIA;
        } else if ( isInAustralia( point ) ) {
            returnVal = AUSTRALIA;
        } else if ( isInEurope( point ) ) {
            returnVal = EUROPE;
        } else if ( isInNorthAmerica( point ) ) {
            returnVal = NORTH_AMERICA;
        } else if ( isInSouthAmerica( point ) ) {
            returnVal = SOUTH_AMERICA;
        }
        return returnVal;
    }
}
