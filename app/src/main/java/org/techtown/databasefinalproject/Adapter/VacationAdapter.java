package org.techtown.databasefinalproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.techtown.databasefinalproject.Model.Plant;
import org.techtown.databasefinalproject.Model.Tool;
import org.techtown.databasefinalproject.Model.Vacation;
import org.techtown.databasefinalproject.R;
import org.techtown.databasefinalproject.SecondaryDescriptionActivity;

import java.util.ArrayList;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.ViewHolder> {
    private ArrayList<Vacation> data = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        //레이아웃들 선언부.
        ImageView imageView;
        TextView name;
        TextView location;

        ViewHolder(View item) {
            super(item);


            imageView = item.findViewById(R.id.item_imageView_image);
            name = item.findViewById(R.id.item_textView_name);
            location = item.findViewById(R.id.item_textView_location);


            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Vacation vacation = data.get(pos);
                        //아이템을 얻는 부분.

                        Intent intent = new Intent(v.getContext(), SecondaryDescriptionActivity.class);
                        intent.putExtra("object", vacation);
                        v.getContext().startActivity(intent);


                    }

                }
            });
        }
    }

    public VacationAdapter(ArrayList<Vacation> list) {
        data = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item, viewGroup, false);


        VacationAdapter.ViewHolder vh =  new VacationAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(data.get(i).getSpotName());
        viewHolder.location.setText(data.get(i).getPhoneNumber());
        viewHolder.imageView.setImageResource(R.drawable.nature);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
