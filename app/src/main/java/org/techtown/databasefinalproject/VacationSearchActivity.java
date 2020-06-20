package org.techtown.databasefinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import org.techtown.databasefinalproject.Adapter.VacationAdapter;
import org.techtown.databasefinalproject.Model.Vacation;

import java.util.ArrayList;

public class VacationSearchActivity extends AppCompatActivity {

    private ImageButton btnSearch;
    private EditText txtSearch;
    private RecyclerView recyclerView;

    private void initializeLayout() {
        btnSearch = findViewById(R.id.activity_vacation_search_imageButton_search);
        txtSearch = findViewById(R.id.activity_vacation_search_editText_search);
        recyclerView = findViewById(R.id.activity_vacation_search_recyclerView_main);

    }

    private void executeQuery(String name) {
        ArrayList<Vacation> vacations = new ArrayList<>();

        SqlManager sqlManager = new SqlManager();
        sqlManager.createDatabase(getApplicationContext());

        vacations.addAll(sqlManager.executeQueryForVacation("select * from Vacation WHERE spot_name like \"%" + name +"%\""));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        VacationAdapter adapter = new VacationAdapter(vacations);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_search);
        initializeLayout();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeQuery(txtSearch.getText().toString());
            }
        });

    }
}
