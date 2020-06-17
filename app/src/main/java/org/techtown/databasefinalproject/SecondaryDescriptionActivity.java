package org.techtown.databasefinalproject;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.techtown.databasefinalproject.Model.Animal;
import org.techtown.databasefinalproject.Model.Plant;
import org.techtown.databasefinalproject.Model.Vacation;

public class SecondaryDescriptionActivity extends AppCompatActivity {

    CreatureFragment  creatureFragment;
    CreatureListFragment creatureListFragment;
    ToolListFragment toolListFragment;
    VacationFragment vacationFragment;
    VacationListFragment vacationListFragment;
    Button btnThird;
    int layoutMode = -1; //0: 생물 //1:휴양지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_description);
        initializeLayoutCommon();

        creatureFragment = new CreatureFragment();
        Intent intent = getIntent();
        Object o = intent.getSerializableExtra("object");

        if(o instanceof Plant) {
            layoutMode = 0;
        } else if(o instanceof Animal) {
            layoutMode = 0;
        }else if(o instanceof Vacation) {
            layoutMode = 1;
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, creatureFragment ).commit();
    }

    private void initializeLayoutCommon() {
        btnThird = findViewById(R.id.activity_secondary_description_button_3);

    }

    private void initializeLayoutCreature() {

    }

    private void initializeLayoutVacation() {
        btnThird.setVisibility(View.INVISIBLE);
    }

}
