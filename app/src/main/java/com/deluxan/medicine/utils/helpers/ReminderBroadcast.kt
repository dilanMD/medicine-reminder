package com.deluxan.medicine.utils.helpers

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.deluxan.medicine.R
import com.deluxan.medicine.view.activity.NotifyActivity

/**
 * Created by Dilan M Deluxan on 01-Jan-21 AD at 2:24 PM
 */

class ReminderBroadcast : BroadcastReceiver() {
    private val TAG = ReminderBroadcast::class.java.name

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
//            val builder: NotificationCompat.Builder = NotificationCompat.Builder(it,it.resources.getString(R.string.notify_channel_id))
//                .setSmallIcon(R.drawable.ic_notification_black)
//                .setContentTitle(it.resources.getString(R.string.reminder_title))
//                .setContentText(it.resources.getString(R.string.reminder_msg))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//            val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(it)
//            notificationManager.notify(200,builder.build())

            val notificationManager: NotificationManager = it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val intent: Intent = Intent(it, NotifyActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            val pendingIntent: PendingIntent = PendingIntent.getActivity(it,100, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = NotificationCompat.Builder(it, it.resources.getString(R.string.notify_channel_id))
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification_black)
                .setContentTitle(it.resources.getString(R.string.reminder_title))
                .setContentText(it.resources.getString(R.string.reminder_msg))
                .setAutoCancel(true)

            notificationManager.notify(100, builder.build())
        }
    }
}