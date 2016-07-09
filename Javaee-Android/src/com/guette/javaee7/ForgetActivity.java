package com.guette.javaee7;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.guette.javaee.R;

@SuppressWarnings("deprecation")
public class ForgetActivity extends Activity {
	public TextView tvPrenom, tvNom, tvEmail, tvLogin, tvPassword;
	private Button valider,annuler;
	private EditText editId;
	public ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget);
		
		progress = new ProgressDialog(ForgetActivity.this);
		progress.setMessage("Veuillez patienter SVP !"); 

		tvPrenom=(TextView)findViewById(R.id.prenom);
		tvNom=(TextView)findViewById(R.id.nom);
		tvEmail=(TextView)findViewById(R.id.email);
		tvLogin=(TextView)findViewById(R.id.login);
		tvPassword=(TextView)findViewById(R.id.password);
		editId=(EditText)findViewById(R.id.editid);
		
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
        	 String id = editId.getText().toString().trim();
        	 Log.v("idddd", id);
        		String url = "http://10.0.2.2:8080/projetjavaee/rest/utilisateur/"+id;
        		new UadbDownloader().execute(url);	
        	}
        });
      
	}
        
/*******************************************************************************
 *
 *
 ********************************************************************************/
        protected class UadbDownloader extends AsyncTask<String, Void, String>{

        	@Override
        	protected void onPreExecute() {
        		progress.show();
        	}
        	@Override
        	protected String doInBackground(String... url) {
        		
        		try{
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url[0] );
        ResponseHandler<String> tunnel = new BasicResponseHandler();
        String result = client.execute(get,tunnel);
        return result;
        		}catch  (Exception e){
        			e.printStackTrace();
        		}
        		return null;
        	}
        	
        	@Override
        	protected void onPostExecute(String result) {
        		progress.dismiss();
        		try {
        			parseXml(result);
        		} catch (Exception e) {
        		}
        	}
        }
        
        protected void parseXml(String xmlString) throws XmlPullParserException, IOException{

        	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        	factory.setNamespaceAware(true);
        	XmlPullParser xpp = factory.newPullParser();
        	xpp.setInput(new StringReader(xmlString));

        	String texte=null;

        	while(xpp.getEventType()!=XmlPullParser.END_DOCUMENT){
        	if(xpp.getEventType()==XmlPullParser.START_TAG){
        	if(xpp.getName().equals("utilisateur")){
        	 
        	}
        	}
        	else if(xpp.getEventType()==XmlPullParser.END_TAG){
        	if(xpp.getName().equals("prenom")){
        	 tvPrenom.setText(texte);
        	}
        	else if(xpp.getName().equals("nom")){
        		 tvNom.setText(texte);
        		}
        	else if(xpp.getName().equals("email")){
        		 tvEmail.setText(texte);
        		}
        	else if(xpp.getName().equals("login")){
        		 tvLogin.setText(texte);
        		}
        	else if(xpp.getName().equals("password")){
       		 tvPassword.setText(texte);
       		}
        	texte=null;
        	}
        	else if(xpp.getEventType()==XmlPullParser.TEXT){
        	texte = xpp.getText();
        	}
        	xpp.next();
        	}
        }
}
