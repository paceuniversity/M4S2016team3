package com.m4s2016.sensalon;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.m4s2016.sensalon.dao.ModeleDao;
import com.m4s2016.sensalon.dao.ProprietaireDao;
import com.m4s2016.sensalon.dao.SalonDao;
import com.m4s2016.sensalon.dao.StreetDao;
import com.m4s2016.sensalon.dao.UserDao;
import com.m4s2016.sensalon.model.Modele;
import com.m4s2016.sensalon.model.Proprietaire;
import com.m4s2016.sensalon.model.Salon;
import com.m4s2016.sensalon.model.Street;
import com.m4s2016.sensalon.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private View rootView;
    private Button btn1, btn2, btn3, btn4;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_main, container, false);

        btn1= (Button) rootView.findViewById(R.id.menuitem1);
        btn2= (Button) rootView.findViewById(R.id.menuitem2);
        btn3= (Button) rootView.findViewById(R.id.menuitem3);
        btn4= (Button) rootView.findViewById(R.id.menuitem4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"You click on Activity Maps", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"You click on Activity List Salon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SalonActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"You click on Activity List Model", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ModeleActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"You click on Activity Faqs", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), FaqsActivity.class);
                startActivity(intent);
            }
        });

        return  rootView;
    }

}
