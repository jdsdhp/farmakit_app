package cu.jesusd0897.farmakit.util

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object KAlert {

    fun buildDialog(
        context: Context,
        @StringRes title: Int?, @StringRes message: Int?,
        @StringRes positiveText: Int?, @StringRes negativeText: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, positiveListener) }
        negativeText?.let { builder.setNegativeButton(it, negativeListener) }
        return builder
    }

    fun buildChoiceDialog(
        context: Context,
        @StringRes title: Int?, @StringRes message: Int?,
        @StringRes positiveText: Int?, @StringRes negativeText: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?,
        @ArrayRes items: Int,
        checkedItem: Int,
        clickListener: DialogInterface.OnClickListener?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, positiveListener) }
        negativeText?.let { builder.setNegativeButton(it, negativeListener) }
        builder.setSingleChoiceItems(items, checkedItem, clickListener)
        return builder
    }

    fun buildMultiChoiceDialog(
        context: Context,
        @StringRes title: Int?, @StringRes message: Int?,
        @StringRes positiveText: Int?, @StringRes negativeText: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?,
        @ArrayRes items: Int,
        checkedItems: BooleanArray,
        clickListener: DialogInterface.OnMultiChoiceClickListener?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, positiveListener) }
        negativeText?.let { builder.setNegativeButton(it, negativeListener) }
        builder.setMultiChoiceItems(items, checkedItems, clickListener)
        return builder
    }

    fun buildSimpleOKDialog(
        context: Context,
        @StringRes title: Int?, @StringRes message: Int?,
        @StringRes positiveText: Int?,
        @DrawableRes icon: Int?, isCancelable: Boolean?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, null) }
        return builder
    }

    fun buildSimpleOKDialog(
        context: Context, title: String?, message: String?, positiveText: String?,
        @DrawableRes icon: Int?, isCancelable: Boolean?
    ): MaterialAlertDialogBuilder {
        val builder = MaterialAlertDialogBuilder(context)
        isCancelable?.let { builder.setCancelable(it) }
        message?.let { builder.setMessage(it) }
        icon?.let { builder.setIcon(it) }
        title?.let { builder.setTitle(it) }
        positiveText?.let { builder.setPositiveButton(it, null) }
        return builder
    }


}

