package pl.peepolab.integration.slack.model

import com.slack.api.model.block.Blocks
import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.BlockCompositions
import com.slack.api.model.block.element.BlockElements
import com.slack.api.model.block.element.ButtonElement
import com.slack.api.model.view.View
import com.slack.api.model.view.Views
import com.slack.api.util.json.GsonFactory
import org.junit.jupiter.api.Test

class TEstTEst {

    @Test
    fun lol() {
        val GSON = GsonFactory.createSnakeCase()

        val appHomeView = Views.view { view: View.ViewBuilder ->
            view
                .type("home")
                .blocks(
                    Blocks.asBlocks(
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

        val json = GSON.toJson(appHomeView)
        val fromJson = GSON.fromJson(json, View::class.java)

        assert(appHomeView == fromJson)
    }

}