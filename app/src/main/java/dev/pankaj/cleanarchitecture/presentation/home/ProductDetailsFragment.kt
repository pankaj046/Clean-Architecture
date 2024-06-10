package dev.pankaj.cleanarchitecture.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import dev.pankaj.cleanarchitecture.databinding.FragmentProductDetailsBinding
import dev.pankaj.cleanarchitecture.extensions.hide
import dev.pankaj.cleanarchitecture.extensions.show
import dev.pankaj.cleanarchitecture.presentation.home.viewmodel.ProductViewModel
import dev.pankaj.cleanarchitecture.presentation.home.viewmodel.ProductViewModelFactory
import dev.pankaj.cleanarchitecture.utils.CallBack
import dev.pankaj.cleanarchitecture.utils.serializable
import dev.pankaj.cleanarchitecture.utils.showToast
import javax.inject.Inject


@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ProductViewModelFactory
    private lateinit var viewModel: ProductViewModel

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
        addObserver()
        arguments?.let {
            if (it.containsKey("Data")){
                it.serializable<Product>("Data")?.let { data->
                    activity?.let {action->
                        (action as AppCompatActivity).supportActionBar?.let { actionBar->
                            actionBar.title = data.title
                        }
                    }
                    lifecycleScope.launchWhenStarted {
                        viewModel.product(data.id!!)
                    }
                }
            }
        }
    }


    private fun addObserver() {
        viewModel.productResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is CallBack.Loading -> setLoadingIndicator(result.isLoading)
                is CallBack.Success -> handleProudctSuccess(result.data)
                is CallBack.Error -> showMessage(result.exception.message)
                is CallBack.Message -> showMessage(result.msg)
            }
        }
    }

    private fun handleProudctSuccess(product: Product) {
        Glide.with(requireContext())
            .load(product.image)
            .into(binding.productImage)
        binding.productTitle.text = product.title
        binding.productPrice.text = "$" + product.price
        binding.productOldPrice.text = "$" + product.price
        binding.productDescription.text = product.description
        binding.productRating.text = ""+product.rating?.rate+ " (" + product.rating?.count + " Reviews)";
    }

    private fun setLoadingIndicator(loading: Boolean) {
        if (loading){
            binding.loading.show()
            binding.viewRoot.hide()
        }else{
            binding.loading.hide()
            binding.viewRoot.show()
        }
    }

    private fun showMessage(message: String?) {
        requireActivity().showToast(message ?: "Something went wrong")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}