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
import com.example.college_navigator_10.MainActivity;
import com.example.college_navigator_10.R;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import static com.example.college_navigator_10.MainActivity.commingfrom;

public class HomeFragment extends Fragment {
    //region public static String[] statesArray=new String[]
    public static String[] statesArray=new String[]{"California", "Alabama", "Arkansas", "Arizona",
            "Alaska", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
            "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
            "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
            "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" };


    //endregion  public static String[] statesArray=new String[]



    View root;
    RangeSeekBar rangeSeekBar;

    AutoCompleteTextView states_input_ACTextview;
    EditText school_Name_Edit_Text;
    CrystalRangeSeekbar rangeSeekbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

        school_Name_Edit_Text=(EditText) root.findViewById(R.id.School_Name_editText);

        Advanced_Search_ExpandList_SetUp();

        setSearchBtn();

        return root;
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

        ((TextView) AdvancedSettings_items.findViewById(R.id.sub_title)).setText("One");

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

                Results_Main_page_Controller.SearchbyName = school_Name_Edit_Text.getText().toString();

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