package io.fragmentationsalt.resilecsupport;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by rohan on 2/1/2017.
 */

public class FirebaseInstanceIDService extends Service {
        String token = FirebaseInstanceId.getInstance().getToken();

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseIDService exec = new FirebaseIDService(token);
        exec.execute((Void) null);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

