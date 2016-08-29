package com.m4s2016.sensalon;

import android.support.v7.app.AppCompatActivity;

import com.m4s2016.sensalon.dao.ModeleDao;
import com.m4s2016.sensalon.dao.ProprietaireDao;
import com.m4s2016.sensalon.dao.SalonDao;
import com.m4s2016.sensalon.dao.StreetDao;
import com.m4s2016.sensalon.dao.UserDao;
import com.m4s2016.sensalon.model.Modele;
import com.m4s2016.sensalon.model.Proprietaire;
import com.m4s2016.sensalon.model.Salon;
import com.m4s2016.sensalon.model.Street;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 29/08/2016.
 */
public class SalonData extends AppCompatActivity {

    public void createStreet(){
        //Create instance of the class Modele
        StreetDao streetDao = new StreetDao(this.getApplicationContext());

        //Open database
        streetDao.open();

        //Create new Street
        streetDao.createStreet(new Street(1L,"Dakar","Dakar","Liberte4"));
        streetDao.createStreet(new Street(2L,"Dakar","Dakar","Hann Mariste"));

        // List all street
        List<Street> streetList= streetDao.getAllStreets();
        List<String> newStreetList= new ArrayList<String>();
        for (int j=0;j<streetList.size();j++) {
            newStreetList.add(streetList.get(j).getStreetName());
            System.out.println("All streets: "+ streetList.get(j).getIdStreet()+", "+  streetList.get(j).getDepartement()  +", "+streetList.get(j).getStreetName());
        }
    }

    public void createProprietaire(){
        //Create instance of the class Proprietaire
        ProprietaireDao proprietaireDao = new ProprietaireDao(this.getApplicationContext());

        //Open database
        proprietaireDao.open();

        //Create new Proprietaire
        proprietaireDao.createProprietaire(new Proprietaire(1,"Lionnel Patrick","DOOKO","Dakar","7722222222"));
        proprietaireDao.createProprietaire(new Proprietaire(2,"KHADIM","GAYE","Dakar","773333333"));
        proprietaireDao.createProprietaire(new Proprietaire(3,"KHADIM","GAYE","Dakar","7744444444"));
        proprietaireDao.createProprietaire(new Proprietaire(4,"KHADIM","GAYE","Dakar","775555555"));
        proprietaireDao.createProprietaire(new Proprietaire(5,"KHADIM","GAYE","Dakar","776666666"));
        proprietaireDao.createProprietaire(new Proprietaire(6,"KHADIM","GAYE","Dakar","778888888"));

        // List all proprietaire
        List<Proprietaire> proprietaireList= proprietaireDao.getAllProprietaires();
        List<String> newProprietaireList= new ArrayList<String>();
        for (int j=0;j<proprietaireList.size();j++) {
            System.out.println("All proprietaires: "+ proprietaireList.get(j).getIdProprietaire()+ ", "+ proprietaireList.get(j).getPrenom()+", "+ proprietaireList.get(j).getNom()  +", "+proprietaireList.get(j).getAdresse()+
                    ", "+proprietaireList.get(j).getTelephone());
        }
    }

    public void createSalon(){
        //Create instance of my class Modele
        SalonDao salonDao = new SalonDao(this.getApplicationContext());

        //Open database
        salonDao.open();

        //Create new Salon
        Salon salon = new Salon();
        salonDao.createSalon(new Salon(1L,"Latifah Coiffure","14.720010","-17.448983","Boulevard du Président Habib Bouguiba","338647475","Femme"),1,1);
        salonDao.createSalon(new Salon(2L,"Sope Khadim Coiffure","14.695786","-17.447393","Boulevard du General de Gaulle, Dakar","a","Homme"),1,1);
        salonDao.createSalon(new Salon(3L,"Beauty Coiffure","14.729541","-17.460921","Rte du Front de Terre","777859878","Homme-Femme"),1,1);
        salonDao.createSalon(new Salon(4L,"MICHAEL COIFFURE","14.696832","-17.450450","HLM Grand Dakar","776413087","Homme"),2,1);
        salonDao.createSalon(new Salon(5L,"Siby Coiffure","14.717703","-17.466275","Sicap Sacre coeur","","Homme"),2,1);
        salonDao.createSalon(new Salon(6L,"Michele Ka Coiffure","14.694985","-17.461908","Point E","","Homme"),2,1);
        salonDao.createSalon(new Salon(7L,"Hair Studio Coiffure","14.668914","-17.434271","Rue Mohamed V","33 821 73 63","Homme-Femme"),2,1);
        salonDao.createSalon(new Salon(8L,"Coupe De Tête Coiffure","14.680123","-17.462509","10 boulevard Martin Luther King, Corniche Ouest FANN HOCK ","338229899","Homme-Femme"),2,1);
        salonDao.createSalon(new Salon(9L,"Pullman Téranga Coiffure","14.667731","-17.431310","Place De L'Indépendance, 3 Rue Parent X Rue Carnot","33 823 53 78","a"),2,1);
        salonDao.createSalon(new Salon(10L,"Bruch color Coiffure","14.666984","-14.435430","Rue Felix Faure","33 821 37 16","Homme-Femme"),2,1);

        // List all street
        List<Salon> salonList= salonDao.getAllSalons();
        List<String> newSalonList= new ArrayList<String>();
        for (int j=0;j<salonList.size();j++) {
            System.out.println("All salons: "+ salonList.get(j).getIdSalon()+", "+ salonList.get(j).getNomSalon()  +", "+salonList.get(j).getTypeSalon()+
                    ", "+salonList.get(j).getAdresse()+", "+salonList.get(j).getTelephone()+", "+salonList.get(j).getLatitude()+
                    ", "+salonList.get(j).getLongitude()+", "+salonList.get(j).getStreetId()+", "+salonList.get(j).getProprietaireId());
        }
    }

    public void createModele(){
        //Create instance of the class Modele
        ModeleDao modelDao = new ModeleDao(this.getApplicationContext());

        //Open database
        modelDao.open();

        //Create new Modele
        modelDao.createModele(new Modele(1,"Mod1","500","30mn"),1);
        modelDao.createModele(new Modele(2,"Mod2","500","30mn"),1);
        modelDao.createModele(new Modele(3,"Mod3","800","30mn"),2);
        modelDao.createModele(new Modele(4,"Mod4","500","30mn"),2);
        modelDao.createModele(new Modele(5,"Mod5","1000","1h"),4);

        // List all modele
        List<Modele> modeleList= modelDao.getAllModeles();
        List<String> newModeleList= new ArrayList<String>();
        for (int j=0;j<modeleList.size();j++) {
            System.out.println("All modeles: "+ modeleList.get(j).getIdModele()+", "+ modeleList.get(j).getModelName()  +", "+modeleList.get(j).getModelDuration()+
                    ", "+modeleList.get(j).getModelPrice());
        }
    }

    public void createUser(){
        //Create instance of the class User
        UserDao userDao = new UserDao(this.getApplicationContext());

        //Open database
        userDao.open();

        //Create new Street

        // List all street
    }

}
