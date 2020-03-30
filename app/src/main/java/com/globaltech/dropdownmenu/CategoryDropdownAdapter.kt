package com.globaltech.dropdownmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.globaltech.dropdownmenu.CategoryDropdownAdapter.CategoryViewHolder
import com.globaltech.dropdownmenu.model.Category

/**
 * Created by Admin2 on 10/Jul/2018.
 */
class CategoryDropdownAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryViewHolder>(), Filterable {

    private val filteredList = ArrayList<Category>(categories)

    private var categorySelectedListener: CategorySelectedListener? = null
    fun setCategorySelectedListener(categorySelectedListener: CategorySelectedListener?) {
        this.categorySelectedListener = categorySelectedListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = filteredList[position]
        holder.ivIcon.setImageResource(category.iconRes)
        holder.tvCategory.text = category.category
        holder.itemView.setOnClickListener {
            if (categorySelectedListener != null) {
                categorySelectedListener?.onCategorySelected(position, category)
            }
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    class CategoryViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvCategory: AppCompatTextView
        var ivIcon: AppCompatImageView

        init {
            tvCategory = itemView.findViewById(R.id.tvCategory)
            ivIcon = itemView.findViewById(R.id.ivIcon)
        }
    }

    interface CategorySelectedListener {
        fun onCategorySelected(position: Int, category: Category?)
    }

    override fun getFilter(): Filter {
        return MyFilter()
    }

    inner class MyFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            val filteredList = ArrayList<Category>()

            if (constraint == null || constraint.isEmpty())
                filteredList.addAll(categories)
            else {
                categories.forEach {
                    if (it.category.toLowerCase().trim().contains(constraint.toString().toLowerCase().trim()))
                        filteredList.add(it)
                }

                filterResult.count = filteredList.size
                filterResult.values = filteredList
            }

            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.values?.let {
                filteredList.clear()
                filteredList.addAll(it as List<Category>)
                notifyDataSetChanged()
            }

        }
    }

}