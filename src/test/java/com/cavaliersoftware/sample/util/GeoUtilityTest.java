package com.cavaliersoftware.sample.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 2:08 PM
 * <p>
 */
@RunWith( JUnit4.class )
public class GeoUtilityTest {

    @Test
    public void testIsInAntarctica() throws Exception {
        // antartica is less than about -65 lat
        GeoUtility util = new GeoUtility();
        assertFalse( util.isInAntarctica( new double[] { 0.0, 45.0 } ) );
        assertFalse( util.isInAntarctica( new double[] { 75.0, 45.0 } ) );
        assertTrue( util.isInAntarctica( new double[] { -70.0, 125.0 } ) );
        assertTrue( util.isInAntarctica( new double[] { -89.0, -125.0 } ) );
    }

    @Test
    public void testIsInNorthAmerica() throws Exception {
        // NorthAmerica is roughly a bounding box between latitudes of 10 and 90 and lon -20 and -180
        GeoUtility util = new GeoUtility();
        assertFalse( util.isInNorthAmerica( new double[] { 5.0, -80.0 } ) );
        assertFalse( util.isInNorthAmerica( new double[] { 15.0, 80.0 } ) );
        assertFalse( util.isInNorthAmerica( new double[] { -5.0, -80.0 } ) );
        assertFalse( util.isInNorthAmerica( new double[] { -5.0, -80.0 } ) );
        assertTrue( util.isInNorthAmerica( new double[] { 15.0, -80.0 } ) );
    }

    @Test
    public void testIsInSouthAmerica() throws Exception {
        // SouthAmerica is roughly a bounding box between latitudes of 10 and -60 and lon -10 and -80
        GeoUtility util = new GeoUtility();
        assertFalse( util.isInSouthAmerica( new double[] { 15.0, 80.0 } ) );
        assertFalse( util.isInSouthAmerica( new double[] { -20, 40.0 } ) );
        assertFalse( util.isInSouthAmerica( new double[] { 40.0, -170.0 } ) );
        assertFalse( util.isInSouthAmerica( new double[] { 50.0, 140.0 } ) );
        assertTrue( util.isInSouthAmerica( new double[] { -10.0, -60.0 } ) );
    }

    @Test
    public void testIsInAustralia() throws Exception {
        // Australia is roughly a bounding box between latitudes of -10 and -50 and lon 100 and 160
        GeoUtility util = new GeoUtility();
        assertFalse( util.isInAustralia( new double[] { -30.0, 60.0 } ) );
        assertFalse( util.isInAustralia( new double[] { 50.0, 120.0 } ) );
        assertFalse( util.isInAustralia( new double[] { 40.0, -140.0 } ) );
        assertFalse( util.isInAustralia( new double[] { -50.0, -140.0 } ) );
        assertTrue( util.isInAustralia( new double[] { -30.0, 140.0 } ) );
    }

    @Test
    public void testIsInAfrica() throws Exception {
        // Africa is roughly a bounding box between latitudes of 40 and -40 and lon -20 and 60
        GeoUtility util = new GeoUtility();
        assertFalse( util.isInAfrica( new double[] { 50.0, 20.0 } ) );
        assertFalse( util.isInAfrica( new double[] { -50.0, 30.0 } ) );
        assertFalse( util.isInAfrica( new double[] { -20.0, -140.0 } ) );
        assertFalse( util.isInAfrica( new double[] { -10.0, -100.0 } ) );
        assertTrue( util.isInAfrica( new double[] { 10.0, 20.0 } ) );
    }

    @Test
    public void testIsInAsia() throws Exception {
        // Asia is roughly a bounding box between latitudes of 90 and -10 and lon 60 and 180
        GeoUtility util = new GeoUtility();
        assertFalse( util.isInAsia( new double[] { 50.0, -150.0 } ) );
        assertFalse( util.isInAsia( new double[] { -50.0, 140.0 } ) );
        assertFalse( util.isInAsia( new double[] { -20.0, -140.0 } ) );
        assertFalse( util.isInAsia( new double[] { -10.0, -100.0 } ) );
        assertTrue( util.isInAsia( new double[] { 50.0, 99.0 } ) );
    }

    @Test
    public void testIsInEurope() throws Exception {
        // Europe is roughly a bounding box between latitudes of 90 and 35 and lon 20 and 60
        GeoUtility util = new GeoUtility();
        assertFalse( util.isInEurope( new double[] { 50.0, 100.0 } ) );
        assertFalse( util.isInEurope( new double[] { 50.0, -100.0 } ) );
        assertFalse( util.isInEurope( new double[] { -50.0, -140.0 } ) );
        assertFalse( util.isInEurope( new double[] { 20.0, 20.0 } ) );
        assertFalse( util.isInEurope( new double[] { 20.0, -20.0 } ) );
        assertTrue( util.isInEurope( new double[] { 50.0, 50.0 } ) );
    }

    @Test
    public void getFlagForPoint( ) {
        GeoUtility util = new GeoUtility();
        assertEquals( GeoUtility.EUROPE, util.getFlagForPoint( new double[] { 50.0, 50.0 } ) );
        assertEquals( GeoUtility.ASIA, util.getFlagForPoint( new double[] { 50.0, 99.0 } ) );
        assertEquals( GeoUtility.AFRICA, util.getFlagForPoint( new double[] { 10.0, 20.0 } ) );
        assertEquals( GeoUtility.AUSTRALIA, util.getFlagForPoint( new double[] { -30.0, 140.0  } ) );
        assertEquals( GeoUtility.SOUTH_AMERICA, util.getFlagForPoint( new double[] {  -10.0, -60.0  } ) );
        assertEquals( GeoUtility.NORTH_AMERICA, util.getFlagForPoint( new double[] {  15.0, -80.0  } ) );
        assertEquals( GeoUtility.ANTARCTICA, util.getFlagForPoint( new double[] {  -85.0, -100.0  } ) );

    }

}