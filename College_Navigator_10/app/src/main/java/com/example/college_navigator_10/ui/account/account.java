package com.example.college_navigator_10.ui.account;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.college_navigator_10.MainActivity;
import com.example.college_navigator_10.R;
import com.example.college_navigator_10.ui.home.HomeFragment;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;
import static com.example.college_navigator_10.MainActivity.Official_Current_User;
import static com.example.college_navigator_10.MainActivity.addUserToDatabase;
import static com.example.college_navigator_10.MainActivity.reff;

public class account extends Fragment {

    View root;
    public static Dialog register_popup;
    public Dialog changeTextview_Dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_account, container, false);





        TextView email_TV=(TextView) root.findViewById(R.id.email_Account_Page);
        TextView reading_SAT_TV=(TextView) root.findViewById(R.id.SAT_reading_Account_Page);
        TextView math_SAT_TV=(TextView) root.findViewById(R.id.SAT_math_Account_Page);
        TextView username_TV=(TextView) root.findViewById(R.id.username_Account_Page);
        TextView username_bottom_imageView_Account_Page=(TextView) root.findViewById(R.id.username_bottom_imageView_Account_Page);
        TextView password_TV=(TextView) root.findViewById(R.id.password_Account_Page);


        email_TV.setText(Official_Current_User.getEmail());
        reading_SAT_TV.setText(Official_Current_User.getReading_SAT());
        math_SAT_TV.setText(Official_Current_User.getMath_SAT());
        username_TV.setText(Official_Current_User.getUsername());
        username_bottom_imageView_Account_Page.setText(Official_Current_User.getUsername());
        password_TV.setText(Official_Current_User.getPassword());


        changeTextview_Dialog=new Dialog(root.getContext());

        textView_pressed_setup(email_TV,"email");
        textView_pressed_setup(reading_SAT_TV,"reading_SAT");
        textView_pressed_setup(math_SAT_TV,"math_SAT");
        textView_pressed_setup(password_TV,"password");

        log_out_btn_setup();

        choose_picture_btn_setup();



        return root;
    }


    private void choose_picture_btn_setup() {
        Button choose_pick=(Button)root.findViewById(R.id.choose_btn_Account_Page);
        imageView=(ImageView)root.findViewById(R.id.imageView);

        choose_pick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openGallary();
            }
        });
    }

    private static final int PICK_IMAGE=100;
    Uri imageUri;
    ImageView imageView;

   private void openGallary(){
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
   }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }


    }










    private void log_out_btn_setup() {
        Button logout_btn=(Button)root.findViewById(R.id.log_out_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(root.getContext(), MainActivity.class);
                root.getContext().startActivity(myIntent);

            }
        });


    }

    private void textView_pressed_setup(final TextView textView, final String parameter) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // register_popup_setup();

                changeTextview_popup_setup(textView,parameter);
            }
        });


    }

   public void  changeTextview_popup_setup(TextView textView,String parameter){
       changeTextview_Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

       changeTextview_Dialog.setContentView(R.layout.dialog_change__text_view);

       cancel_btn_setup();

       change_btn_setup(textView,parameter);

       changeTextview_Dialog.show();

    }

    private void change_btn_setup(final TextView textView,final String parameter) {
        final EditText dialog_ET=(EditText)changeTextview_Dialog.findViewById(R.id.dialog_TextView_Account_page);

        Button change_btn=(Button) changeTextview_Dialog.findViewById(R.id.change_btn_Account_Dialog);

        change_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String Add_this=dialog_ET.getText().toString();
                textView.setText(Add_this);

                //textView_pressed_setup(email_TV,"email");
                if( parameter.equals("email"))
                {Official_Current_User.setEmail(Add_this);}

                if( parameter.equals("reading_SAT"))
                {Official_Current_User.setReading_SAT(Add_this);}

                if( parameter.equals("math_SAT"))
                {Official_Current_User.setMath_SAT(Add_this);}

                if( parameter.equals("password"))
                {Official_Current_User.setPassword(Add_this);}



                reff= FirebaseDatabase.getInstance().getReference()
                        .child("AllUsers")
                        .child(Official_Current_User.getUsername());
                reff.child(parameter).setValue(Add_this);

                changeTextview_Dialog.hide();


            }
        });








    }


    private void cancel_btn_setup(){
        Button cancel_btn=(Button)changeTextview_Dialog.findViewById(R.id.cancel_btn_Account_Dialog);

        cancel_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                changeTextview_Dialog.hide();

            }
        });



    }


}
