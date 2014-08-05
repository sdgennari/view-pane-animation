package com.willowtree.test.viewpaneanimation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.willowtree.test.viewpaneanimation.R;

public class WebViewFragment extends Fragment {

    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_web_detail, container, false);

        webView = (WebView) rootView.findViewById(R.id.web_content);
        webView.loadUrl("http://www.google.com/design/");

        return rootView;
    }

    public void setContent(String url) {
        webView.loadUrl(url);
    }
}
