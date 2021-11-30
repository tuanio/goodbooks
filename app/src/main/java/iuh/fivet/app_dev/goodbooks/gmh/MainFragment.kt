package iuh.fivet.app_dev.goodbooks.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import iuh.fivet.app_dev.goodbooks.Apis.Api

import iuh.fivet.app_dev.goodbooks.Repository.repository
import iuh.fivet.app_dev.goodbooks.databinding.FragmentMainBinding

class MainFragment: Fragment() {

    private var binding: FragmentMainBinding? = null

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(repository(Api.retrofitService)))
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val adapeter = MainAdapter()
//        binding?.
//    }


}