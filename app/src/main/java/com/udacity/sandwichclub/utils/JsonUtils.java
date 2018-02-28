package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    // Initiate JSON variables
    private static final String JSON_NAME = "name";
    private static final String JSON_MAINNAME = "mainName";
    private static final String JSON_KNOWNAS = "alsoKnownAs";
    private static final String JSON_IMG = "image";
    private static final String JSON_INFO = "description";
    private static final String JSON_ORIGIN = "placeOfOrigin";
    private static final String JSON_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject jsonObject = new JSONObject(json);
        // Use optString instead of getString due to JSONException when empty string
        String jsonName = jsonObject.optString(JSON_NAME);
        JSONObject jsonObjectName = new JSONObject(jsonName);

        String jsonMainName = jsonObjectName.optString(JSON_MAINNAME);
        String jsonImgURL = jsonObject.optString(JSON_IMG);
        String jsonOrigin = jsonObject.optString(JSON_ORIGIN);
        String jsonInfo = jsonObject.optString(JSON_INFO);

        JSONArray jsonArrayKnownAs = jsonObjectName.getJSONArray(JSON_KNOWNAS);
        JSONArray jsonArrayIngredients = jsonObject.getJSONArray(JSON_INGREDIENTS);

        // Parse JSON objects to the constructor
        Sandwich sandwich = new Sandwich();
        sandwich.setMainName(jsonMainName);
        sandwich.setImage(jsonImgURL);
        sandwich.setPlaceOfOrigin(jsonOrigin);
        sandwich.setDescription(jsonInfo);
        sandwich.setAlsoKnownAs(JSONArrayToList(jsonArrayKnownAs));
        sandwich.setIngredients(JSONArrayToList(jsonArrayIngredients));

        return sandwich;
    }

    private static ArrayList<String> JSONArrayToList(JSONArray jsonArray) throws JSONException {

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayList.add(jsonArray.getString(i));
        }
        return arrayList;
    }
}
