package com.m4s2016.sensalon;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class SalonActivityFragment extends Fragment {

    ListView list;
    String[] salons = {
            "Latifah Coiffure",
            "Sope Khadim Coiffure",
            "Beauty Coiffure",
            "Michael Coiffure",
            "Siby Coiffure",
            "Hair Studio Coiffure",
            "Coupe De Tête Coiffure",
            "Pullman Téranga Coiffure",
            "Bruch color Coiffure",
    } ;
    Integer[] imageId = {
            R.drawable.salon01,
            R.drawable.salon02,
            R.drawable.salon03,
            R.drawable.salon04,
            R.drawable.salon05,
            R.drawable.salon06,
            R.drawable.salon07,
            R.drawable.salon08,
            R.drawable.salon09,

    };

    public SalonActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_salon, container, false);

        CustomListSalon adapter = new
                CustomListSalon(getActivity(), salons, imageId);

        list = (ListView) rootView.findViewById(R.id.listesalon);
        list.setAdapter(adapter);
        return rootView;
    }
}
