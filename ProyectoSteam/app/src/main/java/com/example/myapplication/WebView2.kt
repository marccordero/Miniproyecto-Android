package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView


class WebView2 : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view2)
        val webView: WebView = findViewById(R.id.webView2)
        webView.loadUrl("https://store.steampowered.com/app/1966720/Lethal_Company/")
    }
    fun formulario2(view: View) {
        val intent = Intent(this, Formulario::class.java)
        startActivity(intent)
    }
    fun goBack2(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}