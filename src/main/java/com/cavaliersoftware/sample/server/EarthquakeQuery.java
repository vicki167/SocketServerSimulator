package com.cavaliersoftware.sample.server;

import groovy.json.JsonSlurper;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 1:27 PM
 * <p>
 */
public class EarthquakeQuery {

    private JsonSlurper slurper;

    public EarthquakeQuery() {
        super();
        // this is a groovy class for easily working with JSON documents...  basically everything is a List or Map
        slurper = new JsonSlurper();
    }

    /**
     * Queries the USGS earthquake service to obtain the instances of earthquakes over magnitude 5 over the last 30
     * days.  The query has a limit of 300 instances to avoid possible issues tying up resources with  a particularly
     * large data set.  The coordinates are extracted and converted to a double[] of lat (pos 0) and lon (pos 1).
     * @return  an array with the latitude at index position 0 and the longitude at index position 1
     * @throws MalformedURLException
     */
    public List<double[]> getEarthquakePoints() throws MalformedURLException {
        List<double[]> points = new ArrayList<double[]>(  ); // return val
        // retrieve the geo json features for each earthquake
        List features = getFeatures();
        if ( features != null ) {
            // every feature should is a Map, so no need to check
            for ( Object featureObj : features ) {
                    Map feature = ( Map ) featureObj;
                    if ( feature.containsKey( "geometry" ) ) {
                        Map geometry = ( Map ) feature.get( "geometry" ); // geometry is also a map, no need to check
                        // make sure we are dealing with a point
                        if ( geometry.containsKey( "type" ) && "Point".equals( geometry.get( "type" ) )
                                && geometry.containsKey( "coordinates" ) ) {
                            List coordinates = ( List ) geometry.get( "coordinates" );
                            if ( coordinates.size() >= 2 ) {
                                // this comes through as a BigDecimal, so convert
                                points.add( new double[] { (( BigDecimal ) coordinates.get( 0 )).doubleValue(),
                                        (( BigDecimal ) coordinates.get( 1 )).doubleValue() } );
                            }
                        }

                    }
            }
        }
        return points;
    }

    private List getFeatures() throws MalformedURLException {
        // read in json from the USGS earthquake feed to bring back the earthquakes over mag 5 in the last 30 days
        Object obj = slurper.parse(
                new URL( "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&limit=300&minmagnitude=5" ) ); // limited to 300 just incase

        // search for the features and extract
        List features = null;
        if ( obj instanceof Map ) {
            Map jsonMap = ( Map ) obj;
            // get the features
            if ( jsonMap.containsKey( "features" ) ) {
                features = ( List ) jsonMap.get( "features" );
            }
        }
        return features;
    }
}
