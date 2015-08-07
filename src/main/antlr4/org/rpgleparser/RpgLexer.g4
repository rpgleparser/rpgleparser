/**
 * Define a grammar called Hello
 */
lexer grammar RpgLexer;

@members {
	public boolean isEndOfToken() {
		return " (;".indexOf(_input.LA(1)) >=0;
	}
} 

// Parser Rules
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

mode DirectiveMode;
DIR_NOT: [nN][oO][tT];
DIR_DEFINED: [dD][eE][fF][iI][nN][eE][dD];
DIR_FREE: {_input.LA(-1)=='/'}? [fF][rR][eE][eE] -> pushMode(SKIP_REMAINING_WS);
DIR_ENDFREE: {_input.LA(-1)=='/'}? [eE][nN][dD] '-' [fF][rR][eE][eE] -> pushMode(SKIP_REMAINING_WS);
DIR_TITLE:{_input.LA(-1)=='/'}? ([tT][iI][tT][lL][eE]);
DIR_EJECT: {_input.LA(-1)=='/'}? [eE][jJ][eE][cC][tT] -> pushMode(SKIP_REMAINING_WS);
DIR_SPACE: {_input.LA(-1)=='/'}? [sS][pP][aA][cC][eE];
DIR_SET: {_input.LA(-1)=='/'}?  [sS][eE][tT];
DIR_RESTORE: {_input.LA(-1)=='/'}? [rR][eE][sS][tT][oO][rR][eE];
DIR_COPY: {_input.LA(-1)=='/'}? [cC][oO][pP][yY];
DIR_INCLUDE: {_input.LA(-1)=='/'}? [iI][nN][cC][lL][uU][dD][eE];
DIR_EOF: {_input.LA(-1)=='/'}? [eE][oO][fF];
DIR_DEFINE: {_input.LA(-1)=='/'}? ([dD][eE][fF][iI][nN][eE]);
DIR_UNDEFINE: {_input.LA(-1)=='/'}? ([uU][nN][dD][eE][fF][iI][nN][eE]);
DIR_IF: {_input.LA(-1)=='/'}? ([iI][fF]);
DIR_ELSE: {_input.LA(-1)=='/'}? ([eE][lL][sS][eE]);
DIR_ELSEIF: {_input.LA(-1)=='/'}? ([eE][lL][sS][eE][iI][fF]);
DIR_ENDIF: {_input.LA(-1)=='/'}? ([eE][nN][dD][iI][fF]);
DIR_Number: NUMBER -> type(NUMBER);
DIR_WhiteSpace: [ ] -> type(WS),skip;
DIR_OtherText : ~[/'"\r\n \t,()]+ ;
DIR_Comma : [,] -> skip;
DIR_Slash : [/] ;
DIR_OpenParen: [(] -> type(OPEN_PAREN);
DIR_CloseParen: [)] -> type(CLOSE_PAREN);
DIR_DblStringLiteralStart: ["] -> pushMode(InDoubleStringMode),type(StringLiteralStart) ;
DIR_StringLiteralStart: ['] -> pushMode(InStringMode),type(StringLiteralStart) ;
DIR_EOL : [ ]* NEWLINE -> type(EOL),popMode;

mode SKIP_REMAINING_WS;
DIR_FREE_OTHER_TEXT: ~[\r\n]+ -> popMode,skip;

mode EndOfSourceMode;
EOS_Text : ~[\r\n]+ ;
EOS_EOL : NEWLINE -> type(EOL); 


// -----------------  ---------------------
mode OpCode;
OP_WS: {getCharPositionInLine()>5}? [ \t]+ -> skip;
OP_ACQ: [Aa][Cc][Qq] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_BEGSR: [Bb][Ee][Gg][Ss][Rr] {isEndOfToken()}?-> mode(FREE);
OP_CALLP: [Cc][Aa][Ll][Ll][Pp] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_CHAIN: [Cc][Hh][Aa][Ii][Nn] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_CLEAR: [Cc][Ll][Ee][Aa][Rr] {isEndOfToken()}?-> mode(FREE);
OP_CLOSE: [Cc][Ll][Oo][Ss][Ee] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_COMMIT: [Cc][Oo][Mm][Mm][Ii][Tt] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_DEALLOC: [Dd][Ee][Aa][Ll][Ll][Oo][Cc] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_DELETE: [Dd][Ee][Ll][Ee][Tt][Ee] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_DOU: [Dd][Oo][Uu] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_DOW: [Dd][Oo][Ww] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_DSPLY: [Dd][Ss][Pp][Ll][Yy] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_DUMP: [Dd][Uu][Mm][Pp] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_ELSE: [Ee][Ll][Ss][Ee] {isEndOfToken()}?-> mode(FREE);
OP_ELSEIF: [Ee][Ll][Ss][Ee][Ii][Ff] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_ENDDO: [Ee][Nn][Dd][Dd][Oo] {isEndOfToken()}?-> mode(FREE);
OP_ENDFOR: [Ee][Nn][Dd][Ff][Oo][Rr] {isEndOfToken()}?-> mode(FREE);
OP_ENDIF: [Ee][Nn][Dd][Ii][Ff] {isEndOfToken()}?-> mode(FREE);
OP_ENDMON: [Ee][Nn][Dd][Mm][Oo][Nn] {isEndOfToken()}?-> mode(FREE);
OP_ENDSL: [Ee][Nn][Dd][Ss][Ll] {isEndOfToken()}?-> mode(FREE);
OP_ENDSR: [Ee][Nn][Dd][Ss][Rr] {isEndOfToken()}?-> mode(FREE);
OP_EVAL: [Ee][Vv][Aa][Ll] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_EVALR: [Ee][Vv][Aa][Ll][Rr] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_EVAL_CORR: [Ee][Vv][Aa][Ll][-][Cc][Oo][Rr][Rr] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_EXCEPT: [Ee][Xx][Cc][Ee][Pp][Tt] {isEndOfToken()}?-> mode(FREE);
OP_EXFMT: [Ee][Xx][Ff][Mm][Tt] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_EXSR: [Ee][Xx][Ss][Rr] {isEndOfToken()}?-> mode(FREE);
OP_FEOD: [Ff][Ee][Oo][Dd] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_FOR: [Ff][Oo][Rr] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_FORCE: [Ff][Oo][Rr][Cc][Ee] {isEndOfToken()}?-> mode(FREE);
OP_IF: [Ii][Ff] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_IN: [Ii][Nn] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_ITER: [Ii][Tt][Ee][Rr] {isEndOfToken()}?-> mode(FREE);
OP_LEAVE: [Ll][Ee][Aa][Vv][Ee] {isEndOfToken()}?-> mode(FREE);
OP_LEAVESR: [Ll][Ee][Aa][Vv][Ee][Ss][Rr] {isEndOfToken()}?-> mode(FREE);
OP_MONITOR: [Mm][Oo][Nn][Ii][Tt][Oo][Rr] {isEndOfToken()}?-> mode(FREE);
OP_NEXT: [Nn][Ee][Xx][Tt] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_ON_ERROR: [Oo][Nn][-][Ee][Rr][Rr][Oo][Rr] {isEndOfToken()}?-> mode(FREE);
OP_OPEN: [Oo][Pp][Ee][Nn] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_OTHER: [Oo][Tt][Hh][Ee][Rr] {isEndOfToken()}?-> mode(FREE);
OP_OUT: [Oo][Uu][Tt] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_POST: [Pp][Oo][Ss][Tt] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_READ: [Rr][Ee][Aa][Dd] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_READC: [Rr][Ee][Aa][Dd][Cc] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_READE: [Rr][Ee][Aa][Dd][Ee] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_READP: [Rr][Ee][Aa][Dd][Pp] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_READPE: [Rr][Ee][Aa][Dd][Pp][Ee] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_REL: [Rr][Ee][Ll] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_RESET: [Rr][Ee][Ss][Ee][Tt] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_RETURN: [Rr][Ee][Tt][Uu][Rr][Nn] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_ROLBK: [Rr][Oo][Ll][Bb][Kk] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_SELECT: [Ss][Ee][Ll][Ee][Cc][Tt] {isEndOfToken()}?-> mode(FREE);
OP_SETGT: [Ss][Ee][Tt][Gg][Tt] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_SETLL: [Ss][Ee][Tt][Ll][Ll] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_SORTA: [Ss][Oo][Rr][Tt][Aa] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_TEST: [Tt][Ee][Ss][Tt] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_UNLOCK: [Uu][Nn][Ll][Oo][Cc][Kk] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_UPDATE: [Uu][Pp][Dd][Aa][Tt][Ee] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_WHEN: [Ww][Hh][Ee][Nn] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_WRITE: [Ww][Rr][Ii][Tt][Ee] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_XML_INTO: [Xx][Mm][Ll][-][Ii][Nn][Tt][Oo] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_XML_SAX: [Xx][Mm][Ll][-][Ss][Aa][Xx] {isEndOfToken()}?-> mode(FREE),pushMode(FreeOpExtender);
OP_NoSpace: -> skip,mode(FREE),pushMode(FreeOpExtender);

mode FREE;
//OP_COMMIT: [Cc][Oo][Mm][Mm][Ii][Tt] -> mode(FREE),pushMode(FreeOpExtender);
DS_Standalone : [dD] [cC] [lL] '-' [sS] ;//-> pushMode(F_SPEC_FREE);
DS_DataStructureStart : [dD] [cC] [lL] '-' [dD][sS] ;//-> pushMode(F_SPEC_FREE);
DS_DataStructureEnd : [eE] [nN] [dD] '-' [dD][sS] ;//-> pushMode(F_SPEC_FREE);
DS_PrototypeStart : [dD] [cC] [lL] '-' [pP][rR] ;//-> pushMode(F_SPEC_FREE);
DS_PrototypeEnd : [eE] [nN] [dD] '-' [pP][rR] ;//-> pushMode(F_SPEC_FREE);
DS_Parm : [dD] [cC] [lL] '-' [pP][aA][rR][mM] ;
DS_SubField : [dD] [cC] [lL] '-' [sS][uU][bB][fF] ;
DS_ProcedureInterfaceStart : [dD] [cC] [lL] '-' [pP][iI] ;//-> pushMode(F_SPEC_FREE);
DS_ProcedureInterfaceEnd : [eE] [nN] [dD] '-' [pP][iI] ;//-> pushMode(F_SPEC_FREE);
DS_ProcedureStart : [dD] [cC] [lL] '-' [pP][rR][oO][cC] ;//-> pushMode(F_SPEC_FREE);
DS_ProcedureEnd : [eE] [nN] [dD] '-' [pP][rR][oO][cC] ;//-> pushMode(F_SPEC_FREE);
DS_Constant : [dD] [cC] [lL] '-' [cC] ;//-> pushMode(F_SPEC_FREE);

FS_FreeFile : [dD] [cC] [lL] '-' [fF] ;//-> pushMode(F_SPEC_FREE);
H_SPEC : [cC] [tT] [lL] '-' [oO][pP][tT];
FREE_CONT: '...' [ ]* NEWLINE ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n][ ]+ {setText("...");} -> type(CONTINUATION);
FREE_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN); // skip comments after 80
EXEC_SQL: [Ee][Xx][Ee][Cc][ ]+[Ss][Qq][Ll]-> pushMode(SQL_MODE) ;

// Built In functions
BIF_ABS: '%'[aA][bB][sS];
BIF_ADDR: '%'[aA][dD][dD][rR];
BIF_ALLOC: '%'[aA][lL][lL][oO][cC];
BIF_BITAND: '%'[bB][iI][tT][aA][nN][dD];
BIF_BITNOT: '%'[bB][iI][tT][nN][oO][tT];
BIF_BITOR: '%'[bB][iI][tT][oO][rR];
BIF_BITXOR: '%'[bB][iI][tT][xX][oO][rR];
BIF_CHAR: '%'[cC][hH][aA][rR];
BIF_CHECK: '%'[cC][hH][eE][cC][kK];
BIF_CHECKR: '%'[cC][hH][eE][cC][kK][rR];
BIF_DATE: '%'[dD][aA][tT][eE];
BIF_DAYS: '%'[dD][aA][yY][sS];
BIF_DEC: '%'[dD][eE][cC];
BIF_DECH: '%'[dD][eE][cC][hH];
BIF_DECPOS: '%'[dD][eE][cC][pP][oO][sS];
BIF_DIFF: '%'[dD][iI][fF][fF];
BIF_DIV: '%'[dD][iI][vV];
BIF_EDITC: '%'[eE][dD][iI][tT][cC];
BIF_EDITFLT: '%'[eE][dD][iI][tT][fF][lL][tT];
BIF_EDITW: '%'[eE][dD][iI][tT][wW];
BIF_ELEM: '%'[eE][lL][eE][mM];
BIF_EOF: '%'[eE][oO][fF];
BIF_EQUAL: '%'[eE][qQ][uU][aA][lL];
BIF_ERROR: '%'[eE][rR][rR][oO][rR];
BIF_FIELDS: '%'[fF][iI][eE][lL][dD][sS];
BIF_FLOAT: '%'[fF][lL][oO][aA][tT];
BIF_FOUND: '%'[fF][oO][uU][nN][dD];
BIF_GRAPH: '%'[gG][rR][aA][pP][hH];
BIF_HANDLER: '%'[hH][aA][nN][dD][lL][eE][rR];
BIF_HOURS: '%'[hH][oO][uU][rR][sS];
BIF_INT: '%'[iI][nN][tT];
BIF_INTH: '%'[iI][nN][tT][hH];
BIF_KDS: '%'[kK][dD][sS];
BIF_LEN: '%'[lL][eE][nN];
BIF_LOOKUP: '%'[lL][oO][oO][kK][uU][pP];
BIF_LOOKUPLT: '%'[lL][oO][oO][kK][uU][pP][lL][tT];
BIF_LOOKUPLE: '%'[lL][oO][oO][kK][uU][pP][lL][eE];
BIF_LOOKUPGT: '%'[lL][oO][oO][kK][uU][pP][gG][tT];
BIF_LOOKUPGE: '%'[lL][oO][oO][kK][uU][pP][gG][eE];
BIF_MINUTES: '%'[mM][iI][nN][uU][tT][eE][sS];
BIF_MONTHS: '%'[mM][oO][nN][tT][hH][sS];
BIF_MSECONDS: '%'[mM][sS][eE][cC][oO][nN][dD][sS];
BIF_NULLIND: '%'[nN][uU][lL][iI][nN][dD];
BIF_OCCUR: '%'[oO][cC][uU][rR];
BIF_OPEN: '%'[oO][pP][eE][nN];
BIF_PADDR: '%'[pP][aA][dD][dD][rR];
BIF_PARMS: '%'[pP][aA][rR][mM][sS];
BIF_PARMNUM: '%'[pP][aA][rR][mM][nN][uU][mM];
BIF_REALLOC: '%'[rR][eE][aA][lL][lL][oO][cC];
BIF_REM: '%'[rR][eE][mM];
BIF_REPLACE: '%'[rR][eE][pP][lL][aA][cC][eE];
BIF_SCAN: '%'[sS][cC][aA][nN];
BIF_SCANRPL: '%'[sS][cC][aA][nN][rR][pP][lL];
BIF_SECONDS: '%'[sS][eE][cC][oO][nN][dD];
BIF_SHTDN: '%'[sS][hH][tT][dD][nN];
BIF_SIZE: '%'[sS][iI][zZ][eE];
BIF_SQRT: '%'[sS][qQ][rR][tT];
BIF_STATUS: '%'[sS][tT][aA][tT][uU][sS];
BIF_STR: '%'[sS][tT][rR];
BIF_SUBARR: '%'[sS][uU][bB][aA][rR][rR];
BIF_SUBDT: '%'[sS][uU][bB][dD][tT];
BIF_SUBST: '%'[sS][uU][bB][sS][tT];
BIF_THIS: '%'[tT][hH][iI][sS];
BIF_TIME: '%'[tT][iI][mM][eE];
BIF_TIMESTAMP: '%'[tT][iI][mM][eE][sS][tT][aA][mM][pP];
BIF_TLOOKUP: '%'[tT][lL][oO][oO][kK][uU][pP];
BIF_TLOOKUPLT: '%'[tT][lL][oO][oO][kK][uU][pP][lL][tT];
BIF_TLOOKUPLE: '%'[tT][lL][oO][oO][kK][uU][pP][lL][eE];
BIF_TLOOKUPGT: '%'[tT][lL][oO][oO][kK][uU][pP][gG][tT];
BIF_TLOOKUPGE: '%'[tT][lL][oO][oO][kK][uU][pP][gG][eE];
BIF_TRIM: '%'[tT][rR][iI][mM];
BIF_TRIML: '%'[tT][rR][iI][mM][lL];
BIF_TRIMR: '%'[tT][rR][iI][mM][rR];
BIF_UCS2: '%'[uU][cC][sS]'2';
BIF_UNS: '%'[uU][nN][sS];
BIF_UNSH: '%'[uU][nN][sS][hH];
BIF_XFOOT: '%'[xX][fF][oO][oO][tT];
BIF_XLATE: '%'[xX][lL][aA][tT][eE];
BIF_XML: '%'[xX][mM][lL];
BIF_YEARS: '%'[yY][eE][aA][rR][sS];

// Symbolic Constants
SPLAT_ALL: '*'[aA][lL][lL];
SPLAT_NONE: '*'[nN][oO][nN][eE];
SPLAT_YES: '*'[yY][eE][sS];
SPLAT_NO: '*'[nN][oO];
SPLAT_ILERPG: '*'[iI][lL][eE][rR][pP][gG];
SPLAT_COMPAT: '*'[cC][oO][mM][pP][aA][tT];
SPLAT_CRTBNDRPG: '*'[cC][rR][tT][bB][nN][dD][rR][pP][gG];
SPLAT_CRTRPGMOD: '*'[cC][rR][tT][rR][pP][gG][mM][oO][dD];
SPLAT_VRM: '*'[vV][0-9][rR][0-9][mM][0-9];
SPLAT_ALLG: '*'[aA][lL][lL][gG];
SPLAT_ALLU: '*'[aA][lL][lL][uU];
SPLAT_ALLTHREAD: '*'[aA][lL][lL][tT][hH][rR][eE][aA][dD];
SPLAT_ALLX: '*'[aA][lL][lL][xX];
SPLAT_BLANKS: ('*'[bB][lL][aA][nN][kK][sS] | '*'[bB][lL][aA][nN][kK]);
SPLAT_CANCL: '*'[cC][aA][nN][cC][lL];
SPLAT_CYMD: '*'[cC][yY][mM][dD];
SPLAT_CMDY: '*'[cC][mM][dD][yY];
SPLAT_CDMY: '*'[cC][dD][mM][yY];
SPLAT_MDY: '*'[mM][dD][yY];
SPLAT_DMY: '*'[dD][mM][yY];
SPLAT_DFT: '*'[dD][fF][tT];
SPLAT_YMD: '*'[yY][mM][dD];
SPLAT_JUL: '*'[jJ][uU][lL];
SPLAT_JAVA: '*'[jJ][aA][vV][aA];
SPLAT_ISO: '*'[iI][sS][oO];
SPLAT_ISO0: '*'[iI][sS][oO]'0';
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
SPLAT_INDICATOR: ('*'[iI][nN][0-9][0-9] | '*'[iI][nN]'('[0-9][0-9]')');
SPLAT_INZSR: '*'[iI][nN][zZ][sS][rR];
SPLAT_IN: '*'[iI][nN];
SPLAT_INPUT: '*'[iI][nN][pP][uU][tT];
SPLAT_OUTPUT: '*'[oO][uU][tT][pP][uU][tT];
SPLAT_JOBRUN: '*'[jJ][oO][bB][rR][uU][nN];
SPLAT_JOB: '*'[jJ][oO][bB];
SPLAT_LDA: '*'[lL][dD][aA];
SPLAT_LIKE: '*'[lL][iI][kK][eE];
SPLAT_LONGJUL: '*'[lL][oO][nN][gG][jJ][uU][lL];
SPLAT_LOVAL: '*'[lL][oO][vV][aA][lL];
SPLAT_KEY: '*'[kK][eE][yY];
SPLAT_MONTH: '*'[mM][oO][nN][tT][hH];
SPLAT_NEXT: '*'[nN][eE][xX][tT];
SPLAT_NOIND: '*'[nN][oO][iI][nN][dD];
SPLAT_NOKEY: '*'[nN][oO][kK][eE][yY];
SPLAT_NULL: '*'[nN][uU][lL][lL];
SPLAT_OFL: '*'[oO][fF][lL];
SPLAT_ON: '*'[oO][nN];
SPLAT_ONLY: '*'[oO][nN][lL][yY];
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
SPLAT_DATA: '*'[dD][aA][tT][aA];
SPLAT_ASTFILL: '*'[aA][sS][tT][fF][iI][lL];
SPLAT_CURSYM: '*'[cC][uU][rR][sS][yY][mM];
SPLAT_MAX: '*'[mM][aA][xX];
SPLAT_LOCK: '*'[lL][oO][cC][kK];
SPLAT_PROGRAM: '*'[pP][rR][oO][gG][rR][aA][mM];
SPLAT_EXTDESC: '*'[eE][xX][tT][dD][eE][sS][cC];

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

//DataType
CHAR: [Cc][Hh][Aa][Rr];
VARCHAR: [Va][Aa][Rr][Cc][Hh][Aa][Rr];
UCS2: [Uu][Cc][Ss][2];
DATE_: [Dd][Aa][Tt][Ee];
VARUCS2: [Va][Aa][Rr][Uu][Cc][Ss][2];
GRAPH: [Gg][Rr][Aa][Pp][Hh];
VARGRAPH: [Va][Aa][Rr][Gg][Rr][Aa][Pp][Hh];
IND: [Ii][Nn][Dd];
PACKED: [Pp][Aa][Cc][Kk][Ee][Dd];
ZONED: [Zz][Oo][Nn][Ee][Dd];
BINDEC: [Bb][Ii][Nn][Dd][Ee][Cc];
INT: [Ii][Nn][Tt];
UNS: [Uu][Nn][Ss];
FLOAT: [Ff][Ll][Oo][Aa][Tt];
TIME: [Tt][Ii][Mm][Ee];
TIMESTAMP: [Tt][Ii][Mm][Ee][Ss][Tt][Aa][Mm][Pp];
POINTER: [Pp][Oo][Ii][Nn][Tt][Ee][Rr];
OBJECT: [Oo][Bb][Jj][Ee][Cc][Tt];

// More Keywords
KEYWORD_ALIAS : [Aa][Ll][Ii][Aa][Ss];
KEYWORD_ALIGN : [Aa][Ll][Ii][Gg][Nn];
KEYWORD_ALT : [Aa][Ll][Tt];
KEYWORD_ALTSEQ : [Aa][Ll][Tt][Ss][Ee][Qq];
KEYWORD_ASCEND : [Aa][Ss][Cc][Ee][Nn][Dd];
KEYWORD_BASED : [Bb][Aa][Ss][Ee][Dd];
KEYWORD_CCSID : [Cc][Cc][Ss][Ii][Dd];
KEYWORD_CLASS : [Cc][Ll][Aa][Ss][Ss];
KEYWORD_CONST : [Cc][Oo][Nn][Ss][Tt];
KEYWORD_CTDATA : [Cc][Tt][Dd][Aa][Tt][Aa];
KEYWORD_DATFMT : [Dd][Aa][Tt][Ff][Mm][Tt];
KEYWORD_DESCEND : [Dd][Ee][Ss][Cc][Ee][Nn][Dd];
KEYWORD_DIM : [Dd][Ii][Mm];
KEYWORD_DTAARA : [Dd][Tt][Aa][Aa][Rr][Aa];
KEYWORD_EXPORT : [Ee][Xx][Pp][Oo][Rr][Tt];
KEYWORD_EXT : [Ee][Xx][Tt];
KEYWORD_EXTFLD : [Ee][Xx][Tt][Ff][Ll][Dd];
KEYWORD_EXTFMT : [Ee][Xx][Tt][Ff][Mm][Tt];
KEYWORD_EXTNAME : [Ee][Xx][Tt][Nn][Aa][Mm][Ee];
KEYWORD_EXTPGM : [Ee][Xx][Tt][Pp][Gg][Mm];
KEYWORD_EXTPROC : [Ee][Xx][Tt][Pp][Rr][Oo][Cc];
KEYWORD_FROMFILE : [Ff][Rr][Oo][Mm][Ff][Ii][Ll][Ee];
KEYWORD_IMPORT : [Ii][Mm][Pp][Oo][Rr][Tt];
KEYWORD_INZ : [Ii][Nn][Zz];
KEYWORD_LEN : [Ll][Ee][Nn];
KEYWORD_LIKE : [Ll][Ii][Kk][Ee];
KEYWORD_LIKEDS : [Ll][Ii][Kk][Ee][Dd][Ss];
KEYWORD_LIKEFILE : [Ll][Ii][Kk][Ee][Ff][Ii][Ll][Ee];
KEYWORD_LIKEREC : [Ll][Ii][Kk][Ee][Rr][Ee][Cc];
KEYWORD_NOOPT : [Nn][Oo][Oo][Pp][Tt];
KEYWORD_OCCURS : [Oo][Cc][Cc][Uu][Rr][Ss];
KEYWORD_OPDESC : [Oo][Pp][Dd][Ee][Ss][Cc];
KEYWORD_OPTIONS : [Oo][Pp][Tt][Ii][Oo][Nn][Ss];
KEYWORD_OVERLAY : [Oo][Vv][Ee][Rr][Ll][Aa][Yy];
KEYWORD_PACKEVEN : [Pp][Aa][Cc][Kk][Ee][Vv][Ee][Nn];
KEYWORD_PERRCD : [Pp][Ee][Rr][Rr][Cc][Dd];
KEYWORD_PREFIX : [Pp][Rr][Ee][Ff][Ii][Xx];
KEYWORD_POS : [Pp][Oo][Ss];
KEYWORD_PROCPTR : [Pp][Rr][Oo][Cc][Pp][Tt][Rr];
KEYWORD_QUALIFIED : [Qq][Uu][Aa][Ll][Ii][Ff][Ii][Ee][Dd];
KEYWORD_RTNPARM : [Rr][Tt][Nn][Pp][Aa][Rr][Mm];
KEYWORD_STATIC : [Ss][Tt][Aa][Tt][Ii][Cc];
KEYWORD_TEMPLATE : [Tt][Ee][Mm][Pp][Ll][Aa][Tt][Ee];
KEYWORD_TIMFMT : [Tt][Ii][Mm][Ff][Mm][Tt];
KEYWORD_TOFILE : [Tt][Oo][Ff][Ii][Ll][Ee];
KEYWORD_VALUE : [Vv][Aa][Ll][Uu][Ee];
KEYWORD_VARYING : [Vv][Aa][Rr][Yy][Ii][Nn][Gg];
// File spec keywords
KEYWORD_BLOCK : [bB][lL][oO][cC][kK]; 
KEYWORD_COMMIT : [cC][oO][mM][mM][iI][tT]; 
KEYWORD_DEVID : [dD][eE][vV][iI][dD]; 
KEYWORD_EXTDESC : [eE][xX][tT][dD][eE][sS][cC];
KEYWORD_EXTFILE  : [eE][xX][tT][fF][iI][lL][eE]; 
KEYWORD_EXTIND  : [eE][xX][tT][iI][nN][dD]; 
KEYWORD_EXTMBR  : [eE][xX][tT][mM][bB][rR]; 
KEYWORD_FORMLEN : [fF][oO][rR][mM][lL][eE][nN];
KEYWORD_FORMOFL : [fF][oO][rR][mM][oO][fF][lL];
KEYWORD_IGNORE : [iI][gG][nN][oO][rR][eE];
KEYWORD_INCLUDE : [iI][nN][cC][lL][uU][dD][eE];
KEYWORD_INDDS : [iI][nN][dD][dD][sS];
KEYWORD_INFDS : [iI][nN][fF][dD][sS];
KEYWORD_INFSR : [iI][nN][fF][sS][rR];
KEYWORD_KEYLOC : [kK][eE][yY][lL][oO][cC];
KEYWORD_MAXDEV : [mM][aA][xX][dD][eE][vV];
KEYWORD_OFLIND : [oO][fF][lL][iI][nN][dD];
KEYWORD_PASS : [pP][aA][sS][sS];
KEYWORD_PGMNAME : [pP][gG][mM][nN][aA][mM][eE];
KEYWORD_PLIST : [pP][lL][iI][sS][tT];
KEYWORD_PRTCTL : [pP][rR][tT][cC][tT][lL];
KEYWORD_RAFDATA : [rR][aA][fF][dD][aA][tT][aA];
KEYWORD_RECNO : [rR][eE][cC][nN][oO];
KEYWORD_RENAME : [rR][eE][nN][aA][mM][eE];
KEYWORD_SAVEDS : [sS][aA][vV][eE][dD][sS];
KEYWORD_SAVEIND : [sS][aA][vV][eE][iI][nN][dD];
KEYWORD_SFILE : [sS][fF][iI][lL][eE];
KEYWORD_SLN : [sS][lL][nN];
KEYWORD_USROPN : [uU][sS][rR][oO][pP][nN];
KEYWORD_DISK : [dD][iI][sS][kK];
KEYWORD_WORKSTN : [wW][oO][rR][kK][sS][tT][nN];
KEYWORD_PRINTER : [pP][rR][iI][nN][tT][eE][rR];
KEYWORD_SPECIAL : [sS][pP][eE][cC][iI][aA][lL];
KEYWORD_KEYED : [kK][eE][yY][eE][dD];
KEYWORD_USAGE : [uU][sS][aA][gG][eE];
// 
AMPERSAND: '&';

// Boolean operators
AND : [aA] [nN] [dD] ;
OR : [oO] [rR] ;
NOT : [nN] [oO] [tT] ;

// Arithmetical Operators
PLUS : '+' ;
MINUS : '-' ;
EXP : '**' ;
//MULT : '*' ;
ARRAY_REPEAT: {_input.LA(2) == ')' && _input.LA(-1) == '('}? '*' ;
MULT_NOSPACE: {_input.LA(2) != 32}? '*';
MULT: {_input.LA(2) == 32}? '*' ;
DIV : '/' ;

// Assignment Operators
CPLUS : '+=' ;
CMINUS : '-=' ;
CMULT : '*=' ;
CDIV : '/=' ;
CEXP : '**=' ;

// Comparison Operators
GT : '>' ;
LT : '<' ;
GE : '>=' ;
LE : '<=' ;
NE : '<>' ;

//--------------
//OP_E: '(' [aAdDeEhHmMnNpPrRtTzZ][aAdDeEhHmMnNpPrRtTzZ]? ')';
FREE_OPEN_PAREN: OPEN_PAREN -> type(OPEN_PAREN);
FREE_CLOSE_PAREN: CLOSE_PAREN -> type(CLOSE_PAREN);
FREE_DOT: '.';
FREE_NUMBER_CONT: NUMBER {_modeStack.peek()==FIXED_DefSpec}? -> pushMode(NumberContinuation),type(NUMBER);
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
FREE_CONTINUATION : {_modeStack.peek()!=FIXED_CalcSpec && _modeStack.peek()!=FIXED_DefSpec}? '...' WS* NEWLINE -> type(CONTINUATION);
C_FREE_CONTINUATION_DOTS : {_modeStack.peek()==FIXED_CalcSpec}? '...' WS* NEWLINE 
	(~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [cC] ~[*] '                            ') {setText("...");} -> type(CONTINUATION);
D_FREE_CONTINUATION_DOTS : {_modeStack.peek()==FIXED_DefSpec}? '...' WS* NEWLINE 
	(~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [dD] ~[*] '                            ') {setText("...");} -> type(CONTINUATION);
C_FREE_CONTINUATION: {_modeStack.peek()==FIXED_CalcSpec}? NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [cC] ~[*] '                            ' -> skip;
D_FREE_CONTINUATION: {_modeStack.peek() == FIXED_DefSpec}? NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [dD] ~[*] '                            ' -> skip;
F_FREE_CONTINUATION: {_modeStack.peek() == FIXED_FileSpec}? NEWLINE 
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [fF] ~[*] '                            ' -> skip;
FREE_LEAD_WS5 :  {getCharPositionInLine()==0}? '     ' -> skip;
FREE_LEAD_WS5_Comments :  {getCharPositionInLine()==0}?~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] -> channel(HIDDEN);
FREE_FREE_SPEC : {getCharPositionInLine()==5}? [  ] -> skip;
	
