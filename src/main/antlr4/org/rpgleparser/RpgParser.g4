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
endSourceLine: EOS_TEXT EOS_EOL;

star_comments: COMMENT_SPEC_FIXED ;//comments COMMENTS_EOL;
free_comments: COMMENTS comments COMMENTS_EOL;
free_linecomments: COMMENTS comments;
comments: COMMENTS_TEXT; 
//just_comments: COMMENTS COMMENTS_TEXT COMMENTS_EOL;

dspec:  D_SPEC identifier (identifier|function)* FREE_SEMI free_linecomments?;
dcl_ds:  D_DS_SPEC (identifier | function)* FREE_SEMI?  
	dcl_ds_field*
	end_dcl_ds FREE_SEMI;
dcl_ds_field: ((identifier | function)+ FREE_SEMI );
end_dcl_ds: D_DS_END;
dcl_pr:  D_PR_SPEC (identifier | function)* FREE_SEMI?  
	dcl_pr_field*
	end_dcl_pr FREE_SEMI;
dcl_pr_field: ((identifier | function)+ FREE_SEMI );
end_dcl_pr: D_PR_END;
dcl_pi:  D_PI_SPEC (identifier | function)* FREE_SEMI?  
	dcl_pi_field*
	end_dcl_pi FREE_SEMI;
dcl_pi_field: ((identifier | function)+ FREE_SEMI );
end_dcl_pi: D_PI_END;
dcl_c:  D_C_SPEC identifier (identifier | expression) FREE_SEMI ;
ctl_opt:  H_SPEC (identifier | expression)* FREE_SEMI ;


dspec_fixed: D_SPEC_FIXED dspec_name EXTERNAL_DESCRIPTION DATA_STRUCTURE_TYPE DEF_TYPE FROM_POSITION TO_POSITION
	DATA_TYPE DECIMAL_POSITIONS RESERVED KEYWORDS EOL;
dspec_name: CONTINUATION_NAME* NAME;

ospec_fixed: O_SPEC_FIXED (((O_RECORD_NAME 
	O_TYPE
	(ospec_fixed_pgmdesc1 | ospec_fixed_pgmdesc2)) | ospec_fixed_pgmfield) 
	  |ospec_fixed_pgmdesc_compound)
	O_COMMENTS80?;

ospec_fixed_pgmdesc1:
	O_FETCH_OVERFLOW
	O_OUTPUT_COND O_OUTPUT_COND O_OUTPUT_COND
	O_EXCEPT_NAME
	O_SPACE_3 O_SPACE_3 O_SPACE_3 O_SPACE_3
	O_REMAINING_SPACE;

ospec_fixed_pgmdesc_compound:
	O_AND_OR
	O_OUTPUT_COND O_OUTPUT_COND O_OUTPUT_COND
	O_EXCEPT_NAME
	O_SPACE_3 O_SPACE_3 O_SPACE_3 O_SPACE_3
	O_REMAINING_SPACE;
	
ospec_fixed_pgmdesc2:
	O_ADD_DELETE
	O1_OUTPUT_COND O1_OUTPUT_COND O1_OUTPUT_COND
	O1_EXCEPT_NAME
	O1_REMAINING_SPACE2;
	
ospec_fixed_pgmfield:
	O_FIELD_RESERVED
	OF_OUTPUT_COND OF_OUTPUT_COND OF_OUTPUT_COND
	OF_FIELD_NAME 
	OF_EDIT_CODES
	OF_BLANK_AFTER
	OF_END_POSITION
	OF_DATA_FORMAT
	OF_WORDS;
	
pspec_fixed: P_SPEC_FIXED P_NAME P_BEGINEND P_KEYWORDS;
 
//dspec_continuation:	D_SPEC_FIXED CONTINUATION_NAME EOL;
fspec:  F_SPEC filename  
	fspec_expression*; 
filename: FREE_ID; 
fspec_expression: (FREE_ID (FREE_OPEN_PAREN (fspec_parm (FREE_COLON fspec_parm)*)? FREE_CLOSE_PAREN)?);
fspec_parm: expression | fspec_string;
fspec_string: (FREE_STRING_START|FREE_HEXSTRING_START|FREE_DATESTRING_START) (FREE_STRING_CONTENT | FREE_STRING_ESCAPEQUOTE )* FREE_STRING_ENDQUOTE;



