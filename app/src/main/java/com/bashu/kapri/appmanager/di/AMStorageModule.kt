package com.bashu.kapri.appmanager.di

import android.app.Application
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AMStorageModule {

    @Binds
    @Singleton
    abstract fun provideStorage(application: Application): Application
}