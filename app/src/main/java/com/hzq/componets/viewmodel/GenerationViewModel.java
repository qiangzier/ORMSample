package com.hzq.componets.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

/**
 * @author: hezhiqiang
 * @date: 2017/6/16
 * @version:
 * @description:
 */

public abstract class GenerationViewModel extends AndroidViewModel{

    public static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    public GenerationViewModel(Application application) {
        super(application);
    }
}
