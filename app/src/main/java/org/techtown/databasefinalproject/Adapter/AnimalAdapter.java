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

import org.techtown.databasefinalproject.Model.Animal;
import org.techtown.databasefinalproject.R;
import org.techtown.databasefinalproject.SecondaryDescriptionActivity;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    private ArrayList<Animal> data = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
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
                        Animal animal = data.get(pos);
                        //아이템을 얻는 부분.

                        Intent intent = new Intent(v.getContext(), SecondaryDescriptionActivity.class);
                        intent.putExtra("object", animal);
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    public AnimalAdapter(ArrayList<Animal> list) {
        data = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item, viewGroup, false);
        AnimalAdapter.ViewHolder vh = new AnimalAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(data.get(i).getName());
        viewHolder.location.setText(data.get(i).getSpecies());
        setImage(viewHolder.imageView, data.get(i).getSpecies());
    }

    private void setImage(ImageView imageView, String species) {
        switch(species){
            case "무척추동물류(곤충제외)":
                imageView.setImageResource(R.drawable.inver);
                break;
            case "곤충류":
                imageView.setImageResource(R.drawable.bug);
                break;
            case "어류":
                imageView.setImageResource(R.drawable.fish);
                break;
            case "포유류":
                imageView.setImageResource(R.drawable.mouse);
                break;
            case "조류":
                imageView.setImageResource(R.drawable.bird);
                break;
            default:
                imageView.setImageResource(R.drawable.animal);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
