package com.sunfusheng.algo.app

import android.os.Bundle
import com.sunfusheng.algo.R
import com.sunfusheng.algo.common.BaseActivity

/**
 * @author sunfusheng
 * @since 2020/3/14
 */
class AboutActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_about)

    initActionBar(getString(R.string.title_about), "V1.0.9")
  }
}