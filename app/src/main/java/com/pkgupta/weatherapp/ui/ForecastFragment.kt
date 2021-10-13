package com.pkgupta.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.pkgupta.weatherapp.R
import com.pkgupta.weatherapp.databinding.ForecastFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment(R.layout.forecast_fragment) {
    private val viewModel: ForecastViewModel by activityViewModels()
    private lateinit var binding: ForecastFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ForecastFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.forecastViewModel = viewModel

        binding.tvCity.isSelected = true

        return binding.root
    }
}