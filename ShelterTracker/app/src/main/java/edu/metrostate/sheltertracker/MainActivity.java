package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showShelterList(View view) {
        Intent intent = new Intent(this, ShelterListActivity.class);
        startActivity(intent);
    }

    public void showDialog(View view) {

        Dialog dialog = new AlertDialog.Builder(this).setTitle("My alert").setCancelable(false)
                .setMessage("This is an alert that is shown when the button is pressed")
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        }).create();

        dialog.show();



    }
}