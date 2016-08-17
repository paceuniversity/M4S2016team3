package com.m4s2016.sensalon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.m4s2016.sensalon.model.Modele;
import com.m4s2016.sensalon.model.Proprietaire;
import com.m4s2016.sensalon.model.Salon;
import com.m4s2016.sensalon.model.Street;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAGUETTE on 09/08/2016.
 */
public class SalonDao {

    static SQLiteDatabase sqLiteDatabase;
    static SenSalonDatabaseHelper senSalonDatabaseHelper;

    String[] all_salons= {SenSalonDatabaseHelper.IDSALON,SenSalonDatabaseHelper.NOMSALON,SenSalonDatabaseHelper.LATITUDE,
            SenSalonDatabaseHelper.LONGITUDE,SenSalonDatabaseHelper.ADRESSESALON,SenSalonDatabaseHelper.TELEPHONESALON,
            SenSalonDatabaseHelper.TYPESALON,SenSalonDatabaseHelper.STREET_ID,SenSalonDatabaseHelper.PROPRIETAIRE_ID};

    private Context context;
    private Salon salon;

    public SalonDao(Context context) {
        this.context=context;
        senSalonDatabaseHelper = new SenSalonDatabaseHelper(context);
        try {
            open();
        }catch (SQLException e){
            Log.e("Connection ", "Openning connection " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void open() {
        sqLiteDatabase=senSalonDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        senSalonDatabaseHelper.close();
    }

       public long createSalon(Salon salon, long _idStreet,long _idProprietaire){
        ContentValues values= new ContentValues();
        values.put(SenSalonDatabaseHelper.IDSALON,salon.getIdSalon());
        values.put(SenSalonDatabaseHelper.NOMSALON,salon.getNomSalon());
        values.put(SenSalonDatabaseHelper.LONGITUDE,salon.getLongitude());
        values.put(SenSalonDatabaseHelper.LATITUDE,salon.getLatitude());
        values.put(SenSalonDatabaseHelper.ADRESSESALON,salon.getAdresse());
        values.put(SenSalonDatabaseHelper.TELEPHONESALON,salon.getTelephone());
        values.put(SenSalonDatabaseHelper.TYPESALON,salon.getTypeSalon());
        values.put(SenSalonDatabaseHelper.STREET_ID,_idStreet);
        values.put(SenSalonDatabaseHelper.PROPRIETAIRE_ID,_idProprietaire);
//        Long idInsert=
                return sqLiteDatabase.insert(SenSalonDatabaseHelper.TABLENAMESALON, null, values);
//        Cursor cursor=sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMESALON, all_salons, SenSalonDatabaseHelper.IDSALON + " = "+
//                idInsert,null,null,null,null);
//        cursor.moveToFirst();
//        Salon salon1= cursorToSalon(cursor);
//        cursor.close();
//        return salon1 ;
    }

    public List<Salon> getAllSalons() {
        List<Salon> listSalons= new ArrayList<Salon>();
        Cursor cursor= sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMESALON,all_salons,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Salon salon=cursorToSalon(cursor);
                listSalons.add(salon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listSalons;
    }

    public Salon getSalonById(long idSalon){
        Log.d("getSalonById ", ""+idSalon);
        Cursor cursor=sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMESALON, all_salons, SenSalonDatabaseHelper.IDSALON +" = ?",
                new String[] { String.valueOf(idSalon) },null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Salon salon = cursorToSalon(cursor);
        return salon;
    }

    public Salon cursorToSalon(Cursor cursor) {
        Salon salon = new Salon();
        salon.setIdSalon(cursor.getLong(0));
        salon.setNomSalon(cursor.getString(1));
        salon.setLongitude(cursor.getString(2));
        salon.setLatitude(cursor.getString(3));
        salon.setAdresse(cursor.getString(4));
        salon.setTelephone(cursor.getString(5));
        salon.setTypeSalon(cursor.getString(6));
        salon.setStreetId(cursor.getLong(7));
        salon.setProprietaireId(cursor.getLong(8));

//        long idStreet= cursor.getLong(7);
//        StreetDao streetDao = new StreetDao(context);
//        Street street= streetDao.getStreetById(idStreet);
//        if(street != null)
//            salon.setStreet(street);
//
//        long idProprietaire = cursor.getLong(8);
//        ProprietaireDao proprietaireDao = new ProprietaireDao(context);
//        Proprietaire proprietaire=proprietaireDao.getProprietaireById(idProprietaire);
//        if (proprietaire != null)
//            salon.setProprietaire(proprietaire);
        return salon;
    }


}
