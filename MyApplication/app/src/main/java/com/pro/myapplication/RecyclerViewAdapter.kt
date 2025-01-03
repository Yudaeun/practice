package com.pro.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pro.myapplication.databinding.ItemListBinding

class RecyclerViewAdapter(private val list: ArrayList<Person>):
RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>(){

    inner class CustomViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person) {
            binding.ivProfile.setImageResource(item.img)
            binding.tvName.text = item.name
            binding.tvPhoneNumber.text = item.phoneNumber

        }
    }

    override fun onCreateViewHolder( // ViewHolder와 연결된 View 생성
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.CustomViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.CustomViewHolder, position: Int) { // 아이템의 View에 데이터 연결
        holder.bind(list[position])
    }

    override fun getItemCount()= list.size // 아이템의 개수

}