package iuh.fivet.app_dev.goodbooks

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import iuh.fivet.app_dev.goodbooks.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener { processingLogin() }
        binding.textViewAskUser.setOnClickListener { goToRegister() }
    }

    public override fun onStart() {
        super.onStart()
        // check if user is signed in (not-null)
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(
                baseContext,
                "Welcome back!",
                Toast.LENGTH_LONG
            ).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun processingLogin() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(
                baseContext,
                "All field must be filled in!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        login(email, password)
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")

                    Toast.makeText(
                        baseContext,
                        "Login successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intentToMainActivity = Intent(this, MainActivity::class.java)
                    startActivity(intentToMainActivity)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}