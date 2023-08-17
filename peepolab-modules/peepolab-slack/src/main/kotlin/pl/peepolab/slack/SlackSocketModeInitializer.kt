package pl.peepolab.slack

import com.slack.api.bolt.socket_mode.SocketModeApp
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

@Singleton
class SlackSocketModeInitializer(
    private val socketModeApp: SocketModeApp
) {
    @EventListener
    fun onStartup(event: StartupEvent?) {
        socketModeApp.startAsync()
    }

}