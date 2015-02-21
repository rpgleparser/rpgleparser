/**
 * Define a grammar called Hello
 */
lexer grammar RpgLexer;

// Parser Rules
	//End Source.  Not more parsing after this.
END_SOURCE : {getCharPositionInLine()==0}? '**'~[\r\n]~[\r\n]~[\r\n]~[\r\n*]~[\r\n]* EOL  -> pushMode(END_OF_SOURCE) ;
    //Ignore or skip leading 5 white space.
LEAD_WS5 :  {getCharPositionInLine()==0}? '     ' -> skip;
LEAD_WS5_Comments :  {getCharPositionInLine()==0}?~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> channel(HIDDEN);
	//5 position blank means FREE, unless..
FREE_SPEC : {getCharPositionInLine()==5}? [  ] -> pushMode(FREE),skip;
    // 6th position asterisk is a comment
COMMENT_SPEC_FIXED : {getCharPositionInLine()==5}? .'*' -> pushMode(FIXED_COMMENT_SPEC) ;
    // X specs
D_SPEC_FIXED : {getCharPositionInLine()==5}? [dD] -> pushMode(FIXED_D_SPEC) ;
F_SPEC_FIXED : {getCharPositionInLine()==5}? [fF] -> pushMode(FIXED_F_SPEC) ;
O_SPEC_FIXED : {getCharPositionInLine()==5}? [oO] -> pushMode(FIXED_O_SPEC) ;
C_SPEC_FIXED : {getCharPositionInLine()==5}? [cC] -> pushMode(FIXED_C_SPEC) ;
C_SPEC_FIXED_SQL : {getCharPositionInLine()==5}? [cC] '/' EXEC_SQL -> pushMode(FIXED_C_SPEC_SQL);
I_SPEC_FIXED : {getCharPositionInLine()==5}? [iI] -> pushMode(FIXED_I_SPEC) ;
P_SPEC_FIXED : {getCharPositionInLine()==5}? [pP] -> pushMode(FIXED_P_SPEC) ;
H_SPEC_FIXED : {getCharPositionInLine()==5}? [hH] -> pushMode(H_SPEC_FREE) ;

BLANK_LINE : {getCharPositionInLine()==5}? [ ]+ NEWLINE -> skip;
COMMENTS : {getCharPositionInLine()>=5}? [ ][ ]*? '//' -> pushMode(FIXED_COMMENT_SPEC),skip ;
EMPTY_LIBE : {getCharPositionInLine()>=5}? 
	'                                                                           ' -> pushMode(FIXED_COMMENT_SPEC),skip ;
	//Directives are not necessarily blank at pos 5
DIRECTIVE : {getCharPositionInLine()>=5}? .[ ]*? '/' -> pushMode(DIRECTIVE_MODE) ;

