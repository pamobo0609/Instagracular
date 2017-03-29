package com.instagracular.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.android.annotations.NonNull;
import com.instagracular.R;
import com.instagracular.constants.InstagracularConstants;
import com.instagracular.databinding.ActivityFileChooserBinding;
import com.instagracular.helper.ImageHelper;
import com.instagracular.util.ViewModelContact;
import com.instagracular.view.activity.EditImageActivity;
import com.instagracular.view.activity.FileChooserActivity;

import java.io.File;
import java.io.IOException;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/16/17
 */
public class FileChooserViewModel extends BaseViewModel {

    private ActivityFileChooserBinding mBinding;

    private Uri photoPath;

    /**
     * <h1>FileChooserViewModel</h1>
     * <p>Default constructor for this view model.</p>
     *
     * @param mBinding the binding object of the activity
     * @param mContext the related {@link Context}
     */
    public FileChooserViewModel(ActivityFileChooserBinding mBinding, Context mContext) {
        this.mBinding = mBinding;
        this.mContext = mContext;
    }

    /**
     * <h1>OpenCamera</h1>
     * <p>Opens the built-in camera.</p>
     */
    public void openCamera() {
        final Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (openCamera.resolveActivity(mContext.getPackageManager()) == null) {
            Snackbar.make(mBinding.getRoot(), R.string.err_unexpected, Snackbar.LENGTH_LONG).show();
        } else {
            File thePhoto = null;
            try {
                thePhoto = ImageHelper.getInstance().createImageFile(mContext);
            } catch (IOException io) {
                Log.e("ERROR: ", io.getMessage());
            }

            if (null == thePhoto) {
                Snackbar.make(mBinding.getRoot(), R.string.err_photo_space, Snackbar.LENGTH_LONG).show();
            } else {
                final Uri photoPath = FileProvider.getUriForFile(mContext, InstagracularConstants
                        .URI_AUTHTORITIES, thePhoto);

                openCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);
                // Save the reference of the photo
                this.photoPath = photoPath;

                ((FileChooserActivity) mContext).startActivityForResult(openCamera,
                        InstagracularConstants.CAMERA_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

    /**
     * <h1>HandleTakenPhoto</h1>
     * <p>Takes the user to the next activity, which is the image editing one.</p>
     */
    public void handleTakenPhoto() {
        if (null == photoPath) {
            Snackbar.make(mBinding.getRoot(), R.string.err_saving_photo, Snackbar.LENGTH_LONG).show();
        } else {
            final Intent goToEdit = new Intent(mContext, EditImageActivity.class);

            // The full size image is passed as an extra
            goToEdit.putExtra(InstagracularConstants.PHOTO_KEY, photoPath);
            mContext.startActivity(goToEdit);
        }
    }
}