package com.example.college_navigator_10.Data_Management_Colleges.User_Data;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.college_navigator_10.Data_Management_Colleges.College;

import java.util.ArrayList;

public class User extends ArrayList{
    String username,password,email,Math_SAT,Reading_SAT;

    public static ArrayList<College> likedCollege=new ArrayList<>();

//    @Override
//    public int indexOf(@Nullable Object o) {
//        Log.d("Working","/////////////////");
//        College mycollege=(College) o;
//        Log.d("Working","/////////////////");
//        Log.d("My college name=",mycollege.getSchoolname()+"/////////////////");
//        int i=0;
//        for(  i=0;i<likedCollege.size();i++ ){
//            College currentcollege=likedCollege.get(i);
//            Log.d("i=",i+"/////////////////");
//            Log.d("current college name=",currentcollege.getSchoolname()+"/////////////////");
//
//
//
//            if(mycollege.getSchoolname().equals(currentcollege.getSchoolname())){
//                return super.indexOf(currentcollege);
//            }
//        }
//
//        return super.indexOf(o);
//    }




    public User(){
        likedCollege=new ArrayList<>();

    }

    public static ArrayList<College> getlikedCollege() {
        return likedCollege;
    }

    public static void setlikedCollege(ArrayList<College> likedCollege) {
        User.likedCollege = likedCollege;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMath_SAT() {
        return Math_SAT;
    }

    public void setMath_SAT(String math_SAT) {
        Math_SAT = math_SAT;
    }

    public String getReading_SAT() {
        return Reading_SAT;
    }

    public void setReading_SAT(String reading_SAT) {
        Reading_SAT = reading_SAT;
    }

    public void LogArrayList(){
        int i=0;
        Log.d("All Colleges LOG","|||||||||||||||||||||||||||||||||||||||||||||||||");

        for(College college:likedCollege){

            Log.d("College "+i,"  "+college.getSchoolname()+"///////////////////////////");
            i++;
        }

    }
}
