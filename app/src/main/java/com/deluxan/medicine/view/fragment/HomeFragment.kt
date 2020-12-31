package com.deluxan.medicine.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.deluxan.medicine.R
import com.deluxan.medicine.room.database.MedicineDatabase
import com.deluxan.medicine.view.adapter.MedicinesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {
    private val TAG = HomeFragment::class.java.name

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)



        return view
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        medicines_list.setHasFixedSize(true)
        medicines_list.layoutManager = LinearLayoutManager(context)

        launch {
            context?.let {
                val medicine = MedicineDatabase(it).getMedicineDao().getAllMedicines()

                medicines_list.adapter = MedicinesAdapter(medicine)
            }
        }

        float_add.setOnClickListener {
            val action = HomeFragmentDirections.actionAddMedicine()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun editMedicine() {
        Log.i(TAG, "Edit menu clicked")
    }

    private fun deleteMedicine() {
        Log.i(TAG, "Delete menu clicked")
    }

}