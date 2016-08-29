package com.m4s2016.sensalon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.m4s2016.sensalon.model.Street;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAGUETTE on 09/08/2016.
 */
public class StreetDao {

    static SQLiteDatabase sqLiteDatabase;
    static SenSalonDatabaseHelper senSalonDatabaseHelper;

    String[] all_streets = {SenSalonDatabaseHelper.IDSTREET, SenSalonDatabaseHelper.REGION, SenSalonDatabaseHelper.DEPARTEMENT,
            SenSalonDatabaseHelper.STREETNAME};

    private Context context;

    public StreetDao(Context context) {
        this.context = context;
        senSalonDatabaseHelper = new SenSalonDatabaseHelper(context);
        try {
            open();
        } catch (SQLException e) {
            Log.e("Connection ", "Openning connection " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() {
        sqLiteDatabase = senSalonDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        senSalonDatabaseHelper.close();
    }

    public Street createStreet(Street street) {
        ContentValues values = new ContentValues();
        values.put(SenSalonDatabaseHelper.IDSTREET, street.getIdStreet());
        values.put(SenSalonDatabaseHelper.REGION, street.getRegion());
        values.put(SenSalonDatabaseHelper.DEPARTEMENT, street.getDepartement());
        values.put(SenSalonDatabaseHelper.STREETNAME, street.getStreetName());

        Long idInsert = sqLiteDatabase.insert(SenSalonDatabaseHelper.TABLENAMESTREET, null, values);
        Cursor cursor = sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMESTREET, all_streets, SenSalonDatabaseHelper.IDSTREET + " = " +
                idInsert, null, null, null, null);
        cursor.moveToFirst();
        Street street1 = cursorToStreet(cursor);
        cursor.close();
        return street1;
    }

    public List<Street> getAllStreets() {
        List<Street> listStreets = new ArrayList<Street>();
        Cursor cursor = sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMESTREET, all_streets, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Street street = cursorToStreet(cursor);
                listStreets.add(street);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listStreets;
    }

    public Street getStreetById(long idStreet) {
        Log.d("getStreetById ", "" + idStreet);
        Cursor cursor = sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMESTREET, all_streets, SenSalonDatabaseHelper.IDSTREET + " = ?",
                new String[]{String.valueOf(idStreet)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Street street1 = cursorToStreet(cursor);
        return street1;
    }

    public Street cursorToStreet(Cursor cursor) {
        Street street = new Street();
        street.setIdStreet(cursor.getLong(0));
        street.setRegion(cursor.getString(1));
        street.setDepartement(cursor.getString(2));
        street.setStreetName(cursor.getString(3));
        return street;
    }

}
