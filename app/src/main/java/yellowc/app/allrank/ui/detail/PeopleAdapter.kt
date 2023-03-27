package yellowc.app.allrank.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yellowc.app.allrank.databinding.ItemPeopleBinding
import yellowc.app.allrank.domain.models.People

class PeopleAdapter() :
    ListAdapter<People, PeopleAdapter.PeopleViewHolder>(diffUtil) {

    inner class PeopleViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: People) {
            binding.apply {
                people = item

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(
            ItemPeopleBinding.inflate(
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
        private val diffUtil = object : DiffUtil.ItemCallback<People>() {
            override fun areItemsTheSame(
                oldItem: People,
                newItem: People
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: People,
                newItem: People
            ) = oldItem == newItem
        }
    }
}