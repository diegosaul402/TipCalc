package com.diegosaul402.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diegosaul402.tipcalc.R;
import com.diegosaul402.tipcalc.TipCalcApp;
import com.diegosaul402.tipcalc.fragments.TipHistoryListFragment;
import com.diegosaul402.tipcalc.fragments.TipHistoryListFragmentListener;
import com.diegosaul402.tipcalc.models.TipRecord;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputBill)
    EditText inputBill;
    @BindView(R.id.BtnSubmit)
    Button BtnSubmit;
    @BindView(R.id.inputPercentage)
    EditText inputPercentage;
    @BindView(R.id.btnIncrease)
    Button btnIncrease;
    @BindView(R.id.btnDecrease)
    Button btnDecrease;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.txtTip)
    TextView txtTip;
    @BindView(R.id.content_tip)
    RelativeLayout contentTip;

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
        fragmentListener = fragment;
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

            String strTip = String.format(getString(R.string.global_message_tip), record.getTip());
            fragmentListener.addToList(record);

            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    public void handleClickIncrease(){
        hideKeyboard();
        handletipChange(TIP_STEP_CHANGE);
    }

    public void handleClickDecrease(){
        hideKeyboard();
        handletipChange(TIP_STEP_CHANGE);
    }
    private int getTipPercentage() {
        //crear una variable tipPercentage en la que guardemos de DEFAULT_TIP_CHANGE
        //crear una string strInputTipPercentage que tome el valor del inputPercentage con su TRIM
        //verificar cadena vacía
        //si strInputTipPercentage no es vacía, sobreescrbir tipPercentage con strInputTipPercentage
        // y pasarlo a entero
        //si viene vacía hacer inputPercetage.seTText(String.ValueOf(DEFAULT_TIP_PERCENTAGE));
        //mostrar en la interfaz tipPercentage
        return DEFAULT_TIP_PERCENTAGE;
    }

    public void handletipChange(int change){
        int TipPercentage = getTipPercentage();
        change += TipPercentage;
        if(change>=0)
        {

        }else{

        }
        //Si tipPercentage > 0 colocar en su caja correspondiente
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
