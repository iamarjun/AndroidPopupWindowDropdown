package com.globaltech.dropdownmenu

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.globaltech.dropdownmenu.CategoryDropdownAdapter.CategorySelectedListener
import com.globaltech.dropdownmenu.model.Category




class MainActivity : AppCompatActivity() {
    var tvPicker: AppCompatTextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvPicker = findViewById(R.id.tvPicker)
        tvPicker?.setOnClickListener(View.OnClickListener { showCategoryMenu() })
    }

    private fun showCategoryMenu() {
        val menu = CategoryDropdownMenu(this)
        menu.height = getPxFromDp(600)
        menu.width = WindowManager.LayoutParams.MATCH_PARENT
        menu.isOutsideTouchable = true
        menu.isFocusable = true
        menu.inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
        menu.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        menu.showAtLocation(tvPicker, Gravity.TOP or Gravity.START, locateView(tvPicker)?.left!!, locateView(tvPicker)?.bottom!!)
        menu.setCategorySelectedListener(object : CategorySelectedListener {
            override fun onCategorySelected(position: Int, category: Category?) {
                menu.dismiss()
                Toast.makeText(this@MainActivity, "Your favourite programming language : " + category?.category, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun locateView(v: View?): Rect? {
        val loc_int = IntArray(2)
        if (v == null) return null
        try {
            v.getLocationOnScreen(loc_int)
        } catch (npe: NullPointerException) {
            //Happens when the view doesn't exist on screen anymore.
            return null
        }
        val location = Rect()
        location.left = loc_int[0]
        location.top = loc_int[1]
        location.right = location.left + v.width
        location.bottom = location.top + v.height
        return location
    }

    //Convert DP to Pixel
    private fun getPxFromDp(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}