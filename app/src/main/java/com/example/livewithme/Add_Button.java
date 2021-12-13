package com.example.livewithme;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Add_Button extends AppCompatDialogFragment {
    DatabaseReference databaseReference;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View addButtonLayout = getLayoutInflater().inflate(R.layout.activity_add_button, null);
        EditText groupNameInput = addButtonLayout.findViewById(R.id.groupName);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder
                .setTitle("Group Name")
                .setView(addButtonLayout)
                .setPositiveButton("Add Group",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        String groupName = groupNameInput.getText().toString();
//                        databaseReference.child("Groups").push().setValue(groupNameInput.getText().toString());
                        ArrayList<String> members = new ArrayList<String>();
                        members.add("Caden");
                        members.add("Karthik");
                        databaseReference.child("AllGroups").child(groupName).child("Group Name").setValue(groupName);
                        databaseReference.child("AllGroups").child(groupName).child("Group Members").setValue(members);

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

