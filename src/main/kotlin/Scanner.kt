class Scanner(private val source: Source) : ArrayList<Token>() {
	private val tracker = Tracker(source.length)

	fun scanTokens(): List<Token> = with(tracker) {
		while (isNotAtEnd) {
			begin()
			scanToken()
		}
		Token(Type.EOF, "", null, line).run(::add)
		toList()
	}

	private fun scanToken() {
		TODO("Not yet implemented")
	}
}


class Tracker(private val sourceLength: Int) {
	private var _start = 0
	private var _current = 0

	private var _line = 1
	val line: Line
		get() = _line

	val isAtEnd: Boolean
		get() = _current >= sourceLength
	val isNotAtEnd: Boolean
		get() = !isAtEnd

	fun begin() {
		_start = _current
	}
}