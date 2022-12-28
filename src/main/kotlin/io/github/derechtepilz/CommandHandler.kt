package io.github.derechtepilz

import io.github.derechtepilz.interpreter.Interpreter
import io.github.derechtepilz.utils.ConfigHandler
import io.github.derechtepilz.utils.ResourcesHandler
import java.io.Console
import java.io.File

class CommandHandler(private val args: Array<String>, private val console: Console) {

	private lateinit var configHandler: ConfigHandler
	private lateinit var resourcesHandler: ResourcesHandler

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
					val rootDirectory = File(System.getProperty("user.dir"))
					val fileStructureResult = verifyFileStructure(rootDirectory)
					if (fileStructureResult.configFile == null) {
						println("config.json not found! Have you configured your project correctly?")
						return
					}
					val configHandler = if (this::configHandler.isInitialized) configHandler else  ConfigHandler(fileStructureResult.configFile)
					val resourcesHandler = if (this::resourcesHandler.isInitialized) resourcesHandler else ResourcesHandler()
					println("Starting interpreter (version ${resourcesHandler.getVersion()}) with runtime version ${configHandler.getRuntimeVersion()}!")
					val interpreter = Interpreter(resourcesHandler, configHandler)
					interpreter.interpret()
					return
				}
				"--version" -> {
					resourcesHandler = if (this::resourcesHandler.isInitialized) resourcesHandler else ResourcesHandler()
					println("ProgrammingLanguage version: ${resourcesHandler.getVersion()}")
				}
			}
		}
	}

	private fun verifyFileStructure(file: File): FileStructureVerifier.FileStructureResult {
		return FileStructureVerifier(file).verifyFileStructure()
	}

}