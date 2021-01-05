package com.deluxan.medicine.utils.helpers

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.deluxan.medicine.R

/**
 * Created by Dilan M Deluxan on 01-Jan-21 AD at 2:36 PM
 */

@SuppressLint("StringFormatInvalid")
fun createNotificationChannel(context: Context?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context?.let {
            val name = it.resources.getString(R.string.reminder_title)
            val desc = it.resources.getString(R.string.reminder_msg)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                it.resources.getString(R.string.notify_channel_id),
                name,
                importance
            ).apply {
                description = desc
            }
            val notificationManager =
                it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}