package com.example.mykotlincalculator21.mvp.view

import android.app.Activity
import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) {
    private val activityRef: WeakReference<Activity>
    val activity: Activity?
        get() {
            return activityRef.get()
        }
    val context: Activity?
        get() {
            return activity
        }

    init {
        activityRef = WeakReference(activity)
    }
}
