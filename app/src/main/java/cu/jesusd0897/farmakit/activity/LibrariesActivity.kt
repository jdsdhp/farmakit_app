package cu.jesusd0897.farmakit.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.fragment.LibrariesFragment
import cu.jesusd0897.farmakit.util.KNav

class LibrariesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libraries)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        KNav.replaceFragment(
            supportFragmentManager, LibrariesFragment.newInstance(), R.id.container
        )
    }

}