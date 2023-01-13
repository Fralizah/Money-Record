package mobileApps.projekAkhir.kelompokSatu.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mobileApps.projekAkhir.kelompokSatu.data.database.RoomDB
import mobileApps.projekAkhir.kelompokSatu.data.source.model.Transaksi
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation

class LocalRepository(context: Context): Repository {
    private val _db: RoomDB = RoomDB.getDatabase(context)
    private val _disposable = CompositeDisposable()

    override fun getUser(state: MutableLiveData<List<UserInformation>>) {
        _db.user().get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe(state::postValue)
            .let(_disposable::add)
    }

    override fun login(email : String, password: String) {
        _db.user().login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe()
            .let(_disposable::add)
    }

    override fun createUser(data: UserInformation) {
        _db.user().insert(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe()
            .let(_disposable::add)
    }

    override fun getTransaksi(state: MutableLiveData<List<Transaksi>>, data: UserInformation) {
        _db.transaksi().get(data.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe(state::postValue)
            .let(_disposable::add)
    }

    override fun addTransaksi(data: Transaksi) {
        _db.transaksi().insert(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe()
            .let(_disposable::add)
    }

    override fun deleteTransaksi(data: Transaksi) {
        _db.transaksi().delete(data.id)
    }


}