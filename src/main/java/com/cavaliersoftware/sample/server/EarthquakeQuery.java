package com.cavaliersoftware.sample.server;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 4:42 PM
 * <p>
 */
public interface EarthquakeQuery {
    List<double[]> getEarthquakePoints() throws MalformedURLException;
}