C_FREE_NEWLINE: {_modeStack.peek()==FIXED_CalcSpec}? NEWLINE -> popMode,popMode;
O_FREE_NEWLINE: {_modeStack.peek()==FIXED_OutputSpec_PGMFIELD}? NEWLINE -> type(EOL),popMode,popMode,popMode;
D_FREE_NEWLINE: {_modeStack.peek() == FIXED_DefSpec}? NEWLINE -> type(EOL),popMode,popMode;
F_FREE_NEWLINE: {_modeStack.peek() == FIXED_FileSpec}? NEWLINE -> type(EOL),popMode,popMode;
FREE_NEWLINE: {_modeStack.peek()!=FIXED_CalcSpec}? NEWLINE -> skip,popMode;
FREE_SEMI: SEMI -> popMode, pushMode(FREE_ENDED);  //Captures // immediately following the semi colon

mode NumberContinuation;
NumberContinuation_CONTINUATION: ([ ]* NEWLINE)   
	~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] [dD] ~[*] '                            ' [ ]* -> skip;
NumberPart: NUMBER -> popMode;
NumberContinuation_ANY: -> popMode,skip;

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

mode FixedOpCodes; //Referenced (not used)
OP_ADD: [aA][dD][dD];
OP_ADDDUR: OP_ADD [dD][uU][rR];
OP_ALLOC: [aA][lL][lL][oO][cC];
OP_ANDxx: [aA][nN][dD][0-9][0-9];
OP_ANDEQ: [aA][nN][dD][eE][qQ];
OP_ANDNE: [aA][nN][dD][nN][eE];
OP_ANDLE: [aA][nN][dD][lL][eE];
OP_ANDLT: [aA][nN][dD][lL][tT];
OP_ANDGE: [aA][nN][dD][gG][eE];
OP_ANDGT: [aA][nN][dD][gG][tT];
OP_BITOFF: [bB][iI][tT][oO][fF][fF];
OP_BITON: [bB][iI][tT][oO][nN];
OP_CABxx: [cc][aA][bB][0-9][0-9];
OP_CABEQ: [cC][aA][bB][eE][qQ];
OP_CABNE: [cC][aA][bB][nN][eE];
OP_CABLE: [cC][aA][bB][lL][eE];
OP_CABLT: [cC][aA][bB][lL][tT];
OP_CABGE: [cC][aA][bB][gG][eE];
OP_CABGT: [cC][aA][bB][gG][tT];
OP_CALL: [Cc][Aa][Ll][Ll];
OP_CALLB: OP_CALL [bB];
OP_CASEQ: [cC][aA][sS][eE][qQ];
OP_CASNE: [cC][aA][sS][nN][eE];
OP_CASLE: [cC][aA][sS][lL][eE];
OP_CASLT: [cC][aA][sS][lL][tT];
OP_CASGE: [cC][aA][sS][gG][eE];
OP_CASGT: [cC][aA][sS][gG][tT];
OP_CAS: [cC][aA][sS];
OP_CAT: [cC][aA][tT];
//OP_CHAIN: [cC][hH][aA][iI][nN];
OP_CHECK: [cC][hH][eE][cC][kK];
OP_CHECKR: [cC][hH][eE][cC][kK][rR];
//OP_CLEAR: [cC][lL][eE][aA][rR];
//OP_CLOSE: [cC][lL][oO][sS][eE];
//OP_COMMIT: [cC][oO][mM][mM][iI][tT];
OP_COMP: [cC][oO][mM][pP];
//OP_DEALLOC: [dD][eE][aA][lL][lL][oO][cC];
OP_DEFINE: [dD][eE][fF][iI][nN][eE];
//OP_DELETE: [dD][eE][lL][eE][tT][eE];
OP_DIV: [dD][iI][vV];
OP_DO: [dD][oO];
//OP_DOU: [dD][oO][uU];
OP_DOUEQ: [dD][oO][uU][eE][qQ];
OP_DOUNE: [dD][oO][uU][nN][eE];
OP_DOULE: [dD][oO][uU][lL][eE];
OP_DOULT: [dD][oO][uU][lL][tT];
OP_DOUGE: [dD][oO][uU][gG][eE];
OP_DOUGT: [dD][oO][uU][gG][tT];
//OP_DOW: [dD][oO][wW];
OP_DOWEQ: [dD][oO][wW][eE][qQ];
OP_DOWNE: [dD][oO][wW][nN][eE];
OP_DOWLE: [dD][oO][wW][lL][eE];
OP_DOWLT: [dD][oO][wW][lL][tT];
OP_DOWGE: [dD][oO][wW][gG][eE];
OP_DOWGT: [dD][oO][wW][gG][tT];
//OP_DSPLY: [dD][sS][pP][lL][yY];
//OP_DUMP: [dD][uU][mM][pP];
//OP_ELSE: [eE][lL][sS][eE];
//OP_ELSEIF: [eE][lL][sS][eE][iI][fF];
OP_END: [eE][nN][dD];
OP_ENDCS: [eE][nN][dD][cC][sS];
//OP_ENDDO: [eE][nN][dD][dD][oO];
//OP_ENDFOR: [eE][nN][dD][fF][oO][rR];
//OP_ENDIF: [eE][nN][dD][iI][fF];
//OP_ENDMON: [eE][nN][dD][mM][oO][nN];
//OP_ENDSL: [eE][nN][dD][sS][lL];
//OP_ENDSR: [eE][nN][dD][sS][rR];
//OP_EVAL: [eE][vV][aA][lL];
//OP_EVAL-CORR: [eE][vV][aA][lL]'-'[cC][oO][rR][rR];
//OP_EVALR: [eE][vV][aA][lL][rR];
//OP_EXCEPT: [eE][xX][cC][eE][pP][tT];
//OP_EXFMT: [eE][xX][fF][mM][tT];
//OP_EXSR: [eE][xX][sS][rR];
OP_EXTRCT: [eE][xX][tT][rR][cC][tT];
//OP_FEOD: [fF][eE][oO][dD];
//OP_FOR: [fF][oO][rR];
//OP_FORCE: [fF][oO][rR][cC][eE];
OP_GOTO: [gG][oO][tT][oO];
//OP_IF: [iI][fF];
OP_IFEQ: [iI][fF][eE][qQ];
OP_IFNE: [iI][fF][nN][eE];
OP_IFLE: [iI][fF][lL][eE];
OP_IFLT: [iI][fF][lL][tT];
OP_IFGE: [iI][fF][gG][eE];
OP_IFGT: [iI][fF][gG][tT];
//OP_IN: [iI][nN];
//OP_ITER: [iI][tT][eE][rR];
OP_KFLD: [kK][fF][lL][dD];
OP_KLIST: [kK][lL][iI][sS][tT];
//OP_LEAVE: [lL][eE][aA][vV][eE];
//OP_LEAVESR: [lL][eE][aA][vV][eE][sS][rR];
OP_LOOKUP: [lL][oO][oO][kK][uU][pP];
OP_MHHZO: [mM][hH][hH][zZ][oO];
OP_MHLZO: [mM][hH][lL][zZ][oO];
OP_MLHZO: [mM][lL][hH][zZ][oO];
OP_MLLZO: [mM][lL][lL][zZ][oO];
//OP_MONITOR: [mM][oO][nN][iI][tT][oO][rR];
OP_MOVE: [mM][oO][vV][eE];
OP_MOVEA: [mM][oO][vV][eE][aA];
OP_MOVEL: [mM][oO][vV][eE][lL];
OP_MULT: [mM][uU][lL][tT];
OP_MVR: [mM][vV][rR];
//OP_NEXT: [nN][eE][xX][tT];
OP_OCCUR: [oO][cC][cC][uU][rR];
//OP_ON_ERROR: [oO][nN]'-'[eE][rR][rR][oO][rR];
//OP_OPEN: [oO][pP][eE][nN];
OP_OREQ: [oO][rR][eE][qQ];
OP_ORNE: [oO][rR][nN][eE];
OP_ORLE: [oO][rR][lL][eE];
OP_ORLT: [oO][rR][lL][tT];
OP_ORGE: [oO][rR][gG][eE];
OP_ORGT: [oO][rR][gG][tT];
//OP_OTHER: [oO][tT][hH][eE][rR];
//OP_OUT: [oO][uU][tT];
OP_PARM: [pP][aA][rR][mM];
OP_PLIST: [pP][lL][iI][sS][tT];
//OP_POST: [pP][oO][sS][tT];
//OP_READ: [rR][eE][aA][dD];
//OP_READC: [rR][eE][aA][dD][cC];
//OP_READE: [rR][eE][aA][dD][eE];
//OP_READP: [rR][eE][aA][dD][pP];
//OP_READPE: [rR][eE][aA][dD][pP][eE];
OP_REALLOC: [rR][eE][aA][lL][lL][oO][cC];
//OP_REL: [rR][eE][lL];
//OP_RESET: [rR][eE][sS][eE][tT];
//OP_RETURN: [rR][eE][tT][uU][rR][nN];
//OP_ROLBK: [rR][oO][lL][bB][kK];
OP_SCAN: [sS][cC][aA][nN];
//OP_SELECT: [sS][eE][lL][eE][cC][tT];
//OP_SETGT: [sS][eE][tT][gG][tT];
//OP_SETLL: [sS][eE][tT][lL][lL];
OP_SETOFF: [sS][eE][tT][oO][fF][fF];
OP_SETON: [sS][eE][tT][oO][nN];
OP_SHTDN: [sS][hH][tT][dD][nN];
//OP_SORTA: [sS][oO][rR][tT][aA];
OP_SQRT: [sS][qQ][rR][tT];
OP_SUB: [sS][uU][bB];
OP_SUBDUR: [sS][uU][bB][dD][uU][rR];
OP_SUBST: [sS][uU][bB][sS][tT];
OP_TAG: [tT][aA][gG];
//OP_TEST: [tT][eE][sS][tT];
OP_TESTB: [tT][eE][sS][tT][bB];
OP_TESTN: [tT][eE][sS][tT][nN];
OP_TESTZ: [tT][eE][sS][tT][zZ];
OP_TIME: [tT][iI][mM][eE];
//OP_UNLOCK: [uU][nN][lL][oO][cC][kK];
//OP_UPDATE: [uU][pP][dD][aA][tT][eE];
//OP_WHEN: [wW][hH][eE][nN];
OP_WHENEQ: [wW][hH][eE][nN][eE][qQ];
OP_WHENNE: [wW][hH][eE][nN][nN][eE];
OP_WHENLE: [wW][hH][eE][nN][lL][eE];
OP_WHENLT: [wW][hH][eE][nN][lL][tT];
OP_WHENGE: [wW][hH][eE][nN][gG][eE];
OP_WHENGT: [wW][hH][eE][nN][gG][tT];
//OP_WRITE: [wW][rR][iI][tT][eE];
OP_XFOOT: [xX][fF][oO][oO][tT];
OP_XLATE: [xX][lL][aA][tT][eE];
//OP_XML_INTO: [xX][mM][lL]'-'[iI][nN][tT][oO];
//OP_XML_SAX: [xX][mM][lL]'-'[sS][aA][xX];
OP_Z_ADD: [zZ]'-'[aA][dD][dD];
OP_Z_SUB: [zZ]'-'[sS][uU][bB];

