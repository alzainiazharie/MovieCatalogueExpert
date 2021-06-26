package com.azharie.alzaini.moviecatalogueexpert.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.azharie.alzaini.moviecatalogueexpert.R
import com.azharie.alzaini.moviecatalogueexpert.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mFragManager = supportFragmentManager
        val mHomeFragment = MovieFragment()
        val fragment = mFragManager.findFragmentByTag(MovieFragment::class.java.simpleName)

        if(fragment !is MovieFragment){
            Log.d("CHECK","Name Fragment: "+ MovieFragment::class.java.simpleName)

            mFragManager
                .beginTransaction()
                .add(R.id.frame_container,mHomeFragment,MovieFragment::class.java.simpleName)
                .commit()
        }

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (item.itemId == R.id.favorite) {
            val uri = Uri.parse("moviecatalogueexpert://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return super.onOptionsItemSelected(item)
    }

}