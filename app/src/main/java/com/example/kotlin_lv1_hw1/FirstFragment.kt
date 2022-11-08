package com.example.kotlin_lv1_hw1

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin_lv1_hw1.databinding.FragmentFirstBinding


const val SAVING_KEY = "key"
const val LANDS_N_COLS = 4
const val PORTR_N_COLS = 3


fun createFirstFragment(
    field: String
): FirstFragment {
    return FirstFragment().apply {
        arguments = Bundle().apply { putString("key", field) }
    }
}

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
        var numOfCards = savedInstanceState?.getInt(SAVING_KEY) ?: 0

        binding.totalNum.text = numOfCards.toString()


        val cardsList: MutableList<Int> = (0..numOfCards).toMutableList()
        val orientation = activity?.resources?.configuration?.orientation
            ?: Configuration.ORIENTATION_PORTRAIT
        val ncols: Int
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            ncols = PORTR_N_COLS
        } else {
            ncols = LANDS_N_COLS
        }


        val mainAdapter = MainAdapter(cardsList, this)
        binding.rview.adapter = mainAdapter
        binding.rview.layoutManager = GridLayoutManager(view.context, ncols)

        binding.buttonFirst.setOnClickListener {
            numOfCards++
            binding.totalNum.text = numOfCards.toString()
            cardsList.add(numOfCards)
            mainAdapter.notifyDataSetChanged()
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