mode FREE_ENDED;
FE_BLANKS : [ ]+ -> skip;
FE_COMMENTS: '//' -> popMode,pushMode(FIXED_CommentMode_HIDDEN),channel(HIDDEN) ;
FE_NEWLINE : NEWLINE -> popMode,skip;

mode InStringMode;
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

mode InDoubleStringMode;
	//  Any char except +,- or ", or a + or - followed by more than just whitespace 
DblStringContent: ~["\r\n]+ -> type(StringContent); 
DblStringLiteralEnd: ["] -> popMode,type(StringLiteralEnd);


//This mode is basically a language independent flag.
mode EatCommentLinesPlus;
EatCommentLinesPlus_Any: -> popMode,skip;

// Inside continuations, ignore comment and blank lines.
mode EatCommentLines;
EatCommentLines_WhiteSpace: ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]{getCharPositionInLine()==6}?[ ]* NEWLINE -> skip;
EatCommentLines_StarComment: 
   ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]{getCharPositionInLine()==6}? [*] ~[\r\n]* NEWLINE -> skip;
FIXED_FREE_STRING_CONTINUATION_Part2:  
   (
     ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 
     ( [cC] {_modeStack.contains(FIXED_CalcSpec)}?
      | [dD] {_modeStack.contains(FIXED_DefSpec)}? 
      | [oO] {_modeStack.contains(FIXED_OutputSpec)}? 
     ) 
     ~[*] 
     ( '                            ' {_modeStack.contains(FIXED_CalcSpec)}?
       | '                                    ' {_modeStack.contains(FIXED_DefSpec)}?
       | '                                             ' {_modeStack.contains(FIXED_OutputSpec)}?
     ) 
     ([ ]* {_modeStack.peek() == EatCommentLinesPlus}?
      | 
     )  // If it plus continuation eat whitespace.
   ) 
   -> type(CONTINUATION),skip ;
