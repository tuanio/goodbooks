package iuh.fivet.app_dev.goodbooks.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iuh.fivet.app_dev.goodbooks.model.ListBookRating
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse : MutableLiveData<ListBookRating> = MutableLiveData()

    fun getBooks(){
        viewModelScope.launch {
            val response = repository.getBooks()
        myResponse.value = response
        }
    }
}