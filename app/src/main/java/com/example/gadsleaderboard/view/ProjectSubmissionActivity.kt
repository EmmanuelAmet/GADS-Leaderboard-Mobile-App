package com.example.gadsleaderboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.service.NetworkCalls
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectSubmissionActivity : AppCompatActivity(), ConfirmSubmissionDialogFragment.SendConfirmationState {
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var emailAddress: EditText
    private lateinit var projectLink: EditText
    private lateinit var submitButton: Button
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_submission)

        firstName = findViewById(R.id.first_name_edit_text)
        lastName = findViewById(R.id.last_name_edit_text)
        emailAddress = findViewById(R.id.email_address_edit_text)
        projectLink = findViewById(R.id.project_link_edit_text)
        submitButton = findViewById(R.id.submit_button)
        backButton = findViewById(R.id.back_button)


        submitButton.setOnClickListener {
            if(checkValidInputs()){
                val dialog = ConfirmSubmissionDialogFragment()
                dialog.show(supportFragmentManager,"confirm")
            }
        }

        backButton.setOnClickListener {
            finish()
        }

    }

    private fun checkValidInputs(): Boolean {
        if(firstName.text.isEmpty()) {
            firstName.error = getString(R.string.lbl_invalid_input)
            return false
        }
        if(lastName.text.isEmpty()){
            lastName.error = getString(R.string.lbl_invalid_input)
            return false
        }
        if(emailAddress.text.isEmpty()){
            emailAddress.error = getString(R.string.lbl_invalid_input)
            return false
        }
        if(projectLink.text.isEmpty()){
            projectLink.error = getString(R.string.lbl_invalid_input)
            return false
        }
        return true
    }

    override fun onConfirm() {
        NetworkCalls.submitProject(
            firstName.text.toString(),
            lastName.text.toString(),
            emailAddress.text.toString(),
            projectLink.text.toString(),
            object: Callback<Void> {
                val bundle = Bundle()
                val dialog = ProjectSubmissionResponseDialogFragment()
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful){
                        bundle.putBoolean("success",true)
                        dialog.arguments = bundle
                        dialog.show(supportFragmentManager,"response")
                    }
                    else{
                        bundle.putBoolean("success",false)
                        dialog.arguments = bundle
                        dialog.show(supportFragmentManager,"response")
                        Log.d("notSuccessful",response.body().toString())
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    bundle.putBoolean("success",false)
                    dialog.arguments = bundle
                    dialog.show(supportFragmentManager,"response")
                    Log.d("failure",t.message.toString())
                }
            }
        )
    }
}