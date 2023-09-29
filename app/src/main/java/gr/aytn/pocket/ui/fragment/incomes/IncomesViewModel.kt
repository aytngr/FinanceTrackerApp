package gr.aytn.pocket.ui.fragment.incomes

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
class IncomesViewModel @Inject constructor(private val categoryRepository: CategoryRepository): ViewModel() {
    private val incomesCategoryList = MutableLiveData<List<Category>>()
    val getIncomesCategoryList: LiveData<List<Category>> get() = incomesCategoryList

    fun addCategory(category: Category){
        viewModelScope.launch {
            try {
                categoryRepository.insertCategory(category)
            }catch (e:Exception){
                Log.d("ViewModel","Error adding category")
            }

        }
    }
    fun getIncomesCategories(){
        viewModelScope.launch {
            try {
                val list = categoryRepository.getIncomesCategories()
                incomesCategoryList.value = list
            }catch (e: Exception){
                Log.d("ViewModel","Error getting category")
            }
        }
    }
}