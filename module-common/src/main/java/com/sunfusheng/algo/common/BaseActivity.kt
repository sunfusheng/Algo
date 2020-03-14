package com.sunfusheng.algo.common

import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

/**
 * @author sunfusheng
 * @since 2020/3/14
 */
open class BaseActivity : AppCompatActivity() {

    protected fun initActionBar(
        title: String,
        subtitle: String = "",
        showHomeAsUp: Boolean = true
    ) {
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.title = title
            actionBar.subtitle = subtitle
            actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}