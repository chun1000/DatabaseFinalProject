package org.techtown.databasefinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private void executeQuery() {
        ArrayList<Vacation> vacations = new ArrayList<>();
        vacations.add(new Vacation());
        vacations.get(0).setSpotName("어디로든 휴양지");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        VacationAdapter adapter = new VacationAdapter(vacations);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_search);
        initializeLayout();
        executeQuery();
    }
}
