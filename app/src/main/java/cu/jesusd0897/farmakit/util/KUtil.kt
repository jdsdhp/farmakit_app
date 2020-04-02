package cu.jesusd0897.farmakit.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import cu.jesusd0897.farmakit.R
import java.text.DecimalFormat
import java.util.*

const val TAG_DEBUG = " - jd/dev"
const val ASSETS_DIRECTORY = "file:///android_asset/"
const val REQUEST_CODE_CAMERA = 666

object KUtil {

    /**
     * Request permission through Dialog.
     * @param activity           Activity who request.
     * @param manifestPermission Permission from manifest.
     * @param id       Permission ID.
     */
    fun requestDialogPermissions(activity: Activity, manifestPermission: String, id: Int) =
        ActivityCompat.requestPermissions(activity, arrayOf(manifestPermission), id)

    /**
     * Request permission through Dialog.
     * @param activity            Activity who request.
     * @param manifestPermissions Permissions from manifest.
     * @param id        Permission ID.
     */
    fun requestDialogPermissions(
        activity: Activity, manifestPermissions: Array<String>, id: Int
    ) = ActivityCompat.requestPermissions(activity, manifestPermissions, id)

    fun checkPermission(context: Context, permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    fun getThemeColor(context: Context, @AttrRes color: Int): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(color, value, true)
        return ContextCompat.getColor(context, value.resourceId)
    }

    fun getStringColorFromAttr(context: Context, @AttrRes color: Int): String =
        "#" + Integer.toHexString(getThemeColor(context, color)).substring(2);


    fun getBitmapFromDrawable(context: Context, @DrawableRes drawableId: Int): Bitmap? {
        var drawable = ContextCompat.getDrawable(context, drawableId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) drawable =
            DrawableCompat.wrap(drawable!!).mutate()
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun base64Encode(s: String): String? = Base64.encodeToString(s.toByteArray(), Base64.DEFAULT)

    fun base64Decode(s: String?): String? = String(Base64.decode(s, Base64.DEFAULT))

    fun openWebPage(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(context.packageManager) != null) context.startActivity(intent)
    }

    private val PRESENTATIONS: Map<String, Int> = hashMapOf(
        "areosol" to R.drawable.ic_027_inhaler,
        "ampolleta" to R.drawable.ic_022_ampoule,
        "bulbo" to R.drawable.ic_007_vaccine,
        "cápsula" to R.drawable.ic_012_pills_1,
        "carpule" to R.drawable.ic_ointment,
        "champú" to R.drawable.ic_003_antiseptic,
        "colirio" to R.drawable.ic_018_eye_drops,
        "crema" to R.drawable.ic_021_cream_1,
        "crema vaginal" to R.drawable.ic_021_cream_1,
        "elixir" to R.drawable.ic_medicine_3,
        "frasco" to R.drawable.ic_medicine,
        "gas" to R.drawable.ic_wind,
        "gel oftálmico" to R.drawable.ic_eye_drops,
        "gotas" to R.drawable.ic_raindrops,
        "jalea" to R.drawable.ic_poop,
        "jarabe" to R.drawable.ic_020_syrup,
        "liofilizado para inyección iv" to R.drawable.ic_humidity,
        "líquido" to R.drawable.ic_medicine_3,
        "loción" to R.drawable.ic_hand_holding_water,
        "óvulo" to R.drawable.ic_dot_circle,
        "polvo" to R.drawable.ic_004_medicine_9,
        "polvo para suspensión oral" to R.drawable.ic_026_medicine_6,
        "solución" to R.drawable.ic_034_medicine_4,
        "solución oral" to R.drawable.ic_034_medicine_4,
        "spray anestésico" to R.drawable.ic_spray_can,
        "supositorio" to R.drawable.ic_017_suppositories,
        "suspensión" to R.drawable.ic_026_medicine_6,
        "tableta" to R.drawable.ic_006_pills_3,
        "tableta masticable" to R.drawable.ic_006_pills_3,
        "tableta revestida" to R.drawable.ic_006_pills_3,
        "tableta vaginal" to R.drawable.ic_006_pills_3,
        "ungüento" to R.drawable.ic_031_cream,
        "ungüento oftálmico" to R.drawable.ic_eye_drops,
        "ungüento rectal" to R.drawable.ic_031_cream,
        "vial" to R.drawable.ic_019_test_tube
    )

    @DrawableRes
    fun getPresentationIcon(presentation: String): Int =
        PRESENTATIONS[presentation.toLowerCase(Locale.getDefault())] ?: R.drawable.ic_medicine

    /*fun loadAssetTextFile(context: Context, inFile: String): String? {
        val reader = BufferedReader(InputStreamReader(context.assets.open(inFile)))
        val stringBuffer = StringBuilder("")
        try {
            // do reading, usually loop until end of file reading
            while (true) {
                val mLine = reader.readLine()
                if (mLine != null) stringBuffer.append(mLine)
                else break
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
            android.util.Log.e(TAG_DEBUG, e.message, e)
        }
        return stringBuffer.toString()
    }*/

    fun getTwoDecimals(value: Double): String {
        val df = DecimalFormat("0.00")
        return df.format(value)
    }

    private val IMC_RESULT_SUBTITLES: Map<Int, Pair<Int, Int>> = hashMapOf(
        0 to Pair(R.string.imc_low_weight, R.string.imc_low_weight_1),
        1 to Pair(R.string.imc_low_weight, R.string.imc_low_weight_2),
        2 to Pair(R.string.imc_low_weight, R.string.imc_low_weight_3),
        3 to Pair(R.string.imc_normal_weight, R.string.imc_normal_weight_1),
        4 to Pair(R.string.imc_over_weight, R.string.imc_over_weight_1),
        5 to Pair(R.string.imc_obese_weight, R.string.imc_obese_weight_1),
        6 to Pair(R.string.imc_obese_weight, R.string.imc_obese_weight_2),
        7 to Pair(R.string.imc_obese_weight, R.string.imc_obese_weight_3)
    )

    fun getResultFlag(result: Double): Pair<Int, Int> {
        return when {
            result < 18.50 -> when {
                result < 16.00 -> IMC_RESULT_SUBTITLES.getValue(0)
                result < 17.00 -> IMC_RESULT_SUBTITLES.getValue(1)
                else -> IMC_RESULT_SUBTITLES.getValue(2)
            }
            result < 25.00 -> IMC_RESULT_SUBTITLES.getValue(3)
            result < 30.00 -> IMC_RESULT_SUBTITLES.getValue(4)
            else -> when {
                result < 35.00 -> IMC_RESULT_SUBTITLES.getValue(5)
                result < 40.00 -> IMC_RESULT_SUBTITLES.getValue(6)
                else -> IMC_RESULT_SUBTITLES.getValue(7)
            }
        }
    }
}