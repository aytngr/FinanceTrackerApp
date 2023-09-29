package gr.aytn.pocket.ui.fragment.categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import gr.aytn.pocket.R
import gr.aytn.pocket.databinding.FragmentCategoriesBinding
import gr.aytn.pocket.ui.adapter.FragmentAdapter

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var viewModel: CategoriesViewModel
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)


        binding.tabLayout.addTab(binding.tabLayout.newTab().setCustomView(R.layout.tab_expense_layout))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setCustomView(R.layout.tab_income_layout))

        val adapter = FragmentAdapter(requireActivity().supportFragmentManager,lifecycle)
        binding.viewPager2.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}