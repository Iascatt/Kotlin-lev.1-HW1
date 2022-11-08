package com.example.kotlin_lv1_hw1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin_lv1_hw1.databinding.FragmentFirstBinding
import com.example.kotlin_lv1_hw1.databinding.FragmentSecondBinding


fun createSecondFragment(
    field: String
): SecondFragment {
    return SecondFragment().apply {
        arguments = Bundle().apply { putString("key", field) }
    }
}

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding = FragmentSecondBinding.inflate(inflater, container, false)
            val num = arguments?.getString("key")
            binding.tnum.text = num
            if (num != null) {
                if (num.toInt() % 2 == 0) {
                    binding.tnum.setBackgroundResource(R.color.red)
                } else {
                    binding.tnum.setBackgroundResource(R.color.blue)
                }
            }


            binding.tnum.setOnClickListener {
                val trasaction = this.activity?.supportFragmentManager?.beginTransaction()
                if (trasaction != null) {
                    trasaction.remove(this)
                    trasaction.commit()
                }
            }

            return binding.root
    }

}