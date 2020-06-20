package org.techtown.databasefinalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import org.techtown.databasefinalproject.Model.Query;

public class MainActivity extends AppCompatActivity {

    ImageButton creature;
    ImageButton vacation;


    private void initializeLayout() {
        creature = findViewById(R.id.activity_main_button_creature);
        vacation = findViewById(R.id.activity_main_button_vacation);
    }

    private void initializeQuery() {
        Query.tableCreateQuery = getString(R.string.table_create_query);
    }

    private void initializeDatabaseTable() {

    }

    private void initializeDatabaseSet() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeLayout();
        initializeQuery();

        SqlManager sqlManager = new SqlManager();
        sqlManager.createDatabase(getApplicationContext());
        sqlManager.createTable();

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
