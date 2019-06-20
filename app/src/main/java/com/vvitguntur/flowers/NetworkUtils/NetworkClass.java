package com.vvitguntur.flowers.NetworkUtils;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkClass {
    public static String getFlowerInfo(){
        try{
            final String Flowers_URL ="https://pixabay.com/api/?key=12724518-f640c13b0ad9398e35a57c4ce&q=yellow+flowers&image_type=photo&pretty=true";
            Uri builtURI = Uri.parse(Flowers_URL).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());
            HttpsURLConnection Connection = (HttpsURLConnection) requestURL.openConnection();
            Connection.setRequestMethod("GET");
            Connection.connect();
            InputStream inputStream = Connection.getInputStream();

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            return buffer.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
