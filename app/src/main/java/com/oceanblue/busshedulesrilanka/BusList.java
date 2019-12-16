package com.oceanblue.busshedulesrilanka;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BusList extends ArrayAdapter<Bus> {

    private Activity context;
    private List<Bus> busList;

    public BusList(Activity context, List<Bus> busList)
    {
        super(context, R.layout.list_layout, busList);
        this.context = context;
        this.busList = busList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem  = inflater.inflate(R.layout.list_layout, null, true);

        TextView lblBusNo = listViewItem.findViewById(R.id.lblBusNoRoot);
        TextView lblBusStartimeRoot = listViewItem.findViewById(R.id.lblStratTimeRoot);
        TextView lblBusEndTimeRoot = listViewItem.findViewById(R.id.lblEndtimeRoot);
        TextView lblBusStartPlaceRoot = listViewItem.findViewById(R.id.lblstartPlaceRoot);
        TextView lblBusEndPlaceRoot = listViewItem.findViewById(R.id.lblEndPlaceRoot);

        Bus bus = busList.get(position);

        lblBusNo.setText(bus.getBusNumber());
        lblBusStartimeRoot.setText(bus.getStartTime());
        lblBusEndTimeRoot.setText(bus.getEndTimee());
        lblBusStartPlaceRoot.setText(bus.getStartPlace());
        lblBusEndPlaceRoot.setText(bus.getEndPlace());

        return listViewItem;

    }
}
