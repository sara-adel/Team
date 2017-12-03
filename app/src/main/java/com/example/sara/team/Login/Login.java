package com.example.sara.team.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sara.team.R;
import com.example.sara.team.profile.Home;
import com.example.sara.team.profile.Profile;
import com.example.sara.team.profile.RequestTask;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText edit_email, edit_pass;
    Button login;
    ProgressDialog progressDialog ;

    String email, pass;
    public static String data_image ,data_name ,data_field ,data_country ,data_overview ,data_email ,data_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        edit_email = (EditText) findViewById(R.id.email_log);
        edit_pass = (EditText) findViewById(R.id.pass_log);
        login = (Button) findViewById(R.id.login);

        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = edit_email.getText().toString();
                pass = edit_pass.getText().toString();

                if(email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Login.this,"Email or password must be filled",Toast.LENGTH_LONG).show();
                }

                else if ( !email.isEmpty() && !pass.isEmpty()) {
                    new LoginTask().execute(email, pass);
                }

            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////// Login Request ////////////////////////////////////////////////////

    public class LoginTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

                RequestTask task = new RequestTask();

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", params[0])
                        .appendQueryParameter("pass", params[1]);
                String query = builder.build().getEncodedQuery();

                String result = task.PostData("http://lahonda-egy.com/api/login" , query);
                return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("parsing", result);
            progressDialog.dismiss();
            try {
                //
                JSONObject ob = new JSONObject(result);
                int success = ob.getInt("success");

                if (success == 0){
                    Toast.makeText(Login.this, "Please,Check your email or password", Toast.LENGTH_SHORT).show();

                } else if (success == 1) {

                    //get email and other data and but in bundle to display it in profile
                    JSONObject data = ob.getJSONObject("data");

                    data_image = data.getString("image");
                    data_name = data.getString("name");
                    data_field = data.optString("field");
                    data_country = data.optString("country");
                    data_overview = data.getString("overview");
                    data_email = data.getString("email");
                    data_pass = data.getString("pass");

                    Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}