//FIXED_FREE_STRING_CONTINUATION_Minus_Part2:  
//   (
//     ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 
//     ( [cC] {_modeStack.contains(FIXED_CalcSpec)}?
//      | [dD] {_modeStack.contains(FIXED_DefSpec)}? 
//     ) 
//     ~[*] '                            ' 
//   ) 
//   -> type(CONTINUATION),skip ;
//Deliberate match no char, pop out again
EatCommentLines_NothingLeft: -> popMode,skip;

mode InFactorStringMode;
InFactor_StringContent:({(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
}?
		~[\r\n'])+ -> type(StringContent);
		
InFactor_StringEscapedQuote: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=61)
}?
		[']['] -> type(StringEscapedQuote);
		
InFactor_StringLiteralEnd: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
}?
		['] -> type(StringLiteralEnd),popMode;

InFactor_EndFactor: {(getCharPositionInLine()==25)
			|| (getCharPositionInLine()==49)
			|| (getCharPositionInLine()==61)
}? -> skip,popMode;

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
BLANK_SPEC : {getCharPositionInLine()==6}? 
    '                                                                           ';
CONTINUATION_NAME : {getCharPositionInLine()<21}? [ ]* ~[\r\n ]+ CONTINUATION {setText(getText().substring(0,getText().length()-3));} -> pushMode(CONTINUATION_ELIPSIS) ;
CONTINUATION : '...' ;
NAME : {getCharPositionInLine()==6}? WORD5 WORD5 WORD5 {setText(getText().trim());};
EXTERNAL_DESCRIPTION: {getCharPositionInLine()==21}? [eE ];
DATA_STRUCTURE_TYPE: {getCharPositionInLine()==22}? [sSuU ];
DEF_TYPE_C: {getCharPositionInLine()==23}? [cC][ ];
DEF_TYPE_PI: {getCharPositionInLine()==23}? [pP][iI];
DEF_TYPE_PR: {getCharPositionInLine()==23}? [pP][rR];
DEF_TYPE_DS: {getCharPositionInLine()==23}? [dD][sS];
DEF_TYPE_S: {getCharPositionInLine()==23}? [sS][ ];
DEF_TYPE_BLANK: {getCharPositionInLine()==23}? [ ][ ];
DEF_TYPE: {getCharPositionInLine()==23}? [a-zA-Z0-9 ][a-zA-Z0-9 ];
FROM_POSITION: {getCharPositionInLine()==25}? WORD5 [a-zA-Z0-9 ][a-zA-Z0-9 ];
TO_POSITION: {getCharPositionInLine()==32}? WORD5[a-zA-Z0-9 ][a-zA-Z0-9 ];
DATA_TYPE: {getCharPositionInLine()==39}? [a-zA-Z\* ];
DECIMAL_POSITIONS: {getCharPositionInLine()==40}? [0-9 ][0-9 ];
RESERVED : {getCharPositionInLine()==42}? ' ' -> pushMode(FREE);
//KEYWORDS : {getCharPositionInLine()==43}? ~[\r\n]+ ;
D_WS : {getCharPositionInLine()>=80}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
D_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN); // skip comments after 80
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
FS_BLANK_SPEC : {getCharPositionInLine()==6}? 
    '                                                                           ' -> type(BLANK_SPEC);
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
FS_Reserved: {getCharPositionInLine()==42}? [ ] -> pushMode(FREE);
//FS_Keywords : {getCharPositionInLine()==43}? ~[\r\n]+;
FS_WhiteSpace : {getCharPositionInLine()>=80}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
FS_EOL : NEWLINE -> type(EOL),popMode;

