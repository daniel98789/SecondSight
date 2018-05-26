package com.google.sample.cloudvision;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Calendar;

public class DescriptionListener extends AsyncTask<String, String, String> {

    int startTime = 0; // seconds
    String description = "defaultDescription";

    @Override
    protected String doInBackground(String... params) {
        startTime = Calendar.getInstance().getTime().getSeconds();

        while(description.equals("defaultDescription") && Calendar.getInstance().getTime().getSeconds() - startTime < 30)
        {
            if (Calendar.getInstance().getTime().getSeconds() - startTime > 1)
            {
                description = "something new";
            }
            // check for description
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
        Log.d("Loop", text[0]);
    }
}
