package com.training.islamyapp.repositories;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.training.islamyapp.pojo.azkar.AzkarItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AzkarRepository {
    // call source of data
    // read data from json file

    private String TAG = "AzkarRepository";
    public List<AzkarItem> getAllAzkar(Context context){
        StringBuilder jsonStringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("azkar.json")))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                jsonStringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        String jsonString = jsonStringBuilder.toString();

        // convert string file in the form of json to list of custom object

        Gson gson = new Gson();
        ArrayList<AzkarItem> azkarItems = gson.fromJson(jsonString, new TypeToken<List<AzkarItem>>(){}.getType());

        return azkarItems;
    }

}
