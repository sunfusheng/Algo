package com.sunfusheng.algo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sunfusheng.codeviewer.CodeHtmlGenerator
import com.sunfusheng.codeviewer.CodeViewUtil
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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

        initActionBar(mAlgoItem.subject!!, true)

        loadSampleCode()
    }

    private fun initActionBar(title: String, showHomeAsUp: Boolean) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = title
            actionBar.subtitle = mAlgoItem.getHardLevel()
            actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.getItem(0).isChecked = isNightMode
        menu.getItem(1).isChecked = showLineNums
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
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

    // 加载Assets下的示例代码
    private fun loadSampleCode() {
        GlobalScope.launch(Dispatchers.Main) {
            vCodeView.loadCodeHtml(asyncLoadCodeFile())
        }
    }

    private suspend fun asyncLoadCodeFile(): String? {
        return GlobalScope.async(Dispatchers.Default) {
            val filePath = mAlgoItem.getFilePath()
            if (mSourceCode == null) {
                mSourceCode = CodeViewUtil.getStringFromAssetsFile(applicationContext, filePath)
            }
            return@async CodeHtmlGenerator.generate(
                filePath,
                mSourceCode,
                isNightMode,
                showLineNums
            )
        }.await()
    }
}