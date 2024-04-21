package com.aliza.alizawikipedia.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.aliza.alizawikipedia.base.BaseFragment
import com.aliza.alizawikipedia.databinding.FragmentWebBinding


class FragmentWeb(private val url:String) : BaseFragment<FragmentWebBinding>(
    FragmentWebBinding::inflate
) {

    inner class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progressBarWebView.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressBarWebView.visibility = View.GONE
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = MyWebViewClient()
        binding.webView.loadUrl(url)
    }
}