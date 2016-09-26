package com.diegosaul402.tipcalc;

import android.app.Application;

/**
 * Created by diego on 26/09/16.
 */
public class TipCalcApp extends Application {
    private final static String ABOUT_URL = "http://google.com";
    public String getAboutUrl(){
        return ABOUT_URL;
    }
}
