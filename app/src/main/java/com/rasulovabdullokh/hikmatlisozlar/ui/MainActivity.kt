package com.rasulovabdullokh.hikmatlisozlar.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.rasulovabdullokh.hikmatlisozlar.R
import com.rasulovabdullokh.hikmatlisozlar.core.adapter.ViewPagerAdapter
import com.rasulovabdullokh.hikmatlisozlar.core.database.DataBase
import com.rasulovabdullokh.hikmatlisozlar.core.models.PageData
import com.rasulovabdullokh.hikmatlisozlar.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), SendData {

    private val link = "There will be a link to the application."
    private var quote = ""
    private var author = ""
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
        adapter.setOnClick(this)
        adapter.setViewScroll(binding.onBoard)
        loadBoardData()
        share()
        setStatusBar()

    }

    private fun share() {
        binding.shareImage.setOnClickListener() {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"

            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "$quote\n\n$author\n\nUlug' donishmandlarning hikmatli so'zlaridan bahramand bo'ling. Qalblaringizni go'zal hikmat jilolari ila to'ldiring... \"Hikmatli So'zlar\" dasturimizni ko'chirib oling\n\n $link"
            )
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

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

    }

    private fun sendData(string: String) {
        val myIntent = Intent(Intent.ACTION_SEND)
        myIntent.type = "text/plain"
        myIntent.setPackage(string)
        myIntent.putExtra(
            Intent.EXTRA_TEXT,
            "$quote\n\n$author\n\nUlug' donishmandlarning hikmatli so'zlaridan bahramand bo'ling. Qalblaringizni go'zal hikmat jilolari ila to'ldiring... \"Hikmatli So'zlar\" dasturimizni ko'chirib oling\n\n $link"
        )
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

    override fun sendData(quote: String, author: String) {
        this.quote = quote
        this.author = author

    }
}