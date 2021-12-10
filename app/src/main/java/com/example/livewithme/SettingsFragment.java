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


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = getActivity().getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        boolean isNightMode = preferences.getBoolean("scheck", false);
        if (isNightMode) {
            Switch swit = (Switch) getView().findViewById(R.id.switch2);
            swit.setChecked(true);
        }

        boolean notes = preferences.getBoolean("notecheck", false);
        if (notes) {
            Switch swit = (Switch) getView().findViewById(R.id.switch3);
            swit.setChecked(true);
        }

    }
}