package com.example.veronica.vsalm_feelsbook;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* Class: FileIO

Used for data persistence to allow saving and restoring
of the emotion list.

This code is largely adapted from shidahe's LonelyTwitter
demo for CSC 301 from
https://github.com/shidahe/lonelyTwitter/tree/2947a4af8a461687bf62439b9a589905998c82f8

 */
public class FileIO {

    private static final String FILENAME = "file.sav";

    public ArrayList<Emotion>  loadFromFile(Context ctx) {

        ArrayList<Emotion> emotionList;

        try {
            // attempt to read in the emotion list
            FileInputStream fis = ctx.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            // create a new gson
            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            // returns the expected type (ArrayList of Emotion objects) to tell the Gson
            // what we plan to load from the file
            Type listType = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotionList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            emotionList = new ArrayList<Emotion>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return emotionList;
    }

    public void saveInFile(ArrayList<Emotion> emotionList, Context ctx) {
        try {
            // attempt to create the file stream and writer
            FileOutputStream fos = ctx.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            // serialize and write the list to the file
            Gson gson = new Gson();
            gson.toJson(emotionList, out);
            out.flush();

            // ensure that both filestream and writer are closed
            out.close();
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}

