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

import org.techtown.databasefinalproject.Adapter.PlantAdapter;
import org.techtown.databasefinalproject.Model.Plant;
import org.techtown.databasefinalproject.Model.Vacation;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatureListFragmentPlant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatureListFragmentPlant extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreatureListFragmentPlant() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatureListFragmentPlant.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatureListFragmentPlant newInstance(String param1, String param2) {
        CreatureListFragmentPlant fragment = new CreatureListFragmentPlant();
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
        return inflater.inflate(R.layout.fragment_creature_list_plant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fragment_creature_list_plant_recyclerView_main);

        Bundle bundle = getArguments();
        Object o = bundle.getSerializable("object");
        Vacation v = (Vacation) o;
        executeQueryByVacation(v, view.getContext());
    }

    private void executeQueryByVacation(Vacation v, Context context) {
        ArrayList<Plant> plants = new ArrayList<>();


        SqlManager sqlManager = new SqlManager();
        sqlManager.createDatabase(context);

        plants.addAll(sqlManager.executeQueryForPlant("SELECT * FROM PLANT WHERE plant_name IN (SELECT plant_name FROM GrowthIN WHERE location_id IN (SELECT location_id FROM Location WHERE province = \""+v.getProvince()+"\" AND town = \""+v.getTown()+"\" AND city = \""+v.getCity()+"\"))"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PlantAdapter adapter = new PlantAdapter(plants);
        recyclerView.setAdapter(adapter);
    }
}
