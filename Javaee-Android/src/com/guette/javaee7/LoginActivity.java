package com.guette.javaee7;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.guette.javaee.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class LoginActivity extends Activity {
	
	private static String url="http://10.0.2.2/uadb/conn.php";
	
	private EditText editLogin,editPassword;
	private TextView txtForget, txtinscription;
	private Button btnQuitter ,btnConnexion;
	private String  login,  password; 
	public ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		editLogin = (EditText) findViewById(R.id.editlogin);
        editPassword  = (EditText) findViewById(R.id.editpassword);
        dialog=new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Veuillez Patienter SVP !");
        btnQuitter = (Button)findViewById(R.id.btnquitter);
        
        btnQuitter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {	
				finish() ;	
			}
	});
        
        txtForget=(TextView)findViewById(R.id.txtforget);
        txtForget.setOnClickListener(new OnClickListener() {
 		@Override
 		public void onClick(View v) {
 			Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
 			startActivity(intent);
 		}
 	});
        
        txtinscription=(TextView)findViewById(R.id.txtinscription);
        txtinscription.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			Intent intent = new Intent(LoginActivity.this,InscriptionActivity.class);
    			startActivity(intent);
    		}
    	});
        
       btnConnexion = (Button)findViewById(R.id.btnconnecter);
       btnConnexion.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {	
				login= editLogin.getText().toString();
				password= editPassword.getText().toString();
				if (login.equals("")|| password.equals("")){
					Toast.makeText(LoginActivity.this, "Veuillez remplir les champs ", Toast.LENGTH_LONG).show();
				}
				else {	
					
					PhpDownloder downloader = new PhpDownloder();
					downloader.execute(url);
			Intent intent = new Intent(LoginActivity.this,PresentationActivity.class);
			startActivity(intent);
				
	   
				}
			}
		}) ;
       
       
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
	
	
/*****************************************************************************
 * 
 * 	
 *****************************************************************************/
protected class PhpDownloder extends AsyncTask<String, Void, String>{
		
		@Override
	    protected void onPreExecute() {
			super.onPreExecute();
    		dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Connexion. Please wait...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(true);
            dialog.show();
	    	}

		@Override
		protected String doInBackground(String... url) {
			try{
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(url[0]);
				List<NameValuePair> form= new ArrayList<NameValuePair>();
				form.add(new BasicNameValuePair("Login",login));
				form.add(new BasicNameValuePair("Password",password));
				post.setEntity(new UrlEncodedFormEntity(form, HTTP.UTF_8));
				ResponseHandler <String> tunel = new BasicResponseHandler();
				Toast.makeText(getApplicationContext(), "Connecte avec"+ login+","+ password,Toast.LENGTH_LONG).show();
				String result = client.execute(post, tunel);
				return result;
			} 
			catch (Exception e){}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			if (result.startsWith("OK")){
				Intent intent = new Intent(LoginActivity.this,PresentationActivity.class);
				startActivity(intent);
			}
			else {
				Toast.makeText(LoginActivity.this, "Paramétre inconnue",Toast.LENGTH_LONG).show();
			}
		}
    
	}
	
}
