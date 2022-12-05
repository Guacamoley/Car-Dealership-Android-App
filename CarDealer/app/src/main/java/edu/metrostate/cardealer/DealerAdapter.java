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

public class DealerAdapter extends ArrayAdapter<Dealership> {
    public DealerAdapter(Context context, List<Dealership> shelterList) {
        super(context, R.layout.dealer_item, shelterList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dealer_item, parent, false);
        }

        TextView id = convertView.findViewById(R.id.dealer_name);


        id.setText("Dealer " + getItem(position).getName());


        return convertView;
    }
}