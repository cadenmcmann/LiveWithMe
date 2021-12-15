package com.example.livewithme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        RelativeLayout relativeLayout = view.findViewById(R.id.HomeLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

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




//package com.example.livewithme;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//
//public class HomeFragment extends Fragment {
//    private RecyclerView recyclerView;
//    GroupAdapter adapter;
//    DatabaseReference ref;
//
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.fragment_home);
//
//    }
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        SharedPreferences preferences = getActivity().getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
//
//        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
//
//        ref = FirebaseDatabase.getInstance().getReference();
//
//        DatabaseReference userGroupRef = ref.child("Users").child(preferences.getString("username", "")).child("Group");
//        ValueEventListener userGroupListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String groupName = snapshot.getValue().toString();
//                preferences.edit().putString("userGroupName", groupName).apply();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.i("error", ", really");
//
//            }
//        };
//
//        ref.addValueEventListener(userGroupListener);
//
//        String groupName = preferences.getString("username", "");
//
////        DatabaseReference newRef = FirebaseDatabase.getInstance().getReference();
////
////        newRef =  ref.child("Groups").child(groupName).child("Members");
////        ValueEventListener newListener  = new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                JSONObject[] groupMembers = (JSONObject[]) snapshot.getValue();
////                preferences.edit().putString("groupMembers", groupMembers.toString()).apply();
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                Log.i("error", ", really");
////
////            }
////        };
//
//        DatabaseReference membersReference = ref.child("Groups").child(groupName).child("Members");
//
//        recyclerView =  homeView.findViewById(R.id.recycler_view);
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//
//        FirebaseRecyclerOptions<GroupMemberCard> options
//                = new FirebaseRecyclerOptions.Builder<GroupMemberCard>()
//                .setQuery(membersReference, GroupMemberCard.class)
//                .build();
//
//        adapter = new GroupAdapter(options);
//
//        recyclerView.setLayoutManager(
//                manager);
//        recyclerView.setAdapter(adapter);
//
//
//        View view =  inflater.inflate(R.layout.fragment_home, container, false);
//
//        FloatingActionButton button = homeView.findViewById(R.id.addGroupButton);
//
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                moveToNewActivity();
//            }
//        });
//
//        FloatingActionButton button1 = homeView.findViewById(R.id.joinGroupButton);
//        button1.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                moveToNewActivity2();
//            }
//        });
//
//        return view;
//    }
//
//    public void onStart(){
//        super.onStart();
//        adapter.startListening();
//    }
//
//    // Function to tell the app to stop getting
//    // data from database on stopping of the activity
//    public void onStop(){
//        super.onStop();
//        adapter.stopListening();
//    }
//
//    private void moveToNewActivity () {
//        Add_Button add_button = new Add_Button();
//        add_button.show(getParentFragmentManager(), "add group dialog");
//    }
//
//    private void moveToNewActivity2 () {
//        Join_Button join_button = new Join_Button();
//        join_button.show(getParentFragmentManager(), "add group dialog");
//    }
//
//
//
//}