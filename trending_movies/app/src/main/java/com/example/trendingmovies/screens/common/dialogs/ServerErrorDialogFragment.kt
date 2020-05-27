package com.example.trendingmovies.screens.common.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.trendingmovies.R

class ServerErrorDialogFragment : DialogFragment() {

    companion object{
        fun newInstance(): ServerErrorDialogFragment { return ServerErrorDialogFragment() }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(getString(R.string.server_error))
        alertDialogBuilder.setMessage(getString(R.string.server_error_message))
        alertDialogBuilder.setPositiveButton(
            "OK", DialogInterface.OnClickListener { dialogInterface, i ->
                dismiss()
            })

        return alertDialogBuilder.create()
    }
}