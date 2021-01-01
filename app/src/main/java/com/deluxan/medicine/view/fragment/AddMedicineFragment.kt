package com.deluxan.medicine.view.fragment

import android.app.TimePickerDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import ca.antonious.materialdaypicker.MaterialDayPicker
import com.deluxan.medicine.R
import com.deluxan.medicine.room.database.MedicineDatabase
import com.deluxan.medicine.room.entity.Medicine
import com.deluxan.medicine.utils.helpers.toast
import kotlinx.android.synthetic.main.fragment_add_medicine.*
import kotlinx.android.synthetic.main.fragment_add_medicine.view.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddMedicineFragment : BaseFragment(), View.OnClickListener {
    private val TAG = AddMedicineFragment::class.java.name
    private lateinit var name: EditText
    private lateinit var days: MaterialDayPicker
    private lateinit var time: TextView
    private lateinit var daysError: TextView
    private lateinit var timeError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_medicine, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        name = add_medicine_name
        days = add_medicine_days
        time = show_time
        daysError = days_error
        timeError = time_error

        add_medicine_time.setOnClickListener(this)

        float_save.setOnClickListener { view ->
            val nameString = name.text.toString()
            val selectedDays = days.selectedDays
            val selectedTime = time.text.toString()

            // Validation
            if (nameString.isEmpty()) {
                name.error = resources.getString(R.string.name_error)
                name.requestFocus()
            }

            if (selectedDays.isEmpty()) {
                daysError.visibility = TextView.VISIBLE
            }

            if (selectedTime == "00:00") {
                timeError.visibility = TextView.VISIBLE
            }

            launch {
                val medicine = Medicine(nameString, selectedDays, selectedTime)
                context?.let {
                    MedicineDatabase(it).getMedicineDao().addMedicine(medicine)
                    it.toast(it.resources.getString(R.string.medicine_saved))

                    val action = AddMedicineFragmentDirections.actionSaveMedicine()
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
    }

    private fun selectTime() {
        val selectedDays = days.selectedDays
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, min ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, min)
            show_time.text = SimpleDateFormat("HH:mm a").format(cal.time)
            add_medicine_time.text = resources.getString(R.string.toggle_time_picker2)
            if (selectedDays.isEmpty()) {
                daysError.visibility = TextView.VISIBLE
            } else {
                daysError.visibility = TextView.GONE
            }
        }
        TimePickerDialog(
            context,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add_medicine_time -> selectTime()
        }
    }

}