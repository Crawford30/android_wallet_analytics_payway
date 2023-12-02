package com.example.mobilewalletanalytics.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mobilewalletanalytics.R
import com.example.mobilewalletanalytics.databinding.FragmentTransactionsListBinding
import com.example.mobilewalletanalytics.presentation.adapters.TransactionHistoryAdapter
import com.example.mobilewalletanalytics.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TransactionsListFragment : Fragment() {
    private val appViewModel: AppViewModel by activityViewModels()
    private var binding: FragmentTransactionsListBinding? = null
    private lateinit var adapter: TransactionHistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTransactionsListBinding.inflate(inflater, container, false)
        adapter = TransactionHistoryAdapter(binding!!.progressBar) {
//            val action = UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(it)
//            findNavController().navigate(action)
        }
        binding?.transactionsHistoryRecyclerView?.adapter = adapter
        return binding?.root
        return inflater.inflate(R.layout.fragment_transactions_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Transactions History"
        viewLifecycleOwner.lifecycleScope.launch {
            appViewModel.transactionLiveData.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance() = TransactionsListFragment()
    }


}