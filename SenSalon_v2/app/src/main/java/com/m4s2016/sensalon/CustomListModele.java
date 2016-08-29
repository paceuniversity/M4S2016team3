package com.m4s2016.sensalon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MAGUETTE on 23/08/2016.
 */
public class CustomListModele extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] modele;
    private final Integer[] imageId;

    public CustomListModele(Activity context, String[] modele, Integer[] imageId) {
        super(context, R.layout.list_single_modele, modele);
        this.context=context;
        this.modele=modele;
        this.imageId=imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single_modele, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.single_textmodele);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.single_imagemodele);
        txtTitle.setText(modele[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}