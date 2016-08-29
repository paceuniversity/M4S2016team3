package com.m4s2016.sensalon;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ModeleActivityFragment extends Fragment {

    ListView list;
    String[] modeles = {
            "Méches curly",
            "Dégradé",
            "Tresses stylisées",
            "A la Riri",
            "Old School Dégradé",
            "Hollywood Now",
            "Beckham Style",
            "Sur un côté...",
            "CasaDiMansa",
            "Black",
    } ;
    Integer[] imageId = {
            R.drawable.femme1,
            R.drawable.homme1,
            R.drawable.femme2,
            R.drawable.femme3,
            R.drawable.homme2,
            R.drawable.femme4,
            R.drawable.homme3,
            R.drawable.femme7,
            R.drawable.femme6,
            R.drawable.homme4,
    };

    public ModeleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_modele, container, false);

        CustomListSalon adapter = new
                CustomListSalon(getActivity(), modeles, imageId);

        list = (ListView) rootView.findViewById(R.id.listemodele);
        list.setAdapter(adapter);
        return rootView;
    }
}
