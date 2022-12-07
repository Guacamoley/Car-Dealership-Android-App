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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class VehicleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();

        // Get current dealership selected
        Dealership dealership = CarDealerApplication.selectedDealer;

        //Set title
        setTitle(dealership.getName());

        //Set edit title text box
        EditText editTitle = findViewById(R.id.title2);
        editTitle.setVisibility(View.GONE);

        //Set Edit Button
        Button editButton = findViewById(R.id.editNameBtn);

        //Set Save Button
        Button saveButton = findViewById(R.id.button7);
        saveButton.setVisibility(View.GONE);


        // Create an adapter for the list view
        // TODO: hardcoded to show dealer "485". not sure how to retrieve the dealerId that was clicked.
        String dealerIdSelected = dealership.getDealershipId();
        VehicleAdapter adapter = new VehicleAdapter(this, app.getVehicleList(dealerIdSelected));

        // Find the list view and add the adapter
        ListView vehicleList = findViewById(R.id.vehicle_list);
        vehicleList.setAdapter(adapter);

        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CarDealerApplication.selectedCar = adapter.getItem((int)id);
                Intent intent = new Intent (VehicleListActivity.this, VehicleActivity.class );
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTitle.setVisibility(View.VISIBLE);
                editTitle.setText(dealership.getName());
                saveButton.setVisibility(View.VISIBLE);
                vehicleList.setVisibility(View.GONE);

            }

        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTitle.getWindowToken(), 0);
                String temp = editTitle.getText().toString();
                dealership.setName(temp);
                adapter.notifyDataSetChanged();
                saveButton.setVisibility(View.GONE);
                editTitle.setVisibility(View.GONE);
                vehicleList.setVisibility(View.VISIBLE);
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dealer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.switch1:
                Toast.makeText(this, "Switch turned", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}