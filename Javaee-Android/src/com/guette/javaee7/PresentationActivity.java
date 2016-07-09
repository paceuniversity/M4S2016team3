package com.guette.javaee7;

import com.guette.javaee.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class PresentationActivity extends Activity {
	ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_presentation);

		image=(ImageView)findViewById(R.id.image1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.presentation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		 if (item.getItemId()== R.id.listerOffre)
		{
			Intent intent= new Intent(PresentationActivity.this, ListerOffreActivity.class );
			startActivity(intent);	
		}
		else if (item.getItemId()== R.id.chercherOffre)
		{
			Intent intent= new Intent(PresentationActivity.this, ChercherOffreActivity.class );
			startActivity(intent);	
		}	 
		else if (item.getItemId()== R.id.creerCV)
		{
			Intent intent= new Intent(PresentationActivity.this, CreerCVActivity.class );
			startActivity(intent);	
		}
//		else if (item.getItemId()== R.id.editerCV)
//		{
//			Intent intent= new Intent(PresentationActivity.this, EditerCVActivity.class );
//			startActivity(intent);	
//		}
//		else if (item.getItemId()== R.id.menuGeoMap)
//		{
//			Intent intent= new Intent(PresentationActivity.this, MapGeoActivity.class );
//			startActivity(intent);	
//		}
//		
//		else if (item.getItemId()== R.id.menuAbout){
//			
//		}
		return super.onOptionsItemSelected(item);
	}

}
