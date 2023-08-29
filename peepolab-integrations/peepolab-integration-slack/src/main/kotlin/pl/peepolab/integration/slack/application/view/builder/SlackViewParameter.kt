package pl.peepolab.integration.slack.application.view.builder

class SlackViewParameter private constructor(
    val name: String,
    val value: String
) {
    companion object {
        fun of(name: String, value: String) = SlackViewParameter("{${name}}", value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SlackViewParameter

        if (name != other.name) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }


}
