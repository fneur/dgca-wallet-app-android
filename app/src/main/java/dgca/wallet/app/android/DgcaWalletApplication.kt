/*
 *  ---license-start
 *  eu-digital-green-certificates / dgca-verifier-app-android
 *  ---
 *  Copyright (C) 2021 T-Systems International GmbH and all other contributors
 *  ---
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  ---license-end
 *
 *  Created by osarapulov on 5/7/21 2:19 PM
 */

package dgca.wallet.app.android

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import dagger.hilt.android.HiltAndroidApp
import dgca.verifier.app.android.worker.RulesLoadWorker
import dgca.wallet.app.android.worker.ConfigsLoadingWorker
import dgca.wallet.app.android.worker.CountriesLoadWorker
import dgca.wallet.app.android.worker.ValueSetsLoadWorker
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class DgcaWalletApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        WorkManager.getInstance(this).apply {
            schedulePeriodicWorker<ConfigsLoadingWorker>()
            schedulePeriodicWorker<RulesLoadWorker>()
            schedulePeriodicWorker<CountriesLoadWorker>()
            schedulePeriodicWorker<ValueSetsLoadWorker>()
        }
    }

    private inline fun <reified T : ListenableWorker> WorkManager.schedulePeriodicWorker() =
        this.enqueue(
            PeriodicWorkRequestBuilder<T>(1, TimeUnit.DAYS)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
        )
}