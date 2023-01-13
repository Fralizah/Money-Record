package mobileApps.projekAkhir.kelompokSatu.ui.component

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import mobileApps.projekAkhir.kelompokSatu.R

const val notificationId = 1
const val channelId = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class Notification: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_calendar)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(intent.getStringExtra(messageExtra)))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationId, notification)
    }

}