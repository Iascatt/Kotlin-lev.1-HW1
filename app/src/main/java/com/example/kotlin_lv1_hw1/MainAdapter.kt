package com.example.kotlin_lv1_hw1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val cardsList: MutableList<Int>,
    private val fragment: Fragment,
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, null)

        return MainViewHolder(view, fragment)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(cardsList[position])

    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    class MainViewHolder(itemView: View, fragment: Fragment) : RecyclerView.ViewHolder(itemView) {
        fun bind(cardNum: Int) {
            val card: TextView = itemView.findViewById(R.id.ctext)
            card.text = cardNum.toString()

                if (cardNum % 2 == 0) {
                    card.setBackgroundResource(R.color.red)
                } else {
                    card.setBackgroundResource(R.color.blue)
                }
            }


        init {
            itemView.setOnClickListener {
                val card: TextView = itemView.findViewById(R.id.ctext)
                val trasaction = fragment.activity?.supportFragmentManager?.beginTransaction()
                if (trasaction != null) {
                    trasaction.add(R.id.container, createSecondFragment(card.text.toString()))
                    trasaction.commit()
                }
            }
        }
    }
}
