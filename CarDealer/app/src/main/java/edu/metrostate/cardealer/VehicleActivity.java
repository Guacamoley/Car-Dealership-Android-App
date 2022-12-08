package edu.metrostate.cardealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

// This is the car details screen
public class VehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        // get extras passed from previous page and lookup car
        String dealerId = getIntent().getStringExtra("dealerId");
        String vehicleId = getIntent().getStringExtra("vehicleId");
        Car myCar = CarDealerApplication.INVENTORY.getCarById(dealerId, vehicleId);

        // setup data view
        TextView carData = findViewById(R.id.car_data);
        carData.setText(myCar.toString());

        // setup title
        TextView carTitle = findViewById(R.id.car_title);
        carTitle.setText("Car ID " + myCar.getVehicle_id());

        // transfer button
        Button transferCarButton = findViewById(R.id.button_transfer);
        transferCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (VehicleActivity.this, transferCarActivity.class );
                intent.putExtra("currentDealerId", dealerId);
                intent.putExtra("vehicleId", vehicleId);
                startActivity(intent);
            }
        });

        // delete button
        Button deleteButton = findViewById(R.id.button_delete_car);
        deleteButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarDealerApplication.INVENTORY.removeIncomingVehicle(myCar);
            }
        });

        // loaned switch
        Switch loanedSwitch = findViewById(R.id.switch_loaned);
        // this sets the switch to the correct state when you first load the page
        loanedSwitch.setChecked(myCar.isLoaned());
        loanedSwitch.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                // i didn't think this would work, but it does
                myCar.setLoaned(loanedSwitch.isChecked());
            }
        });
    }
}