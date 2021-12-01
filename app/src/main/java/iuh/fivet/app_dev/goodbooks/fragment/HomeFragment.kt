package iuh.fivet.app_dev.goodbooks.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import iuh.fivet.app_dev.goodbooks.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.top100BookInfo.adapter = Top100BookAdapter()

        binding.topAuthorBookInfo.adapter = TopBooksByAuthorAdapter()

        binding.topGenreBookInfo.adapter = TopBooksByGenreAdapter()

        binding.topSimilarBookInfo.adapter = TopBooksSimilarAdapter()

        return binding.root
    }


}
