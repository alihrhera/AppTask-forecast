package hrhera.task.forecast.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hrhera.task.forecast.domain.wether.Clouds
import hrhera.task.forecast.domain.wether.Main
import hrhera.task.forecast.domain.wether.Sys
import hrhera.task.forecast.domain.wether.Weather
import hrhera.task.forecast.domain.wether.Wind

class ObjectsMapper {

    @TypeConverter
    fun fromClouds(clouds: Clouds?): String? {
        return Gson().toJson(clouds)
    }

    @TypeConverter
    fun toClouds(cloudsString: String?): Clouds? {
        return Gson().fromJson(cloudsString, Clouds::class.java)
    }

    @TypeConverter
    fun fromMain(main: Main?): String? {
        return Gson().toJson(main)
    }

    @TypeConverter
    fun toMain(mainString: String?): Main? {
        return Gson().fromJson(mainString, Main::class.java)
    }

    @TypeConverter
    fun fromSys(sys: Sys?): String? {
        return Gson().toJson(sys)
    }

    @TypeConverter
    fun toSys(sysString: String?): Sys? {
        return Gson().fromJson(sysString, Sys::class.java)
    }

    @TypeConverter
    fun fromWeatherList(weather: List<Weather?>?): String? {
        return Gson().toJson(weather)
    }

    @TypeConverter
    fun toWeatherList(weatherString: String?): List<Weather?>? {
        val listType = object : TypeToken<List<Weather?>?>() {}.type
        return Gson().fromJson(weatherString, listType)
    }

    @TypeConverter
    fun fromWind(wind: Wind?): String? {
        return Gson().toJson(wind)
    }

    @TypeConverter
    fun toWind(windString: String?): Wind? {
        return Gson().fromJson(windString, Wind::class.java)
    }
}
