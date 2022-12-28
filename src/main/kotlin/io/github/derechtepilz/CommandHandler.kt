package io.github.derechtepilz

import io.github.derechtepilz.utils.ResourcesHandler
import java.io.Console
import java.io.File

class CommandHandler(private val args: Array<String>, private val console: Console) {

	fun processCommand() {
		if (args.isEmpty()) {
			console.printf("You need to specify this command: pl run")
			return
		}
		if (args.size == 1) {
			when (args[0]) {
				"run" -> {
					println("Current directory: ${System.getProperty("user.dir")}")
					println("Looking for a config.json file...")

					// Assume we are in the root directory
					val file = File(System.getProperty("user.dir"))
					return
				}
				"--version" -> {
					val resourcesHandler = ResourcesHandler()
					println("ProgrammingLanguage version: ${resourcesHandler.getVersion()}")
				}
			}
		}
	}

	private fun verifyFileStructure(file: File) {
		FileStructureVerifier(file).verifyFileStructure()
	}

}