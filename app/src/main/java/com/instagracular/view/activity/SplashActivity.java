package com.instagracular.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent startApp = new Intent(this, FileChooserActivity.class);
        startActivity(startApp);
        finish();
    }
}
