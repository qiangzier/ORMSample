package com.hzq.componets.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hzq.ormsample.BaseActivity;
import com.hzq.ormsample.CreateProductActivity;
import com.hzq.ormsample.R;


public class ProductListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);
        initToolBar();
        setTitle("DataBinding");
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new ProductListFragment()).commit();
        }
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductListActivity.this, CreateProductActivity.class));
            }
        });
    }
}
