package com.bashu.kapri.appmanager.di

import com.bashu.kapri.appmanager.view.PlaceholderFragment
import dagger.Subcomponent

@Subcomponent
interface AppManagerComponent {
    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): AppManagerComponent
    }

    fun inject(fragment: PlaceholderFragment)
}

interface AppManagerDependencyProvider {
    fun provideAMComponent(): AppManagerComponent
}