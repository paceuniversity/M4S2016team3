package com.m4s2016.sensalon;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.m4s2016.sensalon.dao.ModeleDao;
import com.m4s2016.sensalon.dao.ProprietaireDao;
import com.m4s2016.sensalon.dao.SalonDao;
import com.m4s2016.sensalon.dao.StreetDao;
import com.m4s2016.sensalon.dao.UserDao;
import com.m4s2016.sensalon.model.Modele;
import com.m4s2016.sensalon.model.Proprietaire;
import com.m4s2016.sensalon.model.Salon;
import com.m4s2016.sensalon.model.Street;
import com.m4s2016.sensalon.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private View rootView;
    private ProgressBar progressBar;
    StreetDao streetDao;
    ProprietaireDao proprietaireDao;
    SalonDao salonDao;
    ModeleDao modelDao;
    UserDao userDao;

    private List<Street> streetList;
    private List<Proprietaire> proprietaireList;
    private List<Salon> salonList;
    private List<Modele> modeleList;

    private List<String> newStreetList;
    private List<String> newProprietaireList;
    private List<String> newSalonList;
    private List<String> newModeleList;

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;

    private final String TAG_IDSALON="idSalon";
    private final String TAG_NOMSALON="nomSalon";
    private final String TAG_LONGITUDE="longitude";
    private final String TAG_LATITUDE="latitude";
    private final String TAG_ADRESSE="adresse";
    private final String TAG_TELEPHONE="telephone";
    private final String TAG_TYPESALON="typeSalon";
    private final String TAG_ALLLATITUDE="allLatitudeSalon";
    private final String TAG_ALLLONGITUDE="allLongitudeSalon";
    private final String TAG_ALLNOMSALON="allNomSalon";
    private final String TAG_ALLMODELE="allNomModele";

    private long idSalon;
    private String nomSalon;
    private String longitude;
    private String latitude;
    private String adresse;
    private String telephone;
    private String typeSalon;

    List<String> tabLat;
    List<String> tabLng;
    List<String> tabNomSalon;
    private int[] tabImage;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_main, container, false);

        //Create instance of the class Street
        streetDao = new StreetDao(this.getActivity());
        streetDao.open();
        createStreet();

        //Create instance of the class Proprietaire
        proprietaireDao = new ProprietaireDao(this.getActivity());
        proprietaireDao.open();
        createProprietaire();

        //Create instance of my class Salon
        salonDao = new SalonDao(this.getActivity());
        salonDao.open();
        createSalon();
        getAllLatitude();
        getAllLongitude();
        getAllNomSalon();

        //Create instance of the class Modele
        modelDao = new ModeleDao(this.getActivity());
        modelDao.open();
        createModele();
        getAllModele();

        //Create instance of the class User
        userDao = new UserDao(this.getActivity());
        userDao.open();
        createUser();

        imageView1= (ImageView) rootView.findViewById(R.id.imageView1);
        imageView2= (ImageView) rootView.findViewById(R.id.imageView2);
        imageView3= (ImageView) rootView.findViewById(R.id.imageView3);
        imageView4= (ImageView) rootView.findViewById(R.id.imageView4);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"You click on Activity Maps", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MapsActivity.class);

                intent.putStringArrayListExtra("TAG_ALLLATITUDE", (ArrayList<String>) tabLat);
                intent.putStringArrayListExtra("TAG_ALLLONGITUDE", (ArrayList<String>) tabLng);
                intent.putStringArrayListExtra("TAG_ALLNOMSALON", (ArrayList<String>) tabNomSalon);

                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"You click on Activity List Salon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SalonActivity.class);

                intent.putStringArrayListExtra("TAG_ALLNOMSALON", (ArrayList<String>) tabNomSalon);

                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"You click on Activity List Model", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ModeleActivity.class);

                intent.putStringArrayListExtra("TAG_ALLMODELE", (ArrayList<String>) newModeleList);

                startActivity(intent);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"You click on Activity Faqs", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), FaqsActivity.class);
                startActivity(intent);
            }
        });
        return  rootView;
    }

    public void createStreet(){
        //Create new Street
        streetDao.createStreet(new Street(1L,"Dakar","Dakar","Liberte4"));
        streetDao.createStreet(new Street(2L,"Dakar","Dakar","Han Mariste"));

        // List all street
        streetList= streetDao.getAllStreets();
        newStreetList= new ArrayList<String>();
        for (int j=0;j<streetList.size();j++) {
            newStreetList.add(streetList.get(j).getStreetName());
            System.out.println("All streets: "+ streetList.get(j).getIdStreet()+", "+  streetList.get(j).getDepartement()  +", "+streetList.get(j).getStreetName());
        }
    }

    public void createProprietaire(){
        //Create new Proprietaire
        proprietaireDao.createProprietaire(new Proprietaire(1,"AWA","NDAW","Dakar","77 932 74 10"));
        proprietaireDao.createProprietaire(new Proprietaire(2,"PAPE ABDOU","DIENG","Dakar","77 651 41 85"));
        proprietaireDao.createProprietaire(new Proprietaire(3,"BIRAME","SALLA","Dakar","77 785 98 78"));
        proprietaireDao.createProprietaire(new Proprietaire(4,"MICHAEL","CORREA","Dakar","77 641 30 87"));
        proprietaireDao.createProprietaire(new Proprietaire(5,"SIBY","DIABATE","Dakar","77 965 52 31"));
        proprietaireDao.createProprietaire(new Proprietaire(6,"MICHEL","KA","Dakar","77 217 82 40"));
        proprietaireDao.createProprietaire(new Proprietaire(7,"NDEYE fATOU","NDIAYE","Dakar","77 330 01 18"));
        proprietaireDao.createProprietaire(new Proprietaire(8,"AMINATA","DIOP","Dakar","77 145 20 06"));
        proprietaireDao.createProprietaire(new Proprietaire(9,"SAMBA","TOURE","Dakar","77 354 31 30"));
        proprietaireDao.createProprietaire(new Proprietaire(10,"ORTENCE","BIAYE","Dakar","77 636 66 86"));

        // List all proprietaire
        proprietaireList= proprietaireDao.getAllProprietaires();
        newProprietaireList= new ArrayList<String>();
        for (int j=0;j<proprietaireList.size();j++) {
            System.out.println("All proprietaires: "+ proprietaireList.get(j).getIdProprietaire()+ ", "+ proprietaireList.get(j).getPrenom()+", "+ proprietaireList.get(j).getNom()  +", "+proprietaireList.get(j).getAdresse()+
                    ", "+proprietaireList.get(j).getTelephone());
        }
    }

    public void createSalon() {
        //Create new Salon
        Salon salon = new Salon();
        salonDao.createSalon(new Salon(1L, "Latifah Coiffure", "14.720010", "-17.448983", "Boulevard du Président Habib Bouguiba", "33 864 74 75", "Femme"), 1, 1);
        salonDao.createSalon(new Salon(2L, "Sope Khadim Coiffure", "14.695786", "-17.447393", "Boulevard du General de Gaulle, Dakar", "33 821 64 64", "Homme"), 1, 1);
        salonDao.createSalon(new Salon(3L, "Beauty Coiffure", "14.729541", "-17.460921", "Rte du Front de Terre", "77 785 98 78", "Homme-Femme"), 1, 1);
        salonDao.createSalon(new Salon(4L, "MICHAEL COIFFURE", "14.696832", "-17.450450", "HLM Grand Dakar", "77 641 30 87", "Homme"), 2, 1);
        salonDao.createSalon(new Salon(5L, "Siby Coiffure", "14.717703", "-17.466275", "Sicap Sacre coeur", "77 965 52 31", "Homme"), 2, 1);
        salonDao.createSalon(new Salon(6L, "Michele Ka Coiffure", "14.694985", "-17.461908", "Point E", "77 217 82 40", "Homme"), 2, 1);
        salonDao.createSalon(new Salon(7L, "Hair Studio Coiffure", "14.668914", "-17.434271", "Rue Mohamed V", "33 821 73 63", "Femme"), 2, 1);
        salonDao.createSalon(new Salon(8L, "Coupe De Tête Coiffure", "14.680123", "-17.462509", "10 boulevard Martin Luther King, Corniche Ouest FANN HOCK ", "33 822 98 99", "Femme"), 2, 1);
        salonDao.createSalon(new Salon(9L, "Pullman Téranga Coiffure", "14.667731", "-17.431310", "Place De L'Indépendance, 3 Rue Parent X Rue Carnot", "33 823 53 78", "Homme-Femme"), 2, 1);
        salonDao.createSalon(new Salon(10L, "Bruch color Coiffure", "14.666984", "-14.435430", "Rue Felix Faure", "33 821 37 16", "Femme"), 2, 1);

        // List all salon
        salonList = salonDao.getAllSalons();
    }

        public List<String> getAllLatitude(){

        tabLat= new ArrayList<String>();
            for (int i = 0; i < salonList.size(); i++) {
                tabLat.add(salonList.get(i).getLatitude());
                Log.i("LAT", salonList.get(i).getLatitude());
            }
            return tabLat;
    }

    public List<String> getAllLongitude(){

        tabLng= new ArrayList<String>();
        for (int i = 0; i < salonList.size(); i++) {
            tabLng.add(salonList.get(i).getLongitude() );
            Log.i("LNG", salonList.get(i).getLongitude());
        }
        return tabLng;
    }

    public List<String> getAllNomSalon(){
        tabNomSalon= new ArrayList<String>();
        for (int i = 0; i < salonList.size(); i++) {
            tabNomSalon.add(salonList.get(i).getNomSalon() );
            Log.i("NOMSALON", salonList.get(i).getNomSalon());
        }
        return tabNomSalon;
    }

    public void createModele(){

        tabImage= new int[]{R.drawable.femme1,R.drawable.femme2,R.drawable.femme3,R.drawable.femme4,R.drawable.femme5,
                            R.drawable.femme6,R.drawable.femme7,R.drawable.femme8,R.drawable.femme9,R.drawable.femme10,
                            R.drawable.homme1,R.drawable.homme2,R.drawable.homme3,R.drawable.homme4,R.drawable.homme5,
                            R.drawable.homme6,R.drawable.homme7,R.drawable.homme8,R.drawable.homme9,R.drawable.homme10,
        };
        //Create new Modele
        modelDao.createModele(new Modele(1,"Mod1","500","30mn",tabImage[1]),1);
        modelDao.createModele(new Modele(2,"Mod2","500","30mn",tabImage[2]),1);
        modelDao.createModele(new Modele(3,"Mod3","800","30mn",tabImage[3]),3);
        modelDao.createModele(new Modele(4,"Mod4","500","30mn",tabImage[4]),8);
        modelDao.createModele(new Modele(5,"Mod5","1000","1h",tabImage[5]),8);
        modelDao.createModele(new Modele(6,"Mod6","500","30mn",tabImage[6]),7);
        modelDao.createModele(new Modele(7,"Mod7","500","30mn",tabImage[7]),7);
        modelDao.createModele(new Modele(8,"Mod8","800","30mn",tabImage[8]),9);
        modelDao.createModele(new Modele(9,"Mod9","500","30mn",tabImage[9]),10);
        modelDao.createModele(new Modele(10,"Mod10","1000","1h",tabImage[10]),10);
        modelDao.createModele(new Modele(11,"Mod11","500","30mn",tabImage[11]),2);
        modelDao.createModele(new Modele(12,"Mod12","500","30mn",tabImage[12]),2);
        modelDao.createModele(new Modele(13,"Mod13","800","30mn",tabImage[13]),3);
        modelDao.createModele(new Modele(14,"Mod14","500","30mn",tabImage[14]),4);
        modelDao.createModele(new Modele(15,"Mod15","1000","1h",tabImage[15]),4);
        modelDao.createModele(new Modele(16,"Mod16","500","30mn",tabImage[16]),5);
        modelDao.createModele(new Modele(17,"Mod7","500","30mn",tabImage[17]),5);
        modelDao.createModele(new Modele(18,"Mod18","800","30mn",tabImage[18]),9);
        modelDao.createModele(new Modele(19,"Mod19","500","30mn",tabImage[19]),6);
        modelDao.createModele(new Modele(20,"Mod20","1000","1h",tabImage[0]),6);

        // List all modele
        modeleList= modelDao.getAllModeles();
        newModeleList= new ArrayList<String>();
        for (int j=0;j<modeleList.size();j++) {
            System.out.println("All modeles: "+ modeleList.get(j).getIdModele()+", "+ modeleList.get(j).getModelName()  +", "+modeleList.get(j).getModelDuration()+
                    ", "+modeleList.get(j).getModelPrice()+", "+modeleList.get(j).getImage());
        }
    }

    public List<String> getAllModele(){
        newModeleList= new ArrayList<String>();
        for (int i = 0; i < modeleList.size(); i++) {
            newModeleList.add(modeleList.get(i).getModelName());
            Log.i("NOMSALON", modeleList.get(i).getModelName());
        }
        return newModeleList;
    }

    public void createUser(){

        //Create new Street

        // List all street
    }

//    private class tacheAsynchrone extends AsyncTask< Void,Integer, Void>{
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getActivity(),"Début du traitement asynchrone", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            progressBar.setProgress(values[0]);
//        }
//
//        @Override
//        protected Void doInBackground(Void... args) {
//            int progress;
//            for(progress=0; progress<=100; progress++){
//
//            }
//            for (int i = 0; i < 1000000; i++) {
//                publishProgress(progress);
//                progress++;
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            Toast.makeText(getActivity(),"Le traitement asynchrone est terminé", Toast.LENGTH_SHORT).show();
//        }
//    }

}
