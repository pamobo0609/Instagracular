package com.instagracular.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.instagracular.BR;
import com.instagracular.R;
import com.instagracular.databinding.FilterItemBinding;
import com.instagracular.model.FilterItem;

import java.util.ArrayList;

/**
 * @author Accenture - <a href="mailto:jose.p.monge.bonilla@accenture.com">Jose Pablo Monge Bonilla</a>
 * @since 103/21/17
 */
public class FilterOptionsAdapter extends RecyclerView.Adapter<FilterOptionsAdapter.FilterViewHolder> {

    /**
     * Where all the filters are
     */
    private ArrayList<FilterItem> mDataSet;

    /**
     * <h1>FilterOptionsAdapter</h1>
     * <p>Constructor for this class.</p>
     *
     * @param mDataSet the container of all filters.
     */
    public FilterOptionsAdapter(ArrayList<FilterItem> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        FilterItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.filter_item, parent, false);

        return new FilterViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(FilterViewHolder holder, int position) {
        FilterItem item = mDataSet.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * <h1>FilterViewHolder</h1>
     * <p>ViewHolder for the filter recycler view</p>
     */
    class FilterViewHolder extends RecyclerView.ViewHolder {

        /**
         * Binding object of the recycler row.
         */
        private final FilterItemBinding mBinding;

        FilterViewHolder(FilterItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.mBinding = itemBinding;
        }

        /**
         * <h1>Bind</h1>
         * <p>Binds a {@link FilterItem} to a Recycler row.</p>
         *
         * @param pItem the item to bind
         */
        void bind(FilterItem pItem) {
            mBinding.setVariable(BR.mItem, pItem);
            mBinding.executePendingBindings();
        }
    }
}