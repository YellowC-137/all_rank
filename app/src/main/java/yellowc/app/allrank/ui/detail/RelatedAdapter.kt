package yellowc.app.allrank.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yellowc.app.allrank.databinding.ItemRelateBinding
import yellowc.app.allrank.domain.models.RelateModel

class RelatedAdapter : ListAdapter<RelateModel, RelatedAdapter.PeopleViewHolder>(diffUtil) {

    inner class PeopleViewHolder(private val binding: ItemRelateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RelateModel) {
            binding.apply {
                relateModel = item
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(
            ItemRelateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RelateModel>() {
            override fun areItemsTheSame(
                oldItem: RelateModel,
                newItem: RelateModel
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: RelateModel,
                newItem: RelateModel
            ) = oldItem == newItem
        }
    }
}