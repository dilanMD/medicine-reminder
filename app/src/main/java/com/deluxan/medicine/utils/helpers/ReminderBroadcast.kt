package com.deluxan.medicine.utils.helpers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.deluxan.medicine.R

/**
 * Created by Dilan M Deluxan on 01-Jan-21 AD at 2:24 PM
 */

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val builder = NotificationCompat.Builder(
                it,
                it.resources.getString(R.string.notify_channel_id)
            )
                .setSmallIcon(R.drawable.ic_notification_black)
                .setContentTitle(it.resources.getString(R.string.reminder_title))
                .setContentText(it.resources.getString(R.string.reminder_msg))
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            val notificationManager = NotificationManagerCompat.from(it)
            notificationManager.notify(200, builder.build())
        }
    }
}