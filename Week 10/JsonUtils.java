package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public static List<Movie> loadMoviesFromJson(Context context)
            throws IOException, JSONException {

        List<Movie> movies = new ArrayList<>();

        // If file not found, call  IOException
        InputStream is = context.getAssets().open("movies.json");

        String jsonString = readStream(is);

        // If invalid JSON format, call JSONException
        JSONArray jsonArray = new JSONArray(jsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            try {
                Movie movie = parseMovie(obj);
                movies.add(movie);
            } catch (Exception e) {
                Log.e(TAG, "Invalid movie data at index " + i, e);
                movies.add(new Movie(null, null, null, null));
            }
        }
        return movies;
    }

    private static String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    private static Movie parseMovie(JSONObject json) {
        // Processing missing fields and null
        Object titleObj = json.opt("title");
        String title = (titleObj != null && titleObj != JSONObject.NULL) ? titleObj.toString() : null;

        Object yearObj = json.opt("year");
        Integer year = parseYear(yearObj);

        Object genreObj = json.opt("genre");
        String genre = (genreObj != null && genreObj != JSONObject.NULL) ? genreObj.toString() : null;

        Object posterObj = json.opt("poster");
        String poster = (posterObj != null && posterObj != JSONObject.NULL) ? posterObj.toString() : null;

        return new Movie(title, year, genre, poster);
    }

    private static Integer parseYear(Object obj) {
        if (obj == null || obj == JSONObject.NULL) return null;

        if (obj instanceof Integer) {
            int y = (Integer) obj;
            return y > 0 ? y : null;
        }
        if (obj instanceof Number) {
            int y = ((Number) obj).intValue();
            return y > 0 ? y : null;
        }
        if (obj instanceof String) {
            try {
                int y = Integer.parseInt((String) obj);
                return y > 0 ? y : null;
            } catch (NumberFormatException e) {
                return null; //
            }
        }
        return null;
    }

    public static void handleJsonException(Exception e) {
        Log.e(TAG, "JSON exception", e);
    }
}
