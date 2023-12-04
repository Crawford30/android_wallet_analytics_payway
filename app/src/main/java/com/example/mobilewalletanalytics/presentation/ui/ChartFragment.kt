package com.example.mobilewalletanalytics.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream


class ChartFragment : Fragment() {
    private var binding: FragmentChartBinding? = null
    private val appViewModel: AppViewModel by activityViewModels()
    private lateinit var spinner: Spinner
    private var selectedOption: String = "All"
    private var categories = mutableListOf<String>()
    private val depositEntries = mutableListOf<BarEntry>()
    private val withdrawalEntries = mutableListOf<BarEntry>()

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
                        selectedOption = parentView?.getItemAtPosition(position).toString()
                        filterChartData(selectedOption, data)
                    }

                    override fun onNothingSelected(parentView: AdapterView<*>?) {
                        // Do nothing
                    }
                }

                /**
                 * Initial load with default selection as [All]
                 */
                filterChartData("All", data)
            }
        }

        val exportButton = view.findViewById<Button>(R.id.btnExport)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        exportButton.setOnClickListener {
            if (checkPermission()) {
                // Disable the button and show the progress bar
                exportButton.isEnabled = false
                progressBar.visibility = View.VISIBLE

                // Perform the export operation asynchronously
                GlobalScope.launch {
                    exportChartDataToExcel(
                        selectedOption,
                        categories,
                        depositEntries,
                        withdrawalEntries
                    )

                    // Re-enable the button and hide the progress bar on the main/UI thread
                    withContext(Dispatchers.Main) {
                        exportButton.isEnabled = true
                        progressBar.visibility = View.GONE
                    }
                }
            } else {
                requestPermission()
            }
        }


