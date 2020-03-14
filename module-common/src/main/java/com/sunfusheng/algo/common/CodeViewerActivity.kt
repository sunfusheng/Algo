package com.sunfusheng.algo.common

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.sunfusheng.codeviewer.CodeHtmlGenerator
import com.sunfusheng.codeviewer.CodeViewUtil
import kotlinx.android.synthetic.main.activity_code_viewer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @author sunfusheng
 * @since 2020-02-22
 */
class CodeViewerActivity : BaseActivity() {
    companion object {
        const val PARAM_KEY = "algo-item"
    }

    private lateinit var mAlgo: AlgoItem
    private var mSourceCode: String? = null

    private var isNightMode: Boolean = true
    private var showLineNums: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_viewer)

        mAlgo = intent.getSerializableExtra(PARAM_KEY) as AlgoItem

        val hardLevel = if (mAlgo.rootPath == ALGO_ROOT_PATH) {
            mAlgo.getAlgoHardLevel()
        } else {
            mAlgo.getLeetCodeHardLevel()
        }
        initActionBar(mAlgo.subject!!, hardLevel)

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

    // 加载Assets下的示例代码
    private fun loadSampleCode() {
        GlobalScope.launch(Dispatchers.Main) {
            vCodeView.loadCodeHtml(asyncLoadCodeFile())
        }
    }

    private suspend fun asyncLoadCodeFile(): String? {
        return GlobalScope.async(Dispatchers.Default) {
            val filePath = mAlgo.getFilePath()
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