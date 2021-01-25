package com.example.mykotlincalculator21.mvp.view

import android.app.Activity
import android.app.FragmentManager
import android.content.Context
import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) {

    private val activityRef: WeakReference<Activity>
    val activity: Activity?
        get() = activityRef.get()

    val fragmentManager: FragmentManager?
        get() {
            return activity?.fragmentManager
        }

    init {
        activityRef = WeakReference(activity)
    }
}
