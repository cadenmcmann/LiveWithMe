package com.example.livewithme;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Join_Button extends AppCompatDialogFragment {
    DatabaseReference databaseReference;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SharedPreferences preferences = getActivity().getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        final View joinButtonLayout = getLayoutInflater().inflate(R.layout.activity_join_button, null);
        EditText joinGroupInput = joinButtonLayout.findViewById(R.id.joinGroup);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog.Builder noGroup = new AlertDialog.Builder(getActivity());

        builder
                .setTitle("Join Group")
                .setView(joinButtonLayout)
                .setPositiveButton("Join",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        String check = joinGroupInput.getText().toString();
                        Query query = databaseReference.orderByValue().equalTo(String.valueOf(check));

                        DatabaseReference groupRef = databaseReference.child("Groups").child(check.toString());
                        ValueEventListener eventListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (!snapshot.exists()) {
                                    // alert to user that it does not exist
                                    noGroup.setMessage("This is not a group!");
                                    noGroup.create();
                                    noGroup.show();
                                }
                                else {
                                    // do work to make user join group
                                    String user = preferences.getString("username", "");
                                    String pass = preferences.getString("password", "");

                                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = db.getReference();
                                    myRef.child("Users").child(user).child("Password").setValue(pass);
                                    myRef.child("Users").child(user).child("Name").setValue(user);
                                    myRef.child("Users").child(user).child("Group").setValue(check.toString());

                                    Task<DataSnapshot> data = myRef.child("Groups").child(check).child("Members").get();
                                    while ((!data.isComplete()));
                                    DataSnapshot data2 = data.getResult();
                                    Long numChild = data2.getChildrenCount();
                                    int num = numChild.intValue();
                                    String gg = Integer.toString(num);

                                    myRef.child("Groups").child(check).child("Members").child(gg).setValue(user);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d("errorTag", error.getMessage());
                            }
                        };
                        groupRef.addListenerForSingleValueEvent(eventListener);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder.create();

    }

}

//package com.example.livewithme;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.text.Editable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//
//import androidx.appcompat.app.AppCompatDialogFragment;
//import androidx.fragment.app.DialogFragment;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//
//public class Join_Button extends AppCompatDialogFragment {
//    DatabaseReference databaseReference;
//
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        final View joinButtonLayout = getLayoutInflater().inflate(R.layout.activity_join_button, null);
//        EditText joinGroupInput = joinButtonLayout.findViewById(R.id.joinGroup);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        builder
//                .setTitle("Join Group")
//                .setView(joinButtonLayout)
//                .setPositiveButton("Join",  new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        databaseReference = FirebaseDatabase.getInstance().getReference();
//                        Editable check = joinGroupInput.getText();
//                        Query query = databaseReference.orderByValue().equalTo(String.valueOf(check));
//
//                    }
//                })
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//        return builder.create();
//
//    }
//
//}
//
