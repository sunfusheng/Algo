package com.sunfusheng.algo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sunfusheng.codeviewer.CodeHtmlGenerator
import com.sunfusheng.codeviewer.CodeViewUtil
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * @author sunfusheng
 * @since 2020-02-22
 */
class DetailActivity : AppCompatActivity() {
    companion object {
        const val PARAM_KEY = "algo-item"
    }

    private lateinit var mAlgoItem: AlgoItem
    private var mSourceCode: String? = null

    private var isNightMode: Boolean = true
    private var showLineNums: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mAlgoItem = intent.getSerializableExtra(PARAM_KEY) as AlgoItem
        loadSampleCode()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.getItem(0).isChecked = isNightMode
        menu.getItem(1).isChecked = showLineNums
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_night_mode -> {
                isNightMode = !isNightMode
                item.isChecked = isNightMode
                loadSampleCode()
                return true
            }
            R.id.item_show_linenums -> {
                showLineNums = !showLineNums
                item.isChecked = showLineNums
                loadSampleCode()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadSampleCode() {
        if (mSourceCode == null) {
            mSourceCode = CodeViewUtil.getStringFromAssetsFile(
                applicationContext,
                mAlgoItem.getFilePath()
            )
        }
        val sourceCodeHtml = CodeHtmlGenerator.generate(
            mAlgoItem.getFilePath(),
            mSourceCode,
            isNightMode,
            showLineNums
        )
        vCodeView.loadCodeHtml(sourceCodeHtml)
    }
}