package cu.jesusd0897.farmakit.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.fragment.AboutFragment
import cu.jesusd0897.farmakit.util.KNav

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        KNav.replaceFragment(
            supportFragmentManager, AboutFragment.newInstance(), R.id.container
        )
    }

}