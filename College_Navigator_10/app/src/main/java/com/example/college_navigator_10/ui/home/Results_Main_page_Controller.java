package com.example.college_navigator_10.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.college_navigator_10.Data_Management_Colleges.College;
import com.example.college_navigator_10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Results_Main_page_Controller extends Fragment {


    DatabaseReference reff;
    ListView results_ListView;
    ResultsList_Adapter resultList_adapter;


    ArrayList<College> colleges_List=new ArrayList<>();

    //region Stating Strings

        public static String SearchbyName;
        public static String SearchbyId;
        public static String SearchbyState;
        public String StateSymbol;
        public static int SearchbyTuitionMin;
        public static int SearchbyTuitionMax;

        public static String SearchbyMajors=null;
        public static String SearchbyLevelAward=null;
    //endregion Stating Strings



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.results_main_page, container, false);

        //region Log print values

            Log.d("SearchbyName=","//"+String.valueOf(SearchbyName)+"/////");
            Log.d("SearchbyState=", "//"+String.valueOf(SearchbyState)+"/////");
            Log.d("SearchbyTuitionMin=", "//"+String.valueOf(SearchbyTuitionMin)+"/////");
            Log.d("SearchbyTuitionMax=", "//"+String.valueOf(SearchbyTuitionMax)+"/////");
        //endregion Log print values

        ArrayList<College> list1= new ArrayList<>();

//        SearchbyName="Empire Beauty School-N Memphis";
//        SearchbyState="TN";

        StateSymbol=ConvertState_to_StateSymbol(SearchbyState);


        Boolean searching_by_Name=false,
         searching_by_state=false,
         searching_by_tuition=false;

        if (!SearchbyName.equals("")){
            searching_by_Name=true;
        }

        if (!SearchbyState.equals("")){
            searching_by_state=true;
        }



        query_by_what(searching_by_Name,
                      searching_by_state,
                      searching_by_tuition);



        results_ListView=(ListView)root.findViewById(R.id.Fragment_results_List_VIew);
        resultList_adapter=new ResultsList_Adapter(root.getContext(),R.layout.results_adapter,colleges_List);
        results_ListView.setAdapter(resultList_adapter);


        return root;

    }


    public void query_by_what(final Boolean searching_by_Name,
                              final Boolean searching_by_state,
                              final Boolean searching_by_tuition) {

        Query query=FirebaseDatabase.getInstance().getReference("Colleges");

        ValueEventListener valueEventListener=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                       colleges_List.clear();
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){


                                College mycollege= snapshot.getValue(College.class);




                                if (searching_by_Name==true){




                                }



                                colleges_List.add(mycollege);
                                //   colleges_List.add(mycollege);
                            }
                            resultList_adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };


        ValueEventListener SearchbyName_valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                colleges_List.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                        College mycollege= snapshot.getValue(College.class);


                       if(  StateSymbol.equals(mycollege.getState() )           //check if state input is the same as the college state
                         || StateSymbol.equals("No StateSymbol")    //check if college state Symbol is null, if so then the user never entered a State
                       ){
                           Log.d("College Added",mycollege.getSchoolname()+"//////////////////////////////////////////");
                           colleges_List.add(mycollege);
                       }

                    }
                    resultList_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        ValueEventListener SearchbyState_valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                colleges_List.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                        College mycollege= snapshot.getValue(College.class);


                        Log.d("College",mycollege.getSchoolname()+"//////////////////////////////////////////////");

                            colleges_List.add(mycollege);


                    }
                    resultList_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };






        if (searching_by_Name==true){


             query = FirebaseDatabase.getInstance().getReference("Colleges")
                    .orderByChild("schoolname")
                    .equalTo(SearchbyName);

            query.addListenerForSingleValueEvent(SearchbyName_valueEventListener);

        }
        else if(searching_by_state==true){
//            Log.d("searchbyState","True/////////////////////////////////");
//
//            Log.d("State",SearchbyState+"/////////////////////////////////");
//
//
//
//            Log.d("StateSymbol",StateSymbol+"//////////////////////////////////////////////");




            query.addListenerForSingleValueEvent(SearchbyState_valueEventListener);

        }







    }






    public void query_get_All_Colleges(){
        reff= FirebaseDatabase.getInstance().getReference("Colleges");
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                colleges_List.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                        College mycollege= snapshot.getValue(College.class);
                        colleges_List.add(mycollege);
                        //   colleges_List.add(mycollege);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        reff.addListenerForSingleValueEvent(valueEventListener);

    }

    private String ConvertState_to_StateSymbol(String searchbyState) {
        String st;

        Log.d("Conver_StateSymbol","beginnning******************************");

        try {

            InputStream is = getContext().getAssets().open("States.txt");


            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while ((st = br.readLine()) != null) {


                Scanner scanner = new Scanner(st);


                int iend = st.indexOf("-");

                String state="";
                if (iend != -1)
                {
                    state= st.substring(0 , iend-1);
                }
                if(state.equals(searchbyState)) {
                    if (iend != -1)
                    {
                        return state= st.substring(iend+2 , st.length());
                    }


                }
                scanner.close();

            }

        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Log.d("Conver_StateSymbol","ending******************************");
        return "No StateSymbol";
    }


    private boolean isEmptyOrNull(String mystr){
        if (mystr != null && !mystr.isEmpty()){ return true; }
        else { return false; }
    }


    //region READ DATABASE///////////////////////////////////////////////////////







//                        reff.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                String str=dataSnapshot.child("schoolname").getValue().toString();
//
//                                Log.d("The STR","////////////////////////////"+str);
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });


    //endregion READ DATABASE///////////////////////////////////////////////////////

}
