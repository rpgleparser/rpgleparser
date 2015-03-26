parser grammar RpgParser;

//import FreeOpsParser;

options {   tokenVocab = RpgLexer; }

r: (dspec 
	| dcl_ds
	| dcl_pr 
	| dcl_pi
	| dcl_c
	| ctl_opt
//	|dspec_continuation
//	| (dspec_continuation* dspec_fixed) 
  	| (dspec_fixed)
  	| ospec_fixed
  	| pspec_fixed
	| fspec 
	| fspec_fixed 
	| cspec_fixed 
	| cspec_fixed_sql
	| ispec_fixed 
	| hspec_fixed
	| star_comments
	| free_comments
	| blank_line 
	| directive 
	| free
)*
endSource*
;
endSource: endSourceHead endSourceLine*;
endSourceHead: END_SOURCE ;
endSourceLine: EOS_Text EOL;

star_comments: COMMENT_SPEC_FIXED ;//comments COMMENTS_EOL;
free_comments: COMMENTS comments COMMENTS_EOL;
free_linecomments: COMMENTS comments;
comments: COMMENTS_TEXT; 
//just_comments: COMMENTS COMMENTS_TEXT COMMENTS_EOL;

dspec:  DS_Standalone identifier (identifier|function)* FREE_SEMI free_linecomments?;
dcl_ds:  DS_DataStructureStart (identifier | function)* FREE_SEMI?  
	dcl_ds_field*
	end_dcl_ds FREE_SEMI;
dcl_ds_field: ((identifier | function)+ FREE_SEMI );
end_dcl_ds: DS_DataStructureEnd;
dcl_pr:  DS_PrototypeStart (identifier | function)* FREE_SEMI?  
	dcl_pr_field*
	end_dcl_pr FREE_SEMI;
dcl_pr_field: ((identifier | function)+ FREE_SEMI );
end_dcl_pr: DS_PrototypeEnd;
dcl_pi:  DS_ProcedureStart (identifier | function)* FREE_SEMI?  
	dcl_pi_field*
	end_dcl_pi FREE_SEMI;
dcl_pi_field: ((identifier | function)+ FREE_SEMI );
end_dcl_pi: DS_ProcedureEnd;
dcl_c:  DS_Constant identifier (identifier | expression) FREE_SEMI ;
ctl_opt:  H_SPEC (identifier | expression)* FREE_SEMI ;


dspec_fixed: DS_FIXED ds_name EXTERNAL_DESCRIPTION DATA_STRUCTURE_TYPE DEF_TYPE FROM_POSITION TO_POSITION
	DATA_TYPE DECIMAL_POSITIONS RESERVED KEYWORDS EOL;
ds_name: CONTINUATION_NAME* NAME;

ospec_fixed: OS_FIXED (((OS_RecordName 
	OS_Type
	(os_fixed_pgmdesc1 | os_fixed_pgmdesc2)) | os_fixed_pgmfield) 
	  |os_fixed_pgmdesc_compound)
	OS_Comments?;

os_fixed_pgmdesc1:
	OS_FetchOverflow
	outputConditioningOnOffIndicator
	outputConditioningOnOffIndicator
	outputConditioningOnOffIndicator
	OS_ExceptName
	OS_Space3 OS_Space3 OS_Space3 OS_Space3
	OS_RemainingSpace;

outputConditioningOnOffIndicator:
	onOffIndicatorsFlag
	outputConditioningIndicator;

outputConditioningIndicator:
	BlankIndicator
	| GeneralIndicator
	| FunctionKeyIndicator
	| ControlLevelIndicator
	| HaltIndicator
	| ExternalIndicator
	| OverflowIndicator
	| MatchingRecordIndicator
	| LastRecordIndicator
	| ReturnIndicator
	| FirstPageIndicator;
	
os_fixed_pgmdesc_compound:
	OS_AndOr
	outputConditioningOnOffIndicator
	outputConditioningOnOffIndicator
	outputConditioningOnOffIndicator
	OS_ExceptName
	OS_Space3 OS_Space3 OS_Space3 OS_Space3
	OS_RemainingSpace;
	
