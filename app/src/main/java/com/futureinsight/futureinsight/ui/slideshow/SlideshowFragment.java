package com.futureinsight.futureinsight.ui.slideshow;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.DeadSystemException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.org.futureinsight.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        try{
            binding = FragmentSlideshowBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            final WebView ServicesWebView = binding.WebViewServices;
            String FutureInsightSerciesURL = "https://future-insight.blog/services/";
            final ProgressBar loadingPb = binding.PBLoading;

            WebSettings webSettings = ServicesWebView.getSettings();

            //enable Javascript
            ServicesWebView.getSettings().setJavaScriptEnabled(true);
            ServicesWebView.getSettings().setDomStorageEnabled(true);
            ServicesWebView.getSettings().setLoadWithOverviewMode(true);
            ServicesWebView.getSettings().setUseWideViewPort(true);
            ServicesWebView.getSettings().setBuiltInZoomControls(true);
            ServicesWebView.getSettings().setDisplayZoomControls(false);
            ServicesWebView.getSettings().setSupportZoom(true);
            ServicesWebView.getSettings().setDefaultTextEncodingName("utf-8");

            // Performace improvement settings

            ServicesWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            ServicesWebView.getSettings().setGeolocationEnabled(true);
            ServicesWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            ServicesWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webSettings.setUseWideViewPort(true);
            webSettings.setSavePassword(true);
            webSettings.setSaveFormData(true);
            webSettings.setEnableSmoothTransition(true);

            //loading URl into WebView
            ServicesWebView.loadUrl(FutureInsightSerciesURL);

            ServicesWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    loadingPb.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    loadingPb.setVisibility(View.GONE);
                }
            });

            ServicesWebView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keycode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN)
                    {
                        switch (keycode)
                        {
                            case KeyEvent.KEYCODE_BACK:
                                if (ServicesWebView.canGoBack())
                                {
                                    ServicesWebView.goBack();
                                }
                                break;
                        }
                    }
                    return false;
                }
            });
            return root;
        }
        catch(Exception e) {
            System.out.println("DeadSystemException: " + e.getMessage());
        }
        return null;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}