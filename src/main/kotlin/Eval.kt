fun String.eval() {
	error.handle()
	for (token in tokens()) {
		println(token)
	}
}

private fun String.tokens() = split(" ")