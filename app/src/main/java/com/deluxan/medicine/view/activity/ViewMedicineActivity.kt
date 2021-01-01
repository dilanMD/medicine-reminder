package com.deluxan.medicine.view.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.deluxan.medicine.R
import com.deluxan.medicine.room.database.MedicineDatabase
import com.deluxan.medicine.room.entity.Medicine
import com.deluxan.medicine.utils.helpers.toast
import kotlinx.android.synthetic.main.activity_view_medicine.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.io.Serializable

class ViewMedicineActivity : AppCompatActivity() {
    private var medicine: Medicine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_medicine)

        medicine =
            intent.getSerializableExtra(resources.getString(R.string.view_medicine)) as? Medicine

        view_medicine_name.text = medicine?.name
        view_medicine_time.text = medicine?.reminder

        val days = medicine?.days

        for (day in days!!) {
            view_medicine_days.text = "${view_medicine_days.text} $day"
        }

        delete_medicine.setOnClickListener {
            medicine?.let { it1 -> deleteAlert(it1, this) }
        }
    }

    private fun deleteAlert(medicine: Medicine, context: Context) {
        AlertDialog.Builder(context).apply {
            setTitle(context.resources.getString(R.string.delete_medicine_title))
            setMessage(context.resources.getString(R.string.delete_medicine_message))
            setPositiveButton(context.resources.getString(R.string.menu_item_delete)) { _, _ ->
                deleteMedicine(medicine, context)
            }
            setNegativeButton(context.resources.getString(R.string.cancel_btn)) { _, _ ->

            }
        }.create().show()
    }

    @Suppress("DEPRECATION")
    private fun deleteMedicine(medicine: Medicine, context: Context) {
        class DeleteMedicine : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                MedicineDatabase(context).getMedicineDao().deleteMedicine(medicine)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                context?.toast("Medicine Deleted")
                val intent = Intent(context, HomeActivity::class.java)
                startActivity(intent)
            }

        }
        DeleteMedicine().execute()
    }
}