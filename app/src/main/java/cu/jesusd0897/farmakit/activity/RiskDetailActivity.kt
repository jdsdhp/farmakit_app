package cu.jesusd0897.farmakit.activity

import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.model.Risk
import cu.jesusd0897.farmakit.util.KNav
import java.util.*
import kotlin.math.abs

class RiskDetailActivity : BaseActivity() {

    private lateinit var risk: Risk
    private lateinit var toolbar: Toolbar
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var iconView: AppCompatImageView
    private lateinit var titleView: AppCompatTextView
    private lateinit var subtitleView: AppCompatTextView
    private lateinit var textView: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_risk_detail)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        risk = intent.getParcelableExtra(KNav.EXTRA_ITEM_TAG)!!
        initViews()
        setData()
    }

    private fun initViews() {
        iconView = findViewById(R.id.icon_view)
        titleView = findViewById(R.id.title_view)
        subtitleView = findViewById(R.id.subtitle_view)
        textView = findViewById(R.id.text)
        appBarLayout = findViewById(R.id.app_bar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange > -100) {
                toolbar.title = getString(risk.name).toUpperCase(Locale.getDefault())
                toolbar.subtitle = getString(risk.subtitle)
            } else {
                title = ""
                toolbar.subtitle = ""
            }
        })
    }

    private fun setData() {
        iconView.setImageResource(risk.icon)
        titleView.text = getString(risk.name).toUpperCase(Locale.getDefault())
        subtitleView.text = getString(risk.subtitle)
        textView.text = getString(risk.description)
    }

}