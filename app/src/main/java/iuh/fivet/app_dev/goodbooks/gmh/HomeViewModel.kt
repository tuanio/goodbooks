package iuh.fivet.app_dev.goodbooks.Fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iuh.fivet.app_dev.goodbooks.Models.top100Books
import iuh.fivet.app_dev.goodbooks.Repository.repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: repository):ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName
    private val _top100Books = MutableLiveData<List<top100Books>>()
    val top100BooksLiveData: LiveData<List<top100Books>>
        get() = _top100Books

    init {
        getTop100Books()
    }


    private fun getTop100Books(){
        viewModelScope.launch {
            try {
                _top100Books.value = repository.getTop100Books().data.list_book
                Log.d(TAG, "${_top100Books.value}")
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
}