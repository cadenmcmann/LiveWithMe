package com.example.livewithme;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Join_Button extends AppCompatDialogFragment {
    DatabaseReference databaseReference;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final EditText joinButtonLayout = (EditText) getLayoutInflater().inflate(R.layout.activity_join_button, null);
        EditText joinGroupInput = joinButtonLayout.findViewById(R.id.joinGroup);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder
                .setTitle("Join Group")
                .setView(joinButtonLayout)
                .setPositiveButton("Join",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        Editable check = joinGroupInput.getText();
                        Query query = databaseReference.orderByValue().equalTo(String.valueOf(check));

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

