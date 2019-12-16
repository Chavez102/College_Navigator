package com.example.college_navigator_10;

import android.app.Dialog;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.college_navigator_10.Data_Management_Colleges.AllColleges;
import com.example.college_navigator_10.Data_Management_Colleges.College;

import com.example.college_navigator_10.Data_Management_Colleges.User_Data.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static DatabaseReference reff;

    public static String commingfrom;




    College mycollege;
    ObjectMapper mapper=new ObjectMapper();

    public static User Official_Current_User;
    private static final String TAG = "MainActivity";

    public static Dialog logIn_popup;
    public static Dialog register_popup;
    Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        download_btn_setp();
        logIn_popup=new Dialog(this);
        register_popup=new Dialog(this);
        lets_Go_btn_setup();
    }

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

    public void lets_Go_btn_setup(){
        Button letsGo_Btn=(Button)findViewById(R.id.LetsGo_btn);

        letsGo_Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                logIn_popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                logIn_popup.setContentView(R.layout.log_in_popup);
                logIn_popup.show();

                final Button login_Btn=(Button)logIn_popup.findViewById(R.id.login_btn);
                Button guest_Btn=(Button)logIn_popup.findViewById(R.id.Guest_btn);
                TextView newaccountTextView=(TextView)logIn_popup.findViewById(R.id.New_Account_TV);

                final EditText Username_ET=(EditText)logIn_popup.findViewById(R.id.LogIn_username_ET);
                final EditText password_ET=(EditText)logIn_popup.findViewById(R.id.LogIn_password_EditText);


                newaccountTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        register_popup_setup();
                    }
                });

                login_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CheckUser(      Username_ET.getText().toString(),
                                password_ET.getText().toString() );


                    }
                });


                //region GuestButton setup
                guest_Btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        CheckUser("guest","password");
//                        AddingMenu();
//                        logIn_popup.hide();

                    }
                });
                //endregion GuestButton setup


            }


        });
    }


    public void CheckUser(String username, final String password){

       final TextView errorMessage_TV=(TextView)logIn_popup.findViewById(R.id.ErrorMessage_TV);

        ValueEventListener LogIn_valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists()){

                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        User myuser= snapshot.getValue(User.class);

                        if(myuser.getPassword().equals(password)){


                            Official_Current_User=myuser;
                            Official_Current_User.likedCollege=new ArrayList<>();




                                ValueEventListener ReadList_EventListener = new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        if(snapshot.exists()) {
                                            GenericTypeIndicator<ArrayList<College>> t = new GenericTypeIndicator<ArrayList<College>>() {
                                            };
                                            ArrayList<College> college_List = snapshot.getValue(t);
                                            Official_Current_User.setlikedCollege(college_List);

//                                    Log.d("College Read",college_List.get(0).getSchoolname()+"********************");
//                                    Log.d("College Read",
//                                            Official_Current_User.getlikedCollege().get(0).getSchoolname()+"********************");
                                        }

                                    }
                                    @Override
                                    public void onCancelled(DatabaseError firebaseError) {
                                        Log.e("The read failed: " ,firebaseError.getMessage());
                                    }
                                };

                                Query query2 = FirebaseDatabase.getInstance().getReference("AllUsers")
                                        .child(Official_Current_User.getUsername())
                                        .child("likedCollege")
                                        ;

                                query2.addListenerForSingleValueEvent(ReadList_EventListener);


                            AddingMenu();
                            logIn_popup.hide();




                        }


                        else{

                            errorMessage_TV.setText("Wrong Password");

                        }

                    }


                }

                else{

                    errorMessage_TV.setText("user not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        Query query = FirebaseDatabase.getInstance().getReference("AllUsers")
                .orderByChild("username")
                .equalTo(username);

        query.addListenerForSingleValueEvent(LogIn_valueEventListener);


    }

    public void download_btn_setp(){

        Button download_btn=(Button)findViewById(R.id.download_btn);
        download_btn.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Unique for android to access the web!!!!
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                String original_url = "https://api.data.gov/ed/collegescorecard/v1/schools.json?&per_page=100&api_key=LsIWyvYAHhge9xFr8aI32ujIVxSaaXLzNbN7U13V&_fields=latest.cost.tuition.out_of_state,id,school.name,school.city,school.zip,school.state,school.school_url,latest.cost.tuition.in_state,latest.admissions.sat_scores.25th_percentile.critical_reading,latest.admissions.sat_scores.25th_percentile.math,latest.admissions.sat_scores.75th_percentile.critical_reading,latest.admissions.sat_scores.75th_percentile.math";
                //https://api.data.gov/ed/collegescorecard/v1/schools.json?&per_page=100&api_key=LsIWyvYAHhge9xFr8aI32ujIVxSaaXLzNbN7U13V&_fields=latest.cost.tuition.out_of_state,id,school.name,school.city,school.zip,school.state,school.school_url,latest.cost.tuition.in_state,latest.admissions.sat_scores.25th_percentile.critical_reading,latest.admissions.sat_scores.25th_percentile.math,latest.admissions.sat_scores.75th_percentile.critical_reading,latest.admissions.sat_scores.75th_percentile.math

                int page = 0;
                String pageAddOn = "&page=";
                //url &page=0

                String url = original_url + pageAddOn + page;

                AllColleges colleges_perpage = new AllColleges();

                ObjectMapper mapper = new ObjectMapper();

                Remove_All_College_FromDatabase();

                do {
                    try {

                        colleges_perpage = mapper.readValue(new URL(url), AllColleges.class);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < colleges_perpage.colleges.length; i++) {


                        reff.child(colleges_perpage.colleges[i].getId())
                                .setValue(colleges_perpage.colleges[i]);



                    }

                    Log.d("Page"+page,"Read //////////////////////////////////////////////////////////");

                    page++;
                    url = original_url + pageAddOn + page;
                } while (page < 72);


            }

        });

    }

    public void register_popup_setup(){

        register_popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        register_popup.setContentView(R.layout.register_popup);
        register_popup.show();



        final Button register_btn=(Button)register_popup.findViewById(R.id.register_btn);

        final TextView username_TV=(TextView)register_popup.findViewById(R.id.Register_username_ET);
        final TextView password_TV=(TextView)register_popup.findViewById(R.id.password_ET);
        final TextView email_TV=(TextView)register_popup.findViewById(R.id.email_ET);
        final TextView Math_TV=(TextView)register_popup.findViewById(R.id.SAT_Math_TV);
        final TextView Reading_TV=(TextView)register_popup.findViewById(R.id.SAT_Reading_TV);

        
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_popup.hide();
                User newuser=new User();
                newuser.setUsername(username_TV.getText().toString());
                newuser.setPassword(password_TV.getText().toString());
                newuser.setEmail(email_TV.getText().toString());
                newuser.setMath_SAT(Math_TV.getText().toString());
                newuser.setReading_SAT(Reading_TV.getText().toString());

                addUserToDatabase(newuser);
            }
        });


    }

    public static void addUserToDatabase(User myuser){
        DatabaseReference reff;

            Log.d("this is new user",myuser.getUsername()+"/////////////////////");
            
                reff=FirebaseDatabase.getInstance().getReference()
                .child("AllUsers")
                .child(myuser.getUsername());
        reff.setValue(myuser);

    }

    public void Remove_All_College_FromDatabase(){
        reff=FirebaseDatabase.getInstance().getReference().child("Colleges");
        reff.removeValue();
    }

    public void AddingMenu(){
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,

                R.id.navigation_favorites,

                R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }




}
