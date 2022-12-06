package edu.metrostate.cardealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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
        String dealerId = getIntent().getStringExtra("dealerId");
        String vehicleId = getIntent().getStringExtra("vehicleId");
        Car myCar = CarDealerApplication.INVENTORY.getCarById(dealerId, vehicleId);

        // setup data view
        TextView carData = findViewById(R.id.car_data);
        carData.setText(myCar.toString());

        // setup title
        TextView carTitle = findViewById(R.id.car_title);
        carTitle.setText("Car ID " + myCar.getId());

        // setup buttons
        Button transferButton = findViewById(R.id.button_transfer);
        transferButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarDealerApplication.INVENTORY.addIncomingVehicle(myCar);
            }
        });

        Button deleteButton = findViewById(R.id.button_delete_car);
        deleteButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarDealerApplication.INVENTORY.removeIncomingVehicle(myCar);
            }
        });
    }

    @Override
    public void onBackPressed() {


        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item 3 Selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}