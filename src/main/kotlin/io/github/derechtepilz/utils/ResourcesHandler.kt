package io.github.derechtepilz.utils

import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.InputStreamReader

class ResourcesHandler {

	private val pluginInformation: MutableMap<String, String> = mutableMapOf()

	init {
		val pluginReader = BufferedReader(InputStreamReader(javaClass.classLoader.getResourceAsStream("plugin.json")!!))
		val jsonBuilder = StringBuilder()
		var line: String?
		while (pluginReader.readLine().also { line = it } != null) {
			jsonBuilder.append(line)
		}
		val pluginInformationContents = JsonParser.parseString(jsonBuilder.toString()).asJsonObject
		for (key in pluginInformationContents.keySet()) {
			pluginInformation[key] = pluginInformationContents[key].asString
		}
	}

	fun getVersion(): String {
		return pluginInformation["version"]!!
	}

}