package com.instagracular.viewmodel;

import android.content.Context;

import com.instagracular.util.ViewModelContact;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/16/17
 */
class BaseViewModel implements ViewModelContact.ViewModel {

    /**
     * The related context for every view model.
     */
    Context mContext;

    @Override
    public void onDestroy() {
        mContext = null;
    }
}
