package com.guette.javaee7;

import java.io.IOException;
import java.io.StringReader;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.guette.javaee.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class ChercherOffreActivity extends Activity {
	
	public TextView tvTitre, tvCategorie, tvContat, tvDescription, tvAvantages, tvCompetence,tvCandidater, tvDatedebut, tvDatecloture;
	private Button btnpostuler;
	public ProgressDialog dialog;
	String idoffre;
	
    private static final String TAG_IDOFFRE = "IDOFFRE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualiseroffre);
		
		tvTitre=(TextView)findViewById(R.id.titre);
		tvCategorie=(TextView)findViewById(R.id.categorie);
		tvContat=(TextView)findViewById(R.id.contrat);
		tvDescription=(TextView)findViewById(R.id.description);
		tvAvantages=(TextView)findViewById(R.id.avantage);
		tvCompetence=(TextView)findViewById(R.id.competences);
		tvCandidater=(TextView)findViewById(R.id.email);
		tvDatedebut=(TextView)findViewById(R.id.datedebut);
		tvDatecloture=(TextView)findViewById(R.id.datecloture);
		

		Intent intent=getIntent();
		
		idoffre=intent.getStringExtra(TAG_IDOFFRE);
		
		 //String id = idoffre.getText().toString().trim();
   	    Log.v("idddd", idoffre);
   		String url = "http://10.0.2.2:8080/projetjavaee/rest/offre/" +idoffre;
		PhpDownloader downloader = new PhpDownloader();
		downloader.execute(url);
	
		btnpostuler=(Button)findViewById(R.id.btnPostuler);
		btnpostuler.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ChercherOffreActivity.this,ReponseOffreActivity.class);
				startActivity(intent);
				
			}
		});
	}
		
/*******************************************************************
* 
* 
*******************************************************************/
	protected class PhpDownloader extends AsyncTask<String, Void, String>{
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(ChercherOffreActivity.this);
	            dialog.setMessage("Edition detail offre ...");
	            dialog.setIndeterminate(false);
	            dialog.setCancelable(true);
	    	    dialog.show();
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
		dialog.dismiss();
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
    	if(xpp.getName().equals("offre")){
    	 
    	}
    	}
    	else if(xpp.getEventType()==XmlPullParser.END_TAG){
    	if(xpp.getName().equals("titre")){
    	 tvTitre.setText(texte);
    	}
    	else if(xpp.getName().equals("categorie")){
    		 tvCategorie.setText(texte);
    		}
    	else if(xpp.getName().equals("contrat")){
    		 tvContat.setText(texte);
    		}
    	else if(xpp.getName().equals("description")){
    		 tvDescription.setText(texte);
    		}
    	else if(xpp.getName().equals("avantages")){
   		 tvAvantages.setText(texte);
   		}
    	else if(xpp.getName().equals("competence")){
      		 tvCompetence.setText(texte);
      		}
    	else if(xpp.getName().equals("candidater")){
      		 tvCandidater.setText(texte);
      		}
    	else if(xpp.getName().equals("datedebut")){
      		 tvDatedebut.setText(texte);
      		}
    	else if(xpp.getName().equals("datefin")){
      		 tvDatecloture.setText(texte);
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
