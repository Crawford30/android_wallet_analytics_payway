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
import com.example.mobilewalletanalytics.databinding.FragmentHomeBinding
import com.example.mobilewalletanalytics.databinding.FragmentTransactionsListBinding
import com.example.mobilewalletanalytics.presentation.adapters.TransactionDashboardAdapter
import com.example.mobilewalletanalytics.presentation.adapters.TransactionHistoryAdapter
import com.example.mobilewalletanalytics.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val appViewModel: AppViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: TransactionDashboardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = TransactionDashboardAdapter(binding!!.progressBar){
//            val action = UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(it)
//            findNavController().navigate(action)
        }
        binding?.transactionDashboardRecycler?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Mobile Analytics"

        viewLifecycleOwner.lifecycleScope.launch {
            appViewModel.dashboardLiveData.observe(viewLifecycleOwner){
                println("DASHBOARD: ${it}")
                adapter.submitList(it.category_breakdown)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}