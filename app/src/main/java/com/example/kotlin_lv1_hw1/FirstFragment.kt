package com.example.kotlin_lv1_hw1

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin_lv1_hw1.databinding.FragmentFirstBinding
import java.lang.invoke.ConstantCallSite


const val SAVING_KEY = "key"
const val LANDS_N_COLS = 4
const val PORTR_N_COLS = 3
const val margin = 50
const val CARD_SIZE = 180


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var numberOfCards = savedInstanceState?.getInt(SAVING_KEY) ?:0
        drawTable(numberOfCards)
        binding.totalNum.text = numberOfCards.toString()
        binding.buttonFirst.setOnClickListener {
            numberOfCards++
            binding.totalNum.text = numberOfCards.toString()
            addCard(numberOfCards)
        }
    }

    private fun addCard(cardNum: Int) {
        val tl = binding.tablelayout
        val row: TableRow
        val orientation = activity?.resources?.configuration?.orientation
            ?: Configuration.ORIENTATION_PORTRAIT
        val ncols: Int
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            ncols = PORTR_N_COLS
        } else {
            ncols = LANDS_N_COLS
        }
        if (cardNum % ncols == 1) {
            row = TableRow(activity)
            row.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            tl.addView(row)
        } else {
            row = binding.tablelayout.getChildAt((cardNum - 1) / ncols) as TableRow
        }
        val card = TextView(activity)

        card.text = cardNum.toString()
        card.textSize = resources.getDimension(R.dimen.text_size)
        card.textAlignment = TEXT_ALIGNMENT_CENTER
        card.setTextColor(resources.getColor(R.color.black))

        if (cardNum % 2 == 0) {
            card.setBackgroundColor(resources.getColor(R.color.red))
        } else {
            card.setBackgroundColor(resources.getColor(R.color.blue))
        }

        val params: TableRow.LayoutParams =
            TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)


        params.height = CARD_SIZE
        params.width = CARD_SIZE
        params.setMargins(margin, margin, margin, margin)
        row.addView(card, params)
    }

    private fun drawTable(numberOfCards: Int) {
        for (i in 1..numberOfCards) {
            addCard(i)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val forSave = binding.totalNum.text.toString().toInt()
        outState.putInt(SAVING_KEY, forSave)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}