package com.diegosaul402.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diegosaul402.tipcalc.R;
import com.diegosaul402.tipcalc.TipCalcApp;
import com.diegosaul402.tipcalc.fragments.TipHistoryListFragment;
import com.diegosaul402.tipcalc.fragments.TipHistoryListFragmentListener;
import com.diegosaul402.tipcalc.entity.TipRecord;
import com.diegosaul402.tipcalc.utils.TipUtils;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.BtnSubmit)
    Button BtnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;
    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }

        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.BtnSubmit)
    public void handleSubmit(){

        hideKeyboard();
        String strInputTotal = inputBill.getText().toString().trim();
        if(!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();

            TipRecord record = new TipRecord();
            record.setBill(total);
            record.setTipPercentage(tipPercentage);
            record.setTimestamp(new Date());

            String strTip = String.format(getString(R.string.global_message_tip), TipUtils.getTip(record));
            fragmentListener.addToList(record);

            //txtTip.setVisibility(View.VISIBLE);
            //txtTip.setText(strTip);
        }
    }
    @OnClick(R.id.btnClear)
    public void handleClear(){
        fragmentListener.clearList();
        inputPercentage.setText("");
        inputBill.setText("");
        inputBill.requestFocus();
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }
    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }
    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = inputPercentage.getText().toString().trim();

        if(!strInputTipPercentage.isEmpty()) {
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }
        else {
            inputPercentage.setText(String.valueOf(DEFAULT_TIP_PERCENTAGE));
        }

        return tipPercentage;
    }

    public void handleTipChange(int change){
        int tipPercentage = getTipPercentage();
        tipPercentage += change;

        if(tipPercentage > 0) {
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }
    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }catch (NullPointerException npe){
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }


}
