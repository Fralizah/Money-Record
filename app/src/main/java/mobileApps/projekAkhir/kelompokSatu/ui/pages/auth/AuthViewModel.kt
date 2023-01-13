package mobileApps.projekAkhir.kelompokSatu.ui.pages.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mobileApps.projekAkhir.kelompokSatu.data.repository.DataRepository
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation

class AuthViewModel(application: Application): AndroidViewModel(application) {

    private val _repo = DataRepository(application)

    val getState : MutableLiveData<List<UserInformation>> by lazy {
        MutableLiveData<List<UserInformation>>()
    }

    val addState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getUser(){
        _repo.getUser(getState)
    }


    fun createUser(data: UserInformation){
        CoroutineScope(Dispatchers.IO).launch {
            _repo.createUser(data)
            withContext(Dispatchers.IO){
                addState.postValue(true)
            }
        }
    }

    fun login(email : String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            _repo.login(email, password)
        }
    }

}