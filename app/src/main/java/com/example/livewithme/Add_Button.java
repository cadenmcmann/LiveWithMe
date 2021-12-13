package com.example.livewithme;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Add_Button extends AppCompatDialogFragment {
    DatabaseReference databaseReference;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SharedPreferences preferences = getActivity().getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);

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
                        String check =  groupNameInput.getText().toString();

                        String user = preferences.getString("username", "");
                        Task<DataSnapshot> data = databaseReference.child("Groups").child(check).child("Members").get();
                        while ((!data.isComplete()));
                        DataSnapshot data2 = data.getResult();
                        Long numChild = data2.getChildrenCount();
                        int num = numChild.intValue();
                        String gg = Integer.toString(num);

                        databaseReference.child("Groups").child(groupName).child("Group Name").setValue(groupName);
                        databaseReference.child("Groups").child(groupName).child("Members").child(gg).setValue(user);

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

