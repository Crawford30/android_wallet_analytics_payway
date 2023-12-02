package com.example.mobilewalletanalytics.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobilewalletanalytics.R
import com.example.mobilewalletanalytics.databinding.FragmentAllTransactionsBinding
import com.example.mobilewalletanalytics.presentation.adapters.TransactionsPagingAdapter
import com.example.mobilewalletanalytics.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllTransactionsFragment : Fragment() {

    private var binding: FragmentAllTransactionsBinding? = null
    private val appViewModel: AppViewModel by activityViewModels()
    lateinit var adapter: TransactionsPagingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "All Transactions"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllTransactionsBinding.inflate(inflater, container, false)
        adapter = TransactionsPagingAdapter(binding!!.progressBar){
        }
        binding?.allTransactionsRecyclerView?.adapter = adapter

        appViewModel.allTransactions.observe(viewLifecycleOwner){ data ->
            viewLifecycleOwner.lifecycleScope.launch {
                data?.let { adapter.submitData(data) }
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = AllTransactionsFragment()
    }
}