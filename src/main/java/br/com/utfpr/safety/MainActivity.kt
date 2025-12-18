package br.com.utfpr.safety

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import br.com.utfpr.safety.core.theme.SafetyTheme
import br.com.utfpr.safety.feature.safe.SafeScreen
import br.com.utfpr.safety.feature.safe.SafeViewModel


class MainActivity : ComponentActivity() {
    private val vm: SafeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafetyTheme {
                SafeScreen(vm)
            }
        }
    }
}
