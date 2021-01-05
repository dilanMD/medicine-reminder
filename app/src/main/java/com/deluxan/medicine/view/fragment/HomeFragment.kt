package com.deluxan.medicine.view.fragment

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.deluxan.medicine.R
import com.deluxan.medicine.room.database.MedicineDatabase
import com.deluxan.medicine.view.activity.NotifyActivity
import com.deluxan.medicine.view.adapter.MedicinesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.launch
import java.util.*


class HomeFragment : BaseFragment(), View.OnClickListener {
    private val TAG = HomeFragment::class.java.name

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view?.float_add?.setOnClickListener(this)

        notificationManager = view.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        return view
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        medicines_list.setHasFixedSize(true)
        medicines_list.layoutManager = LinearLayoutManager(context)

        launch {
            context?.let {
                val medicines = MedicineDatabase(it).getMedicineDao().getAllMedicines()
                medicines_list.adapter = MedicinesAdapter(medicines)

                for (medicine in medicines) {
                    val hour = medicine.reminder.split(':')[0]
                    val minute = medicine.reminder.split(':')[1].substring(0, 2)

                    val weeksArray = resources.getStringArray(R.array.week_days)

                    for (day in medicine.days) {
                        val calendar = Calendar.getInstance()
                        calendar.set(Calendar.DAY_OF_WEEK, weeksArray.indexOf(day.toString())+1)
                        calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
                        calendar.set(Calendar.MINUTE, minute.toInt())
                        calendar.set(Calendar.SECOND, 0)

                        Timer().schedule(object : TimerTask() {
                            @RequiresApi(Build.VERSION_CODES.O)
                            override fun run() {
                                createNotification(it)
                            }
                        }, calendar.time)
                    }

                }

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotification(context: Context) {
        val channelId = resources.getString(R.string.notify_channel_id)
        val description = resources.getString(R.string.channel_desc)
        notificationChannel = NotificationChannel(
            channelId,
            description,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(notificationChannel)

        val intent = Intent(context, NotifyActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder = Notification.Builder(context, channelId)
            .setContentTitle(context.resources.getString(R.string.reminder_title))
            .setContentText(context.resources.getString(R.string.reminder_msg))
            .setSmallIcon(R.drawable.ic_notification_black)
            .setContentIntent(pendingIntent)

        notificationManager.notify(200, builder.build())
    }

}