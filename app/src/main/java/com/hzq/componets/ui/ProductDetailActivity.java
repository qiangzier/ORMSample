package com.hzq.componets.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hzq.componets.viewmodel.ProductDetailViewModel;
import com.hzq.db.room.entity.ProductEntity;
import com.hzq.ormsample.BaseActivity;
import com.hzq.ormsample.CreateProductActivity;
import com.hzq.ormsample.R;
import com.hzq.ormsample.databinding.ProductDetailBinding;
import com.hzq.ormsample.model.Product;

import org.jetbrains.annotations.NotNull;

/**
 * @author: hezhiqiang
 * @date: 2017/6/16
 * @version:
 * @description:
 */

public class ProductDetailActivity extends BaseActivity {

    private long productId;

    ProductDetailBinding mBinding;
    ProductDetailViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_detail_layout,null,false);
        setContentView(mBinding.getRoot());
        initToolBar();
        setTitle("ProductDetail");
        productId = getIntent().getLongExtra("id",0);

        ProductDetailViewModel.Factory factory = new ProductDetailViewModel.Factory(getApplication(),productId);
        viewModel = ViewModelProviders.of(this,factory).get(ProductDetailViewModel.class);
        mBinding.setDetailViewModel(viewModel);
        viewModel.setProductClickCallback(new ProductClickCallback() {
            @Override
            public void onClick(@NotNull Product product) {
                Intent intent = new Intent(ProductDetailActivity.this, CreateProductActivity.class);
                intent.putExtra("id",productId);
                startActivity(intent);
            }
        });

        viewModel.getObserableLiveData().observe(this, new Observer<ProductEntity>() {
            @Override
            public void onChanged(@Nullable ProductEntity entity) {
                if(entity != null){
                    viewModel.setProduct(entity);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_menu,menu);
        menu.findItem(R.id.action_add).setTitle("删除");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(viewModel.deleteProduct()){
            Toast.makeText(this, "delete success!", Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
