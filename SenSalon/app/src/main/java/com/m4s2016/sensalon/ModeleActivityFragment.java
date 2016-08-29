package com.m4s2016.sensalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.m4s2016.sensalon.dao.ModeleDao;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A placeholder fragment containing a simple view.
 */
public class ModeleActivityFragment extends Fragment {
    private List<String> getAllModeles;
    private String[] tabAllModeles;
    private int position;
    private String[] newTokens;
    private String[] newTokens2;

    private List<String> getAllSalons;
    private String[] tabAllSalons;

    private String idModele;
    private String modelName;
    private String modelDuration;
    private String modelPrice;
    private String positionimage;
    private String salonId;
    private int idsalon;
    private String nomSalon;

    public ModeleActivityFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_modele, container, false);

        int[] tabImage = new int[]{
                R.drawable.femme1, R.drawable.femme2, R.drawable.femme3, R.drawable.femme4, R.drawable.femme5,
                R.drawable.femme6, R.drawable.femme7, R.drawable.femme8, R.drawable.femme9, R.drawable.femme10,
                R.drawable.homme1, R.drawable.homme2, R.drawable.homme3, R.drawable.homme4, R.drawable.homme5,
                R.drawable.homme6, R.drawable.homme7, R.drawable.homme8, R.drawable.homme9, R.drawable.homme10,
        };

        ModeleDao modeleDao = new ModeleDao(getActivity());
        modeleDao.open();

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        List<String> newModeleList = new ArrayList<>();
        if (extras != null) {
            newModeleList = extras.getStringArrayList("TAG_ALLNOMMODELE");
            getAllModeles = extras.getStringArrayList("TAG_ALLMODELE");
            getAllSalons = extras.getStringArrayList("TAG_ALLSALON");
        }

        String[] tabNomImage = newModeleList.toArray(new String[newModeleList.size()]);
        tabAllSalons = getAllSalons.toArray(new String[getAllSalons.size()]);

        CustomListModele adapter = new CustomListModele(getActivity(), tabNomImage, tabImage);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getActivity(), "You clicked on " + i, Toast.LENGTH_SHORT).show();
                position = i;
                positionimage= String.valueOf(position);

                tabAllModeles = getAllModeles.toArray(new String[getAllModeles.size()]);
                System.out.println("TabAllModeles " + i  + " " + tabAllModeles[i]);

                allTokensModele();
                newTokens();
                allTokensSalon();
                newTokens2();

                System.out.println("-*+-*+-*+-*+ " + tabAllSalons[idsalon]);

                Intent intent = new Intent(getActivity(),DetailsModeleActivity.class);

                intent.putExtra("TAG_IDMODELE",idModele);
                intent.putExtra("TAG_MODELENAME",modelName);
                intent.putExtra("TAG_MODELEPRICE",modelPrice);
                intent.putExtra("TAG_MODELEDURATION",modelDuration);
                intent.putExtra("TAG_POSITIONIMAGE",positionimage);
                intent.putExtra("TAG_SALONID",salonId);
                intent.putExtra("TAG_NOMSALON", nomSalon);

                startActivity(intent);
            }
        });
        return rootView;
    }

    public String[] allTokensModele() {
        StringTokenizer stringTokenizer = new StringTokenizer(tabAllModeles[position], ",");
        newTokens = new String[stringTokenizer.countTokens()];
        System.out.println("*** " + stringTokenizer.countTokens());
        while (stringTokenizer.hasMoreElements()) {
            for (int i = 0; i < newTokens.length; i++) {
                newTokens[i] = (String) stringTokenizer.nextElement();
                System.out.println("===== " + newTokens[i]);
            }
        }
        return newTokens;
    }

    public String[] allTokensSalon() {
        int a = Integer.parseInt(salonId);
        int idsalon=a-1;
        StringTokenizer stringTokenizer2 = new StringTokenizer(tabAllSalons[idsalon], ",");
        newTokens2 = new String[stringTokenizer2.countTokens()];
        System.out.println("*** " + stringTokenizer2.countTokens());
        while (stringTokenizer2.hasMoreElements()) {
            for (int i = 0; i < newTokens2.length; i++) {
                newTokens2[i] = (String) stringTokenizer2.nextElement();
                System.out.println("=+=+=+=+ " + newTokens2[i]);
            }
        }
        return newTokens2;
    }

    public void newTokens() {
        int j = 0;
        idModele = newTokens[j].trim();
        j++;
        modelName = newTokens[j].trim();
        j++;
        modelDuration = newTokens[j].trim();
        j++;
        modelPrice = newTokens[j].trim();
        j++;
        String image = newTokens[j].trim();
        j++;
        salonId = newTokens[j].trim();

        System.out.println("xxxx-modeles " + idModele + "," + modelName + "," + modelDuration + "," + modelPrice + "," + salonId + "," + image);
    }

    public void newTokens2(){
        int j=0;
        String idSalon = newTokens2[j].trim();
        j++;
        nomSalon = newTokens2[j].trim();
        j++;
        String typeSalon = newTokens2[j].trim();
        j++;
        String longitude = newTokens2[j].trim();
        j++;
        String latitude = newTokens2[j].trim();
        j++;
        String adresse = newTokens2[j].trim();
        j++;
        String telephone = newTokens2[j].trim();
        j++;
        String streetId = newTokens2[j].trim();
        j++;
        String proprietaireId = newTokens2[j].trim();

        System.out.println("xxxx-salons " + idsalon + "," + nomSalon + "," + longitude + "," + latitude + "," + adresse + "," + telephone
                + "," + typeSalon + "," + streetId + "," + proprietaireId);

    }

}
