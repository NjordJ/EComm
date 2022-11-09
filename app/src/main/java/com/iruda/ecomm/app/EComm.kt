package com.iruda.ecomm.app

import android.app.Application
import com.iruda.ecomm.di.appModule
import com.iruda.ecomm.di.dataModule
import com.iruda.ecomm.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EComm : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@EComm)
            workManagerFactory()
            modules(
                listOf(
                    appModule,
                    domainModule,
                    dataModule
                )
            )
        }
    }
}