os_fixed_pgmdesc2:
	OS_AddDelete
	outputConditioningOnOffIndicator
	outputConditioningOnOffIndicator
	outputConditioningOnOffIndicator
	OS_ExceptName
	OS_RemainingSpace;
	
os_fixed_pgmfield:
	OS_FieldReserved
	outputConditioningOnOffIndicator
	outputConditioningOnOffIndicator
	outputConditioningOnOffIndicator
	OS_FieldName 
	OS_EditNames
	OS_BlankAfter
	OS_EndPosition
	OS_DataFormat
	OS_Words;
	
pspec_fixed: PS_FIXED ps_name (PS_BEGIN | PS_END) PS_KEYWORDS;
ps_name: PS_CONTINUATION_NAME* PS_NAME;
 
//dspec_continuation:	DS_FIXED CONTINUATION_NAME EOL;
fspec:  FS_FreeFile filename  
	fs_expression*; 
filename: ID; 
fs_expression: (ID (OPEN_PAREN (fs_parm (COLON fs_parm)*)? CLOSE_PAREN)?);
fs_parm: expression | fs_string;
fs_string: (StringLiteralStart|HexLiteralStart|DateLiteralStart) (StringContent | StringEscapedQuote )* StringLiteralEnd;



fspec_fixed: FS_FIXED FS_RecordName FS_Type FS_Designation FS_EndOfFile FS_Addution 
	FS_Sequence FS_Format FS_RecordLength FS_Limits FS_LengthOfKey FS_RecordAddressType FS_Organization FS_Device FS_Reserved 
	FS_Keywords FS_EOL;	
cspec_fixed: CS_FIXED 
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	(cspec_fixed_standard|cspec_fixed_x2);
	
onOffIndicatorsFlag:
BlankFlag
| NoFlag;

cs_controlLevel:
BlankIndicator
	|ControlLevel0Indicator
	| ControlLevelIndicator
	| LastRecordIndicator
	| SubroutineIndicator
	| AndIndicator
	| OrIndicator
;
cs_indicators:
BlankIndicator
	| GeneralIndicator
	| ControlLevelIndicator
	| FunctionKeyIndicator 
	| LastRecordIndicator
	| MatchingRecordIndicator
	| HaltIndicator
	| ReturnIndicator
	| ExternalIndicator
	| OverflowIndicator
;
resultIndicator:
 BlankIndicator
    | GeneralIndicator
	| ControlLevelIndicator
	| FunctionKeyIndicator 
	| LastRecordIndicator
	| MatchingRecordIndicator
	| HaltIndicator
	| ExternalIndicator
	| OverflowIndicator
	| ReturnIndicator
;
cspec_fixed_sql: CS_ExecSQL
	CSQL_TEXT+
	CSQL_END;
cspec_fixed_standard: operation=CS_OperationAndExtender
	operationExtender=cs_operationExtender?
	factor2=factor
	result=resultType 
	len=CS_FieldLength 
	decimalPositions=CS_DecimalPositions 
	hi=resultIndicator 
	lo=resultIndicator
	eq=resultIndicator 
	cs_fixed_comments? EOL;
	
cs_operationExtender:
  OPEN_PAREN
  extender=CS_OperationAndExtender
  CLOSE_PAREN;	
factor:
   content=CS_FactorContent (COLON (content2=CS_FactorContent | constant2=symbolicConstants))? | CS_BlankFactor | constant=symbolicConstants;
resultType:	
   CS_FactorContent | CS_BlankFactor;
cs_fixed_comments:CS_Comments;		
//cs_fixed_x2: CS_OperationAndExtendedFactor2 C2_FACTOR2_CONT* C2_FACTOR2 C_EOL;
cspec_fixed_x2: CS_OperationAndExtendedFactor2 c_free C_FREE_NEWLINE;


