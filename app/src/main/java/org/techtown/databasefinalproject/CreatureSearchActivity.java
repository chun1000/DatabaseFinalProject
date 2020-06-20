package org.techtown.databasefinalproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.techtown.databasefinalproject.Adapter.AnimalAdapter;
import org.techtown.databasefinalproject.Adapter.PlantAdapter;
import org.techtown.databasefinalproject.Model.Animal;
import org.techtown.databasefinalproject.Model.Plant;

import java.util.ArrayList;

public class CreatureSearchActivity extends AppCompatActivity {

    private Button plant;
    private Button animal;
    private ImageButton btnSearch;
    private EditText txtSearch;
    private RecyclerView recyclerView;
    private boolean isPlant = true;
    private ArrayList<Plant> plants = new ArrayList<>();
    private PlantAdapter plantAdapter;
    private ArrayList<Animal> animals = new ArrayList<>();
    private AnimalAdapter animalAdapter;


    private void initializeLayout() {
        plant = findViewById(R.id.activity_creature_search_button_plant);
        animal = findViewById(R.id.activity_creature_search_button_animal);
        btnSearch = findViewById(R.id.activity_creature_search_imageButton_search);
        txtSearch = findViewById(R.id.activity_creature_search_editText_search);
        recyclerView = findViewById(R.id.activity_creature_search_recyclerView_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        plantAdapter = new PlantAdapter(plants);
        animalAdapter = new AnimalAdapter(animals);
        executeQueryPlant();
    }

    private void executeQueryPlant() {
        plants.add(new Plant());
        plants.get(0).setName("나쁜 식물");
        plants.get(0).setSpecies("관속식물류");
        plants.add(new Plant());
        plants.get(1).setName("나쁜 식물");
        plants.get(1).setSpecies("균류");


        recyclerView.setAdapter(plantAdapter);
        plantAdapter.notifyDataSetChanged();

    }

    private void executeQueryAnimal() {
        animals.add(new Animal());
        animals.get(0).setName("나쁜 동물");
        animals.get(0).setSpecies("조류");
        recyclerView.setAdapter(animalAdapter);
        animalAdapter.notifyDataSetChanged();
    }


    private void nonSelectEffectOnBtn(Button btn) {
        btn.setBackgroundResource(R.drawable.non_select_button);
        btn.setTextColor(Color.parseColor("#000000"));
    }

    private void selectEffectOnBtn(Button btn) {
        btn.setBackgroundResource(R.drawable.select_button);
        btn.setTextColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_search);
        initializeLayout();

        plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonSelectEffectOnBtn(animal);
                selectEffectOnBtn(plant);
                isPlant = true;
            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonSelectEffectOnBtn(plant);
                selectEffectOnBtn(animal);
                isPlant = false;
            }
        });

    }
}
