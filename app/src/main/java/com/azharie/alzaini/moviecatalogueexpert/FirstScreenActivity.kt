package com.azharie.alzaini.moviecatalogueexpert

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.azharie.alzaini.moviecatalogueexpert.home.MainActivity

class FirstScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstscreen)

        Handler(mainLooper).postDelayed({
            val intentMain = Intent(this@FirstScreenActivity, MainActivity::class.java)
            startActivity(intentMain)
            finish()
        }, 2000)
    }
}