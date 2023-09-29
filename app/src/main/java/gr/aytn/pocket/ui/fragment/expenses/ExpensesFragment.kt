package gr.aytn.pocket.ui.fragment.expenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gr.aytn.pocket.AddCategoryListener
import gr.aytn.pocket.CategoryOnClickListener
import gr.aytn.pocket.R
import gr.aytn.pocket.databinding.BottomSheetCategoryBinding
import gr.aytn.pocket.databinding.FragmentExpensesBinding
import gr.aytn.pocket.enums.CategoryTypeEnum
import gr.aytn.pocket.model.Category
import gr.aytn.pocket.ui.adapter.CategoryAdapter
import gr.aytn.pocket.ui.fragment.CategoryBottomSheet

@AndroidEntryPoint
class ExpensesFragment : Fragment(), AddCategoryListener, CategoryOnClickListener {

    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ExpensesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpensesBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(ExpensesViewModel::class.java)

        val layoutManager = GridLayoutManager(requireContext(),2)
        val adapter = CategoryAdapter(this, this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        viewModel.getExpensesCategories()
        viewModel.getExpensesCategoryList.observe(viewLifecycleOwner){
            adapter.addData(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addCategory() {
        viewModel.addCategory(Category(R.drawable.ic_internet,"New",20, CategoryTypeEnum.EXPENSE,0))
        viewModel.getExpensesCategories()
    }

    override fun onCategoryClick() {
        val bottomSheet = CategoryBottomSheet()
        bottomSheet.show(requireActivity().supportFragmentManager,"BOTTOM_SHEET")
    }


}