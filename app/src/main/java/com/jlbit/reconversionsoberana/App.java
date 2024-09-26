package com.jlbit.reconversionsoberana;

import android.app.Application;
import android.content.Intent;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startActivity(new Intent(this, HomeActivity.class));
    }
}
