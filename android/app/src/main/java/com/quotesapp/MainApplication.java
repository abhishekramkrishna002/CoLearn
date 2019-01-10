package com.quotesapp;

import android.app.Application;

import com.facebook.react.ReactApplication;

import io.invertase.firebase.RNFirebasePackage;

import com.lugg.ReactNativeConfig.ReactNativeConfigPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.AlexanderZaytsev.RNI18n.RNI18nPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import com.quotesapp.packages.Book;
import com.quotesapp.packages.Category;
import com.quotesapp.packages.Chapter;
import com.quotesapp.packages.Quote;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import io.invertase.firebase.firestore.RNFirebaseFirestorePackage;
import io.realm.Realm;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {

            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new RNFirebasePackage(),
                    new ReactNativeConfigPackage(),
                    new RNDeviceInfo(),
                    new RNI18nPackage(),
                    new VectorIconsPackage(),
                    new RNGestureHandlerPackage(),
                    new RNFirebaseFirestorePackage(),
                    new Category(),
                    new Book(),
                    new Chapter(),
                    new Quote()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        Realm.init(this);
    }
}
