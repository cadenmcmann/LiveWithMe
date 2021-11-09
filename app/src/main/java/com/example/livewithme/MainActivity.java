package com.example.livewithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    public NavigationBarView bottomNavBar;


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
    }

    public NavigationBarView.OnItemSelectedListener bottomNavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    goToActivityHome();
                    break;
                case R.id.calendar:
                    goToActivitySchedule();
                    break;
                case R.id.finance:
                    goToActivityFinance();
                    break;
                case R.id.settings:
                    goToActivitySettings();
                    break;
            }
            return true;
        }
    };


}