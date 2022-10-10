package com.rasulovabdullokh.hikmatlisozlar.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rasulovabdullokh.hikmatlisozlar.core.models.PageData
import com.rasulovabdullokh.hikmatlisozlar.databinding.ItemBoardBinding
import com.rasulovabdullokh.hikmatlisozlar.ui.SendData

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var onSend: SendData? = null
    private var viewPager2: ViewPager2? = null

    fun setViewScroll(viewPager2: ViewPager2) {
        this.viewPager2 = viewPager2
    }

    fun setOnClick(onSend: SendData) {
        this.onSend = onSend
    }

    var data = ArrayList<PageData>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(d: PageData) {
            /*binding.ivView.setImageResource(d.image)*/
            binding.title.text = d.title
            binding.description.text = d.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
        viewPager2?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (onSend != null) {
                    onSend?.sendData(data[position].title, data[position].description)

                }
            }
        })
    }

    override fun getItemCount(): Int = data.size
}