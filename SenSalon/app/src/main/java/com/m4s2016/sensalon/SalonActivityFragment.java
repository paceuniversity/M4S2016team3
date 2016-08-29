package com.m4s2016.sensalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A placeholder fragment containing a simple view.
 */
public class SalonActivityFragment extends Fragment {
    int[] tabImage = new int[]{R.drawable.imagesalon};
    private String[] tabAllSalons;
    private String[] tabAllProprietaires;
    private String[] tabAllStreets;
    private List<String> tabAllNomsSalon;
    private List<String> getAllSalons;
    private List<String> getAllProprietaires;
    private List<String> getAllStreets;
    private String[] newTokens2;
    private String[] newTokens3;
    private String[] newTokens;
    private int position;
    private String nomSalon;
    private String adresse;
    private String telephone;
    private String typeSalon;
    private String streetId;
    private String proprietaireId;
    private String streetName;
    private String prenom;
    private String nom;

    public SalonActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_salon, container, false);

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        tabAllNomsSalon = new ArrayList<String>();
        if (extras != null) {
            tabAllNomsSalon = extras.getStringArrayList("TAG_ALLNOMSALON");
            getAllSalons = extras.getStringArrayList("TAG_ALLSALON");
            getAllProprietaires = extras.getStringArrayList("TAG_ALLPROPRIETAIRE");
            getAllStreets = extras.getStringArrayList("TAG_ALLSTEERT");
        }

        String[] tabNomSalon = tabAllNomsSalon.toArray(new String[tabAllNomsSalon.size()]);

        tabAllProprietaires = getAllProprietaires.toArray(new String[getAllProprietaires.size()]);
        tabAllStreets = getAllStreets.toArray(new String[getAllStreets.size()]);

        CustomListSalon adapter = new CustomListSalon(getActivity(), tabNomSalon, tabImage);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                tabAllSalons = getAllSalons.toArray(new String[getAllSalons.size()]);

                allTokensSalon();
                newTokensSalon();
                allTokensProprietaire();
                allTokensStreet();
                newTokensStreet();
                newTokensProprietaire();

                String prenom_nom = prenom + " " + nom;

                Intent intent = new Intent(getActivity(), DetailsSalonActivity.class);
                intent.putExtra("TAG_NOMSALON", nomSalon);
                intent.putExtra("TAG_TYPESALON", typeSalon);
                intent.putExtra("TAG_ADRESSE", adresse);
                intent.putExtra("TAG_TELEPHONE", telephone);

                intent.putExtra("TAG_NOMPROPIETAIRE", prenom_nom);
                intent.putExtra("TAG_NOMSTREET", streetName);

                intent.putStringArrayListExtra("TAG_ALLNOMSALON", (ArrayList<String>) tabAllNomsSalon);

                startActivity(intent);
            }
        });
        return rootView;
    }

    public String[] allTokensSalon() {
        StringTokenizer stringTokenizer = new StringTokenizer(tabAllSalons[position], ",");
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

    public void newTokensSalon() {
        int j = 0;
        String idSalon = newTokens[j].trim();
        j++;
        nomSalon = newTokens[j].trim();
        j++;
        typeSalon = newTokens[j].trim();
        j++;
        String longitude = newTokens[j].trim();
        j++;
        String latitude = newTokens[j].trim();
        j++;
        telephone = newTokens[j].trim();
        j++;
        proprietaireId = newTokens[j].trim();
        j++;
        streetId = newTokens[j].trim();
        j++;
        adresse = newTokens[j];

        System.out.println("xxxx - salon " + idSalon + "," + nomSalon + "," + longitude + "," + latitude + "," + telephone
                + "," + proprietaireId + "," + streetId + "," + adresse);
    }

    public String[] allTokensProprietaire() {
        int a = Integer.parseInt(proprietaireId);
        int idproprietaire = a - 1;
        StringTokenizer stringTokenizer2 = new StringTokenizer(tabAllProprietaires[idproprietaire], ",");
        newTokens2 = new String[stringTokenizer2.countTokens()];
        System.out.println("*** " + stringTokenizer2.countTokens());
        while (stringTokenizer2.hasMoreElements()) {
            for (int i = 0; i < newTokens2.length; i++) {
                newTokens2[i] = (String) stringTokenizer2.nextElement();
                System.out.println("===== " + newTokens2[i]);
            }
        }
        return newTokens2;
    }

    public void newTokensProprietaire() {
        int j = 0;
        String idProprietaire = newTokens2[j].trim();
        j++;
        prenom = newTokens2[j].trim();
        j++;
        nom = newTokens2[j].trim();
        j++;
        String adressepro = newTokens2[j].trim();
        j++;
        String telephonepro = newTokens2[j].trim();

        System.out.println("xxxx - proprietaire " + idProprietaire + "," + prenom + "," + nom + "," + adressepro + "," + telephonepro);
    }

    public String[] allTokensStreet() {
        int a = Integer.parseInt(streetId);
        int idstreet = a - 1;
        StringTokenizer stringTokenizer3 = new StringTokenizer(tabAllStreets[idstreet], ",");
        newTokens3 = new String[stringTokenizer3.countTokens()];
        System.out.println("*** " + stringTokenizer3.countTokens());
        while (stringTokenizer3.hasMoreElements()) {
            for (int i = 0; i < newTokens3.length; i++) {
                newTokens3[i] = (String) stringTokenizer3.nextElement();
                System.out.println("===== " + newTokens3[i]);
            }
        }
        return newTokens3;
    }

    public void newTokensStreet() {
        int j = 0;
        String idStreet = newTokens3[j].trim();
        j++;
        String region = newTokens3[j].trim();
        j++;
        String departement = newTokens3[j].trim();
        j++;
        streetName = newTokens3[j].trim();

        System.out.println("xxxx - salon " + idStreet + "," + region + "," + departement + "," + streetName);
    }
}
