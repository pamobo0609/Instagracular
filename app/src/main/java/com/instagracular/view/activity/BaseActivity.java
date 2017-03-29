package com.instagracular.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.instagracular.application.InstagracularApplication;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/16/17
 */
abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        InstagracularApplication.getAuth().addAuthStateListener(InstagracularApplication.getAuthListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (InstagracularApplication.getAuthListener() != null) {
            InstagracularApplication.getAuth().removeAuthStateListener(InstagracularApplication.getAuthListener());
        }
    }
}