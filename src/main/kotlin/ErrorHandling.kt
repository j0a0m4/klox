import Error.Logger
import kotlin.system.exitProcess

object Error {

	enum class Status(val code: Int, val message: String) {
		None(0, ""),
		InvalidArgument(64, "Usage: klox [script]"),
		LexicalError(65, "Unexpected character.");

		val isFlagged: Boolean
			get() = this != None


		fun killProcess() {
			exitProcess(code)
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
		val isNone: Boolean
			get() = status == Status.None

		private var status = Status.None

		private var line: Line = 0

		infix fun set(status: Status) = apply {
			this.status = status
		}

		fun handle() = with(status) {
			if (isFlagged) {
				logger.error(line, message, "")
				killProcess()
			}
		}

		infix fun on(line: Line) = apply {
			this.line = line
		}
	}
}
