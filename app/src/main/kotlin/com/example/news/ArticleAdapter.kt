package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(private val articles: MutableList<Article> = mutableListOf()) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    fun updateArticles(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
        // val imageViewHeader: ImageView = view.findViewById(R.id.imageViewHeader)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.textViewTitle.text = article.title
        holder.textViewDescription.text = article.description
        // holder.imageViewHeader.setImageUrl(article.headerImageURL) // If using an ImageView
    }

    override fun getItemCount() = articles.size
}
