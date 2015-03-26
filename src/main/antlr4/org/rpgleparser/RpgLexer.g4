/**
 * Define a grammar called Hello
 */
lexer grammar RpgLexer;

// Parser Rules
	//End Source.  Not more parsing after this.
END_SOURCE : {getCharPositionInLine()==0}? '**'~[\r\n]~[\r\n]~[\r\n]~[\r\n*]~[\r\n]* EOL  -> pushMode(EndOfSourceMode) ;
    //Ignore or skip leading 5 white space.
LEAD_WS5 :  {getCharPositionInLine()==0}? '     ' -> skip;
LEAD_WS5_Comments :  {getCharPositionInLine()==0}?~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> channel(HIDDEN);
	//5 position blank means FREE, unless..
FREE_SPEC : {getCharPositionInLine()==5}? [  ] -> pushMode(FREE),skip;
    // 6th position asterisk is a comment
COMMENT_SPEC_FIXED : {getCharPositionInLine()==5}? .'*' -> pushMode(FIXED_CommentMode) ;
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
COMMENTS : {getCharPositionInLine()>=5}? [ ][ ]*? '//' -> pushMode(FIXED_CommentMode),skip ;
EMPTY_LINE : {getCharPositionInLine()>=5}?
	'                                                                           ' -> pushMode(FIXED_CommentMode),skip ;
	//Directives are not necessarily blank at pos 5
DIRECTIVE : {getCharPositionInLine()>=5}? .[ ]*? '/' -> pushMode(DirectiveMode) ;

OPEN_PAREN : '(';
CLOSE_PAREN : ')';
NUMBER : ([0-9]+([.][0-9]*)?) | [.][0-9]+ ;
SEMI : ';';
COLON : ':';
ID : {getCharPositionInLine()>6}?('*'? '*' [a-zA-Z])?[#@%$a-zA-Z] [#@\-$a-zA-Z0-9_]* ;             // match lower-case identifiers
NEWLINE : ('\r' '\n'? | '\n') -> skip;
WS : {getCharPositionInLine()>5}? [ \t]+ -> skip ; // skip spaces, tabs, NEWLINEs

mode DirectiveMode;
DIR_FREE: {getCharPositionInLine()==7}? ([fF][rR][eE][eE] | [eE][nN][dD] '-' [fF][rR][eE][eE]) -> pushMode(SKIP_REMAINING_WS);
DIR_TITLE:{getCharPositionInLine()==7}? ([tT][iI][tT][lL][eE]);
DIR_EJECT: {getCharPositionInLine()==7}? [eE][jJ][eE][cC][tT] -> pushMode(SKIP_REMAINING_WS);
DIR_SPACE: {getCharPositionInLine()==7}? [sS][pP][aA][cC][eE];
DIR_SET: {getCharPositionInLine()==7}? [sS][eE][tT];
DIR_RESTORE: {getCharPositionInLine()==7}? [rR][eE][sS][tT][oO][rR][eE];
DIR_COPY: {getCharPositionInLine()==7}? [cC][oO][pP][yY];
DIR_INCLUDE: {getCharPositionInLine()==7}? [iI][nN][cC][lL][uU][dD][eE];
DIR_EOF: {getCharPositionInLine()==7}? [eE][oO][fF];
DIR_DEFINE: {getCharPositionInLine()==7}? ([dD][eE][fF][iI][nN][eE]);
DIR_UNDEFINE: {getCharPositionInLine()==7}? ([uU][nN][dD][eE][fF][iI][nN][eE]);
DIR_IF: {getCharPositionInLine()==7}? ([iI][fF]);
DIR_ELSE: {getCharPositionInLine()==7}? ([eE][lL][sS][eE]);
DIR_ELSEIF: {getCharPositionInLine()==7}? ([eE][lL][sS][eE][iI][fF]);
DIR_ENDIF: {getCharPositionInLine()==7}? ([eE][nN][dD][iI][fF]);
DIR_Number: NUMBER -> type(NUMBER);
DIR_WhiteSpace: [ ] -> type(WS);
DIR_OtherText : ~[\r\n \t]+ ;
DIR_EOL : [ ]* NEWLINE -> type(EOL),popMode;

mode SKIP_REMAINING_WS;
DIR_FREE_OTHER_TEXT: ~[\r\n]+ -> popMode,skip;

mode EndOfSourceMode;
EOS_Text : ~[\r\n]+ ;
EOS_EOL : NEWLINE -> type(EOL); 

// -----------------  ---------------------
mode FREE;
DS_Standalone : [dD] [cC] [lL] '-' [sS] ;//-> pushMode(F_SPEC_FREE);
DS_DataStructureStart : [dD] [cC] [lL] '-' [dD][sS] ;//-> pushMode(F_SPEC_FREE);
DS_DataStructureEnd : [eE] [nN] [dD] '-' [dD][sS] ;//-> pushMode(F_SPEC_FREE);
DS_PrototypeStart : [dD] [cC] [lL] '-' [pP][rR] ;//-> pushMode(F_SPEC_FREE);
DS_PrototypeEnd : [eE] [nN] [dD] '-' [pP][rR] ;//-> pushMode(F_SPEC_FREE);
DS_ProcedureStart : [dD] [cC] [lL] '-' [pP][iI] ;//-> pushMode(F_SPEC_FREE);
DS_ProcedureEnd : [eE] [nN] [dD] '-' [pP][iI] ;//-> pushMode(F_SPEC_FREE);
DS_Constant : [dD] [cC] [lL] '-' [cC] ;//-> pushMode(F_SPEC_FREE);

FS_FreeFile : [dD] [cC] [lL] '-' [fF] ;//-> pushMode(F_SPEC_FREE);
H_SPEC : [cC] [tT] [lL] '-' [oO][pP][tT];
FREE_CONT: '...' [ ]* NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n][ ]+ {setText("...");};
FREE_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN); // skip comments after 80
EXEC_SQL: [Ee][Xx][Ee][Cc][ ]+[Ss][Qq][Ll]-> pushMode(SQL_MODE) ;
OP_ACQ: [Aa][Cc][Qq];
OP_BEGSR: [Bb][Ee][Gg][Ss][Rr];
OP_CALLP: [Cc][Aa][Ll][Ll][Pp];
OP_CHAIN: [Cc][Hh][Aa][Ii][Nn];
OP_CLEAR: [Cc][Ll][Ee][Aa][Rr];
OP_CLOSE: [Cc][Ll][Oo][Ss][Ee];
OP_COMMIT: [Cc][Oo][Mm][Mm][Ii][Tt];
OP_DEALLOC: [Dd][Ee][Aa][Ll][Ll][Oo][Cc];
OP_DELETE: [Dd][Ee][Ll][Ee][Tt][Ee];
OP_DOU: [Dd][Oo][Uu];
OP_DOW: [Dd][Oo][Ww];
OP_DSPLY: [Dd][Ss][Pp][Ll][Yy];
OP_DUMP: [Dd][Uu][Mm][Pp];
OP_ELSE: [Ee][Ll][Ss][Ee];
OP_ELSEIF: [Ee][Ll][Ss][Ee][Ii][Ff];
OP_ENDDO: [Ee][Nn][Dd][Dd][Oo];
OP_ENDFOR: [Ee][Nn][Dd][Ff][Oo][Rr];
OP_ENDIF: [Ee][Nn][Dd][Ii][Ff];
OP_ENDMON: [Ee][Nn][Dd][Mm][Oo][Nn];
OP_ENDSL: [Ee][Nn][Dd][Ss][Ll];
OP_ENDSR: [Ee][Nn][Dd][Ss][Rr];
OP_EVAL: [Ee][Vv][Aa][Ll];
OP_EVALR: [Ee][Vv][Aa][Ll][Rr];
OP_EVAL_CORR: [Ee][Vv][Aa][Ll][-][Cc][Oo][Rr][Rr];
OP_EXCEPT: [Ee][Xx][Cc][Ee][Pp][Tt];
OP_EXFMT: [Ee][Xx][Ff][Mm][Tt];
OP_EXSR: [Ee][Xx][Ss][Rr];
OP_FEOD: [Ff][Ee][Oo][Dd];
OP_FOR: [Ff][Oo][Rr];
OP_FORCE: [Ff][Oo][Rr][Cc][Ee];
OP_IF: [Ii][Ff];
OP_IN: [Ii][Nn];
OP_ITER: [Ii][Tt][Ee][Rr];
OP_LEAVE: [Ll][Ee][Aa][Vv][Ee];
OP_LEAVESR: [Ll][Ee][Aa][Vv][Ee][Ss][Rr];
OP_MONITOR: [Mm][Oo][Nn][Ii][Tt][Oo][Rr];
OP_NEXT: [Nn][Ee][Xx][Tt];
OP_ON_ERROR: [Oo][Nn][-][Ee][Rr][Rr][Oo][Rr];
OP_OPEN: [Oo][Pp][Ee][Nn];
OP_OTHER: [Oo][Tt][Hh][Ee][Rr];
OP_OUT: [Oo][Uu][Tt];
OP_POST: [Pp][Oo][Ss][Tt];
OP_READ: [Rr][Ee][Aa][Dd];
OP_READC: [Rr][Ee][Aa][Dd][Cc];
OP_READE: [Rr][Ee][Aa][Dd][Ee];
OP_READP: [Rr][Ee][Aa][Dd][Pp];
OP_READPE: [Rr][Ee][Aa][Dd][Pp][Ee];
OP_REL: [Rr][Ee][Ll];
OP_RESET: [Rr][Ee][Ss][Ee][Tt];
OP_RETURN: [Rr][Ee][Tt][Uu][Rr][Nn];
OP_ROLBK: [Rr][Oo][Ll][Bb][Kk];
OP_SELECT: [Ss][Ee][Ll][Ee][Cc][Tt];
OP_SETGT: [Ss][Ee][Tt][Gg][Tt];
OP_SETLL: [Ss][Ee][Tt][Ll][Ll];
OP_SORTA: [Ss][Oo][Rr][Tt][Aa];
OP_TEST: [Tt][Ee][Ss][Tt];
OP_UNLOCK: [Uu][Nn][Ll][Oo][Cc][Kk];
OP_UPDATE: [Uu][Pp][Dd][Aa][Tt][Ee];
OP_WHEN: [Ww][Hh][Ee][Nn];
OP_WRITE: [Ww][Rr][Ii][Tt][Ee];
OP_XML_INTO: [Xx][Mm][Ll][-][Ii][Nn][Tt][Oo];
OP_XML_SAX: [Xx][Mm][Ll][-][Ss][Aa][Xx];

