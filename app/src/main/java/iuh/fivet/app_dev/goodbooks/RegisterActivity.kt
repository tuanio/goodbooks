package iuh.fivet.app_dev.goodbooks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.databinding.ActivityRegisterBinding
import iuh.fivet.app_dev.goodbooks.models.create_user.CreateUserData
import iuh.fivet.app_dev.goodbooks.models.create_user.PostUserData
import iuh.fivet.app_dev.goodbooks.utils.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private val firebaseTAG = "FirebaseTAG"
    private val registerActivityTag = "RegisterActivityTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        auth = Firebase.auth
        database = Firebase.database.reference

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

    private fun setLoading(isLoading: Boolean) {
        binding.buttonRegister.text = when (isLoading) {
            true -> "Loading ..."
            false -> "Register"
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
        setLoading(true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // sign in success, update UI
                    Log.d(firebaseTAG, "createUserWithEmail:success")

                    // update displayname : username
                    val profileUpdates = userProfileChangeRequest { displayName = username }
                    auth.currentUser!!.updateProfile(profileUpdates)

                    // update to realtime database
                    // the uid corresponding with userId on database
                    auth.currentUser?.let { user ->
                        val postUserData = PostUserData(user.uid, username, email)
                        val retrofitData = Api.retrofitService.createUser(postUserData)
                        retrofitData.enqueue(object: Callback<CreateUserData> {
                            override fun onResponse(
                                call: Call<CreateUserData>,
                                response: Response<CreateUserData>
                            ) {
                                val res = response.body()!!
                                when (res.statusCode) {
                                    1 -> { // success
                                        setLoading(true)

                                        GlobalVariables.userId = res.data.userId

                                        Toast.makeText(
                                            applicationContext,
                                            "Create account successfully! ${GlobalVariables.userId}",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        val intentToMainActivity = Intent(this@RegisterActivity, MainActivity::class.java)
                                        startActivity(intentToMainActivity)
                                    }
                                    else -> { // 0 -> failure
                                        Log.e(registerActivityTag, "Status code failure!")
                                        setLoading(false)
                                    }
                                }
                                Log.v(registerActivityTag, res.msg)
                            }

                            override fun onFailure(call: Call<CreateUserData>, t: Throwable) {
                                Log.e(registerActivityTag, "Fail to create user account!")
                                setLoading(false)
                            }
                        })
                    }
                } else {
                    setLoading(false)
                    // if sign in fails, display a message to the user
                    Log.w(firebaseTAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        applicationContext,
                        task.exception?.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}