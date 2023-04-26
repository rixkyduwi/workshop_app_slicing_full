package com.rizky.workshopexample

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
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

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)

        viewPager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }
        val demoData = arrayListOf(
            R.drawable.bahari,
            R.drawable.bahari_2,
            R.drawable.bahari_3,
            R.drawable.bahari_4
        )

        viewPager.adapter = CarouselRVAdapter(demoData)
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        viewPager.setPageTransformer(compositePageTransformer)

        // calling the action bar
        var actionBar = getSupportActionBar()

        if (actionBar != null) {

            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.title = "Detail Wisata";
            actionBar.setBackgroundDrawable(getDrawable(R.color.bg_bg3));

            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        back_to_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        to_order.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

// this event will enable the back
// function to the button on press

override fun onContextItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        android.R.id.home -> {
            finish()
            return true
        }
    }
    return super.onContextItemSelected(item)
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