package com.deluxan.medicine.view.adapter

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.deluxan.medicine.R
import com.deluxan.medicine.room.entity.Medicine
import com.deluxan.medicine.utils.helpers.ReminderBroadcast
import com.deluxan.medicine.utils.helpers.createNotificationChannel
import com.deluxan.medicine.view.activity.ViewMedicineActivity
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.layout_medicine.view.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Created by Dilan M Deluxan on 31-Dec-20 AD at 12:19 PM
 */

class MedicinesAdapter(private val medicines: List<Medicine>) :
    RecyclerView.Adapter<MedicinesAdapter.MedicineViewHolder>() {

    private val TAG = MedicinesAdapter::class.java.name

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_medicine,
            parent,
            false
        )



        return MedicineViewHolder(inflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.view.medicine_name.text = medicines[position].name

        for (day in medicines[position].days) {
            holder.view.medicine_days.text =
                "${holder.view.medicine_days.text} ${day.toString().substring(0, 3)}"
        }

        holder.view.medicine_time.text = medicines[position].reminder

        holder.view.setOnClickListener {
            val intent = Intent(it.context, ViewMedicineActivity::class.java)
            intent.putExtra(
                it.context.resources.getString(R.string.view_medicine),
                medicines[position]
            )
            it.context.startActivity(intent)
        }



        for (medicine in medicines) {
            val date = LocalDateTime.now()
            var dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            dateString = dateString.dropLast(8)

            var time = medicines[position].reminder
            time = time.dropLast(3)

            val dateTime = "$dateString$time:00"

            val localDateTime = LocalDateTime.parse(
                dateTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            )
//            val notifyTime = localDateTime
//                .atZone(ZoneId.systemDefault())
//                .toInstant().toEpochMilli()



            Log.i(TAG, dateTime)
        }

    }

    override fun getItemCount(): Int = medicines.size

    class MedicineViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}