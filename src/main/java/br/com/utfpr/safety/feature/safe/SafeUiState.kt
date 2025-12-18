package br.com.utfpr.safety.feature.safe

import br.com.utfpr.safety.feature.safe.model.SafeEventUiModel

data class SafeUiState(
    val status: SafeStatus = SafeStatus.LOCKED,
    val history: List<SafeEventUiModel> = emptyList()
)

sealed class SafeStatus {
    data object LOCKED : SafeStatus()
    data object UNLOCKED : SafeStatus()
}
