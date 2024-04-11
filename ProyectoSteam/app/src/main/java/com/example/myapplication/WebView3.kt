package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView


class WebView3 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view3)
        val webView: WebView = findViewById(R.id.webView3)
        webView.loadUrl("https://store.steampowered.com/app/1708850/Drug_Dealer_Simulator_2/")
    }
    fun formulario3(view: View) {
        val intent = Intent(this, Formulario::class.java)
        startActivity(intent)
    }
    fun goBack3(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
