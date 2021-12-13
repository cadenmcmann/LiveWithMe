package com.example.livewithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public NavigationBarView bottomNavBar;
    public SharedPreferences preferences;


    // The navbar we are using navigates between fragments, not activites. Currently
    // the functions below just act as 'filler' functions so that the navbar has onClick functions
    // and doesn't throw an error. In reality, they do nothing
    // After fragments are built, the functions need to be reconfigured to display their
    // corresponding fragment

    // Note: After fragments are built, we will no longer need the following files, as they will
    // be replaced by their corresponding fragments
    //     // Activities:
    //              // FinanceActivity, ScheduleActivity, Settings Activity
    //     // Layout.xml
    //              // activity_finance.xml, activity_schedule.xml, activity_settings.xml



    // COMMIT 2: I changed all the activities into fragments. I left the original activities
    // here just incase


    public void goToActivityHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToActivityFinance(){
        Intent intent = new Intent(this, FinanceActivity.class);
        startActivity(intent);
    }


    public void goToActivitySchedule(){
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    public void goToActivitySettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void clickAccount(View view){


        AccountFragment fragment= new AccountFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, "account")
                .addToBackStack(null)
                .commit();


    }

    public void clickSettings(View view){


        SettingsFragment fragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, "settings")
                .addToBackStack(null)
                .commit();


    }

    public void darkMode(View view) {

        Switch s =  findViewById(R.id.switch2);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("scheck", false);

        if (s.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editor.putBoolean("scheck", s.isChecked());
            editor.commit();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            editor.putBoolean("scheck", s.isChecked());
            editor.commit();
        }


    }

    public void setNotif(View view) {
        Switch s =  findViewById(R.id.switch3);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("notecheck", false);

        if (s.isChecked()) {


            // EDIT THE NOTIFICATIONS HERE



            editor.putBoolean("notecheck", s.isChecked());
            editor.commit();
        } else {

            // EDIT THE NOTIFICATIONS HERE

            editor.putBoolean("notecheck", s.isChecked());
            editor.commit();
        }

    }

    public void logOut(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", "").apply();
        Intent intent = new Intent(this, LoginAct.class);
        startActivity(intent);
    }

    public void applyUser(View view){

        EditText myTextField = (EditText) findViewById(R.id.editTextTextPersonName);
        String user = myTextField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", user).apply();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference();
        preferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        String pass = preferences.getString("password", "");
        //myRef.child("Users").child(user).setValue(pass);

        myRef.child("Users").child(user).child("Password").setValue(pass);
        myRef.child("Users").child(user).child("Name").setValue(user);
        myRef.child("Users").child(user).child("Group").setValue("[List of groups]");

    }

    public void showPass(View view){

        TextView text4 = (TextView) findViewById(R.id.textView10);
        preferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        String pass = preferences.getString("password", "");
        text4.setText(pass);

    }


    public void applyPass(View view){

        EditText myTextField = (EditText) findViewById(R.id.editTextTextPersonName3);
        String pass = myTextField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("password", pass).apply();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference();
        preferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        String user = preferences.getString("username", "");
        //myRef.child("Users").child(user).setValue(pass);

        myRef.child("Users").child(user).child("Password").setValue(pass);
        myRef.child("Users").child(user).child("Name").setValue(user);
        myRef.child("Users").child(user).child("Group").setValue("[List of groups]");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("username", "").equals("")) {

            Intent intent = new Intent(this, LoginAct.class);
            startActivity(intent);
        }


        bottomNavBar = findViewById(R.id.bottomnav);
        bottomNavBar.setOnItemSelectedListener(bottomNavFunction);
        preferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);

        boolean isNightMode = preferences.getBoolean("scheck", false);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }



    }

    public NavigationBarView.OnItemSelectedListener bottomNavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    //goToActivityHome();
                    fragment = new HomeFragment();
                    break;
                case R.id.calendar:
                    fragment = new ScheduleFragment();
                    //goToActivitySchedule();
                    break;
                case R.id.finance:
                    fragment = new FinanceFragment();
                    //goToActivityFinance();
                    break;
                case R.id.settings:
                    fragment = new SettingsFragment();
                    //goToActivitySettings();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };


}