OPEN_PAREN : '(';
CLOSE_PAREN : ')';
NUMBER : ([0-9]+([.][0-9]*)?) | [.][0-9]+ ;
SEMI : ';';
COLON : ':';
ID : {getCharPositionInLine()>6}?('*'? '*' [a-zA-Z])?[#@%$a-zA-Z] [#@\-$a-zA-Z0-9_]* ;             // match lower-case identifiers
NEWLINE : ('\r' '\n'? | '\n') -> skip;
WS : {getCharPositionInLine()>5}? [ \t]+ -> skip ; // skip spaces, tabs, NEWLINEs

mode DIRECTIVE_MODE;
DIRECTIVE_FREE: ([fF][rR][eE][eE] | [eE][nN][dD] '-' [fF][rR][eE][eE]) -> pushMode(SKIP_REMAINING_WS);
DIRECTIVE_TITLE: 'TITLE';
DIRECTIVE_EJECT: 'EJECT' -> pushMode(SKIP_REMAINING_WS);
DIRECTIVE_SPACE: 'SPACE';
DIRECTIVE_SET: 'SET';
DIRECTIVE_RESTORE: 'RESTORE';
DIRECTIVE_COPY: 'COPY';
DIRECTIVE_INCLUDE: 'INCLUDE';
DIRECTIVE_EOF: 'EOF';
DIR_NUMBER: NUMBER;
DIR_SPACE: [ ] ;
DIR_OTHER_TEXT : ~[\r\n \t]+ ;
DIR_EOL : [ ]* NEWLINE -> popMode;

mode SKIP_REMAINING_WS;
DIR_FREE_OTHER_TEXT: ~[\r\n]+ -> popMode,skip;

mode END_OF_SOURCE;
EOS_TEXT : ~[\r\n]+ ;
EOS_EOL : NEWLINE; 

// -----------------  ---------------------
mode FREE;
D_SPEC : [dD] [cC] [lL] '-' [sS] ;//-> pushMode(F_SPEC_FREE);
D_DS_SPEC : [dD] [cC] [lL] '-' [dD][sS] ;//-> pushMode(F_SPEC_FREE);
D_DS_END : [eE] [nN] [dD] '-' [dD][sS] ;//-> pushMode(F_SPEC_FREE);
D_PR_SPEC : [dD] [cC] [lL] '-' [pP][rR] ;//-> pushMode(F_SPEC_FREE);
D_PR_END : [eE] [nN] [dD] '-' [pP][rR] ;//-> pushMode(F_SPEC_FREE);
D_PI_SPEC : [dD] [cC] [lL] '-' [pP][iI] ;//-> pushMode(F_SPEC_FREE);
D_PI_END : [eE] [nN] [dD] '-' [pP][iI] ;//-> pushMode(F_SPEC_FREE);
D_C_SPEC : [dD] [cC] [lL] '-' [cC] ;//-> pushMode(F_SPEC_FREE);

F_SPEC : [dD] [cC] [lL] '-' [fF] ;//-> pushMode(F_SPEC_FREE);
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
SPLAT_ALL: '*'[aA][lL][lL];
//--------------
OP_E: '(' [aAdDeEhHmMnNpPrRtTzZ][aAdDeEhHmMnNpPrRtTzZ]? ')';
FREE_OPEN_PAREN: OPEN_PAREN;
FREE_CLOSE_PAREN: CLOSE_PAREN;
FREE_DOT: '.';
FREE_NUMBER: NUMBER;
FREE_ASSIGNMENT: '=';
FREE_OPERATION: '+' | '/' | '+=' | '-=';
FREE_OPERATION_MULT_NOSPACE: {_input.LA(2) != 32}? '*';
FREE_OPERATION_MULT: {_input.LA(2) == 32}? '*' ;
FREE_OPERATION_MINUS: '-' ;
FREE_COMPARE: '<' | '>'  | '<'[>=] | '>=';
FREE_COLON: COLON;
FREE_NOT: [nN][oO][tT];
FREE_OR: [oO][rR];
FREE_AND: [aA][nN][dD];
FREE_BY: [bB][yY];
FREE_TO: [tT][oO];
FREE_DOWNTO: [dD][oO][wW][nN][tT][oO];
FREE_ID: ID;
//FREE_STRING: ['] ~[']* ['];
FREE_HEXSTRING_START: [xX]['] -> pushMode(FREE_STRING_MODE) ;
FREE_DATESTRING_START: [dD]['] -> pushMode(FREE_STRING_MODE) ;
FREE_STRING_START: ['] -> pushMode(FREE_STRING_MODE) ; 
FREE_COMMENTS: {getCharPositionInLine()>=7}? [ ]*? '//' -> pushMode(FIXED_COMMENT_SPEC_HIDDEN),channel(HIDDEN) ;
FREE_WS: {getCharPositionInLine()>5}? [ \t]+ -> skip;
FREE_CONTINUATION : {_modeStack.peek()!=FIXED_C_SPEC}? '...' WS* NEWLINE -> skip ;
C_FREE_CONTINUATION_DOTS : {_modeStack.peek()==FIXED_C_SPEC}? '...' WS* NEWLINE 
	(~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 'C' ~[*] '                            ') {setText("...");};
C_FREE_CONTINUATION: {_modeStack.peek()==FIXED_C_SPEC}? NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 'C' ~[*] '                            ' -> skip;
FREE_LEAD_WS5 :  {getCharPositionInLine()==0}? '     ' -> skip;
FREE_LEAD_WS5_Comments :  {getCharPositionInLine()==0}?~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> channel(HIDDEN);
FREE_FREE_SPEC : {getCharPositionInLine()==5}? [  ] -> skip;
	
C_FREE_NEWLINE: {_modeStack.peek()==FIXED_C_SPEC}? NEWLINE -> popMode,popMode;
FREE_NEWLINE: {_modeStack.peek()!=FIXED_C_SPEC}? NEWLINE -> skip;
FREE_SEMI: SEMI -> popMode, pushMode(FREE_ENDED);  //Captures // immediately following the semi colon

mode FREE_ENDED;
FE_BLANKS : [ ]+ -> skip;
FE_COMMENTS: '//' -> popMode,pushMode(FIXED_COMMENT_SPEC_HIDDEN),channel(HIDDEN) ;
FE_NEWLINE : NEWLINE -> popMode,skip;

mode FREE_STRING_MODE;
	//  Any char except +,- or ', or a + or - followed by more than just whitespace 
FREE_STRING_CONTENT: (~['\r\n+-] | [+-] ' '* ~['\r\n ])+;// space or not 
FREE_STRING_ESCAPEQUOTE: [']['] {setText("'");};
FREE_STRING_ENDQUOTE: ['] -> popMode;
C_FREE_STRING_CONTINUATION: {_modeStack.peek()==FIXED_C_SPEC}? '+' [ ]* NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 'C' ~[*] '                            ' [ ]* -> skip;
C_FREE_STRING_CONTINUATION_MINUS: {_modeStack.peek()==FIXED_C_SPEC}? '-' [ ]* NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 'C' ~[*] '                            ' -> skip;
FREE_STRING_CONTINUATION: {_modeStack.peek()!=FIXED_C_SPEC}? '+' [ ]* NEWLINE '       ' [ ]* -> skip;
FREE_STRING_CONTINUATION_MINUS: {_modeStack.peek()!=FIXED_C_SPEC}? '-' [ ]* NEWLINE '       ' -> skip;
FREE_MINUS_PLUS: [+-];


// -----------------  ---------------------
mode FIXED_COMMENT_SPEC;
COMMENTS_TEXT : ~[\r\n]+ -> channel(HIDDEN);
COMMENTS_EOL : NEWLINE -> popMode,skip;

mode FIXED_COMMENT_SPEC_HIDDEN;
COMMENTS_TEXT_SKIP : [ ]+ -> skip;
COMMENTS_TEXT_HIDDEN : ~[ ] ~[\r\n]+ -> channel(HIDDEN);
COMMENTS_EOL_HIDDEN : NEWLINE ->  channel(HIDDEN),popMode;

mode SQL_MODE;
SQL_WS: [ \t\r\n]+ -> skip;
//SINGLE_QTE: ['];
//DOUBLE_QTE: ["];
SEMI_COLON: SEMI -> popMode, popMode;
WORDS: ~[ ;\r\n] (~[;\r\n]+ ~[ ;\r\n])?; 

// ----------------- Everything FIXED_P_SPEC of a tag ---------------------
mode FIXED_P_SPEC;
P_NAME : {getCharPositionInLine()==6}? WORD5 WORD5 WORD5 {setText(getText().trim());};
P_RESERVED1: {getCharPositionInLine()==21}? '  ' -> skip;
P_BEGINEND: {getCharPositionInLine()==23}? [bBeE];
P_RESERVED2: {getCharPositionInLine()==24}? '                   ' -> skip;
P_KEYWORDS : {getCharPositionInLine()==43}? ~[\r\n]+ -> popMode;

// ----------------- Everything FIXED_D_SPEC of a tag ---------------------
mode FIXED_D_SPEC;
CONTINUATION_NAME : [ ]* ~[\r\n ]+ CONTINUATION {setText(getText().substring(0,getText().length()-3));} -> pushMode(CONTINUATION_D_ELIPSIS) ;
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

mode CONTINUATION_D_ELIPSIS;
CE_WS: WS ->skip;
CE_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n ]~[\r\n]* -> channel(HIDDEN); // skip comments after 80
CE_LEAD_WS5 :  LEAD_WS5 ->skip;
CE_D_SPEC_FIXED : {getCharPositionInLine()==5}? [dD] -> skip,popMode ;
CE_NEWLINE: NEWLINE ->skip;

// ----------------- Everything FIXED_F_SPEC of a tag ---------------------
mode FIXED_F_SPEC;
F_RECORD_NAME : {getCharPositionInLine()==6}? WORD5 WORD5;
F_TYPE: {getCharPositionInLine()==16}? [a-zA-Z ];
F_DESIGNATION: {getCharPositionInLine()==17}? [a-zA-Z ];
F_END_OF_FILE: {getCharPositionInLine()==18}? [eE ];
F_ADDITION: {getCharPositionInLine()==19}? [aA ];
F_SEQUENCE: {getCharPositionInLine()==20}? [aAdD ];
F_FORMAT: {getCharPositionInLine()==21}? [eEfF ];
F_RECORD_LEN: {getCharPositionInLine()==22}? WORD5;
F_LIMITS: {getCharPositionInLine()==27}? [lL ];
F_LEN_OF_KEY: {getCharPositionInLine()==28}? [0-9 ][0-9 ][0-9 ][0-9 ][0-9 ];
F_RECORD_ADDRESS_TYPE: {getCharPositionInLine()==33}? [a-zA-Z ];
F_ORGANIZATION: {getCharPositionInLine()==34}? [a-zA-Z ];
F_DEVICE: {getCharPositionInLine()==35}? WORD5 [a-zA-Z ][a-zA-Z ];
F_RESERVED: {getCharPositionInLine()==42}? [ ];
F_KEYWORDS : {getCharPositionInLine()==43}? ~[\r\n]+;
F_WS : {getCharPositionInLine()>=80}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
F_EOL : NEWLINE -> popMode;

mode FIXED_O_SPEC;
O_RECORD_NAME : {getCharPositionInLine()==6}? WORD5 WORD5;
O_AND_OR: {getCharPositionInLine()==6}? '         ' ([aA][nN][dD] | [oO][rR] ' ') '  ';
O_FIELD_RESERVED: {getCharPositionInLine()==6}? '              ' -> pushMode(FIXED_O_SPEC_PGMFIELD);
O_TYPE: {getCharPositionInLine()==16}? [a-zA-Z ];
O_ADD_DELETE: {getCharPositionInLine()==17}? ([aA][dD][dD] | [dD][eE][lL])  -> pushMode(FIXED_O_SPEC_PGM1); 
O_FETCH_OVERFLOW: {getCharPositionInLine()==17}? (' ' | [fFrR]) '  ';
//O_FETCH_OVERFLOW: {getCharPositionInLine()==17}? [aA][dD][dD] | [dD][eE][lL] | '   ';
O_OUTPUT_COND: {getCharPositionInLine()==20 || getCharPositionInLine()==23
		|| getCharPositionInLine()==26}? [N ] IND_FRAG ; 
O_EXCEPT_NAME: {getCharPositionInLine()==29}? WORD5 WORD5;
//O_RECORD_ADDRESS_TYPE: {getCharPositionInLine()==39}? WORD5 WORD5 ~[\r\n]  ~[\r\n];
O_SPACE_3: {getCharPositionInLine()==39 || getCharPositionInLine()==42 
	|| getCharPositionInLine()==45 || getCharPositionInLine()==48}? [ 0-9][ 0-9][ 0-9];
O_REMAINING_SPACE: {getCharPositionInLine()==51}? '                             ';
O_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN); // skip comments after 80
	
//O_ORGANIZATION: {getCharPositionInLine()==34}? [a-zA-Z ];
//O_DEVICE: {getCharPositionInLine()==35}? WORD5 [a-zA-Z ][a-zA-Z ];
//O_RESERVED: {getCharPositionInLine()==42}? [ ];
//O_KEYWORDS : {getCharPositionInLine()==43}? ~[\r\n]+;
O_WS : {getCharPositionInLine()>=80}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
O_EOL : NEWLINE -> popMode,skip;

mode FIXED_O_SPEC_PGM1;
O1_OUTPUT_COND: {getCharPositionInLine()==20 || getCharPositionInLine()==23
		|| getCharPositionInLine()==26}? [N ] IND_FRAG ;
O1_EXCEPT_NAME: {getCharPositionInLine()==29}? WORD5 WORD5;
O1_REMAINING_SPACE2: {getCharPositionInLine()==39}? '                                         ' -> popMode;	

mode FIXED_O_SPEC_PGMFIELD;
OF_OUTPUT_COND: {getCharPositionInLine()==20 || getCharPositionInLine()==23
		|| getCharPositionInLine()==26}? [Nn ] IND_FRAG ;
OF_FIELD_NAME: {getCharPositionInLine()==29}? WORD5 WORD5 ~[\r\n] ~[\r\n] ~[\r\n] ~[\r\n];
OF_EDIT_CODES: {getCharPositionInLine()==43}? [ 0-9A-Za-z];
OF_BLANK_AFTER: {getCharPositionInLine()==44}? [ bB];
OF_RESERVED1: {getCharPositionInLine()==45}? [ ] -> skip;
OF_END_POSITION: {getCharPositionInLine()==46}? WORD5;
OF_DATA_FORMAT: {getCharPositionInLine()==51}? [ 0-9A-Za-z];
OF_WORDS: {getCharPositionInLine()==52}? WORD5 WORD5 WORD5 WORD5 WORD5 ~[\r\n] ~[\r\n] ~[\r\n] -> popMode;

mode FIXED_C_SPEC;
C_CONTROL_LEVEL : {getCharPositionInLine()==6}? ([sS][rR] | [lL][rR0-9] | '  ') ;
C_CONTROL_LEVEL_COND : {getCharPositionInLine()==6}? ([aA][nN] | [oO][rR]);
//C_CONTROL_LEVEL_BLANK : {getCharPositionInLine()==6}? '  ' ;
C_INDICATORS: {getCharPositionInLine()==8}? [nN ] IND_FRAG ;
//C_BLANK_FACTOR: {getCharPositionInLine()==11 || getCharPositionInLine()==35 || getCharPositionInLine()==49}? '              ';
C_FACTOR: {getCharPositionInLine()==11 || getCharPositionInLine()==35 || getCharPositionInLine()==49}? 
		WORD5_WCOLON WORD5_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
C_OP_AND_EXTNDR_X2: {getCharPositionInLine()==25}? 
	([eE][vV][aA][lL] '      ' 
		| [iI][fF]'        ' 
		| [cC][aA][lL][lL][pP]'     '
		| [cC][aA][lL][lL][pP][(][eE][)]'  '
		| [dD][oO][wW]'       '
		| [eE][lL][sS][eE][iI][fF]'    '
	) -> pushMode(FREE);
C_OP_AND_EXTNDR: {getCharPositionInLine()==25}? WORD5 WORD5;
//C_FACTOR: {getCharPositionInLine()==35}?--> FACTOR2
//C_FACTOR: {getCharPositionInLine()==49}? -->RESULT
C_FIELD_LENGTH: {getCharPositionInLine()==63}? [ 0-9][ 0-9][ 0-9][ 0-9][ 0-9];
C_DECIMAL_POSITIONS: {getCharPositionInLine()==68}? [ 0-9][ 0-9];
C_RESULTING_INDICATORS: {getCharPositionInLine()==70}? IND_FRAG IND_FRAG IND_FRAG;
C_WS : {getCharPositionInLine()>=76}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
C_COMMENTS : {getCharPositionInLine()>=80}? ~[\r\n]+  ; // skip spaces, tabs, newlines
C_EOL : NEWLINE -> popMode;

mode FIXED_C_SPEC_SQL;
C_SQL_EMPTY_TEXT: [ ]+ -> skip;
C_SQL_TEXT: ~[\r\n]+;
//C_SQL_CONT : ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]  [cC] '+' -> skip;
C_SQL_LEADWS : {getCharPositionInLine()==0}? ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> skip,popMode;
C_SQL_END : NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 
	 [cC] '/' [Ee][nN][dD][-][Ee][Xx][Ee][Cc] WS NEWLINE-> popMode ;
C_SQL_CONT : NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [cC] '+' -> skip; 
C_SQL_EOL : NEWLINE -> popMode; 

mode FIXED_C_SPEC_X2;
C2_FACTOR2_CONT: {getCharPositionInLine()==35}? 
		~[\r\n]+ '+' [ ]+ NEWLINE;  //TODO  the continuation should not include the plus and the \r\n
//C2_FACTOR2_CONT2: {getCharPositionInLine()==35}? 
//		~[\r\n]+ NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [cC] '                             ' ;  
C2_FACTOR2: {getCharPositionInLine()==35}? 
		~[\r\n]+ ->popMode;
C2_OTHER: {getCharPositionInLine()<35}? ~('\r' | '\n') ->skip;

mode FIXED_I_SPEC;
I_FILE_NAME: {getCharPositionInLine()==6}? WORD5_WCOLON WORD5_WCOLON ;
I_FIELD_RESERVED: {getCharPositionInLine()==6}? '                        ' -> pushMode(FIXED_I_FIELD_SPEC),skip ;
I_EXT_FIELD_RESERVED: {getCharPositionInLine()==6}? '              ' -> pushMode(FIXED_I_EXT_FIELD_SPEC),skip ;
I_LOGICAL_RELATIONSHIP : {getCharPositionInLine()==15}? ('AND' | 'OR '| ' OR') ;
I_EXT_REC_RESERVED : {getCharPositionInLine()==16}? '    '  -> pushMode(FIXED_I_EXT_REC_SPEC) ;
I_SEQUENCE : {getCharPositionInLine()==16}? WORD_WCOLON WORD_WCOLON ;
I_NUMBER : {getCharPositionInLine()==18}? [ 1nN] ;
I_OPTION: {getCharPositionInLine()==19}? [ oO] ;
I_RECORD_ID_IND: {getCharPositionInLine()==20}? IND_FRAG | '**';
I_RECORD_ID_CODE: {getCharPositionInLine()==22}?  WORD5_WCOLON WORD5_WCOLON WORD5_WCOLON WORD5_WCOLON
		WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON; //TODO better lexing
I_WS : {getCharPositionInLine()>=46}? [ \t]+ -> skip  ; // skip spaces, tabs
I_COMMENTS : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines
I_EOL : NEWLINE -> popMode;

mode FIXED_I_EXT_FIELD_SPEC;
I_EXT_FILE_NAME: {getCharPositionInLine()==20}? WORD5_WCOLON WORD5_WCOLON ;
I_EXT_FILE_RESERVED: {getCharPositionInLine()==30}? '                  ' -> skip;
I_EXT_FILE_FIELDNAME: {getCharPositionInLine()==48}? WORD5_WCOLON WORD5_WCOLON WORD_WCOLON WORD_WCOLON
	WORD_WCOLON WORD_WCOLON;
I_EXT_FILE_CONTROL_LEVEL : {getCharPositionInLine()==62}? ('L'[0-9] | '  ') ;	
I_EXT_FILE_MATCHING_FIELDS: {getCharPositionInLine()==64}? ('M'[0-9] | '  ') ;
I_EXT_FILE_RESERVED2: {getCharPositionInLine()==66}? '  ' ->skip ;
I_EXT_FILE_INDICATORS: {getCharPositionInLine()==68}? IND_FRAG IND_FRAG IND_FRAG;
I_EXT_FILE_WS : {getCharPositionInLine()>=74}? [ \t]+ -> popMode,skip  ; // skip spaces, tabs

mode FIXED_I_EXT_REC_SPEC;
I_EXT_REC_ID_IND : {getCharPositionInLine()==20}? IND_FRAG ;
I_EXT_REC_WS : {getCharPositionInLine()>=22}? [ \t]+ -> popMode  ; // skip spaces, tabs

mode FIXED_I_FIELD_SPEC;
I_F_DATA_ATTR: {getCharPositionInLine()==30}?  WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
I_F_DATETIME_SEP: {getCharPositionInLine()==34}?  ~[\r\n];
I_F_DATA_FORMAT: {getCharPositionInLine()==35}?  [A-Z ];
I_F_FIELD_LOCATION: {getCharPositionInLine()==36}?  WORD5_WCOLON WORD5_WCOLON;
I_F_DECIMAL_POSITIONS: {getCharPositionInLine()==46}?  [ 0-9][ 0-9];
I_F_FIELD_NAME: {getCharPositionInLine()==48}?  WORD5_WCOLON WORD5_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
I_F_CONTROL_LEVEL : {getCharPositionInLine()==62}? ('L'[0-9] | '  ') ;
I_F_MATCHING_FIELDS: {getCharPositionInLine()==64}? ('M'[0-9] | '  ') ;
I_F_FIELD_RELATION: {getCharPositionInLine()==66}? IND_FRAG ;
I_F_FIELD_IND: {getCharPositionInLine()==68}? IND_FRAG IND_FRAG IND_FRAG ;
I_F_BLANKS: {getCharPositionInLine()==74}? '      ' -> skip;
I_F_COMMENTS : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines
I_F_EOL : NEWLINE -> popMode,popMode;

mode H_SPEC_FREE;
H_OPEN_PAREN: OPEN_PAREN;
H_CLOSE_PAREN: CLOSE_PAREN;
H_STRING_START: ['] -> pushMode(FREE_STRING_MODE) ;
H_COLON: ':';
H_ID: [#@%$*a-zA-Z] [#@\-$*a-zA-Z0-9_]*;
H_WS : [ \t]+ -> skip  ; // skip spaces, tabs, newlines
H_EOL : NEWLINE -> popMode;

mode F_SPEC_FREE;
FREE_F_OPEN_PAREN: OPEN_PAREN;
FREE_F_CLOSE_PAREN: CLOSE_PAREN;
FREE_F_STRING_START: ['] -> pushMode(FREE_STRING_MODE) ;
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
FREE_D_STRING_START: ['] -> pushMode(FREE_STRING_MODE) ;
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
fragment IND_FRAG: ('  ' | [0-9lLhH][0-9] | [kK][A-NP-Ya-np0y] | [lLmM][rR] | [rR][tT] | [uU][1-8] | [oO][a-gvA-GV] | [0-9][pP]);