fspec_fixed: F_SPEC_FIXED F_RECORD_NAME F_TYPE F_DESIGNATION F_END_OF_FILE F_ADDITION 
	F_SEQUENCE F_FORMAT F_RECORD_LEN F_LIMITS F_LEN_OF_KEY F_RECORD_ADDRESS_TYPE F_ORGANIZATION F_DEVICE F_RESERVED 
	F_KEYWORDS F_EOL;	
cspec_fixed: C_SPEC_FIXED 
	(C_CONTROL_LEVEL | C_CONTROL_LEVEL_COND ) 
	C_INDICATORS C_FACTOR 
	(cspec_fixed_standard|cspec_fixed_x2);
cspec_fixed_sql: C_SPEC_FIXED_SQL
	C_SQL_TEXT+
	C_SQL_END;
cspec_fixed_standard: C_OP_AND_EXTNDR C_FACTOR C_FACTOR C_FIELD_LENGTH 
	C_DECIMAL_POSITIONS C_RESULTING_INDICATORS cspec_fixed_comments? C_EOL;
cspec_fixed_comments:C_COMMENTS;		
//cspec_fixed_x2: C_OP_AND_EXTNDR_X2 C2_FACTOR2_CONT* C2_FACTOR2 C_EOL;
cspec_fixed_x2: C_OP_AND_EXTNDR_X2 c_free C_FREE_NEWLINE;


ispec_fixed: I_SPEC_FIXED 
	((I_FILE_NAME
	//I_LOGICAL_RELATIONSHIP
		(ispec_external_rec
		|ispec_rec)
		I_EOL
	)
	| (ispec_external_field
		I_EOL
	)
	| (I_F_DATA_ATTR
		I_F_DATETIME_SEP
		I_F_DATA_FORMAT
		I_F_FIELD_LOCATION
		I_F_DECIMAL_POSITIONS
		I_F_FIELD_NAME
		I_F_CONTROL_LEVEL
		I_F_MATCHING_FIELDS
		I_F_FIELD_RELATION
		I_F_FIELD_IND
		I_F_EOL
	))
	;
ispec_external_rec:	
			I_EXT_REC_RESERVED
			I_EXT_REC_ID_IND
			I_EXT_REC_WS;
ispec_rec:				
			I_SEQUENCE
			I_NUMBER
			I_OPTION
			I_RECORD_ID_IND
			I_RECORD_ID_CODE
			I_WS?;
ispec_external_field:
			I_EXT_FILE_NAME
			I_EXT_FILE_FIELDNAME
			I_EXT_FILE_CONTROL_LEVEL
			I_EXT_FILE_MATCHING_FIELDS
			I_EXT_FILE_INDICATORS
;

hspec_fixed: H_SPEC_FIXED 
	hspec_expression*
	H_EOL;
hspec_expression: (H_ID (H_OPEN_PAREN (hspec_parm (H_COLON hspec_parm)*)? H_CLOSE_PAREN)?);
hspec_parm: H_ID | hspec_string;
hspec_string: H_STRING_START (FREE_STRING_CONTENT | FREE_STRING_ESCAPEQUOTE )* FREE_STRING_ENDQUOTE;
blank_line: BLANK_LINE;
directive: DIRECTIVE 
		(free_directive
		| title_directive
		| DIRECTIVE_EJECT
		| space_directive
		| DIRECTIVE_SET
		| DIRECTIVE_RESTORE
		| (DIRECTIVE_COPY DIR_SPACE* DIR_OTHER_TEXT)
		| (DIRECTIVE_INCLUDE DIR_SPACE* DIR_OTHER_TEXT)
		| DIRECTIVE_EOF
		| (DIRECTIVE_DEFINE DIR_SPACE* DIR_OTHER_TEXT)
		| (DIRECTIVE_UNDEFINE DIR_SPACE* DIR_OTHER_TEXT)
		| (DIRECTIVE_IF DIR_SPACE* DIR_OTHER_TEXT)
		| (DIRECTIVE_ELSE DIR_SPACE* DIR_OTHER_TEXT)
		| (DIRECTIVE_ELSEIF DIR_SPACE* DIR_OTHER_TEXT)
		| (DIRECTIVE_ENDIF DIR_SPACE* DIR_OTHER_TEXT)
		)
	DIR_EOL;
free_directive: free_text;
space_directive: DIRECTIVE_SPACE (DIR_SPACE DIR_NUMBER)?;
free_text: DIRECTIVE_FREE;
trailing_ws: DIR_FREE_OTHER_TEXT;
title_directive: DIRECTIVE_TITLE DIR_SPACE title_text DIR_SPACE*;
title_text: (DIR_SPACE | DIR_NUMBER | DIR_OTHER_TEXT)* (DIR_NUMBER | DIR_OTHER_TEXT)+;
 
