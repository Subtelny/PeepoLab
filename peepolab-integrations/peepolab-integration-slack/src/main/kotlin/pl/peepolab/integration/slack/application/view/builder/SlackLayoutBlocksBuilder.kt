package pl.peepolab.integration.slack.application.view.builder

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slack.api.model.block.LayoutBlock
import com.slack.api.util.json.GsonFactory
import org.apache.commons.lang3.StringUtils

class SlackLayoutBlocksBuilder(
    private val blocks: MutableList<LayoutBlock> = mutableListOf()
) {

    companion object {

        val objectMapper: Gson = GsonFactory.createSnakeCase()

    }

    fun addBlocks(
        layoutBlocksJson: String,
        parameters: List<SlackViewParameter> = emptyList()
    ): SlackLayoutBlocksBuilder {
        val preparedJson = replaceParameters(layoutBlocksJson, parameters)
        val deserializedBlocks: List<LayoutBlock> = deserializeBlocks(preparedJson)
        blocks.addAll(deserializedBlocks)
        return this
    }

    fun build(): List<LayoutBlock> = blocks.toList()

    private fun replaceParameters(
        json: String,
        parameters: List<SlackViewParameter>,
    ): String {
        if (parameters.isEmpty()) {
            return json
        }
        val keyValue = parameters.associate { it.name to it.value }
        return StringUtils.replaceEach(json, keyValue.keys.toTypedArray(), keyValue.values.toTypedArray())
    }

    private fun deserializeBlocks(json: String): List<LayoutBlock> {
        val typeToken = TypeToken.getParameterized(List::class.java, LayoutBlock::class.java).type
        return objectMapper.fromJson(json, typeToken)
    }


}