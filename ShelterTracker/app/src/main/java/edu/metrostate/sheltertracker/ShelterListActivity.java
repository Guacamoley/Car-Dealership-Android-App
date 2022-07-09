package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ShelterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        ListView lv = findViewById(R.id.shelter_list);

        lv.setAdapter(new ShelterAdapter(this,
                ((ShelterTrackerApplication)getApplication()).getShelterList()));

    }
}