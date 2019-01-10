package com.quotesapp.packages;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.quotesapp.bridges.BookModule;
import com.quotesapp.bridges.ChapterModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chapter implements ReactPackage {

    public Chapter(){

    }

    @Override
    public List<NativeModule> createNativeModules(final ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<NativeModule>();
        modules.add(new ChapterModule(reactContext));

        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
