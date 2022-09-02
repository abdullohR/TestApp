package com.rasulovabdullokh.testapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rasulovabdullokh.testapp.R
import com.rasulovabdullokh.testapp.core.adapter.ViewPagerAdapter
import com.rasulovabdullokh.testapp.core.database.DataBase
import com.rasulovabdullokh.testapp.core.models.PageData
import com.rasulovabdullokh.testapp.databinding.ActivityMainBinding

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
        binding.onBoard.adapter = adapter
        loadBoardData()
        share()
        createNotification()

    }

    private fun scheduleNotification() {

    }

    private fun createNotification() {

    }

    private fun share() {
        binding.mainShare.setOnClickListener() {
            val title = "link to download the application"
            val description = "Hikmatli Gaplar"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, title)
            shareIntent.putExtra(Intent.EXTRA_TEXT, description)
            startActivity(shareIntent)
        }
        binding.telegramShare.setOnClickListener() {
            sendData(getString(R.string.Telegram))
        }
        binding.facebookShare.setOnClickListener() {
            sendData(getString(R.string.Facebook))
        }
        binding.whatsAppShare.setOnClickListener {
            sendData(getString(R.string.WhatsApp))
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
                    title = item.text,
                    description = item.author

                )
            )
        }

        adapter.data = data
    }
}