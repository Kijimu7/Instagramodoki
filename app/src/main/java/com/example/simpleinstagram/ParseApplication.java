package com.example.simpleinstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("I7wxDlonYvHp0DjfI6wUYezWwOwhc7wLdtF0RWeD")
                .clientKey("I9MSJ49y0mtyXX9IjOCC3OVFfxRCA5N6es44Q8kQ")
                .server("https://parseapi.back4app.com")
                .build());
    }
}
