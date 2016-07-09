package com.guette.javaee7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.guette.javaee.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class ListerOffreActivity extends ListActivity {
	
	private ProgressDialog dialog;
	
	JSONParser jParser = new JSONParser();
	
	ArrayList<HashMap<String, String>> offreList;
	
	private static String url="http://10.0.2.2/Android/listeroffre.php";

	private static final String TAG_SUCCESS = "success";
    private static final String TAG_OFFRE = "offre";
    private static final String TAG_IDOFFRE = "IDOFFRE";
    private static final String TAG_TITRE = "Titre";
    private static final String TAG_DATE = "DATECLOTURE";
    
    JSONArray offre= null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_listeroffre);
    	
    	// Hashmap for ListView
        offreList = new ArrayList<HashMap<String, String>>();
 
        //appel de la classe phpDownloader()
        new PhpDownloader().execute();
 
        // Get listview
        ListView lv = getListView();
        
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String idoffre=((TextView) view.findViewById(R.id.idoffre)).getText().toString();
						
				//Start intent
				Intent intent = new Intent(ListerOffreActivity.this,VisualiserOffreActivity.class);
				intent.putExtra(TAG_IDOFFRE,idoffre);
				startActivityForResult(intent, 1000);	
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	if(resultCode==1000){
    		Log.i("",""+resultCode);
    		Intent intent=getIntent();
    		finish();
    		startActivity(intent);
    	}
    }

/*************************************************************
* 
**************************************************************/
    
     protected class PhpDownloader extends AsyncTask<String, String, String>{

    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		dialog = new ProgressDialog(ListerOffreActivity.this);
            dialog.setMessage("Loading Liste Offre. Please wait...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
    	}
    	
		@Override
		protected String doInBackground(String... args) {
			 // Building Parameters
            List<NameValuePair> form = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url, "GET", form);
 
            // Check your log cat for JSON reponse
            Log.d("Liste_offre: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                   offre = json.getJSONArray(TAG_OFFRE);
                    
                    for (int i = 0; i < offre.length(); i++) {
                        JSONObject c = offre.getJSONObject(i);
  
                        String id = c.getString(TAG_IDOFFRE);
                        String titre = c.getString(TAG_TITRE);
                        String date = c.getString(TAG_DATE);
                    
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_IDOFFRE, id);
                        map.put(TAG_TITRE,titre);
                        map.put(TAG_DATE, date);
 
                        // adding HashList to ArrayList
                        offreList.add(map);
                    }
                } else {
                    // pas de liste trouve
                    // Launch Add New liste Activity
                    Intent intent = new Intent(ListerOffreActivity.this,PresentationActivity.class);
                    // Closing all previous activities
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
		
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            dialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(ListerOffreActivity.this, offreList,
                            R.layout.activity_offredetails, new String[] {TAG_IDOFFRE, TAG_TITRE,TAG_DATE},
                            new int[] { R.id.idoffre, R.id.titre,R.id.date });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
        }
    }
}
