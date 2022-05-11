package com.ilgiz.testapp.weathertestapp.ui.weatherScreen

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilgiz.testapp.weathertestapp.R
import com.ilgiz.testapp.weathertestapp.data.model.City
import com.ilgiz.testapp.weathertestapp.databinding.FrWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fr_weather) {
    private val viewModel: WeatherViewModel by activityViewModels()

    private var viewBinding: FrWeatherBinding? = null
    private val adapter = WeatherAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FrWeatherBinding.bind(view)
        initSpinnerData()
        initDayCountField()
        initDefaultList()
    }

    private fun initDayCountField() {
        viewBinding?.countOfDaysEdittext?.addTextChangedListener {
            if (viewModel.selectedCity != null && it?.trim()?.isNotEmpty() == true) {
                checkCountDays(it)
            }
        }
    }

    private fun checkCountDays(it: Editable) {
        if (it.toString().toInt() in (1..16)) {
            viewModel.getWeather(viewModel.selectedCity, it.toString().toInt())
        } else {
            viewBinding?.countOfDaysEdittext?.error = getString(R.string.error_text)
        }
    }

    private fun initSpinnerData() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, cities.map { it.cityName }
        )
        viewBinding?.citySelector?.adapter = adapter
        viewBinding?.citySelector?.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                viewModel.selectedCity = cities[position]
                val textCountDays = viewBinding?.countOfDaysEdittext?.text
                if (textCountDays != null) {
                    checkCountDays(textCountDays)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun initDefaultList() {
        viewBinding?.weatherList?.layoutManager = LinearLayoutManager(context)
        viewBinding?.weatherList?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.weathersItem.observe(requireActivity(), {
            adapter.setData(it)
        })
        viewModel.errorMessage.observe(requireActivity(), {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    val cities: List<City> =
        listOf(
            City("České Budějovice", "48.97378881915517", "14.476120512906416"),
            City("New York", "40.79052384606425", "-73.95908688800822"),
            City("Sydney", "-33.8470241774331", "151.0624326592654"),
        )
}