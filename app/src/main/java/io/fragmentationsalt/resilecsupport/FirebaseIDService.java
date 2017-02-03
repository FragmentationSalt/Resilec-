package io.fragmentationsalt.resilecsupport;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by rohan on 2/1/2017.
 */

public class FirebaseIDService extends AsyncTask<Void, Void, String>  {

    private String token;
        FirebaseIDService(String token) {
            this.token = token;
        }
    @Override
    protected String doInBackground(Void... params) {
        try
        {
            String response = null;
            String tokenurl = "http://fragsalt.pe.hu/register.php";
            String data = URLEncoder.encode("Token","UTF-8") + "=" + URLEncoder.encode(token,"UTF-8");
            URL url = new URL(tokenurl);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
