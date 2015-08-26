RpgLexer() ::= <</**
 * Define a grammar called RpgLexer
 */
 
lexer grammar RpgLexer;

<members()>

// Parser Rules
<initialRules()>

mode DirectiveMode;
<directives()>

mode SKIP_REMAINING_WS;
DIR_FREE_OTHER_TEXT: ~[\r\n]+ -> popMode,skip;

mode EndOfSourceMode;
EOS_Text : ~[\r\n]+ ;
EOS_EOL : NEWLINE -> type(EOL); 

// -----------------  ---------------------
mode OpCode;
OP_WS: {getCharPositionInLine()>5}? [ \t]+ -> skip;
<opCodes()>
OP_NoSpace: -> skip,mode(FREE);//,pushMode(FreeOpExtender);

mode FREE;
<free_dcls()>

FS_FreeFile : [dD] [cC] [lL] '-' [fF] ;//-> pushMode(F_SPEC_FREE);
H_SPEC : [cC] [tT] [lL] '-' [oO][pP][tT];
FREE_CONT: '...' [ ]* NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n][ ]+ {setText("...");} -> type(CONTINUATION);
FREE_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN); // skip comments after 80
EXEC_SQL: [Ee][Xx][Ee][Cc][ ]+[Ss][Qq][Ll]-> pushMode(SQL_MODE) ;

// Built In functions
<bifs()>

// Symbolic Constants
<symbolicConstants()>

// Reserved Words
<reservedWords()>

<operators()>

<free()>


mode NumberContinuation;
NumberContinuation_CONTINUATION: ([ ]* NEWLINE)   
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [dD] ~[*] '                            ' [ ]* -> skip;
NumberPart: NUMBER -> popMode;
NumberContinuation_ANY: -> popMode,skip;

mode DurationCodes; //Referenced (not used)
<durationCodes()>

mode FixedOpCodes; //Referenced (not used)
<fixedOpCodes()>

mode FREE_ENDED;
FE_BLANKS : [ ]+ -> skip;
FE_COMMENTS: '//' -> popMode,pushMode(FIXED_CommentMode_HIDDEN),channel(HIDDEN) ;
FE_NEWLINE : NEWLINE -> popMode,skip;

mode InStringMode;
<inStringMode()>

mode InDoubleStringMode;
	//  Any char except +,- or ", or a + or - followed by more than just whitespace 