ispec_fixed: IS_FIXED 
	((IS_FileName
	//IS_LogicalRelationship
		(is_external_rec
		|is_rec)
		EOL
	)
	| (is_external_field
		EOL
	)
	| (IFD_DATA_ATTR
		IFD_DATETIME_SEP
		IFD_DATA_FORMAT
		IFD_FIELD_LOCATION
		IFD_DECIMAL_POSITIONS
		IFD_FIELD_NAME
		IFD_CONTROL_LEVEL
		IFD_MATCHING_FIELDS
		fieldRecordRelation
		fieldIndicator
		fieldIndicator
		fieldIndicator
		IFD_COMMENTS?
		EOL
	))
	;
	
fieldRecordRelation:
 BlankIndicator
    | GeneralIndicator
	| ControlLevelIndicator
	| MatchingRecordIndicator
	| ExternalIndicator
	| HaltIndicator
	| ReturnIndicator
;	

fieldIndicator:
 BlankIndicator
    | GeneralIndicator
	| ControlLevelIndicator
	| HaltIndicator
	| ExternalIndicator
	| ReturnIndicator
;	
is_external_rec:	
			IS_ExtRecordReserved
			resultIndicator
			WS;
is_rec:				
			IS_Sequence
			IS_Number
			IS_Option
			recordIdIndicator
			IS_RecordIdCode
			WS?;
recordIdIndicator:
	GeneralIndicator
  | HaltIndicator
  | ControlLevelIndicator
  | LastRecordIndicator
  | ExternalIndicator
  | ReturnIndicator;

is_external_field:
			IF_Name
			IF_FieldName
			controlLevelIndicator
			matchingFieldsIndicator
			resultIndicator
			resultIndicator
			resultIndicator
;

controlLevelIndicator:
ControlLevelIndicator
| BlankIndicator;

matchingFieldsIndicator:
MatchingRecordIndicator
| BlankIndicator;

hspec_fixed: HS_FIXED 
	hs_expression*
	EOL;
hs_expression: (ID (OPEN_PAREN (hs_parm (COLON hs_parm)*)? CLOSE_PAREN)?);
hs_parm: ID | hs_string;
hs_string: StringLiteralStart (StringContent | StringEscapedQuote )* StringLiteralEnd;
blank_line: BLANK_LINE;
directive: DIRECTIVE 
		(free_directive
		| title_directive
		| DIR_EJECT
		| space_directive
		| DIR_SET
		| DIR_RESTORE
		| (DIR_COPY WS* DIR_OtherText)
		| (DIR_INCLUDE WS* DIR_OtherText)
		| DIR_EOF
		| (DIR_DEFINE WS* DIR_OtherText)
		| (DIR_UNDEFINE WS* DIR_OtherText)
		| (DIR_IF WS* DIR_OtherText)
		| (DIR_ELSE WS* DIR_OtherText)
		| (DIR_ELSEIF WS* DIR_OtherText)
		| (DIR_ENDIF WS* DIR_OtherText)
		)
	EOL;
free_directive: free_text;
space_directive: DIR_SPACE (WS NUMBER)?;
free_text: DIR_FREE;
trailing_ws: DIR_FREE_OTHER_TEXT;
//title_directive: DIR_TITLE WS title_text WS*;
//title_directive: DIR_TITLE (WS* DIR_OtherText)?;
title_directive: DIR_TITLE WS title_text*;
//title_text: (WS | NUMBER | DIR_OtherText) (NUMBER | DIR_OtherText);
title_text: (WS | NUMBER | DIR_OtherText);
 
//------- Auto from here
op: op_acq 
	| op_begsr 
	| op_callp 
	| op_chain 
	| op_clear 
	| op_close 
	| op_commit 
	| op_dealloc 
	| op_delete 
	| op_dou 
	| op_dow 
	| op_dsply 
	| op_dump 
	| op_else 
	| op_elseif 
	| op_enddo 
	| op_endfor 
	| op_endif 
	| op_endmon 
	| op_endsl 
	| op_endsr 
	| op_eval 
	| op_evalr
	| op_eval_corr 
	| op_except 
	| op_exfmt 
	| op_exsr 
	| op_feod 
	| op_for 
	| op_force 
	| op_if 
	| op_in 
	| op_iter 
	| op_leave 
	| op_leavesr 
	| op_monitor 
	| op_next 
	| op_on_error 
	| op_open 
	| op_other 
	| op_out 
	| op_post 
	| op_read 
	| op_readc 
	| op_reade 
	| op_readp 
	| op_readpe 
	| op_rel 
	| op_reset 
	| op_reset2 
	| op_return 
	| op_rolbk 
	| op_select 
	| op_setgt 
	| op_setll 
	| op_sorta 
	| op_test 
	| op_unlock 
	| op_update 
	| op_when 
	| op_write 
	| op_xml_into 
	| op_xml_sax;
