fun String.eval() {
	for (token in tokens()) {
		println(token)
	}
}

private fun String.tokens() = split(" ")