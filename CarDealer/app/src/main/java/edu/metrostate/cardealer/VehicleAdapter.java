package edu.metrostate.cardealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class VehicleAdapter extends ArrayAdapter<Car> {
    public VehicleAdapter(Context context, List<Car> shelterList) {
        super(context, R.layout.vehicle_item, shelterList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_item, parent, false);
        }

        TextView id = convertView.findViewById(R.id.vehicle_id);
        TextView model = convertView.findViewById(R.id.vehicle_model);


        id.setText(getItem(position).getId());
        model.setText(getItem(position).getModel());

        return convertView;
    }
}
