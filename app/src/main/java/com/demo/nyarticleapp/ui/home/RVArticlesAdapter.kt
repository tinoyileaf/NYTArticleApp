package com.demo.nyarticleapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.nyarticleapp.R
import com.demo.nyarticleapp.data.model.ResultsItem
import com.demo.nyarticleapp.ui.MainActivity

class RVArticlesAdapter(
    private val context: Context?
) : RecyclerView.Adapter<RVArticlesAdapter.ListViewHolder>() {

    private var articles: MutableList<ResultsItem?> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_article_layout,
            parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = articles[position]
        holder.bind(context, item)
        holder.itemView.setOnClickListener {
            item?.let { (context as MainActivity).openDetailedPage(item) }
        }
    }

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val tvSubTitle = view.findViewById<TextView>(R.id.tvSubtitle)
        private val ivThumbnail = view.findViewById<ImageView>(R.id.ivThumbnail)
        private val tvDate = view.findViewById<TextView>(R.id.tvDate)
        fun bind(context: Context?, content: ResultsItem?) {
            tvTitle.text = content?.title
            tvSubTitle.text = content?.byline
            tvDate.text = content?.publishedDate
            context?.let {
                var url = ""
                if (content?.media?.isNotEmpty()!!) {
                    content.media[0]?.mediaMetadata?.forEach { item ->
                        if (item?.format == "Standard Thumbnail")
                            url = item.url ?: ""
                    }
                }
                Glide.with(context)
                    .load(url)
                    .circleCrop()
                    .placeholder(R.drawable.placeholder)
                    .into(ivThumbnail)
            }
        }
    }

    fun updateList(list: List<ResultsItem?>) {
        articles.clear()
        articles.addAll(list)
    }

}