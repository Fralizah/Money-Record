package mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.dasboard.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import mobileApps.projekAkhir.kelompokSatu.databinding.ActivityDashboardBinding
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.TransaksiViewModel
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.dasboard.component.HistoryTransaksiAdapter
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.inputtransaksi.pages.InputTransaksiActivity
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.profile.pages.ProfileActivity
import mobileApps.projekAkhir.kelompokSatu.util.Prefs
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDashboardBinding
    private val _adapterHistoryTransaksi = HistoryTransaksiAdapter()
    private val _viewModel: TransaksiViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TransaksiViewModel::class.java]
    }

    private val _format: NumberFormat = NumberFormat.getCurrencyInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _format.setMaximumFractionDigits(0)
        _format.setCurrency(Currency.getInstance("IDR"))

        _mainButton()
        _setupAdapter()
        _getDataUser()
        _setUpObservedData()
    }

    private fun _getDataUser(){
        val user = Prefs.getUser()
        if (user != null){
            _binding.apply {
                _binding.tvUsername.text = user.username
            }
        }
    }

    private fun _setupAdapter(){
        _binding.rvHistory.adapter   = _adapterHistoryTransaksi
    }

    private fun _setUpObservedData() {

        _viewModel.gettransaksi(Prefs.getUser()!!)

        _viewModel.getState.observe(this){
            var netAmount = 0
            var netIncome = 0
            var netExpense = 0

            var netAmountMonth = 0
            var netIncomeMonth = 0
            var netExpenseMonth = 0

            for(i in 0..(it.size-1)){
                if (it[i].type == "Income"){
                    netIncome = netIncome + it[i].amount!!
                    if (
                        LocalDate.parse(it[i].date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).month
                        == LocalDateTime.now().month
                    ){
                        netIncomeMonth = netIncomeMonth + it[i].amount
                    }
                }else if(it[i].type == "Expense"){
                    netExpense = netExpense + it[i].amount!!
                    if (
                        LocalDate.parse(it[i].date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).month
                        == LocalDateTime.now().month
                        &&
                        LocalDate.parse(it[i].date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).year
                        == LocalDateTime.now().year
                    ){
                        netExpenseMonth = netExpenseMonth + it[i].amount
                    }
                }
            }

            netAmount = netIncome - netExpense
            netAmountMonth = netIncomeMonth - netExpenseMonth

            _binding.tvNetamount.text =_format.format(netAmount)
            _binding.tvIncome.text =_format.format(netIncome)
            _binding.tvExpense.text =_format.format(netExpense)
            _adapterHistoryTransaksi.addItems(it?: emptyList())

            // net All Transaction
            Prefs.netAmountPref = netAmount
            Prefs.netIncomePref = netIncome
            Prefs.netExpensePref = netExpense

            // net All Transaction in Month
            Prefs.netAmountMonthPref = netAmountMonth
            Prefs.netIncomeMonthPref = netIncomeMonth
            Prefs.netExpenseMonthPref = netExpenseMonth
        }

    }

    private fun _mainButton(){
        _binding.btnAddtransaksi.setOnClickListener {
            val i = Intent(this, InputTransaksiActivity::class.java)
            startActivity(i)
        }

        _binding.profileImage.setOnClickListener {
            val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }
    }
}