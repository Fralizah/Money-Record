package mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.detailtransaksi.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.getStringExtra
import com.inyongtisto.myhelper.extension.toModel
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.databinding.ActivityDetailTransaksiBinding
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.TransaksiViewModel
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.dasboard.pages.DashboardActivity
import java.text.NumberFormat
import java.util.*

class DetailTransaksiActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailTransaksiBinding
    private val _viewModel: TransaksiViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TransaksiViewModel::class.java]
    }
    private val _format: NumberFormat = NumberFormat.getCurrencyInstance()
    private var dataHistory = Transaksi(0,0,"",0,"","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _format.setMaximumFractionDigits(0)
        _format.setCurrency(Currency.getInstance("IDR"))

        _getData()
        mainButton()
    }

    private fun mainButton(){
        _binding.btnDone.setOnClickListener {
            val i = Intent(this, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun _getData(){

        val data: String? = getStringExtra()

        dataHistory =data.toModel(Transaksi::class.java) ?: Transaksi(0, 0,"",0,"","")

        _binding.apply {
            tvDate.setText(dataHistory.date)
            tvTitle.setText(dataHistory.title)
            tvAmount.setText(_format.format(dataHistory.amount))
        }

    }
}