package br.com.utfpr.safety.feature.safe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.filterNotNull

import br.com.utfpr.safety.remote.mqtt.AccessEvent
import br.com.utfpr.safety.remote.mqtt.EventType
import br.com.utfpr.safety.remote.mqtt.MqttManager


class SafeViewModel(app: Application) : AndroidViewModel(app) {
    private val mqtt = MqttManager()

    private val _status = MutableStateFlow<String?>(null)
    val status: StateFlow<String?> = _status

    private val _history = MutableStateFlow<List<AccessEvent>>(emptyList())
    val history: StateFlow<List<AccessEvent>> = _history

    init {
        mqtt.connect()

        viewModelScope.launch {
            mqtt.status
                .filterNotNull()
                .collect {
                    _status.value = it
                    addEvent(it)
                }
        }
    }

    private fun addEvent(msg: String) {
        _history.value = listOf(AccessEvent(EventType.STATUS, msg)) + _history.value.take(19)
    }

    override fun onCleared() {
        mqtt.disconnect()
    }
}
