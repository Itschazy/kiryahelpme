package com.chxzyfps.lectionskotlin


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val textInputEditText = textInputLayout.editText as TextInputEditText
        val loginButton = findViewById<Button>(R.id.login_button)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val spannableString = SpannableString(getString(R.string.agreement_full_text))
        loginButton.isEnabled = false
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            loginButton.isEnabled = isChecked
        }
        val contentLayout = findViewById<View>(R.id.contentLayout)
        val progressBar = findViewById<View>(R.id.progressBar)

        loginButton.setOnClickListener {
            if (EMAIL_ADDRESS.matcher(textInputEditText.text.toString()).matches()) {
                contentLayout.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                Snackbar.make(loginButton, "Go to postlogin", Snackbar.LENGTH_LONG).show()
                Handler(Looper.myLooper()!!).postDelayed({
                    contentLayout.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    val dialog = BottomSheetDialog(this)
                    val view = LayoutInflater.from(this).inflate(R.layout.dialog, contentLayout, false)
                    dialog.setCancelable(false)
                    view.findViewById<View>(R.id.closeButton).setOnClickListener{
                        dialog.dismiss()
                    }
                    dialog.setContentView(view)
                    dialog.show()
                }, 3000)
            }
        }


    }
}
