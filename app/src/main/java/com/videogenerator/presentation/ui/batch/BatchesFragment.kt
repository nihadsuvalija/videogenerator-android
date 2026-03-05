package com.videogenerator.presentation.ui.batch

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.videogenerator.databinding.FragmentBatchesBinding
import com.videogenerator.presentation.viewmodel.BatchUiEvent
import com.videogenerator.presentation.viewmodel.ImageBatchViewModel
import com.videogenerator.presentation.viewmodel.VideoBatchViewModel
import com.videogenerator.util.snack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BatchesFragment : Fragment() {

    private var _binding: FragmentBatchesBinding? = null
    private val binding get() = _binding!!

    private val videoBatchViewModel: VideoBatchViewModel by viewModels()
    private val imageBatchViewModel: ImageBatchViewModel by viewModels()

    private var pendingType: BatchType = BatchType.VIDEO

    private val folderPicker = registerForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) { uri: Uri? ->
        uri ?: return@registerForActivityResult
        requireContext().contentResolver.takePersistableUriPermission(
            uri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        val path = getRealPathFromUri(uri) ?: return@registerForActivityResult
        when (pendingType) {
            BatchType.VIDEO -> videoBatchViewModel.addBatch(uri, uri.path.toString())
            BatchType.IMAGE -> imageBatchViewModel.addBatch(path)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = BatchPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Videos"
                1 -> "Images"
                else -> ""
            }
        }.attach()

        binding.fabAddBatch.setOnClickListener {
            showAddBatchDialog()
        }

        observeEvents()
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    videoBatchViewModel.events.collect { event ->
                        handleEvent(event)
                    }
                }
                launch {
                    imageBatchViewModel.events.collect { event ->
                        handleEvent(event)
                    }
                }
            }
        }
    }

    private fun handleEvent(event: BatchUiEvent) {
        when (event) {
            is BatchUiEvent.ShowError -> binding.root.snack("❌ ${event.message}")
            is BatchUiEvent.ShowSuccess -> binding.root.snack("✅ ${event.message}")
        }
    }

    private fun showAddBatchDialog() {
        val currentTab = binding.viewPager.currentItem
        pendingType = if (currentTab == 0) BatchType.VIDEO else BatchType.IMAGE
        val typeLabel = if (pendingType == BatchType.VIDEO) "video" else "image"

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add ${typeLabel.replaceFirstChar { it.uppercase() }} Batch")
            .setMessage("Select a folder containing ${typeLabel} files (MP4 / JPG).\n\nThe folder name will be used as the batch name (e.g. BATCH_001).")
            .setPositiveButton("Choose Folder") { _, _ ->
                folderPicker.launch(null)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun getRealPathFromUri(uri: Uri): String? {
        return try {
            val docId = DocumentsContract.getTreeDocumentId(uri)
            val split = docId.split(":")
            val type = split[0]
            if ("primary".equals(type, ignoreCase = true)) {
                "/storage/emulated/0/${split.getOrElse(1) { "" }}"
            } else {
                "/storage/${type}/${split.getOrElse(1) { "" }}"
            }
        } catch (e: Exception) {
            uri.path
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class BatchType { VIDEO, IMAGE }
}
