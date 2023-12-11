package com.example.englishforkids

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class GameWonDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.apply {
            setTitle("Congratulations!")
            setMessage("You won the game. Now you can repeat to get more points!")
            setPositiveButton("Okey") { dialog, _ ->
                dialog.dismiss()
            }
        }
        return builder.create()
    }
}