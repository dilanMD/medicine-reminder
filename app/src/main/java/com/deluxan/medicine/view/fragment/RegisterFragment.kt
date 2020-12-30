package com.deluxan.medicine.view.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.deluxan.medicine.R
import kotlinx.android.synthetic.main.fragment_register.view.*

@Suppress("DEPRECATION")
class RegisterFragment : Fragment() {
    private val TAG = RegisterFragment::class.java.name
    private lateinit var email: EditText
    private lateinit var pwd: EditText
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        progressDialog = ProgressDialog(view.context)

        email = view.register_email
        pwd = view.register_pwd

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}