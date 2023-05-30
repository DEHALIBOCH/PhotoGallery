package com.demoapp.photogallery

import android.content.Context
import android.content.SharedPreferences

private const val APP_PREFERENCES = "appPrefs"
private const val PREF_SEARCH_QUERY = "searchQuery"
private const val PREF_LAST_RESULT_ID = "lastResultId"

object QueryPreferences {

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun getStoredQuery(context: Context): String {
        val preferences = getPrefs(context)
        return preferences.getString(PREF_SEARCH_QUERY, "")!!
    }

    fun setStoredQuery(context: Context, query: String) {
        getPrefs(context).edit()
            .putString(PREF_SEARCH_QUERY, query)
            .apply()
    }

    fun getLastResultId(context: Context): String {
        val preferences = getPrefs(context)
        return preferences.getString(PREF_LAST_RESULT_ID, "")!!
    }

    fun setLastResultId(context: Context, lastResultId: String) {
        getPrefs(context).edit()
            .putString(PREF_LAST_RESULT_ID, lastResultId)
            .apply()
    }
}