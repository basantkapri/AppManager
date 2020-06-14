package com.bashu.kapri.appmanager

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.os.Bundle
import com.bashu.kapri.appmanager.di.AppGraph
import com.bashu.kapri.appmanager.di.AppManagerComponent
import com.bashu.kapri.appmanager.di.AppManagerDependencyProvider
import com.bashu.kapri.appmanager.di.DaggerAppGraph

class AMApplication : Application(), AppManagerDependencyProvider {

    val appGraph: AppGraph by lazy {
        DaggerAppGraph.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        println("StarKitApplication onCreate()")

        registerActivityLifecycleCallbacks(LifeCycleCallbacks)
    }

    override fun onTerminate() {
        super.onTerminate()
        println("StarKitApplication onTerminate()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        println("StarKitApplication onConfigurationChanged(newConfig = $newConfig)")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        println("StarKitApplication onLowMemory()")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        println("StarKitApplication onTrimMemory(level = $level)")
    }

    override fun provideAMComponent(): AppManagerComponent {
        return appGraph.ableComponent().create()
    }
}

object LifeCycleCallbacks : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {
        println("StarKitApplication onActivityPaused(activity = $activity)")
    }

    override fun onActivityStarted(activity: Activity) {
        println("StarKitApplication onActivityStarted(activity = $activity)")
    }

    override fun onActivityDestroyed(activity: Activity) {
        println("StarKitApplication onActivityDestroyed(activity = $activity)")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        println(
            "StarKitApplication onActivitySaveInstanceState(" +
                    "activity = $activity, " +
                    "outState = $outState)"
        )
    }

    override fun onActivityStopped(activity: Activity) {
        println("StarKitApplication onActivityStopped(activity = $activity)")
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        println(
            "StarKitApplication onActivityCreated(" +
                    "activity = $activity, " +
                    "savedInstanceState = $savedInstanceState)"
        )
    }

    override fun onActivityResumed(activity: Activity) {
        println("StarKitApplication onActivityResumed(activity = $activity)")
    }

}