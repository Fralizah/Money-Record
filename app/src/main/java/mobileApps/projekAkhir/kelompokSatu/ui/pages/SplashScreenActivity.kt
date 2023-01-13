package mobileApps.projekAkhir.kelompokSatu.ui.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mobileApps.projekAkhir.kelompokSatu.MainActivity
import mobileApps.projekAkhir.kelompokSatu.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.ivLogo.alpha = 0f
        _binding.ivLogo.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
