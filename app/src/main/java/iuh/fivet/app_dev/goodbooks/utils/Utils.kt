package iuh.fivet.app_dev.goodbooks.utils

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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

    @JvmStatic
    fun writeContentToFile(context: Context, filename: String = "userid", fileContent: String) {
        // context is always applicationContext
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContent.toByteArray())
        }
    }

    @JvmStatic
    fun readContentFromFile(context: Context, filename: String): String {
        val file = File(context.filesDir, filename)
        return file.readText()
    }
}