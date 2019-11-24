package io.vinicius.androidcommon.view

import android.os.Bundle
import io.vinicius.androidcommon.R
import io.vinicius.androidcommon.custom.BaseActivity

class MainActivity : BaseActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}