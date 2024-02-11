package io.familymoments.app.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import io.familymoments.app.ui.screen.LoginScreen
import io.familymoments.app.ui.theme.FamilyMomentsTheme
import io.familymoments.app.viewmodel.LoginViewModel

@AndroidEntryPoint

class LoginActivity : BaseActivity<LoginViewModel>(LoginViewModel::class) {
    @OptIn(ExperimentalMaterial3Api::class)
    override val screen:   @Composable () -> Unit = { LoginScreen(viewModel) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.surface) {
                FamilyMomentsTheme { screen() }
            }
        }
    }
}
