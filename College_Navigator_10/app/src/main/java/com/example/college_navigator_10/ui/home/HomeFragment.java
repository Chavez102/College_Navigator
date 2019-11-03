package com.example.college_navigator_10.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.example.college_navigator_10.R;

public class HomeFragment extends Fragment {
    public static String[] statesArray=new String[]{"California", "Alabama", "Arkansas", "Arizona",
            "Alaska", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
            "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
            "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
            "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" };



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);



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



        return root;
    }




































}