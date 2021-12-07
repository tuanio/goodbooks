package iuh.fivet.app_dev.goodbooks

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import iuh.fivet.app_dev.goodbooks.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        auth = Firebase.auth

        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener { processingRegister() }
    }

    public override fun onStart() {
        super.onStart()

        // check if user is signed in (not-null)
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // reload here
            println("Reload")
        }
    }

    private fun processingRegister() {
        val email = binding.editTextEmail.text.toString()
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()

        if (email.isEmpty() or username.isEmpty() or password.isEmpty()) {
            Toast.makeText(
                baseContext,
                "All field must be filled in!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(
                baseContext,
                "Confirm password does not match!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        createAccount(email, username, password)
    }

    private fun createAccount(email: String, username: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // sign in success, update UI
                    Log.d(TAG, "createUserWithEmail:success")

                    Toast.makeText(
                        baseContext,
                        "Create account successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intentToMainActivity = Intent(this, RegisterActivity::class.java)
                    startActivity(intentToMainActivity)
                } else {
                    // if sign in fails, display a message to the user
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}