package iuh.fivet.app_dev.goodbooks.utils

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import iuh.fivet.app_dev.goodbooks.activities.BookDetailsActivity
import iuh.fivet.app_dev.goodbooks.models.utils.UserData
import java.io.File

object Utils {
    private var auth = Firebase.auth

    @JvmStatic
    fun getUserData() : UserData {
        val user = auth.currentUser

        val displayName = user?.displayName
        val email = user?.email
        val photoUrl = user?.photoUrl.toString()
        val uid = user?.uid

        return UserData(displayName, email, photoUrl, uid)
    }

    fun showBook(context: Context, bookId: Int) {
        GlobalVariables.bookId = bookId
        val intent = Intent(context, BookDetailsActivity::class.java)
        context.startActivity(intent)
    }

}