package xdd.example.podcastsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xdd.example.podcastsdemo.ui.overview.OverviewFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OverviewFragment.newInstance())
                .commitNow()
        }
    }
}