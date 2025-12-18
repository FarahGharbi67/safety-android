package br.com.utfpr.safety.remote.mqtt

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class MqttManager(
    brokerUrl: String = "tcp://broker.hivemq.com:1883",
    clientId: String = "SAFETY_IOT_ESP32_${System.currentTimeMillis()}"
) {
    private val client = MqttClient(brokerUrl, clientId, MemoryPersistence())

    private val _status = MutableStateFlow<String?>(null)
    val status: StateFlow<String?> get() = _status

    fun connect(onConnected: (() -> Unit)? = null) {
        val options = MqttConnectOptions().apply {
            isAutomaticReconnect = true
            isCleanSession = true
        }

        client.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {}

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                val payload = message?.toString() ?: ""
                Log.d("MQTT", "[$topic] $payload")

                when (topic) {
                    "safer/status" -> _status.value = payload
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {}
        })

        client.connect(options)
        client.subscribe("safer/status")

        onConnected?.invoke()
    }

    fun disconnect() {
        if (client.isConnected) client.disconnect()
    }
}
