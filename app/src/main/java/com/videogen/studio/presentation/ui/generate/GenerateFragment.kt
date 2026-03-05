package com.videogen.studio.presentation.ui.generate

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.videogen.databinding.FragmentGenerateBinding
import com.videogen.domain.model.*
import com.videogen.studio.presentation.viewmodel.GenerateViewModel
import com.videogen.studio.domain.model.FontOption
import com.videogen.studio.domain.model.GeneratedVideo
import com.videogen.studio.domain.model.GenerationConfig
import com.videogen.studio.domain.model.GenerationState
import com.videogen.studio.domain.model.GenerationType
import com.videogen.studio.domain.model.ImageBatchItem
import com.videogen.studio.domain.model.Resolution
import com.videogen.studio.domain.model.TextCategory
import com.videogen.studio.domain.model.VideoBatchItem
import com.videogen.studio.util.snack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenerateFragment : Fragment() {

    private var _binding: FragmentGenerateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GenerateViewModel by viewModels()

    private var videoBatches: List<VideoBatchItem> = emptyList()
    private var imageBatches: List<ImageBatchItem> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenerateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTypeToggle()
        setupDropdowns()
        setupGenerateButton()
        observeState()
    }

    private fun setupTypeToggle() {
        binding.toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            val isVideoConcat = checkedId == R.id.btnTypeVideoConcat
            binding.layoutVideoBatch.visibility = if (isVideoConcat) View.VISIBLE else View.GONE
            binding.layoutImageBatch.visibility = if (isVideoConcat) View.GONE else View.VISIBLE
            binding.layoutImageDuration.visibility = if (isVideoConcat) View.GONE else View.VISIBLE
        }
        binding.btnTypeVideoConcat.isChecked = true
    }

    private fun setupDropdowns() {
        // Resolution
        val resolutions = Resolution.values().map { it.label }
        binding.spinnerResolution.adapter = ArrayAdapter(
            requireContext(), R.layout.simple_spinner_item, resolutions
        ).also { it.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) }
        binding.spinnerResolution.setSelection(1) // VERTICAL_FULL_HD default

        // Text category
        val textOptions = listOf("None") + TextCategory.values().map { it.displayName }
        binding.spinnerText.adapter = ArrayAdapter(
            requireContext(), R.layout.simple_spinner_item, textOptions
        ).also { it.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) }

        // Font
        val fontOptions = FontOption.values().map { it.displayName }
        binding.spinnerFont.adapter = ArrayAdapter(
            requireContext(), R.layout.simple_spinner_item, fontOptions
        ).also { it.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) }
        binding.spinnerFont.setSelection(1) // Times New Roman default

        // Observe batches for spinners
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.videoBatches.collect { batches ->
                        videoBatches = batches
                        val names = if (batches.isEmpty()) listOf("No video batches added") else batches.map { it.batchName }
                        binding.spinnerVideoBatch.adapter = ArrayAdapter(
                            requireContext(), R.layout.simple_spinner_item, names
                        ).also { it.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) }
                    }
                }
                launch {
                    viewModel.imageBatches.collect { batches ->
                        imageBatches = batches
                        val names = if (batches.isEmpty()) listOf("No image batches added") else batches.map { it.batchName }
                        binding.spinnerImageBatch.adapter = ArrayAdapter(
                            requireContext(), R.layout.simple_spinner_item, names
                        ).also { it.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) }
                    }
                }
            }
        }
    }

    private fun setupGenerateButton() {
        binding.btnGenerate.setOnClickListener {
            buildAndSubmitConfig()
        }
    }

    private fun buildAndSubmitConfig() {
        val isVideoConcat = binding.btnTypeVideoConcat.isChecked

        val resolution = Resolution.values()[binding.spinnerResolution.selectedItemPosition]

        val textCategoryIndex = binding.spinnerText.selectedItemPosition
        val textCategory = if (textCategoryIndex == 0) null
        else TextCategory.values()[textCategoryIndex - 1]

        val fontOption = FontOption.values()[binding.spinnerFont.selectedItemPosition]
        val fontSize = binding.etFontSize.text.toString().toIntOrNull() ?: 26
        val includeLogo = binding.switchLogo.isChecked
        val repeatCount = binding.etRepeatCount.text.toString().toIntOrNull() ?: 1

        val config = if (isVideoConcat) {
            if (videoBatches.isEmpty()) {
                binding.root.snack("Please add a video batch first")
                return
            }
            val batch = videoBatches[binding.spinnerVideoBatch.selectedItemPosition]
            GenerationConfig(
                type = GenerationType.VIDEO_CONCAT,
                videoBatchId = batch.id,
                resolution = resolution,
                textCategory = textCategory,
                fontOption = fontOption,
                fontSize = fontSize,
                includeLogo = includeLogo,
                repeatCount = repeatCount.coerceIn(1, 10)
            )
        } else {
            if (imageBatches.isEmpty()) {
                binding.root.snack("Please add an image batch first")
                return
            }
            val batch = imageBatches[binding.spinnerImageBatch.selectedItemPosition]
            val imageDuration = binding.etImageDuration.text.toString().toDoubleOrNull() ?: 0.2
            val totalDuration = binding.etTotalDuration.text.toString().toDoubleOrNull() ?: 15.0
            GenerationConfig(
                type = GenerationType.IMAGE_LOOP,
                imageBatchId = batch.id,
                resolution = resolution,
                textCategory = textCategory,
                fontOption = fontOption,
                fontSize = fontSize,
                includeLogo = includeLogo,
                imageDuration = imageDuration,
                totalDuration = totalDuration,
                repeatCount = repeatCount.coerceIn(1, 10)
            )
        }

        viewModel.generateVideo(config)
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.generationState.collect { state ->
                    when (state) {
                        is GenerationState.Idle -> {
                            binding.progressCard.visibility = View.GONE
                            binding.btnGenerate.isEnabled = true
                        }
                        is GenerationState.Running -> {
                            binding.progressCard.visibility = View.VISIBLE
                            binding.btnGenerate.isEnabled = false
                            binding.progressBar.progress = state.progress
                            binding.tvProgressMessage.text = state.message
                            binding.tvProgressPercent.text = "${state.progress}%"
                        }
                        is GenerationState.Success -> {
                            binding.progressCard.visibility = View.GONE
                            binding.btnGenerate.isEnabled = true
                            showSuccessDialog(state.video)
                            viewModel.resetState()
                        }
                        is GenerationState.Error -> {
                            binding.progressCard.visibility = View.GONE
                            binding.btnGenerate.isEnabled = true
                            binding.root.snack("❌ ${state.message}")
                            viewModel.resetState()
                        }
                    }
                }
            }
        }
    }

    private fun showSuccessDialog(video: GeneratedVideo) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("✅ Video Generated!")
            .setMessage(
                "Output saved to:\n${video.outputPath}\n\n" +
                "Resolution: ${video.width}×${video.height}\n" +
                "Duration: ${String.format("%.1f", video.duration)}s"
            )
            .setPositiveButton("Great!") { d, _ -> d.dismiss() }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
