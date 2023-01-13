package mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.pages.signin.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.isEmpty
import mobileApps.projekAkhir.kelompokSatu.databinding.ActivitySignInBinding
import mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.AuthViewModel
import mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.pages.signup.pages.SignUpActivity
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.dasboard.pages.DashboardActivity
import mobileApps.projekAkhir.kelompokSatu.util.Prefs

class SignInActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySignInBinding
    private val _viewModel: AuthViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[AuthViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _mainButton()
    }

    private fun _mainButton(){
        _binding.btnLogin.setOnClickListener {
            _login()
        }

        _binding.tvSignup.setOnClickListener {
            val i = Intent(this, SignUpActivity::class.java)
            startActivity(i)
        }
    }

    private fun _login(){

        if (_binding.tfEmail.isEmpty()) return
        if (_binding.tfPassword.isEmpty()) return

        _viewModel.getUser()

        _viewModel.getState.observe(this){
            for (i in 0..(it.size-1)){
                if (
                    it[i].email == _binding.tfEmail.text.toString()
                    && it[i].password == _binding.tfPassword.text.toString()
                ){
                    Toast.makeText(this, "Anda Berhasil Login", Toast.LENGTH_SHORT).show()
                    Prefs.isLogin = true
                    Prefs.isStart = true
                    Prefs.setUser(it[i])
                    val i = Intent(this, DashboardActivity::class.java)
                    startActivity(i)
                    finish()
                }else{
                    Toast.makeText(this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}