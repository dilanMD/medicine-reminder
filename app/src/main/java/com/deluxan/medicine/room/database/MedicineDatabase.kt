package com.deluxan.medicine.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deluxan.medicine.room.dao.MedicineDao
import com.deluxan.medicine.room.entity.DaysTypeConverter
import com.deluxan.medicine.room.entity.Medicine

/**
 * Created by Dilan M Deluxan on 30-Dec-20 AD at 5:25 PM
 */

@Database(entities = [Medicine::class], version = 1)
@TypeConverters(DaysTypeConverter::class)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun getMedicineDao(): MedicineDao

    companion object {
        @Volatile
        private var instance: MedicineDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MedicineDatabase::class.java,
            ""
        ).build()
    }
}