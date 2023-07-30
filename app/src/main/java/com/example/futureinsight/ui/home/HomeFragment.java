package com.example.futureinsight.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.futureinsight.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
//      web Site URL
        String future_insight_home= "https://future-insight.blog/";
//       Web View Client
        final WebView homeWebview = binding.idWebViewHome;
//        Progress bar
        final ProgressBar loadingPb = binding.PBLoading;

//        Loading Url
        homeWebview.loadUrl(future_insight_home);
//        Enable Javascript
        homeWebview.getSettings().setJavaScriptEnabled(true);
//      logic for showing or not showing progress bar
        homeWebview.setWebViewClient(new WebViewClient(){
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

//        Local for Back button
        homeWebview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keycode)
                        {
                            case KeyEvent.KEYCODE_BACK:
                                if (homeWebview.canGoBack())
                                {
                                    homeWebview.goBack();
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