DblStringContent: ~["\r\n]+ -> type(StringContent); 
DblStringLiteralEnd: ["] -> popMode,type(StringLiteralEnd);


//This mode is basically a language independent flag.
mode EatCommentLinesPlus;
EatCommentLinesPlus_Any: -> popMode,skip;

// Inside continuations, ignore comment and blank lines.
mode EatCommentLines;
<eatCommentLinesMode()>

mode InFactorStringMode;
<inFactorStringMode()>

// -----------------  ---------------------
mode FIXED_CommentMode;
BLANK_COMMENTS_TEXT : [ ]+ -> skip;
COMMENTS_TEXT : ~[\r\n]+ -> channel(HIDDEN);
COMMENTS_EOL : NEWLINE -> popMode,skip;

mode FIXED_CommentMode_HIDDEN;
COMMENTS_TEXT_SKIP : [ ]+ -> skip;
COMMENTS_TEXT_HIDDEN :  ~[\r\n]* -> channel(HIDDEN);
COMMENTS_EOL_HIDDEN : NEWLINE ->  channel(HIDDEN),popMode;

mode SQL_MODE;
<sqlMode()>

// ----------------- Everything FIXED_ProcedureSpec of a tag ---------------------
mode FIXED_ProcedureSpec;
PS_NAME : {getCharPositionInLine()==6}? NAME5 NAME5 NAME5 {setText(getText().trim());};
PS_CONTINUATION_NAME : [ ]* ~[\r\n ]+ PS_CONTINUATION {setText(getText().substring(0,getText().length()-3));} -> pushMode(CONTINUATION_ELIPSIS) ;
PS_CONTINUATION : '...' ;

PS_RESERVED1: {getCharPositionInLine()==21}? '  ' -> skip;
PS_BEGIN: {getCharPositionInLine()==23}? [bB];
PS_END: {getCharPositionInLine()==23}? [eE];
PS_RESERVED2: {getCharPositionInLine()==24}? '                   ' -> skip;
PS_KEYWORDS : {getCharPositionInLine()==43}? ~[\r\n]+ -> popMode;

// ----------------- Everything FIXED_DefSpec of a tag ---------------------
mode FIXED_DefSpec;
<fixed_defSpec()>
EOL : NEWLINE ->  popMode;

mode CONTINUATION_ELIPSIS;
CE_WS: WS ->skip;
CE_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n ]~[\r\n]* -> channel(HIDDEN); // skip comments after 80
CE_LEAD_WS5 :  LEAD_WS5 ->skip;
CE_LEAD_WS5_Comments : LEAD_WS5_Comments -> channel(HIDDEN);
CE_D_SPEC_FIXED : {_modeStack.peek()==FIXED_DefSpec && getCharPositionInLine()==5}? [dD] -> skip,popMode ;
CE_P_SPEC_FIXED : {_modeStack.peek()==FIXED_ProcedureSpec && getCharPositionInLine()==5}? [pP] -> skip,popMode ;
CE_NEWLINE: NEWLINE ->skip;

// ----------------- Everything FIXED_FileSpec of a tag ---------------------
mode FIXED_FileSpec;
<fixed_fileSpec()>
FS_EOL : NEWLINE -> type(EOL),popMode;

mode FIXED_OutputSpec;
<fixed_outputSpec()>

mode FIXED_CalcSpec;
<fixed_calcSpec()>

mode OnOffIndicatorMode;
BlankFlag: [ ] ->popMode,pushMode(IndicatorMode);
NoFlag: [nN] ->popMode,pushMode(IndicatorMode);

mode IndicatorMode;
BlankIndicator: [ ][ ] ->popMode;
GeneralIndicator: ([0][1-9] | [1-9][0-9]) ->popMode;
FunctionKeyIndicator: [Kk][A-NP-Ya-np-y] ->popMode;
ControlLevelIndicator: [lL][1-9] ->popMode;
ControlLevel0Indicator: [lL][0] ->popMode;
LastRecordIndicator: [lL][rR] ->popMode;
MatchingRecordIndicator: [mM][rR] ->popMode;
HaltIndicator: [hH][1-9] ->popMode;
ReturnIndicator: [rR][tT] ->popMode;
ExternalIndicator: [uU][1-8] ->popMode;
OverflowIndicator: [oO][A-GVa-gv] ->popMode;
SubroutineIndicator: [sS][rR] ->popMode;
AndIndicator: [aA][nN] ->popMode;
OrIndicator: [oO][rR] ->popMode;
DoubleSplatIndicator: '**';
FirstPageIndicator: [1][pP];
OtherTextIndicator: ~[\r\n]~[\r\n];


mode FIXED_CalcSpec_SQL;
CSQL_EMPTY_TEXT: [ ]+ -> skip;
CSQL_TEXT: ~[\r\n]+;
//CSQL_CONT : ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]  [cC] '+' -> skip;
CSQL_LEADWS : {getCharPositionInLine()==0}? ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> skip,popMode;
CSQL_END : NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 
	 [cC] '/' [Ee][nN][dD][-][Ee][Xx][Ee][Cc] WS NEWLINE-> popMode ;
CSQL_CONT : NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [cC] '+' -> skip; 
CSQL_EOL : NEWLINE -> popMode; 

mode FIXED_CalcSpec_X2;
C2_FACTOR2_CONT: {getCharPositionInLine()==35}? 
		~[\r\n]+ '+' [ ]+ NEWLINE;  //TODO  the continuation should not include the plus and the \r\n
//C2_FACTOR2_CONT2: {getCharPositionInLine()==35}? 
//		~[\r\n]+ NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [cC] '                             ' ;  
C2_FACTOR2: {getCharPositionInLine()==35}? 
		~[\r\n]+ ->popMode;
C2_OTHER: {getCharPositionInLine()\<35}? ~('\r' | '\n') ->skip;

mode FIXED_InputSpec;
<fixed_inputSpec()>

mode HeaderSpecMode;
HS_OPEN_PAREN: OPEN_PAREN -> type(OPEN_PAREN);
HS_CLOSE_PAREN: CLOSE_PAREN -> type(CLOSE_PAREN);
HS_StringLiteralStart: ['] -> type(StringLiteralStart),pushMode(InStringMode) ;
HS_COLON: ':' -> type(COLON);
HS_ID: [#@%$*a-zA-Z] [&#@\-$*a-zA-Z0-9_]* -> type(ID);
HS_WhiteSpace : [ \t]+ -> skip  ; // skip spaces, tabs, newlines
HS_CONTINUATION: NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [hH] ~[*] -> skip;
HS_EOL : NEWLINE -> type(EOL),popMode;

/*
mode F_SPEC_FREE;
FREE_F_OPEN_PAREN: OPEN_PAREN;
FREE_F_CLOSE_PAREN: CLOSE_PAREN;
FREE_F_STRING_START: ['] -> pushMode(InStringMode) ;
FREE_F_COLON: ':';
FREE_F_ID: ID;
FREE_F_NUMBER: NUMBER;
FREE_F_CONT: '...' [ ]* NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n][ ]+ {setText("...");};
FREE_F_WS : [ \t]+ -> skip  ; // skip spaces, tabs, newlines
FREE_F_SEMICOLON: ';' -> popMode,popMode;
FREE_F_EOL : NEWLINE -> skip;
/*
mode D_SPEC_FREE;
FREE_D_OPEN_PAREN: OPEN_PAREN;
FREE_D_CLOSE_PAREN: CLOSE_PAREN;
FREE_D_STRING_START: ['] -> pushMode(InStringMode) ;
FREE_D_COLON: ':';
FREE_D_ID: ID;
FREE_D_WS : [ \t]+ -> skip  ; // skip spaces, tabs, newlines
FREE_D_SEMICOLON: ';' -> popMode,popMode;
FREE_D_EOL : NEWLINE -> skip;
*/

/*	26,35,"Operation and Extender"
	36,49,"Factor 2"
	50,63,"Result Field"
	64,68,"Field Length"
	69,70,"Decimal Positions"
	71,76,"Resulting Indicators"
	7,8,"Control Level"
	7,8,"Control Level"
	9,11,"Indicators"
	9,11,"Indicators"
	12,25,"Factor 1"
	26,35,"Operation and Extender"
	36,80,"Extended Factor 2"
	8,80,"Free-form Operations"
 */
fragment WORD5 : ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n];
fragment NAME5 : NAMECHAR NAMECHAR NAMECHAR NAMECHAR NAMECHAR;
// valid characters in symbolic names.
fragment NAMECHAR : [A-Za-z0-9$#@_ ];
// names cannot start with _ or numbers
fragment INITNAMECHAR : [A-Za-z$#@];
fragment WORD_WCOLON : ~[\r\n];//[a-zA-Z0-9 :*];
fragment WORD5_WCOLON : WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
//fragment C_OP_EXT: [ aAhHnNpPdDtTzZmMrReE];
//fragment IND_FRAG: ('  ' | [0-9lLhH][0-9] | [kK][A-NP-Ya-np0y] | [lLmM][rR] | [rR][tT] | [uU][1-8] | [oO][a-gvA-GV] | [0-9][pP]);


>>
