package com.guette.javaee7;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.guette.javaee.R;


@SuppressWarnings("deprecation")
public class InscriptionActivity extends Activity {
	
	public EditText editPrenom, editNom, editEmail, editLogin, editPassword;
	private Button valider,annuler;
	private String prenom,nom,email,login, password;
	private ProgressDialog dialog;
	
	JSONParser jsonParser = new JSONParser();
	
	private static String url="http://10.0.2.2/uadb/inscription.php";
	
	private static final String TAG_SUCCESS="succes";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		dialog = new ProgressDialog(InscriptionActivity.this);
		dialog.setMessage("Veuillez patienter SVP !"); 

		//Recuperer les saisies
		editPrenom=(EditText)findViewById(R.id.prenom);
		editNom=(EditText)findViewById(R.id.nom);
		editEmail=(EditText)findViewById(R.id.email);
		editLogin=(EditText)findViewById(R.id.login);
		editPassword=(EditText)findViewById(R.id.password);
		
		 annuler = (Button)findViewById(R.id.btnannuler);   
		 annuler.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {	
					finish() ;	
				}
		});
		
        valider= (Button)findViewById(R.id.btnvalider);
        valider.setOnClickListener (new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
        		//create user
        		PhpDownloder downloader= new PhpDownloder();
        		downloader.execute();  
				}
		}) ;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
        
/*******************************************************************************
 *
 *
 ********************************************************************************/
    protected class PhpDownloder extends AsyncTask<String, String, String>{
    	
    	@Override
    protected void onPreExecute() {
	        super.onPreExecute();
            dialog = new ProgressDialog(InscriptionActivity.this);
            dialog.setMessage("Creating User...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(true);
    	    dialog.show();
    	}
    	
    @Override
    protected String doInBackground(String... args) {
			prenom=editPrenom.getText().toString();
        	nom=editNom.getText().toString();
        	email=editEmail.getText().toString();
        	login= editLogin.getText().toString();
			password= editPassword.getText().toString();
	
			List<NameValuePair> form= new ArrayList<NameValuePair>();
			form.add(new BasicNameValuePair("Prenom",prenom));
			form.add(new BasicNameValuePair("Nom",nom));
			form.add(new BasicNameValuePair("Email",email));
			form.add(new BasicNameValuePair("Login",login));
			form.add(new BasicNameValuePair("Password",password));
			
			// getting JSON Object
            // Note that create user url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url, "POST", form);
 
            // check log cat from response
            Log.d("Create Response", json.toString());
 
            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // successfully created user
                    Intent intent = new Intent(InscriptionActivity.this, LoginActivity.class);
                    startActivity(intent);
 
                    // closing this screen
                    finish();
                } else {
                    // failed to create user
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        protected void onPostExecute(String file_url) {
            dialog.dismiss();
        }
 
    }
}
