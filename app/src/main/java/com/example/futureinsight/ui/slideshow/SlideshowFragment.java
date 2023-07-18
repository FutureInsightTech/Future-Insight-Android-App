package com.example.futureinsight.ui.slideshow;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.futureinsight.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

    binding = FragmentSlideshowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final WebView ServicesWebView = binding.WebViewServices;
        String FutureInsightSerciesURL = "https://future-insight.blog/services/";
        final ProgressBar loadingPb = binding.PBLoading;

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

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}