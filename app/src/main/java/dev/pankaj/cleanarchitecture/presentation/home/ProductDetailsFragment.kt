package dev.pankaj.cleanarchitecture.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import dev.pankaj.cleanarchitecture.databinding.FragmentHomeBinding
import dev.pankaj.cleanarchitecture.databinding.FragmentProductDetailsBinding
import dev.pankaj.cleanarchitecture.extensions.hide
import dev.pankaj.cleanarchitecture.extensions.show
import dev.pankaj.cleanarchitecture.presentation.home.adapter.ProductAdapter
import dev.pankaj.cleanarchitecture.presentation.home.viewmodel.ProductViewModel
import dev.pankaj.cleanarchitecture.presentation.home.viewmodel.ProductViewModelFactory
import dev.pankaj.cleanarchitecture.utils.CallBack
import dev.pankaj.cleanarchitecture.utils.showToast
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ProductViewModelFactory
    private lateinit var viewModel: ProductViewModel

    private val productAdapter by lazy { ProductAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, factory)[ProductViewModel::class.java]
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        addObserver()
        lifecycleScope.launchWhenStarted {
            viewModel.productList()
        }
    }

    private fun initAdapter(){

    }

    private fun addObserver() {
        viewModel.productResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is CallBack.Loading -> setLoadingIndicator(result.isLoading)
                is CallBack.Success -> handleLoginSuccess(result.data)
                is CallBack.Error -> showMessage(result.exception.message)
                is CallBack.Message -> showMessage(result.msg)
            }
        }
    }

    private fun handleLoginSuccess(data: List<Product>) {
        productAdapter.updateProduct(data)
    }

    private fun setLoadingIndicator(loading: Boolean) {

    }

    private fun showMessage(message: String?) {
        requireActivity().showToast(message ?: "Something went wrong")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}