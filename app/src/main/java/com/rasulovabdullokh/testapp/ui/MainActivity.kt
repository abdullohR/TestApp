package com.rasulovabdullokh.testapp.ui

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        binding.onBoard.adapter = adapter
        loadBoardData()
        /*createNotification()*/

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.telegram -> sendData(getString(R.string.Telegram))
            R.id.facebook -> sendData(getString(R.string.Facebook))
            R.id.whatsApp -> sendData(getString(R.string.WhatsApp))
            R.id.mainSh -> share()
        }
        return true
    }
    private fun share(){
        val title = "link to download the application"
        val description = "Hikmatli So'zalr"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, title)
        shareIntent.putExtra(Intent.EXTRA_TEXT, description)
        startActivity(shareIntent)
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