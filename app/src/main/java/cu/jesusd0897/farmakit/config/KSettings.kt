package cu.jesusd0897.farmakit.config

import android.content.Context
import cu.jesusd0897.farmakit.util.KUtil

//Languages
const val ES_LANG = "es"
const val EN_LANG = "en"

//Root settings
private const val LANG_KEY = "lang"
private const val DARK_THEME_KEY = "dark_theme"
private const val REMEMBER_LOGIN_KEY = "remember_login"
private const val SHOW_WIZARD_KEY = "show_wizard"

//General keys
private const val VERSION_GENERAL_KEY = "version"
private const val AUTH_GENERAL_KEY = "auth"

//AuthConfig file keys
private const val AUTH_REFRESH_TOKEN_KEY = "refresh_token_key"
private const val AUTH_ACTIVE_PROFILE_KEY = "active_profile"

object KSettings {

    fun getLang(context: Context): String {
        val lang: String? = KPreferences.getDefaultPreference(context, LANG_KEY)
        return lang ?: ES_LANG
    }

    fun setLang(context: Context, lang: String) =
        KPreferences.savePreference(context, LANG_KEY, lang)

    fun setDarkTheme(context: Context, isEnable: Boolean) =
        KPreferences.saveBooleanPreference(context, DARK_THEME_KEY, isEnable)

    fun isDarkTheme(context: Context): Boolean =
        KPreferences.getDefaultBooleanPreference(context, DARK_THEME_KEY, false)

    fun setWizardEnabled(context: Context, isEnable: Boolean) =
        KPreferences.saveBooleanPreference(context, SHOW_WIZARD_KEY, isEnable)

    fun isWizardEnabled(context: Context): Boolean =
        KPreferences.getDefaultBooleanPreference(context, SHOW_WIZARD_KEY, true)

    fun setRememberLoginEnabled(context: Context, isEnable: Boolean) =
        KPreferences.saveBooleanPreference(context, REMEMBER_LOGIN_KEY, isEnable)

    fun isRememberLoginEnabled(context: Context): Boolean =
        KPreferences.getDefaultBooleanPreference(context, REMEMBER_LOGIN_KEY, false)

    object AuthConfig {

        fun setRefreshTokenKey(context: Context, token: String) {
            val map: MutableMap<String, Any?> = HashMap()
            map[AUTH_REFRESH_TOKEN_KEY] = KUtil.base64Encode(token)
            KPreferences.saveCustomPreferences(context, AUTH_GENERAL_KEY, map)
        }

        fun getRefreshTokenKey(context: Context): String? {
            val encryptedRefreshTokenKey: String? = KPreferences.getCustomPreference(
                context, AUTH_GENERAL_KEY, AUTH_REFRESH_TOKEN_KEY
            )
            return KUtil.base64Decode(encryptedRefreshTokenKey)
        }

        fun setActiveProfile(context: Context, profileId: String) {
            val map: MutableMap<String, Any?> = HashMap()
            map[AUTH_ACTIVE_PROFILE_KEY] = KUtil.base64Encode(profileId)
            KPreferences.saveCustomPreferences(context, AUTH_GENERAL_KEY, map)
        }

        fun getActiveProfile(context: Context): String? {
            val s: String? = KPreferences.getCustomPreference(
                context, AUTH_GENERAL_KEY, AUTH_ACTIVE_PROFILE_KEY
            )
            return KUtil.base64Decode(s)
        }
    }

    //TODO: Check this.
    /*object VersionConfig {
        */
    /**
     * Check if version is outdated.
     * @param context App context.
     * @return true if is outdated.
     *//*
        fun isOutdated(context: Context): Boolean {
            val status: Version.VersionStatus = getStatus(context)
            return status !== Version.VersionStatus.UPDATED
        }

        */
    /**
     * Compare App version vs API version and deduce a status fo changes.
     * @param context App context.
     * @return Status change.
     *//*
        private fun getStatus(context: Context): Version.VersionStatus {
            val apiVersion = getAPIVersion(context)
            val appVersion = appVersionName
            if (apiVersion.trim { it <= ' ' } == appVersion.trim { it <= ' ' }) return Version.VersionStatus.UPDATED
            val apiTokenizer = StringTokenizer(apiVersion, ".")
            val appTokenizer = StringTokenizer(appVersion, ".")
            val apiDigits = intArrayOf(
                apiTokenizer.nextToken().toInt(),
                apiTokenizer.nextToken().toInt(),
                apiTokenizer.nextToken().toInt()
            )
            val appDigits = intArrayOf(
                appTokenizer.nextToken().toInt(),
                appTokenizer.nextToken().toInt(),
                appTokenizer.nextToken().toInt()
            )
            if (apiDigits[0] > appDigits[0]) return Version.VersionStatus.BIG_CHANGE
            return if (apiDigits[2] > appDigits[1]) Version.VersionStatus.MEDIUM_CHANGE else Version.VersionStatus.MINIMUM_CHANGE
        }

        */
    /**
     * Access to app version name. Util for app - API version control.
     * @return App version name.
     *//*
        val appVersionName: String
            get() = BuildConfig.VERSION_NAME

        */
    /**
     * Access to API version stored has shared preferences. Util for app - API version control.
     * @param context App context.
     * @return API version name.
     *//*
        fun getAPIVersion(context: Context): String {
            return KPreferences.getCustomPreference(
                context,
                VERSION_GENERAL_KEY,
                "apiVersion"
            )
        }

        */
    /**
     * Store API version has shared preference.
     * @param context App context.
     * @param version API version to store.
     *//*
        fun storeVersion(context: Context, version: Version) {
            KPreferences.saveCustomPreferences(
                context,
                VERSION_GENERAL_KEY,
                version.toMap()
            )
        }
    }*/
}