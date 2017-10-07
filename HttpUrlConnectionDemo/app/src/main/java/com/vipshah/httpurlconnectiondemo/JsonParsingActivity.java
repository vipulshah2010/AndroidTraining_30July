package com.vipshah.httpurlconnectiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonParsingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_downloading);

        try {
            InputStream stream = getAssets().open("authors.json");
            InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder builder = new StringBuilder();
            String tempString;

            while ((tempString = bufferedReader.readLine()) != null) {
                builder.append(tempString);
                builder.append(System.getProperty("line.separator"));
            }

            String json = builder.toString();
            Log.i("vipul", json);

            try {
                printPrice(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get C programing book price in london written by vipul
    private void printPrice(String json) throws JSONException {
        JSONObject object = new JSONObject(json);
        JSONArray authors = object.getJSONArray("authors");

        for (int i = 0; i < authors.length(); i++) {
            JSONObject authorObject = authors.getJSONObject(i);
            String name = authorObject.getString("name");

            if (name.equalsIgnoreCase("vipul")) {
                JSONArray books = authorObject.getJSONArray("books");
                for (int j = 0; j < books.length(); j++) {
                    JSONObject bookObject = books.getJSONObject(j);

                    if (bookObject.getString("title").equalsIgnoreCase("C")) {
                        JSONArray prices = bookObject.getJSONArray("prices");

                        for (int k = 0; k < prices.length(); k++) {
                            JSONObject priceObject = prices.getJSONObject(k);
                            if (priceObject.getString("country").equalsIgnoreCase("london")) {
                                int price = priceObject.getInt("price");
                                Toast.makeText(JsonParsingActivity.this,
                                        "Book price in london is " + price, Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
