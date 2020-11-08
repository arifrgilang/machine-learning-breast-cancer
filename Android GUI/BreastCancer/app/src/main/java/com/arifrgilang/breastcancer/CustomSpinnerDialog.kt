package com.arifrgilang.breastcancer

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.spinner_dialog.view.*

class CustomSpinnerDialog(
    private val title: String,
    private val list: List<String>,
    private val callback: OnSpinnerItemSelected
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater
            .from(requireContext())
            .inflate(R.layout.spinner_dialog, null).apply {
                this.tvSpinnerDialog.text = title
                this.lvSpinnerDialog.apply {
                    adapter = generateAdapter()
                    onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                        adapter.getItem(position)?.let{ callback.onSelected(it as String) }
                        dismiss()
                    }
                }
            }
        return AlertDialog.Builder(requireContext())
            .setView(view)
            .create()
    }

    private fun generateAdapter() = ArrayAdapter(
        requireContext(),
        android.R.layout.simple_expandable_list_item_1,
        list
    )

    interface OnSpinnerItemSelected {
        fun onSelected(itemName: String)
    }
}