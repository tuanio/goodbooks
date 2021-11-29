package iuh.fivet.app_dev.goodbooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import iuh.fivet.app_dev.goodbooks.repository.MainViewModel
import iuh.fivet.app_dev.goodbooks.repository.Repository

class MainViewModelFacotry(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}