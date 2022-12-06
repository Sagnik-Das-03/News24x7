package com.example.news24

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.sourceView.text = currentItem.name
        holder.authorView.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageView)
        holder.titleView.text = currentItem.title
        holder.descriptionView.text = currentItem.description
        holder.contentView.text = currentItem.description
        val zonedTime = ZonedDateTime.parse(currentItem.publishedAt)
        val formatted : String = zonedTime.format(DateTimeFormatter.ofPattern("EEEE-MMMM-u HH:mm"))
        holder.publishedView.text = formatted
        holder.shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Hey, Check this cool meme !!! ${currentItem.url}")
            val chooser = Intent.createChooser(intent, "Share this meme using....")
            startActivity(holder.shareButton.context,chooser,null)
            Animatoo.animateDiagonal(holder.shareButton.context)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

}
class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val sourceView: TextView = itemView.findViewById(R.id.tvName)
    val  authorView : TextView = itemView.findViewById(R.id.tvAuthor)
    val imageView : ImageView = itemView.findViewById(R.id.ivImage)
    val titleView: TextView = itemView.findViewById(R.id.tvTitle)
    val descriptionView : TextView = itemView.findViewById(R.id.tvDescription)
    val contentView: TextView = itemView.findViewById(R.id.tvContent)
    val publishedView: TextView = itemView.findViewById(R.id.tvPublishedAt)
    val shareButton: FloatingActionButton = itemView.findViewById(R.id.fabShare)
}

interface NewsItemClicked{
    fun onItemClick(item: News)
}