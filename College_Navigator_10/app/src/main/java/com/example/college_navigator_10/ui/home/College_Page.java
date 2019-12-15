package com.example.college_navigator_10.ui.home;


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

        TextView school_Name_TV=(TextView)root.findViewById(R.id.School_Name_College_Page);
        TextView location_TV=(TextView)root.findViewById(R.id.Location_TV_College_Page);
        TextView SAT_Reading_TV=(TextView)root.findViewById(R.id.SAT_Reading_TV_College_page);
        TextView SAT_Math_TV=(TextView)root.findViewById(R.id.SAT_math_TV_College_Page);
        TextView url_TV=(TextView)root.findViewById(R.id.url_TV_College_Page);


        school_Name_TV.setText(college_selected.getSchoolname());
        location_TV.setText(college_selected.getSchoolcity()+","
                           +college_selected.getState()+","
                           +college_selected.getZip());
        SAT_Reading_TV.setText("25th"+college_selected.getReadingpercentile_25()+
                             "\n75th"+college_selected.getReadingpercentile_75());
        SAT_Math_TV.setText("25th"+college_selected.getMathpercentile_25()+
                            "\n75th"+college_selected.getMathpercentile_75());
        url_TV.setText(college_selected.getUrl());




        return root;
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
