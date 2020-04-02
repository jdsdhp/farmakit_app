package cu.jesusd0897.farmakit.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.config.KSettings
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        initLocale()
    }

    private fun initTheme() {
        val isDark = KSettings.isDarkTheme(this)
        val theme = if (isDark) R.style.MyTheme_Dark else R.style.MyTheme_Light
        super.setTheme(theme)
    }

    private fun initLocale() {
        val locale = Locale(KSettings.getLang(this))
        Locale.setDefault(locale)
        resources.configuration.locale = locale
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }

}