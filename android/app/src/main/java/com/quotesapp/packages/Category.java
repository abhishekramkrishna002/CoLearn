package com.quotesapp.packages;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.quotesapp.bridges.CategoryModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category implements ReactPackage {

    public Category(){

    }

    @Override
    public List<NativeModule> createNativeModules(final ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<NativeModule>();
        modules.add(new CategoryModule(reactContext));

        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