op_acq: OP_ACQ OP_E? identifier identifier ;
op_begsr: OP_BEGSR identifier ;
op_callp: (OP_CALLP OP_E? )? identifier OPEN_PAREN (expression (COLON expression )* )? CLOSE_PAREN ;
op_chain: OP_CHAIN OP_E? search_arg identifier (identifier )? ;
op_clear: OP_CLEAR (identifier )? (identifier )? expression ;
op_close: OP_CLOSE OP_E? identifier ;
op_commit: OP_COMMIT OP_E? (identifier )? ;
op_dealloc: OP_DEALLOC OP_E? identifier ;
op_delete: OP_DELETE OP_E? (search_arg )? identifier ;
op_dou: OP_DOU OP_E? indicator_expr ;
op_dow: OP_DOW OP_E? indicator_expr ;
op_dsply: OP_DSPLY OP_E? (expression (expression (expression )? )? )? ;
op_dump: OP_DUMP OP_E? (identifier )? ;
op_else: OP_ELSE ;
op_elseif: OP_ELSEIF OP_E? indicator_expr ;
op_enddo: OP_ENDDO ;
op_endfor: OP_ENDFOR ;
op_endif: OP_ENDIF ;
op_endmon: OP_ENDMON ;
op_endsl: OP_ENDSL ;
op_endsr: OP_ENDSR (identifier )? ;
op_eval: (OP_EVAL OP_E? )? assignmentExpression ;
op_evalr: OP_EVALR OP_E? assignmentExpression ;
op_eval_corr: OP_EVAL_CORR OP_E? assignmentExpression ;
op_except: OP_EXCEPT (identifier )? ;
op_exfmt: OP_EXFMT OP_E? identifier (identifier )? ;
op_exsr: OP_EXSR identifier ;
op_feod: OP_FEOD OP_E? identifier ;
op_for: OP_FOR OP_E? expression   //For(E) I
	(EQUAL expression )? // = 1
	(FREE_BY expression )?    // By 1
	((FREE_TO | FREE_DOWNTO) expression )?; // TO 10 ;
