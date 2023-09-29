package gr.aytn.pocket.repository

import gr.aytn.pocket.model.Category
import gr.aytn.pocket.room.dao.CategoryDao
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {
    suspend fun getExpensesCategories(): List<Category>{
        return categoryDao.getExpensesCategories()
    }
    suspend fun getIncomesCategories(): List<Category>{
        return categoryDao.getIncomesCategories()
    }
    suspend fun insertCategory(category: Category){
        categoryDao.insertCategory(category)
    }
    suspend fun deleteCategory(category: Category){
        categoryDao.deleteCategory(category)
    }
}