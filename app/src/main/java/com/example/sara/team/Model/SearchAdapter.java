package com.example.sara.team.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sara.team.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sara on 5/8/2017.
 */
public class SearchAdapter extends ArrayAdapter<SearchModel> {

    public SearchAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_search, parent, false);
        }

        CircleImageView image = (CircleImageView) convertView.findViewById(R.id.img);
        //image.setImageResource();
        Picasso.with(getContext()).load("http://lahonda-egy.com/"+ getItem(position).getImage()).into(image);

        TextView text_name= (TextView) convertView.findViewById(R.id.name_search);
        text_name.setText(getItem(position).getName());

        TextView text_country= (TextView) convertView.findViewById(R.id.country);
        text_country.setText(getItem(position).getCountry());

        return convertView;
    }
}
