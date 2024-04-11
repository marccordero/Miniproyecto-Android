package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buckshot_link(view: View) {
        val intent = Intent(this, WebView::class.java)
        startActivity(intent)
    }
    fun lethal_link(view: View) {
        val intent = Intent(this, WebView2::class.java)
        startActivity(intent)
    }
    fun dealer_link(view: View) {
        val intent = Intent(this, WebView3::class.java)
        startActivity(intent)
    }
}
