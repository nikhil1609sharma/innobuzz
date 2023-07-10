package com.example.taskapp.util


import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class WhatsAppAccessibilityService : AccessibilityService() {

    val TAG = "MyAccesibiltyService"

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        Log.e(TAG, "onAccessibilityEvent: ", )
        val packageName = event.packageName.toString()
        val packageManager = this.packageManager
        try {
            val appInfo:ApplicationInfo = packageManager.getApplicationInfo(packageName,0)
            val appLabel = packageManager.getApplicationLabel(appInfo)
            Log.e(TAG, "app name is : "+appLabel )
            Toast.makeText(applicationContext, "$appLabel Running", Toast.LENGTH_SHORT).show()
        }catch (e:PackageManager.NameNotFoundException){
         e.stackTrace
        }
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            // Check if the package name matches WhatsApp
            if (event.packageName == "com.snapchat.android") {
                // Display a toast message when WhatsApp is opened
                Log.e(TAG, "onAccessibilityEvent: "+"checked" )
                Toast.makeText(this, "WhatsApp Launched.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInterrupt() {
        Log.e(TAG, "onInterrupt: "+"Interupted" )
    }

    override fun onServiceConnected() {
        val info = AccessibilityServiceInfo()
        info.apply {
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED

            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN


            notificationTimeout = 100
        }

        this.serviceInfo = info
        Log.e(TAG, "onServiceConnected: " )

    }
}
