package com.example.sara.team.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.sara.team.Login.Login;
import com.example.sara.team.Model.SearchAdapter;
import com.example.sara.team.Model.SearchModel;
import com.example.sara.team.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sara on 6/30/2017.
 */
public class Search extends Fragment {

    EditText search_name;
    ImageButton imageButton;
    ListView list_search;

    String user_search;
    public static String data_image ,data_name ,data_country ,data_email;

    public static ArrayList<SearchModel> data ;
    public static SearchAdapter searchAdapter;
    SearchModel search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search , container ,false);

        search_name = (EditText) view.findViewById(R.id.search);
        imageButton = (ImageButton) view .findViewById(R.id.search_icon);
        list_search = (ListView) view.findViewById(R.id.list_search);

        searchAdapter = new SearchAdapter(getContext());

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_search = search_name.getText().toString();

                if (!user_search.isEmpty()){
                    new SearchTask().execute(user_search);
                }
                search_name.setText("");
            }
        });

        list_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String email = data.get(position).getEmail();
//                if (email.equals(Login.data_email)){
//                    Intent m = new Intent(getContext() , Profile.class);
//                    startActivity(m);
//
//                }else {
                    Intent i = new Intent(getContext(), ViewProfile.class);
                    i.putExtra("email", email);
                    startActivity(i);
                //}
            }
        });

        return view;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////// Search Request /////////////////////////////////////////////////////////////

    public class SearchTask extends AsyncTask<String, String,String > {
        @Override
        protected String doInBackground(String... params) {
            RequestTask task = new RequestTask();
            // Append parameters to URL
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("name", params[0]);
            String query = builder.build().getEncodedQuery();

            String result = task.PostData("http://lahonda-egy.com/api/search", query);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("search", result);
            data = new ArrayList<>();

            try {
                JSONObject ob = new JSONObject(result);
                int success = ob.getInt("success");
                JSONArray array = ob.getJSONArray("data");

                for (int i = 0 ; i < array.length() ; i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    data_image = jsonObject.getString("image");
                    data_name = jsonObject.getString("name");
                    data_country = jsonObject.getString("country");
                    data_email = jsonObject.getString("email");

                    search = new SearchModel(data_image ,data_name ,data_country,data_email );
                    data.add(search);
                }
                //Log.e("res2", data.toString());

                searchAdapter.clear();
                for(SearchModel searchModel : data){
                    searchAdapter.add(searchModel);
                }
                searchAdapter.notifyDataSetChanged();

                if (success == 0) {
                    Toast.makeText(getContext(), "No Data Found ", Toast.LENGTH_LONG).show();
                } else if (success == 1) {
                    list_search.setAdapter(searchAdapter);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
