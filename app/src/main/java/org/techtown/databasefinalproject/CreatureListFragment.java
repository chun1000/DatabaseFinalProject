package org.techtown.databasefinalproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.techtown.databasefinalproject.Adapter.AnimalAdapter;
import org.techtown.databasefinalproject.Adapter.PlantAdapter;
import org.techtown.databasefinalproject.Adapter.ToolAdapter;
import org.techtown.databasefinalproject.Adapter.VacationAdapter;
import org.techtown.databasefinalproject.Model.Animal;
import org.techtown.databasefinalproject.Model.Plant;
import org.techtown.databasefinalproject.Model.Tool;
import org.techtown.databasefinalproject.Model.Vacation;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatureListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatureListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean isPlant;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    public CreatureListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatureListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatureListFragment newInstance(String param1, String param2) {
        CreatureListFragment fragment = new CreatureListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creature_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.fragment_creature_list_recyclerView_main);

        Bundle bundle = getArguments();
        Object o = bundle.getSerializable("object");
        Vacation v = (Vacation) o;
        executeQueryByVacation(v, view.getContext());

    }

    private void executeQueryByVacation(Vacation v, Context context) {

            ArrayList<Animal> animals = new ArrayList<>();

            SqlManager sqlManager = new SqlManager();
            sqlManager.createDatabase(context);


            animals.addAll(sqlManager.executeQueryForAnimal("SELECT * FROM Animal WHERE animal_name IN (SELECT animal_name FROM LiveIn WHERE location_id IN (SELECT location_id FROM Location WHERE province = \""+v.getProvince()+"\" AND town = \""+v.getTown()+"\" AND city = \""+v.getCity()+"\"))"));
            //SELECT * FROM Animal WHERE animal_name IN (SELECT animal_name FROM LiveIn WHERE location_id IN (SELECT location_id FROM Location WHERE province = "강원도" AND town = "강릉시" AND city = "강문동"))
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            AnimalAdapter adapter = new AnimalAdapter(animals);
            recyclerView.setAdapter(adapter);

       /* ArrayList<Plant>
        vacations.add(new Vacation());
        vacations.get(0).setSpotName("이러이런 휴양지");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VacationAdapter adapter = new VacationAdapter(vacations);
        recyclerView.setAdapter(adapter);*/
    }
}
