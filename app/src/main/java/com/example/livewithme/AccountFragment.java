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

    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myReff = db.getReference();
        myReff.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.

                Map<Object, String> map = (Map<Object, String>) snapshot.getValue();
                String jj = (String) map.get("Cassius");
                System.out.println("jj");
                System.out.println(jj);
                System.out.println("jj");


                // after getting the value we are setting
                // our value to our text view in below line.

                ////TextView test = (TextView) getView().findViewById(R.id.textView11);


                //Object object = data2.getValue(Object.class);
                //String see = (String) object;

                ////test.setText("jj");

                //retrieveTV.setText(value);
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

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference();
        //String s = myRef.child("users").child("caden").child("nickname").get().getResult().getValue(String.class);
        Task<DataSnapshot> data = myRef.child("users").child("username").get();
        while ((!data.isComplete()));
        DataSnapshot data2 = data.getResult();
        //Object ss = data2.getValue();

        //data2.getValue()
        //String s = (String) data2.getValue(String.class);
        TextView test = (TextView) getView().findViewById(R.id.textView11);
        getdata();


        //Object object = data2.getValue(Object.class);
        //String see = (String) object;
        //test.setText(ss.toString());







        // Attach a listener to read the data at our posts reference

        /*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post = dataSnapshot.getValue(String.class);
                //System.out.println(post);
                test.setText(post);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

         */


    }
}