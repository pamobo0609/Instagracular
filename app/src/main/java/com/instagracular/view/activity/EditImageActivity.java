package com.instagracular.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.instagracular.R;
import com.instagracular.adapter.FilterOptionsAdapter;
import com.instagracular.constants.InstagracularConstants;
import com.instagracular.databinding.ActivityEditImageBinding;
import com.instagracular.viewmodel.EditImageViewModel;

public class EditImageActivity extends BaseActivity {

    ActivityEditImageBinding mBinding;

    Uri photoPath;

    private EditImageViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Binding object for this activity
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_image);

        // The view model for this activity
        mViewModel = new EditImageViewModel(this, mBinding);

        // We add the taken photo to the Imageview
        Bundle extras = getIntent().getExtras();
        if (null  == extras) {
            Snackbar.make(mBinding.getRoot(), R.string.err_unexpected, Snackbar.LENGTH_LONG).show();
        } else {
            photoPath = (Uri) extras.get(InstagracularConstants.PHOTO_KEY);
            mBinding.imgvFullSizeImage.setImageBitmap(mViewModel.getImageFromUri(photoPath));
        }

        // Initialization of the recyclerview
        FilterOptionsAdapter adapter = new FilterOptionsAdapter(mViewModel.getFilters());

        mBinding.recyclerFilters.setHasFixedSize(true);
        mBinding.recyclerFilters.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mBinding.recyclerFilters.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_edit_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
            case R.id.menu_auto_fix:
                break;
            case R.id.menu_flip_horizontal:
                break;
            case R.id.menu_flip_vertical:
                break;
            case R.id.menu_none:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mViewModel) {
            mViewModel.onDestroy();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}