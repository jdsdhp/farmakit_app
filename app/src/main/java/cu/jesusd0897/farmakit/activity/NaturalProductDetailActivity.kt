package cu.jesusd0897.farmakit.activity

import android.graphics.Color
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.repo.NaturalProductRepo
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.util.KUtil
import cu.jesusd0897.farmakit.viewmodel.NaturalProductViewModel
import java.util.*
import kotlin.math.abs

class NaturalProductDetailActivity : BaseActivity() {

    private lateinit var product: NaturalProduct

    private lateinit var toolbar: Toolbar
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var titleView: AppCompatTextView
    private lateinit var subtitleView: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_natural_product_detail)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val id = intent.getIntExtra(KNav.EXTRA_ID_TAG, -1)
        val viewModel = NaturalProductViewModel(application)
        viewModel.get(id.toString()).observe(this, Observer { p ->
            product = p
            setData()
        })
        initViews()
    }

    private fun initViews() {
        titleView = findViewById(R.id.title_view)
        subtitleView = findViewById(R.id.subtitle_view)
        fab = findViewById(R.id.fab)

        appBarLayout = findViewById(R.id.app_bar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange > -100) {
                toolbar.title = product.name.toUpperCase(Locale.getDefault())
                toolbar.subtitle = product.scientificName
            } else {
                title = ""
                toolbar.subtitle = ""
            }
        })
    }

    private fun setData() {
        titleView.text = product.name
        subtitleView.text = product.scientificName
        fab.setImageResource(if (product.isFavorite) R.drawable.ic_heart else R.drawable.ic_heart_empty)
        fab.setOnClickListener {
            product.isFavorite = !product.isFavorite
            NaturalProductRepo(this).update(product)
        }
        val description = product.description.substring(product.description.indexOf("</"))
        val textColor = KUtil.getStringColorFromAttr(this, android.R.attr.textColor)
        val content =
            "<html><head><style type=\"text/css\">body{color: $textColor; padding:8;font-size:14;}</style></head>" +
                    "<body>${description}</body></html>"
        val webView = findViewById<WebView>(R.id.web_view)
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.setOnTouchListener { _, _ -> true }
        webView.setOnClickListener { }
        webView.loadData(content, "text/html", "UTF-8")
    }

}