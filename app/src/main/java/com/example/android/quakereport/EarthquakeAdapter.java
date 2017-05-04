package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by Sudhanshu on 04-05-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private double mMagnitude;
    private String mPrimaryLocation;
    private String mOffset;
    private long mDate;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        //Calling the default constructor to initialize
        super(context, 0, earthquakes);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake quake = getItem(position);

        //Getting the respective views
        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView location = (TextView) listItemView.findViewById(R.id.location);
        TextView offset = (TextView) listItemView.findViewById(R.id.offset);
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView time = (TextView) listItemView.findViewById(R.id.time);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(quake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        //Setting the items
        mMagnitude = quake.getMagnitude();
        mDate = quake.getDate();
        String place = quake.getLocation();

        //Splitting location in two parts
        String[] places = place.split("of ");
        if (places.length == 1) {
            mOffset = getContext().getString(R.string.near_the);
            mPrimaryLocation = place;
        } else {
            mOffset = places[0] + "of";
            mPrimaryLocation = places[1];
        }

        Date d = new Date(mDate);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD,yyyy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");

        DecimalFormat decimalFormatter = new DecimalFormat("0.0");

        magnitude.setText(decimalFormatter.format(mMagnitude));
        location.setText(mPrimaryLocation);
        offset.setText(mOffset);
        date.setText(dateFormatter.format(d));
        time.setText(timeFormatter.format(d));

        return listItemView;

    }
}
