package com.example.littlelemonfinal.model.common.extension

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.example.littlelemonfinal.R


fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showGenericAlertDialog(message: String){
    AlertDialog.Builder(this).apply {
        setMessage(message)
        setPositiveButton(getString(R.string.ok)){ dialog, _ ->
             dialog.dismiss()
        }
    }.show()
}