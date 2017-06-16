package com.hzq.componets.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.hzq.componets.repository.ProductDataRepository;
import com.hzq.db.room.entity.ProductEntity;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/14
 * @version:
 * @description:
 */

public class ProductListViewModel extends GenerationViewModel {

    private final LiveData<List<ProductEntity>> mObservableProducts;

    public ProductListViewModel(Application application) {
        super(application);
        final ProductDataRepository repository = new ProductDataRepository();
        mObservableProducts = Transformations.switchMap(repository.isCreatedDatabase(), new Function<Boolean, LiveData<List<ProductEntity>>>() {
            @Override
            public LiveData<List<ProductEntity>> apply(Boolean input) {
                if(!Boolean.TRUE.equals(input)){
                    return ABSENT;
                }else{
                    return repository.getProducts();
                }
            }
        });
    }

    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }
}
