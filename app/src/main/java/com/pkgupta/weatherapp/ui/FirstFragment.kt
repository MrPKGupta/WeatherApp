package com.pkgupta.weatherapp.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.pkgupta.weatherapp.ui.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var binding: FragmentFirstBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener(View.OnClickListener {
            NavHostFragment.findNavController(this@FirstFragment)
                .navigate(R.id.action_FirstFragment_to_SecondFragment)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}