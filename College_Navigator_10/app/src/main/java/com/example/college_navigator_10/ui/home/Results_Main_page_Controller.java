package com.example.college_navigator_10.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.example.college_navigator_10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Results_Main_page_Controller extends Fragment {


    DatabaseReference reff;
    ListView results_ListView;
    ResultsList_Adapter resultList_adapter;
    ArrayList<College> colleges_List=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.results_main_page, container, false);


//        College college=new College();
//        college.setTuition_outstate("100");
//        college.setinstate_tuition("200");
//        college.setSchoolname("School1");
//        college.setState("New York");
//
//        colleges_List.add(college);
//        college=new College();
//
//        college.setTuition_outstate("1");
//        college.setinstate_tuition("2");
//        college.setSchoolname("School2");
//        college.setState("New York");
//        colleges_List.add(college);

        //region READ DATABASE///////////////////////////////////////////////////////







//                        reff.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                String str=dataSnapshot.child("schoolname").getValue().toString();
//
//                                Log.d("The STR","////////////////////////////"+str);
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });


        //endregion READ DATABASE///////////////////////////////////////////////////////



        results_ListView=(ListView)root.findViewById(R.id.Fragment_results_List_VIew);
        resultList_adapter=new ResultsList_Adapter(root.getContext(),R.layout.results_adapter,colleges_List);

        results_ListView.setAdapter(resultList_adapter);

        reff= FirebaseDatabase.getInstance().getReference("Colleges");
        reff.addListenerForSingleValueEvent(valueEventListener);

        return root;
    }



    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            colleges_List.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    College mycollege= snapshot.getValue(College.class);
                    colleges_List.add(mycollege);


                }
                resultList_adapter.notifyDataSetChanged();

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
