package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your Parse model
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("4gMwbOGtu6xJLdpRbaOG11OyGyahYEHQCbYm6Kaq")
                .clientKey("CxObk9OHx7nshzHCzoELeIntfha01PapFcTJaguX")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
