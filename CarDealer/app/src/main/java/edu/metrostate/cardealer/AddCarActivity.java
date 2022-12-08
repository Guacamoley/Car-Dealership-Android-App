package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Button submit = findViewById(R.id.submitButton);



        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                EditText dealerIDEditText = (EditText) findViewById(R.id.dealer_id);
                String dealerID = dealerIDEditText.getText().toString();

                EditText manufacturerEditText = (EditText) findViewById(R.id.manufacturer);
                String manufacturer = manufacturerEditText.getText().toString();

                EditText typeEditText = (EditText) findViewById(R.id.vehicle_type);
                String type = typeEditText.getText().toString();

                EditText vehicleModelEditText = (EditText) findViewById(R.id.model);
                String vehicleModel = vehicleModelEditText.getText().toString();

                EditText vehicleIDEditText = (EditText) findViewById(R.id.vehicle_id);
                String vehicleID = vehicleIDEditText.getText().toString();

                EditText vehiclePriceEditText = (EditText) findViewById(R.id.vehicle_price);
                double vehiclePrice = Double.parseDouble(vehiclePriceEditText.getText().toString());

                EditText dealerNameEditText = (EditText) findViewById(R.id.dealer_name);
                String dealerName = dealerNameEditText.getText().toString();

                EditText currencyEditText = (EditText) findViewById(R.id.currency);
                String currency = currencyEditText.getText().toString();


                app.INVENTORY.addIncomingVehicle(new Car(dealerID, type, manufacturer, vehicleModel, vehicleID, vehiclePrice,10, dealerName, currency, false));
                Intent intent = new Intent (AddCarActivity.this, VehicleListActivity.class );
                startActivity(intent);
            }

        });

    }


}