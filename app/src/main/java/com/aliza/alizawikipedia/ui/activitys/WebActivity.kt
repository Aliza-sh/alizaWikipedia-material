package com.aliza.alizawikipedia.ui.activitys

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.aliza.alizawikipedia.R
import com.aliza.alizawikipedia.base.BaseActivity
import com.aliza.alizawikipedia.base.TITLE
import com.aliza.alizawikipedia.base.URL_DATA
import com.aliza.alizawikipedia.base.WEBSITE
import com.aliza.alizawikipedia.base.copyToClipboard
import com.aliza.alizawikipedia.databinding.ActivityWebBinding

class WebActivity : BaseActivity<ActivityWebBinding>() {
    override fun inflateBinding(): ActivityWebBinding = ActivityWebBinding.inflate(layoutInflater)

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

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val bundle = intent.getBundleExtra(URL_DATA)!!
        val url = bundle.getString(WEBSITE)!!
        val title = bundle.getString(TITLE)!!

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = MyWebViewClient()
        if (url != null) {
            binding.webView.loadUrl(url)
        }

        binding.toolbarWebview.title = title
        binding.toolbarWebview.setNavigationOnClickListener {
            finish()
        }

        binding.toolbarWebview.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_copy_address -> {
                    val currentUrl = binding.webView.url!!
                    copyToClipboard(currentUrl)
                }
            }
            true
        }
    }
}