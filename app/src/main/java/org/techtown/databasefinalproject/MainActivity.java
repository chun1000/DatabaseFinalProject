package org.techtown.databasefinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton creature;
    ImageButton vacation;


    private void initializeLayout() {
        creature = findViewById(R.id.activity_main_button_creature);
        vacation = findViewById(R.id.activity_main_button_vacation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeLayout();

        creature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreatureSearchActivity.class);
                startActivity(intent);
            }
        });

        vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VacationSearchActivity.class);
                startActivity(intent);
            }
        });

    }
}