//------- Auto from here
op: op_acq | op_begsr | op_callp | op_chain | op_clear | op_close | op_commit | op_dealloc | op_delete | op_dou | op_dow | op_dsply | op_dump | op_else | op_elseif | op_enddo | op_endfor | op_endif | op_endmon | op_endsl | op_endsr | op_eval | op_evalr | op_eval_corr | op_except | op_exfmt | op_exsr | op_feod | op_for | op_force | op_if | op_in | op_iter | op_leave | op_leavesr | op_monitor | op_next | op_on_error | op_open | op_other | op_out | op_post | op_read | op_readc | op_reade | op_readp | op_readpe | op_rel | op_reset | op_return | op_rolbk | op_select | op_setgt | op_setll | op_sorta | op_test | op_unlock | op_update | op_when | op_write | op_xml_into | op_xml_sax;
op_acq: OP_ACQ OP_E? identifier identifier ;
op_begsr: OP_BEGSR identifier ;
op_callp: (OP_CALLP OP_E? )? identifier FREE_OPEN_PAREN (expression (FREE_COLON expression )* )? FREE_CLOSE_PAREN ;
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
	(FREE_ASSIGNMENT expression )? // = 1
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
op_on_error: OP_ON_ERROR (identifier (FREE_COLON identifier )* )? ;
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
op_reset: OP_RESET OP_E? (identifier )? (identifier )? identifier ;
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
exec_sql: EXEC_SQL WORDS+ SEMI_COLON ;
//sql_quoted: SINGLE_QTE (WORDS | SEMI_COLON | DOUBLE_QTE) * SINGLE_QTE ;
//sql_quoted2: DOUBLE_QTE (WORDS | SEMI_COLON |SINGLE_QTE)* DOUBLE_QTE ;

//--------------- 
baseExpression: op | assignmentExpression | expression;
assignmentExpression: expression FREE_ASSIGNMENT expression;
expression: 
	// op | // should drop op I think? 
	identifier 
	| number 
	| literal  
	| function 
	| FREE_NOT expression
	| FREE_OPEN_PAREN expression FREE_CLOSE_PAREN
	| expression (FREE_ASSIGNMENT | FREE_COMPARE) expression
	| expression (FREE_OR | FREE_AND | FREE_OPERATION | FREE_OPERATION_MINUS |FREE_OPERATION_MULT | FREE_OPERATION_MULT_NOSPACE) expression	
	;
indicator_expr: expression;
function: functionName args;

/*--------------- what words 
assignment: expression FREE_ASSIGNMENT indicator_expr;
indicator_expr: indicator_expr_simple ((FREE_OR | FREE_AND | FREE_OPERATION) indicator_expr)*;
indicator_expr_simple: (FREE_NOT? expression (compare_expr expression)?) ;
compare_expr:(FREE_ASSIGNMENT | FREE_COMPARE);
//and_or: {$getText =="OR"}? free_identifier ; //TODO
expression: identifier | number | literal | function;
function: functionName args;
//---------------*/
args: FREE_OPEN_PAREN (expression (FREE_COLON expression)*)? FREE_CLOSE_PAREN;
literal: (FREE_STRING_START|FREE_HEXSTRING_START|FREE_DATESTRING_START) (FREE_STRING_CONTENT | FREE_STRING_ESCAPEQUOTE | FREE_MINUS_PLUS)* FREE_STRING_ENDQUOTE;
identifier: free_identifier (FREE_CONT free_identifier)? |multipart_identifier | all;
all: SPLAT_ALL literal?;
//assignIdentifier: multipart_identifier;
functionName: free_identifier;
multipart_identifier: free_identifier (FREE_DOT (free_identifier | indexed_identifier))*;
indexed_identifier: free_identifier FREE_OPEN_PAREN expression FREE_CLOSE_PAREN;
opCode: free_identifier;
number: FREE_OPERATION_MINUS? FREE_NUMBER ;
free_identifier: (continuedIdentifier | FREE_OPERATION_MULT_NOSPACE? FREE_ID | FREE_NOT | FREE_BY | FREE_TO | FREE_DOWNTO |op_code) OP_E?;
continuedIdentifier: FREE_ID (FREE_CONTINUATION | C_FREE_CONTINUATION_DOTS) FREE_ID ;
		
datatype: ID OPEN_PAREN NUMBER CLOSE_PAREN SEMI;
argument: ID;

