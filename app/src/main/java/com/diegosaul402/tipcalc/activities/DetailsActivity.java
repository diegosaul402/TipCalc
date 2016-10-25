package com.diegosaul402.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.diegosaul402.tipcalc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {


    @Bind(R.id.details)
    TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        double bill = intent.getDoubleExtra("bill", 0);
        double tip = intent.getDoubleExtra("tip", 0);
        int percentage = intent.getIntExtra("percentage", 0);
        String date = intent.getStringExtra("date");
        String text = "Bill: $" + bill + ", Tip " + percentage +"%: $"+ tip +" date: " + date;
        details.setText(text);
    }
}
