package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.db.ArticalDatabase
import com.example.newsapp.model.NewsViewModel
import com.example.newsapp.model.NewsViewModelProvider
import com.example.newsapp.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository= NewsRepository(ArticalDatabase(this))
        val viewsModelProviderFactory = NewsViewModelProvider(application, newsRepository)
        viewModel = ViewModelProvider(this, viewsModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavigationFragment.findNavController())
    }
}
