package com.example.college_navigator_10;

import android.app.Dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.college_navigator_10.Data_Management_Colleges.AllColleges;
import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DatabaseReference reff;
    College mycollege;
    ObjectMapper mapper=new ObjectMapper();


    private static final String TAG = "MainActivity";

public static Dialog logIn_popup;
public String hello="d";
public int i=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        Button letsGo_Btn=(Button)findViewById(R.id.LetsGo_btn);
        Button download_btn=(Button)findViewById(R.id.download_btn);

        logIn_popup=new Dialog(this);
        letsGo_Btn.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view){

        //region READ DATABASE///////////////////////////////////////////////////////

        //                reff=FirebaseDatabase.getInstance().getReference().child("Colleges").child("College2");
        //
        //                reff.addValueEventListener(new ValueEventListener() {
        //                    @Override
        //                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //                        String str=dataSnapshot.child("name").getValue().toString();
        //
        //                        Log.d(TAG,"////////////////////////////"+str);
        //                    }
        //
        //                    @Override
        //                    public void onCancelled(@NonNull DatabaseError databaseError) {
        //
        //                    }
        //                });


        //endregion READ DATABASE///////////////////////////////////////////////////////


                logIn_popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                logIn_popup.setContentView(R.layout.log_in_popup);
                logIn_popup.show();

                Button login_Btn=(Button)logIn_popup.findViewById(R.id.login_btn);
                Button guest_Btn=(Button)logIn_popup.findViewById(R.id.Guest_btn);

                login_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddingMenu();
                        logIn_popup.hide();
                    }
                });
             }


        });

        download_btn.setOnClickListener (new View.OnClickListener() {
            //region OnClick
            @Override
            public void onClick(View view) {

                //Unique for android to access the web!!!!
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);




                String original_url="https://api.data.gov/ed/collegescorecard/v1/schools.json?&per_page=100&api_key=LsIWyvYAHhge9xFr8aI32ujIVxSaaXLzNbN7U13V&_fields=latest.cost.tuition.out_of_state,id,school.name,school.city,school.zip,school.state,school.school_url,latest.cost.tuition.in_state,latest.admissions.sat_scores.25th_percentile.critical_reading,latest.admissions.sat_scores.25th_percentile.math,latest.admissions.sat_scores.75th_percentile.critical_reading,latest.admissions.sat_scores.75th_percentile.math";
                //https://api.data.gov/ed/collegescorecard/v1/schools.json?&per_page=100&api_key=LsIWyvYAHhge9xFr8aI32ujIVxSaaXLzNbN7U13V&_fields=latest.cost.tuition.out_of_state,id,school.name,school.city,school.zip,school.state,school.school_url,latest.cost.tuition.in_state,latest.admissions.sat_scores.25th_percentile.critical_reading,latest.admissions.sat_scores.25th_percentile.math,latest.admissions.sat_scores.75th_percentile.critical_reading,latest.admissions.sat_scores.75th_percentile.math

                int page=0;
                String pageAddOn="&page=";
                //url &page=0

                String url=original_url+pageAddOn+page;

                AllColleges colleges_perpage = new AllColleges();

                College[] arrayofColleges=new College[8000];
                ArrayList<College> list=new ArrayList<>();
                int arrayindex=0;
                ObjectMapper mapper = new ObjectMapper();
                reff=FirebaseDatabase.getInstance().getReference().child("Colleges");
                do {
                    try {

                        colleges_perpage = mapper.readValue(new URL(url),AllColleges.class);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for(int i=0;i<colleges_perpage.colleges.length;i++) {

//                                arrayofColleges[arrayindex]=colleges_perpage.colleges[i];
//                                arrayindex++;
//                                list.add(colleges_perpage.colleges[i]);

                        reff.child(colleges_perpage.colleges[i].getId())
                                .setValue(colleges_perpage.colleges[i]);
                        //region implements writeToDatabase

                        /////////////////////WRITE TO DATABASE///////////////////////////////////////////////////////


                        // reff.push().setValue(mycollege);

                        //reff=FirebaseDatabase.getInstance().getReference().child("Colleges");

                        //                reff=FirebaseDatabase.getInstance().getReference()
                        //                        .child("Colleges")
                        //                            .child("College"+String.valueOf(i));
                        //                reff.setValue(mycollege);
                        //endregion implements writeToDatabase

                    }
                    Log.d("PAGE",page+"/////////////////////////////////////////////////////////////////////////");
                    page++;
                    url=original_url+pageAddOn+page;
                }while(page<72);




            }
                                         }
            //endregion OnClick
        );


    }

    public void AddingMenu(){
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

}
