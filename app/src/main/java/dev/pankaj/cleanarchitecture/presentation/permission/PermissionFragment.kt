package dev.pankaj.cleanarchitecture.presentation.permission

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.pankaj.cleanarchitecture.R
import dev.pankaj.cleanarchitecture.databinding.FragmentPermissionBinding
import dev.pankaj.cleanarchitecture.extensions.checkPermissionIsAllowed
import dev.pankaj.cleanarchitecture.extensions.disable
import dev.pankaj.cleanarchitecture.extensions.permissionStorage

@AndroidEntryPoint
class PermissionFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentPermissionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPermissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        // Initialize views here if needed
    }

    private fun initListeners() {
        binding.camera.setOnClickListener(this)
        binding.storage.setOnClickListener(this)
        binding.microphone.setOnClickListener(this)
        binding.notification.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.camera.id -> requestPermission(arrayOf(Manifest.permission.CAMERA))
            binding.storage.id -> requestPermission(permissionStorage())
            binding.microphone.id -> requestPermission(arrayOf(Manifest.permission.RECORD_AUDIO))
            binding.notification.id -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestPermission(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
                }
            }
            binding.btnNext.id -> {
                if (v.context.checkPermissionIsAllowed()) {
                    v.disable()
                    v.findNavController()
                        .navigate(R.id.action_permissionFragment_to_loginFragment)
                } else {
                    Log.e("PermissionFragment", "Permissions not granted")
                }
            }
        }
    }

    private fun requestPermission(permissions: Array<String>) {
        permissionRequest.launch(permissions)
    }

    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Handle camera permission
            val cameraPermissionGranted = permissions[Manifest.permission.CAMERA] ?: false
            if (cameraPermissionGranted) {
                binding.camera.disable()
            }

            // Handle storage permission
            var storagePermissionGranted = false
                permissionStorage().forEach {
                    storagePermissionGranted = permissions[it] == true
            }
            if (storagePermissionGranted) {
                binding.storage.disable()
            }

            // Handle microphone permission
            val microphonePermissionGranted = permissions[Manifest.permission.RECORD_AUDIO] ?: false
            if (microphonePermissionGranted) {
                binding.microphone.disable()
            }

            // Handle notification permission
            val notificationPermissionGranted = permissions[if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Manifest.permission.POST_NOTIFICATIONS
            } else {
                true
            }] ?: false
            if (notificationPermissionGranted) {
                binding.notification.disable()
            }
        }
}
