package io.fragmentationsalt.resilecsupport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import static io.fragmentationsalt.resilecsupport.MainActivity.MyPREFERENCES;
import static io.fragmentationsalt.resilecsupport.MainActivity.getContext;
//Created by rohan on 2/3/2017.


public class GPSAsync extends AsyncTask<Void, Void, String> {
    private Context context = getContext();
    SharedPreferences sp = context.getSharedPreferences(MyPREFERENCES,context.MODE_PRIVATE);

    @Override
    protected String doInBackground(Void... params) {
        try
        {
            String token = FirebaseInstanceId.getInstance().getToken();
            String longitude =sp.getString("long","");
            String latitude =sp.getString("lat","");
            String response;
            String gps = "http://fragsalt.pe.hu/gps.php";
            String data = URLEncoder.encode("long","UTF-8") + "=" + URLEncoder.encode(longitude,"UTF-8") +"&"+
                    URLEncoder.encode("lat","UTF-8") + "=" + URLEncoder.encode(latitude,"UTF-8") + "&" +
                    URLEncoder.encode("token","UTF-8") + "=" + URLEncoder.encode(token,"UTF-8");
            URL url = new URL(gps);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while((response  = reader.readLine())!= null)
            {
                sb.append(response);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
