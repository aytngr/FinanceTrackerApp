package gr.aytn.pocket.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import gr.aytn.pocket.ui.fragment.expenses.ExpensesFragment
import gr.aytn.pocket.ui.fragment.incomes.IncomesFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0){
            ExpensesFragment()
        }else{
            IncomesFragment()
        }
    }
}