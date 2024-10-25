package nz.co.test.transactions

import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import nz.co.test.base.di.NetworkModule
import nz.co.test.transactions.ui.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule

@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class TransactionsTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    private val mockWebServer = MockWebServer()
}