package com.guette.javaee7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.guette.javaee.R;

@SuppressWarnings("deprecation")
public class VisualiserOffreActivity extends Activity {
	
	public TextView tvId,tvTitre, tvCategorie, tvContat, tvDescription, tvAvantages, tvCompetence,tvCandidater, tvDatedebut, tvDatecloture;
	private Button btnpostuler;
	public ProgressDialog dialog;
	String idoffre;
	
	ArrayList<HashMap<String, String>> offreList;
	
	JSONParser jParser = new JSONParser();
	JSONArray offre= null;
	
	private static final String url="http://10.0.2.2/Android/detailsoffre.php";
	
	 // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_OFFRE = "offre";
    private static final String TAG_IDOFFRE = "IDOFFRE";
    private static final String TAG_TITRE = "Titre";
    private static final String TAG_CATEGORIE = "Categorie";
    private static final String TAG_CONTRAT = "Contrat";
    private static final String TAG_DESCRIPTION = "Description";
    private static final String TAG_AVANTAGE = "Avantages";
    private static final String TAG_COMPETENCE = "Competences";
    private static final String TAG_EMAIL = "EmailPourCandidater";
    private static final String TAG_DATEDEBUT = "DATEDEBUT";
    private static final String TAG_DATECLOTURE = "DATECLOTURE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualiseroffre);
		
		// Hashmap for ListView
        offreList = new ArrayList<HashMap<String, String>>();
		
		Intent intent=getIntent();
		
		idoffre=intent.getStringExtra(TAG_IDOFFRE);
		
		//Toast.makeText(getApplicationContext(), "id bien recuperer"+idoffre, Toast.LENGTH_LONG).show();
		
		PhpDownloader downloader = new PhpDownloader();
		downloader.execute();
	
		btnpostuler=(Button)findViewById(R.id.btnPostuler);
		btnpostuler.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(VisualiserOffreActivity.this,ReponseOffreActivity.class);
				startActivity(intent);
			}
		});
	}
		
/*******************************************************
 * 
 ******************************************************/
	protected class PhpDownloader extends AsyncTask<String, String, String>{
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(VisualiserOffreActivity.this);
	            dialog.setMessage("Edition detail offre ...");
	            dialog.setIndeterminate(false);
	            dialog.setCancelable(true);
	    	    dialog.show();
			}

	@Override
	protected String doInBackground(String... params) {

				int success;
				try{
					List<NameValuePair> form= new ArrayList<NameValuePair>();
					form.add(new BasicNameValuePair("IDOFFRE",idoffre));
					
					JSONObject json=jParser.makeHttpRequest(url,  "GET", form);
					
					success=json.getInt(TAG_SUCCESS);
					if(success==1){
						JSONArray array=json.getJSONArray(TAG_OFFRE);
						
						JSONObject offre=array.getJSONObject(1);
						
						Log.d("Un produit detaille",offre.toString());
						
						//tvId=(TextView)findViewById(R.id.editid);
						tvTitre=(TextView)findViewById(R.id.titre);
						tvCategorie=(TextView)findViewById(R.id.categorie);
						tvContat=(TextView)findViewById(R.id.contrat);
						tvDescription=(TextView)findViewById(R.id.description);
						tvAvantages=(TextView)findViewById(R.id.avantage);
						tvCompetence=(TextView)findViewById(R.id.competences);
						tvCandidater=(TextView)findViewById(R.id.email);
						tvDatedebut=(TextView)findViewById(R.id.datedebut);
						tvDatecloture=(TextView)findViewById(R.id.datecloture);
						
						//tvId.setText(idoffre);
						tvTitre.setText(offre.getString(TAG_TITRE));
						tvCategorie.setText(offre.getString(TAG_CATEGORIE));
						tvContat.setText(offre.getString(TAG_CONTRAT));
						tvDescription.setText(offre.getString(TAG_DESCRIPTION));
						tvAvantages.setText(offre.getString(TAG_AVANTAGE));
						tvCompetence.setText(offre.getString(TAG_COMPETENCE));
						tvCandidater.setText(offre.getString(TAG_EMAIL));
						tvDatedebut.setText(offre.getString(TAG_DATEDEBUT));
						tvDatecloture.setText(offre.getString(TAG_DATECLOTURE));	
						}
					else{
						String error = "Not found!";
                        Toast.makeText(VisualiserOffreActivity.this, error, Toast.LENGTH_LONG).show();
					}
				}catch(JSONException e){
					e.printStackTrace(); 
				}

		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		dialog.dismiss();
	}
	}
}
