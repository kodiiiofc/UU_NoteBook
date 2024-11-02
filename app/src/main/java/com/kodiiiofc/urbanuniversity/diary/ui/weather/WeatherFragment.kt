package com.kodiiiofc.urbanuniversity.diary.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kodiiiofc.urbanuniversity.diary.R
import com.kodiiiofc.urbanuniversity.diary.databinding.FragmentWeatherBinding
import com.squareup.picasso.Picasso

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private var viewModel: WeatherViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(
            this,
            WeatherViewModelFactory(requireContext())
        )[WeatherViewModel::class.java]

        observeViewModel()
        viewModel?.getCurrentWeatherByLocation()

        _binding = FragmentWeatherBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.updateBtn.setOnClickListener {
            viewModel?.getCurrentWeatherByCity(binding.cityEt.text.toString().trim())
        }

        return root
    }

    private fun observeViewModel() {
        viewModel?.city?.observe(viewLifecycleOwner) {
            binding.cityTv.text = it
        }
        viewModel?.temp?.observe(viewLifecycleOwner) {
            binding.tempTv.text = "$it ${getString(R.string.units_deg_c)}"
        }
        viewModel?.pressure?.observe(viewLifecycleOwner) {
            binding.pressureTv.text = "$it ${getString(R.string.units_pressure)}"
        }
        viewModel?.humidity?.observe(viewLifecycleOwner) {
            binding.humidityTv.text = "${getString(R.string.tv_humidity)} $it %"
        }
        viewModel?.windDeg?.observe(viewLifecycleOwner) {
            binding.windDegTv.text = "${getString(R.string.tv_wind_deg)} $it"
        }
        viewModel?.windSpeed?.observe(viewLifecycleOwner) {
            binding.windSpeedTv.text = "$it ${getString(R.string.units_wind_speed)}"
        }
        viewModel?.iconUrl?.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(binding.iconIv)
        }
    }

}