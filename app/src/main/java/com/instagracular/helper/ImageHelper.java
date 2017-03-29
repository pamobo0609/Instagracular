package com.instagracular.helper;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.instagracular.constants.InstagracularConstants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/16/17
 */
public class ImageHelper {

    public static ImageHelper instance;

    /**
     * Object to save and retrieve files from Firebase photos.
     */
    private StorageReference mStorageRef;

    private ImageHelper() {
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public static ImageHelper getInstance() {
        if (null == instance) {
            instance = new ImageHelper();
        }
        return instance;
    }

    /**
     * <h1>CreateImageFile</h1>
     * <p>Saves an image into the pictures directory.</p>
     *
     * @param pContext to get the files directory
     * @return the saved image
     * @throws IOException
     */
    public File createImageFile(Context pContext) throws IOException {
        String timeStamp = new SimpleDateFormat(InstagracularConstants.DATE_FORMAT, Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = pContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    /**
     * <h1>GetRealPathFromURI</h1>
     * <p>Returns the absolute path of an image.</p>
     *
     * @param pContext to get the {@link android.content.ContentResolver}
     * @param uri      the {@link Uri} to search in
     * @return the absolute path of a given {@link Uri}
     */
    public String getRealPathFromURI(Context pContext, Uri uri) {
        Cursor cursor = pContext.getContentResolver().query(uri, null, null, null, null);

        String result = "";

        if (null != cursor) {
            cursor.moveToFirst();
            result = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
            cursor.close();
        }

        return result;
    }

    /**
     * <h1>GetImageUri</h1>
     * <p>Given an image, returns the Uri of where it is.</p>
     *
     * @param pContext the related context
     * @param pImage   the image to look for
     * @return the Uri for a given image
     */
    public Uri getImageUri(Context pContext, Bitmap pImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        pImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(pContext.getContentResolver(), pImage, "Title", null);
        return Uri.parse(path);
    }

    /**
     * <h1>SaveImageToFirebase</h1>
     * <p>Saves an image to Google cloud photos.</p>
     *
     * @param pImagePath where the image is
     */
    public void saveImageToFirebase(String pImagePath) {
        Uri file = Uri.fromFile(new File(pImagePath));
        StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    /**
     *
     */
    public void getImageFromFirebase() {
        File localFile;
        try {
            localFile = File.createTempFile("images", "jpg");

            mStorageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
