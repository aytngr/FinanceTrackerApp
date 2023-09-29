package gr.aytn.pocket.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import gr.aytn.pocket.model.Category
import gr.aytn.pocket.room.dao.CategoryDao

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}