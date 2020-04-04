package cu.jesusd0897.farmakit.ui

import android.content.DialogInterface
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatRadioButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.util.KAlert

class KFilterDialog(
    val activity: AppCompatActivity,
    @StringRes var title: Int?,
    @StringRes var message: Int?,
    @DrawableRes var icon: Int?,
    var isCancelable: Boolean?,
    @StringRes var positiveText: Int?,
    @StringRes var negativeText: Int?,
    @StringRes var neutralText: Int?,
    private var clickListener: OnClickListener
) {

    interface OnClickListener {
        fun onPositiveClick(radioIndexChecked: Int, checkedItems: BooleanArray)
        fun onNegativeClick()
        fun onNeutralClick()
    }

    private val rootView = activity.layoutInflater.inflate(R.layout.products_filter_layout, null)

    private val radios: List<AppCompatRadioButton> = arrayListOf(
        rootView.findViewById(R.id.inclusive_radio),
        rootView.findViewById(R.id.exclusive_radio)
    )
    private val checks: List<AppCompatCheckBox> = arrayListOf(
        rootView.findViewById(R.id.teenager_population),
        rootView.findViewById(R.id.old_population),
        rootView.findViewById(R.id.hepatic_population),
        rootView.findViewById(R.id.renal_population),
        rootView.findViewById(R.id.diabetes_population),
        rootView.findViewById(R.id.pregnancy_population),
        rootView.findViewById(R.id.lactation_population),
        rootView.findViewById(R.id.child_population)
    )

    private val dialog = build(activity, title, message, icon, isCancelable,
        positiveText, negativeText, neutralText,
        DialogInterface.OnClickListener { _, _ ->
            clickListener.onPositiveClick(
                if (radios[0].isChecked) 0 else 1,
                booleanArrayOf(
                    checks[0].isChecked, checks[1].isChecked,
                    checks[2].isChecked, checks[3].isChecked,
                    checks[4].isChecked, checks[5].isChecked,
                    checks[6].isChecked, checks[7].isChecked
                )
            )
        },
        DialogInterface.OnClickListener { _, _ -> clickListener.onNegativeClick() },
        DialogInterface.OnClickListener { _, _ ->
            radios[0].isChecked = true
            checks[0].isChecked = true
            checks[1].isChecked = true
            checks[2].isChecked = true
            checks[3].isChecked = true
            checks[4].isChecked = true
            checks[5].isChecked = true
            checks[6].isChecked = true
            checks[7].isChecked = true
            clickListener.onNeutralClick()
        }
    ).create()

    init {
        rootView.findViewById<AppCompatImageButton>(R.id.help_btn).setOnClickListener {
            KAlert.buildSimpleOKDialog(
                activity, R.string.help, R.string.filter_help, R.string.close,
                null, true
            ).show()
        }
    }

    private fun build(
        activity: AppCompatActivity,
        @StringRes title: Int?, @StringRes message: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?,
        @StringRes positiveText: Int?, @StringRes negativeText: Int?, @StringRes neutralText: Int?,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?,
        neutralListener: DialogInterface.OnClickListener?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(activity)
        builder.setView(rootView)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, positiveListener) }
        negativeText?.let { builder.setNegativeButton(it, negativeListener) }
        neutralText?.let { builder.setNeutralButton(it, neutralListener) }
        return builder
    }

    fun show() = dialog.show()
    fun dismiss() = dialog.dismiss()

}
