package com.example.vipshah.filehandlingdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "sample.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            createFile();
            readFile();
            // deleteFile();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Writing Failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void createFile() throws IOException {
        FileOutputStream outputStream =
                openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        bufferedWriter.write("This is sample Content!!!");

        bufferedWriter.close();
        writer.close();
        outputStream.close();

        Toast.makeText(this, "Writing Success!",
                Toast.LENGTH_LONG).show();
    }

    private void readFile() throws IOException {
        FileInputStream inputStream = openFileInput(FILE_NAME);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String contents = bufferedReader.readLine();

        Toast.makeText(this, contents, Toast.LENGTH_LONG).show();
    }

    private void deleteFile() {
        if (deleteFile(FILE_NAME)) {
            Toast.makeText(this, "File deletion success!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "File deletion failed!", Toast.LENGTH_LONG).show();
        }
    }
}
