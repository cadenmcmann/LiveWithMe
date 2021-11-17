package com.example.livewithme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

public class SettingsFragment extends Fragment {

    Button button = null;
    Switch dark;



    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dark = (Switch) this.getActivity().findViewById(R.id.switch2);

        //dark.setOnClickListener(
        //        new View.OnClickListener() {
        //            @Override
         //           public void onClick(View view) {
         //               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
          //          }
         //       }
       // );


      //  SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
      //  final SharedPreferences.Editor editor = sharedPreferences.edit();
      //  final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

     //  if (isDarkModeOn) {

       //     dark.setChecked(true);
        //    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

     //   } else {

     //   }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}