package com.rasulovabdullokh.testapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.rasulovabdullokh.testapp.R
import com.rasulovabdullokh.testapp.core.adapter.onBoardAdapter
import com.rasulovabdullokh.testapp.core.database.DataBase
import com.rasulovabdullokh.testapp.core.models.PageData
import com.rasulovabdullokh.testapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val adapter = onBoardAdapter()
    private var _binding: ActivityMainBinding?=null
    private val binding get() = _binding!!
    private val data1 = DataBase.getDataBase().sozlar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TestApp)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onBoard.adapter=adapter
        loadBoardData()
        share()


    }

    private fun share() {
        binding.mainShare.setOnClickListener(){
            val title = "link to download the application"
            val description = "Hikmatli Gaplar"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,title)
            shareIntent.putExtra(Intent.EXTRA_TEXT, description)
            startActivity(shareIntent)
        }
        binding.telegramShare.setOnClickListener(){
            sendData("org.telegram.messenger")
        }
        binding.facebookShare.setOnClickListener(){
            sendData("com.facebook.katana")
        }
        binding.whatsAppShare.setOnClickListener{
            sendData("com.whatsapp")
        }

    }

    private fun sendData(string: String){
        val myIntent = Intent(Intent.ACTION_SEND)
        myIntent.type = "text/plain"
        myIntent.setPackage(string)
        myIntent.putExtra(Intent.EXTRA_TEXT, "link to download the application")
        startActivity(Intent.createChooser(myIntent, "Share with"))
    }

    private fun loadBoardData() {
        val data = ArrayList<PageData>()
        for( item in data1){
            data.add(
                PageData(
                    title = item.text,
                    description = item.author

                )
            )
        }
        // Бета версия для проверки функционала Вювпейджер В следующих обновлениях данные будем брать с базы SQLite 1/00/30 36

        /*data.add(
            PageData(
            title = "Halollikni ulug'lagan kishini Yaratgan Egam saxiy qiladi, ibodatda shayton vasvasidan asraydi.",
                description = "Az-Zamaxshariy",
                image = R.drawable.zamaxshariy
        )
        )
        data.add(
            PageData(
                title = "Shukr qilish rizqingga baraka beradi. Noshukrlik yo'qchilikka olib beradi.",
                description = "Az-Zamaxshariy",
                image = R.drawable.zamaxshariy
            )
        )
        data.add(
            PageData(
                title = "Biror ish boshlaganingizda landavurlik bilan ish qilsangiz, ko'zlagan niyatlaringizga yeta olmaysiz.",
                description = "Az-Zamaxshariy",
                image = R.drawable.zamaxshariy
            )
        )
        data.add(
            PageData(
                title = "Yaxshilar, mo'minlar davrasida ko'proq bo'l, qalb ziyoga to'lsin, har bir holatda Alloh. Azza va Jalla uchun sadoqat bilan ibodat qil.",
                description = "Yunus Ibn Ubayd",
                image = R.drawable.yunusibnubayd
            )
        )*/
        adapter.data=data
    }
}