mode FIXED_OutputSpec;
OS_BLANK_SPEC : {getCharPositionInLine()==6}? 
    '                                                                           ' -> type(BLANK_SPEC);
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
OS_EOL : NEWLINE -> type(EOL),popMode;//,skip;

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
OS_DataFormat: {getCharPositionInLine()==51}? [ 0-9A-Za-z] -> pushMode(FREE);
//OS_Words: {getCharPositionInLine()==52}? WORD5 WORD5 WORD5 WORD5 WORD5 ~[\r\n] ~[\r\n] ~[\r\n] -> popMode;
OS_Any: -> popMode;

	
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
 CS_FactorContentHexLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=61)
}?
		[xX]['] -> type(HexLiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentDateLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=61)
}?
		[dD]['] -> type(DateLiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentTimeLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=61)
}?
		[tT]['] -> type(TimeLiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentGraphicLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=61)
}?
		[gG]['] -> type(GraphicLiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentUCS2Literal: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=61)
}?
		[uU]['] -> type(UCS2LiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentStringLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
}?
		['] -> type(StringLiteralStart),pushMode(InFactorStringMode);
// 		
//CS_FactorContentLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
//			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
//			|| (getCharPositionInLine()>=49 && getCharPositionInLine()<=62)
//}?
//		['] -> type(UCS2LiteralStart),pushMode(InFactorStringMode);
		
CS_FactorContent: ({(getCharPositionInLine()>=11 && getCharPositionInLine()<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()<=48)
}?
		~[\r\n'\'' :])+;
CS_ResultContent: ({(getCharPositionInLine()>=49 && getCharPositionInLine()<=62)}?
		~[\r\n'\'' :])+ -> type(CS_FactorContent);
CS_FactorColon: ({(getCharPositionInLine()>11 && getCharPositionInLine()<24)
			|| (getCharPositionInLine()>35 && getCharPositionInLine()<48)
			|| (getCharPositionInLine()>49 && getCharPositionInLine()<62)
}?
		[:]) -> type(COLON);//pushMode(FIXED_CalcSpec_2PartFactor),
		/* 
CS_OperationAndExtendedFactor2: {getCharPositionInLine()==25}? 
	(OP_EVAL '      ' 
		| OP_IF '        '  
		| OP_CALLP'     '
		| OP_CALLP[(][eE][)]'  '
		| OP_DOW'       '
		| OP_ELSEIF'    '
		| OP_ON_ERROR'  '
	) -> pushMode(FREE);
	*/
CS_OperationAndExtender_Blank:  
   {getCharPositionInLine()==25}?'          ';
CS_OperationAndExtender_WS:
	({getCharPositionInLine()>=25 && getCharPositionInLine()<35}?[ ])+ -> skip;	
CS_Operation_ACQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<33}? OP_ACQ -> type(OP_ACQ);
CS_Operation_ADD: {getCharPositionInLine()>=25 && getCharPositionInLine()<33}? OP_ADD -> type(OP_ADD);
CS_Operation_ADDDUR: {getCharPositionInLine()>=25 && getCharPositionInLine()<30}? OP_ADDDUR -> type(OP_ADDDUR);
CS_Operation_ALLOC: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ALLOC -> type(OP_ALLOC);
CS_Operation_ANDEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ANDEQ -> type(OP_ANDEQ);
CS_Operation_ANDNE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ANDNE -> type(OP_ANDNE);
CS_Operation_ANDLE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ANDLE -> type(OP_ANDLE);
CS_Operation_ANDLT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ANDLT -> type(OP_ANDLT);
CS_Operation_ANDGE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ANDGE -> type(OP_ANDGE);
CS_Operation_ANDGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ANDGT -> type(OP_ANDGT);
CS_Operation_ANDxx: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ANDxx -> type(OP_ANDxx);
CS_Operation_BEGSR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_BEGSR -> type(OP_BEGSR);
CS_Operation_BITOFF: {getCharPositionInLine()>=25 && getCharPositionInLine()<30}? OP_BITOFF -> type(OP_BITOFF);
CS_Operation_BITON: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_BITON -> type(OP_BITON);
CS_Operation_CABxx: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CABxx-> type(OP_CABxx);
CS_Operation_CABEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CABEQ-> type(OP_CABEQ);
CS_Operation_CABNE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CABNE-> type(OP_CABNE);
CS_Operation_CABLE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CABLE-> type(OP_CABLE);
CS_Operation_CABLT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CABLT-> type(OP_CABLT);
CS_Operation_CABGE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CABGE-> type(OP_CABGE);
CS_Operation_CABGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CABGT-> type(OP_CABGT);
CS_Operation_CALL: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CALL -> type(OP_CALL);
CS_Operation_CALLB: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CALLB -> type(OP_CALLB);
CS_Operation_CALLP: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CALLP -> type(OP_CALLP),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_CASEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CASEQ-> type(OP_CASEQ);
CS_Operation_CASNE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CASNE-> type(OP_CASNE);
CS_Operation_CASLE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CASLE-> type(OP_CASLE);
CS_Operation_CASLT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CASLT-> type(OP_CASLT);
CS_Operation_CASGE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CASGE-> type(OP_CASGE);
CS_Operation_CASGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CASGT-> type(OP_CASGT);
CS_Operation_CAS: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CAS-> type(OP_CAS);
CS_Operation_CAT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CAT-> type(OP_CAT);
CS_Operation_CHAIN: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CHAIN-> type(OP_CHAIN);
CS_Operation_CHECK: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CHECK-> type(OP_CHECK);
CS_Operation_CHECKR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CHECKR-> type(OP_CHECKR);
CS_Operation_CLEAR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CLEAR-> type(OP_CLEAR);
CS_Operation_CLOSE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_CLOSE-> type(OP_CLOSE);
CS_Operation_COMMIT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_COMMIT-> type(OP_COMMIT);
CS_Operation_COMP: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_COMP-> type(OP_COMP);
CS_Operation_DEALLOC: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DEALLOC-> type(OP_DEALLOC);
CS_Operation_DEFINE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DEFINE-> type(OP_DEFINE);
CS_Operation_DELETE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DELETE-> type(OP_DELETE);
CS_Operation_DIV: {getCharPositionInLine()>=25 && getCharPositionInLine()<33}? OP_DIV-> type(OP_DIV);
CS_Operation_DO: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DO-> type(OP_DO);
CS_Operation_DOU: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOU-> type(OP_DOU),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_DOUEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOUEQ-> type(OP_DOUEQ);
CS_Operation_DOUNE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOUNE-> type(OP_DOUNE);
CS_Operation_DOULE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOULE-> type(OP_DOULE);
CS_Operation_DOULT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOULT-> type(OP_DOULT);
CS_Operation_DOUGE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOUGE-> type(OP_DOUGE);
CS_Operation_DOUGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOUGT-> type(OP_DOUGT);
CS_Operation_DOW: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOW-> type(OP_DOW),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_DOWEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOWEQ-> type(OP_DOWEQ);
CS_Operation_DOWNE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOWNE-> type(OP_DOWNE);
CS_Operation_DOWLE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOWLE-> type(OP_DOWLE);
CS_Operation_DOWLT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOWLT-> type(OP_DOWLT);
CS_Operation_DOWGE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOWGE-> type(OP_DOWGE);
CS_Operation_DOWGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DOWGT-> type(OP_DOWGT);
CS_Operation_DSPLY: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DSPLY-> type(OP_DSPLY);
CS_Operation_DUMP: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_DUMP-> type(OP_DUMP);
CS_Operation_ELSE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ELSE-> type(OP_ELSE);
CS_Operation_ELSEIF: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ELSEIF-> type(OP_ELSEIF),pushMode(FREE);
CS_Operation_END: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_END-> type(OP_END);
CS_Operation_ENDCS: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ENDCS-> type(OP_ENDCS);
CS_Operation_ENDDO: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ENDDO-> type(OP_ENDDO);
CS_Operation_ENDFOR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ENDFOR-> type(OP_ENDFOR);
CS_Operation_ENDIF: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ENDIF-> type(OP_ENDIF);
CS_Operation_ENDMON: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ENDMON-> type(OP_ENDMON);
CS_Operation_ENDSL: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ENDSL-> type(OP_ENDSL);
CS_Operation_ENDSR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ENDSR-> type(OP_ENDSR);
CS_Operation_EVAL: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_EVAL-> type(OP_EVAL),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_EVALR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_EVALR-> type(OP_EVALR),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_EVAL_CORR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_EVAL_CORR-> type(OP_EVAL_CORR),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_EXCEPT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_EXCEPT-> type(OP_EXCEPT);
CS_Operation_EXFMT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_EXFMT-> type(OP_EXFMT);
CS_Operation_EXSR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_EXSR-> type(OP_EXSR);
CS_Operation_EXTRCT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_EXTRCT-> type(OP_EXTRCT);
CS_Operation_FEOD: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_FEOD-> type(OP_FEOD);
CS_Operation_FOR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_FOR-> type(OP_FOR),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_FORCE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_FORCE-> type(OP_FORCE);
CS_Operation_GOTO: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_GOTO-> type(OP_GOTO);
CS_Operation_IF: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_IF-> type(OP_IF),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_IFEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_IFEQ-> type(OP_IFEQ);
CS_Operation_IFNE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_IFNE-> type(OP_IFNE);
CS_Operation_IFLE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_IFLE-> type(OP_IFLE);
CS_Operation_IFLT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_IFLT-> type(OP_IFLT);
CS_Operation_IFGE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_IFGE-> type(OP_IFGE);
CS_Operation_IFGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_IFGT-> type(OP_IFGT);
CS_Operation_IN: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_IN-> type(OP_IN);
CS_Operation_ITER: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ITER-> type(OP_ITER);
CS_Operation_KFLD: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_KFLD-> type(OP_KFLD);
CS_Operation_KLIST: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_KLIST-> type(OP_KLIST);
CS_Operation_LEAVE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_LEAVE-> type(OP_LEAVE);
CS_Operation_LEAVESR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_LEAVESR-> type(OP_LEAVESR);
CS_Operation_LOOKUP: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_LOOKUP-> type(OP_LOOKUP);
CS_Operation_MHHZO: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MHHZO-> type(OP_MHHZO);
CS_Operation_MHLZO: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MHLZO-> type(OP_MHLZO);
CS_Operation_MLHZO: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MLHZO-> type(OP_MLHZO);
CS_Operation_MLLZO: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MLLZO-> type(OP_MLLZO);
CS_Operation_MONITOR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MONITOR-> type(OP_MONITOR);
CS_Operation_MOVE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MOVE-> type(OP_MOVE);
CS_Operation_MOVEA: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MOVEA-> type(OP_MOVEA);
CS_Operation_MOVEL: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MOVEL-> type(OP_MOVEL);
CS_Operation_MULT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MULT-> type(OP_MULT);
CS_Operation_MVR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_MVR-> type(OP_MVR);
CS_Operation_NEXT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_NEXT-> type(OP_NEXT);
CS_Operation_OCCUR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_OCCUR-> type(OP_OCCUR);
CS_Operation_ON_ERROR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ON_ERROR-> type(OP_ON_ERROR),pushMode(FREE);
CS_Operation_OPEN: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_OPEN-> type(OP_OPEN);
CS_Operation_OREQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_OREQ-> type(OP_OREQ);
CS_Operation_ORNE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ORNE-> type(OP_ORNE);
CS_Operation_ORLE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ORLE-> type(OP_ORLE);
CS_Operation_ORLT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ORLT-> type(OP_ORLT);
CS_Operation_ORGE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ORGE-> type(OP_ORGE);
CS_Operation_ORGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ORGT-> type(OP_ORGT);
CS_Operation_OTHER: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_OTHER-> type(OP_OTHER);
CS_Operation_OUT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_OUT-> type(OP_OUT);
CS_Operation_PARM: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_PARM-> type(OP_PARM);
CS_Operation_PLIST: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_PLIST-> type(OP_PLIST);
CS_Operation_POST: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_POST-> type(OP_POST);
CS_Operation_READ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_READ-> type(OP_READ);
CS_Operation_READC: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_READC-> type(OP_READC);
CS_Operation_READE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_READE-> type(OP_READE);
CS_Operation_READP: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_READP-> type(OP_READP);
CS_Operation_READPE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_READPE-> type(OP_READPE);
CS_Operation_REALLOC: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_REALLOC-> type(OP_REALLOC);
CS_Operation_REL: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_REL-> type(OP_REL);
CS_Operation_RESET: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_RESET-> type(OP_RESET);
CS_Operation_RETURN: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_RETURN-> type(OP_RETURN),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_ROLBK: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_ROLBK-> type(OP_ROLBK);
CS_Operation_SCAN: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SCAN-> type(OP_SCAN);
CS_Operation_SELECT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SELECT-> type(OP_SELECT);
CS_Operation_SETGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SETGT-> type(OP_SETGT);
CS_Operation_SETLL: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SETLL-> type(OP_SETLL);
CS_Operation_SETOFF: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SETOFF-> type(OP_SETOFF);
CS_Operation_SETON: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SETON-> type(OP_SETON);
CS_Operation_SORTA: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SORTA-> type(OP_SORTA),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_SHTDN: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SHTDN-> type(OP_SHTDN);
CS_Operation_SQRT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SQRT-> type(OP_SQRT);
CS_Operation_SUB: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SUB-> type(OP_SUB);
CS_Operation_SUBDUR: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SUBDUR-> type(OP_SUBDUR);
CS_Operation_SUBST: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_SUBST-> type(OP_SUBST);
CS_Operation_TAG: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_TAG-> type(OP_TAG);
CS_Operation_TEST: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_TEST-> type(OP_TEST);
CS_Operation_TESTB: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_TESTB-> type(OP_TESTB);
CS_Operation_TESTN: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_TESTN-> type(OP_TESTN);
CS_Operation_TESTZ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_TESTZ-> type(OP_TESTZ);
CS_Operation_TIME: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_TIME-> type(OP_TIME);
CS_Operation_UNLOCK: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_UNLOCK-> type(OP_UNLOCK);
CS_Operation_UPDATE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_UPDATE-> type(OP_UPDATE);
CS_Operation_WHEN: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_WHEN-> type(OP_WHEN),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_WHENEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_WHENEQ-> type(OP_WHENEQ);
CS_Operation_WHENNE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_WHENNE-> type(OP_WHENNE);
CS_Operation_WHENLE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_WHENLE-> type(OP_WHENLE);
CS_Operation_WHENLT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_WHENLT-> type(OP_WHENLT);
CS_Operation_WHENGE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_WHENGE-> type(OP_WHENGE);
CS_Operation_WHENGT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_WHENGT-> type(OP_WHENGT);
CS_Operation_WRITE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_WRITE-> type(OP_WRITE);
CS_Operation_XFOOT: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_XFOOT-> type(OP_XFOOT);
CS_Operation_XLATE: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_XLATE-> type(OP_XLATE);
CS_Operation_XML_INTO: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_XML_INTO-> type(OP_XML_INTO),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_XML_SAX: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_XML_SAX-> type(OP_XML_SAX),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_Z_ADD: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_Z_ADD-> type(OP_Z_ADD);
CS_Operation_Z_SUB: {getCharPositionInLine()>=25 && getCharPositionInLine()<31}? OP_Z_SUB-> type(OP_Z_SUB);