// Symbolic Constants
SPLAT_ALL: '*'[aA][lL][lL];
SPLAT_NONE: '*'[nN][oO][nN][eE];
SPLAT_ILERPG: '*'[iI][lL][eE][rR][pP][gG];
SPLAT_CRTBNDRPG: '*'[cC][rR][tT][bB][nN][dD][rR][pP][gG];
SPLAT_CRTRPGMOD: '*'[cC][rR][tT][rR][pP][gG][mM][oO][dD];
SPLAT_VRM: '*'[vV][0-9][rR][0-9][mM][0-9];
SPLAT_ALLG: '*'[aA][lL][lL][gG];
SPLAT_ALLU: '*'[aA][lL][lL][uU];
SPLAT_ALLX: '*'[aA][lL][lL][xX];
SPLAT_BLANKS: ('*'[bB][lL][aA][nN][kK][sS] | '*'[bB][lL][aA][nN][kK]);
SPLAT_CANCL: '*'[cC][aA][nN][cC][lL];
SPLAT_CYMD: '*'[cC][yY][mM][dD];
SPLAT_CMDY: '*'[cC][mM][dD][yY];
SPLAT_CDMY: '*'[cC][dD][mM][yY];
SPLAT_MDY: '*'[mM][dD][yY];
SPLAT_DMY: '*'[dD][mM][yY];
SPLAT_YMD: '*'[yY][mM][dD];
SPLAT_JUL: '*'[jJ][uU][lL];
SPLAT_ISO: '*'[iI][sS][oO];
SPLAT_USA: '*'[uU][sS][aA];
SPLAT_EUR: '*'[eE][uU][rR];
SPLAT_JIS: '*'[jJ][iI][sS];
SPLAT_DATE: '*'[dD][aA][tT][eE];
SPLAT_DAY:  '*'[dD][aA][yY];
SPlAT_DETC: '*'[dD][eE][tT][cC];
SPLAT_DETL: '*'[dD][eE][tT][lL];
SPLAT_DTAARA: '*'[dD][tT][aA][aA][rR][aA];
SPLAT_END:  '*'[eE][nN][dD];
SPLAT_ENTRY: '*'[eE][nN][tT][rR][yY];
SPLAT_EQUATE: '*'[eE][qQ][uU][aA][tT][eE];
SPLAT_EXTDFT: '*'[eE][xX][tT][dD][fF][tT];
SPLAT_EXT: '*'[eE][xX][tT];
SPLAT_FILE: '*'[fF][iI][lL][eE];
SPLAT_GETIN: '*'[gG][eE][tT][iI][nN];
SPLAT_HIVAL: '*'[hH][iI][vV][aA][lL];
SPLAT_INIT: '*'[iI][nN][iI][tT];
SPLAT_INDICATOR: '*'[iI][nN][0-9][0-9];
SPLAT_INZSR: '*'[iI][nN][zZ][sS][rR];
SPLAT_IN: '*'[iI][nN];
SPLAT_JOBRUN: '*'[jJ][oO][bB][rR][uU][nN];
SPLAT_JOB: '*'[jJ][oO][bB];
SPLAT_LDA: '*'[lL][dD][aA];
SPLAT_LIKE: '*'[lL][iI][kK][eE];
SPLAT_LONGJUL: '*'[lL][oO][nN][gG][jJ][uU][lL];
SPLAT_LOVAL: '*'[lL][oO][vV][aA][lL];
SPLAT_MONTH: '*'[mM][oO][nN][tT][hH];
SPLAT_NOIND: '*'[nN][oO][iI][nN][dD];
SPLAT_NOKEY: '*'[nN][oO][kK][eE][yY];
SPLAT_NULL: '*'[nN][uU][lL][lL];
SPLAT_OFL: '*'[oO][fF][lL];
SPLAT_ON: '*'[oO][nN];
SPLAT_OFF: '*'[oO][fF][fF];
SPLAT_PDA: '*'[pP][dD][aA];
SPLAT_PLACE: '*'[pP][lL][aA][cC][eE];
SPLAT_PSSR: '*'[pP][sS][sS][rR];
SPLAT_ROUTINE: '*'[rR][oO][uU][tT][iI][nN][eE];
SPLAT_START: '*'[sS][tT][aA][rR][tT];
SPLAT_SYS: '*'[sS][yY][sS];
SPLAT_TERM: '*'[tT][eE][rR][mM];
SPLAT_TOTC: '*'[tT][oO][tT][cC];
SPLAT_TOTL: '*'[tT][oO][tT][lL];
SPLAT_USER: '*'[uU][sS][eE][rR];
SPLAT_VAR: '*'[vV][aA][rR];
SPLAT_YEAR: '*'[yY][eE][aA][rR];
SPLAT_ZEROS: ('*'[zZ][eE][rR][oO][sS] | '*'[zZ][eE][rR][oO]);
SPLAT_HMS: '*'[hH][mM][sS];
SPLAT_INLR: '*'[iI][nN][lL][rR];
SPLAT_INOF: '*'[iI][nN][oO][fF];

