package org.techtown.databasefinalproject;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class CreatureSearchActivity extends AppCompatActivity {

    private Button plant;
    private Button animal;
    private ImageButton btnSearch;
    private EditText txtSearch;
    private RecyclerView recyclerView;


    private void initializeLayout() {
        plant = findViewById(R.id.activity_creature_search_button_plant);
        animal = findViewById(R.id.activity_creature_search_button_animal);
        btnSearch = findViewById(R.id.activity_creature_search_imageButton_search);
        txtSearch = findViewById(R.id.activity_creature_search_editText_search);
        recyclerView = findViewById(R.id.activity_creature_search_recyclerView_main);
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
            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonSelectEffectOnBtn(plant);
                selectEffectOnBtn(animal);
            }
        });

    }
}
