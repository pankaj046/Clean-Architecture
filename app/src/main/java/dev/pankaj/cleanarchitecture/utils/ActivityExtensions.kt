package dev.pankaj.cleanarchitecture.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.pankaj.cleanarchitecture.BuildConfig
import java.io.Serializable

fun Activity.showToast(text: String, debug: Boolean = false) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Activity.emailIntent(email: String = "", subject: String = "", body: String = "Hi,") {
    val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null)).apply {
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    startActivity(intent)
}

fun Activity.openAppInPlayStore() {
    val uri = Uri.parse("market://details?id=$packageName")
    val goToMarketIntent = Intent(Intent.ACTION_VIEW, uri).apply {
        addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
    }

    try {
        startActivity(goToMarketIntent)
    } catch (e: ActivityNotFoundException) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=$packageName"))
        startActivity(intent)
    }
}

fun Activity.goToAppSetting(requestCode: Int) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
    startActivityForResult(intent, requestCode)
}

fun Activity.checkPermissionUtil(permission: String, sharedPreferences: SharedPreferences, listener: (ask: Boolean, permanentlyDenied: Boolean) -> Unit) {
    when {
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
            listener(false, false)
        }
        ActivityCompat.shouldShowRequestPermissionRationale(this, permission) -> {
            listener(true, false)
        }
        !sharedPreferences.getBoolean(permission, false) -> {
            listener(true, false)
            sharedPreferences.edit().putBoolean(permission, true).apply()
        }
        else -> {
            listener(true, true)
        }
    }
}

fun Activity.copyClipboard(label: String?, value: String?, showToast: Boolean = true) {
    val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, value)
    clipboard.setPrimaryClip(clip)

    if (showToast) {
        Toast.makeText(this, "$label copied", Toast.LENGTH_SHORT).show()
    }
}

fun Activity.startNewActivity(clazz: Class<*>) {
    startActivity(Intent(this, clazz).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    })
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}
