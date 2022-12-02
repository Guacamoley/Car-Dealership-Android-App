package edu.metrostate.cardealer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class VehicleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();

        // Create an adapter for the list view
        VehicleAdapter adapter = new VehicleAdapter(this, app.getVehicleList());

        // Find the list view and add the adapter
        ListView vehicleList = findViewById(R.id.vehicle_list);
        vehicleList.setAdapter(adapter);

        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent (VehicleListActivity.this, VehicleActivity.class );
               startActivity(intent);
            }
        });


    }

    public void showDialog(Vehicle vehicle) {

        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("My alert")
                .setCancelable(false)
                .setTitle("Vehicle ID: " + vehicle.getId())
                .setMessage("Model: " + vehicle.getModel())
                .setPositiveButton( "OK", (dialog1, id) -> dialog1.dismiss()).create();

        dialog.show();



    }
}