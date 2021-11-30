package iuh.fivet.app_dev.goodbooks.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.models.Top100Books
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _top100Books = MutableLiveData<List<Top100Books>>()
    val Top100Books: LiveData<List<Top100Books>> = _top100Books

    init {
        getTop100Books()
    }

    private fun getTop100Books() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _top100Books.value = Api.retrofitService.getTop100Books().data.list_book
                _status.value = ApiStatus.DONE

                Log.d(TAG, "Ok bro")
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _top100Books.value = listOf()

                Log.e(TAG, e.message.toString())
                Log.d(TAG, "Oh no bro")
            }
        }
    }
}