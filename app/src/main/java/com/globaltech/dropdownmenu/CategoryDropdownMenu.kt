package com.globaltech.dropdownmenu

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
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
    private var search: EditText? = null
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

        search?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dropdownAdapter?.filter?.filter(s)
            }

        })

        contentView = view
    }

    init {
        setupView()
    }
}