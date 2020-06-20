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

import org.techtown.databasefinalproject.Model.Location;
import org.techtown.databasefinalproject.Model.Vacation;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VacationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VacationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView name, phone, location, description;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VacationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VacationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VacationFragment newInstance(String param1, String param2) {
        VacationFragment fragment = new VacationFragment();
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
        return inflater.inflate(R.layout.fragment_vacation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.fragment_vacation_textView_name);
        phone = view.findViewById(R.id.fragment_vacation_textView_phone);
        location = view.findViewById(R.id.fragment_vacation_textView_location);
        description = view.findViewById(R.id.fragment_vacation_textView_description);
        description.setMovementMethod(ScrollingMovementMethod.getInstance());

        Bundle bundle =getArguments();
        Object o = bundle.getSerializable("object");
        Vacation v = (Vacation)o;

        SqlManager sqlManager = new SqlManager();
        sqlManager.createDatabase(view.getContext());
        Location location2 = sqlManager.executeQueryForLocation("SELECT * FROM Location WHERE location_id IN (SELECT location_id FROM Vacation WHERE spot_name=\""+v.getSpotName()+"\")").get(0);
        v.setLocation(location2);

        name.setText(v.getSpotName());
        description.setText(v.getDescription());
        location.setText(v.getLocation());
        phone.setText(v.getPhoneNumber());


    }
}
