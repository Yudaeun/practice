package com.example.businesscardapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscardapp.data.Card
import com.example.businesscardapp.databinding.ItemCardBinding

class CardAdapter(private val onItemClick: (Card) -> Unit, private val onItemLongClick: (Card) -> Unit) :
    ListAdapter<Card, CardAdapter.CardViewHolder>(CardDiffCallback()) {

        class CardViewHolder(private val binding: ItemCardBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(card: Card, onItemClick: (Card) -> Unit, onItemLongClick: (Card) -> Unit) {
                    binding.cardName.text = card.name
                    binding.cardPhone.text = card.phoneNumber
                    binding.cardImage.setImageURI(Uri.parse(card.imagePath))
                    binding.root.setOnClickListener{ onItemClick(card) }
                    binding.root.setOnLongClickListener {
                        onItemLongClick(card)
                        true
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardAdapter.CardViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick, onItemLongClick)
    }
}

class CardDiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean = oldItem == newItem

}
