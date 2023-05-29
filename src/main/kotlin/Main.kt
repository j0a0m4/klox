import Error.Status
import Option.*
import java.io.File

fun main(args: Array<String>) = with(args) {
	when (size.toOption) {
		EXIT -> exit()
		REPL -> repl()
		READ -> first().file.readText().eval()
	}
	error.handle()
}

val error = Error.Handler()

private val String.file: File
	get() = File(this)

private val Int.toOption: Option
	get() = Option from this

enum class Option {
	REPL, READ, EXIT;

	companion object {
		infix fun from(size: Int) = Option.values()
			.find { it.ordinal == size }
			?: EXIT
	}
}

fun exit() {
	println("Usage: klox [script]");
	error set Status.InvalidArgument
}

fun repl() {
	while (true) {
		print("> ")
		readlnOrNull()
			?.eval()
			?: break
		error set Status.None
	}
}
