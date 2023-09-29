package gr.aytn.pocket.ui.fragment.expenses

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.aytn.pocket.model.Category
import gr.aytn.pocket.repository.CategoryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ExpensesViewModel @Inject constructor(private val categoryRepository: CategoryRepository) : ViewModel() {

    private val expensesCategoryList = MutableLiveData<List<Category>>()
    val getExpensesCategoryList: LiveData<List<Category>> get() = expensesCategoryList

    fun addCategory(category: Category){
        viewModelScope.launch {
            try {
                categoryRepository.insertCategory(category)
            }catch (e:Exception){
                Log.d("ViewModel","Error adding category")
            }

        }
    }
    fun getExpensesCategories(){
        viewModelScope.launch {
            try {
                val list = categoryRepository.getExpensesCategories()
                expensesCategoryList.value = list
            }catch (e: Exception){
                Log.d("ViewModel","Error getting category")
            }
        }
    }
}