//        view.findViewById<Button>(R.id.btnExport).setOnClickListener {
//            if (checkPermission()) {
//                exportChartDataToExcel(
//                    selectedOption,
//                    categories,
//                    depositEntries,
//                    withdrawalEntries
//                )
//            } else {
//                requestPermission()
//            }
//        }
    }

    /**
     * Function to export the data to excel
     */
    private fun exportChartDataToExcel(
        selection: String,
        categories: List<String>,
        depositEntries: List<BarEntry>,
        withdrawalEntries: List<BarEntry>
    ) {
        try {
            /**
             * Create a new workbook
             */
            val workbook: Workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Chart Data")

            /**
             * Create the header row
             */
            val headerRow = sheet.createRow(0)


            /**
             * Handle different scenario based on user selection
             */
            when (selection) {
                "Deposit" -> {
                    headerRow.createCell(0).setCellValue("Transaction Date")
                    headerRow.createCell(1).setCellValue("Deposit Amount")
                    addDataRows(sheet, categories, depositEntries, withdrawalEntries, "Deposit")
                }
                "Withdrawal" -> {
                    headerRow.createCell(0).setCellValue("Transaction Date")
                    headerRow.createCell(1).setCellValue("Withdrawal Amount")
                    addDataRows(sheet, categories, depositEntries, withdrawalEntries, "Withdrawal")
                }
                "All" -> {
                    headerRow.createCell(0).setCellValue("Transaction Date")
                    headerRow.createCell(1).setCellValue("Deposit Amount")
                    headerRow.createCell(2).setCellValue("Withdrawal Amount")
                    addDataRows(sheet, categories, depositEntries, withdrawalEntries, "All")
                }
            }


            /**
             * Save the workbook to a file
             */
            val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                "chart_data.xlsx"
            )



            MediaScannerConnection.scanFile(
                requireContext(),
                arrayOf(file.absolutePath),
                null,
                null
            )

            Log.d("File Path", file.absolutePath)

            val uri = FileProvider.getUriForFile(
                requireContext(),
                "com.example.mobilewalletanalytics.fileprovider",
                file
            )

            val fileOut = requireContext().contentResolver.openOutputStream(uri)

            if (fileOut != null) {
                workbook.write(fileOut)
                fileOut.close()

                /**
                 * Providing feedback to the user using toast
                 */

                activity?.runOnUiThread {
                    Toast.makeText(
                        requireContext(),
                        "Chart data exported to Excel ${file.absoluteFile}. Please check for the name in your file explorer app or document directory",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), "Error Exporting Chart", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            activity?.runOnUiThread {
                Toast.makeText(requireContext(), "Error Exporting Chart", Toast.LENGTH_SHORT).show()
            }

        }
    }

    /**
     * The function is used to dynamically update the excel column header based on filter applied, ie [Deposit] Only or [Withdraw] only or [Deposit]
     * or [All] to represent both [Deposit] and [Withdraw]
     */
    private fun addDataRows(
        sheet: Sheet,
        categories: List<String>,
        depositEntries: List<BarEntry>,
        withdrawalEntries: List<BarEntry>,
        selectedType: String
    ) {
        val columnIndex = when (selectedType) {
            "Deposit" -> 1
            "Withdrawal" -> 1
            "All" -> 2
            else -> 1
        }

        for (i in categories.indices) {
            val dataRow = sheet.getRow(i + 1) ?: sheet.createRow(i + 1)
            dataRow.createCell(0).setCellValue(categories[i])

            when (selectedType) {
                "Deposit" -> dataRow.createCell(columnIndex)
                    .setCellValue(depositEntries[i].y.toDouble())
                "Withdrawal" -> dataRow.createCell(columnIndex)
                    .setCellValue(withdrawalEntries[i].y.toDouble())
                "All" -> {
                    dataRow.createCell(1).setCellValue(depositEntries[i].y.toDouble())
                    dataRow.createCell(2).setCellValue(withdrawalEntries[i].y.toDouble())
                }
                else -> throw IllegalArgumentException("Invalid selected type: $selectedType")
            }
        }
    }

    /**
     * Check for permission if GRANTED or not, and if not, request for it
     */
    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_PERMISSION_CODE
        )
    }


    /**
     * Requesting for file permission(to write to file)
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSION_CODE && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {

            /**
             * if you requested MANAGE_EXTERNAL_STORAGE, you don't need to request WRITE_EXTERNAL_STORAGE separately
             */
            exportChartDataToExcel(
                selectedOption,
                categories,
                depositEntries,
                withdrawalEntries
            )
        } else {
            Toast.makeText(
                requireContext(),
                "Permission denied. Cannot export chart data.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * This function filters out the chart based on [type] which can be  [Deposit] or [Withdraw]
     */
    private fun filterChartData(selection: String, data: List<Transaction>) {
        categories.clear()  // Clear existing categories before populating

        val groupedData = data.groupBy { it.tx_finish.substring(0, 10) }
        var currentIndex = 0

        /**
         * Group the transaction data based on the transaction [type] which can be  [Deposit] or [Withdraw]
         */
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

        /**
         *  [Deposit] dataset configuration with its label color set to [Green]
         */
        val depositDataSet = BarDataSet(depositEntries, "Deposit")
        depositDataSet.color = Color.parseColor("#4CAF50") // Green for Deposit

        /**
         *  [Withdrawal] dataset configuration with its label color set to [Red]
         */
        val withdrawalDataSet = BarDataSet(withdrawalEntries, "Withdrawal")
        withdrawalDataSet.color = Color.parseColor("#F44336") // Red for Withdrawal

        var data: BarData

        /**
         * Description, change it based on user selection of the type
         */
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

        /**
         * Set Date data as x-axis data
         */
        binding?.barChart?.data = data


        /**
         * X-axis configurations
         */
        val xAxis = binding?.barChart?.xAxis
        xAxis?.valueFormatter = IndexAxisValueFormatter(categories)
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
        xAxis?.granularity = 1f
        xAxis?.setCenterAxisLabels(true)


        /**
         * X-axis label configurations
         */
        xAxis?.setLabelCount(categories.size, true)
        xAxis?.setDrawGridLines(false)
        xAxis?.setAvoidFirstLastClipping(true)
        xAxis?.labelRotationAngle = -45f // Rotate labels for better readability

        /**
         * Y-axis
         */
        binding?.barChart?.axisLeft?.axisMinimum = 0f
        binding?.barChart?.axisRight?.isEnabled = false

        /**
         * Legend configurations
         */
        val legend = binding?.barChart?.legend
        legend?.isEnabled = true
        legend?.form = Legend.LegendForm.SQUARE
        binding?.barChart?.legend?.isEnabled = true

        /**
         * Set a larger initial scale to make it visible
         * for large data sets
         */
        binding?.barChart?.setVisibleXRangeMaximum(10f)

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
        private const val REQUEST_PERMISSION_CODE = 1

        @JvmStatic
        fun newInstance(param1: String, param2: String) = ChartFragment().apply {
            // If needed, you can pass arguments to the fragment here
        }
    }
}
