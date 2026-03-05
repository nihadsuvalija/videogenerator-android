package com.videogen.studio.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.FileProvider
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Long.toFormattedDate(): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date(this))
}

fun Double.toFormattedDuration(): String {
    val minutes = TimeUnit.SECONDS.toMinutes(this.toLong())
    val seconds = this.toLong() % 60
    return if (minutes > 0) "${minutes}m ${seconds}s" else "${this.toLong()}s"
}

fun View.snack(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun Context.shareVideo(filePath: String) {
    val file = File(filePath)
    if (!file.exists()) return
    val uri: Uri = FileProvider.getUriForFile(
        this,
        "${packageName}.fileprovider",
        file
    )
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "video/mp4"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    startActivity(Intent.createChooser(intent, "Share Video"))
}

fun File.countMp4Files(): Int =
    listFiles { f -> f.isFile && f.extension.lowercase() == "mp4" }?.size ?: 0

fun File.countImageFiles(): Int =
    listFiles { f -> f.isFile && f.extension.lowercase() in listOf("jpg", "jpeg", "png") }?.size ?: 0
