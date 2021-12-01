package iuh.fivet.app_dev.goodbooks.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iuh.fivet.app_dev.goodbooks.api.Api
import iuh.fivet.app_dev.goodbooks.models.Top100Books
import iuh.fivet.app_dev.goodbooks.models.TopBooksByAuthor
import iuh.fivet.app_dev.goodbooks.models.TopBooksByGenre
import iuh.fivet.app_dev.goodbooks.models.TopBooksSimilar
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _top100Books = MutableLiveData<List<Top100Books>>()
    val Top100Books: LiveData<List<Top100Books>> = _top100Books

    private val _topBooksByAuthor = MutableLiveData<List<TopBooksByAuthor>>()
    val TopBooksByAuthor: LiveData<List<TopBooksByAuthor>> = _topBooksByAuthor

    private val _topBooksByGenre = MutableLiveData<List<TopBooksByGenre>>()
    val TopBooksByGenre: LiveData<List<TopBooksByGenre>> = _topBooksByGenre

    private val _topBooksSimilar = MutableLiveData<List<TopBooksSimilar>>()
    val TopBooksSimilar: LiveData<List<TopBooksSimilar>> = _topBooksSimilar

    init {
        getTop100Books()
    }

    private fun getTop100Books() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _top100Books.value = Api.retrofitService.getTop100Books().data.list_book_top100

                _topBooksByAuthor.value = Api.retrofitService.getTopBooksByAuthor().data.list_book_topAuthor

                _topBooksByGenre.value = Api.retrofitService.getTopBooksByGenre().data.list_book_topGenre

                _topBooksSimilar.value = Api.retrofitService.getTopBooksSimilar().data.list_book_topSimilar

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