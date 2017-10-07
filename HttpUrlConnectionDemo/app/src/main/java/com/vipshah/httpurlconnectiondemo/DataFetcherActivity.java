package com.vipshah.httpurlconnectiondemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataFetcherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = "http://www.json-generator.com/api/json/get/bVXagEndDS?indent=2";

        downloadJson(url);
    }

    private void downloadJson(String url) {
        new DownloadTask().execute(url);
    }

    class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(DataFetcherActivity.this, "Loading Data!", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String[] urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder builder = new StringBuilder();
                    String tempString;

                    while ((tempString = bufferedReader.readLine()) != null) {
                        builder.append(tempString);
                        builder.append(System.getProperty("line.separator"));
                    }

                    return builder.toString();
                } else {
                    return null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (TextUtils.isEmpty(response)) {
                Toast.makeText(DataFetcherActivity.this, "Received error response!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DataFetcherActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
