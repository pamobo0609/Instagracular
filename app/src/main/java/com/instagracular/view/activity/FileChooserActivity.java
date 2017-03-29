package com.instagracular.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.instagracular.R;
import com.instagracular.application.InstagracularApplication;
import com.instagracular.constants.InstagracularConstants;
import com.instagracular.databinding.ActivityFileChooserBinding;
import com.instagracular.helper.ImageHelper;
import com.instagracular.viewmodel.FileChooserViewModel;

public class FileChooserActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ActivityFileChooserBinding mBinding;

    private FileChooserViewModel mViewModel;

    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Data binding initialization
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_file_chooser);
        mViewModel = new FileChooserViewModel(mBinding, this);
        mBinding.setMListener(mViewModel);

        // Toolbar init
        setSupportActionBar(mBinding.applicationBar.toolbar);
        mBinding.applicationBar.toolbar.setTitle(R.string.lbl_file_chooser);

        // For grid init
        initGridBehavior();

        // FAB behavior & initializes the open camera button
        initFabBehavior();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            ActivityCompat.requestPermissions(FileChooserActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    InstagracularConstants.READ_EXTERNAL_STORAGE_REQUEST_CODE);

        } else {
            // Initializes the loader to handle images from gallery
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mViewModel) {
            mViewModel.onDestroy();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            // Write and camera permissions
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Initializes the loader to handle images from gallery
                    getSupportLoaderManager().initLoader(0, null, this);
                } else {
                    Snackbar.make(mBinding.getRoot(), R.string.msg_need_permission_read, Snackbar.LENGTH_SHORT).show();
                }
                break;

            case 1:
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    mViewModel.openCamera();
                } else {
                    Snackbar.make(mBinding.getRoot(), R.string.msg_need_permissions, Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (InstagracularConstants.CAMERA_REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
            mViewModel.handleTakenPhoto();
        }
    }

    /**
     * <h1>InitFabClickListener</h1>
     * <p>Adds the click listener to the open camera button, with permission asking.</p>
     */
    private void initFabClickListener() {
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    ActivityCompat.requestPermissions(FileChooserActivity.this,
                            new String[]{Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, InstagracularConstants.ALL_REQUESTS_CODE);
                } else {
                    mViewModel.openCamera();
                }
            }
        });
    }

    /**
     * <h1>InitFabBehavior</h1>
     * <p>Initializes the scroll behavior on the fab button.</p>
     */
    private void initFabBehavior() {
        mBinding.content.gridImages.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                switch (scrollState) {
                    default:
                        //show button here
                        mBinding.fab.show();
                        break;

                    case 2: // SCROLL_STATE_FLING
                        mBinding.fab.hide();
                        break;

                    case 1: // SCROLL_STATE_TOUCH_SCROLL
                        mBinding.fab.hide();
                        break;

                    case 0: // SCROLL_STATE_IDLE
                        mBinding.fab.show();
                        break;
                }
            }
        });
        // Click listener to open camera
        initFabClickListener();
    }

    private void initGridBehavior() {
        // Adapter for the images
        mAdapter = new SimpleCursorAdapter(this, R.layout.image_item, null,
                new String[]{"_data"}, new int[]{R.id.imgv_thumb}, 0);

        // Sets an adapter with the current loaded images
        mBinding.content.gridImages.setAdapter(mAdapter);

        // The Grid view's onItemClickListener
        mBinding.content.gridImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor c = ((SimpleCursorAdapter) adapterView.getAdapter()).getCursor();
                c.moveToPosition(position);

            }
        });
    }
}