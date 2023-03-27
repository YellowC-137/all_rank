package yellowc.app.allrank.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yellowc.app.allrank.databinding.ItemVideoBinding
import yellowc.app.allrank.domain.models.Videos

class VideoAdapter(private val itemClicked: (Videos) -> Unit) :
    ListAdapter<Videos, VideoAdapter.VideoViewHolder>(diffUtil) {

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Videos, itemClicked: (Videos) -> Unit) {
            binding.apply {
                video = item
                tvVideoTitle.text=item.title.replace("&quot;", "").replace(";", "")
                videoContainer.setOnClickListener {
                    itemClicked(item)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it, itemClicked)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Videos>() {
            override fun areItemsTheSame(
                oldItem: Videos,
                newItem: Videos
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Videos,
                newItem: Videos
            ) = oldItem == newItem
        }
    }
}