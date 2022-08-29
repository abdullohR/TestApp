package com.rasulovabdullokh.testapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rasulovabdullokh.testapp.core.models.PageData
import com.rasulovabdullokh.testapp.databinding.ItemBoardBinding

class onBoardAdapter : RecyclerView.Adapter<onBoardAdapter.ViewHolder>(){
    var data = ArrayList<PageData>()

    set(value){
        field.clear()
        field.addAll(value)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemBoardBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(d: PageData){
            binding.ivView.setImageResource(d.image)
            binding.title.text=d.title
            binding.description.text=d.description

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindData(data[position])
    override fun getItemCount(): Int = data.size
}