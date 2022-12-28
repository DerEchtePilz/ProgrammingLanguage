package io.github.derechtepilz.interpreter

import io.github.derechtepilz.utils.ConfigHandler
import io.github.derechtepilz.utils.ResourcesHandler

class Interpreter(private val resourcesHandler: ResourcesHandler, private val configHandler: ConfigHandler) {

	fun interpret() {
		if (!isRuntimeVersionCompatible()) {
			println("This version of the ProgrammingLanguage interpreter (${resourcesHandler.getVersion()}) is not compatible with the runtime version set in your config.json (${configHandler.getRuntimeVersion()})!")
			return
		}
	}

	private fun isRuntimeVersionCompatible(): Boolean {
		val configRuntimeVersion = configHandler.getRuntimeVersion().replace(".", "")
		val resourcesInterpreterVersion = resourcesHandler.getVersion().replace(".", "")
		val runtimeVersion = intArrayOf(
			configRuntimeVersion[0].digitToInt(),
			configRuntimeVersion[1].digitToInt(),
			configRuntimeVersion[2].digitToInt()
		)
		val interpreterVersion = intArrayOf(
			resourcesInterpreterVersion[0].digitToInt(),
			resourcesInterpreterVersion[1].digitToInt(),
			resourcesInterpreterVersion[2].digitToInt()
		)
		if (interpreterVersion[0] < runtimeVersion[0]) {
			return false
		}
		if (interpreterVersion[1] < runtimeVersion[1]) {
			return false
		}
		if (interpreterVersion[2] < runtimeVersion[2]) {
			return false
		}
		return true
	}

}