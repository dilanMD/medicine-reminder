package com.deluxan.medicine.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.DayOfWeek
import java.time.Clock

/**
 * Created by Dilan M Deluxan on 30-Dec-20 AD at 5:04 PM
 */

@Entity
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "medicine_name")
    val name: String,
    @ColumnInfo(name = "medicine_days")
    val days: ArrayList<String>,
    @ColumnInfo(name = "medicine_reminder")
    val reminder: String
)

class DaysTypeConverter {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>): String {
        return Gson().toJson(list)
    }
}
