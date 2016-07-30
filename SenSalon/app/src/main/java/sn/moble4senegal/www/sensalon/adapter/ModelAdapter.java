package sn.moble4senegal.www.sensalon.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import sn.moble4senegal.www.sensalon.R;
import sn.moble4senegal.www.sensalon.model.Modele;

/**
 * Created by lionn on 30/07/2016.
 */
public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder> {

    private Context context;
    private List<Modele> modelList;


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView modelName, modelDuration, modelPrice;
        public ImageView thumbnail, overflow;
        public MyViewHolder(View itemView) {
            super(itemView);

            modelName = (TextView)itemView.findViewById(R.id.modelName);
            modelDuration =(TextView)itemView.findViewById(R.id.modelDuration);
            modelPrice = (TextView)itemView.findViewById(R.id.modelPrice);
            thumbnail =(ImageView)itemView.findViewById(R.id.thumbnail);
            overflow = (ImageView)itemView.findViewById(R.id.overflow);
        }
    }

    public ModelAdapter(Context context, List<Modele> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public ModelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, int position) {

        Modele modele = modelList.get(position);
        holder.modelName.setText(modele.getModelName());
        holder.modelDuration.setText(modele.getModelDuration());
        holder.modelPrice.setText(modele.getModelPrice() + " FCFA");

        // loading album cover using Glide library
        Glide.with(context).load(modele.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_model, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }


    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(context, "See next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}
