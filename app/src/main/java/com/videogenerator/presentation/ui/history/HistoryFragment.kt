package com.videogenerator.presentation.ui.history

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.videogenerator.databinding.FragmentHistoryBinding
import com.videogenerator.domain.model.GeneratedVideo
import com.videogenerator.presentation.adapter.GeneratedVideoAdapter
import com.videogenerator.presentation.viewmodel.HistoryViewModel
import com.videogenerator.util.snack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var adapter: GeneratedVideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GeneratedVideoAdapter(
            onPlay = { video -> playVideo(video) },
            onShare = { video -> shareVideo(video) },
            onDelete = { video -> confirmDelete(video) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.history.collect { videos ->
                        adapter.submitList(videos)
                        binding.emptyState.visibility = if (videos.isEmpty()) View.VISIBLE else View.GONE
                    }
                }
                launch {
                    viewModel.events.collect { message ->
                        binding.root.snack(message)
                    }
                }
            }
        }
    }

    private fun playVideo(video: GeneratedVideo) {
        val file = File(video.outputPath)
        if (!file.exists()) {
            binding.root.snack("File not found: ${video.outputPath}")
            return
        }
        val uri: Uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            file
        )
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "video/mp4")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(intent, "Play with..."))
    }

    private fun shareVideo(video: GeneratedVideo) {
        val file = File(video.outputPath)
        if (!file.exists()) {
            binding.root.snack("File not found")
            return
        }
        val uri: Uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            file
        )
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "video/mp4"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(intent, "Share video"))
    }

    private fun confirmDelete(video: GeneratedVideo) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Entry")
            .setMessage("Remove this video from history?\n(The file will remain on disk.)")
            .setPositiveButton("Delete") { _, _ -> viewModel.deleteVideo(video.id) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
