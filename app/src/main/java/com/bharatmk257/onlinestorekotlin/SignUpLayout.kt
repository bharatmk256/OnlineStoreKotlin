package com.bharatmk257.onlinestorekotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.sign_up_layout.*

class SignUpLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_layout)

        sign_up_layout_btnSignIn.setOnClickListener {
            var signInIntent = Intent(this@SignUpLayout, MainActivity::class.java)
            startActivity(signInIntent)
        }

        sign_up_layout_btnSignUp.setOnClickListener {

            if (sign_up_layout_edtPassword.text.toString().equals(sign_up_layout_edtConfirmPassword.text.toString())) {

                // registration process

                val signUpURL =
                    "http://192.168.0.104/OnlineStoreApp/join_new_user.php?email=" +
                            sign_up_layout_edtEmail.text.toString() + "&username=" +
                            sign_up_layout_edtUsername.text.toString() + "&pass=" +
                            sign_up_layout_edtPassword.text.toString()
                val requestQ = Volley.newRequestQueue(this@SignUpLayout)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL, Response.Listener { response ->
                    if (response.equals("A user with this Email Address already exist")){
                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Alert")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.show()
                    }else{
                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Alert")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.show()
                    }

                }, Response.ErrorListener { error ->
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Alert")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.show()

                })

                requestQ.add(stringRequest)

            } else {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.show()
            }
        }
    }
}
