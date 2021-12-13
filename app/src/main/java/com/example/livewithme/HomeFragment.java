package com.example.livewithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        Button button = (Button) view.findViewById(R.id.ADD);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                moveToNewActivity();
            }
        });

        Button button1 = (Button) view.findViewById(R.id.JOIN);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                moveToNewActivity2();
            }
        });

        return view;
    }

    private void moveToNewActivity () {
        Add_Button add_button = new Add_Button();
        add_button.show(getParentFragmentManager(), "add group dialog");
    }

    private void moveToNewActivity2 () {
        Join_Button join_button = new Join_Button();
        join_button.show(getParentFragmentManager(), "add group dialog");
    }

}