import Error.Logger
import kotlin.system.exitProcess

object Error {

	enum class Status(val code: Int) {
		None(0),
		InvalidArgument(64),
		UserMistake(65);

		fun panicIfFailure() {
			if (this != None) {
				exitProcess(code)
			}
		}
	}

	fun interface Logger {
		fun error(line: Line, message: String, where: String)
	}

	class Handler(
		private val logger: Logger = Logger { line, message, where ->
			System.err.println("[line $line] Error $where: $message")
		}
	) {
		private var status = Status.None

		infix fun set(status: Status) {
			this.status = status
		}

		private fun error(line: Line, message: String) {
			logger.error(line, message, "")
		}

		fun handle() {
			status.panicIfFailure()
		}
	}
}
