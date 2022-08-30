package com.rasulovabdullokh.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onBoard.adapter=adapter
        loadBoardData()



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