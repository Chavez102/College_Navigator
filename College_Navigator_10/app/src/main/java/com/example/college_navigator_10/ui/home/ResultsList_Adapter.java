package com.example.college_navigator_10.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.example.college_navigator_10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.college_navigator_10.MainActivity.Official_Current_User;
import static com.example.college_navigator_10.MainActivity.addUserToDatabase;
import static com.example.college_navigator_10.MainActivity.reff;


public class ResultsList_Adapter extends ArrayAdapter<College> {

    public static College college_selected;
    public static boolean College_is_in_USerlist;



    Context mcontext;
    int mResource;
    public ResultsList_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<College> objects) {
        super(context, resource, objects);
        mcontext=context;
        mResource=resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String Schoolname=getItem(position).getSchoolname();
        String state=getItem(position).getState();
        String in_state_tuition=getItem(position).getinstate_tuition();
        String out_state_tuition=getItem(position).getTuition_outstate();
        College newcollege=getItem(position);


        LayoutInflater inflater =LayoutInflater.from(mcontext);

        convertView=inflater.inflate(mResource,parent,false);


       // findCollege(Schoolname,convertView);

        final TextView SchoolName_TextView=(TextView)convertView.findViewById(R.id.SchoolName_TextView) ;
        final TextView SchoolState_TextView=(TextView)convertView.findViewById(R.id.SchoolState_TextView);
        final TextView School_InState_Tuition_TextView=(TextView)convertView.findViewById(R.id.instate_Tuition_TextView);
        final  TextView School_OutState_Tuition_TextView=(TextView)convertView.findViewById(R.id.outState_Tuition_TextView);


        SchoolName_TextView.setText(newcollege.getSchoolname());
        SchoolState_TextView.setText(newcollege.getState());
        School_InState_Tuition_TextView.setText(newcollege.getinstate_tuition());
        School_OutState_Tuition_TextView.setText(newcollege.getTuition_outstate());


         setHeartBtn(convertView,newcollege);

        setLayout_Btn(convertView,newcollege);


        return convertView;

    }

    private void setLayout_Btn(View convertView,final College newcollege) {
        LinearLayout layout=(LinearLayout)convertView.findViewById(R.id.Linear_LayOut);
        layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                College_is_in_USerlist=liked;
                college_selected=newcollege;

                Fragment fragment = new College_Page();
                ((FragmentActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,fragment)
                    .commit();

            }
        });
    }

    public void changetoCollegepage(){

        Fragment fragment = new Results_Main_page_Controller();
        FragmentTransaction fr = fragment.getFragmentManager().beginTransaction();
        fr.replace(R.id.nav_host_fragment, fragment);
        fr.commit();

    }



    public boolean liked=false;
    public void setHeartBtn(View convertView,final College selectedCollege){


        final ImageButton heartButton=(ImageButton)convertView.findViewById(R.id.heart_imgbtn);


        if(newcollege_In_LikedCOlleges_List(selectedCollege)){
            liked=true;
            heartButton.setImageResource(R.drawable.red_heart_logo);
        }


        //region OnClick

            heartButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Log.d("heart","PRESSED////////////////////////////////////0");
                    if (liked==false){
                        liked=!liked;
                        heartButton.setImageResource(R.drawable.red_heart_logo);

                        add_College_toUserLikedColleges(selectedCollege);


                    }else{
                        remove_College_toUserLikedColleges(selectedCollege);
                        liked=!liked;
                        heartButton.setImageResource(R.drawable.heart_logo_white);
                    }

                }
            });

        //endregion OnClick


    }

    public boolean newcollege_In_LikedCOlleges_List(College newcollege){

        for(College current_college:Official_Current_User.getlikedCollege()){

            if(newcollege.getSchoolname().equals(current_college.getSchoolname())){
                return true;
            }
        }
        return false;
    }


   public static void add_College_toUserLikedColleges(College collegetobe_Added){



        Official_Current_User.likedCollege.add(collegetobe_Added);




       reff= FirebaseDatabase.getInstance().getReference()
               .child("AllUsers")
               .child(Official_Current_User.getUsername());

        reff=reff.child("likedCollege");
        reff.setValue(Official_Current_User.getlikedCollege());



    }


    public static void remove_College_toUserLikedColleges(College collegetobe_Removed){


        Official_Current_User.likedCollege.remove(collegetobe_Removed);




        reff=FirebaseDatabase.getInstance().getReference()
                .child("AllUsers")
                .child(Official_Current_User.getUsername());

        reff=reff.child("likedCollege");
        reff.setValue(Official_Current_User.getlikedCollege());


    }















}
