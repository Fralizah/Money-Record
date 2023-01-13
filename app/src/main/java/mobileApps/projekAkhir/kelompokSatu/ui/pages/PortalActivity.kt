package mobileApps.projekAkhir.kelompokSatu.ui.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mobileApps.projekAkhir.kelompokSatu.databinding.ActivityPortalBinding
import mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.pages.signin.pages.SignInActivity
import mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.pages.signup.pages.SignUpActivity

class PortalActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPortalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPortalBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _mainButton()
    }

    private fun _mainButton(){

        _binding.btnSignin.setOnClickListener {
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            finish()
        }

        _binding.btnCreateaccount.setOnClickListener {
            val i = Intent(this, SignUpActivity::class.java)
            startActivity(i)
            finish()
        }

    }
}