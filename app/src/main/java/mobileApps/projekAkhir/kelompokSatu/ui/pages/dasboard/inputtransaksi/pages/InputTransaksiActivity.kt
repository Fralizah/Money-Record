package mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.inputtransaksi.pages

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.isEmpty
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.databinding.ActivityInputTransaksiBinding
import mobileApps.projekAkhir.kelompokSatu.ui.component.channelId
import mobileApps.projekAkhir.kelompokSatu.ui.component.messageExtra
import mobileApps.projekAkhir.kelompokSatu.ui.component.notificationId
import mobileApps.projekAkhir.kelompokSatu.ui.component.titleExtra
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.TransaksiViewModel
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.dasboard.pages.DashboardActivity
import mobileApps.projekAkhir.kelompokSatu.util.Prefs
import java.text.NumberFormat
import java.util.*
import kotlin.random.Random
import mobileApps.projekAkhir.kelompokSatu.ui.component.Notification

class InputTransaksiActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInputTransaksiBinding
    private val _viewModel: TransaksiViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TransaksiViewModel::class.java]
    }

    private var _radioSelected : String? = null

    private val _format: NumberFormat = NumberFormat.getCurrencyInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInputTransaksiBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _format.setMaximumFractionDigits(0)
        _format.setCurrency(Currency.getInstance("IDR"))

        _createNotificationChannel()
        _onRadioButtonClicked()
        _datePicker()
        _mainButton()
    }

    private fun _scheduleNotification(){

        val intent = Intent(applicationContext, Notification::class.java)
        val title = _binding.tfTitle.text.toString()
        var message = ""
        if (_radioSelected == "Expense"){
            message = "Anda Baru saja mengeluarkan Dana " +
                    "\nSebesar ${_format.format(_binding.tfAmount.text.toString().toInt())}"
        }else if (_radioSelected == "Income"){
            message = "Selamat Anda Baru saja mendapatkan Pemasukkan " +
                    "\nsebesar ${_format.format(_binding.tfAmount.text.toString().toInt())}"
        }

        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationId,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = _getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )

    }

    private fun _getTime():Long{
        val calendar = Calendar.getInstance().timeInMillis
        return calendar
    }

    private fun _createNotificationChannel(){

        val name = "Notif Channel"
        val desc = "A description of channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }

    private fun _datePicker(){

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        _binding.tfDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, mYear, mMonth, mDay ->
                if (mDay > 9 && mMonth + 1 > 9){
                    _binding.tfDate.setText("$mDay/${mMonth + 1}/$mYear")
                }else if (mDay < 10 && mMonth + 1 > 9 ){
                    _binding.tfDate.setText("0$mDay/${mMonth + 1}/$mYear")
                }else if (mDay > 9 && mMonth + 1 < 10 ){
                    _binding.tfDate.setText("$mDay/0${mMonth + 1}/$mYear")
                }else{
                    _binding.tfDate.setText("0$mDay/0${mMonth + 1}/$mYear")
                }
            }, year, month, day)
            dpd.show()
        }

    }

    private fun _mainButton(){
        _binding.btnDone.setOnClickListener {
            _addTransaksi()
        }
    }

    private fun _onRadioButtonClicked(){
        _binding.radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == _binding.rbIncome.id){
                _radioSelected = _binding.rbIncome.text.toString()
            }else if (checkedId == _binding.rbExpense.id){
                _radioSelected = _binding.rbExpense.text.toString()
            }
        }
    }

    private fun _addTransaksi(){
        var  id = Random.nextInt(0, 1000000)

        if (_radioSelected == null) {
            Toast.makeText(this, "Pilih Terlebih Dahulu", Toast.LENGTH_SHORT).show()
        }else{
            if (_binding.tfDate.isEmpty()) return
            if (_binding.tfTitle.isEmpty()) return
            if (_binding.tfAmount.isEmpty()) return
            _viewModel.addtransaksi(
                Transaksi(
                    id,
                    Prefs.getUser()!!.id,
                    _binding.tfTitle.text.toString(),
                    _binding.tfAmount.text.toString().toInt(),
                    _radioSelected!!,
                    _binding.tfDate.text.toString(),
                )
            )

        }

        _viewModel.addState.observe(this){
            if (it){
                _scheduleNotification()
                val i = Intent(this, DashboardActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
}