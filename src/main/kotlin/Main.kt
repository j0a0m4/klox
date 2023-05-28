import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
	with(args) {
		when (size) {
			0    -> repl()
			1    -> first().readBytes().execute()
			else -> exit()
		}
	}
}

private val String.path
	get() = Paths.get(this)

private fun String.readBytes() =
	Files.readAllBytes(path)
		.let { String(it, Charset.defaultCharset()) }

private fun String.execute() {
	val tokens = Scanner(this).tokens()
	for (token in tokens) {
		println(token)
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
			?.execute()
			?: break
	}
}
