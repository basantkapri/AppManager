package com.bashu.kapri.appmanager.di

import android.content.Context
import com.bashu.kapri.appmanager.ui.home.PlaceholderFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AMStorageModule::class])
interface AppGraph {

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppGraph
    }

    fun ableComponent(): AppManagerComponent.Factory

    fun inject(fragment: PlaceholderFragment)
}

