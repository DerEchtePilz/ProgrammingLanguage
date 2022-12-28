package io.github.derechtepilz.utils

import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ConfigHandler(config: File) {

	private val configContents: MutableMap<String, String> = mutableMapOf()

	init {
		val reader = BufferedReader(FileReader(config))
		val builder = StringBuilder()
		var line: String?
		while (reader.readLine().also { line = it } != null) {
			builder.append(line)
		}
		val configJson = JsonParser.parseString(builder.toString()).asJsonObject
		for (key in configJson.keySet()) {
			configContents[key] = configJson[key].asString
		}
	}

	fun getMain(): String {
		return configContents["main"]!!
	}

	fun getVersion(): String {
		return configContents["project.version"]!!
	}

	fun getRuntimeVersion(): String {
		return configContents["runtime.version"]!!
	}

}