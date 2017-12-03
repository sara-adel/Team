package com.example.sara.team.profile;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sara.team.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfile extends AppCompatActivity {

    CircleImageView image;
    TextView user ,country , field , overview , contact ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        image = (CircleImageView) findViewById(R.id.view_image);
        user = (TextView) findViewById(R.id.view_user);
        country = (TextView) findViewById(R.id.view_country);
        field = (TextView) findViewById(R.id.view_field);
        overview = (TextView) findViewById(R.id.view_overview);
        contact = (TextView) findViewById(R.id.view_contact);


        String email = getIntent().getStringExtra("email");

        new GetViewProfileData().execute(email);

        contact.setText(email);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////// Get Data Request ////////////////////////////////////////////////////

    class GetViewProfileData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            RequestTask task = new RequestTask();

            Uri builtUri = Uri.parse("http://lahonda-egy.com/team/public/api/user-profile/" + params[0])
                    .buildUpon().build();

            String link = builtUri.toString();

            String result = task.getData(link);
            //  Log.e("views", result);
            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("view", s);

            try {
                JSONObject obj = new JSONObject(s);
                JSONObject data = obj.getJSONObject("results");

                // get data from json
                String data_image = data.getString("image");
                String data_name = data.getString("name");
                String data_field = data.getString("field");
                String data_country = data.getString("country");
                String data_overview = data.getString("overview");

                // put data in view profile
                Picasso.with(ViewProfile.this).load(data_image).into(image);
                user.setText(data_name);
                field.setText(data_field);
                country.setText(data_country);
                overview.setText(data_overview);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
