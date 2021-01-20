package com.example.mykotlincalculator21.mvp.view


import android.app.Activity

import android.content.Context
import androidx.annotation.Nullable
import java.lang.ref.WeakReference

open class ActivityView(activity:Activity) {
    private val activityRef:WeakReference<Activity>
    val activity: Activity?
        @Nullable
        get() {
            return activityRef.get()
        }
    val context: Activity?
        @Nullable
        get() {
            return activity
        }

    init{
        activityRef = WeakReference(activity)
    }
}
