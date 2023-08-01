package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.model.Article

@Database(
    entities =[Article::class],
    version = 5

)

@TypeConverters(Converters::class)
abstract class ArticalDatabase : RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile
        private var instance: ArticalDatabase? = null
        private val LOCK = Any()

        operator  fun  invoke(context: Context) = instance ?: synchronized(LOCK)

        {
            instance ?: createDatabase(context).also{ instance = it }

        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticalDatabase::class.java,
                "article_db.db"
                ).build()
    }
}