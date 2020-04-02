package cu.jesusd0897.farmakit.config

import android.content.Context
import android.preference.PreferenceManager

internal object KPreferences {

    /**
     * Save a preference passing key and value. Stored in default xml file.
     * @param context App context.
     * @param key     Key for preference.
     * @param value   Key of preference.
     */
    fun savePreference(context: Context, key: String, value: Any?) =
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putString(key, value.toString())
            .apply()

    /**
     * Save a boolean preference passing key and value. Stored in default xml file.
     * @param context App context.
     * @param key     Key for preference.
     * @param value   Key of preference.
     */
    @JvmStatic
    fun saveBooleanPreference(context: Context, key: String, value: Boolean) =
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putBoolean(key, value)
            .apply()

    /**
     * Get a saved preference.
     * @param context  App context.
     * @param keyValue Specific key of preference.
     * @return Value of a preference. Null if was't found.
     */
    @JvmStatic
    fun getDefaultPreference(context: Context, keyValue: String): String? =
        PreferenceManager.getDefaultSharedPreferences(context).getString(keyValue, null)

    /**
     * Get a boolean saved preference.
     * @param context  App context.
     * @param keyValue Specific key of preference.
     * @return Boolean value of a preference. Default value if was't found.
     */
    @JvmStatic
    fun getDefaultBooleanPreference(
        context: Context, keyValue: String, defaultValue: Boolean
    ): Boolean =
        PreferenceManager.getDefaultSharedPreferences(context).getBoolean(keyValue, defaultValue)

    /**
     * Save a list of preferences identified by a general key on a different xml file.
     * @param context    App context.
     * @param generalKey General key for this preferences list.
     * @param nameMap    MutableMap of preferences.
     */
    @JvmStatic
    fun saveCustomPreferences(
        context: Context, generalKey: String, nameMap: MutableMap<String, Any?>
    ) {
        val preferences = context.getSharedPreferences(generalKey, Context.MODE_PRIVATE)
        val edit = preferences.edit()
        if (nameMap.isNotEmpty()) for ((key, value) in nameMap)
            edit.putString(key, value.toString()).apply()
    }

    /**
     * Get a saved preference.
     * @param context    App context.
     * @param generalKey General key for this preferences list.
     * @param keyValue   Specific key of preference.
     * @return Value of a preference. Null if was't found.
     */
    @JvmStatic
    fun getCustomPreference(context: Context, generalKey: String, keyValue: String): String? =
        context.getSharedPreferences(generalKey, Context.MODE_PRIVATE).getString(keyValue, null)

    /**
     * Remove all default saved preferences.
     * @param context App context.
     */
    fun removeAllDefault(context: Context) =
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply()

    /**
     * Remove all custom saved preferences.
     * @param context App context.
     */
    fun removeAllCustom(context: Context) =
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply()

    /**
     * Remove all saved preferences.
     * @param context App context.
     */
    fun removeAll(context: Context) {
        removeAllDefault(context)
        removeAllCustom(context)
    }
}