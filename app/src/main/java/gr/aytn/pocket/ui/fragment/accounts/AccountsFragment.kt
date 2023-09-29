package gr.aytn.pocket.ui.fragment.accounts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import gr.aytn.pocket.R
import gr.aytn.pocket.databinding.FragmentAccountsBinding

@AndroidEntryPoint
class AccountsFragment : Fragment() {

    private lateinit var viewModel: AccountsViewModel
    private var _binding: FragmentAccountsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountsBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}