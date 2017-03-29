package com.instagracular.adapter;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.instagracular.R;
import com.instagracular.databinding.ImageItemBinding;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/20/17
 */
public class MySimpleCursorAdapter extends SimpleCursorAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private ImageItemBinding mBinding;

    public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        mBinding = DataBindingUtil.inflate(mInflater, R.layout.image_item, parent, false);

        return mBinding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);



    }
}
