package com.example.sara.team.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sara.team.Login.Login;
import com.example.sara.team.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.security.AccessController.getContext;

/**
 * Created by Sara on 6/30/2017.
 */
public class Profile extends Fragment {
    CircleImageView image;
    TextView user ,country , field , overview , contact ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile , container , false);

        image = (CircleImageView) view.findViewById(R.id.pro_image);
        user = (TextView) view.findViewById(R.id.pro_user);
        country =(TextView) view.findViewById(R.id.pro_country);
        field =(TextView)  view.findViewById(R.id.pro_field);
        overview =(TextView)  view.findViewById(R.id.pro_overview);
        contact =(TextView)  view.findViewById(R.id.pro_contact);

        Picasso.with(getContext()).load("http://lahonda-egy.com/" +Login.data_image).into(image);
        user.setText(Login.data_name);
        field.setText(Login.data_field);
        country.setText(Login.data_country);
        overview.setText(Login.data_overview);
        contact.setText(Login.data_email);


        return view;

    }
}
