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
import iuh.fivet.app_dev.goodbooks.R
import iuh.fivet.app_dev.goodbooks.databinding.FragmentUserBinding

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

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser
        user?.let {
            binding.textViewUsername.text = getString(R.string.username, it.displayName)
            binding.textViewEmail.text = getString(R.string.email, it.email)
        }
    }

    private fun processSignOut() {
        auth.signOut()
        Toast.makeText(
            binding.root.context,
            "Sign out successfully!",
            Toast.LENGTH_LONG
        ).show()

        val intent = Intent(binding.root.context, LoginActivity::class.java)
        requireActivity().finish()
        startActivity(intent)
    }

}