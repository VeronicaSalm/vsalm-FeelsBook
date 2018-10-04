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

public class FileIO {

    private static final String FILENAME = "file.sav";

    public ArrayList<Emotion>  loadFromFile(Context ctx) {

        ArrayList<Emotion> emotionList;

        try {
            FileInputStream fis = ctx.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotionList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            emotionList = new ArrayList<Emotion>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return emotionList;
//        try {
//            FileInputStream fis = ctx.openFileInput(FILENAME);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
////            StringBuilder sb = new StringBuilder();
////            String line;
////            while ((line = in.readLine()) != null) {
////                sb.append(line);
////            }
//            Gson gson = new Gson();
////            String json = sb.toString();
//
//            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
//            // 2017-01-24 18:19
//            Type listType = new TypeToken<ArrayList<Emotion>>(){}.getType();
//            emotionList = gson.fromJson(in, listType);
//
//            fis.close();
//            in.close();
//
//        } catch (FileNotFoundException e) {
//            emotionList = new ArrayList<Emotion>();
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//
//        return emotionList;
    }

    public void saveInFile(ArrayList<Emotion> emotionList, Context ctx) {
        try {
            FileOutputStream fos = ctx.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(emotionList, out);
            out.flush();

            out.close();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception properly later
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}

