package com.deluxan.medicine.room.dao

import androidx.room.*
import com.deluxan.medicine.room.entity.Medicine

/**
 * Created by Dilan M Deluxan on 30-Dec-20 AD at 5:20 PM
 */

@Dao
interface MedicineDao {
    @Insert
    suspend fun addMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicine ORDER BY id DESC")
    suspend fun getAllMedicines(): List<Medicine>

    @Update
    suspend fun updateMedicine(medicine: Medicine)

    @Delete
    fun deleteMedicine(medicine: Medicine)
}