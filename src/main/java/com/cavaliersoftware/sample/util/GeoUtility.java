package com.cavaliersoftware.sample.util;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 2:03 PM
 * <p>
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

    public boolean isInAntarctica( double[] point ) {
        // Antartica is rougly below -65 lat
        return point[ 0 ] < -65.0;
    }

    public boolean isInNorthAmerica( double[] point ) {
        // NorthAmerica is roughly a bounding box between latitudes of 10 and 90 and lon -20 and -180
        return ( 10.0 < point[0]  && point[0] < 90.0 ) && ( -20.0 > point[1] && point[1] > -180.0 ) ;
    }

    public boolean isInSouthAmerica( double[] point ) {
        // SouthAmerica is roughly a bounding box between latitudes of 10 and -60 and lon -10 and -80
        return ( -60.0 < point[0]  && point[0] < 10.0 ) && ( -0.0 > point[1] && point[1] > -80.0 ) ;

    }

    public boolean isInAustralia( double[] point ) {
        // Australia is roughly a bounding box between latitudes of -10 and -50 and lon 100 and 160
        return ( -10.0 > point[0]  && point[0] > -50.0 ) && ( 100.0 < point[1] && point[1] < 160.0 ) ;
    }

    public boolean isInAfrica( double[] point ) {
        // Africa is roughly a bounding box between latitudes of 40 and -40 and lon -20 and 60
        return ( 40.0 > point[0]  && point[0] > -40.0 ) && ( -20.0 < point[1] && point[1] < 60.0 ) ;
    }

    public boolean isInAsia( double[] point ) {
        // Asia is roughly a bounding box between latitudes of 90 and -10 and lon 60 and 180
        return ( 90.0 > point[0]  && point[0] > -10.0 ) && ( 60.0 < point[1] && point[1] < 180.0 ) ;

    }

    public boolean isInEurope( double[] point ) {
        // Europe is roughly a bounding box between latitudes of 90 and 35 and lon 20 and 60
        return ( 90.0 > point[0]  && point[0] > 35.0 ) && ( 20.0 < point[1] && point[1] < 60.0 ) ;
    }

    public int getFlagForPoint( double[] point ) {
        int returnVal = -1;
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
