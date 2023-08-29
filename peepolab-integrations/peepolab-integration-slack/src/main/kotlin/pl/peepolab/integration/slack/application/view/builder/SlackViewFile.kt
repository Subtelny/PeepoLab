package pl.peepolab.integration.slack.application.view.builder

import com.google.common.base.Preconditions
import java.io.BufferedReader
import java.io.InputStreamReader

class SlackViewFile(private val path: String) {

    init {
        Preconditions.checkArgument(path.endsWith(".json"), "File must be a json file")
    }

    fun readJson(): String {
        SlackViewFile::class.java.classLoader.getResourceAsStream(path)!!.use {
            val bufferedReader = BufferedReader(InputStreamReader(it))
            return bufferedReader.readText()
        }
    }
}
