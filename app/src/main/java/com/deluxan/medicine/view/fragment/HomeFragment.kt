package com.deluxan.medicine.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deluxan.medicine.R
import com.deluxan.medicine.room.database.MedicineDatabase
import com.deluxan.medicine.utils.helpers.toast
import com.deluxan.medicine.view.adapter.MedicinesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.layout_medicine.*
import kotlinx.android.synthetic.main.layout_medicine.view.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment(), View.OnClickListener {
    private val TAG = HomeFragment::class.java.name

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view?.float_add?.setOnClickListener(this)
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
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.float_add -> redirectToAddFragment(v)
        }
    }

    private fun redirectToAddFragment(view: View) {
        val action = HomeFragmentDirections.actionAddMedicine()
        Navigation.findNavController(view).navigate(action)
    }

}