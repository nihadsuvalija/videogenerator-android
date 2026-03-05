package com.videogenerator.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.videogenerator.databinding.ItemBatchBinding
import com.videogenerator.databinding.ItemGeneratedVideoBinding
import com.videogenerator.domain.model.GeneratedVideo
import com.videogenerator.domain.model.ImageBatchItem
import com.videogenerator.domain.model.VideoBatchItem
import com.videogenerator.util.toFormattedDate
import com.videogenerator.util.toFormattedDuration
import java.io.File

// --- Video Batch Adapter ---

class VideoBatchAdapter(
    private val onDelete: (VideoBatchItem) -> Unit
) : ListAdapter<VideoBatchItem, VideoBatchAdapter.ViewHolder>(VideoBatchDiff()) {

    inner class ViewHolder(private val binding: ItemBatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoBatchItem) {
            binding.tvBatchName.text = item.batchName
            binding.tvBatchPath.text = item.folderPath
            binding.tvFileCount.text = "${item.videoCount} video(s)"
            binding.tvCreatedAt.text = item.createdAt.toFormattedDate()
            binding.ivBatchIcon.setImageResource(com.videogenerator.R.drawable.ic_video_batch)
            binding.btnDelete.setOnClickListener { onDelete(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemBatchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class VideoBatchDiff : DiffUtil.ItemCallback<VideoBatchItem>() {
        override fun areItemsTheSame(a: VideoBatchItem, b: VideoBatchItem) = a.id == b.id
        override fun areContentsTheSame(a: VideoBatchItem, b: VideoBatchItem) = a == b
    }
}

// --- Image Batch Adapter ---

class ImageBatchAdapter(
    private val onDelete: (ImageBatchItem) -> Unit
) : ListAdapter<ImageBatchItem, ImageBatchAdapter.ViewHolder>(ImageBatchDiff()) {

    inner class ViewHolder(private val binding: ItemBatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageBatchItem) {
            binding.tvBatchName.text = item.batchName
            binding.tvBatchPath.text = item.folderPath
            binding.tvFileCount.text = "${item.imageCount} image(s)"
            binding.tvCreatedAt.text = item.createdAt.toFormattedDate()
            binding.ivBatchIcon.setImageResource(com.videogenerator.R.drawable.ic_image_batch)
            binding.btnDelete.setOnClickListener { onDelete(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemBatchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ImageBatchDiff : DiffUtil.ItemCallback<ImageBatchItem>() {
        override fun areItemsTheSame(a: ImageBatchItem, b: ImageBatchItem) = a.id == b.id
        override fun areContentsTheSame(a: ImageBatchItem, b: ImageBatchItem) = a == b
    }
}

// --- Generated Video History Adapter ---

class GeneratedVideoAdapter(
    private val onPlay: (GeneratedVideo) -> Unit,
    private val onShare: (GeneratedVideo) -> Unit,
    private val onDelete: (GeneratedVideo) -> Unit
) : ListAdapter<GeneratedVideo, GeneratedVideoAdapter.ViewHolder>(GeneratedVideoDiff()) {

    inner class ViewHolder(private val binding: ItemGeneratedVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GeneratedVideo) {
            binding.tvVideoName.text = File(item.outputPath).name
            binding.tvBatchSource.text = "Batch: ${item.batchName}"
            binding.tvDuration.text = item.duration.toFormattedDuration()
            binding.tvResolution.text = "${item.width}×${item.height}"
            binding.tvType.text = if (item.type.name == "VIDEO_CONCAT") "🎬 Video Concat" else "🖼️ Image Loop"
            binding.tvCreatedAt.text = item.createdAt.toFormattedDate()

            val exists = File(item.outputPath).exists()
            binding.tvMissing.visibility = if (exists) android.view.View.GONE else android.view.View.VISIBLE
            binding.btnPlay.isEnabled = exists
            binding.btnShare.isEnabled = exists

            binding.btnPlay.setOnClickListener { onPlay(item) }
            binding.btnShare.setOnClickListener { onShare(item) }
            binding.btnDelete.setOnClickListener { onDelete(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemGeneratedVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class GeneratedVideoDiff : DiffUtil.ItemCallback<GeneratedVideo>() {
        override fun areItemsTheSame(a: GeneratedVideo, b: GeneratedVideo) = a.id == b.id
        override fun areContentsTheSame(a: GeneratedVideo, b: GeneratedVideo) = a == b
    }
}
