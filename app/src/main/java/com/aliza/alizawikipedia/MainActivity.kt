package com.aliza.alizawikipedia

import android.os.Bundle
import com.aliza.alizawikipedia.base.BaseActivity
import com.aliza.alizawikipedia.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolBarMain)
    }
}