package com.example.livewithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    public NavigationBarView bottomNavBar;
    final Context context = this;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavBar = findViewById(R.id.bottomnav);
        bottomNavBar.setOnItemSelectedListener(bottomNavFunction);


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