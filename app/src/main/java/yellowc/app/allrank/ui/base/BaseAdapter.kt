package yellowc.app.allrank.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.ItemRankingBinding
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.util.DOWN
import yellowc.app.allrank.util.UP

class BaseAdapter(
    private val itemClicked: (BaseModel) -> Unit
) : ListAdapter<BaseModel, BaseAdapter.BaseViewHolder>(diffUtil) {

    inner class BaseViewHolder(private val binding: ItemRankingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BaseModel, itemClicked: (BaseModel) -> Unit) {
            binding.apply {
                model = item
                Glide.with(itemImage.context).load(item.img).into(itemImage)
                itemImage.isVisible = !item.img.isNullOrBlank()
                itemContent.isVisible = !item.content.isNullOrBlank()
                itemOwner.isVisible = !item.owner.isNullOrBlank()


                itemContainer.setOnClickListener { itemClicked(item) }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            ItemRankingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
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