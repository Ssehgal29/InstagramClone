package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KAsSe6vVysrQhlsHKESn8VjNiAm0NntssVMmfMDB")
                // if defined
                .clientKey("GLZzuw3TyakLfd3SFle6UcGxoJmjpGNlaVJrqzeG")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
