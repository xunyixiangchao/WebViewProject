package com.lis.webviewproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * 第二个页面
 * Created by lis on 2021/8/21.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}