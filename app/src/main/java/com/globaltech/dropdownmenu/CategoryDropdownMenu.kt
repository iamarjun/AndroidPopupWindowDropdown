package com.globaltech.dropdownmenu

import android.content.Context
import android.view.LayoutInflater
import android.widget.PopupWindow
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globaltech.dropdownmenu.CategoryDropdownAdapter.CategorySelectedListener
import com.globaltech.dropdownmenu.model.Category.Companion.generateCategoryList
import kotlinx.android.synthetic.main.popup_category.view.*

/**
 * Created by Admin2 on 10/Jul/2018.
 */
class CategoryDropdownMenu(private val context: Context) : PopupWindow(context) {
    private var rvCategory: RecyclerView? = null
    private var search: SearchView? = null
    private var dropdownAdapter: CategoryDropdownAdapter? = null
    fun setCategorySelectedListener(categorySelectedListener: CategorySelectedListener?) {
        dropdownAdapter!!.setCategorySelectedListener(categorySelectedListener)
    }

    private fun setupView() {
        val view = LayoutInflater.from(context).inflate(R.layout.popup_category, null)
        search = view.search
        rvCategory = view.rvCategory
        rvCategory?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        rvCategory?.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        dropdownAdapter = CategoryDropdownAdapter(generateCategoryList())
        rvCategory?.setAdapter(dropdownAdapter)

        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                dropdownAdapter?.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                dropdownAdapter?.filter?.filter(newText)
                return false
            }

        })

        contentView = view
    }

    init {
        setupView()
    }
}