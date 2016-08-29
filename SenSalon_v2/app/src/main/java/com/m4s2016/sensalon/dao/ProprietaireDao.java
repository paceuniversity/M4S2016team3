package com.m4s2016.sensalon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.m4s2016.sensalon.model.Proprietaire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAGUETTE on 09/08/2016.
 */
public class ProprietaireDao {

    static SQLiteDatabase sqLiteDatabase;
    static SenSalonDatabaseHelper senSalonDatabaseHelper;

    String[] all_proprietaires= {SenSalonDatabaseHelper.IDPROPRIETAIRE,SenSalonDatabaseHelper.PRENOMPROPRIETAIRE,SenSalonDatabaseHelper.NOMPROPRIETAIRE,SenSalonDatabaseHelper.ADRESSEPROPRIETAIRE,SenSalonDatabaseHelper.TELEPHONEPROPRIETAIRE};

    private Context mcontext;
    private Proprietaire proprietaire;

    public ProprietaireDao(Context context) {
        this.mcontext=context;
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

    public Proprietaire createProprietaire(Proprietaire proprietaire){
        ContentValues values= new ContentValues();
        values.put(SenSalonDatabaseHelper.IDPROPRIETAIRE,proprietaire.getIdProprietaire());
        values.put(SenSalonDatabaseHelper.PRENOMPROPRIETAIRE,proprietaire.getPrenom());
        values.put(SenSalonDatabaseHelper.NOMPROPRIETAIRE,proprietaire.getNom());
        values.put(SenSalonDatabaseHelper.ADRESSEPROPRIETAIRE,proprietaire.getAdresse());
        values.put(SenSalonDatabaseHelper.TELEPHONEPROPRIETAIRE,proprietaire.getTelephone());
        Long idInsert= sqLiteDatabase.insert(SenSalonDatabaseHelper.TABLENAMEPROPRIETAIRE, null, values);
        Cursor cursor=sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEPROPRIETAIRE, all_proprietaires, SenSalonDatabaseHelper.IDPROPRIETAIRE + " = "+
                idInsert,null,null,null,null);
        cursor.moveToFirst();
        Proprietaire proprietaire1= cursorToProprietaire(cursor);
        cursor.close();
        return proprietaire1 ;
    }

    public List<Proprietaire> getAllProprietaires() {
        List<Proprietaire> listProprietaires= new ArrayList<Proprietaire>();
        Cursor cursor= sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEPROPRIETAIRE,all_proprietaires,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Proprietaire proprietaire=cursorToProprietaire(cursor);
                listProprietaires.add(proprietaire);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listProprietaires;
    }

    public Proprietaire getProprietaireById(long idProprietaire){
        Log.d("getProprietaireById ", ""+idProprietaire);
        Cursor cursor=sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEPROPRIETAIRE, all_proprietaires, SenSalonDatabaseHelper.IDPROPRIETAIRE +" = ?",
                new String[] { String.valueOf(idProprietaire) },null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Proprietaire proprietaire = cursorToProprietaire(cursor);
        return proprietaire;
    }

    public Proprietaire cursorToProprietaire(Cursor cursor) {
        Proprietaire proprietaire1 = new Proprietaire();
        proprietaire1.setIdProprietaire(cursor.getLong(0));
        proprietaire1.setPrenom(cursor.getString(1));
        proprietaire1.setNom(cursor.getString(2));
        proprietaire1.setAdresse(cursor.getString(3));
        proprietaire1.setTelephone(cursor.getString(4));
        return proprietaire1;
    }

}
