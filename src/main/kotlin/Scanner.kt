import Error.Status

private val Char.tokenize: Type?
	get() = Type parse this

class Scanner(source: Source, private val error: Error.Handler) : ArrayList<Token>() {
	private val tracker = Tracker(source)

	fun scanTokens(): List<Token> = with(tracker) {
		while (isNotAtEnd && error.isNone) {
			begin()
			scanToken()
			error.handle()
		}
		Token(Type.EOF, "", null, line).run(::add)
		toList()
	}

	private fun scanToken() = with(tracker) {
		advance().char.tokenize
			?.run(::addToken)
			?: (error set Status.LexicalError on line)
	}

	private fun addToken(type: Type, literal: Literal? = null) = with(tracker) {
		Token(type, lexeme, literal, line).run(::add)
	}
}


class Tracker(private val source: Source) {
	private var _start = 0
	private var _current = 0

	private var _line = 1
	val line: Line
		get() = _line

	private val isAtEnd: Boolean
		get() = _current >= source.length
	val isNotAtEnd: Boolean
		get() = !isAtEnd

	val char: Char
		get() = source.elementAt(_current)

	val lexeme: Lexeme
		get() = source.substring(_start.._current)

	fun begin() = apply {
		_start = _current
	}

	fun advance() = apply {
		_current++
	}
}