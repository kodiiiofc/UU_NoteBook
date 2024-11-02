package com.kodiiiofc.urbanuniversity.diary.ui.weather

import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodiiiofc.urbanuniversity.diary.domain.weather.models.CurrentWeather
import com.kodiiiofc.urbanuniversity.diary.domain.weather.utils.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class WeatherViewModel(private val locationManager: LocationManager, private val apiKey: String) :
    ViewModel() {
    val city = MutableLiveData<String>()
    val temp = MutableLiveData<String>()
    val pressure = MutableLiveData<String>()
    val humidity = MutableLiveData<String>()
    val windDeg = MutableLiveData<String>()
    val windSpeed = MutableLiveData<String>()
    val iconUrl = MutableLiveData<String>()
    private val byLocation = MutableLiveData<Boolean>().apply { value = true }
    private val lat = MutableLiveData<Double>().apply { value = 0.0 }
    private val lon = MutableLiveData<Double>().apply { value = 0.0 }

    fun getCurrentWeatherByCity(_city: String) = viewModelScope.launch {
        byLocation.value = false

        val response = try {
            RetrofitInstance.api.getCurrentWeatherByCity(
                city = _city,
                units = "metric",
                apiKey = apiKey
            )
        } catch (e: IOException) {
            Log.d("EXC", "${e.message}")
            return@launch
        } catch (e: HttpException) {
            Log.d("EXC", "${e.message}")
            return@launch
        }
        val data = response.body()
        Log.d("data", response.body().toString())
        if (response.isSuccessful && data != null) {
            updateData(data)
        }
    }

    fun getCurrentWeatherByLocation() {
        Log.d("location", "starting getLocation()")
        Log.d("location", "location manager = $locationManager ")
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000 * 10,
                500f
            )
            { location ->
                lat.value = location.latitude
                lon.value = location.longitude
                if (byLocation.value!!) updateCurrentWeatherByLocation()
            }
        } catch (e: SecurityException) {
            Log.d("location", "returned NULL")
        }
    }

    private fun updateCurrentWeatherByLocation() = viewModelScope.launch {
        val response = try {
            RetrofitInstance.api.getCurrentWeatherByLocation(
                lat = lat.value.toString(),
                lon = lon.value.toString(),
                units = "metric",
                apiKey = apiKey
            )
        } catch (e: IOException) {
            Log.d("EXC", "${e.message}")
            return@launch
        } catch (e: HttpException) {
            Log.d("EXC", "${e.message}")
            return@launch
        }
        val data = response.body()
        if (response.isSuccessful && data != null) {
            updateData(data)
        }

    }

    private fun updateData(data: CurrentWeather) {
        city.value = data.name
        temp.value = data.main.temp.toInt()
            .toString() + " (" + data.main.temp_min.toInt() + "..." + data.main.temp_max.toInt() + ")"
        pressure.value = (data.main.pressure / 1.33).toInt().toString()
        humidity.value = data.main.humidity.toString()
        windDeg.value = data.wind.deg.toString()
        windSpeed.value = data.wind.speed.toString()
        val iconId = data.weather[0].icon
        iconUrl.value = "https://openweathermap.org/img/wn/$iconId@2x.png"
    }
}