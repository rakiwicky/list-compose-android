package nz.co.test.base.test

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider

val applicationContext: Context = ApplicationProvider.getApplicationContext<Context>()

val applicationResources: Resources = applicationContext.resources