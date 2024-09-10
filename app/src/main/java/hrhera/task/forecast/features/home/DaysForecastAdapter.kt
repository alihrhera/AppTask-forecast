package hrhera.task.forecast.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import hrhera.task.forecast.databinding.ItemDayForecastBinding
import hrhera.task.forecast.databinding.ItemDayTempsBinding
import hrhera.task.forecast.domain.weather.DayForecast
import hrhera.task.forecast.domain.weather.DayTemperature
import java.time.format.DateTimeFormatter

class DaysForecastAdapter : ListAdapter<DayForecast, DaysForecastViewHolder>(object : DiffUtil.ItemCallback<DayForecast>() {
    override fun areItemsTheSame(oldItem: DayForecast, newItem: DayForecast): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: DayForecast, newItem: DayForecast): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DaysForecastViewHolder(ItemDayForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: DaysForecastViewHolder, position: Int) {
        val forecast = getItem(position)
        holder.binding.forecast = forecast
        holder.binding.executePendingBindings()
        bindDayTemperatures(
            binding = holder.binding, dayTemperatures = forecast.dayTemperatures
        )
    }

    /**
     * Bind the day temperatures to the day forecast layout.
     *
     * @param binding the binding of the item day forecast
     * @param dayTemperatures the list of day temperatures
     */
    private fun bindDayTemperatures(
        binding: ItemDayForecastBinding,
        dayTemperatures: List<DayTemperature>,
    ) {
        binding.layDayTemps.removeAllViews()
        dayTemperatures.forEach { dayTemperature ->
            addViewToDayLayout(
                dayTemperature = dayTemperature,
                binding = binding,
            )
        }
    }

    /**
     * Add a view to the day layout for the given [dayTemperature].
     *
     * @param dayTemperature the day temperature to add a view for
     * @param binding the binding of the item day forecast
     */
    private fun addViewToDayLayout(
        dayTemperature: DayTemperature,
        binding: ItemDayForecastBinding,
    ) {
        val view = ItemDayTempsBinding.inflate(LayoutInflater.from(binding.root.context), binding.layDayTemps, false)
        view.textTime.text = dayTemperature.time.format(DateTimeFormatter.ofPattern("HH:mm"))

        view.txtTemperature.text = dayTemperature.temperature
        view.root.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        binding.layDayTemps.addView(view.root)
    }
}