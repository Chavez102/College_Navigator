package com.example.college_navigator_10.ui.home;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.example.college_navigator_10.R;
import com.example.college_navigator_10.ui.favorites.FavoritesFragment;

import static com.example.college_navigator_10.MainActivity.Official_Current_User;
import static com.example.college_navigator_10.MainActivity.commingfrom;
import static com.example.college_navigator_10.ui.home.ResultsList_Adapter.College_is_in_USerlist;
import static com.example.college_navigator_10.ui.home.ResultsList_Adapter.add_College_toUserLikedColleges;
import static com.example.college_navigator_10.ui.home.ResultsList_Adapter.college_selected;
import static com.example.college_navigator_10.ui.home.ResultsList_Adapter.remove_College_toUserLikedColleges;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.

 * create an instance of this fragment.
 */
public class College_Page extends Fragment {
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_college__page, container, false);

        backbtn_setup();
        heartbtn_setup(college_selected);

        accetance_decision_setup(college_selected);

        TextView tuition_TV=(TextView)root.findViewById(R.id.Tution_TV_College_Page);

        TextView school_Name_TV=(TextView)root.findViewById(R.id.School_Name_College_Page);
        TextView location_TV=(TextView)root.findViewById(R.id.Location_TV_College_Page);
        TextView SAT_Reading_TV=(TextView)root.findViewById(R.id.SAT_Reading_TV_College_page);
        TextView SAT_Math_TV=(TextView)root.findViewById(R.id.SAT_math_TV_College_Page);
        TextView url_TV=(TextView)root.findViewById(R.id.url_TV_College_Page);

        college_selected.setReadingpercentile_25(switch_Null_strings(college_selected.getReadingpercentile_25()));
        college_selected.setReadingpercentile_75(switch_Null_strings(college_selected.getReadingpercentile_75()));

        college_selected.setMathpercentile_25(switch_Null_strings(college_selected.getMathpercentile_25()));
        college_selected.setMathpercentile_75(switch_Null_strings(college_selected.getMathpercentile_75()));

        college_selected.setinstate_tuition(switch_Null_strings(college_selected.getinstate_tuition()));
        college_selected.setTuition_outstate(switch_Null_strings(college_selected.getTuition_outstate()));

        Log.d("//"+college_selected.getTuition_outstate(),"Tutition//////////////////////////////////////////");







        tuition_TV.setText("Instate Tuition: "+college_selected.getinstate_tuition()
        +"\nOutstate Tuition: "+college_selected.getTuition_outstate());


        school_Name_TV.setText(college_selected.getSchoolname());
        location_TV.setText(college_selected.getSchoolcity()+","
                           +college_selected.getState()+","
                           +college_selected.getZip());
        SAT_Reading_TV.setText("SAT 25th Reading Percentile: "+college_selected.getReadingpercentile_25()+
                             "\nSAT 75th Reading Percentile: "+college_selected.getReadingpercentile_75());

        SAT_Math_TV.setText("SAT 25th Math Percentile: "+college_selected.getMathpercentile_25()+
                          "\nSAT 75th Math Percentile: "+college_selected.getMathpercentile_75());
        url_TV.setText(college_selected.getUrl());




        return root;
    }

    private void accetance_decision_setup(College college_selected) {

        TextView acceptance_TV=(TextView)root.findViewById(R.id.acceptance_decision_TV_College_Page);



        double user_SATReading=Double.valueOf(Official_Current_User.getReading_SAT());
        double user_SATMath=Double.valueOf(Official_Current_User.getMath_SAT());

        if(     college_selected.getReadingpercentile_25()==null||
                college_selected.getReadingpercentile_75()==null||

                college_selected.getMathpercentile_25()==null||
                college_selected.getMathpercentile_75()==null
        ){
            return;
        }




        double college_SATReading25=Double.valueOf(college_selected.getReadingpercentile_25());
        double college_SATReading75=Double.valueOf(college_selected.getReadingpercentile_75());
        double college_SATMath25=Double.valueOf(college_selected.getMathpercentile_25());
        double college_SATMath75=Double.valueOf(college_selected.getMathpercentile_75());

        if(
               user_SATReading>college_SATReading75
             &&user_SATMath>college_SATMath75
        ){
            acceptance_TV.setBackgroundColor(Color.parseColor("#6FE098"));

            acceptance_TV.setText( "Your SAT Scores are Higher than this school's SAT scores");

        }
        else if(
                user_SATReading<college_SATReading25
                        &&user_SATMath<college_SATMath25
        ){
            acceptance_TV.setBackgroundColor(Color.parseColor("#C52424"));
            acceptance_TV.setText( "Your SAT Scores are lower than this school's SAT scores");
        }else{
            acceptance_TV.setBackgroundColor(Color.parseColor("#CAB358"));
            acceptance_TV.setText( "Your SAT scores are moderate compared to this school's SAT scores");
        }



    }









    private String switch_Null_strings(String nullstring) {

        if(nullstring==null){
            return "unknown";
        }

        return nullstring;
    }


    public void heartbtn_setup(final College selectedCollege){

        final ImageButton heartButton=(ImageButton)root.findViewById(R.id.heart_btn_College_Page);


       if(College_is_in_USerlist){
           College_is_in_USerlist=true;
           heartButton.setImageResource(R.drawable.big_heart_logo);
       }

        heartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (College_is_in_USerlist==false){
                    College_is_in_USerlist=!College_is_in_USerlist;
                    heartButton.setImageResource(R.drawable.big_heart_logo);

                    add_College_toUserLikedColleges(selectedCollege);


                }else{
                    remove_College_toUserLikedColleges(selectedCollege);
                    College_is_in_USerlist=!College_is_in_USerlist;
                    heartButton.setImageResource(R.drawable.big_heart_logo_white);
                }

            }
        });









    }







    private void backbtn_setup(){

        Button back_btn=(Button) root.findViewById(R.id.back_btn_College_Page);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button","back btn Pressed");

                Fragment fragment=new Fragment();
                if(commingfrom.equals("HomeFragment"))
                {
                     fragment = new HomeFragment();
                }
                if(commingfrom.equals("FavoritesFragment"))
                {
                     fragment = new FavoritesFragment();
                }


                ((FragmentActivity) view.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,fragment)
                        .commit();




            }
        });
    }


}
