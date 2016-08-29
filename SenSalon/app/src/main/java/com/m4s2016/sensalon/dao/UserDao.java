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
import com.m4s2016.sensalon.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAGUETTE on 09/08/2016.
 */
public class UserDao {

    static SQLiteDatabase sqLiteDatabase;
    static SenSalonDatabaseHelper senSalonDatabaseHelper;

    String[] all_users= {SenSalonDatabaseHelper.IDUSER,SenSalonDatabaseHelper.PRENOMUSER,SenSalonDatabaseHelper.NOMUSER,SenSalonDatabaseHelper.USERNAME,SenSalonDatabaseHelper.PASSWORD};

    private Context context;
    private User user;

    public UserDao(Context context) {
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

    public User createUser(User user){
        ContentValues values= new ContentValues();
        values.put(SenSalonDatabaseHelper.IDUSER,user.getIdUser());
        values.put(SenSalonDatabaseHelper.PRENOMUSER,user.getPrenom());
        values.put(SenSalonDatabaseHelper.NOMUSER,user.getNom());
        values.put(SenSalonDatabaseHelper.USERNAME,user.getUsername());
        values.put(SenSalonDatabaseHelper.PASSWORD,user.getPassword());
        Long idInsert=
                 sqLiteDatabase.insert(SenSalonDatabaseHelper.TABLENAMEUSER, null, values);
        Cursor cursor=sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEUSER, all_users, SenSalonDatabaseHelper.IDSTREET + " = "+
                idInsert,null,null,null,null);
        cursor.moveToFirst();
        User user1= cursorToUser(cursor);
        cursor.close();
        return user1;
    }

    public List<User> getAllUsers() {
        List<User> listUsers= new ArrayList<User>();
        Cursor cursor= sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEUSER,all_users,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                User user=cursorToUser(cursor);
                listUsers.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listUsers;
    }

    public User getUserById(Long idUser){
        Log.d("getUserById ", ""+idUser);
        Cursor cursor=sqLiteDatabase.query(SenSalonDatabaseHelper.TABLENAMEUSER, all_users, SenSalonDatabaseHelper.IDUSER +" = ?",
                new String[] { String.valueOf(idUser) },null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        User user = cursorToUser(cursor);
        return user;
    }

    public User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setIdUser(cursor.getInt(0));
        user.setPrenom(cursor.getString(1));
        user.setNom(cursor.getString(2));
        user.setUsername(cursor.getString(3));
        user.setPassword(cursor.getString(4));
         return user;
    }
}
