package com.example.college_navigator_10.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.example.college_navigator_10.MainActivity;
import com.example.college_navigator_10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import static com.example.college_navigator_10.MainActivity.commingfrom;
import static com.example.college_navigator_10.MainActivity.reff;

public class HomeFragment extends Fragment {
    //region public static String[] statesArray=new String[]
    public static String[] statesArray=new String[]{"California", "Alabama", "Arkansas", "Arizona",
            "Alaska", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
            "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
            "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
            "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" };

    public static String[] SchoolNamesArray=new String[7112];


    //endregion  public static String[] statesArray=new String[]



    View root;


    AutoCompleteTextView states_input_ACTextview;
    AutoCompleteTextView school_Name_AutoCompleteTextView;
    CrystalRangeSeekbar rangeSeekbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

        school_Name_AutoCompleteTextView=(AutoCompleteTextView) root.findViewById(R.id.School_Name_editText);
        school_Name_AutoCompleteTextView_setup();


        Advanced_Search_ExpandList_SetUp();

        setSearchBtn();

        return root;
    }

    private void school_Name_AutoCompleteTextView_setup() {


        school_Name_AutoCompleteTextView=(AutoCompleteTextView)root.findViewById(
                R.id.School_Name_editText);

        fillSchoolNameArray();


        ArrayAdapter<String> Schoolname_adapter=new ArrayAdapter<String>(root.getContext(),
                android.R.layout.simple_list_item_1,SchoolNamesArray);


        school_Name_AutoCompleteTextView.setAdapter(Schoolname_adapter);



    }

    private void fillSchoolNameArray() {




        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;

                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                        College mycollege= snapshot.getValue(College.class);
                        SchoolNamesArray[count]=mycollege.getSchoolname();
                        count++;



                        //   colleges_List.add(mycollege);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        reff= FirebaseDatabase.getInstance().getReference("Colleges");
        reff.addListenerForSingleValueEvent(valueEventListener);


    }

    public void Advanced_Search_ExpandList_SetUp(){

        ExpandingList expandingList = (ExpandingList) root.findViewById(R.id.expanding_list_main);

        ExpandingItem item = expandingList.createNewItem(R.layout.expanding_layout);

        TextView Title= (TextView) item.findViewById(R.id.title);
        Title.setText(R.string.AdvancedSearch);

        //This will create 1 items
        item.createSubItems(1);

        //get a sub item View
        View AdvancedSettings_items = item.getSubItemView(0);

        states_input_ACTextview=(AutoCompleteTextView)AdvancedSettings_items.findViewById(
                R.id.states_input_ACTextview);


        ArrayAdapter<String> states_adapter=new ArrayAdapter<String>(root.getContext(),
                android.R.layout.simple_list_item_1,statesArray);


        states_input_ACTextview.setAdapter(states_adapter);

        ((TextView) AdvancedSettings_items.findViewById(R.id.sub_title)).setText("Tution Range");

        SeekBar_setup();
    }

    public void setSearchBtn(){
        Button searchbtn=(Button)root.findViewById(R.id.search_btn);
        commingfrom="HomeFragment";

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                set_BarSeek_Min_MAx();

                Results_Main_page_Controller.SearchbyState = states_input_ACTextview.getText().toString();

                Results_Main_page_Controller.SearchbyName = school_Name_AutoCompleteTextView.getText().toString();

                Fragment fragment = new Results_Main_page_Controller();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, fragment);
                fr.commit();

            }
        }
        );


    }

    public void SeekBar_setup(){

        // get seekbar from view
        rangeSeekbar = (CrystalRangeSeekbar) root.findViewById(R.id.rangeSeekbar1);


        rangeSeekbar.setMinValue(1000);
        rangeSeekbar.setMaxValue(70000);

        // get min and max text view
        final TextView seekBarMintv = (TextView) root.findViewById(R.id.textMin1);
        final TextView seekBarMaxtv = (TextView) root.findViewById(R.id.textMax1);

        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener(){
            @Override
            public void valueChanged(Number minValue, Number maxValue){
                seekBarMintv.setText(String.valueOf(minValue));
                seekBarMaxtv.setText(String.valueOf(maxValue));

                Results_Main_page_Controller.SearchbyTuitionMin = minValue.intValue();
                Results_Main_page_Controller.SearchbyTuitionMax = maxValue.intValue();

            }
        });



    }

//    public void set_BarSeek_Min_MAx(){
//
//        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
//            @Override
//            public void finalValue(Number minValue, Number maxValue) {
//                Results_Main_page_Controller.SearchbyTuitionMin = (int)minValue;
//                Results_Main_page_Controller.SearchbyTuitionMax = (int)maxValue;
//
//
//                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
//            }
//        });
//    }




}