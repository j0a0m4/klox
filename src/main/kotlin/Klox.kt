import Error.Status
import Option.*
import java.io.File

private val String.file: File
	get() = File(this)

class Klox(
	option: Option,
	source: Source?,
	private val error: Error.Handler = Error.Handler(),
) {
	init {
		when (option) {
			EXIT -> error set Status.InvalidArgument
			REPL -> runPrompt()
			READ -> source?.file?.readText()?.eval()
		}
		error.handle()
	}

	private fun runPrompt() {
		while (true) {
			print("> ")
			readlnOrNull()?.eval() ?: break
			error set Status.None
		}
	}
}
