package com.example.androidappfinalproject

import admin.ProfileAdminActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import user.ProfileActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

//--------------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        googleLoginButton.setOnClickListener(this)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
        buttonLogin.setOnClickListener {
            performLogin()
        }
        buttonRegister.setOnClickListener {
            preformRegister()
        }
    }
    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
//--------------------------------------------------------------------------------------------------
    private fun performLogin() {
        val email = emailLogin.text.toString()
        val password = passwordLogin.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please Fill", Toast.LENGTH_SHORT).show()
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                val db = FirebaseFirestore.getInstance()
                val uid = FirebaseAuth.getInstance().uid ?: ""
                val documentRef = db.collection("users").document(uid)
                documentRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            val value = document.getString("type")
                            Log.d("Login", "Successfully logged in: ${document.data}")
                            if (value == "User") {
                                Log.d("Login", "Successfully logged in: $value")
                                Log.d("Login", "Successfully logged in: ${it.result?.user?.uid}")
                                Toast.makeText(this, "Welcome  ", Toast.LENGTH_SHORT).show()
                                val intentLogin = Intent(this, ProfileActivity::class.java)
                                startActivity(intentLogin)
                            }
                            if(value == "Admin") {
                                Log.d("Login", "Successfully logged in: $value")
                                Log.d("Login", "Successfully logged in: ${it.result?.user?.uid}")
                                Toast.makeText(this, "Welcome  ", Toast.LENGTH_SHORT).show()
                                val intentLoginAdmin = Intent(this, ProfileAdminActivity::class.java)
                                startActivity(intentLoginAdmin)
                            }
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
            }
    }

//--------------------------------------------------------------------------------------------------
    private fun preformRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

//--------------------------------------------------------------------------------------------------
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

//--------------------------------------------------------------------------------------------------
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(
                        this,
                        "Login Successful" + "${task.result?.user?.displayName}",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intentLoginGoogle = Intent(this, ProfileActivity::class.java)
                    startActivity(intentLoginGoogle)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()

                }
            }
    }
//--------------------------------------------------------------------------------------------------
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

//--------------------------------------------------------------------------------------------------
    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.googleLoginButton -> signIn()
        }
    }
}

