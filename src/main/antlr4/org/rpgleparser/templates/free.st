free() ::= <<
//--------------
//OP_E: '(' [aAdDeEhHmMnNpPrRtTzZ][aAdDeEhHmMnNpPrRtTzZ]? ')';
FREE_OPEN_PAREN: OPEN_PAREN -> type(OPEN_PAREN);
FREE_CLOSE_PAREN: CLOSE_PAREN -> type(CLOSE_PAREN);
FREE_DOT: '.';
FREE_NUMBER_CONT: NUMBER {_modeStack.peek()==FIXED_DefSpec}? -> pushMode(NumberContinuation),type(NUMBER);
FREE_NUMBER: NUMBER -> type(NUMBER);
EQUAL: '=';

//FREE_OPERATION: '+' | '/' | '+=' | '-=';
//FREE_COMPARE: '\<' | '>'  | '\<'[>=] | '>=';
FREE_COLON: COLON -> type(COLON);

FREE_BY: [bB][yY];
FREE_TO: [tT][oO];
FREE_DOWNTO: [dD][oO][wW][nN][tT][oO];
FREE_ID: ID -> type(ID);
//FREE_STRING: ['] ~[']* ['];
HexLiteralStart: [xX]['] -> pushMode(InStringMode) ;
DateLiteralStart: [dD]['] -> pushMode(InStringMode) ;
TimeLiteralStart: [tT]['] -> pushMode(InStringMode) ;
TimeStampLiteralStart: [zZ]['] -> pushMode(InStringMode) ;
GraphicLiteralStart: [gG]['] -> pushMode(InStringMode) ;
UCS2LiteralStart: [uU]['] -> pushMode(InStringMode) ;
StringLiteralStart: ['] -> pushMode(InStringMode) ; 
FREE_COMMENTS: {getCharPositionInLine()>=7}? [ ]*? '//' -> pushMode(FIXED_CommentMode_HIDDEN),channel(HIDDEN) ;
FREE_WS: {getCharPositionInLine()>5}? [ \t]+ -> skip;
FREE_CONTINUATION : {_modeStack.peek()!=FIXED_CalcSpec && _modeStack.peek()!=FIXED_DefSpec}? '...' WS* NEWLINE -> type(CONTINUATION);
C_FREE_CONTINUATION_DOTS : {_modeStack.peek()==FIXED_CalcSpec}? '...' WS* NEWLINE 
	(~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [cC] ~[*] '                            ') {setText("...");} -> type(CONTINUATION);
D_FREE_CONTINUATION_DOTS : {_modeStack.peek()==FIXED_DefSpec}? '...' WS* NEWLINE 
	(~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [dD] ~[*] '                            ') {setText("...");} -> type(CONTINUATION);
C_FREE_CONTINUATION: {_modeStack.peek()==FIXED_CalcSpec}? NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [cC] ~[*] '                            ' -> skip;
D_FREE_CONTINUATION: {_modeStack.peek() == FIXED_DefSpec}? NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [dD] ~[*] '                                    ' -> skip;
F_FREE_CONTINUATION: {_modeStack.peek() == FIXED_FileSpec}? NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [fF] ~[*] '                                    ' -> skip;
FREE_LEAD_WS5 :  {getCharPositionInLine()==0}? '     ' -> skip;
FREE_LEAD_WS5_Comments :  {getCharPositionInLine()==0}?~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> channel(HIDDEN);
FREE_FREE_SPEC : {getCharPositionInLine()==5}? [  ] -> skip;

C_FREE_NEWLINE: {_modeStack.peek()==FIXED_CalcSpec}? NEWLINE -> popMode,popMode;
O_FREE_NEWLINE: {_modeStack.peek()==FIXED_OutputSpec_PGMFIELD}? NEWLINE -> type(EOL),popMode,popMode,popMode;
D_FREE_NEWLINE: {_modeStack.peek() == FIXED_DefSpec}? NEWLINE -> type(EOL),popMode,popMode;
F_FREE_NEWLINE: {_modeStack.peek() == FIXED_FileSpec}? NEWLINE -> type(EOL),popMode,popMode;
FREE_NEWLINE: {_modeStack.peek()!=FIXED_CalcSpec}? NEWLINE -> skip,popMode;
FREE_SEMI: SEMI -> popMode, pushMode(FREE_ENDED);  //Captures // immediately following the semi colon
>>