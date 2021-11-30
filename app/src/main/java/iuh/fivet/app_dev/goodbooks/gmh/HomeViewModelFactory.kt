package iuh.fivet.app_dev.goodbooks.Fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import iuh.fivet.app_dev.goodbooks.Repository.repository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val repository: repository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        } else {
            throw IllegalArgumentException("unknown class")
        }
    }
}