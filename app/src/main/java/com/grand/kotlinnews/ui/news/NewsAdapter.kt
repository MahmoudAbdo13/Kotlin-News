package com.grand.kotlinnews.ui.news

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grand.kotlinnews.R
import com.grand.kotlinnews.data.News
import com.grand.kotlinnews.ui.article.DisplayArtical
import com.grand.kotlinnews.ui.article.DisplayArticalDirections

class NewsAdapter(var list: List<News>, var fragment: DisplayArtical) :
    RecyclerView.Adapter<NewsAdapter.help>() {
    class help(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.title)
        var image = itemView.findViewById<ImageView>(R.id.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): help {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return help(view)
    }

    override fun onBindViewHolder(holder: help, position: Int) {
        val layoutParams = LinearLayout.LayoutParams(0, 0)
        holder.title.text = list.get(position).title
        Log.e("Nullllll", "" + position + list[position].icon_url!!)
        if (list[position].icon_url.equals("null")) {
            holder.image.layoutParams = layoutParams
        } else {
            Log.e("image", "" + position + list[position].icon_url!!)

            Glide.with(fragment.requireContext()).load(Uri.parse(list[position].icon_url)).into(holder.image)
        }
        holder.itemView.setOnClickListener {

            showFullArticle(list[position])
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun showFullArticle(get: News) {
        val direction = DisplayArticalDirections.actionFirstFragmentToSecondFragment(
            get.title,
            get.selftext,
            get.icon_url!!
        )
        NavHostFragment.findNavController(fragment).navigate(direction)
    }
}