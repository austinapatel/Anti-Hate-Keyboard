package com.ibm;

import android.os.AsyncTask;
import android.util.Log;

import com.blackcj.customkeyboard.SoftKeyboard;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import org.json.*;

/**
 * Created by Austin Patel on 2/4/2017.
 */

public class ToneAnalyzerThread extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {

        ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
        service.setUsernameAndPassword("f71900a8-79c6-4295-8ee7-028abb760f60", "gGduZOlebFLC");

//        String text =
//                "I know the times are difficult! Our sales have been "
//                        + "disappointing for the past three quarters for our data analytics "
//                        + "product suite. We have a competitive data analytics product "
//                        + "suite in the industry. But we need to do our job selling it! "
//                        + "We need to acknowledge and fix our sales challenges. "
//                        + "We can’t blame the economy for our lack of execution! "
//                        + "We are missing critical sales opportunities. "
//                        + "Our product is in no way inferior to the competitor products. "
//                        + "Our clients are hungry for analytical tools to improve their "
//                        + "business outcomes. Economy has nothing to do with it.";

        ToneAnalysis tone = service.getTone(strings[0], null).execute();

        double anger = 0;

        try {
            JSONObject obj = new JSONObject(tone.toString());

            JSONObject obj1 = obj.getJSONObject("document_tone");
            JSONArray arr1 = obj1.getJSONArray("tone_categories");
            JSONArray arr2 = arr1.getJSONArray("tones");




        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("keyboard", tone.toString());

        SoftKeyboard.abc = tone.toString();

        return null;
    }

    protected void onPostExecute() {


    }
}
