package com.instagracular.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.instagracular.databinding.ActivityEditImageBinding;
import com.instagracular.model.FilterItem;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/21/17
 */
public class EditImageViewModel extends BaseViewModel {

    private ActivityEditImageBinding mBinding;

    /**
     * <h1>EditImageViewModel</h1>
     * <p>Constructor for this class</p>
     *
     * @param pContext the related activity
     * @param pBinding the binding object for the {@link com.instagracular.view.activity.EditImageActivity}
     */
    public EditImageViewModel(Context pContext, ActivityEditImageBinding pBinding) {
        this.mContext = pContext;
        this.mBinding = pBinding;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

    /**
     * <h1>GetFilters</h1>
     * <p>Returns the available filters in this app.</p>
     *
     * @return an {@link ArrayList} of filters.
     */
    public ArrayList<FilterItem> getFilters() {
        ArrayList<FilterItem> arrayList = new ArrayList<>();

        arrayList.add(new FilterItem(0, "asdasdf"));
        arrayList.add(new FilterItem(0, "asdasdf"));
        arrayList.add(new FilterItem(0, "asdasdf"));
        arrayList.add(new FilterItem(0, "asdasdf"));
        arrayList.add(new FilterItem(0, "asdasdf"));

        return arrayList;
    }

    /**
     * <h1>GetImageFromUri</h1>
     * <p>Returns a {@link Bitmap} from a given {@link android.net.Uri}</p>
     *
     * @param photoPath where the photo is
     * @return a {@link Bitmap} containing the photo
     */
    public Bitmap getImageFromUri(Uri photoPath) {
        Bitmap fullSizePhoto = null;

        try {
            fullSizePhoto = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), photoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullSizePhoto;
    }
}
