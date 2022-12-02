package edu.metrostate.cardealer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button exportButton;
    Button importButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarDealerApplication app = (CarDealerApplication) getApplication();

        ListView ListViewDealer = findViewById(R.id.dealer_list);

        DealerAdapter ad = new DealerAdapter(this, app.getDealerList());
        ListViewDealer.setAdapter(ad);

      ListViewDealer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent intent = new Intent(MainActivity.this, VehicleListActivity.class);
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