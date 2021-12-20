package iuh.fivet.app_dev.goodbooks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.databinding.ActivityLoginBinding
import iuh.fivet.app_dev.goodbooks.models.get_user.GetUserData
import iuh.fivet.app_dev.goodbooks.utils.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private val loginActivityTag = "LoginActivityTAG"

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

    private fun setLoading(isLoading: Boolean) {
        binding.buttonLogin.text = when (isLoading) {
            true -> "Loading ..."
            false -> "Login"
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
        setLoading(true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(loginActivityTag, "signInWithEmail:success")

                    auth.currentUser?.let { user ->
                        val retrofitData = Api.retrofitService.getUser(user.uid)
                        retrofitData.enqueue(object : Callback<GetUserData> {
                            override fun onResponse(
                                call: Call<GetUserData>,
                                response: Response<GetUserData>
                            ) {
                                val res = response.body()!!
                                when (res.statusCode) {
                                    1 -> {
                                        GlobalVariables.userId = res.data.user.id

                                        Toast.makeText(
                                            baseContext,
                                            "Login successfully!",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        val intentToMainActivity = Intent(this@LoginActivity, MainActivity::class.java)
                                        startActivity(intentToMainActivity)
                                    }
                                    else -> {
                                        Log.e(loginActivityTag, "Fail to create user account!")
                                        setLoading(false)
                                    }
                                }
                                Log.v(loginActivityTag, res.msg)
                            }

                            override fun onFailure(call: Call<GetUserData>, t: Throwable) {
                                Log.e(loginActivityTag, "Fail login! $t")
                                setLoading(false)
                            }
                        })
                    }
                } else {
                    Log.w(loginActivityTag, "signInWithEmail:failure", task.exception)
                    setLoading(false)
                    Toast.makeText(
                        baseContext,
                        task.exception?.message.toString(),
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