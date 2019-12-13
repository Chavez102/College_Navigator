package com.example.college_navigator_10.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.example.college_navigator_10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.college_navigator_10.MainActivity.Official_Current_User;
import static com.example.college_navigator_10.MainActivity.addUserToDatabase;

public class ResultsList_Adapter extends ArrayAdapter<College> {

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


        College newcollege=new College();
        newcollege.setSchoolname(Schoolname);
        newcollege.setState(state);
        newcollege.setinstate_tuition(in_state_tuition);
        newcollege.setTuition_outstate(out_state_tuition);

        LayoutInflater inflater =LayoutInflater.from(mcontext);

        convertView=inflater.inflate(mResource,parent,false);


        TextView SchoolName_TextView=(TextView)convertView.findViewById(R.id.SchoolName_TextView) ;
        TextView SchoolState_TextView=(TextView)convertView.findViewById(R.id.SchoolState_TextView);
        TextView School_InState_Tuition_TextView=(TextView)convertView.findViewById(R.id.instate_Tuition_TextView);
        TextView School_OutState_Tuition_TextView=(TextView)convertView.findViewById(R.id.outState_Tuition_TextView);

        setHeartBtn(convertView,newcollege);


        SchoolName_TextView.setText(newcollege.getSchoolname());
        SchoolState_TextView.setText(newcollege.getState());
        School_InState_Tuition_TextView.setText(newcollege.getinstate_tuition());
        School_OutState_Tuition_TextView.setText(newcollege.getTuition_outstate());
        return convertView;

    }


    public void setHeartBtn(View convertView,final College selectedCollege){

        final ImageButton heartButton=(ImageButton)convertView.findViewById(R.id.heart_imgbtn);

        heartButton.setOnClickListener(new View.OnClickListener() {
            boolean liked=false;
            @Override
            public void onClick(View view) {

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

    }

    DatabaseReference reff;



   public void add_College_toUserLikedColleges(College collegetobe_Added){



        Official_Current_User.likedCollege.add(collegetobe_Added);



       Official_Current_User.LogArrayList();

       reff=FirebaseDatabase.getInstance().getReference()
               .child("AllUsers")
               .child(Official_Current_User.getUsername());

        reff=reff.child("likedCollege");
        reff.setValue(Official_Current_User.getlikedCollege());



    }




    public void remove_College_toUserLikedColleges(College collegetobe_Removed){

//
//
//        Official_Current_User.likedCollege.remove();



//        Official_Current_User.LogArrayList();
//
//        reff=FirebaseDatabase.getInstance().getReference()
//                .child("AllUsers")
//                .child(Official_Current_User.getUsername());
//
//        reff=reff.child("likedCollege");
//        reff.setValue(Official_Current_User.getlikedCollege());


    }















}
