package com.example.livewithme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

public class AccountFragment extends Fragment {

    public SharedPreferences preferences;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView text3 = (TextView) getView().findViewById(R.id.textView2);
        preferences = getActivity().getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        String user = preferences.getString("username", "");
        text3.setText(user);

        TextView text4 = (TextView) getView().findViewById(R.id.textView10);
        //preferences = getActivity().getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        String pass = preferences.getString("password", "");
        String hiddenPass = "";

        for(int i = 0; i < pass.length(); i++) {
            hiddenPass = hiddenPass + "*";
        }

        text4.setText(hiddenPass);

        if(Event.eventsList.size() > 0) {
            TextView text = (TextView) getView().findViewById(R.id.textView8);
            TextView text2 = (TextView) getView().findViewById(R.id.textView9);

            text.setText(Event.eventsList.get(Event.eventsList.size() - 1).getName());
            String s = String.format("%d",Event.eventsList.size());
            text2.setText(s);
        }

    }
}