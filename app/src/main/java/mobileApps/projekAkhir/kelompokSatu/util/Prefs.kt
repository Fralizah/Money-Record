package mobileApps.projekAkhir.kelompokSatu.util

import com.chibatching.kotpref.KotprefModel
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation


object Prefs :KotprefModel(){

    var isLogin by booleanPref(false)
    var isStart by booleanPref(false)
    var userPref by stringPref()
    var transaksiPref by stringPref("transaksi")
    var netAmountPref by intPref(0)
    var netAmountMonthPref by intPref(0)
    var netIncomePref by intPref(0)
    var netExpensePref by intPref(0)
    var netIncomeMonthPref by intPref(0)
    var netExpenseMonthPref by intPref(0)

    fun setUser(data: UserInformation?){
        userPref = data.toJson()
    }

    fun getUser(): UserInformation? {
        if (userPref.isEmpty()) return null
        return userPref.toModel(UserInformation::class.java)
    }

    fun setTransaksi(data: List<Transaksi>?){
        transaksiPref = data.toJson()
    }

    fun getTransaksi(): Transaksi? {
        if (transaksiPref.isEmpty())return null
        return transaksiPref.toModel(Transaksi::class.java)
    }

}