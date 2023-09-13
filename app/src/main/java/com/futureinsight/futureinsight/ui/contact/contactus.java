package com.futureinsight.futureinsight.ui.contact;

import android.content.ActivityNotFoundException;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.org.futureinsight.databinding.FragmentContactusBinding;


    public class contactus extends Fragment {
    private FragmentContactusBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContactusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        try {
            final WebView ContactusView = binding.WebViewContact;
            String Future_Insight_ContactPage_URL="https://future-insight.blog/contact";

            final ProgressBar loadingPB = binding.PBLoading;

            WebSettings webSettings = ContactusView.getSettings();

            //Enable Javascript & alot for the webview loading properties
            ContactusView.getSettings().setJavaScriptEnabled(true);
            ContactusView.getSettings().setDomStorageEnabled(true);
            ContactusView.getSettings().setLoadWithOverviewMode(true);
            ContactusView.getSettings().setUseWideViewPort(true);
            ContactusView.getSettings().setBuiltInZoomControls(true);
            ContactusView.getSettings().setDisplayZoomControls(false);
            ContactusView.getSettings().setSupportZoom(true);
            ContactusView.getSettings().setDefaultTextEncodingName("utf-8");

            ContactusView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            ContactusView.getSettings().setGeolocationEnabled(true);
            ContactusView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            ContactusView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webSettings.setUseWideViewPort(true);
            webSettings.setSavePassword(true);
            webSettings.setSaveFormData(true);
            webSettings.setEnableSmoothTransition(true);

            ContactusView.loadUrl(Future_Insight_ContactPage_URL);

            ContactusView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    loadingPB.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    loadingPB.setVisibility(View.GONE);
                }
            });

            ContactusView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keycode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN)
                    {
                        switch (keycode)
                        {
                            case KeyEvent.KEYCODE_BACK:
                                if (ContactusView.canGoBack())
                                {
                                    ContactusView.goBack();
                                }
                                break;
                        }
                    }
                    return false;
                }
            });
        }
        catch (ActivityNotFoundException e) {
            System.out.println("error: "+ e.getMessage());
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        try {
            super.onDestroyView();
            binding = null;
        }
        catch (ActivityNotFoundException e) {
            System.out.println("error: "+ e.getMessage());
        }
    }
}