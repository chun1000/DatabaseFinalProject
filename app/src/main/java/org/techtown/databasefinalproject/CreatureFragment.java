package org.techtown.databasefinalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.techtown.databasefinalproject.Model.Animal;
import org.techtown.databasefinalproject.Model.Location;
import org.techtown.databasefinalproject.Model.Plant;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatureFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView name;
    private TextView species;
    private TextView description;
    private TextView location;

    public CreatureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatureFragment newInstance(String param1, String param2) {
        CreatureFragment fragment = new CreatureFragment();
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
        return inflater.inflate(R.layout.fragment_creature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.fragment_creature_textView_name);
        description = view.findViewById(R.id.fragment_creature_textView_description);
        location = view.findViewById(R.id.fragment_creature_textView_location);
        species = view.findViewById(R.id.fragment_creature_textView_species);
        description.setMovementMethod(ScrollingMovementMethod.getInstance());

        Bundle bundle = getArguments();
        Object o = bundle.getSerializable("object");

        Plant plant; Animal animal;
        if(o instanceof Plant) {
            plant = (Plant) o;
            name.setText(plant.getName());
            description.setText(plant.getDescription());

            species.setText(plant.getSpecies());
            SqlManager sqlManager = new SqlManager();
            sqlManager.createDatabase(view.getContext());
            Location location2 = sqlManager.executeQueryForLocation("SELECT * FROM Location WHERE location_id IN (SELECT location_id FROM GrowthIn WHERE plant_name = \""+plant.getName()+"\")").get(0);
            plant.setLocation(location2);
            location.setText(plant.getLocation());
        }
        else {
            animal = (Animal) o;
            name.setText(animal.getName());
            description.setText(animal.getDescription());
            species.setText(animal.getSpecies());
            SqlManager sqlManager = new SqlManager();
            sqlManager.createDatabase(view.getContext());
            Location location2 = sqlManager.executeQueryForLocation("SELECT * FROM Location WHERE location_id IN (SELECT location_id FROM LiveIn WHERE animal_name = \""+animal.getName()+"\")" ).get(0);
            animal.setLocation(location2);
            location.setText(animal.getLocation());
        }
    }
}
