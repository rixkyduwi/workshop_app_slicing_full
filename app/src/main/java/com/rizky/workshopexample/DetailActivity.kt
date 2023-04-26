package com.rizky.workshopexample

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailActivity:AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        webView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://maps.google.com/maps/search/BahariWaterpark")
        webView.settings.javaScriptEnabled = true
        webView.evaluateJavascript("var a = document.getElementById(\"scene-footer-container\"); a.style.display = \"none\";",null)
        webView.settings.setSupportZoom(true)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webView.canGoBack())
            webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }
}