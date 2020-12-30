package com.deluxan.medicine.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.deluxan.medicine.room.entity.Medicine

/**
 * Created by Dilan M Deluxan on 30-Dec-20 AD at 5:20 PM
 */

@Dao
interface MedicineDao {
    @Insert
    fun addMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicine")
    fun getAllMedicines(): List<Medicine>
}