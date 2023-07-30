package com.example.futureinsight.ui.gallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.futureinsight.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

    binding = FragmentGalleryBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        //Loading web View
        final WebView BlogWebView = binding.idWebViewBlog;
        // URL of the blog Page
        String FutureInsightsBlogURL = "https://future-insight.blog/post/";

        //Loading Progress Bar
        final ProgressBar loadingPB = binding.PBLoading;

        WebSettings webSettings = BlogWebView.getSettings();

        //Enable Javascript & alot for the webview loading properties
        BlogWebView.getSettings().setJavaScriptEnabled(true);
        BlogWebView.getSettings().setDomStorageEnabled(true);
        BlogWebView.getSettings().setLoadWithOverviewMode(true);
        BlogWebView.getSettings().setUseWideViewPort(true);
        BlogWebView.getSettings().setBuiltInZoomControls(true);
        BlogWebView.getSettings().setDisplayZoomControls(false);
        BlogWebView.getSettings().setSupportZoom(true);
        BlogWebView.getSettings().setDefaultTextEncodingName("utf-8");

        //performace improvement settings
        BlogWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        BlogWebView.getSettings().setGeolocationEnabled(true);
        BlogWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        BlogWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);





//      Loading URL into the Blog Post
        BlogWebView.loadUrl(FutureInsightsBlogURL);
        BlogWebView.setWebViewClient(new WebViewClient() {
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

        BlogWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keycode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if (BlogWebView.canGoBack())
                            {
                                BlogWebView.goBack();
                            }
                            break;
                    }
                }
                return false;
            }
        });


        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}