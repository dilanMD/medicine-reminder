package com.deluxan.medicine.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deluxan.medicine.R
import com.deluxan.medicine.room.database.MedicineDatabase
import com.deluxan.medicine.room.entity.Medicine
import com.deluxan.medicine.utils.helpers.toast
import com.deluxan.medicine.view.activity.ViewMedicineActivity
import com.deluxan.medicine.view.fragment.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.layout_medicine.view.*

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

    }

    override fun getItemCount(): Int = medicines.size

    class MedicineViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}