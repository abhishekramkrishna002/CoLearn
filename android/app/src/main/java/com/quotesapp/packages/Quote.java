package com.quotesapp.packages;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.quotesapp.bridges.ChapterModule;
import com.quotesapp.bridges.QuoteModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quote implements ReactPackage {

    public Quote(){

    }

    @Override
    public List<NativeModule> createNativeModules(final ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<NativeModule>();
        modules.add(new QuoteModule(reactContext));

        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
