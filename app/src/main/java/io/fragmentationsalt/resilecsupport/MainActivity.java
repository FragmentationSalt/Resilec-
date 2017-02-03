package io.fragmentationsalt.resilecsupport;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.test.mock.MockPackageManager;
import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;

public class MainActivity extends AppCompatActivity{
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    // GPSTracker class
    GPSTracker gps;

    double latitude;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getApplicationContext(),FirebaseInstanceIDService.class));
           try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will
                //execute every time, else your else part will work
            }
            gps = new GPSTracker(MainActivity.this);

            // check if GPS enabled
            if(gps.canGetLocation()){

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                        + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            }else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