// Reserved Words
UDATE : [uU] [dD] [aA] [tT] [eE] ;
DATE : '*' [dD] [aA] [tT] [eE] ;
UMONTH : [uU] [mM] [oO] [nN] [tT] [hH] ;
MONTH : '*' [mM] [oO] [nN] [tT] [hH] ;
UYEAR : [uU] [yY] [eE] [aA] [rR] ;
YEAR : '*' [yY] [eE] [aA] [rR] ;
UDAY : [uU] [dD] [aA] [yY] ;
DAY : '*' [dD] [aA] [yY] ;
PAGE : [pP] [aA] [gG] [eE] [1-7]? ;

// Boolean operators
AND : [aA] [nN] [dD] ;
OR : [oO] [rR] ;
NOT : [nN] [oO] [tT] ;

// Arithmetical Operators
PLUS : '+' ;
MINUS : '-' ;
EXP : '**' ;
//MULT : '*' ;
MULT_NOSPACE: {_input.LA(2) != 32}? '*';
MULT: {_input.LA(2) == 32}? '*' ;
DIV : '/' ;

// Assignment Operators
CPLUS : '+=' ;
CMINUS : '-=' ;
CMULT : '*=' ;
CDIV : '/=' ;

// Comparison Operators
GT : '>' ;
LT : '<' ;
GE : '>=' ;
LE : '<=' ;
NE : '<>' ;

//--------------
OP_E: '(' [aAdDeEhHmMnNpPrRtTzZ][aAdDeEhHmMnNpPrRtTzZ]? ')';
FREE_OPEN_PAREN: OPEN_PAREN -> type(OPEN_PAREN);
FREE_CLOSE_PAREN: CLOSE_PAREN -> type(CLOSE_PAREN);
FREE_DOT: '.';
FREE_NUMBER: NUMBER -> type(NUMBER);
EQUAL: '=';

//FREE_OPERATION: '+' | '/' | '+=' | '-=';
//FREE_COMPARE: '<' | '>'  | '<'[>=] | '>=';
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
FREE_CONTINUATION : {_modeStack.peek()!=FIXED_CalcSpec}? '...' WS* NEWLINE -> type(CONTINUATION);
C_FREE_CONTINUATION_DOTS : {_modeStack.peek()==FIXED_CalcSpec}? '...' WS* NEWLINE 
	(~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 'C' ~[*] '                            ') {setText("...");} -> type(CONTINUATION);
C_FREE_CONTINUATION: {_modeStack.peek()==FIXED_CalcSpec}? NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 'C' ~[*] '                            ' -> skip;
FREE_LEAD_WS5 :  {getCharPositionInLine()==0}? '     ' -> skip;
FREE_LEAD_WS5_Comments :  {getCharPositionInLine()==0}?~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> channel(HIDDEN);
FREE_FREE_SPEC : {getCharPositionInLine()==5}? [  ] -> skip;
	
C_FREE_NEWLINE: {_modeStack.peek()==FIXED_CalcSpec}? NEWLINE -> popMode,popMode;
FREE_NEWLINE: {_modeStack.peek()!=FIXED_CalcSpec}? NEWLINE -> skip;
FREE_SEMI: SEMI -> popMode, pushMode(FREE_ENDED);  //Captures // immediately following the semi colon

mode DurationCodes; //Referenced (not used)
SPLAT_D: '*'[dD];
SPLAT_H: '*'[hH];
SPLAT_HOURS: '*'[hH][oO][uU][rR][sS];
SPLAT_DAYS:  SPLAT_DAY[sS];
SPLAT_M: '*'[mM];
SPLAT_MINUTES: '*'[mM][iI][nN][uU][tT][eE][sS];
SPLAT_MONTHS: SPLAT_MONTH[sS];
SPLAT_MN: '*'[mM][nN]; //Minutes
SPLAT_MS: '*'[mM][sS]; //Minutes
SPLAT_MSECONDS: '*'[mM][sS][eE][cC][oO][nN][dD][sS];
SPLAT_S: '*'[sS];
SPLAT_SECONDS: '*'[sS][eE][cC][oO][nN][dD][sS];
SPLAT_Y: '*'[yY];
SPLAT_YEARS: SPLAT_YEAR[sS];



mode FREE_ENDED;
FE_BLANKS : [ ]+ -> skip;
FE_COMMENTS: '//' -> popMode,pushMode(FIXED_CommentMode_HIDDEN),channel(HIDDEN) ;
FE_NEWLINE : NEWLINE -> popMode,skip;

mode InStringMode;
	//  Any char except +,- or ', or a + or - followed by more than just whitespace 
