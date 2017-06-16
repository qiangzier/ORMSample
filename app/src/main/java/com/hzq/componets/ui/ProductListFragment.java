package com.hzq.componets.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzq.componets.adapter.ProductListAdapter;
import com.hzq.componets.viewmodel.ProductListViewModel;
import com.hzq.db.room.entity.ProductEntity;
import com.hzq.ormsample.R;
import com.hzq.ormsample.databinding.ListFragmentBinding;
import com.hzq.ormsample.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/14
 * @version:
 * @description:
 */

public class ProductListFragment extends LifecycleFragment {

    private ListFragmentBinding mBinding;
    private ProductListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.activity_list_fragment, container, false);
        adapter = new ProductListAdapter(new ProductClickCallback() {
            @Override
            public void onClick(@NotNull Product product) {
                if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)){
                    Intent intent = new Intent(getActivity(),ProductDetailActivity.class);
                    intent.putExtra("id",product.getId());
                    startActivity(intent);
                }
            }
        });
        mBinding.recyclerView.setAdapter(adapter);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProductListViewModel viewModel =
                ViewModelProviders.of(this).get(ProductListViewModel.class);
        subscribeUi(viewModel);
    }

    private void subscribeUi(ProductListViewModel viewModel){
        viewModel.getProducts().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> productEntities) {
                if(productEntities != null){
                    mBinding.setIsLoading(false);
                    adapter.setProducts(productEntities);
                }else{
                    mBinding.setIsLoading(true);
                }
            }
        });
    }
}
