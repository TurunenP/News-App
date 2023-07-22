package com.example.newsapp.repository

import com.example.newsapp.model.Article
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticalDatabase

class NewsRepository(
    val db: ArticalDatabase
) {
    suspend fun getBreakingNews (countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews (searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticles(article)
}
