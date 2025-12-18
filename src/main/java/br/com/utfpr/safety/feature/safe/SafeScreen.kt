package br.com.utfpr.safety.feature.safe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import br.com.utfpr.safety.feature.safe.components.SafeHeader
import br.com.utfpr.safety.feature.safe.components.SafeHistorySection
import br.com.utfpr.safety.feature.safe.components.SafeStatusCard


@Composable
fun SafeScreen(vm: SafeViewModel) {
    val status by vm.status.collectAsState()
    val history by vm.history.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        SafeHeader()

        SafeStatusCard(status);

        Spacer(modifier = Modifier.height(28.dp))

        if (history.isNotEmpty()) {
            SafeHistorySection(history)
        }
    }
}
