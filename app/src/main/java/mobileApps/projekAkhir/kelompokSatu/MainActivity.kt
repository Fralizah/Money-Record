package mobileApps.projekAkhir.kelompokSatu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mobileApps.projekAkhir.kelompokSatu.ui.pages.PortalActivity
import mobileApps.projekAkhir.kelompokSatu.ui.pages.auth.pages.signin.pages.SignInActivity
import mobileApps.projekAkhir.kelompokSatu.ui.pages.dasboard.dasboard.pages.DashboardActivity
import mobileApps.projekAkhir.kelompokSatu.util.Prefs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Prefs.isLogin){
            val i = Intent(this, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }else if (Prefs.isStart){
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            finish()
        }else{
            val i = Intent(this, PortalActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}