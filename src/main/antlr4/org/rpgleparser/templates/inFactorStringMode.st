inFactorStringMode() ::= <<
InFactor_StringContent:({(getCharPositionInLine()>=11 && getCharPositionInLine()\<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=62)
}?
		~[\r\n'])+ -> type(StringContent);
		
InFactor_StringEscapedQuote: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=61)
}?
		[']['] -> type(StringEscapedQuote);
		
InFactor_StringLiteralEnd: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=62)
}?
		['] -> type(StringLiteralEnd),popMode;

InFactor_EndFactor: {(getCharPositionInLine()==25)
			|| (getCharPositionInLine()==49)
			|| (getCharPositionInLine()==61)
}? -> skip,popMode;
>>