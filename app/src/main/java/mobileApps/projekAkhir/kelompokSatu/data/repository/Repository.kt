package mobileApps.projekAkhir.kelompokSatu.data.repository

import androidx.lifecycle.MutableLiveData
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation

interface Repository {

    fun getUser(state: MutableLiveData<List<UserInformation>>)
    fun login(email : String, password: String)
    fun createUser(data: UserInformation)

    fun getTransaksi(state: MutableLiveData<List<Transaksi>>, data: UserInformation)
    fun addTransaksi(data: Transaksi)
    fun deleteTransaksi(data: Transaksi)

}