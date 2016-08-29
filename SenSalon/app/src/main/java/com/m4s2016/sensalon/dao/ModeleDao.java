package com.m4s2016.sensalon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.m4s2016.sensalon.model.Modele;
import com.m4s2016.sensalon.model.Salon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAGUETTE on 09/08/2016.
 */
public class ModeleDao {

    static SQLiteDatabase sqLiteDatabase;
    static SenSalonDatabaseHelper senSalonDatabaseHelper;

    String[] all_modeles= {SenSalonDatabaseHelper.IDMODELE,SenSalonDatabaseHelper.MODELNAME,SenSalonDatabaseHelper.MODELDURATION,SenSalonDatabaseHelper.MODELPRICE,SenSalonDatabaseHelper.IMAGE,SenSalonDatabaseHelper.SALON_ID};

    private Context context;
    private Modele model;

    public ModeleDao(Context context) {
        this.context=context;
        senSalonDatabaseHelper = new SenSalonDatabaseHelper(context);
        try {
            open();
        }catch (SQLException e){
            Log.e("Connection ", "Openning connection " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() {
        sqLiteDatabase=senSalonDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        senSalonDatabaseHelper.close();
    }

    public Modele createModele(Modele modele,long _idSalon){
        ContentValues values= new ContentValues();
        values.put(SenSalonDatabaseHelper.IDMODELE,modele.getIdModele());
        values.put(SenSalonDatabaseHelper.MODELNAME,modele.getModelName());
        values.put(SenSalonDatabaseHelper.MODELDURATION,modele.getModelDuration());
        values.put(SenSalonDatabaseHelper.MODELPRICE,modele.getModelPrice());
        values.put(SenSalonDatabaseHelper.IMAGE,modele.getImage());
        values.put(SenSalonDatabaseHelper.SALON_ID,_idSalon);
        Long idInsert=
                 sqLiteDatabase.insert(SenSalonDatabaseHelper.TABLENAMEMODEL, null, values);
        Cursor cursor=sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEMODEL, all_modeles, SenSalonDatabaseHelper.IDMODELE + " = "+
                idInsert,null,null,null,null);
        cursor.moveToFirst();
        Modele modele1= cursorToModel(cursor);
        cursor.close();
        return modele1 ;
    }

    public List<Modele> getAllModeles() {
        List<Modele> listModeles= new ArrayList<Modele>();
        Cursor cursor= sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEMODEL,all_modeles,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Modele modele=cursorToModel(cursor);
                listModeles.add(modele);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listModeles;
    }

    public Modele getModeleById(long idModele){
        Log.d("getModeleById ", ""+idModele);
        Cursor cursor=sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEMODEL, all_modeles, SenSalonDatabaseHelper.IDMODELE +" = ?",
                new String[] { String.valueOf(idModele) },null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Modele modele = cursorToModel(cursor);
        return modele;
    }

    public Modele cursorToModel(Cursor cursor) {
        Modele modele = new Modele();
        modele.setIdModele(cursor.getLong(0));
        modele.setModelName(cursor.getString(1));
        modele.setModelDuration(cursor.getString(2));
        modele.setModelPrice(cursor.getString(3));
        modele.setSalonId(cursor.getLong(4));

        return modele;
    }

}
