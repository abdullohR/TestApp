package com.rasulovabdullokh.testapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.rasulovabdullokh.testapp.R
import com.rasulovabdullokh.testapp.core.adapter.ViewPagerAdapter
import com.rasulovabdullokh.testapp.core.database.DataBase
import com.rasulovabdullokh.testapp.core.models.PageData
import com.rasulovabdullokh.testapp.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val adapter = ViewPagerAdapter()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val data1 = DataBase.getDataBase().sozlar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TestApp)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        binding.onBoard.adapter = adapter
        loadBoardData()
        share()
        setStatusBar()

    }

    private fun share() {
        binding.shareImage.setOnClickListener() {
            val title = "link to download the application"
            val description = "Hikmatli Gaplar"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, title)
            shareIntent.putExtra(Intent.EXTRA_TEXT, description)
            startActivity(shareIntent)
        }
        binding.telegramShare.setOnClickListener {
            sendData(getString(R.string.Telegram))
        }
        binding.shareWhatsApp.setOnClickListener {
            sendData(getString(R.string.WhatsApp))

        }
    }
    private fun setStatusBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

    }

    private fun sendData(string: String) {
        val myIntent = Intent(Intent.ACTION_SEND)
        myIntent.type = "text/plain"
        myIntent.setPackage(string)
        myIntent.putExtra(Intent.EXTRA_TEXT, "link to download the application")
        startActivity(Intent.createChooser(myIntent, "Share with"))
    }

    private fun loadBoardData() {
        val data = ArrayList<PageData>()
        for (item in data1) {
            data.add(
                PageData(
                    description = item.text,
                    title = item.author

                )
            )
        }
        data.shuffle()

        adapter.data = data
    }
}