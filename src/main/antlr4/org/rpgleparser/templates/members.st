members() ::= <<
@members {
	public boolean isEndOfToken() {
		return " (;".indexOf(_input.LA(1)) >=0;
	}
}>>