CS_OperationAndExtender:  
   ({getCharPositionInLine()>=25 && getCharPositionInLine()<35}?[a-zA-Z0-9\\-])+;
CS_OperationExtenderOpen: {getCharPositionInLine()>=25 && getCharPositionInLine()<35}?OPEN_PAREN -> type(OPEN_PAREN);
CS_OperationExtenderClose: {getCharPositionInLine()>=25 && getCharPositionInLine()<35}?CLOSE_PAREN 
  ({getCharPositionInLine()>=25 && getCharPositionInLine()<35}? ' ')*
  {setText(getText().trim());}
  -> type(CLOSE_PAREN);
  
CS_FieldLength: {getCharPositionInLine()==63}? [+\\- 0-9][+\\- 0-9][+\\- 0-9][+\\- 0-9][+\\- 0-9];
CS_DecimalPositions: {getCharPositionInLine()==68}? [ 0-9][ 0-9]
	-> pushMode(IndicatorMode),pushMode(IndicatorMode),pushMode(IndicatorMode); // 3 Indicators in a row
CS_WhiteSpace : {getCharPositionInLine()>=76}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
CS_Comments : {getCharPositionInLine()>=80}? ~[\r\n]+  ; // skip spaces, tabs, newlines
CS_EOL : NEWLINE -> type(EOL),popMode;

