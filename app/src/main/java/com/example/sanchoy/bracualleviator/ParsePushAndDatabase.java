package com.example.sanchoy.bracualleviator;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class ParsePushAndDatabase extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "twPVZRsTXu3g7sZsDPH5j6hxCNl17RPZORlBRBME", "aMohxIZisIbZl9HOzYWgdbkBVztf8wtYwFE2Zjzz");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        //registering itself for push notifications
        /*
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
        */
    }
}
