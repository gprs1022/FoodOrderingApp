package com.example.wavesoffood

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.wavesoffood.databinding.ActivityLoginBinding
import com.example.wavesoffood.databinding.ActivityMainBinding
import com.example.wavesoffood.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class loginActivity : AppCompatActivity() {
     private   var  userName: String ? = null
     private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient
    private  val binding: ActivityLoginBinding by lazy {
         ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail()
            .build()
        // intialize Firebase auth
        auth = Firebase.auth

// intialize Firebase Database

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        database = Firebase.database.reference
     //login with email and password
     binding.loginButton.setOnClickListener {

         //get data from text field

         email = binding.email.text.toString().trim()
         password = binding.password.text.toString().trim()

         if (email.isBlank() || password.isBlank()) {
             Toast.makeText(this, "Please Fill all the details 🙄", Toast.LENGTH_SHORT).show()

         } else {
             createUser(email, password)
             Toast.makeText(this, "Login Successful 😁", Toast.LENGTH_SHORT).show()
         }


     }
        binding.donthavebutton  .setOnClickListener {
            val intent = Intent(this,signupActivity::class.java)
            startActivity(intent)
        }

        binding.signInGoogle.setOnClickListener {

            val signIntent = googleSignInClient.signInIntent
            launcher.launch(signIntent)
        }

    }
    //launcher for google sign-in
    private val launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    if (task.isSuccessful) {
                        val account: GoogleSignInAccount = task.result
                        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                        // Log the ID token for debugging


                        auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                            if (authTask.isSuccessful) {
                                // Successfully sign in with Google
                                startActivity(Intent(this, MainActivity::class.java))
                                Toast.makeText(
                                    this,
                                    "Successfully signed in with Google",
                                    Toast.LENGTH_SHORT
                                ).show()

                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Google Sign-in failed😌:",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    }

                }
            }
    private fun createUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                val user = auth.currentUser
                updateUi(user)
/*
                Toast.makeText(this, " Account created Successfully", Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, loginActivity::class.java)
                startActivity(intent)
                finish()
*/
            } else {
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        saveUserdata()
                        val user = auth.currentUser
                        Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show()

                        updateUi(user)
                    } else{

                    }

                }

            }
        }
    }

    private fun saveUserdata() {

        //get data from text field
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()
        val user = UserModel( userName,email, password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

            database.child("user").child(userId).setValue(user)

    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateUi(user: FirebaseUser?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}


