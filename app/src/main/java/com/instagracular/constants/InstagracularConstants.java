package com.instagracular.constants;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/16/17
 */
public interface InstagracularConstants {
    /**
     * Request for all permissions
     */
    int ALL_REQUESTS_CODE = 1;

    /**
     * Request for the read permission, since we need it to show the images
     */
    int READ_EXTERNAL_STORAGE_REQUEST_CODE = 0;

    /**
     * Camera request code
     */
    int CAMERA_REQUEST_CODE = 1;

    /**
     * Key to send the data of the taken photo
     */
    String DATA_KEY = "data";

    /**
     * Key for the bitmap extra (que just taken photo)
     */
    String PHOTO_KEY = "photo";

    /**
     * String format to save photos
     */
    String DATE_FORMAT = "yyyyMMdd_HHmmss";

    /**
     * Uri authorities for image saving
     */
    String URI_AUTHTORITIES = "com.instagracular.view.activity.FileChooserActivity";
}
