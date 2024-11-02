package com.kodiiiofc.urbanuniversity.diary.ui.weather

import android.content.Context
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity.LOCATION_SERVICE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodiiiofc.urbanuniversity.diary.R

class WeatherViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val apiKey = context.getString(R.string.api)

    private val locationManager: LocationManager =
        context.getSystemService(LOCATION_SERVICE) as LocationManager

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(locationManager = locationManager, apiKey = apiKey) as T
    }

}