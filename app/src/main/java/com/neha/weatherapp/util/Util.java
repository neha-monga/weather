package com.neha.weatherapp.util;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Util {

    /**
     * Clears the back stack
     *
     * @param activity Activity
     */
    public static void clearFragmentStack(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fm.popBackStack();
        }
    }

    /**
     * Replaces fragment with TAG
     *
     * @param activity Activity
     * @param fragment Fragment
     * @param args     Bundle
     */
    public static void replaceFragmentWithTag(FragmentActivity activity, Fragment fragment, Bundle args) {
        if (args != null) {
            fragment.setArguments(args);
            activity.getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(args.getInt(KeyConstant.RESOURCE_ID), fragment, args.getString(KeyConstant.FRAGMENT_TAG))
                    .addToBackStack(args.getString(KeyConstant.FRAGMENT_TAG))
                    .commit();
        }
    }




    public static void loadImage(@NonNull Context context, @NonNull String imageUrl, @NonNull ImageView image) {
        Glide.with(context)
                .load(imageUrl)
                .timeout(AppConstant.TIMEOUT)

                .into(image);
    }




}