op_force: OP_FORCE identifier ;
op_if: OP_IF OP_E? expression ;
op_in: OP_IN OP_E? (identifier )? identifier ;
op_iter: OP_ITER ;
op_leave: OP_LEAVE ;
op_leavesr: OP_LEAVESR ;
op_monitor: OP_MONITOR ;
op_next: OP_NEXT OP_E? identifier identifier ;
op_on_error: OP_ON_ERROR (identifier (COLON identifier )* )? ;
op_open: OP_OPEN OP_E? identifier ;
op_other: OP_OTHER ;
op_out: OP_OUT OP_E? (identifier )? identifier ;
op_post: OP_POST OP_E? (identifier )? identifier ;
op_read: OP_READ OP_E? identifier (identifier )? ;
op_readc: OP_READC OP_E? identifier (identifier )? ;
op_reade: OP_READE OP_E? search_arg identifier (identifier )? ;
op_readp: OP_READP OP_E? identifier (identifier )? ;
op_readpe: OP_READPE OP_E? search_arg identifier (identifier )? ;
op_rel: OP_REL OP_E? identifier identifier ;
op_reset2: OP_RESET OP_E? identifier  OPEN_PAREN MULT_NOSPACE?  CLOSE_PAREN ;
op_reset: OP_RESET OP_E?  (identifier)? (identifier )? identifier ;
op_return: OP_RETURN OP_E? identifier? ;
op_rolbk: OP_ROLBK OP_E? ;
op_select: OP_SELECT ;
op_setgt: OP_SETGT OP_E? search_arg identifier ;
op_setll: OP_SETLL OP_E? search_arg identifier ;
op_sorta: OP_SORTA OP_E? (identifier | function);
op_test: OP_TEST OP_E? (identifier )? identifier ;
op_unlock: OP_UNLOCK OP_E? identifier ;
op_update: OP_UPDATE OP_E? identifier (identifier function )? ;
op_when: OP_WHEN OP_E? indicator_expr ;
op_write: OP_WRITE OP_E? identifier (identifier )? ;
op_xml_into: OP_XML_INTO OP_E? identifier expression ;
op_xml_sax: OP_XML_SAX OP_E? identifier identifier ;
search_arg: expression | args;
op_code: OP_ACQ
	  | OP_BEGSR
	  | OP_CALLP
	  | OP_CHAIN
	  | OP_CLEAR
	  | OP_CLOSE
	  | OP_COMMIT
	  | OP_DEALLOC
	  | OP_DELETE
      | OP_DOU
      | OP_DOW
      | OP_DSPLY
      | OP_DUMP
      | OP_ELSE
      | OP_ELSEIF
      | OP_ENDDO
      | OP_ENDFOR
      | OP_ENDIF
      | OP_ENDMON
      | OP_ENDSL
      | OP_ENDSR
      | OP_EVAL
      | OP_EVALR
      | OP_EVAL_CORR
      | OP_EXCEPT
      | OP_EXFMT
      | OP_EXSR
      | OP_FEOD
      | OP_FOR
      | OP_FORCE
      | OP_IF
      | OP_IN
      | OP_ITER
      | OP_LEAVE
      | OP_LEAVESR
      | OP_MONITOR
      | OP_NEXT
      | OP_ON_ERROR
      | OP_OPEN
      | OP_OTHER
      | OP_OUT
      | OP_POST
      | OP_READ
      | OP_READC
      | OP_READE
      | OP_READP
      | OP_READPE
      | OP_REL
      | OP_RESET
      | OP_RETURN
      | OP_ROLBK
      | OP_SELECT
      | OP_SETGT
      | OP_SETLL
      | OP_SORTA
      | OP_TEST
      | OP_UNLOCK
      | OP_UPDATE
      | OP_WHEN
      | OP_WRITE
      | OP_XML_INTO
      | OP_XML_SAX;
//--------------------------------

free: ((baseExpression FREE_SEMI free_linecomments? ) | exec_sql); // (NEWLINE |COMMENTS_EOL);
c_free: (((baseExpression) free_linecomments? ) | exec_sql);

control: opCode indicator_expr;
exec_sql: EXEC_SQL WORDS+ SEMI ;
//sql_quoted: SINGLE_QTE (WORDS | SEMI | DOUBLE_QTE) * SINGLE_QTE ;
//sql_quoted2: DOUBLE_QTE (WORDS | SEMI |SINGLE_QTE)* DOUBLE_QTE ;

//--------------- 
baseExpression: op | assignmentExpression | expression;
assignmentExpression: expression EQUAL expression;
expression: 
	// op | // should drop op I think? 
	identifier 
	| number 
	| literal  
	| function 
	| NOT expression
	| OPEN_PAREN expression CLOSE_PAREN
	| expression (assignmentOperator | comparisonOperator) expression
	| expression (OR | AND | arithmeticalOperator | EQUAL) expression	
	;
indicator_expr: expression;
function: functionName args;
arithmeticalOperator:PLUS | MINUS | EXP | MULT | MULT_NOSPACE | DIV;
comparisonOperator: GT | LT | GE | LE | NE;
assignmentOperator: CPLUS | CMINUS | CMULT | CDIV ;

