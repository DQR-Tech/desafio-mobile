package dev.keader.coinconversor.view.mainAcitivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.keader.coinconversor.R
import dev.keader.coinconversor.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val navController
        get() = findNavController(R.id.nav_host_fragment)
    private val uiViewModel: UIViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.root.doOnLayout {
            NavigationUI.setupActionBarWithNavController(this, navController)
        }

        // Update network data
        // TODO: Reactive it
        uiViewModel.updateData()
        binding.lifecycleOwner = this
    }

    override fun onSupportNavigateUp(): Boolean {
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun getSnackBarInstance(text: String, duration: Int = Snackbar.LENGTH_LONG): Snackbar {
        return Snackbar.make(binding.root, text, duration)
    }
}
