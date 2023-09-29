package gr.aytn.pocket.ui.fragment.incomes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gr.aytn.pocket.AddCategoryListener
import gr.aytn.pocket.CategoryOnClickListener
import gr.aytn.pocket.R
import gr.aytn.pocket.databinding.FragmentIncomesBinding
import gr.aytn.pocket.enums.CategoryTypeEnum
import gr.aytn.pocket.model.Category
import gr.aytn.pocket.ui.adapter.CategoryAdapter

@AndroidEntryPoint
class IncomesFragment : Fragment(), AddCategoryListener, CategoryOnClickListener {

    private var _binding: FragmentIncomesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: IncomesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIncomesBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(IncomesViewModel::class.java)

        val layoutManager = GridLayoutManager(requireContext(),2)
        val adapter = CategoryAdapter(this,this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        viewModel.getIncomesCategories()
        viewModel.getIncomesCategoryList.observe(viewLifecycleOwner){
            adapter.addData(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addCategory() {
        viewModel.addCategory(Category(R.drawable.ic_internet,"Old",30, CategoryTypeEnum.INCOME,0))
        viewModel.getIncomesCategories()
    }

    override fun onCategoryClick() {

    }
}