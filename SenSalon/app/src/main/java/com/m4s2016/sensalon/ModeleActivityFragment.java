package com.m4s2016.sensalon;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ModeleActivityFragment extends Fragment {
    private View rootView;
    private List<String> newModeleList;
    private ListView listView;
    private final String TAG_ALLMODELE="allNomModele";
    private String[] tabNomImage;

    int[] tabImage= new int[]{R.drawable.femme1,R.drawable.femme2,R.drawable.femme3,R.drawable.femme4,R.drawable.femme5,
            R.drawable.femme6,R.drawable.femme7,R.drawable.femme8,R.drawable.femme9,R.drawable.femme10,
            R.drawable.homme1,R.drawable.homme2,R.drawable.homme3,R.drawable.homme4,R.drawable.homme5,
            R.drawable.homme6,R.drawable.homme7,R.drawable.homme8,R.drawable.homme9,R.drawable.homme10,
    };

    public ModeleActivityFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modele, container, false);

        Intent intent = getActivity().getIntent();
        Bundle extras =intent.getExtras();
        newModeleList = new ArrayList<>();
        if (extras != null) {
            newModeleList = extras.getStringArrayList("TAG_ALLMODELE");
        }

        tabNomImage = newModeleList.toArray(new String[newModeleList.size()]);
        for (int i = 0; i < tabNomImage.length; i++) {
            System.out.println("New MODELE " + i + " " + tabNomImage[i]);
        }

        CustomList adapter = new CustomList(getActivity(),tabNomImage,tabImage);
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        return rootView;
    }
}
