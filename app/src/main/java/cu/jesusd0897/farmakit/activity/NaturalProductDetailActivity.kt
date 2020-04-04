package cu.jesusd0897.farmakit.activity

import android.graphics.Color
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.database.Tables
import cu.jesusd0897.farmakit.database.model.Image
import cu.jesusd0897.farmakit.database.model.NaturalProduct
import cu.jesusd0897.farmakit.database.repo.NaturalProductRepo
import cu.jesusd0897.farmakit.util.ASSETS_DIRECTORY
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.util.KUtil
import cu.jesusd0897.farmakit.viewmodel.ImageViewModel
import cu.jesusd0897.farmakit.viewmodel.NaturalProductViewModel
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*
import kotlin.math.abs

class NaturalProductDetailActivity : BaseActivity() {

    private lateinit var product: NaturalProduct
    private var image: Image? = null

    private lateinit var toolbar: Toolbar
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var titleView: AppCompatTextView
    private lateinit var subtitleView: AppCompatTextView
    private lateinit var iconView: CircleImageView
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_natural_product_detail)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val id = intent.getIntExtra(KNav.EXTRA_ID_TAG, -1)
        initViews()
        val viewModel = NaturalProductViewModel(application)
        viewModel.get(id.toString()).observe(this, Observer { p ->
            product = p
            setData()
        })
    }

    private fun initViews() {
        titleView = findViewById(R.id.title_view)
        subtitleView = findViewById(R.id.subtitle_view)
        iconView = findViewById(R.id.icon_view)
        fab = findViewById(R.id.fab)
        webView = findViewById(R.id.web_view)
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.setOnTouchListener { _, _ -> true }
        webView.setOnClickListener { }
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
        ImageViewModel(application).get(product.id.toString(), Tables.NATURAL_PRODUCTS)
            .observe(this, Observer { img ->
                image = img
                if (image != null) KUtil.loadImage(
                    iconView, "$ASSETS_DIRECTORY/images/naturals", "${image!!.name}.jpg",
                    R.drawable.medicine_natural_default
                ) else Picasso.get().load(R.drawable.medicine_natural_default).into(iconView)
            })
        fab.setImageResource(if (product.isFavorite) R.drawable.ic_heart else R.drawable.ic_heart_empty)
        fab.setOnClickListener {
            product.isFavorite = !product.isFavorite
            NaturalProductRepo(this).update(product)
            Toast.makeText(
                this, if (product.isFavorite) R.string.added_to_fav else R.string.removed_from_fav,
                Toast.LENGTH_SHORT
            ).show()
        }
        val description = product.description.substring(product.description.indexOf("</"))
        val textColor = KUtil.getStringColorFromAttr(this, android.R.attr.textColor)

        val content = "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;" +
                "src: url(\"file:///android_res/font/hind_regular.ttf\")}body {font-family: MyFont;color: $textColor; padding:8; font-size:14; text-align: justify;}" +
                "</style></head><body>$description</body></html>"
        webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null)
    }

}