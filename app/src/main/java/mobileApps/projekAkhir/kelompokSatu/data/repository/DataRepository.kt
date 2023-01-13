package mobileApps.projekAkhir.kelompokSatu.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation

class DataRepository(context: Context): Repository {

    private val _localRepository = LocalRepository(context)

    // UserInformation
    override fun getUser(state: MutableLiveData<List<UserInformation>>) = _localRepository.getUser(state)
    override fun createUser(data: UserInformation) = _localRepository.createUser(data)
    override fun login(email : String, password: String) = _localRepository.login(email, password)

    // TransactionInfo
    override fun addTransaksi(data: Transaksi) = _localRepository.addTransaksi(data)
    override fun getTransaksi(state: MutableLiveData<List<Transaksi>>, data: UserInformation) = _localRepository.getTransaksi(state, data)
    override fun deleteTransaksi(data: Transaksi) =_localRepository.deleteTransaksi(data)

}