package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class transferCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_car);
        Button submit = findViewById(R.id.submitButton2);

        CarDealerApplication app = (CarDealerApplication) getApplication();
        String currentDealer = getIntent().getStringExtra("currentDealerId");
        String carId = getIntent().getStringExtra("vehicleId");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText dealerIDEditText = (EditText) findViewById(R.id.dealerID);
                String newDealerId = dealerIDEditText.getText().toString();

                CarDealerApplication.INVENTORY.transferCar(currentDealer, newDealerId, carId);

                Intent intent = new Intent(transferCarActivity.this, VehicleListActivity.class);
                startActivity(intent);
            }
        });
    }
}