StringContent: (~['\r\n+-] | [+-] ' '* ~['\r\n ])+;// space or not 
StringEscapedQuote: [']['] {setText("'");};
StringLiteralEnd: ['] -> popMode;
C_FREE_STRING_CONTINUATION: {_modeStack.peek()==FIXED_CalcSpec}? '+' [ ]* NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 'C' ~[*] '                            ' [ ]* -> type(CONTINUATION),skip;
C_FREE_STRING_CONTINUATION_MINUS: {_modeStack.peek()==FIXED_CalcSpec}? '-' [ ]* NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 'C' ~[*] '                            ' -> type(CONTINUATION),skip;
FREE_STRING_CONTINUATION: {_modeStack.peek()!=FIXED_CalcSpec}? '+' [ ]* NEWLINE '       ' [ ]* -> skip;
FREE_STRING_CONTINUATION_MINUS: {_modeStack.peek()!=FIXED_CalcSpec}? '-' [ ]* NEWLINE '       ' -> skip;
PlusOrMinus: [+-];


// -----------------  ---------------------
mode FIXED_CommentMode;
COMMENTS_TEXT : ~[\r\n]+ -> channel(HIDDEN);
COMMENTS_EOL : NEWLINE -> popMode,skip;

mode FIXED_CommentMode_HIDDEN;
COMMENTS_TEXT_SKIP : [ ]+ -> skip;
COMMENTS_TEXT_HIDDEN : ~[ ] ~[\r\n]+ -> channel(HIDDEN);
COMMENTS_EOL_HIDDEN : NEWLINE ->  channel(HIDDEN),popMode;

mode SQL_MODE;
SQL_WS: [ \t\r\n]+ -> skip;
//SINGLE_QTE: ['];
//DOUBLE_QTE: ["];
SEMI_COLON: SEMI -> type(SEMI),popMode, popMode;
WORDS: ~[ ;\r\n] (~[;\r\n]+ ~[ ;\r\n])?; 

// ----------------- Everything FIXED_ProcedureSpec of a tag ---------------------
mode FIXED_ProcedureSpec;
PS_NAME : {getCharPositionInLine()==6}? WORD5 WORD5 WORD5 {setText(getText().trim());};
PS_CONTINUATION_NAME : [ ]* ~[\r\n ]+ PS_CONTINUATION {setText(getText().substring(0,getText().length()-3));} -> pushMode(CONTINUATION_ELIPSIS) ;
PS_CONTINUATION : '...' ;

PS_RESERVED1: {getCharPositionInLine()==21}? '  ' -> skip;
PS_BEGIN: {getCharPositionInLine()==23}? [bB];
PS_END: {getCharPositionInLine()==23}? [eE];
PS_RESERVED2: {getCharPositionInLine()==24}? '                   ' -> skip;
PS_KEYWORDS : {getCharPositionInLine()==43}? ~[\r\n]+ -> popMode;

// ----------------- Everything FIXED_DefSpec of a tag ---------------------
mode FIXED_DefSpec;
CONTINUATION_NAME : [ ]* ~[\r\n ]+ CONTINUATION {setText(getText().substring(0,getText().length()-3));} -> pushMode(CONTINUATION_ELIPSIS) ;
CONTINUATION : '...' ;
NAME : {getCharPositionInLine()==6}? WORD5 WORD5 WORD5 {setText(getText().trim());};
EXTERNAL_DESCRIPTION: {getCharPositionInLine()==21}? [eE ];
DATA_STRUCTURE_TYPE: {getCharPositionInLine()==22}? [sSuU ];
DEF_TYPE: {getCharPositionInLine()==23}? [a-zA-Z0-9 ][a-zA-Z0-9 ];
FROM_POSITION: {getCharPositionInLine()==25}? WORD5 [a-zA-Z0-9 ][a-zA-Z0-9 ];
TO_POSITION: {getCharPositionInLine()==32}? WORD5[a-zA-Z0-9 ][a-zA-Z0-9 ];
DATA_TYPE: {getCharPositionInLine()==39}? [a-zA-Z\* ];
DECIMAL_POSITIONS: {getCharPositionInLine()==40}? [0-9 ][0-9 ];
RESERVED : {getCharPositionInLine()==42}? ' ' ;
KEYWORDS : {getCharPositionInLine()==43}? ~[\r\n]+ ;
D_WS : {getCharPositionInLine()>=80}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
D_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN); // skip comments after 80
EOL : NEWLINE ->  popMode;

mode CONTINUATION_ELIPSIS;
CE_WS: WS ->skip;
CE_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n ]~[\r\n]* -> channel(HIDDEN); // skip comments after 80
CE_LEAD_WS5 :  LEAD_WS5 ->skip;
CE_D_SPEC_FIXED : {_modeStack.peek()==FIXED_DefSpec && getCharPositionInLine()==5}? [dD] -> skip,popMode ;
CE_P_SPEC_FIXED : {_modeStack.peek()==FIXED_ProcedureSpec && getCharPositionInLine()==5}? [pP] -> skip,popMode ;
CE_NEWLINE: NEWLINE ->skip;

// ----------------- Everything FIXED_FileSpec of a tag ---------------------
mode FIXED_FileSpec;
FS_RecordName : {getCharPositionInLine()==6}? WORD5 WORD5;
FS_Type: {getCharPositionInLine()==16}? [a-zA-Z ];
FS_Designation: {getCharPositionInLine()==17}? [a-zA-Z ];
FS_EndOfFile: {getCharPositionInLine()==18}? [eE ];
FS_Addution: {getCharPositionInLine()==19}? [aA ];
FS_Sequence: {getCharPositionInLine()==20}? [aAdD ];
FS_Format: {getCharPositionInLine()==21}? [eEfF ];
FS_RecordLength: {getCharPositionInLine()==22}? WORD5;
FS_Limits: {getCharPositionInLine()==27}? [lL ];
FS_LengthOfKey: {getCharPositionInLine()==28}? [0-9 ][0-9 ][0-9 ][0-9 ][0-9 ];
FS_RecordAddressType: {getCharPositionInLine()==33}? [a-zA-Z ];
FS_Organization: {getCharPositionInLine()==34}? [a-zA-Z ];
FS_Device: {getCharPositionInLine()==35}? WORD5 [a-zA-Z ][a-zA-Z ];
FS_Reserved: {getCharPositionInLine()==42}? [ ];
FS_Keywords : {getCharPositionInLine()==43}? ~[\r\n]+;
FS_WhiteSpace : {getCharPositionInLine()>=80}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
FS_EOL : NEWLINE -> popMode;

mode FIXED_OutputSpec;
OS_RecordName : {getCharPositionInLine()==6}? WORD5 WORD5;
OS_AndOr: {getCharPositionInLine()==6}? '         ' ([aA][nN][dD] | [oO][rR] ' ') '  ' -> 
	pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode);
OS_FieldReserved: {getCharPositionInLine()==6}? '              ' -> pushMode(FIXED_OutputSpec_PGMFIELD),
	pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode);
OS_Type: {getCharPositionInLine()==16}? [a-zA-Z ];
OS_AddDelete: {getCharPositionInLine()==17}? ([aA][dD][dD] | [dD][eE][lL])  -> pushMode(FIXED_OutputSpec_PGM1),
	pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode); 
OS_FetchOverflow: {getCharPositionInLine()==17}? (' ' | [fFrR]) '  ' -> pushMode(OnOffIndicatorMode),
	pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode);
//OS_OutputCondition: {getCharPositionInLine()==20 || getCharPositionInLine()==23
//		|| getCharPositionInLine()==26}? [N ] IND_FRAG ; 
OS_ExceptName: {getCharPositionInLine()==29}? WORD5 WORD5;
OS_Space3: {getCharPositionInLine()==39 || getCharPositionInLine()==42 
	|| getCharPositionInLine()==45 || getCharPositionInLine()==48}? [ 0-9][ 0-9][ 0-9];
OS_RemainingSpace: {getCharPositionInLine()==51}? '                             ';
OS_Comments : CS_Comments -> channel(HIDDEN); // skip comments after 80
OS_WS : {getCharPositionInLine()>=80}? [ \t]+ -> type(WS),skip  ; // skip spaces, tabs, newlines
OS_EOL : NEWLINE -> type(EOL),popMode,skip;

mode FIXED_OutputSpec_PGM1;
//O1_OutputCondition: {getCharPositionInLine()==20 || getCharPositionInLine()==23
//		|| getCharPositionInLine()==26}? [N ] IND_FRAG -> type(OS_OutputCondition);
O1_ExceptName: {getCharPositionInLine()==29}? WORD5 WORD5 -> type(OS_ExceptName);
O1_RemainingSpace: {getCharPositionInLine()==39}? '                                         ' -> type(OS_RemainingSpace),popMode;	

