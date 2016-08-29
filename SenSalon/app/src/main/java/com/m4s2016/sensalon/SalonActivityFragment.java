package com.m4s2016.sensalon;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.getIntent;

/**
 * A placeholder fragment containing a simple view.
 */
public class SalonActivityFragment extends Fragment {
    private View rootView;
    private ListView listView;
    private String[] tabNomSalon;
    private List<String> newListNomSalon;
    private final String TAG_ALLNOMSALON="allNomSalon";

    int[] tabImage= new int[]{R.drawable.imagesalon};

    public SalonActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_salon, container, false);

        Intent intent = getActivity().getIntent();
        Bundle extras =intent.getExtras();
        newListNomSalon = new ArrayList<>();
        if (extras != null) {
            newListNomSalon = extras.getStringArrayList("TAG_ALLNOMSALON");
        }

        tabNomSalon = newListNomSalon.toArray(new String[newListNomSalon.size()]);
        for (int i = 0; i < tabNomSalon.length; i++) {
            System.out.println("New NOMSALON " + i + " " + tabNomSalon[i]);
        }

//        ArrayAdapter adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, tabNomSalon);
//        listView = (ListView) rootView.findViewById(R.id.listView);
//        listView.setAdapter(adapter);

        CustomListSalon adapter = new CustomListSalon(getActivity(),tabNomSalon,tabImage);
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return rootView;
    }

}
