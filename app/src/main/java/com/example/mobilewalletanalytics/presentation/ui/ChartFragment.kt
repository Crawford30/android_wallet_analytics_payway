package com.example.mobilewalletanalytics.presentation.ui

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mobilewalletanalytics.R
import com.example.mobilewalletanalytics.data.models.Transaction
import com.example.mobilewalletanalytics.databinding.FragmentChartBinding
import com.example.mobilewalletanalytics.presentation.viewmodels.AppViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.launch


class ChartFragment : Fragment() {
    private var binding: FragmentChartBinding? = null
    private val appViewModel: AppViewModel by activityViewModels()
    private lateinit var spinner: Spinner
    private var categories = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Chart Statistics"

        spinner = binding?.spinnerFilter ?: view.findViewById(R.id.spinnerFilter)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.transaction_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        appViewModel.allTransactionLiveData.observe(viewLifecycleOwner) { data ->
            viewLifecycleOwner.lifecycleScope.launch {

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parentView: AdapterView<*>?,
                        selectedItemView: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedOption = parentView?.getItemAtPosition(position).toString()
                        filterChartData(
                            selectedOption,
                            appViewModel.allTransactionLiveData.value.orEmpty()
                        )
                    }

                    override fun onNothingSelected(parentView: AdapterView<*>?) {
                        // Do nothing
                    }
                }


                // Initial load with the default selection
                filterChartData("All", data)
            }
        }
    }

    private fun filterChartData(selection: String, data: List<Transaction>) {
        categories.clear()  // Clear existing categories before populating

        val groupedData = data.groupBy { it.tx_finish.substring(0, 10) }
        val depositEntries = mutableListOf<BarEntry>()
        val withdrawalEntries = mutableListOf<BarEntry>()
        var currentIndex = 0

        groupedData.forEach { (_, dailyTransactions) ->
            val depositAmount = dailyTransactions.filter { it.type == "Deposit" }
                .sumByDouble { it.amount.toDouble() }
            val withdrawalAmount = dailyTransactions.filter { it.type == "Withdraw" }
                .sumByDouble { it.amount.toDouble() }

            if (selection == "All" || (selection == "Deposit" && depositAmount > 0) || (selection == "Withdrawal" && withdrawalAmount > 0)) {
                depositEntries.add(BarEntry(currentIndex.toFloat(), depositAmount.toFloat()))
                withdrawalEntries.add(BarEntry(currentIndex.toFloat(), withdrawalAmount.toFloat()))
                categories.add(dailyTransactions.first().tx_finish.substring(0, 10))
                currentIndex++
            }
        }

        val depositDataSet = BarDataSet(depositEntries, "Deposit")
        depositDataSet.color = Color.parseColor("#4CAF50") // Green for Deposit

        val withdrawalDataSet = BarDataSet(withdrawalEntries, "Withdrawal")
        withdrawalDataSet.color = Color.parseColor("#F44336") // Red for Withdrawal

        var data = BarData(depositDataSet, withdrawalDataSet)

        // Description
        val description = Description()
        when (selection) {
            "All" -> {
                description.text = "Daily Deposit and Withdrawal Amount"
                data = BarData(depositDataSet, withdrawalDataSet)
                description.setPosition(600f, 16f)
            }
            "Deposit" -> {
                description.text = "Daily Deposit Amount"
                data = BarData(depositDataSet)
                description.setPosition(500f, 16f)
            }
            "Withdrawal" -> {
                description.text = "Daily Withdrawal Amount"
                data = BarData(withdrawalDataSet)
                description.setPosition(500f, 16f)
            }
            else -> {
                description.text = "Daily Deposit and Withdrawal Amount"
                data = BarData(depositDataSet, withdrawalDataSet)
                description.setPosition(600f, 16f)
            }
        }
        description.textColor = Color.BLACK
        description.typeface = Typeface.DEFAULT_BOLD
        description.textSize = 12f
        binding?.barChart?.description = description

        // Set data
        binding?.barChart?.data = data

        // X-axis
        val xAxis = binding?.barChart?.xAxis
        xAxis?.valueFormatter = IndexAxisValueFormatter(categories)
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
        xAxis?.granularity = 1f
        xAxis?.setCenterAxisLabels(true)  // Set to true to center labels below bars

        // Further customize the position of X-axis labels
        xAxis?.setLabelCount(categories.size, true)
        xAxis?.setDrawGridLines(false)
        xAxis?.setAvoidFirstLastClipping(true)
        xAxis?.labelRotationAngle = -45f // Rotate labels for better readability

        // Y-axis
        binding?.barChart?.axisLeft?.axisMinimum = 0f
        binding?.barChart?.axisRight?.isEnabled = false

        // Legend
        val legend = binding?.barChart?.legend
        legend?.isEnabled = true
        legend?.form = Legend.LegendForm.SQUARE

        // Further customization as needed
        binding?.barChart?.legend?.isEnabled = true

        binding?.barChart?.invalidate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ChartFragment().apply {
            // If needed, you can pass arguments to the fragment here
        }
    }
}
