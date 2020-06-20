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

import org.techtown.databasefinalproject.Adapter.ToolAdapter;
import org.techtown.databasefinalproject.Model.Animal;
import org.techtown.databasefinalproject.Model.Plant;
import org.techtown.databasefinalproject.Model.Tool;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToolListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToolListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToolListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToolListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToolListFragment newInstance(String param1, String param2) {
        ToolListFragment fragment = new ToolListFragment();
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
        return inflater.inflate(R.layout.fragment_tool_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.fragment_tool_list_recyclerView_main);

        Bundle bundle = getArguments();
        Object o = bundle.getSerializable("object");

        Plant plant; Animal animal;
        if(o instanceof Plant) {
            plant = (Plant) o;
            executeQueryByPlant(plant, view.getContext());
        }
        else {
            animal = (Animal) o;
            executeQueryByAnimal(animal, view.getContext());
        }

    }

    private void executeQueryByPlant(Plant plant, Context context) {
        ArrayList<Tool> tools = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SqlManager sqlManager = new SqlManager();
        sqlManager.createDatabase(context);

        tools.addAll(sqlManager.executeQueryForTool("SELECT * FROM Tool WHERE tool_name IN (SELECT tool_name FROM CollectBy WHERE species IN (SELECT species FROM Plant WHERE plant_name =\""+plant.getName()+"\"))"));

        ToolAdapter adapter = new ToolAdapter(tools);
        recyclerView.setAdapter(adapter);
    }

    private void executeQueryByAnimal(Animal animal, Context context) {
        ArrayList<Tool> tools = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SqlManager sqlManager = new SqlManager();
        sqlManager.createDatabase(context);

        tools.addAll(sqlManager.executeQueryForTool("SELECT * FROM Tool WHERE tool_name IN (SELECT tool_name FROM CatchBy WHERE species IN (SELECT species FROM Animal WHERE animal_name = \""+animal.getName()+"\"))"));

        ToolAdapter adapter = new ToolAdapter(tools);
        recyclerView.setAdapter(adapter);
    }


}
