package com.m4s2016.sensalon;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsSalonActivityFragment extends Fragment {

    private String nomSalon;
    private String adresse;
    private String telephone;
    private String typeSalon;
    private String prenom_nom;
    private String streetName;


    public DetailsSalonActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_details_salon, container, false);

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            nomSalon = extras.getString("TAG_NOMSALON");
            typeSalon = extras.getString("TAG_TYPESALON");
            adresse = extras.getString("TAG_ADRESSE");
            telephone = extras.getString("TAG_TELEPHONE");
            prenom_nom = extras.getString("TAG_NOMPROPIETAIRE");
            streetName = extras.getString("TAG_NOMSTREET");


            System.out.println("xxxx-Detailssalons " + nomSalon + "," + typeSalon + "," + adresse + "," + telephone );
        }

        TextView textView1 = (TextView) rootView.findViewById(R.id.txtnomsalon);
        TextView textView2 = (TextView) rootView.findViewById(R.id.txttypesalon);
        TextView textView3 = (TextView) rootView.findViewById(R.id.txtadresse);
        TextView textView4 = (TextView) rootView.findViewById(R.id.txttelephone);
        TextView textView5 = (TextView) rootView.findViewById(R.id.txtproprietaire);
        TextView textView6 = (TextView) rootView.findViewById(R.id.txtstreet);

        ImageView imageView= (ImageView) rootView.findViewById(R.id.salon_image);

        textView1.setText(nomSalon);
        textView2.setText(typeSalon);
        textView3.setText(adresse);
        textView4.setText(telephone);
        textView5.setText(prenom_nom);
        textView6.setText(streetName);
        imageView.setImageResource(R.drawable.salon);

        return rootView;
    }
}
