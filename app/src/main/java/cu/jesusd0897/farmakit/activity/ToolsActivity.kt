package cu.jesusd0897.farmakit.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.fragment.ToolsFragment
import cu.jesusd0897.farmakit.util.KNav

class ToolsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        KNav.replaceFragment(supportFragmentManager, ToolsFragment.newInstance(), R.id.container)
    }

}