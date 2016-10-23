package com.minhaj.earthquakereport;

import android.content.Context;
import android.content.Loader;
import android.util.Log;

import java.util.List;

/**
 * Created by Minhaj on 12-Oct-16.
 */

public class EarthquakeLoader extends Loader {

    String mUrl;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST : onStartLoading() called...");
        forceLoad();
    }

    public List<Earthquake> LoadInBackground() {

        Log.i(LOG_TAG, "TEST : LoadInBackground called... ");
        if (mUrl == null) {
            return null;
        }
        List<Earthquake> earthquakes = QueryUtlis.fetchEarthquakeData(murl);
        return earthquakes;
    }
}
