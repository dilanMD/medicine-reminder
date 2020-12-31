package com.deluxan.medicine.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.deluxan.medicine.view.fragment.LoginFragment
import com.deluxan.medicine.view.fragment.RegisterFragment

/**
 * Created by Dilan M Deluxan on 28-Dec-20 AD at 10:01 PM
 */

class AuthPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LoginFragment.newInstance()
            1 -> RegisterFragment.newInstance()
            else -> LoginFragment()
        }
    }

}