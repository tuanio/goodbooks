package iuh.fivet.app_dev.goodbooks.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.models.TopBooks
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _Top100Books = MutableLiveData<List<TopBooks>>()
    val TopBooks: LiveData<List<TopBooks>> = _Top100Books

    private val _TopBooksByAuthor = MutableLiveData<List<TopBooks>>()
    val TopBooksByAuthor: LiveData<List<TopBooks>> = _TopBooksByAuthor

    private val _TopBooksByGenre = MutableLiveData<List<TopBooks>>()
    val TopBooksByGenre: LiveData<List<TopBooks>> = _TopBooksByGenre

    private val _TopBooksSimilar = MutableLiveData<List<TopBooks>>()
    val TopBooksSimilar: LiveData<List<TopBooks>> = _TopBooksSimilar

    init {
        getTop100Books()
    }

    private fun getTop100Books() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _Top100Books.value = Api.retrofitService.getTop100Books().data.list_book_top

                _TopBooksByAuthor.value = Api.retrofitService.getTopBooksByAuthor().data.list_book_topAuthor

                _TopBooksByGenre.value = Api.retrofitService.getTopBooksByGenre().data.list_book_topGenre

                _TopBooksSimilar.value = Api.retrofitService.getTopBooksSimilar().data.list_book_topSimilar

                _status.value = ApiStatus.DONE

                Log.d(TAG, "Ok bro, nice !!")
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR

                Log.e(TAG, e.message.toString())
                Log.d(TAG, "Ah shiet, What dafug is going on !! we have a bug, bro !")
            }
        }
    }
}