package ru.kpfu.stud.decepticons.quizcard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_card.view.*

class CardItem(
    view: View,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    fun bind(card: Card) {
//        with(itemView) {  alternative using
//            itemView.tv_city.text = city.name
//            tv_country.text = city.country
//        }
        itemView.tv_card.text = card.engText

        itemView.setOnClickListener { action(card.id) }
    }

    companion object {

        fun create(parent: ViewGroup, action: (Int) -> Unit): CardItem = CardItem(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false),
            action
        )
    }
}