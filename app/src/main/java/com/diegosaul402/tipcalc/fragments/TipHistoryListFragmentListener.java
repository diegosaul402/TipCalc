package com.diegosaul402.tipcalc.fragments;

import com.diegosaul402.tipcalc.entity.TipRecord;

/**
 * Created by diego on 10/10/16.
 */
public interface TipHistoryListFragmentListener {
    void initList();
    void addToList(TipRecord record);
    void clearList();
}
