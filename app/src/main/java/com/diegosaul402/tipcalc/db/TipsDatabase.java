package com.diegosaul402.tipcalc.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by diego on 03/11/2016.
 */
@Database(name = TipsDatabase.NAME, version = TipsDatabase.VERSION)
public class TipsDatabase {
    public static final String NAME = "Tips";
    public static final int VERSION = 1;
}
