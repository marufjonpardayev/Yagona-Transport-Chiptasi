package uz.transport.yagonatransportchiptasi

import android.content.Context
import androidx.core.content.edit

class SharePref(context: Context) {
    private val pref = context.getSharedPreferences("nimadir", Context.MODE_PRIVATE)

    var isSaved: Boolean
    get() = pref.getBoolean("isSaved", false)
    set(value) = pref.edit { this.putBoolean("isSaved", value) }
}