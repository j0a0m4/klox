class Scanner(source: Source) : ArrayList<Token>() {
	private val tracker = Tracker(source)

	fun scanTokens(): List<Token> = with(tracker) {
		while (isNotAtEnd) {
			begin()
			scanToken()
		}
		Token(Type.EOF, "", null, line).run(::add)
		toList()
	}

	private fun scanToken() = with(tracker) {
		advance().also { Type.parse(it.char) }
	}
}


class Tracker(private val source: Source) {
	private var _start = 0
	private var _current = 0

	private var _line = 1
	val line: Line
		get() = _line

	val isAtEnd: Boolean
		get() = _current >= source.length
	val isNotAtEnd: Boolean
		get() = !isAtEnd

	val char: Char
		get() = source.elementAt(_current)

	fun begin() = apply {
		_start = _current
	}

	fun advance() = apply {
		_current++
	}
}