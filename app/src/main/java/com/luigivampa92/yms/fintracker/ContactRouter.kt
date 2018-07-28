package com.luigivampa92.yms.fintracker

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class ContactRouter(private val context: Context) {

    fun openVkContactPage(pageUrl: String) {
        val contactIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
        var vkAppFound = false

        val otherApps = context.packageManager.queryIntentActivities(contactIntent, 0)
        for (otherApp in otherApps) {
            if (otherApp.activityInfo.applicationInfo.packageName == Constants.packageNameVk) {

                val otherAppActivity = otherApp.activityInfo
                val componentName = ComponentName(
                        otherAppActivity.applicationInfo.packageName,
                        otherAppActivity.name
                )
                contactIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                contactIntent.component = componentName
                context.startActivity(contactIntent)
                vkAppFound = true
                break

            }
        }

        if (!vkAppFound) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
            webIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(webIntent)
        }
    }

    fun openTgContactPage(pageUrl: String) {
        val contactIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
        var tgAppFound = false

        val otherApps = context.packageManager.queryIntentActivities(contactIntent, 0)
        for (otherApp in otherApps) {
            if (otherApp.activityInfo.applicationInfo.packageName == Constants.packageNameTg) {

                val otherAppActivity = otherApp.activityInfo
                val componentName = ComponentName(
                        otherAppActivity.applicationInfo.packageName,
                        otherAppActivity.name
                )
                contactIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                contactIntent.component = componentName
                context.startActivity(contactIntent)
                tgAppFound = true
                break

            }
        }

        if (!tgAppFound) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
            webIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(webIntent)
        }
    }

    fun openSendEmailPage(email: String) {
        val contactIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        var gmailAppFound = false

        val otherApps = context.packageManager.queryIntentActivities(contactIntent, 0)
        for (otherApp in otherApps) {
            if (otherApp.activityInfo.applicationInfo.packageName == Constants.packageNameGmail) {
                val otherAppActivity = otherApp.activityInfo
                val componentName = ComponentName(otherAppActivity.applicationInfo.packageName, otherAppActivity.name)
                contactIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                contactIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_info_about_app_email_subject))
                contactIntent.component = componentName
                context.startActivity(contactIntent)
                gmailAppFound = true
                break
            }
        }

        if (!gmailAppFound) {
            val directMailtoIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email))
            directMailtoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            directMailtoIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_info_about_app_email_subject))
            try {
                context.startActivity(directMailtoIntent)
            }
            catch (e: ActivityNotFoundException) {
                Toast.makeText(context, R.string.text_info_about_app_email_no_client, Toast.LENGTH_SHORT).show()
            }
        }
    }
}