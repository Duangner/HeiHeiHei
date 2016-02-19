package com.qf.danan.daydayup.other.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qf.danan.daydayup.R;
import com.qf.danan.daydayup.other.utils.DaydayContants;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstUsed()){
            Intent intent = new Intent(this,GuideActivity.class);
            startActivity(intent);
            finish();
        }else {
            setContentView(R.layout.activity_welcome);
        }
    }

    private boolean isFirstUsed(){
        SharedPreferences preferences = getSharedPreferences(
                DaydayContants.PERFERENCE_FIRST_USED, Context.MODE_PRIVATE);
        return preferences.getBoolean(DaydayContants.PERFERENCE_FLAG_USED,true);
    }
}
