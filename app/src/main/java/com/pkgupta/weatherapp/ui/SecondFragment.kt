package com.pkgupta.weatherapp.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.pkgupta.weatherapp.ui.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var binding: FragmentSecondBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSecond.setOnClickListener(View.OnClickListener {
            NavHostFragment.findNavController(this@SecondFragment)
                .navigate(R.id.action_SecondFragment_to_FirstFragment)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}