package hrhera.task.forecast.features.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import appssquare.projects.HealloCare.utils.ext.arrayadapter.arrangeListWithModelDisplayNameNormalView
import com.window.wndo.utils.arrayadapter.ModelDisplayName
import dagger.hilt.android.AndroidEntryPoint
import hrhera.task.forecast.R
import hrhera.task.forecast.databinding.ActivityMainBinding
import hrhera.task.forecast.domain.cities.City

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)
        enableEdgeToEdge()
        binding.executePendingBindings()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        viewModel.cities.observe(this) {
            renderCities(it)
        }
    }

    private fun renderCities(
        cities: List<City>,
    ) {

        arrangeListWithModelDisplayNameNormalView(
            cities.map {
                ModelDisplayName(it.id, "${it.cityNameAr} ${it.cityNameEn}")
            }, binding.cities, cities.size == 1
        ) { pos ->
            viewModel.setSelectCity(Pair(cities[pos].lat, cities[pos].lon))
        }
    }

}