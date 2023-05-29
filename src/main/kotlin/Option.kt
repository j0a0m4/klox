enum class Option {
	REPL, READ, EXIT;

	companion object {
		infix fun from(size: Int) = Option.values()
			.find { it.ordinal == size }
			?: EXIT
	}
}

val Int.toOption: Option
	get() = Option from this
