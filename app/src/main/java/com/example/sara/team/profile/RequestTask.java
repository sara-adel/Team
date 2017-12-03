package com.example.sara.team.profile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sara on 6/18/2017.
 */
public class RequestTask {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    HttpURLConnection con;
    URL url = null;

    public String PostData(String link, String Query) {
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(READ_TIMEOUT);
            con.setConnectTimeout(CONNECTION_TIMEOUT);
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);

            OutputStream outputStream = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(Query);
            writer.flush();
            writer.close();
            outputStream.close();
            con.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int response = con.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer result = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();

            } else {
                return "Not Found";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Not Found";
        }finally {
            con.disconnect();
        }
    }

    public String getData(String link){
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int response = con.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK){

                InputStream inputStream = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer result = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null){
                    result.append(line);
                }
                return result.toString();
            }
            else {
                return "Not Found";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Not Found";
        }
    }
}
