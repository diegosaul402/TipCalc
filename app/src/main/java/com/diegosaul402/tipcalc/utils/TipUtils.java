package com.diegosaul402.tipcalc.utils;

import com.diegosaul402.tipcalc.entity.TipRecord;

import java.text.SimpleDateFormat;

/**
 * Created by diego on 03/11/2016.
 */

public class TipUtils {
    public static double getTip(TipRecord tipRecord){
        return tipRecord.getBill()*(tipRecord.getTipPercentage()/100d);
    }

    public static String getDateFormated(TipRecord tipRecord){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd, yyyy HH:mm");
        return simpleDateFormat.format(tipRecord.getTimestamp());
    }
}