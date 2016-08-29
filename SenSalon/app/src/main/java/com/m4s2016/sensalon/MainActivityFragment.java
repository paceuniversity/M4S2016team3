package com.m4s2016.sensalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.m4s2016.sensalon.dao.ModeleDao;
import com.m4s2016.sensalon.dao.ProprietaireDao;
import com.m4s2016.sensalon.dao.SalonDao;
import com.m4s2016.sensalon.dao.StreetDao;
import com.m4s2016.sensalon.model.Modele;
import com.m4s2016.sensalon.model.Proprietaire;
import com.m4s2016.sensalon.model.Salon;
import com.m4s2016.sensalon.model.Street;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private StreetDao streetDao;
    private ProprietaireDao proprietaireDao;
    private SalonDao salonDao;
    private ModeleDao modelDao;

    private List<Salon> salonList;
    private List<Modele> modeleList;
    private List<Proprietaire> proprietaireList;
    private List<Street> streetList;
    private List<String> tabLat;
    private List<String> tabLng;
    private List<String> tabAllNomsSalon;
    private List<String> tabAllSalons;
    private List<String> tabAllModeles;
    private List<String> tabAllNomModeles;

    private List<String> tabAllProprietaires;
    private List<String> tabAllStreet;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Create instance of the class Street
        streetDao = new StreetDao(this.getActivity());
        streetDao.open();
        createStreet();
        getAllStreets();

        //Create instance of the class Proprietaire
        proprietaireDao = new ProprietaireDao(this.getActivity());
        proprietaireDao.open();
        createProprietaire();
        getAllProprietaires();

        //Create instance of my class Salon
        salonDao = new SalonDao(this.getActivity());
        salonDao.open();
        createSalon();
        getAllLatitude();
        getAllLongitude();
        getAllNomsSalon();
        getAllSalons();

        //Create instance of the class Modele
        modelDao = new ModeleDao(this.getActivity());
        modelDao.open();
        createModele();
        getAllModele();
        getAllNomModeles();

