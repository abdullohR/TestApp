package com.rasulovabdullokh.testapp.ui

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.rasulovabdullokh.testapp.R
import com.rasulovabdullokh.testapp.core.adapter.ViewPagerAdapter
import com.rasulovabdullokh.testapp.core.adapter.channelID
import com.rasulovabdullokh.testapp.core.adapter.notificationID
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
        binding.onBoard.adapter = adapter
        loadBoardData()
        share()
        /*createNotification()*/

    }

    /*@RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification() {
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }
*/
    /*private fun getTime(): Long {
        val minute = 17
        val hour = 14

        val calendar = Calendar.getInstance()
        calendar.set(minute,hour)
        return calendar.timeInMillis
    }*/

    /*@RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification() {
        val name = "Notification channel"
        val desc = "Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }*/

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