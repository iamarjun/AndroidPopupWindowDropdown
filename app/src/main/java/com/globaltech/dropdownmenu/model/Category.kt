package com.globaltech.dropdownmenu.model

import com.globaltech.dropdownmenu.R
import java.util.*

/**
 * Created by Admin2 on 10/Jul/2018.
 */
class Category(var id: Long, var iconRes: Int, var category: String) {

    companion object {
        @JvmStatic
        fun generateCategoryList(): List<Category> {
            val categories: MutableList<Category> = ArrayList()
            val programming = arrayOf("C++", "JAVA", "JAVASCRIPT", "C#", "Objective C", "SWIFT", "C++", "JAVA", "JAVASCRIPT", "C#", "Objective C", "SWIFT", "C++", "JAVA", "JAVASCRIPT", "C#", "Objective C", "SWIFT", "C++", "JAVA", "JAVASCRIPT", "C#", "Objective C", "SWIFT", "C++", "JAVA", "JAVASCRIPT", "C#", "Objective C", "SWIFT", "C++", "JAVA", "JAVASCRIPT", "C#", "Objective C", "SWIFT", "C++", "JAVA", "JAVASCRIPT", "C#", "Objective C", "SWIFT")
            for (i in programming.indices) {
                categories.add(Category(i.toLong(), R.drawable.ic_insert_emoticon_black_24dp, programming[i]))
            }
            return categories
        }
    }

}