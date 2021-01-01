package com.deluxan.medicine.utils.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.deluxan.medicine.R

/**
 * Created by Dilan M Deluxan on 01-Jan-21 AD at 2:36 PM
 */

fun createNotificationChannel(context: Context) = run {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name: CharSequence = context.resources.getString(R.string.channel_name)
        val desc: String = context.resources.getString(R.string.channel_desc)
        val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            context.resources.getString(R.string.notify_channel_id),
            name, importance
        )
        channel.description = desc

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}