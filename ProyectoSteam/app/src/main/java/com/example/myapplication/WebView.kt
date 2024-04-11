package com.example.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView


class WebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val webView: WebView = findViewById(R.id.webView)
        webView.loadUrl("https://store.steampowered.com/app/2835570/Buckshot_Roulette/") // Cambia esta URL por la que necesites mostrar
    }

    fun goBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun formulario(view: View) {
        val intent = Intent(this, Formulario::class.java)
        startActivity(intent)
    }


}
