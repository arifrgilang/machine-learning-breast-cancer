package com.arifrgilang.breastcancer

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.diagnose_dialog.view.*

class DiagnoseDialogFragment(
    private val title: String,
    private val content: String
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view =
            LayoutInflater
                .from(requireContext())
                .inflate(R.layout.diagnose_dialog, null)

        view.tvTitle.text = title
        view.tvContent.text = content
        val builder =
            AlertDialog
                .Builder(requireContext())
                .setView(view)
        return builder.create()
    }
}