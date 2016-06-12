package com.mo.sunshine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ForecastFragment extends android.support.v4.app.Fragment {

    public ForecastFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast_fragment, container, false);
            setHasOptionsMenu(true);


        ArrayList<String> Fakedata = new ArrayList<String>();
        Fakedata.add("Today - hot");
        Fakedata.add("yestarday - hot");
        Fakedata.add("Tomorrow - very hot");
        Fakedata.add("Day after tomorrow - cool");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity()   // get the context of the layout
                , R.layout.list_item_forecast    // declear the the layout for each element
                , R.id.listItemForecast_textView // declear how the data is displayed
                , Fakedata);                     // the data
        ListView forecastList = (ListView) rootView.findViewById(R.id.MainFragment_ListView);
        forecastList.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecast_menue,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
                String id = (String)item.getTitle().toString();
                    if(id.equals("refresh") == true){
                        try {
                            View parentView = getActivity().findViewById(R.id.container);
                            NetworkConnection networkConnection = new NetworkConnection();
                            networkConnection.execute("94043");
                            return true ;
                        }   catch (Exception e){
                            Log.e("Errors","Something Wrong with the network connection again -_- ");
                        }
                    }
        return true ;
    }
}