mode FIXED_OutputSpec_PGMFIELD;
//OF_OutputCondition: {getCharPositionInLine()==20 || getCharPositionInLine()==23
//		|| getCharPositionInLine()==26}? [Nn ] IND_FRAG -> type(OS_OutputCondition);
OS_FieldName: {getCharPositionInLine()==29}? WORD5 WORD5 ~[\r\n] ~[\r\n] ~[\r\n] ~[\r\n];
OS_EditNames: {getCharPositionInLine()==43}? [ 0-9A-Za-z];
OS_BlankAfter: {getCharPositionInLine()==44}? [ bB];
OS_Reserved1: {getCharPositionInLine()==45}? [ ] -> skip;
OS_EndPosition: {getCharPositionInLine()==46}? WORD5;
OS_DataFormat: {getCharPositionInLine()==51}? [ 0-9A-Za-z];
OS_Words: {getCharPositionInLine()==52}? WORD5 WORD5 WORD5 WORD5 WORD5 ~[\r\n] ~[\r\n] ~[\r\n] -> popMode;

	
mode FIXED_CalcSpec;
//CS_IndicatorOff: {getCharPositionInLine()==8}? [nN ] -> pushMode(IndicatorMode);
//CS_BlankFactor: {getCharPositionInLine()==11 || getCharPositionInLine()==35 || getCharPositionInLine()==49}? '              ' ;
// Symbolic Constants
CS_Factor1_SPLAT_ALL : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_ALL -> type(SPLAT_ALL);
CS_Factor1_SPLAT_NONE : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_NONE -> type(SPLAT_NONE);
CS_Factor1_SPLAT_ILERPG : {11<= getCharPositionInLine() && getCharPositionInLine()+7<=24}? SPLAT_ILERPG -> type(SPLAT_ILERPG);
CS_Factor1_SPLAT_CRTBNDRPG : {11<= getCharPositionInLine() && getCharPositionInLine()+10<=24}? SPLAT_CRTBNDRPG -> type(SPLAT_CRTBNDRPG);
CS_Factor1_SPLAT_CRTRPGMOD : {11<= getCharPositionInLine() && getCharPositionInLine()+10<=24}? SPLAT_CRTRPGMOD -> type(SPLAT_CRTRPGMOD);
CS_Factor1_SPLAT_VRM : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_VRM -> type(SPLAT_VRM);
CS_Factor1_SPLAT_ALLG : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_ALLG -> type(SPLAT_ALLG);
CS_Factor1_SPLAT_ALLU : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_ALLU -> type(SPLAT_ALLU);
CS_Factor1_SPLAT_ALLX : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_ALLX -> type(SPLAT_ALLX);
CS_Factor1_SPLAT_BLANKS : {11<= getCharPositionInLine() && getCharPositionInLine()+7<=24}? SPLAT_BLANKS -> type(SPLAT_BLANKS);
CS_Factor1_SPLAT_CANCL : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_CANCL -> type(SPLAT_CANCL);
CS_Factor1_SPLAT_CYMD : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_CYMD -> type(SPLAT_CYMD);
CS_Factor1_SPLAT_CMDY : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_CMDY -> type(SPLAT_CMDY);
CS_Factor1_SPLAT_CDMY : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_CDMY -> type(SPLAT_CDMY);
CS_Factor1_SPLAT_MDY : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_MDY -> type(SPLAT_MDY);
CS_Factor1_SPLAT_DMY : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_DMY -> type(SPLAT_DMY);
CS_Factor1_SPLAT_YMD : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_YMD -> type(SPLAT_YMD);
CS_Factor1_SPLAT_JUL : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_JUL -> type(SPLAT_JUL);
CS_Factor1_SPLAT_ISO : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_ISO -> type(SPLAT_ISO);
CS_Factor1_SPLAT_USA : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_USA -> type(SPLAT_USA);
CS_Factor1_SPLAT_EUR : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_EUR -> type(SPLAT_EUR);
CS_Factor1_SPLAT_JIS : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_JIS -> type(SPLAT_JIS);
CS_Factor1_SPLAT_DATE : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_DATE -> type(SPLAT_DATE);
CS_Factor1_SPLAT_DAY : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_DAY -> type(SPLAT_DAY);
CS_Factor1_SPLAT_DETC : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPlAT_DETC -> type(SPlAT_DETC);
CS_Factor1_SPLAT_DETL : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_DETL -> type(SPLAT_DETL);
CS_Factor1_SPLAT_DTAARA : {11<= getCharPositionInLine() && getCharPositionInLine()+7<=24}? SPLAT_DTAARA -> type(SPLAT_DTAARA);
CS_Factor1_SPLAT_END : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_END -> type(SPLAT_END);
CS_Factor1_SPLAT_ENTRY : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_ENTRY -> type(SPLAT_ENTRY);
CS_Factor1_SPLAT_EQUATE : {11<= getCharPositionInLine() && getCharPositionInLine()+7<=24}? SPLAT_EQUATE -> type(SPLAT_EQUATE);
CS_Factor1_SPLAT_EXTDFT : {11<= getCharPositionInLine() && getCharPositionInLine()+7<=24}? SPLAT_EXTDFT -> type(SPLAT_EXTDFT);
CS_Factor1_SPLAT_EXT : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_EXT -> type(SPLAT_EXT);
CS_Factor1_SPLAT_FILE : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_FILE -> type(SPLAT_FILE);
CS_Factor1_SPLAT_GETIN : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_GETIN -> type(SPLAT_GETIN);
CS_Factor1_SPLAT_HIVAL : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_HIVAL -> type(SPLAT_HIVAL);
CS_Factor1_SPLAT_INIT : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_INIT -> type(SPLAT_INIT);
CS_Factor1_SPLAT_INDICATOR : {11<= getCharPositionInLine() && getCharPositionInLine()+10<=24}? SPLAT_INDICATOR -> type(SPLAT_INDICATOR);
CS_Factor1_SPLAT_INZSR : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_INZSR -> type(SPLAT_INZSR);
CS_Factor1_SPLAT_IN : {11<= getCharPositionInLine() && getCharPositionInLine()+3<=24}? SPLAT_IN -> type(SPLAT_IN);
CS_Factor1_SPLAT_JOBRUN : {11<= getCharPositionInLine() && getCharPositionInLine()+7<=24}? SPLAT_JOBRUN -> type(SPLAT_JOBRUN);
CS_Factor1_SPLAT_JOB : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_JOB -> type(SPLAT_JOB);
CS_Factor1_SPLAT_LDA : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_LDA -> type(SPLAT_LDA);
CS_Factor1_SPLAT_LIKE : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_LIKE -> type(SPLAT_LIKE);
CS_Factor1_SPLAT_LONGJUL : {11<= getCharPositionInLine() && getCharPositionInLine()+8<=24}? SPLAT_LONGJUL -> type(SPLAT_LONGJUL);
CS_Factor1_SPLAT_LOVAL : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_LOVAL -> type(SPLAT_LOVAL);
CS_Factor1_SPLAT_MONTH : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_MONTH -> type(SPLAT_MONTH);
CS_Factor1_SPLAT_NOIND : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_NOIND -> type(SPLAT_NOIND);
CS_Factor1_SPLAT_NOKEY : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_NOKEY -> type(SPLAT_NOKEY);
CS_Factor1_SPLAT_NULL : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_NULL -> type(SPLAT_NULL);
CS_Factor1_SPLAT_OFL : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_OFL -> type(SPLAT_OFL);
CS_Factor1_SPLAT_ON : {11<= getCharPositionInLine() && getCharPositionInLine()+3<=24}? SPLAT_ON -> type(SPLAT_ON);
CS_Factor1_SPLAT_OFF : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_OFF -> type(SPLAT_OFF);
CS_Factor1_SPLAT_PDA : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_PDA -> type(SPLAT_PDA);
CS_Factor1_SPLAT_PLACE : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_PLACE -> type(SPLAT_PLACE);
CS_Factor1_SPLAT_PSSR : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_PSSR -> type(SPLAT_PSSR);
CS_Factor1_SPLAT_ROUTINE : {11<= getCharPositionInLine() && getCharPositionInLine()+8<=24}? SPLAT_ROUTINE -> type(SPLAT_ROUTINE);
CS_Factor1_SPLAT_START : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_START -> type(SPLAT_START);
CS_Factor1_SPLAT_SYS : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_SYS -> type(SPLAT_SYS);
CS_Factor1_SPLAT_TERM : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_TERM -> type(SPLAT_TERM);
CS_Factor1_SPLAT_TOTC : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_TOTC -> type(SPLAT_TOTC);
CS_Factor1_SPLAT_TOTL : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_TOTL -> type(SPLAT_TOTL);
CS_Factor1_SPLAT_USER : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_USER -> type(SPLAT_USER);
CS_Factor1_SPLAT_VAR : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_VAR -> type(SPLAT_VAR);
CS_Factor1_SPLAT_YEAR : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_YEAR -> type(SPLAT_YEAR);
CS_Factor1_SPLAT_ZEROS : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_ZEROS -> type(SPLAT_ZEROS);
CS_Factor1_SPLAT_HMS : {11<= getCharPositionInLine() && getCharPositionInLine()+4<=24}? SPLAT_HMS -> type(SPLAT_HMS);
CS_Factor1_SPLAT_INLR : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_INLR -> type(SPLAT_INLR);
CS_Factor1_SPLAT_INOF : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_INOF -> type(SPLAT_INOF);
//DurationCodes
CS_Factor1_SPLAT_D : {11<= getCharPositionInLine() && getCharPositionInLine()+2<=24}? SPLAT_D -> type(SPLAT_D);
CS_Factor1_SPLAT_DAYS : {11<= getCharPositionInLine() && getCharPositionInLine()+5<=24}? SPLAT_DAYS -> type(SPLAT_DAYS);
CS_Factor1_SPLAT_H : {11<= getCharPositionInLine() && getCharPositionInLine()+2<=24}? SPLAT_H -> type(SPLAT_H);
CS_Factor1_SPLAT_HOURS : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_HOURS -> type(SPLAT_HOURS);
CS_Factor1_SPLAT_MINUTES : {11<= getCharPositionInLine() && getCharPositionInLine()+8<=24}? SPLAT_MINUTES -> type(SPLAT_MINUTES);
CS_Factor1_SPLAT_MONTHS : {11<= getCharPositionInLine() && getCharPositionInLine()+7<=24}? SPLAT_MONTHS -> type(SPLAT_MONTHS);
CS_Factor1_SPLAT_M : {11<= getCharPositionInLine() && getCharPositionInLine()+2<=24}? SPLAT_M -> type(SPLAT_M);
CS_Factor1_SPLAT_MN : {11<= getCharPositionInLine() && getCharPositionInLine()+3<=24}? SPLAT_MN -> type(SPLAT_MN);
CS_Factor1_SPLAT_MS : {11<= getCharPositionInLine() && getCharPositionInLine()+3<=24}? SPLAT_MS -> type(SPLAT_MS);
CS_Factor1_SPLAT_MSECONDS : {11<= getCharPositionInLine() && getCharPositionInLine()+9<=24}? SPLAT_MSECONDS -> type(SPLAT_MSECONDS);
CS_Factor1_SPLAT_SECONDS : {11<= getCharPositionInLine() && getCharPositionInLine()+8<=24}? SPLAT_SECONDS -> type(SPLAT_SECONDS);
CS_Factor1_SPLAT_YEARS : {11<= getCharPositionInLine() && getCharPositionInLine()+6<=24}? SPLAT_YEARS -> type(SPLAT_YEARS);
CS_Factor1_SPLAT_Y : {11<= getCharPositionInLine() && getCharPositionInLine()+2<=24}? SPLAT_Y -> type(SPLAT_Y);

