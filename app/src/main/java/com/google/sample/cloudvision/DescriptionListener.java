package com.google.sample.cloudvision;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;

public class DescriptionListener extends AsyncTask<String, String, String> {

    int startTime = 0; // seconds
    String description = "defaultDescription";
    URL url;
    HttpURLConnection connection;
    JSONObject jsonObject;

    @Override
    protected String doInBackground(String... params) {
        startTime = Calendar.getInstance().getTime().getSeconds();

        boolean areNewEntriesAvailable = false;

        InputStream in;

        try {
            url = new URL("https://enghack-firebase.firebaseio.com/.json");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            Thread.sleep(10000);
        } catch (Exception e) {
            Log.e("DB", "Connection Exception: " + e.getMessage());
        }

        try {
            in = new BufferedInputStream(connection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String content = "", line;
            while ((line = rd.readLine()) != null) {
                content += line + "\n";
            }
            jsonObject = new JSONObject(content);
            JSONObject messages = jsonObject.getJSONObject("messages");
            Iterator<?> keys = messages.keys();
            String lastKey = "";
            int tempNumEntries = 0;
            while(keys.hasNext())
            {
                tempNumEntries++;
                lastKey = (String)keys.next();
            }
            JSONObject lastEntry = messages.getJSONObject(lastKey);
            description = lastEntry.getString("Description");
            MainActivity.descriptionEntries = tempNumEntries;
        }
        catch(Exception e)
        {
            Log.e("DB", "Exception: " + e.getMessage());
        }

        publishProgress(description);

        return description;
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(String... text) {
        Log.d("Description: " , text[0]);
        //MainActivity.tts.speak(text[0]);
    }
}
