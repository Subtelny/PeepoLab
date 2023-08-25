package pl.peepolab.integration.slack.event

import com.slack.api.app_backend.events.handler.AppHomeOpenedHandler
import com.slack.api.app_backend.events.payload.AppHomeOpenedPayload
import com.slack.api.bolt.socket_mode.SocketModeApp
import com.slack.api.model.block.Blocks.*
import com.slack.api.model.block.SectionBlock.SectionBlockBuilder
import com.slack.api.model.block.composition.BlockCompositions.markdownText
import com.slack.api.model.block.composition.BlockCompositions.plainText
import com.slack.api.model.block.element.BlockElements.asElements
import com.slack.api.model.block.element.BlockElements.button
import com.slack.api.model.block.element.ButtonElement.ButtonElementBuilder
import com.slack.api.model.view.View
import com.slack.api.model.view.Views.view
import jakarta.inject.Singleton


@Singleton
class AppHomeOpenedEventHandler(
    private val socketModeApp: SocketModeApp,
) : AppHomeOpenedHandler() {
    override fun handle(payload: AppHomeOpenedPayload) {
        val appHomeView = view { view: View.ViewBuilder ->
            view
                .type("home")
                .blocks(asBlocks(
                    section { section: SectionBlockBuilder ->
                        section.text(markdownText { mt ->
                            mt.text(
                                "*Welcome to your _App's Home_* :tada:"
                            )
                        })
                    },
                    divider(),
                    section { section: SectionBlockBuilder ->
                        section.text(markdownText { mt ->
                            mt.text(
                                "This button won't do much for now but you can set up a listener for it using the `actions()` method and passing its unique `action_id`. See an example on <https://slack.dev/java-slack-sdk/guides/interactive-components|slack.dev/java-slack-sdk>."
                            )
                        })
                    },
                    actions { actions ->
                        actions
                            .elements(
                                asElements(
                                    button { b: ButtonElementBuilder ->
                                        b.text(plainText { pt ->
                                            pt.text(
                                                "Click me!"
                                            )
                                        }).value("button1").actionId("button_1")
                                    }
                                )
                            )
                    }
                ))
        }
        socketModeApp.client.slack.methods(socketModeApp.app.config().singleTeamBotToken).viewsPublish {
            it.userId(payload.event.user)
                .view(appHomeView)
        }
    }
}