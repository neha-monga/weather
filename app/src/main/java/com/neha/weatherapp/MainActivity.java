package com.neha.weatherapp;

import android.os.Bundle;

import com.neha.weatherapp.util.FragmentTag;
import com.neha.weatherapp.util.KeyConstant;
import com.neha.weatherapp.util.Util;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Container activity for main fragment
 * @author Neha Monga
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMainFragment();
    }

    /*Shows main fragment*/
    private void showMainFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(KeyConstant.FRAGMENT_TAG, FragmentTag.MAIN_FRAGMENT);
        bundle.putInt(KeyConstant.RESOURCE_ID, R.id.container);
        Util.replaceFragmentWithTag(MainActivity.this, new MainFragment(), bundle);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
