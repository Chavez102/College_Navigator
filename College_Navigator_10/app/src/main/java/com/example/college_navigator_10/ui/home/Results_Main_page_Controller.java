package com.example.college_navigator_10.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.example.college_navigator_10.R;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Results_Main_page_Controller extends AppCompatActivity {
    ListView results_ListView;
    ResultsList_Adapter resultList_adapter;
    ArrayList<College> colleges_List=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_main_page);

        College college=new College();
        college.setTuition_outstate("100");
        college.setinstate_tuition("200");
        college.setSchoolname("MyName");
        college.setState("New York");

        colleges_List.add(college);


        college.setTuition_outstate("100");
        college.setinstate_tuition("200");
        college.setSchoolname("Other School");
        college.setState("New York");
        colleges_List.add(college);

        results_ListView=(ListView)findViewById(R.id.results_List_VIew);


        resultList_adapter=new ResultsList_Adapter(this,R.layout.results_adapter,colleges_List);

        results_ListView.setAdapter(resultList_adapter);
    }

}