/*--------------- what words 
assignment: expression EQUAL indicator_expr;
indicator_expr: indicator_expr_simple ((OR | AND | FREE_OPERATION) indicator_expr)*;
indicator_expr_simple: (NOT? expression (compare_expr expression)?) ;
compare_expr:(EQUAL | FREE_COMPARE);
//and_or: {$getText =="OR"}? free_identifier ; //TODO
expression: identifier | number | literal | function;
function: functionName args;
//---------------*/
args: OPEN_PAREN (expression (COLON expression)*)? CLOSE_PAREN;
literal: (StringLiteralStart|HexLiteralStart|DateLiteralStart|TimeLiteralStart|TimeStampLiteralStart|UCS2LiteralStart|GraphicLiteralStart) 
	(StringContent | StringEscapedQuote | PlusOrMinus)* StringLiteralEnd;
identifier: free_identifier (FREE_CONT free_identifier)? |multipart_identifier | all;
all: symbolicConstants literal?;
//assignIdentifier: multipart_identifier;
functionName: free_identifier;
multipart_identifier: free_identifier (FREE_DOT (free_identifier | indexed_identifier))*;
indexed_identifier: free_identifier OPEN_PAREN expression CLOSE_PAREN;
opCode: free_identifier;
number: MINUS? NUMBER ;
free_identifier: (continuedIdentifier | MULT_NOSPACE? ID | NOT | FREE_BY | FREE_TO | FREE_DOWNTO |op_code) OP_E?;
//free_identifier: (continuedIdentifier | ID | NOT | FREE_BY | FREE_TO | FREE_DOWNTO |op_code) OP_E?;
continuedIdentifier: ID CONTINUATION ID ;
		
datatype: ID OPEN_PAREN NUMBER CLOSE_PAREN SEMI;
argument: ID;

symbolicConstants:
SPLAT_ALL
   | SPLAT_NONE
   | SPLAT_ILERPG
   | SPLAT_CRTBNDRPG
   | SPLAT_CRTRPGMOD
   | SPLAT_VRM
   | SPLAT_ALLG
   | SPLAT_ALLU
   | SPLAT_ALLX
   | SPLAT_BLANKS
   | SPLAT_CANCL
   | SPLAT_CYMD
   | SPLAT_CMDY
   | SPLAT_CDMY
   | SPLAT_MDY
   | SPLAT_DMY
   | SPLAT_YMD
   | SPLAT_JUL
   | SPLAT_ISO
   | SPLAT_USA
   | SPLAT_EUR
   | SPLAT_JIS
   | SPLAT_DATE
   | SPLAT_DAY
   | SPlAT_DETC
   | SPLAT_DETL
   | SPLAT_DTAARA
   | SPLAT_END
   | SPLAT_ENTRY
   | SPLAT_EQUATE
   | SPLAT_EXTDFT
   | SPLAT_EXT
   | SPLAT_FILE
   | SPLAT_GETIN
   | SPLAT_HIVAL
   | SPLAT_INIT
   | SPLAT_INDICATOR
   | SPLAT_INZSR
   | SPLAT_IN
   | SPLAT_JOBRUN
   | SPLAT_JOB
   | SPLAT_LDA
   | SPLAT_LIKE
   | SPLAT_LONGJUL
   | SPLAT_LOVAL
   | SPLAT_MONTH
   | SPLAT_NOIND
   | SPLAT_NOKEY
   | SPLAT_NULL
   | SPLAT_OFL
   | SPLAT_ON
   | SPLAT_OFF
   | SPLAT_PDA
   | SPLAT_PLACE
   | SPLAT_PSSR
   | SPLAT_ROUTINE
   | SPLAT_START
   | SPLAT_SYS
   | SPLAT_TERM
   | SPLAT_TOTC
   | SPLAT_TOTL
   | SPLAT_USER
   | SPLAT_VAR
   | SPLAT_YEAR
   | SPLAT_ZEROS
   | SPLAT_HMS
   | SPLAT_INLR
   | SPLAT_INOF
   //Durations
   | SPLAT_D
   | SPLAT_DAYS
   | SPLAT_HOURS
   | SPLAT_M
   | SPLAT_MINUTES
   | SPLAT_MONTHS
   | SPLAT_MN
   | SPLAT_MS
   | SPLAT_MSECONDS
   | SPLAT_S
   | SPLAT_SECONDS
   | SPLAT_Y
   | SPLAT_YEARS
   ;
