package mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.pages.signup.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.isEmpty
import mobileApps.projekAkhir.kelompokSatu.data.source.model.UserInformation
import mobileApps.projekAkhir.kelompokSatu.databinding.ActivitySignUpBinding
import mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.AuthViewModel
import mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.pages.signin.pages.SignInActivity
import mobileApps.projekAkhir.kelompokSatu.util.Prefs
import kotlin.random.Random

class SignUpActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySignUpBinding
    private val _viewModel: AuthViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[AuthViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _mainButton()
    }


    private fun _mainButton (){

        _binding.btnSignup.setOnClickListener {
            _register()
            Toast.makeText(this, "Akun Anda Telah Terdaftar", Toast.LENGTH_SHORT).show()
            Prefs.isStart = true
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            finish()
        }

        _binding.tvLogin.setOnClickListener {
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
        }

    }

    private  fun _register(){
        var  id = Random.nextInt(0, 1000000)

        if (_binding.tfUsername.isEmpty()) return
        if (_binding.tfEmail.isEmpty()) return
        if (_binding.tfPassword.isEmpty()) return

        _viewModel.createUser(
            UserInformation(
                id,
                _binding.tfUsername.text.toString(),
                _binding.tfEmail.text.toString(),
                _binding.tfPassword.text.toString(),
            )
        )

    }
}