//Factor 2
CS_Factor2_SPLAT_ALL : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_ALL -> type(SPLAT_ALL);
CS_Factor2_SPLAT_NONE : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_NONE -> type(SPLAT_NONE);
CS_Factor2_SPLAT_ILERPG : {35<= getCharPositionInLine() && getCharPositionInLine()+7<=48}? SPLAT_ILERPG -> type(SPLAT_ILERPG);
CS_Factor2_SPLAT_CRTBNDRPG : {35<= getCharPositionInLine() && getCharPositionInLine()+10<=48}? SPLAT_CRTBNDRPG -> type(SPLAT_CRTBNDRPG);
CS_Factor2_SPLAT_CRTRPGMOD : {35<= getCharPositionInLine() && getCharPositionInLine()+10<=48}? SPLAT_CRTRPGMOD -> type(SPLAT_CRTRPGMOD);
CS_Factor2_SPLAT_VRM : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_VRM -> type(SPLAT_VRM);
CS_Factor2_SPLAT_ALLG : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_ALLG -> type(SPLAT_ALLG);
CS_Factor2_SPLAT_ALLU : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_ALLU -> type(SPLAT_ALLU);
CS_Factor2_SPLAT_ALLX : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_ALLX -> type(SPLAT_ALLX);
CS_Factor2_SPLAT_BLANKS : {35<= getCharPositionInLine() && getCharPositionInLine()+7<=48}? SPLAT_BLANKS -> type(SPLAT_BLANKS);
CS_Factor2_SPLAT_CANCL : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_CANCL -> type(SPLAT_CANCL);
CS_Factor2_SPLAT_CYMD : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_CYMD -> type(SPLAT_CYMD);
CS_Factor2_SPLAT_CMDY : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_CMDY -> type(SPLAT_CMDY);
CS_Factor2_SPLAT_CDMY : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_CDMY -> type(SPLAT_CDMY);
CS_Factor2_SPLAT_MDY : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_MDY -> type(SPLAT_MDY);
CS_Factor2_SPLAT_DMY : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_DMY -> type(SPLAT_DMY);
CS_Factor2_SPLAT_YMD : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_YMD -> type(SPLAT_YMD);
CS_Factor2_SPLAT_JUL : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_JUL -> type(SPLAT_JUL);
CS_Factor2_SPLAT_ISO : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_ISO -> type(SPLAT_ISO);
CS_Factor2_SPLAT_USA : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_USA -> type(SPLAT_USA);
CS_Factor2_SPLAT_EUR : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_EUR -> type(SPLAT_EUR);
CS_Factor2_SPLAT_JIS : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_JIS -> type(SPLAT_JIS);
CS_Factor2_SPLAT_DATE : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_DATE -> type(SPLAT_DATE);
CS_Factor2_SPLAT_DAY : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_DAY -> type(SPLAT_DAY);
CS_Factor2_SPLAT_DETC : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPlAT_DETC -> type(SPlAT_DETC);
CS_Factor2_SPLAT_DETL : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_DETL -> type(SPLAT_DETL);
CS_Factor2_SPLAT_DTAARA : {35<= getCharPositionInLine() && getCharPositionInLine()+7<=48}? SPLAT_DTAARA -> type(SPLAT_DTAARA);
CS_Factor2_SPLAT_END : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_END -> type(SPLAT_END);
CS_Factor2_SPLAT_ENTRY : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_ENTRY -> type(SPLAT_ENTRY);
CS_Factor2_SPLAT_EQUATE : {35<= getCharPositionInLine() && getCharPositionInLine()+7<=48}? SPLAT_EQUATE -> type(SPLAT_EQUATE);
CS_Factor2_SPLAT_EXTDFT : {35<= getCharPositionInLine() && getCharPositionInLine()+7<=48}? SPLAT_EXTDFT -> type(SPLAT_EXTDFT);
CS_Factor2_SPLAT_EXT : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_EXT -> type(SPLAT_EXT);
CS_Factor2_SPLAT_FILE : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_FILE -> type(SPLAT_FILE);
CS_Factor2_SPLAT_GETIN : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_GETIN -> type(SPLAT_GETIN);
CS_Factor2_SPLAT_HIVAL : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_HIVAL -> type(SPLAT_HIVAL);
CS_Factor2_SPLAT_INIT : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_INIT -> type(SPLAT_INIT);
CS_Factor2_SPLAT_INDICATOR : {35<= getCharPositionInLine() && getCharPositionInLine()+10<=48}? SPLAT_INDICATOR -> type(SPLAT_INDICATOR);
CS_Factor2_SPLAT_INZSR : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_INZSR -> type(SPLAT_INZSR);
CS_Factor2_SPLAT_IN : {35<= getCharPositionInLine() && getCharPositionInLine()+3<=48}? SPLAT_IN -> type(SPLAT_IN);
CS_Factor2_SPLAT_JOBRUN : {35<= getCharPositionInLine() && getCharPositionInLine()+7<=48}? SPLAT_JOBRUN -> type(SPLAT_JOBRUN);
CS_Factor2_SPLAT_JOB : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_JOB -> type(SPLAT_JOB);
CS_Factor2_SPLAT_LDA : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_LDA -> type(SPLAT_LDA);
CS_Factor2_SPLAT_LIKE : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_LIKE -> type(SPLAT_LIKE);
CS_Factor2_SPLAT_LONGJUL : {35<= getCharPositionInLine() && getCharPositionInLine()+8<=48}? SPLAT_LONGJUL -> type(SPLAT_LONGJUL);
CS_Factor2_SPLAT_LOVAL : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_LOVAL -> type(SPLAT_LOVAL);
CS_Factor2_SPLAT_MONTH : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_MONTH -> type(SPLAT_MONTH);
CS_Factor2_SPLAT_NOIND : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_NOIND -> type(SPLAT_NOIND);
CS_Factor2_SPLAT_NOKEY : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_NOKEY -> type(SPLAT_NOKEY);
CS_Factor2_SPLAT_NULL : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_NULL -> type(SPLAT_NULL);
CS_Factor2_SPLAT_OFL : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_OFL -> type(SPLAT_OFL);
CS_Factor2_SPLAT_ON : {35<= getCharPositionInLine() && getCharPositionInLine()+3<=48}? SPLAT_ON -> type(SPLAT_ON);
CS_Factor2_SPLAT_OFF : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_OFF -> type(SPLAT_OFF);
CS_Factor2_SPLAT_PDA : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_PDA -> type(SPLAT_PDA);
CS_Factor2_SPLAT_PLACE : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_PLACE -> type(SPLAT_PLACE);
CS_Factor2_SPLAT_PSSR : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_PSSR -> type(SPLAT_PSSR);
CS_Factor2_SPLAT_ROUTINE : {35<= getCharPositionInLine() && getCharPositionInLine()+8<=48}? SPLAT_ROUTINE -> type(SPLAT_ROUTINE);
CS_Factor2_SPLAT_START : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_START -> type(SPLAT_START);
CS_Factor2_SPLAT_SYS : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_SYS -> type(SPLAT_SYS);
CS_Factor2_SPLAT_TERM : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_TERM -> type(SPLAT_TERM);
CS_Factor2_SPLAT_TOTC : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_TOTC -> type(SPLAT_TOTC);
CS_Factor2_SPLAT_TOTL : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_TOTL -> type(SPLAT_TOTL);
CS_Factor2_SPLAT_USER : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_USER -> type(SPLAT_USER);
CS_Factor2_SPLAT_VAR : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_VAR -> type(SPLAT_VAR);
CS_Factor2_SPLAT_YEAR : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_YEAR -> type(SPLAT_YEAR);
CS_Factor2_SPLAT_ZEROS : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_ZEROS -> type(SPLAT_ZEROS);
CS_Factor2_SPLAT_HMS : {35<= getCharPositionInLine() && getCharPositionInLine()+4<=48}? SPLAT_HMS -> type(SPLAT_HMS);
CS_Factor2_SPLAT_INLR : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_INLR -> type(SPLAT_INLR);
CS_Factor2_SPLAT_INOF : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_INOF -> type(SPLAT_INOF);
//Duration
CS_Factor2_SPLAT_D : {35<= getCharPositionInLine() && getCharPositionInLine()+2<=48}? SPLAT_D -> type(SPLAT_D);
CS_Factor2_SPLAT_DAYS : {35<= getCharPositionInLine() && getCharPositionInLine()+5<=48}? SPLAT_DAYS -> type(SPLAT_DAYS);
CS_Factor2_SPLAT_H : {35<= getCharPositionInLine() && getCharPositionInLine()+2<=48}? SPLAT_H -> type(SPLAT_H);
CS_Factor2_SPLAT_HOURS : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_HOURS -> type(SPLAT_HOURS);
CS_Factor2_SPLAT_MINUTES : {35<= getCharPositionInLine() && getCharPositionInLine()+8<=48}? SPLAT_MINUTES -> type(SPLAT_MINUTES);
CS_Factor2_SPLAT_MONTHS : {35<= getCharPositionInLine() && getCharPositionInLine()+7<=48}? SPLAT_MONTHS -> type(SPLAT_MONTHS);
CS_Factor2_SPLAT_M : {35<= getCharPositionInLine() && getCharPositionInLine()+2<=48}? SPLAT_M -> type(SPLAT_M);
CS_Factor2_SPLAT_MN : {35<= getCharPositionInLine() && getCharPositionInLine()+3<=48}? SPLAT_MN -> type(SPLAT_MN);
CS_Factor2_SPLAT_MS : {35<= getCharPositionInLine() && getCharPositionInLine()+3<=48}? SPLAT_MS -> type(SPLAT_MS);
CS_Factor2_SPLAT_MSECONDS : {35<= getCharPositionInLine() && getCharPositionInLine()+9<=48}? SPLAT_MSECONDS -> type(SPLAT_MSECONDS);
CS_Factor2_SPLAT_SECONDS : {35<= getCharPositionInLine() && getCharPositionInLine()+8<=48}? SPLAT_SECONDS -> type(SPLAT_SECONDS);
CS_Factor2_SPLAT_YEARS : {35<= getCharPositionInLine() && getCharPositionInLine()+6<=48}? SPLAT_YEARS -> type(SPLAT_YEARS);
CS_Factor2_SPLAT_Y : {35<= getCharPositionInLine() && getCharPositionInLine()+2<=48}? SPLAT_Y -> type(SPLAT_Y);

