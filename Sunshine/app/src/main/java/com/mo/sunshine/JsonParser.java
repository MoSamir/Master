package com.mo.sunshine;

import android.text.format.Time;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;

/**
 * Created by MO on 6/11/2016.
 */
public class JsonParser {

    String JsonString ;
    int NumberOfDays ;
    public JsonParser(String JsonString, int NumberOfDays){
        this.JsonString = JsonString ;
        this.NumberOfDays = NumberOfDays ;
    }

    String getReadableDate(long seconds){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd");
        return simpleDateFormat.format(seconds);
    }

    String GetReadableHighLow(double high , double low){
        return Math.round(high) + "/" + Math.round(low);
    }


    String[] getWeatherData() throws JSONException {
        final String OWM_LIST = "list";
        final String OWM_WEATHER = "weather";
        final String OWM_TEMPERATURE = "temp";
        final String OWM_MAX = "max";
        final String OWM_MIN = "min";
        final String OWM_DESCRIPTION = "main";

        JSONObject ForecastJason = new JSONObject(JsonString);
        JSONArray ListArray = ForecastJason.getJSONArray(OWM_LIST);



        Time dayTime = new Time();
        dayTime.setToNow();
        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);
        dayTime = new Time();




        String [] ForecastArray = new String[NumberOfDays];
            for(int i = 0 ; i < NumberOfDays;i++){
                JSONObject Day = ListArray.getJSONObject(i);
                Log.e("Errors","Object Success 1  ");
                String DateTime = getReadableDate(dayTime.setJulianDay(julianStartDay + i));
                Log.e("Errors","Object Success 2  ");
                String Tempreture = GetReadableHighLow(Day.getJSONObject(OWM_TEMPERATURE).getDouble("max"),
                        Day.getJSONObject(OWM_TEMPERATURE).getDouble("min"));
                Log.e("Errors","Object Success 3  ");
                String Description = Day.getJSONArray(OWM_WEATHER).getJSONObject(0).getString(OWM_DESCRIPTION);

                Log.e("Errors","Object Success 4  ");
                ForecastArray[i] = DateTime + " - " + Tempreture + " - " + Description ;


            }



            for(String s : ForecastArray)
                Log.e("Errors" ,s);
     return ForecastArray ;
    }
}
