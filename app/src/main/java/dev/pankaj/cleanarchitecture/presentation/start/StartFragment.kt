package dev.pankaj.cleanarchitecture.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dev.pankaj.cleanarchitecture.R
import dev.pankaj.cleanarchitecture.databinding.FragmentNotificationsBinding
import dev.pankaj.cleanarchitecture.databinding.FragmentStartBinding
import dev.pankaj.cleanarchitecture.presentation.notifications.NotificationsViewModel


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.openLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}