package edu.metrostate.cardealer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

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
                // pass dealerId to the next screen
                intent.putExtra("dealerId", ad.getItem((int)l).getDealershipId());
                intent.putExtra("dealer position", i);
                startActivity(intent);
            }
        });

        // export all button
        Button exportAllButton = findViewById(R.id.button_export_all);
        exportAllButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: see export button inside the VehicleListActivity.java class
            }
        });

        // import button
        Button importButton = findViewById(R.id.button_import);
        importButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this shows a file selector for all file types
                // which ensures that json/xml files will appear
                mGetContent.launch("*/*");
            }
        });
    }

    // listener for import button
    // ref: https://developer.android.com/training/basics/intents/result#java
    // ref: https://stackoverflow.com/questions/45520599/creating-file-from-uri
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // Handle the returned Uri
                    if (uri != null) {
                        try {
                            // for security reasons, android will not return the file path
                            // it only returns a uri. so, we read the uri into a temp file
                            // and then pass the temp file to the inventory.importFile method.

                            // TODO: at this point, we'll need to check if the file type is json or xml and make the appropriate changes
                            // this extracts the file type from the uri
                            System.out.println(uri);

                            String fileName = uri.getLastPathSegment();

                            System.out.println(fileName);

                            String[] nameSegments = fileName.split(".");
                            int numSegments = nameSegments.length;
                            String fileType = nameSegments[numSegments - 1];
                            System.out.println(fileType);

                            fileType = "json";


                            InputStream in = getContentResolver().openInputStream(uri);
                            File tempFile = File.createTempFile("temp.json", null, getCacheDir());
                            OutputStream out = new FileOutputStream(tempFile);

                            byte[] buf = new byte[1024];
                            int len;
                            while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }
                            out.close();
                            in.close();

                            CarDealerApplication.INVENTORY.importFile(tempFile, fileType);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

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
            case R.id.switch1:

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}