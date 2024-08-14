package com.gustavomacedo.flaquiz.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PageNavigator {

    public PageNavigator() {
    }

    public static void nextPage(Context appContext, Class nextClass, Bundle bd) {
        Intent in = new Intent(appContext, nextClass);

        if (bd != null) {
            in.putExtras(bd);
        }

        appContext.startActivity(in);
    }
}
