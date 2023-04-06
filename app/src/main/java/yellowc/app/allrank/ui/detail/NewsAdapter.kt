package yellowc.app.allrank.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yellowc.app.allrank.databinding.ItemNewsBinding
import yellowc.app.allrank.domain.models.BaseModel

class NewsAdapter(private val itemClicked: (BaseModel) -> Unit) :
    ListAdapter<BaseModel, NewsAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BaseModel, itemClicked: (BaseModel) -> Unit) {
            binding.apply {
                news = item
                newsContainer.setOnClickListener {
                    itemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it, itemClicked)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<BaseModel>() {
            override fun areItemsTheSame(
                oldItem: BaseModel,
                newItem: BaseModel
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: BaseModel,
                newItem: BaseModel
            ) = oldItem == newItem
        }
    }
}