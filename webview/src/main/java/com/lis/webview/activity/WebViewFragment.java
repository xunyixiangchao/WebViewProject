package com.lis.webview.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lis.common.loadsir.ErrorCallback;
import com.lis.common.loadsir.LoadingCallback;
import com.lis.webview.R;
import com.lis.webview.webviewprocess.WebViewCallBack;
import com.lis.webview.common.Constants;
import com.lis.webview.databinding.FragmentWebviewBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * webview的Fragment
 * Created by lis on 2021/7/30.
 */
public class WebViewFragment extends Fragment implements WebViewCallBack, OnRefreshListener {

    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;
    private boolean mCanNativeRefresh;
    private boolean mIsError = false;

    private WebViewFragment() {

    }
    public static WebViewFragment getInstance(String url, boolean mCanNativeRefresh) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        bundle.putBoolean(Constants.CAN_NATIVE_REFRESH, mCanNativeRefresh);
        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    private String mUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUrl = arguments.getString(Constants.URL);
            mCanNativeRefresh = arguments.getBoolean(Constants.CAN_NATIVE_REFRESH, false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        mBinding.webview.registerWebViewCallback(this);
        mBinding.webview.addJsInterface("mywebview");
        mBinding.webview.loadUrl(mUrl);
        mLoadService = LoadSir.getDefault().register(mBinding.smartrefreshlayout, (Callback.OnReloadListener) v -> {
            mLoadService.showCallback(LoadingCallback.class);
            mBinding.webview.reload();
        });
        mBinding.smartrefreshlayout.setOnRefreshListener(this);
        mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        mBinding.smartrefreshlayout.setEnableLoadMore(false);
        return mLoadService.getLoadLayout();

    }


    @Override
    public void pageStartedCallback(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }

    }

    @Override
    public void pageFinishedCallback(String url) {
        //报错时，强制可刷新
        if (mIsError) {
            mBinding.smartrefreshlayout.setEnableRefresh(true);
        } else {
            //根据传入参数设置是否可刷新
            mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        }
        //结束刷新
        mBinding.smartrefreshlayout.finishRefresh();
        if (mLoadService != null) {
            if (mIsError) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                mLoadService.showSuccess();
            }
        }
        mIsError = false;
    }

    @Override
    public void onErrorCallback() {
        mIsError = true;
        mBinding.smartrefreshlayout.finishRefresh();
    }

    @Override
    public void updateTitleCallback(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }

    @Override
    public void updateIconCallback(Bitmap icon) {
        //更新icon
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webview.reload();
    }
}