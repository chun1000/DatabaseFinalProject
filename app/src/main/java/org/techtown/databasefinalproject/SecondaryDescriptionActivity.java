package org.techtown.databasefinalproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Button btnFirst;
    Button btnSecond;
    int layoutMode = -1; //0: 생물 //1:휴양지


    private void initializeLayoutCommon() {
        btnFirst = findViewById(R.id.activity_secondary_description_button_1);
        btnThird = findViewById(R.id.activity_secondary_description_button_3);
        btnSecond = findViewById(R.id.activity_secondary_description_button_2);

        selectEffectOnBtn(btnFirst);
        nonSelectEffectOnBtn(btnSecond);
        nonSelectEffectOnBtn(btnThird);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_description);
        initializeLayoutCommon();


        Intent intent = getIntent();
        Object o = intent.getSerializableExtra("object");

        if(o instanceof Plant || o instanceof  Animal) {
            initializeLayoutCreature(o);
            layoutMode = 0;
        } else if(o instanceof Vacation) {
            initializeLayoutVacation(o);
            layoutMode = 1;
        } else {
            Log.d("app", "Error occur");
        }



        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEffectOnBtn(btnFirst);
                nonSelectEffectOnBtn(btnSecond);
                nonSelectEffectOnBtn(btnThird);

                if(layoutMode == 0) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, creatureFragment ).commit();
                } else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, vacationFragment ).commit();
                }
            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEffectOnBtn(btnSecond);
                nonSelectEffectOnBtn(btnFirst);
                nonSelectEffectOnBtn(btnThird);

                if(layoutMode == 0) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, vacationListFragment ).commit();
                } else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, creatureListFragment ).commit();
                }

            }
        });

        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEffectOnBtn(btnThird);
                nonSelectEffectOnBtn(btnSecond);
                nonSelectEffectOnBtn(btnFirst);

                if(layoutMode == 0) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, toolListFragment ).commit();
                } else {

                }

            }
        });
    }



    private void initializeLayoutCreature(Object o) {
        creatureFragment = new CreatureFragment();
        toolListFragment = new ToolListFragment();
        vacationListFragment = new VacationListFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, creatureFragment ).commit();

        Bundle bundle = new Bundle();

        if(o instanceof  Plant) {
            bundle.putSerializable("object", (Plant)o);
        } else {
            bundle.putSerializable("object", (Animal)o);
        }

        creatureFragment.setArguments(bundle);
        vacationListFragment.setArguments(bundle);
        toolListFragment.setArguments(bundle);

    }

    private void initializeLayoutVacation(Object o) {
        vacationFragment = new VacationFragment();
        toolListFragment = new ToolListFragment();
        creatureListFragment = new CreatureListFragment();

        btnThird.setVisibility(View.INVISIBLE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, vacationFragment ).commit();
    }


    private void nonSelectEffectOnBtn(Button btn) {
        btn.setBackgroundResource(R.drawable.non_select_button);
        btn.setTextColor(Color.parseColor("#000000"));
    }

    private void selectEffectOnBtn(Button btn) {
        btn.setBackgroundResource(R.drawable.select_button);
        btn.setTextColor(Color.parseColor("#FFFFFF"));
    }

}