mode FixedOpExtender;
CS_FixedOperationAndExtender_WS:
	({getCharPositionInLine()>=25 && getCharPositionInLine()<35}?[ ])+ -> skip;	
CS_FixedOperationExtenderOpen: {getCharPositionInLine()>=25 && getCharPositionInLine()<35}?OPEN_PAREN -> type(OPEN_PAREN),popMode,pushMode(FixedOpExtender2);
CS_FixedOperationExtenderReturn: {getCharPositionInLine()>=25 && getCharPositionInLine()<=35}? ->skip,popMode;

mode FixedOpExtender2;
CS_FixedOperationAndExtender2_WS:
	({getCharPositionInLine()>=25 && getCharPositionInLine()<35}?[ ])+ -> skip;	
CS_FixedOperationAndExtender2:  
   ({getCharPositionInLine()>=25 && getCharPositionInLine()<35}?[a-zA-Z0-9\\-])+ -> type(CS_OperationAndExtender);
CS_FixedOperationExtender2Close: {getCharPositionInLine()>=25 && getCharPositionInLine()<35}?CLOSE_PAREN 
  ({getCharPositionInLine()>=25 && getCharPositionInLine()<35}? ' ')*
  {setText(getText().trim());}
  -> type(CLOSE_PAREN);
CS_FixedOperationExtender2Return: {getCharPositionInLine()==35}? ->skip,popMode;


mode FreeOpExtender;
FreeOpExtender_OPEN_PAREN: OPEN_PAREN -> popMode,type(OPEN_PAREN),pushMode(FreeOpExtender2);
//Deliberate match no char, pop out again
FreeOpExtender_Any: -> popMode,skip;

mode FreeOpExtender2;
FreeOpExtender2_CLOSE_PAREN: CLOSE_PAREN -> popMode,type(CLOSE_PAREN);
FreeOpExtender2_WS: WS -> skip;
FreeOpExtender_Extender: [a-zA-Z0-9\\-] -> type(CS_OperationAndExtender);

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
IS_BLANK_SPEC : {getCharPositionInLine()==6}? 
    '                                                                           ' -> type(BLANK_SPEC);
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
fragment WORD_WCOLON : ~[\r\n];//[a-zA-Z0-9 :*];
fragment WORD5_WCOLON : WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
//fragment C_OP_EXT: [ aAhHnNpPdDtTzZmMrReE];
//fragment IND_FRAG: ('  ' | [0-9lLhH][0-9] | [kK][A-NP-Ya-np0y] | [lLmM][rR] | [rR][tT] | [uU][1-8] | [oO][a-gvA-GV] | [0-9][pP]);

