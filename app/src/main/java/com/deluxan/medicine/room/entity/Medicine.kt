package com.deluxan.medicine.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import ca.antonious.materialdaypicker.MaterialDayPicker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.time.DayOfWeek
import java.time.Clock

/**
 * Created by Dilan M Deluxan on 30-Dec-20 AD at 5:04 PM
 */

@Entity
data class Medicine(
    @ColumnInfo(name = "medicine_name")
    val name: String,
    @ColumnInfo(name = "medicine_days")
    val days: List<MaterialDayPicker.Weekday>,
    @ColumnInfo(name = "medicine_reminder")
    val reminder: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class DaysTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<MaterialDayPicker.Weekday> {
        val listType = object : TypeToken<List<MaterialDayPicker.Weekday>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<MaterialDayPicker.Weekday?>): String {
        return Gson().toJson(list)
    }
}
