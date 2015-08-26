inStringMode() ::= <<
	//  Any char except +,- or ', or a + or - followed by more than just whitespace 
StringContent: (~['\r\n+-] | [+-] ' '* ~['\r\n ])+;// space or not 
StringEscapedQuote: [']['] {setText("'");};
StringLiteralEnd: ['] -> popMode;
FIXED_FREE_STRING_CONTINUATION: ('+' [ ]* NEWLINE) 
   {_modeStack.contains(FIXED_CalcSpec) || _modeStack.contains(FIXED_DefSpec)
     || _modeStack.contains(FIXED_OutputSpec)}?
   -> pushMode(EatCommentLinesPlus),pushMode(EatCommentLines),skip;
FIXED_FREE_STRING_CONTINUATION_MINUS: ('-' [ ]* NEWLINE) 
   {_modeStack.contains(FIXED_CalcSpec) || _modeStack.contains(FIXED_DefSpec)
     || _modeStack.contains(FIXED_OutputSpec)}?
   -> pushMode(EatCommentLines),skip;
FREE_STRING_CONTINUATION: {!_modeStack.contains(FIXED_CalcSpec)
     && !_modeStack.contains(FIXED_DefSpec)
     && !_modeStack.contains(FIXED_OutputSpec)}? 
      '+' [ ]* NEWLINE '       ' [ ]* -> skip;
FREE_STRING_CONTINUATION_MINUS: {!_modeStack.contains(FIXED_CalcSpec)
     && !_modeStack.contains(FIXED_DefSpec)
     && !_modeStack.contains(FIXED_OutputSpec)}?
      '-' [ ]* NEWLINE '       ' -> skip;
PlusOrMinus: [+-];
>>