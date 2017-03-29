package com.instagracular.application;

import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/16/17
 */
public class InstagracularApplication extends MultiDexApplication {

    static FirebaseAuth mAuth;

    static FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        // Initialize the firebase client
        initFirebase();
    }

    /**
     * <h1>InitFirebase</h1>
     * <p>Initializes the firebase instance.</p>
     */
    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user == null) {

                } else {

                }
            }
        };
    }

    public static FirebaseAuth getAuth() {
        return mAuth;
    }

    public static FirebaseAuth.AuthStateListener getAuthListener() {
        return mAuthListener;
    }
}
