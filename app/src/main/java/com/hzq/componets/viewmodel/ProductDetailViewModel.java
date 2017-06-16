package com.hzq.componets.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;

import com.hzq.componets.repository.ProductDataRepository;
import com.hzq.componets.ui.ProductClickCallback;
import com.hzq.db.room.entity.ProductEntity;

/**
 * @author: hezhiqiang
 * @date: 2017/6/16
 * @version:
 * @description:
 */

public class ProductDetailViewModel extends GenerationViewModel {

    private LiveData<ProductEntity> obserableLiveData;

    final ProductDataRepository repository;

    private ObservableField<ProductEntity> product = new ObservableField<>();

    private ProductClickCallback productClickCallback = null;

    private final long productId;

    public ProductDetailViewModel(Application application, final long id) {
        super(application);
        this.productId = id;
        repository = new ProductDataRepository();
        obserableLiveData = Transformations.switchMap(repository.isCreatedDatabase(), new Function<Boolean, LiveData<ProductEntity>>() {
            @Override
            public LiveData<ProductEntity> apply(Boolean input) {
                if(Boolean.TRUE.equals(input)){
                    return repository.getProduct(productId);
                }
                return ABSENT;
            }
        });
    }

    public LiveData<ProductEntity> getObserableLiveData() {
        return obserableLiveData;
    }

    public boolean deleteProduct(){
        if(repository.isCreatedDatabase().getValue()){
            repository.deleteProduct(productId);
            return true;
        }else{
            return false;
        }
    }

    public void setProduct(ProductEntity product) {
        this.product.set(product);
    }

    public ObservableField<ProductEntity> getProduct() {
        return product;
    }

    public void setProductClickCallback(ProductClickCallback productClickCallback) {
        this.productClickCallback = productClickCallback;
    }

    public ProductClickCallback getProductClickCallback() {
        return productClickCallback;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{

        @Nullable
        private Application application;
        private long productId;

        public Factory(Application application, long productId) {
            this.application = application;
            this.productId = productId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ProductDetailViewModel(application,productId);
        }
    }
}
