package cu.jesusd0897.farmakit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vansuita.materialabout.builder.AboutBuilder
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.util.KUtil


class AboutFragment : Fragment() {

    companion object {
        fun newInstance(): AboutFragment = AboutFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = AboutBuilder.with(context)
        .setWrapScrollView(true)
        .setLinksAnimated(true)
        .setShowAsCard(false)
        .setPhoto(R.drawable.jesusd0897)
        //.setCover(R.drawable.about_background_photo)
        .setBackgroundColor(KUtil.getThemeColor(context!!, android.R.attr.colorBackground))
        .setName(R.string.app_dev)
        .setSubTitle(R.string.app_copyright)
        .setBrief(R.string.about_brief)
        .setAppIcon(KUtil.getBitmapFromDrawable(context!!, R.drawable.ic_launcher_round))
        .setAppName(R.string.app_name)
        //.addGooglePlayStoreLink("8002078663318221363")
        .addGitHubLink("jdsdhp/kapp")
        .addFacebookLink("jesusdaniel.saura")
        .addLinkedInLink("in/jdsdhp")
        .addTwitterLink("jdsdhp")
        .addEmailLink("jesus0897@gmail.com")
        .addChangeLogAction { KNav.navToChangelog(context!!) }
        .addFiveStarsAction()
        .setVersionNameAsAppSubTitle()
        .addShareAction(R.string.app_name)
        .build()

}