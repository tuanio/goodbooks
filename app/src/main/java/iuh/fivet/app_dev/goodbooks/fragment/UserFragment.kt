package iuh.fivet.app_dev.goodbooks.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.disklrucache.DiskLruCache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
//import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.options
import iuh.fivet.app_dev.goodbooks.LoginActivity
import iuh.fivet.app_dev.goodbooks.databinding.FragmentUserBinding
import iuh.fivet.app_dev.goodbooks.utils.Utils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentUserBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)

//        auth = Firebase.auth
        database = Firebase.database.reference

        binding.buttonLogout.setOnClickListener { processLogout() }
        binding.buttonTest.setOnClickListener { processTest() }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val userData = Utils.getUserData()

        binding.textViewUsername.text = userData.displayName
        binding.textViewEmail.text = userData.email
    }

    private fun processLogout() {
        auth.signOut()
        Toast.makeText(
            binding.root.context,
            "Sign out successfully!",
            Toast.LENGTH_LONG
        ).show()

        val intent = Intent(binding.root.context, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun processTest() {
        val userData = Utils.getUserData()
        val userId = 1
        Toast.makeText(
            binding.root.context,
            "Clicked!",
            Toast.LENGTH_SHORT
        ).show()
        database.child("users")
            .child(userData.uid.toString())
            .child("userId").setValue(userId)
            .addOnSuccessListener {
                Toast.makeText(
                    binding.root.context,
                    "Write done!",
                    Toast.LENGTH_LONG
                ).show()
            }.addOnFailureListener {
                Toast.makeText(
                    binding.root.context,
                    "Write fail!",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}