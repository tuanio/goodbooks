package iuh.fivet.app_dev.goodbooks.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import iuh.fivet.app_dev.goodbooks.LoginActivity
import iuh.fivet.app_dev.goodbooks.databinding.FragmentUserBinding
import iuh.fivet.app_dev.goodbooks.utils.Utils

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        database = Firebase.database.reference

        binding.buttonLogout.setOnClickListener { processSignOut() }
        binding.buttonTest.setOnClickListener {
            val userId = Utils.readContentFromFile(
                binding.root.context.applicationContext, // always application context
                "userid"
            )
            Toast.makeText(
                binding.root.context,
                "UserID: $userId",
                Toast.LENGTH_LONG
            ).show()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val userData = Utils.getUserData()

        binding.textViewUsername.text = userData.displayName
        binding.textViewEmail.text = userData.email
    }

    private fun processSignOut() {
        auth.signOut()
        Toast.makeText(
            binding.root.context,
            "Sign out successfully!",
            Toast.LENGTH_LONG
        ).show()

        val intent = Intent(binding.root.context, LoginActivity::class.java)
        startActivity(intent)
    }

}