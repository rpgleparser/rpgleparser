initialRules() ::= <<
	//End Source.  Not more parsing after this.
END_SOURCE : {getCharPositionInLine()==0}? '**'~[\r\n]~[\r\n]~[\r\n]~[\r\n*]~[\r\n]* EOL  -> pushMode(EndOfSourceMode) ;
    //Ignore or skip leading 5 white space.
LEAD_WS5 :  {getCharPositionInLine()==0}? '     ' -> skip;
LEAD_WS5_Comments :  {getCharPositionInLine()==0}?~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> channel(HIDDEN);
	//5 position blank means FREE, unless..
FREE_SPEC : {getCharPositionInLine()==5}? [  ] -> pushMode(OpCode),skip;
    // 6th position asterisk is a comment
COMMENT_SPEC_FIXED : {getCharPositionInLine()==5}? .'*' -> pushMode(FIXED_CommentMode),channel(HIDDEN) ;
    // X specs 
DS_FIXED : {getCharPositionInLine()==5}? [dD] -> pushMode(FIXED_DefSpec) ; 
FS_FIXED : {getCharPositionInLine()==5}? [fF] -> pushMode(FIXED_FileSpec) ;
OS_FIXED : {getCharPositionInLine()==5}? [oO] -> pushMode(FIXED_OutputSpec) ;
CS_FIXED : {getCharPositionInLine()==5}? [cC] -> pushMode(FIXED_CalcSpec),pushMode(OnOffIndicatorMode),pushMode(IndicatorMode) ;
CS_ExecSQL : {getCharPositionInLine()==5}? [cC] '/' EXEC_SQL -> pushMode(FIXED_CalcSpec_SQL);
IS_FIXED : {getCharPositionInLine()==5}? [iI] -> pushMode(FIXED_InputSpec) ;
PS_FIXED : {getCharPositionInLine()==5}? [pP] -> pushMode(FIXED_ProcedureSpec) ;
HS_FIXED : {getCharPositionInLine()==5}? [hH] -> pushMode(HeaderSpecMode) ;

BLANK_LINE : {getCharPositionInLine()==5}? [ ]+ NEWLINE -> skip;
BLANK_SPEC_LINE : {getCharPositionInLine()==5}? .[ ]* NEWLINE -> skip;
COMMENTS : {getCharPositionInLine()>=5}? [ ][ ]*? '//' -> pushMode(FIXED_CommentMode),channel(HIDDEN) ;
EMPTY_LINE : {getCharPositionInLine()>=5}?
	'                                                                           ' -> pushMode(FIXED_CommentMode),channel(HIDDEN) ;
	//Directives are not necessarily blank at pos 5
DIRECTIVE : {getCharPositionInLine()>=5}? .[ ]*? '/' -> pushMode(DirectiveMode) ;

OPEN_PAREN : '(';
CLOSE_PAREN : ')';
NUMBER : ([0-9]+([.][0-9]*)?) | [.][0-9]+ ;
SEMI : ';';
COLON : ':';
ID : {getCharPositionInLine()>6}?('*'? '*' [a-zA-Z])?[#@%$a-zA-Z] [#@$a-zA-Z0-9_]* ;             // match lower-case identifiers
NEWLINE : ('\r' '\n'? | '\n') -> skip;
WS : {getCharPositionInLine()>5}? [ \t]+ -> skip ; // skip spaces, tabs, NEWLINEs
>>