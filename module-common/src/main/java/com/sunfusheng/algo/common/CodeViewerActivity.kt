package com.sunfusheng.algo.common

import android.content.Context
import android.content.Intent
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
        private const val PARAM_SUBJECT = "algo_subject"
        private const val PARAM_FILE_PATH = "algo_file_path"
        private const val PARAM_HARD_LEVEL = "algo_hard_level"

        fun goto(context: Context, subject: String, filePath: String, hardLevel: String) {
            val intent = Intent(context, CodeViewerActivity::class.java)
            intent.putExtra(PARAM_SUBJECT, subject)
            intent.putExtra(PARAM_FILE_PATH, filePath)
            intent.putExtra(PARAM_HARD_LEVEL, hardLevel)
            context.startActivity(intent)
        }
    }

    private lateinit var mSubject: String
    private lateinit var mFilePath: String
    private lateinit var mHardLevel: String
    private var mSourceCode: String? = null

    private var isNightMode: Boolean = true
    private var showLineNums: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_viewer)

        mSubject = intent.getStringExtra(PARAM_SUBJECT)!!
        mFilePath = intent.getStringExtra(PARAM_FILE_PATH)!!
        mHardLevel = intent.getStringExtra(PARAM_HARD_LEVEL)!!

        initActionBar(mSubject, mHardLevel)
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
            if (mSourceCode == null) {
                mSourceCode = CodeViewUtil.getStringFromAssetsFile(applicationContext, mFilePath)
            }
            return@async CodeHtmlGenerator.generate(
                mFilePath,
                mSourceCode,
                isNightMode,
                showLineNums
            )
        }.await()
    }
}