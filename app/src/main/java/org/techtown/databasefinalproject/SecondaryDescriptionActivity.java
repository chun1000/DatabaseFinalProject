package org.techtown.databasefinalproject;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondaryDescriptionActivity extends AppCompatActivity {

    CreatureFragment  creatureFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_description);

        creatureFragment = new CreatureFragment();


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_secondary_frameLayout_1, creatureFragment ).commit();
    }
}
