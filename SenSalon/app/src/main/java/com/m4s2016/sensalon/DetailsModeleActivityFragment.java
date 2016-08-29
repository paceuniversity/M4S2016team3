package com.m4s2016.sensalon;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.StringTokenizer;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsModeleActivityFragment extends Fragment {

    private String modelName;
    private String modelDuration;
    private String modelPrice;
    private String position_image;

    private String nomSalon;

    private int[] tabImage = new int[]{
                R.drawable.femme1, R.drawable.femme2, R.drawable.femme3, R.drawable.femme4, R.drawable.femme5,
                R.drawable.femme6, R.drawable.femme7, R.drawable.femme8, R.drawable.femme9, R.drawable.femme10,
                R.drawable.homme1, R.drawable.homme2, R.drawable.homme3, R.drawable.homme4, R.drawable.homme5,
                R.drawable.homme6, R.drawable.homme7, R.drawable.homme8, R.drawable.homme9, R.drawable.homme10,
    };

    public DetailsModeleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_details_modele, container, false);

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            modelName = extras.getString("TAG_MODELENAME");
            modelDuration = extras.getString("TAG_MODELEDURATION");
            modelPrice = extras.getString("TAG_MODELEPRICE");
            position_image = extras.getString("TAG_POSITIONIMAGE");
            nomSalon = extras.getString("TAG_NOMSALON");

            System.out.println("xxxx-Detailsmodeles " + modelName + "," + modelDuration + "," + modelPrice + "," + position_image + "," + nomSalon);
        }

        TextView textView1 = (TextView) rootView.findViewById(R.id.txtmodelname);
        TextView textView2 = (TextView) rootView.findViewById(R.id.txtmodelprice);
        TextView textView3 = (TextView) rootView.findViewById(R.id.txtmodelduration);
        TextView textView4 = (TextView) rootView.findViewById(R.id.txtsalonname);
        ImageView imageView= (ImageView) rootView.findViewById(R.id.modele_image);

        int i =Integer.parseInt(position_image);
        imageView.setImageResource(tabImage[i]);
        textView1.setText(modelName);
        textView2.setText(modelPrice);
        textView3.setText(modelDuration);
        textView4.setText(nomSalon);

        return rootView;
    }

}
