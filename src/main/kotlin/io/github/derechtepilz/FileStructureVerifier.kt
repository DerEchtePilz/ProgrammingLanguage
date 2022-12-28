package io.github.derechtepilz

import java.io.File

class FileStructureVerifier(private val directory: File) {

	/**
	 * Verifies the file structure. It is valid when these points are true
	 * - The root directory contains a src/ folder
	 * - The root directory contains a config.json file
	 */
	fun verifyFileStructure(): FileStructureResult {
		val srcFolder = File(directory, "src")
		if (!(srcFolder.exists() && srcFolder.isDirectory)) {
			return FileStructureResult(false, null)
		}
		val configJson = File(directory, "config.json")
		if (!(configJson.exists() && !configJson.isDirectory)) {
			return FileStructureResult(false, null)
		}
		return FileStructureResult(true, configJson)
	}

	@JvmRecord
	data class FileStructureResult(val validStructure: Boolean, val configFile: File?)

}