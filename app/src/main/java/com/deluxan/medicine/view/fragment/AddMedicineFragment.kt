package com.deluxan.medicine.view.fragment

import android.app.TimePickerDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import ca.antonious.materialdaypicker.MaterialDayPicker
import com.deluxan.medicine.R
import com.deluxan.medicine.room.database.MedicineDatabase
import com.deluxan.medicine.room.entity.Medicine
import kotlinx.android.synthetic.main.fragment_add_medicine.*
import kotlinx.android.synthetic.main.fragment_add_medicine.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddMedicineFragment : Fragment(), View.OnClickListener {
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

        float_save.setOnClickListener {
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

            val medicine = Medicine(nameString, selectedDays, selectedTime)
            saveMedicine(medicine)

//            val action = AddMedicineFragmentDirections.actionSaveMedicine()
//            Navigation.findNavController(it).navigate(action)
        }
    }

    @Suppress("DEPRECATION")
    private fun saveMedicine(medicine: Medicine) {
        class SaveMedicine : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                MedicineDatabase(requireActivity()).getMedicineDao().addMedicine(medicine)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(requireActivity(), "Medicine saved", Toast.LENGTH_SHORT).show()
            }
        }
        SaveMedicine().execute()
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