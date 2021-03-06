package com.minhaj.earthquakereport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Minhaj on 30-Sep-16.
 */

public final class QueryUtlis {

    /**
     * Sample JSON response for a USGS query
     */
    private static final String LOG_TAG = QueryUtlis.class.getSimpleName();

    public static final String SAMPLE_JSON_RESPONSE = "http://earthquake.usgs.gov/fdsnws/event/1/query";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtlis() {
    }

    /**
     * Return a list of {@link customclass} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<Earthquake> featureEarthquakeData(String requestUrl) {

        Log.i(LOG_TAG, "TEST: featureEarthquakeData() called...");

        if (TextUtils.isEmpty(earthQuakJson)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to

        List<Earthquake> earthquakes = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD,yyyy h:mm a", Locale.ENGLISH);


        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and


            JSONObject basejsonrespo = new JSONObject(earthQuakJson);
            JSONArray earthquackearray = basejsonrespo.getJSONArray("features");

            for (int i = 0; i < earthquackearray.length(); i++) {

// Get a single earthquake at position i within the list of earthQuakes
                JSONObject currentearthquacke = earthquackearray.getJSONObject(i);
                JSONObject properties = currentearthquacke.getJSONObject("properties");

                double mag = properties.getDouble("mag");

                String place = properties.getString("place");

                long timemilsecond = properties.getLong("time");

                Date dateobject = new Date(timemilsecond);

                String date_formate = dateFormat.format(dateobject);

                String url = properties.getString("url");
                Earthquake object = new Earthquake(mag, place, timemilsecond, url);

                earthquakes.add(object);
            }

            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            //InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Query the USGS dataset and return a list of {@link customclass} objects.
     */
    public static List<Earthquake> fetchEarthquakeData(String requestUrl) {
        Log.e(LOG_TAG, "FetchData");

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {

            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Earthquake> earthquakes = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }
}
