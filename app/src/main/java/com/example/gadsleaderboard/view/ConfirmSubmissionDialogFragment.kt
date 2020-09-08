package com.example.gadsleaderboard.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.gadsleaderboard.R
import java.lang.ClassCastException

class ConfirmSubmissionDialogFragment : DialogFragment() {

    private val TAG = "ConfirmSubmissionDialog"

    public interface SendConfirmationState{
        fun onConfirm()
    }

    private lateinit var sendConfirmationState: SendConfirmationState


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.confirm_submission_dialog, container, false)

        val cancelButton: ImageButton = rootView.findViewById(R.id.cancel_image_button)
        val confirmButton: Button = rootView.findViewById(R.id.button_confirm)

        cancelButton.setOnClickListener{
            dismiss()
        }

        confirmButton.setOnClickListener {
            sendConfirmationState.onConfirm()
            dismiss()
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = 800
        params.height = 800
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            sendConfirmationState = activity as SendConfirmationState
        }catch (e :ClassCastException){
            Log.d(TAG,"on attach "+ e.message)
        }
    }


}