//        //Create instance of the class User
//        userDao = new UserDao(this.getActivity());
//        userDao.open();

        ImageView imageView1 = (ImageView) rootView.findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) rootView.findViewById(R.id.imageView4);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You click on Activity Maps", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MapsActivity.class);

                intent.putStringArrayListExtra("TAG_ALLLATITUDE", (ArrayList<String>) tabLat);
                intent.putStringArrayListExtra("TAG_ALLLONGITUDE", (ArrayList<String>) tabLng);
                intent.putStringArrayListExtra("TAG_ALLNOMSALON", (ArrayList<String>) tabAllNomsSalon);

                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You click on Activity List Salon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SalonActivity.class);

                intent.putStringArrayListExtra("TAG_ALLNOMSALON", (ArrayList<String>) tabAllNomsSalon);
                intent.putStringArrayListExtra("TAG_ALLSALON", (ArrayList<String>) tabAllSalons);
                intent.putStringArrayListExtra("TAG_ALLPROPRIETAIRE", (ArrayList<String>) tabAllProprietaires);
                intent.putStringArrayListExtra("TAG_ALLSTEERT", (ArrayList<String>) tabAllStreet);

                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You click on Activity List Model", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ModeleActivity.class);
                intent.putStringArrayListExtra("TAG_ALLNOMMODELE", (ArrayList<String>) tabAllNomModeles);
                intent.putStringArrayListExtra("TAG_ALLMODELE", (ArrayList<String>) tabAllModeles);
                intent.putStringArrayListExtra("TAG_ALLSALON", (ArrayList<String>) tabAllSalons);

                startActivity(intent);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You click on Activity Faqs", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), FaqsActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    public void createStreet() {
        //Create new Street
        streetDao.createStreet(new Street(1L, "Dakar", "Dakar", "Liberte4"));
        streetDao.createStreet(new Street(2L, "Dakar", "Dakar", "Hanne Mariste"));
        streetDao.createStreet(new Street(3L, "Dakar", "Dakar", "Fass"));
        streetDao.createStreet(new Street(4L, "Dakar", "Dakar", "Castor"));
        streetDao.createStreet(new Street(5L, "Dakar", "Dakar", "Ouest Foire"));

        // List all street
        streetList = streetDao.getAllStreets();

    }

    public List<String> getAllStreets() {
        tabAllStreet = new ArrayList<>();
        for (int j = 0; j < streetList.size(); j++) {
            tabAllStreet.add( streetList.get(j).getIdStreet() + ", " + streetList.get(j).getRegion() + ", " + streetList.get(j).getDepartement() + ", " + streetList.get(j).getStreetName());
            System.out.println("All streets: " + streetList.get(j).getIdStreet() + ", " + streetList.get(j).getDepartement() + ", " + streetList.get(j).getStreetName());
        }
        return tabAllStreet;
    }

    public void createProprietaire() {
        //Create new Proprietaire
        proprietaireDao.createProprietaire(new Proprietaire(1L, "AWA", "NDAW", "Dakar", "77 932 74 10"));
        proprietaireDao.createProprietaire(new Proprietaire(2L, "PAPE ABDOU", "DIENG", "Dakar", "77 651 41 85"));
        proprietaireDao.createProprietaire(new Proprietaire(3L, "BIRAME", "SALLA", "Dakar", "77 785 98 78"));
        proprietaireDao.createProprietaire(new Proprietaire(4L, "MICHAEL", "CORREA", "Dakar", "77 641 30 87"));
        proprietaireDao.createProprietaire(new Proprietaire(5L, "SIBY", "DIABATE", "Dakar", "77 965 52 31"));
        proprietaireDao.createProprietaire(new Proprietaire(6L, "MICHEL", "KA", "Dakar", "77 217 82 40"));
        proprietaireDao.createProprietaire(new Proprietaire(7L, "NDEYE fATOU", "NDIAYE", "Dakar", "77 330 01 18"));
        proprietaireDao.createProprietaire(new Proprietaire(8L, "AMINATA", "DIOP", "Dakar", "77 145 20 06"));
        proprietaireDao.createProprietaire(new Proprietaire(9L, "SAMBA", "TOURE", "Dakar", "77 354 31 30"));
        proprietaireDao.createProprietaire(new Proprietaire(10L, "ORTENCE", "BIAYE", "Dakar", "77 636 66 86"));

        // List all proprietaire
       proprietaireList = proprietaireDao.getAllProprietaires();
    }

    public List<String> getAllProprietaires() {
        tabAllProprietaires = new ArrayList<>();
        for (int j = 0; j < proprietaireList.size(); j++) {
            tabAllProprietaires.add(proprietaireList.get(j).getIdProprietaire() + ", " + proprietaireList.get(j).getPrenom() + ", " + proprietaireList.get(j).getNom() + ", " + proprietaireList.get(j).getAdresse() +
                    ", " + proprietaireList.get(j).getTelephone());

            System.out.println("All proprietaires: " + proprietaireList.get(j).getIdProprietaire() + ", " + proprietaireList.get(j).getPrenom()
                    + ", " + proprietaireList.get(j).getNom() + ", " + proprietaireList.get(j).getAdresse() +
                    ", " + proprietaireList.get(j).getTelephone());
        }
        return tabAllProprietaires;
    }

    public void createSalon() {
        //Create new Salon
        salonDao.createSalon(new Salon(1L, "Latifah Coiffure", "14.720010", "-17.448983", "Boulevard du Président Habib Bouguiba", "33 864 74 75", "Femme"), 1, 1);
        salonDao.createSalon(new Salon(2L, "Sope Khadim Coiffure", "14.695786", "-17.447393", "Boulevard du General de Gaulle", "33 821 64 64", "Homme"), 1, 2);
        salonDao.createSalon(new Salon(3L, "Beauty Coiffure", "14.729541", "-17.460921", "Rte du Front de Terre", "77 785 98 78", "Homme-Femme"), 1, 3);
        salonDao.createSalon(new Salon(4L, "MICHAEL COIFFURE", "14.696832", "-17.450450", "HLM Grand Dakar", "77 641 30 87", "Homme"), 2, 4);
        salonDao.createSalon(new Salon(5L, "Siby Coiffure", "14.717703", "-17.466275", "Sicap Sacre coeur", "77 965 52 31", "Homme"), 2, 5);
        salonDao.createSalon(new Salon(6L, "Michele Ka Coiffure", "14.694985", "-17.461908", "Point E", "77 217 82 40", "Homme"), 2, 5);
        salonDao.createSalon(new Salon(7L, "Hair Studio Coiffure", "14.668914", "-17.434271", "Rue Mohamed V", "33 821 73 63", "Femme"), 2, 3);
        salonDao.createSalon(new Salon(8L, "Coupe De Tête Coiffure", "14.680123", "-17.462509", "10 boulevard Martin Luther King, Corniche Ouest FANN HOCK ", "33 822 98 99", "Femme"), 2, 4);
        salonDao.createSalon(new Salon(9L, "Pullman Téranga Coiffure", "14.667731", "-17.431310", "Place De L'Indépendance, 3 Rue Parent X Rue Carnot", "33 823 53 78", "Homme-Femme"), 2, 1);
        salonDao.createSalon(new Salon(10L, "KAMAL COIFFURE", "14.709881", "-17.461466", "Sicap Liberté", "33 821 37 16", "Femme"), 2, 2);

        // List all salon
        salonList = salonDao.getAllSalons();
   }

    public List<String> getAllSalons() {
        tabAllSalons = new ArrayList<>();

        for (int i = 0; i < salonList.size(); i++) {
            tabAllSalons.add(salonList.get(i).getIdSalon() + "," + salonList.get(i).getNomSalon() + "," + salonList.get(i).getTypeSalon() + "," +
                    salonList.get(i).getLatitude() + "," + salonList.get(i).getLongitude() + "," + salonList.get(i).getTelephone() + "," + salonList.get(i).getStreetId() + "," +
                    salonList.get(i).getProprietaireId() + "," + salonList.get(i).getAdresse());

            System.out.println("All salons :"+ salonList.get(i).getIdSalon() + "," + salonList.get(i).getNomSalon() + "," + salonList.get(i).getTypeSalon() + "," +
                    salonList.get(i).getLatitude() + "," + salonList.get(i).getLongitude() + "," + salonList.get(i).getTelephone() + "," + salonList.get(i).getStreetId() + "," +
                    salonList.get(i).getProprietaireId() + "," + salonList.get(i).getAdresse());
        }
        return tabAllSalons;
    }

    public List<String> getAllLatitude() {
        tabLat = new ArrayList<>();
        for (int i = 0; i < salonList.size(); i++) {
            tabLat.add(salonList.get(i).getLatitude());
        }
        return tabLat;
    }

    public List<String> getAllLongitude() {
        tabLng = new ArrayList<>();
        for (int i = 0; i < salonList.size(); i++) {
            tabLng.add(salonList.get(i).getLongitude());
        }
        return tabLng;
    }

    public List<String> getAllNomsSalon() {
        tabAllNomsSalon = new ArrayList<>();
        for (int i = 0; i < salonList.size(); i++) {
            tabAllNomsSalon.add(salonList.get(i).getNomSalon());
        }
        return tabAllNomsSalon;
    }

    public void createModele() {
        int[] tabImage = new int[]{R.drawable.femme1, R.drawable.femme2, R.drawable.femme3, R.drawable.femme4, R.drawable.femme5,
                R.drawable.femme6, R.drawable.femme7, R.drawable.femme8, R.drawable.femme9, R.drawable.femme10,
                R.drawable.homme1, R.drawable.homme2, R.drawable.homme3, R.drawable.homme4, R.drawable.homme5,
                R.drawable.homme6, R.drawable.homme7, R.drawable.homme8, R.drawable.homme9, R.drawable.homme10,
        };

        //Create new Modele
        modelDao.createModele(new Modele(1L, "Mod1", "500", "30mn", tabImage[0]), 1);
        modelDao.createModele(new Modele(2L, "Mod2", "500", "30mn", tabImage[1]), 1);
        modelDao.createModele(new Modele(3L, "Mod3", "800", "30mn", tabImage[2]), 3);
        modelDao.createModele(new Modele(4L, "Mod4", "500", "30mn", tabImage[3]), 8);
        modelDao.createModele(new Modele(5L, "Mod5", "1000", "1h", tabImage[4]), 8);
        modelDao.createModele(new Modele(6L, "Mod6", "500", "30mn", tabImage[5]), 7);
        modelDao.createModele(new Modele(7L, "Mod7", "500", "30mn", tabImage[6]), 7);
        modelDao.createModele(new Modele(8L, "Mod8", "800", "30mn", tabImage[7]), 9);
        modelDao.createModele(new Modele(9L, "Mod9", "500", "30mn", tabImage[8]), 10);
        modelDao.createModele(new Modele(10L, "Mod10", "1000", "1h", tabImage[9]), 10);
        modelDao.createModele(new Modele(11L, "Mod11", "500", "30mn", tabImage[10]), 2);
        modelDao.createModele(new Modele(12L, "Mod12", "500", "30mn", tabImage[11]), 2);
        modelDao.createModele(new Modele(13L, "Mod13", "800", "30mn", tabImage[12]), 3);
        modelDao.createModele(new Modele(14L, "Mod14", "500", "30mn", tabImage[13]), 4);
        modelDao.createModele(new Modele(15L, "Mod15", "1000", "1h", tabImage[14]), 4);
        modelDao.createModele(new Modele(16L, "Mod16", "500", "30mn", tabImage[15]), 5);
        modelDao.createModele(new Modele(17L, "Mod7", "500", "30mn", tabImage[16]), 5);
        modelDao.createModele(new Modele(18L, "Mod18", "800", "30mn", tabImage[17]), 9);
        modelDao.createModele(new Modele(19L, "Mod19", "500", "30mn", tabImage[18]), 6);
        modelDao.createModele(new Modele(20L, "Mod20", "1000", "1h", tabImage[19]), 6);

        // List all modele
        modeleList = modelDao.getAllModeles();
    }

    public List<String> getAllModele() {
        System.out.println("***ModeleList.size()*** " + modeleList.size());
        tabAllModeles = new ArrayList<>();
        for (int i = 0; i < modeleList.size(); i++) {
            tabAllModeles.add(modeleList.get(i).getIdModele() + ", " + modeleList.get(i).getModelName() + ", " + modeleList.get(i).getModelDuration() +
                    ", " + modeleList.get(i).getModelPrice() + ", " + modeleList.get(i).getImage() + ", " +  modeleList.get(i).getSalonId());
            System.out.println("All modeles :" +modeleList.get(i).getIdModele() + ", " + modeleList.get(i).getModelName() + ", " + modeleList.get(i).getModelDuration() +
                    ", " + modeleList.get(i).getModelPrice() + ", " +  modeleList.get(i).getSalonId() + ", " + modeleList.get(i).getImage() );
        }
        return tabAllModeles;
    }

    public List<String> getAllNomModeles() {
        tabAllNomModeles = new ArrayList<>();
        for (int i = 0; i < modeleList.size(); i++) {
            tabAllNomModeles.add(modeleList.get(i).getModelName());
        }
        return tabAllNomModeles;
    }

}
