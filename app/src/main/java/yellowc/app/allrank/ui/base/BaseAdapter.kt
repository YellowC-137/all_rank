package yellowc.app.allrank.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import yellowc.app.allrank.databinding.ItemRankingBinding
import yellowc.app.allrank.domain.models.BaseModel

class BaseAdapter(
    private val itemClicked: (BaseModel) -> Unit
) : ListAdapter<BaseModel, BaseAdapter.BaseViewHolder>(diffUtil) {

    inner class BaseViewHolder(private val binding: ItemRankingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BaseModel, itemClicked: (BaseModel) -> Unit) {
            binding.apply {
                model = item
                if (item.img.isNullOrBlank()){
                    itemImage.layoutParams = ConstraintLayout.LayoutParams(0, 0)
                }
                else{
                    Glide.with(itemImage.context).load(item.img).into(itemImage)
                }

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