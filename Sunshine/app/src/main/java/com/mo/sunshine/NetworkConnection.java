package com.mo.sunshine;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;



public class NetworkConnection extends AsyncTask<String,Void,String[]> {




    @Override
    protected String[] doInBackground(String... params) {

        String BASE ="http://api.openweathermap.org/data/2.5/forecast/daily";

        Log.e("Errors", "Hello ");
        URL url = null ;
        Uri.Builder Url = Uri.parse(BASE).buildUpon();
        String QPARAM = "q" , FPARAM = "mode",UPARAM = "units",DPARAM = "cnt",IPARAM = "appid";
        String F = "json",U = "metric" , D = "7" , I = "7bedd4dcbcc4753a5feeaf5169d9e849";
        Url.appendQueryParameter(QPARAM,params[0]);
        Url.appendQueryParameter(FPARAM,F);
        Url.appendQueryParameter(UPARAM,U);
        Url.appendQueryParameter(DPARAM,D);
        Url.appendQueryParameter(IPARAM,I);
        Url.build();
         Log.e("Errors","U Bult"   +    Url.toString());
        InputStream in =null;
        try {
            url =  new URL (Url.toString());
            Log.e("Errors","Hi1  " +url.toString());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null ;
        StringBuffer sb = new StringBuffer();
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Log.e("Errors","Hi2  " + connection.toString());
            in = (InputStream) connection.getInputStream() ;
            Log.e("Errors","Hi3  " + in.toString());

            int ch;
            while ((ch = in.read()) != -1) {
                sb.append((char) ch);
            }
            Log.e("Errors","h4  " + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonParser jsonParser = new JsonParser(sb.toString(), 7);

        try {
            return jsonParser.getWeatherData();
        } catch (JSONException e) {
            Log.e("Errors","Parser Error");
            return  null ;
        }
    }

    @Override
    protected void onPostExecute(String[] s) {
    }


}
