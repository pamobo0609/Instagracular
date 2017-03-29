package com.instagracular.util;

import android.support.v4.app.Fragment;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/16/17
 */
public interface ViewModelContact {
    /**
     * <h1>MainView</h1>
     * <p>Implemented by all fragments, to return the instance where we are.</p>
     */
    interface MainView {
        /**
         * <h1>GetFragment</h1>
         * <p>Returns the frgment where we are at the moment.</p>
         *
         * @return a {@link Fragment}
         */
        Fragment getFragment();
    }

    /**
     * <h1>ViewModel</h1>
     * <p>Implemented by all view models, to clean up memory.</p>
     */
    interface ViewModel {
        /**
         * <h1>OnDestroy</h1>
         * <p>Called when the fragment or activity is being destroyed, to clean up memory.</p>
         */
        void onDestroy();
    }
}