CS_BlankFactor: {(getCharPositionInLine()==11)
			|| (getCharPositionInLine()==35)
			|| (getCharPositionInLine()==49)}? 
			 		'              ';
CS_FactorWs: ({(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
}?  
		' ')+ -> skip;
CS_FactorWs2: ({(getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
}?  
		' ')+ -> skip;
		
/*
 * This rather awkward token, matches a literal. including whitespace literals
 */		
CS_FactorContentLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
}?
		'\''
({(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
}?
		~[\r\n :])*
({(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
}?
		'\'')
;		
CS_FactorContent: ({(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
}?
		~[\r\n :])+;
CS_FactorColon: ({(getCharPositionInLine()>11 && getCharPositionInLine()<24)
			|| (getCharPositionInLine()>35 && getCharPositionInLine()<48)
			|| (getCharPositionInLine()>49 && getCharPositionInLine()<62)
}?
		[:]) -> type(COLON);//pushMode(FIXED_CalcSpec_2PartFactor), 
CS_OperationAndExtendedFactor2: {getCharPositionInLine()==25}? 
	(OP_EVAL '      ' 
		| OP_IF '        '  
		| OP_CALLP'     '
		| OP_CALLP[(][eE][)]'  '
		| OP_DOW'       '
		| OP_ELSEIF'    '
	) -> pushMode(FREE);
CS_OperationAndExtender:  
   ({getCharPositionInLine()>=25 && getCharPositionInLine()<35}?~[\r\n)(])+
   {setText(getText().trim());};
CS_OperationExtenderOpen: {getCharPositionInLine()>=25 && getCharPositionInLine()<35}?OPEN_PAREN -> type(OPEN_PAREN);
CS_OperationExtenderClose: {getCharPositionInLine()>=25 && getCharPositionInLine()<35}?CLOSE_PAREN 
  ({getCharPositionInLine()>=25 && getCharPositionInLine()<35}? ' ')*
  {setText(getText().trim());}
  -> type(CLOSE_PAREN);
CS_FieldLength: {getCharPositionInLine()==63}? [ 0-9][ 0-9][ 0-9][ 0-9][ 0-9];
CS_DecimalPositions: {getCharPositionInLine()==68}? [ 0-9][ 0-9]
	-> pushMode(IndicatorMode),pushMode(IndicatorMode),pushMode(IndicatorMode); // 3 Indicators in a row
CS_WhiteSpace : {getCharPositionInLine()>=76}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
CS_Comments : {getCharPositionInLine()>=80}? ~[\r\n]+  ; // skip spaces, tabs, newlines
CS_EOL : NEWLINE -> type(EOL),popMode;

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
C2_OTHER: {getCharPositionInLine()<35}? ~('\r' | '\n') ->skip;

mode FIXED_InputSpec;
IS_FileName: {getCharPositionInLine()==6}? WORD5_WCOLON WORD5_WCOLON ;
IS_FieldReserved: {getCharPositionInLine()==6}? '                        ' -> pushMode(FIXED_I_FIELD_SPEC),skip ;
IS_ExtFieldReserved: {getCharPositionInLine()==6}? '              ' -> pushMode(FIXED_I_EXT_FIELD_SPEC),skip ;
IS_LogicalRelationship : {getCharPositionInLine()==15}? ('AND' | 'OR '| ' OR') ;
IS_ExtRecordReserved : {getCharPositionInLine()==16}? '    '  -> pushMode(FIXED_I_EXT_REC_SPEC),pushMode(IndicatorMode) ;
IS_Sequence : {getCharPositionInLine()==16}? WORD_WCOLON WORD_WCOLON ;
IS_Number : {getCharPositionInLine()==18}? [ 1nN] ;
IS_Option: {getCharPositionInLine()==19}? [ oO] -> pushMode(IndicatorMode);
//IS_RecordIdIndicator: {getCharPositionInLine()==20}? IND_FRAG | '**';
IS_RecordIdCode: {getCharPositionInLine()==22}?  WORD5_WCOLON WORD5_WCOLON WORD5_WCOLON WORD5_WCOLON
		WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON; //TODO better lexing
IS_WS : {getCharPositionInLine()>=46}? [ \t]+ -> type(WS),skip  ; // skip spaces, tabs
IS_COMMENTS : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines
IS_EOL : NEWLINE -> type(EOL),popMode;

mode FIXED_I_EXT_FIELD_SPEC;
IF_Name: {getCharPositionInLine()==20}? WORD5_WCOLON WORD5_WCOLON ;
IF_Reserved: {getCharPositionInLine()==30}? '                  ' -> skip;
IF_FieldName: {getCharPositionInLine()==48}? WORD5_WCOLON WORD5_WCOLON WORD_WCOLON WORD_WCOLON
	WORD_WCOLON WORD_WCOLON->pushMode(IndicatorMode),pushMode(IndicatorMode);
//IF_ControlLevel : {getCharPositionInLine()==62}? ('L'[0-9] | '  ') ;	
//IF_MatchingFields: {getCharPositionInLine()==64}? ('M'[0-9] | '  ') ;
IF_Reserved2: {getCharPositionInLine()==66}? '  ' ->pushMode(IndicatorMode),pushMode(IndicatorMode),pushMode(IndicatorMode),skip; // 3 Indicators in a row
IF_WS : {getCharPositionInLine()>=74}? [ \t]+ -> type(WS),popMode,skip  ; // skip spaces, tabs

mode FIXED_I_EXT_REC_SPEC;
//IR_ID_IND : {getCharPositionInLine()==20}? IND_FRAG ;
IR_WS : {getCharPositionInLine()>=22}? [ \t]+ -> type(WS),popMode  ; // skip spaces, tabs

mode FIXED_I_FIELD_SPEC;
IFD_DATA_ATTR: {getCharPositionInLine()==30}?  WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
IFD_DATETIME_SEP: {getCharPositionInLine()==34}?  ~[\r\n];
IFD_DATA_FORMAT: {getCharPositionInLine()==35}?  [A-Z ];
IFD_FIELD_LOCATION: {getCharPositionInLine()==36}?  WORD5_WCOLON WORD5_WCOLON;
IFD_DECIMAL_POSITIONS: {getCharPositionInLine()==46}?  [ 0-9][ 0-9];
IFD_FIELD_NAME: {getCharPositionInLine()==48}?  WORD5_WCOLON WORD5_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
IFD_CONTROL_LEVEL : {getCharPositionInLine()==62}? ('L'[0-9] | '  ') ;
IFD_MATCHING_FIELDS: {getCharPositionInLine()==64}? ('M'[0-9] | '  ') ->pushMode(IndicatorMode),pushMode(IndicatorMode),
	pushMode(IndicatorMode),pushMode(IndicatorMode);
//IFD_FIELD_RELATION: {getCharPositionInLine()==66}? IND_FRAG ;
//IFD_FIELD_IND: {getCharPositionInLine()==68}? IND_FRAG IND_FRAG IND_FRAG ;
IFD_BLANKS: {getCharPositionInLine()==74}? '      ' -> skip;
IFD_COMMENTS : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines
IFD_EOL : NEWLINE -> type(EOL),popMode,popMode;

mode HeaderSpecMode;
HS_OPEN_PAREN: OPEN_PAREN -> type(OPEN_PAREN);
HS_CLOSE_PAREN: CLOSE_PAREN -> type(CLOSE_PAREN);
HS_StringLiteralStart: ['] -> type(StringLiteralStart),pushMode(InStringMode) ;
HS_COLON: ':' -> type(COLON);
HS_ID: [#@%$*a-zA-Z] [#@\-$*a-zA-Z0-9_]* -> type(ID);
HS_WhiteSpace : [ \t]+ -> skip  ; // skip spaces, tabs, newlines
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
fragment WORD_WCOLON : ~[\r\n];//[a-zA-Z0-9 :*];
fragment WORD5_WCOLON : WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
//fragment C_OP_EXT: [ aAhHnNpPdDtTzZmMrReE];
//fragment IND_FRAG: ('  ' | [0-9lLhH][0-9] | [kK][A-NP-Ya-np0y] | [lLmM][rR] | [rR][tT] | [uU][1-8] | [oO][a-gvA-GV] | [0-9][pP]);

