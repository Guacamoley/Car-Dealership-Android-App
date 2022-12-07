package edu.metrostate.cardealer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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

public class VehicleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();

        // Get current dealership selected
        String dealerId = getIntent().getStringExtra("dealerId");
        Dealership dealership = CarDealerApplication.INVENTORY.getDealerById(dealerId);

        // Set title
        TextView title = findViewById(R.id.title);
        title.setText(dealership.getName());

        // export button, saves json to a single hardcoded location
        Button exportButton = findViewById(R.id.button_export);
        exportButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = "test2.json";
                // saves files to the shared public documents directory
                String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();

                // this is the app-specific external storage. the importer has trouble finding files here for some reason.
                //String dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();

                CarDealerApplication.INVENTORY.exportFile(dealerId, dir + "/" + fileName);
            }
        });

        // Create an adapter for the list view
        VehicleAdapter adapter = new VehicleAdapter(this, app.getVehicleList(dealerId));

        // Find the list view and add the adapter
        ListView vehicleList = findViewById(R.id.vehicle_list);
        vehicleList.setAdapter(adapter);

        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (VehicleListActivity.this, VehicleActivity.class );
                // pass dealerId and vehicleId to the next screen
                intent.putExtra("dealerId", dealerId);
                intent.putExtra("vehicleId", adapter.getItem((int)id).getVehicle_id());
                startActivity(intent);
            }
        });
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