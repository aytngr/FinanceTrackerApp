package gr.aytn.pocket.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import gr.aytn.pocket.model.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category WHERE category_type = 'EXPENSE'")
    suspend fun getExpensesCategories(): List<Category>

    @Query("SELECT * FROM category WHERE category_type = 'INCOME'")
    suspend fun getIncomesCategories(): List<Category>

    @Insert
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)
}