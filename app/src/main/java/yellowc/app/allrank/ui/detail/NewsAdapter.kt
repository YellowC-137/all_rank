package yellowc.app.allrank.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yellowc.app.allrank.databinding.ItemNewsBinding
import yellowc.app.allrank.domain.models.BaseModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NewsAdapter(private val itemClicked: (BaseModel) -> Unit) :
    ListAdapter<BaseModel, NewsAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BaseModel, itemClicked: (BaseModel) -> Unit) {
            binding.apply {
                news = item
                val title = item.title.replace("&quot;", "").replace(";", "").replace("&#39", "").replace("<b>","")
                    .replace("</b>","").replace("&apos","")
                val content = item.content!!.replace("<b>","").replace("</b>","").replace("&apos","")
                tvNewsTitle.text = title
                tvContent.text = content
                tvNewsDate.text = setDate(item.owner!!)

                newsContainer.setOnClickListener {
                    itemClicked(item)
                }
            }
        }
    }

    fun setDate(input: String):String {
        val formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
        val dateTime = LocalDateTime.parse(input, formatter)
        val outputFormatter = DateTimeFormatter.ofPattern("M월 d일 EEEE a h시 m분")
        val output = dateTime.format(outputFormatter)
        return output
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