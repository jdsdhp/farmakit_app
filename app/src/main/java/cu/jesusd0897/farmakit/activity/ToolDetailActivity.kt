package cu.jesusd0897.farmakit.activity

import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.fragment.DrippingFragment
import cu.jesusd0897.farmakit.fragment.ImcFragment
import cu.jesusd0897.farmakit.model.Tool
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.util.KNav.EXTRA_ITEM_TAG
import java.util.*
import kotlin.math.abs

class ToolDetailActivity : BaseActivity() {

    private lateinit var tool: Tool

    private lateinit var toolbar: Toolbar
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var iconView: AppCompatImageView
    private lateinit var titleView: AppCompatTextView
    private lateinit var subtitleView: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_detail)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        tool = intent.getParcelableExtra(EXTRA_ITEM_TAG) as Tool
        initViews()
        handleTool(tool)
    }

    private fun initViews() {
        iconView = findViewById(R.id.icon_view)
        titleView = findViewById(R.id.title_view)
        subtitleView = findViewById(R.id.subtitle_view)
        appBarLayout = findViewById(R.id.app_bar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange > -100) {
                toolbar.title = getString(tool.name).toUpperCase(Locale.getDefault())
                toolbar.subtitle = getString(tool.subtitle)
            } else {
                title = ""
                toolbar.subtitle = ""
            }
        })
    }

    private fun handleTool(tool: Tool) {
        iconView.setImageResource(tool.icon)
        titleView.text = getString(tool.name).toUpperCase(Locale.getDefault())
        subtitleView.text = getString(tool.subtitle)
        when (tool.name) {
            R.string.tool_quetelet -> {
                KNav.replaceFragment(
                    supportFragmentManager, ImcFragment.newInstance(), R.id.container
                )
            }
            R.string.tool_dripping_ev -> {
                KNav.replaceFragment(
                    supportFragmentManager, DrippingFragment.newInstance(), R.id.container
                )
            }
        }
    }

}