package com.guette.javaee7;

import com.guette.javaee.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class AccueilActivity extends Activity {
	ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		
		Toast.makeText(AccueilActivity.this, "Cliquez sur l'image pour entrer", Toast.LENGTH_LONG).show();
		
		image=(ImageView)findViewById(R.id.image1);
		image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				Intent intent = new Intent(AccueilActivity.this,LoginActivity.class);
				startActivity(intent);
			}
	});
		
	
	}
}