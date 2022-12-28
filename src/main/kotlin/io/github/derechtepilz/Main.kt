package io.github.derechtepilz

import java.io.Console

// This is the entrypoint of the ProgrammingLanguage interpreter
fun main(args: Array<String>) {
	val console: Console? = System.console()
	if (console == null) {
		// Write error.log into System.getProperty("user.home") + "\\Desktop\\Programming Language Error Log - (Timestamp).log"
		return
	}
	CommandHandler(args, console).processCommand()
}