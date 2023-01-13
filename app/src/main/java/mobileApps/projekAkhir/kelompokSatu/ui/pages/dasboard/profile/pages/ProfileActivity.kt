package mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.profile.pages

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import mobileApps.projekAkhir.kelompokSatu.MainActivity
import mobileApps.projekAkhir.kelompokSatu.databinding.ActivityProfileBinding
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.TransaksiViewModel
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.dasboard.pages.DashboardActivity
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.profile.components.customChart
import mobileApps.projekAkhir.kelompokSatu.util.Prefs
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class ProfileActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityProfileBinding

    private lateinit var barData: BarData
    private lateinit var barDataSet: BarDataSet
    private lateinit var barEntriesList: ArrayList<BarEntry>
    val myColors: ArrayList<Int> = ArrayList()
    private val _format: NumberFormat = NumberFormat.getCurrencyInstance()

    private val _viewModel: TransaksiViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TransaksiViewModel::class.java]
    }

    private var income = 0
    private val expense = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _format.setMaximumFractionDigits(0)
        _format.setCurrency(Currency.getInstance("IDR"))

        _getDataUser()
        _setUpObservedData()

        myColors.add(Color.RED)
        myColors.add(Color.GREEN)

        _getDataChart(Prefs.netIncomeMonthPref, Prefs.netExpenseMonthPref)

        barDataSet = BarDataSet(barEntriesList, "IDR")
        barData = BarData(barDataSet)
        _binding.bcTransaksi.data = barData

        barDataSet.valueTextColor = Color.BLACK
        barDataSet.setColor(Color.parseColor("#D9D9D9"))
        barDataSet.valueTextSize = 16F

        customChart(_binding.bcTransaksi, myColors)
        _mainButton()
    }

    private fun _mainButton(){
        _binding.btnLogout.setOnClickListener {
            _logOut()
        }

        _binding.btnBack.setOnClickListener {
            val i = Intent(this, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun _logOut(){
        Prefs.isLogin = false
        Prefs.userPref = ""
        Prefs.transaksiPref = ""
        Prefs.netAmountPref = 0
        Prefs.netAmountMonthPref = 0
        Prefs.netIncomePref = 0
        Prefs.netExpensePref = 0
        Prefs.netIncomeMonthPref = 0
        Prefs.netExpenseMonthPref = 0
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun _getDataUser(){
        val user =Prefs.getUser()
        if (user != null){
            _binding.apply {
                _binding.tvUsername.text = user.username
                _binding.tvEmail.text = user.email
            }
        }
    }

    private fun _setUpObservedData() {

        _viewModel.gettransaksi(Prefs.getUser()!!)

        _binding.tvNetAmount.text = _format.format(Prefs.netAmountPref)
        _binding.tvExpense.text = _format.format(Prefs.netExpensePref)
        _binding.tvIncome.text = _format.format(Prefs.netIncomePref)
        _binding.tvNetamountThisMount.text = _format.format(Prefs.netAmountMonthPref)

    }

    private  fun _getDataChart(income:Int, expense:Int){
        barEntriesList = ArrayList()

        barEntriesList.add(BarEntry(1f, expense.toFloat()))
        barEntriesList.add(BarEntry(2f, income.toFloat()))
    }
}