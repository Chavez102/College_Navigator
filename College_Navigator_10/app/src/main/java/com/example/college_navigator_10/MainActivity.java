package com.example.college_navigator_10;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
public static Dialog logIn_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.welcome_page);

        Button letsGo_Btn=(Button)findViewById(R.id.LetsGo_btn);



        logIn_popup=new Dialog(this);

        letsGo_Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                logIn_popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                logIn_popup.setContentView(R.layout.log_in_popup);
                logIn_popup.show();


                Button login_Btn=(Button)logIn_popup.findViewById(R.id.login_btn);
                Button guest_Btn=(Button)logIn_popup.findViewById(R.id.Guest_btn);




                login_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddingMenu();
                        logIn_popup.hide();
                    }
                });









            }
        });
    }

    public void AddingMenu(){
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

}
