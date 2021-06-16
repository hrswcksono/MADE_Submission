package com.dicoding.submissionmade.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submissionmade.core.R
import com.dicoding.submissionmade.core.databinding.ItemListBinding
import com.dicoding.submissionmade.core.domain.model.Movie
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter  : RecyclerView.Adapter<MovieAdapter.ListViewHolder>(), Filterable{

    private var listData = ArrayList<Movie>()
    private var movieFilterList = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    init {
        movieFilterList = listData
    }

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun clear() {
        listData.clear()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    movieFilterList = listData
                } else {
                    val resultList = ArrayList<Movie>()
                    for (row in listData) {
                        if (row.title.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                        movieFilterList = resultList
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = movieFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                movieFilterList = results?.values as ArrayList<Movie>
                notifyDataSetChanged()
            }
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${data.posterPath}")
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(imgAvatar)
                textTitle.text = data.title
                textOverview.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = movieFilterList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = movieFilterList.size
}