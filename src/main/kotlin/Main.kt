import Option.*
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) = with(args) {
	when (size.toOption) {
		EXIT -> exit()
		REPL -> repl()
		READ -> first().file.readText().eval()
	}
}

private val String.file: File
	get() = File(this)

private val Int.toOption: Option
	get() = Option.from(this)

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
	exitProcess(64);
}

fun repl() {
	while (true) {
		print("> ")
		readlnOrNull()
			?.eval()
			?: break
	}
}