package pl.peepolab.integration.slack.ui.event

import com.slack.api.app_backend.events.handler.AppHomeOpenedHandler
import com.slack.api.app_backend.events.payload.AppHomeOpenedPayload
import com.slack.api.bolt.socket_mode.SocketModeApp
import com.slack.api.model.block.Blocks
import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.BlockCompositions
import com.slack.api.model.block.element.BlockElements
import com.slack.api.model.block.element.ButtonElement
import com.slack.api.model.view.View.ViewBuilder
import com.slack.api.model.view.Views
import java.io.BufferedReader
import java.io.InputStreamReader


private const val INTEGRATION_AVAILABLE_VIEW_FILE_NAME = "views/homeapp/test.json"

//@Singleton
class TestAppHomeOpenedEventHandler(
    private val socketModeApp: SocketModeApp,
) : AppHomeOpenedHandler() {

    private val view: String by lazy {
        val resource =
            AppHomeOpenedHandler::class.java.classLoader.getResourceAsStream(INTEGRATION_AVAILABLE_VIEW_FILE_NAME)!!

        val bufferedReader = BufferedReader(InputStreamReader(resource))
        bufferedReader.readText()
    }

    override fun handle(payload: AppHomeOpenedPayload) {
        val appHomeView = Views.view { view: ViewBuilder ->
            view
                .type("home")
                .blocks(Blocks.asBlocks(
                    Blocks.section { section: SectionBlock.SectionBlockBuilder ->
                        section.text(BlockCompositions.markdownText { mt ->
                            mt.text(
                                "*Welcome to your _App's Home_* :tada:"
                            )
                        })
                    },
                    Blocks.divider(),
                    Blocks.section { section: SectionBlock.SectionBlockBuilder ->
                        section.text(BlockCompositions.markdownText { mt ->
                            mt.text(
                                "This button won't do much for now but you can set up a listener for it using the `actions()` method and passing its unique `action_id`. See an example on <https://slack.dev/java-slack-sdk/guides/interactive-components|slack.dev/java-slack-sdk>."
                            )
                        })
                    },
                    Blocks.actions { actions ->
                        actions
                            .elements(
                                BlockElements.asElements(
                                    BlockElements.button { b: ButtonElement.ButtonElementBuilder ->
                                        b.text(BlockCompositions.plainText { pt ->
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
        val methods = socketModeApp.client.slack.methods(socketModeApp.app.config().singleTeamBotToken, payload.teamId)
        methods.viewsPublish {
            it.userId(payload.event.user)
                .viewAsString(view)
        }
    }
}