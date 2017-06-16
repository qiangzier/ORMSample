package com.hzq.ormsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hzq.componets.ui.ProductListActivity;
import com.hzq.ormsample.helper.DB;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolBar();
        setTitle("ComponetsDemo");

    }

    public void startRoom(View view){
        DB.INSTANCE.setType(0);
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
    }
    public void startGreenDao(View view){
        DB.INSTANCE.setType(1);
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
    }
    public void startComponets(View view){
        startActivity(new Intent(HomeActivity.this, ProductListActivity.class));
    }
}
