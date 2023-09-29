package gr.aytn.pocket.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.aytn.pocket.room.dao.CategoryDao
import gr.aytn.pocket.room.database.AppDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()
    }

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao{
        return db.categoryDao()
    }


}