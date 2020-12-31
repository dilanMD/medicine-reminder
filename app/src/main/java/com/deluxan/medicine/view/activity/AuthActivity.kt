package com.deluxan.medicine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deluxan.medicine.R
import com.deluxan.medicine.view.adapter.AuthPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        view_pager_auth?.adapter = AuthPagerAdapter(this)

        val tabLayoutMediator = TabLayoutMediator(
            tab_layout_auth,
            view_pager_auth
        ) { tab: TabLayout.Tab, i: Int ->
            when (i) {
                0 -> tab.text = "Login"
                1 -> tab.text = "Register"
                else -> null
            }
        }
        tabLayoutMediator.attach()
    }
}