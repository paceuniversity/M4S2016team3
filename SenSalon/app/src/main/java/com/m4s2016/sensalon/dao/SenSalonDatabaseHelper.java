package com.m4s2016.sensalon.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by MAGUETTE on 09/08/2016.
 */
public class SenSalonDatabaseHelper extends SQLiteOpenHelper {

    // Data: Columns of the Model table
    public static final String TABLENAMEMODEL="Modele";
    public static final String IDMODELE="idModel";
    public static final String MODELNAME="modelName";
    public static final String MODELDURATION="modelDuration";
    public static final String MODELPRICE="modelPrice";
    public static final String IMAGE="image";
    public static final String SALON_ID="salon_idSalon";

    // Data: Columns of the Proprietaire table
    public static final String TABLENAMEPROPRIETAIRE="Proprietaire";
    public static final String IDPROPRIETAIRE="idPropietaire";
    public static final String PRENOMPROPRIETAIRE="prenom";
    public static final String NOMPROPRIETAIRE="nom";
    public static final String ADRESSEPROPRIETAIRE="adresse";
    public static final String TELEPHONEPROPRIETAIRE="telephone";

    // Data: Columns of the Salon table
    public static final String TABLENAMESALON="Salon";
    public static final String IDSALON="idSalon";
    public static final String NOMSALON="nomSalon";
    public static final String LONGITUDE="longitude";
    public static final String LATITUDE="latitude";
    public static final String ADRESSESALON="adresse";
    public static final String TELEPHONESALON="telephone";
    public static final String TYPESALON="typeSalon";
    public static final String STREET_ID="street_idStreet";
    public static final String PROPRIETAIRE_ID="proprietaire_idProprietaire";

    // Data: Columns of the Street table
    public static final String TABLENAMESTREET="Street";
    public static final String IDSTREET="idStreet";
    public static final String REGION="region";
    public static final String DEPARTEMENT="departement";
    public static final String STREETNAME="streetName";

    //Data: Columns of the User table
    public static final String TABLENAMEUSER="User";
    public static final String IDUSER="idUser";
    public static final String PRENOMUSER="prenom";
    public static final String NOMUSER="nom";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";

    private static final int DATABASEVERSION=1;
    private static final String DATABASENAME="SenSalon.db";

    // Create table modele
    private static final String CREATETABLEMODELE="CREATE TABLE "+ TABLENAMEMODEL + " ("+
            IDMODELE + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            MODELNAME + " TEXT NOT NULL, "+
            MODELDURATION + " TEXT NOT NULL, "+
            MODELPRICE + " INTEGER NOT NULL, "+
            IMAGE + " INTEGER NOT NULL, "+
            SALON_ID + " INTEGER NOT NULL "+
            ")";

    // Create table  proprietaire
    private static final String CREATETABLEPROPRIETAIRE="CREATE TABLE "+ TABLENAMEPROPRIETAIRE + " ("+
            IDPROPRIETAIRE + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            PRENOMPROPRIETAIRE + " TEXT NOT NULL, "+
            NOMPROPRIETAIRE + " TEXT NOT NULL, "+
            ADRESSEPROPRIETAIRE + " TEXT NOT NULL, "+
            TELEPHONEPROPRIETAIRE + " TEXT NOT NULL "+
            ")";

    // Create table  street
    private static final String CREATETABLESTREET="CREATE TABLE "+ TABLENAMESTREET + " ("+
            IDSTREET + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            REGION + " TEXT NOT NULL, "+
            DEPARTEMENT + " TEXT NOT NULL, "+
            STREETNAME + " TEXT NOT NULL "+
            ")";

    // Create table salon
    private static final String CREATETABLESALON="CREATE TABLE " + TABLENAMESALON + " ("+
            IDSALON + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            NOMSALON + " TEXT NOT NULL, "+
            LONGITUDE + " TEXT NOT NULL, "+
            LATITUDE + " TEXT NOT NULL, "+
            ADRESSESALON + " TEXT NOT NULL, "+
            TELEPHONESALON + " TEXT NOT NULL, "+
            TYPESALON + " TEXT NOT NULL, "+
            STREET_ID + " INTEGER NOT NULL REFERENCES "+ TABLENAMESTREET +" ("+ IDSTREET + ")," +
            PROPRIETAIRE_ID + " INTEGER NOT NULL REFERENCES "+ TABLENAMEPROPRIETAIRE +" ("+ IDPROPRIETAIRE + ")"+
            ")";

    // Create table user
    private static final String CREATETABLEUSER="CREATE TABLE "+ TABLENAMEUSER + " ("+
            IDUSER + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            PRENOMUSER + " TEXT NOT NULL, "+
            NOMUSER + " TEXT NOT NULL, "+
            USERNAME + " TEXT NOT NULL, "+
            PASSWORD + " TEXT NOT NULL "+
            ")";

    public SenSalonDatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
        context.deleteDatabase(DATABASENAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMEMODEL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMEPROPRIETAIRE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMESALON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMESTREET );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMEUSER);
        sqLiteDatabase.execSQL(CREATETABLEUSER);
        sqLiteDatabase.execSQL(CREATETABLEPROPRIETAIRE);
        sqLiteDatabase.execSQL(CREATETABLESTREET);
        sqLiteDatabase.execSQL(CREATETABLEMODELE);
        sqLiteDatabase.execSQL(CREATETABLESALON);
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(SenSalonDatabaseHelper.class.getName(),"Upgrading database from version "+ oldVersion + " to " + newVersion
                + "which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMEMODEL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMEPROPRIETAIRE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMESALON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMESTREET );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAMEUSER);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
