package mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mobileApps.projekAkhir.kelompokSatu.data.repository.DataRepository
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation

class TransaksiViewModel(application: Application): AndroidViewModel(application) {

    private val _repo = DataRepository(application)

    val getState : MutableLiveData<List<Transaksi>> by lazy {
        MutableLiveData<List<Transaksi>>()
    }

    val addState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun gettransaksi(data: UserInformation){
        _repo.getTransaksi(getState, data)
    }


    fun addtransaksi(data: Transaksi){
        CoroutineScope(Dispatchers.IO).launch {
            _repo.addTransaksi(data)
            withContext(Dispatchers.IO){
                addState.postValue(true)
            }
        }
    }

    fun deletetransaksi(data: Transaksi){
        CoroutineScope(Dispatchers.IO).launch {
            _repo.deleteTransaksi(data)
            withContext(Dispatchers.IO){
                addState.postValue(true)
            }
        }
    }

}