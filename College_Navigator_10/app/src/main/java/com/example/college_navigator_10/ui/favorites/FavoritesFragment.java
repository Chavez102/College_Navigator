package com.example.college_navigator_10.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.example.college_navigator_10.R;
import com.example.college_navigator_10.ui.home.ResultsList_Adapter;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    ArrayList<College> colleges_List=new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        College c1=new College();
        c1.setSchoolname("Name");
        c1.setinstate_tuition("500");

        colleges_List.add(c1);

        College c2=new College();
        c1.setSchoolname("Nam2");
        c1.setinstate_tuition("600");


        colleges_List.add(c1);

        for(int i=0;i<50;i++){
            College c3=new College();
            c3.setSchoolname("Name"+i);
            c3.setinstate_tuition(String.valueOf(i));


            colleges_List.add(c3);
        }


        ListView favorites_listView=(ListView)root.findViewById(R.id.favorites_listView);

        ResultsList_Adapter favoritesList_adapter=new ResultsList_Adapter(root.getContext(),R.layout.results_adapter,colleges_List);

        favorites_listView.setAdapter(favoritesList_adapter);


        return root;

    }
}
