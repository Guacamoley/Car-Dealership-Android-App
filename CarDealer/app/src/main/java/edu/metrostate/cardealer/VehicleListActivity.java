package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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
        ((ListView)findViewById(R.id.vehicle_list)).setAdapter(adapter);


    }
}