package edu.metrostate.cardealer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
        setTitle("Car ID " + myCar.getVehicle_id());

        //set up edit screen
        EditText editCar = findViewById(R.id.car_id_edit);
        editCar.setVisibility(View.GONE);

        //set up save button
        Button saveCarId = findViewById(R.id.save_car_id);
        saveCarId.setVisibility(View.GONE);
        saveCarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCar.setVehicle_id(editCar.getText().toString());
                editCar.setVisibility(View.GONE);
                saveCarId.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editCar.getWindowToken(), 0);
                Toast.makeText(VehicleActivity.this, "Vehicle ID updated", Toast.LENGTH_SHORT).show();
            }
        });


        //set up edit button
        Button editButton = findViewById(R.id.button_edit_car);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCar.setText(myCar.getVehicle_id());
                editCar.setVisibility(View.VISIBLE);
                saveCarId.setVisibility(View.VISIBLE);
            }
        });


        // transfer button
        Button transferButton = findViewById(R.id.button_transfer);
        transferButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
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
                myCar.setLoaned(loanedSwitch.isChecked());
            }
        });
    }
}