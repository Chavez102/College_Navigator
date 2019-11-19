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

public class HomeFragment extends Fragment {
    public static String[] statesArray=new String[]{"California", "Alabama", "Arkansas", "Arizona",
            "Alaska", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
            "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
            "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
            "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" };

    View root;
    RangeSeekBar rangeSeekBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         root = inflater.inflate(R.layout.fragment_home, container, false);


        setSearchBtn();

        //region ExpandingList set up
        ExpandingList expandingList = (ExpandingList) root.findViewById(R.id.expanding_list_main);

        ExpandingItem item = expandingList.createNewItem(R.layout.expanding_layout);

            /*ExpandingItem extends from View, so you can call
            findViewById to get any View inside the layout*/
        TextView Title= (TextView) item.findViewById(R.id.title);
        Title.setText(R.string.AdvancedSearch);
        //This will create 1 items
        item.createSubItems(1);

//get a sub item View
        View AdvancedSettings_items = item.getSubItemView(0);
        AutoCompleteTextView states_input_ACTextview=(AutoCompleteTextView)AdvancedSettings_items.findViewById(
                R.id.states_input_ACTextview);
        ArrayAdapter<String> states_adapter=new ArrayAdapter<String>(root.getContext(),android.R.layout.simple_list_item_1,statesArray);
        states_input_ACTextview.setAdapter(states_adapter);
        ((TextView) AdvancedSettings_items.findViewById(R.id.sub_title)).setText("One");

        //  item.setIndicatorColorRes(R.color.black);
        //      item.setIndicatorIconRes(R.drawable.ic_icon);




        //region SEAKBAR SET UP

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) root.findViewById(R.id.rangeSeekbar1);

// get min and max text view
        final TextView tvMin = (TextView) root.findViewById(R.id.textMin1);
        final TextView tvMax = (TextView) root.findViewById(R.id.textMax1);

// set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener(){
            @Override
            public void valueChanged(Number minValue, Number maxValue){
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });


        //endregion SEEKBAR SET UP


        //endregion  ExpandingList set up


        return root;
    }





    public void setSearchBtn(){
        Button searchbtn=(Button)root.findViewById(R.id.search_btn);

        searchbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Log.d("Search Button","CLicked/////////////////////////////////////////");

                Fragment fragment = new Results_Main_page_Controller();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, fragment);
                //transaction.addToBackStack(null);
                fr.commit();




            }
        }


        );


    }






}