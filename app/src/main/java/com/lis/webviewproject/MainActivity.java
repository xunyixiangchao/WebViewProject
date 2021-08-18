package com.lis.webviewproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import com.lis.base.util.ServiceLoaderUtil;
import com.lis.common.autoservice.IWebViewService;
import com.lis.webviewproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mMainBinding;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //抖动
        // Animation shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake);
        // mMainBinding.openWebview.startAnimation(shakeAnim);

        // mMainBinding.openWebview.animate()
        //         .translationX(100)
        //         .setInterpolator(new CycleInterpolator(100))
        //         .setDuration(500)
        //         .start();

        ObjectAnimator objectAnimator = startShakeByPropertyAnim(mMainBinding.openWebview, 1f, 1f
                , 2, 500, false);


        mMainBinding.openWebview.setOnClickListener(v -> {
            IWebViewService webViewService = ServiceLoaderUtil.getService(IWebViewService.class);
            Log.i(TAG, "onCreate: webviewService" + webViewService);
            // objectAnimator.start();
            if (webViewService != null) {
                // webViewService.startWebViewActivity(MainActivity.this, "http://www.baidu.com", "百度",
                //         true);
                webViewService.startDemoHtml(this);
            }
        });
    }

    /**
     * 动画抖动效果
     *
     * @param view         抖动控件
     * @param scaleLarge   缩小倍数
     * @param shakeDegrees 放大倍数
     * @param duration     抖动角度
     * @param isInfinite   是否循环抖动
     */
    private ObjectAnimator startShakeByPropertyAnim(View view, float scaleSmall, float scaleLarge, float shakeDegrees, long duration, boolean isInfinite) {
        if (view == null) {
            return null;
        }
        PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)

        );
        PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)

        );
        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -shakeDegrees),
                Keyframe.ofFloat(0.2f, shakeDegrees),
                Keyframe.ofFloat(0.3f, -shakeDegrees),
                Keyframe.ofFloat(0.4f, shakeDegrees),
                Keyframe.ofFloat(0.5f, -shakeDegrees),
                Keyframe.ofFloat(0.6f, shakeDegrees),
                Keyframe.ofFloat(0.7f, -shakeDegrees),
                Keyframe.ofFloat(0.8f, shakeDegrees),
                Keyframe.ofFloat(0.9f, -shakeDegrees),
                Keyframe.ofFloat(1.0f, 0f)
        );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleXValuesHolder, scaleYValuesHolder, rotateValuesHolder);
        objectAnimator.setDuration(duration);
        if (isInfinite) {
            objectAnimator.setRepeatCount(Animation.INFINITE);
        }
        return objectAnimator;

    }
}