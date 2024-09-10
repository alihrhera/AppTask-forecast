package hrhera.task.forecast.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import hrhera.task.forecast.databinding.ItemDayForecastBinding
import hrhera.task.forecast.databinding.ItemDayTempsBinding
import hrhera.task.forecast.domain.weather.DayForecast
import hrhera.task.forecast.domain.weather.DayTemperature
import hrhera.task.forecast.utils.formatTime
import java.time.format.DateTimeFormatter

class DaysForecastAdapter : ListAdapter<DayForecast, DaysForecastViewHolder>(object : DiffUtil.ItemCallback<DayForecast>() {
    override fun areItemsTheSame(oldItem: DayForecast, newItem: DayForecast): Boolean {
        return oldItem == newItem

    }

    override fun areContentsTheSame(oldItem: DayForecast, newItem: DayForecast): Boolean {
        return oldItem.date == newItem.date
    }
}) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DaysForecastViewHolder(ItemDayForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: DaysForecastViewHolder, position: Int) {
        val forecast = getItem(position)
        holder.binding.forecast = forecast
        holder.binding.executePendingBindings()
        bindDayTemperatures(holder.binding, forecast.dayTemperatures)

    }

    private fun bindDayTemperatures(binding: ItemDayForecastBinding, dayTemperatures: List<DayTemperature>) {
        binding.layDayTemps.removeAllViews()
        if (dayTemperatures.size > 3) {

            dayTemperatures.firstOrNull()?.let {
                addViewToDayLayout(it, binding)
            }
            if ((dayTemperatures.size / 2) + 1 in dayTemperatures.indices) {
                addViewToDayLayout(dayTemperatures[dayTemperatures.size / 2], binding)
                addViewToDayLayout(dayTemperatures[(dayTemperatures.size / 2) + 1], binding)
            }
            dayTemperatures.lastOrNull()?.let {
                addViewToDayLayout(it, binding, false)
            }
        } else {
            dayTemperatures.forEachIndexed { index, dayTemperature ->
                addViewToDayLayout(dayTemperature, binding, index != dayTemperatures.lastIndex)
            }
        }
    }

    private fun addViewToDayLayout(
        it: DayTemperature,
        binding: ItemDayForecastBinding,
        showLine: Boolean = true
    ) {
        val view = ItemDayTempsBinding.inflate(LayoutInflater.from(binding.root.context), binding.layDayTemps, false)
        view.textTime.text = it.time.formatTime()

        view.txtTemperature.text = it.temperature
        view.line.isVisible = showLine
        view.root.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        binding.layDayTemps.addView(view.root)
    }
}