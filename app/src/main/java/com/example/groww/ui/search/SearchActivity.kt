package com.example.groww.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groww.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
}