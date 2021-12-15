package com.example.livewithme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

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

    public void getCount(String group){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myReff = db.getReference();

        if (group == null) {
            return;
        }

        Task<DataSnapshot> data = myReff.child("Groups").child(group).child("Expenses").get();
        while ((!data.isComplete()));
        DataSnapshot data2 = data.getResult();
        Long numChild = data2.getChildrenCount();
        int num = numChild.intValue();
        String gg = Integer.toString(num);

        TextView test = (TextView) getView().findViewById(R.id.textView11);
        test.setText(gg);

    }

    public void getGroup(String name){


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myReff = db.getReference();
        myReff.child("Users").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.

                Map<Object, String> map = (Map<Object, String>) snapshot.getValue();
                if (map != null) {
                    String jj = (String) map.get("Group");
                    getCount(jj);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getActivity(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
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

        for (int i = 0; i < pass.length(); i++) {
            hiddenPass = hiddenPass + "*";
        }

        text4.setText(hiddenPass);

        if (Event.eventsList.size() > 0) {
            TextView text = (TextView) getView().findViewById(R.id.textView8);
            TextView text2 = (TextView) getView().findViewById(R.id.textView9);

            text.setText(Event.eventsList.get(Event.eventsList.size() - 1).getName());
            String s = String.format("%d", Event.eventsList.size());
            text2.setText(s);
        }

        getGroup(user);



    }
}