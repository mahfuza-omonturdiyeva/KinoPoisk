package uz.gita.kinopoisk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import uz.gita.kinopoisk.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater,
            null,
            false
        )
    }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { viewBinding.navHost.findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}