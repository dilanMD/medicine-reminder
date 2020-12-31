package com.deluxan.medicine.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deluxan.medicine.R
import com.deluxan.medicine.room.entity.Medicine
import kotlinx.android.synthetic.main.layout_medicine.view.*

/**
 * Created by Dilan M Deluxan on 31-Dec-20 AD at 12:19 PM
 */

class MedicinesAdapter(val medicines: List<Medicine>) :
    RecyclerView.Adapter<MedicinesAdapter.MedicineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.layout_medicine, parent, false)
        return MedicineViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.view.medicine_name.text = medicines[position].name

    }

    override fun getItemCount(): Int = medicines.size

    class MedicineViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}