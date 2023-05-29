data class Token(val type: Type, val lexeme: Lexeme, val literal: Literal, val line: Line) {
	override fun toString() = "$type $lexeme $literal"
}

object Type {
	enum class SingleCharacter {
		LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
		COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,
	}

	enum class MultiCharacter {
		BANG, BANG_EQUAL,
		EQUAL, EQUAL_EQUAL,
		GREATER, GREATER_EQUAL,
		LESS, LESS_EQUAL,
	}

	enum class Literals {
		IDENTIFIER, STRING, NUMBER,
	}

	enum class Keywords {
		AND, CLASS, ELSE, FALSE, FUN, FOR, IF, NIL, OR,
		PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE, EOF
	}
}
