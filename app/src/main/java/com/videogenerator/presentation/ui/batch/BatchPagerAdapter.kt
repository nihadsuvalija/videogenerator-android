package com.videogenerator.presentation.ui.batch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.videogenerator.databinding.FragmentBatchListBinding
import com.videogenerator.presentation.adapter.ImageBatchAdapter
import com.videogenerator.presentation.adapter.VideoBatchAdapter
import com.videogenerator.presentation.viewmodel.ImageBatchViewModel
import com.videogenerator.presentation.viewmodel.VideoBatchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class BatchPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> VideoBatchListFragment()
        1 -> ImageBatchListFragment()
        else -> VideoBatchListFragment()
    }
}

@AndroidEntryPoint
class VideoBatchListFragment : Fragment() {

    private var _binding: FragmentBatchListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VideoBatchViewModel by viewModels()
    private lateinit var adapter: VideoBatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = VideoBatchAdapter(
            onDelete = { batch -> viewModel.deleteBatch(batch.id) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.emptyText.text = "No video batches yet.\nTap + to add a folder."

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.batches.collect { batches ->
                    adapter.submitList(batches)
                    binding.emptyText.visibility = if (batches.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@AndroidEntryPoint
class ImageBatchListFragment : Fragment() {

    private var _binding: FragmentBatchListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImageBatchViewModel by viewModels()
    private lateinit var adapter: ImageBatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ImageBatchAdapter(
            onDelete = { batch -> viewModel.deleteBatch(batch.id) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.emptyText.text = "No image batches yet.\nTap + to add a folder."

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.batches.collect { batches ->
                    adapter.submitList(batches)
                    binding.emptyText.visibility = if (batches.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
