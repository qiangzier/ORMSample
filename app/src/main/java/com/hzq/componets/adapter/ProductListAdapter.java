package com.hzq.componets.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hzq.ormsample.R;
import com.hzq.ormsample.databinding.ActivityItemBinding;
import com.hzq.ormsample.model.Product;
import com.hzq.componets.ui.ProductClickCallback;

import java.util.List;
import java.util.Objects;

/**
 * @author: hezhiqiang
 * @date: 2017/6/14
 * @version:
 * @description:
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListHolder> {

    List<? extends Product> mProducts;

    @Nullable
    private final ProductClickCallback productClickCallback;

    public ProductListAdapter(@Nullable ProductClickCallback clickCallback){
        this.productClickCallback = clickCallback;
    }

    public void setProducts(final List<? extends Product> data){
        if(mProducts == null){
            mProducts = data;
            notifyItemRangeChanged(0,data.size());
        }else{
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProducts.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProducts.get(oldItemPosition).getId() == data.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Product op = mProducts.get(oldItemPosition);
                    Product np = data.get(newItemPosition);
                    return op.getId() == np.getId()
                            && Objects.equals(op.getDescription(),np.getDescription())
                            && Objects.equals(op.getName(),np.getName())
                            && op.getPrice() == np.getPrice();
                }
            });

            mProducts = data;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ProductListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.activity_item, parent, false);
        binding.setCallback(productClickCallback);
        return new ProductListHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductListHolder holder, int position) {
        holder.binding.setProduct(mProducts.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProducts == null ? 0 : mProducts.size();
    }

    static class ProductListHolder extends RecyclerView.ViewHolder{

        final ActivityItemBinding binding;
        public ProductListHolder(ActivityItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
