parser grammar RpgParser;

//import FreeOpsParser;

options {   tokenVocab = RpgLexer; }

r: (dspec 
	| dcl_pr 
	| dcl_pi
	| ctl_opt
//	|dspec_continuation
//	| (dspec_continuation* dspec_fixed) 
  	| subroutine 
  	| statement
  	| procedure
)*
endSource*
;
statement:
	dcl_ds
	| dcl_c
	| (dspec_fixed)
  	| ospec_fixed
	| fspec 
	| fspec_fixed 
	| block
	| cspec_fixed
	| cspec_fixed_sql
	| ispec_fixed 
	| hspec_fixed
	| star_comments
	| free_comments
	| blank_line 
	| directive 
	| free
;

endSource: endSourceHead endSourceLine*;
endSourceHead: END_SOURCE ;
endSourceLine: EOS_Text (EOL|EOF);

star_comments: COMMENT_SPEC_FIXED ;//comments COMMENTS_EOL;
free_comments: COMMENTS comments COMMENTS_EOL;
free_linecomments: COMMENTS comments;
comments: COMMENTS_TEXT; 
//just_comments: COMMENTS COMMENTS_TEXT COMMENTS_EOL;

dspec:  DS_Standalone name=identifier datatype? FREE_SEMI free_linecomments?;
datatype: identifier args?; 
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
dcl_pi:  DS_ProcedureInterfaceStart (identifier | function)* FREE_SEMI?  
	dcl_pi_field*
	end_dcl_pi FREE_SEMI;
dcl_pi_field: ((identifier | function)+ FREE_SEMI );
end_dcl_pi: DS_ProcedureInterfaceEnd;
dcl_c:  DS_Constant identifier (identifier | expression) FREE_SEMI ;
ctl_opt: H_SPEC (identifier | expression)* FREE_SEMI ;

block:
	(csIFxx
		statement*
		endif
	)
	| ((csDOUxx | csDOWxx)
		statement*
		enddo
	)
	| ifstatement
	| selectstatement
	| forstatement
;

ifstatement:
	(beginif
	statement*
	(elseifstmt statement*)?
	(elsestmt statement*)?
	endif)
;
selectstatement:
	beginselect
	(
	   (  
		whenstatement*
		other
		)|
		whenstatement*)
	endselect
;

other:
	(CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	csOTHER
	)
	| (op_other FREE_SEMI free_linecomments? )
;



beginselect:
	(CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag 
	indicators=cs_indicators 
	factor1=factor 
	csSELECT
	)
	| (op_select FREE_SEMI free_linecomments? )
;

whenstatement:
	(csWHENxx
	| when
	)
	statement*
;

when:
	(CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag 
	indicators=cs_indicators 
	factor1=factor 
	csWHEN
	)
	| (op_when FREE_SEMI free_linecomments? )
	statement*
;
csWHENxx:
CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	 (csWHENEQ
	| csWHENNE
	| csWHENLE
	| csWHENLT
	| csWHENGE
	| csWHENGT)
	andConds=csANDxx*
	orConds=csORxx*
;

endselect:
	(
	CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	(csEND | csENDSL)
	)
	| (op_endsl FREE_SEMI free_linecomments? );
	
beginif:
	(CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag 
	indicators=cs_indicators 
	factor1=factor 
	OP_IF cs_operationExtender?
	fixedexpression=c_free
	C_FREE_NEWLINE
	)
	| (op_if FREE_SEMI free_linecomments? )
;

elseifstmt:
    (CS_FIXED
    cs_controlLevel 
    indicatorsOff=onOffIndicatorsFlag 
    indicators=cs_indicators 
    factor1=factor 
    OP_ELSEIF cs_operationExtender?
    fixedexpression=c_free
    C_FREE_NEWLINE
    )
    | (op_if FREE_SEMI free_linecomments? )
;

elsestmt:
    (CS_FIXED
    cs_controlLevel 
    indicatorsOff=onOffIndicatorsFlag 
    indicators=cs_indicators 
    factor1=factor 
    OP_ELSEIF 
    fixedexpression=c_free
    C_FREE_NEWLINE
    )
    | (op_if FREE_SEMI free_linecomments? )
;

csIFxx:
CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	 (csIFEQ
	| csIFNE
	| csIFLE
	| csIFLT
	| csIFGE
	| csIFGT)
	andConds=csANDxx*
	orConds=csORxx*
;
csDOUxx:
CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	 (csDOUEQ
	| csDOUNE
	| csDOULE
	| csDOULT
	| csDOUGE
	| csDOUGT)
	andConds=csANDxx*
	orConds=csORxx*
;

csDOWxx:
CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	 (csDOWEQ
	| csDOWNE
	| csDOWLE
	| csDOWLT
	| csDOWGE
	| csDOWGT)
	andConds=csANDxx*
	orConds=csORxx*
;
		
complexCondxx:
	csANDxx
	|csORxx
;

csANDxx:
CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	(csANDEQ
	| csANDNE
	| csANDLE
	| csANDLT
	| csANDGE
	| csANDGT
	)
;

csORxx:
CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	(csOREQ
	| csORNE
	| csORLE
	| csORLT
	| csORGE
	| csORGT
	)
	andConds=csANDxx*
;

forstatement:
    beginfor
        statement*
    endfor
;

beginfor:
    (CS_FIXED
    cs_controlLevel 
    indicatorsOff=onOffIndicatorsFlag 
    indicators=cs_indicators 
    factor1=factor
    csFOR)
    | (op_for FREE_SEMI free_linecomments? )
    ;
	
endif:
	(
	CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	(csEND | csENDIF)
	)
	| (op_endif FREE_SEMI free_linecomments? );
	
enddo:
	(
	CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	(csEND | csENDDO)
	)
	| (op_enddo FREE_SEMI free_linecomments? );	
	

endfor:
    (
    CS_FIXED
    cs_controlLevel 
    indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
    (csEND | csENDFOR)
    )
    | (op_endfor FREE_SEMI free_linecomments? ); 

dspec_fixed: DS_FIXED ds_name EXTERNAL_DESCRIPTION DATA_STRUCTURE_TYPE DEF_TYPE FROM_POSITION TO_POSITION
	DATA_TYPE DECIMAL_POSITIONS RESERVED KEYWORDS (EOL|EOF);
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
	

ps_name: PS_CONTINUATION_NAME* PS_NAME;
 
//dspec_continuation:	DS_FIXED CONTINUATION_NAME (EOL|EOF);
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

// -------- sub procedures --------  
procedure:
beginProcedure
statements=statement*
endProcedure;

beginProcedure: psBegin | freeBeginProcedure;
endProcedure: psEnd | freeEndProcedure;

psBegin: PS_FIXED ps_name PS_BEGIN PS_KEYWORDS;
freeBeginProcedure:DS_ProcedureStart identifier FREE_SEMI;
 
psEnd: PS_FIXED ps_name PS_END PS_KEYWORDS;
freeEndProcedure:DS_ProcedureEnd  identifier? FREE_SEMI;

// -------- sub routines --------  
subroutine:
begin=begsr
statement*
end=endsr
;

begsr:csBEGSR | freeBEGSR;
endsr:csENDSR | freeENDSR;

csBEGSR:
	CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	operation=OP_BEGSR
	cspec_fixed_standard_parts;
	
freeBEGSR:
	OP_BEGSR identifier
	FREE_SEMI 
	free_linecomments? 
	;	

csENDSR:
	CS_FIXED
	cs_controlLevel 
	indicatorsOff=onOffIndicatorsFlag indicators=cs_indicators factor1=factor 
	operation=OP_ENDSR
	cspec_fixed_standard_parts;

freeENDSR:
	OP_ENDSR identifier?
	FREE_SEMI 
	free_linecomments? 
	;	

	
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
cspec_fixed_standard: 
	csACQ
	| csADD
	| csADDDUR
	| csALLOC
	//| csANDxx
	//| csANDEQ
	//| csANDNE
	//| csANDLE
	//| csANDLT
	//| csANDGE
	//| csANDGT
	//| csBEGSR
	| csBITOFF
	| csBITON
	| csCABxx
	| csCABEQ
	| csCABNE
	| csCABLE
	| csCABLT
	| csCABGE
	| csCABGT
	| csCALL
	| csCALLB
	| csCASEQ
	| csCASNE
	| csCASLE
	| csCASLT
	| csCASGE
	| csCASGT
	| csCAT
	| csCHAIN
	| csCHECK
	| csCHECKR
	| csCLEAR
	| csCLOSE
	| csCOMMIT
	| csCOMP
	| csDEALLOC
	| csDEFINE
	| csDELETE
	| csDIV
	| csDO
	| csDOU
	//| csDOUEQ
	//| csDOUNE
	//| csDOULE
	//| csDOULT
	//| csDOUGE
	//| csDOUGT
	| csDOW
	| csDOWEQ
	| csDOWNE
	| csDOWLE
	| csDOWLT
	| csDOWGE
	| csDOWGT
	| csDSPLY
	| csDUMP
	| csELSE
	| csELSEIF
	| csEND
	| csENDCS
	| csENDDO
	//| csENDFOR
	//| csENDIF
	| csENDMON
	| csENDSL
	//| csENDSR
	| csEVAL
	| csEVAL_CORR
	| csEVALR
	| csEXCEPT
	| csEXFMT
	| csEXSR
	| csEXTRCT
	| csFEOD
	//| csFOR
	| csFORCE
	| csGOTO
	//| csIF
	//| csIFEQ
	//| csIFNE
	//| csIFLE
	//| csIFLT
	//| csIFGE
	//| csIFGT
	| csIN
	| csITER
	| csKLIST
	| csLEAVE
	| csLEAVESR
	| csLOOKUP
	| csMHHZO
	| csMHLZO
	| csMLHZO
	| csMLLZO
	| csMONITOR
	| csMOVE
	| csMOVEA
	| csMOVEL
	| csMULT
	//| csMVR
	| csNEXT
	| csOCCUR
	| csON_ERROR
	| csOPEN
	//| csOREQ
	//| csORNE
	//| csORLE
	//| csORLT
	//| csORGE
	//| csORGT
	| csOTHER
	| csOUT
	//| csPARM
	| csPLIST
	| csPOST
	| csREAD
	| csREADC
	| csREADE
	| csREADP
	| csREADPE
	| csREALLOC
	| csREL
	| csRESET
	| csRETURN
	| csROLBK
	| csSCAN
	//| csSELECT
	| csSETGT
	| csSETLL
	| csSETOFF
	| csSETON
	| csSHTDN
	| csSORTA
	| csSQRT
	| csSUB
	| csSUBDUR
	| csSUBST
	| csTAG
	| csTEST
	| csTESTB
	| csTESTN
	| csTESTZ
	| csTIME
	| csUNLOCK
	| csUPDATE
	//| csWHEN
	//| csWHENEQ
	//| csWHENNE
	//| csWHENLE
	//| csWHENLT
	//| csWHENGE
	//| csWHENGT
	| csWRITE
	| csXFOOT
	| csXLATE
	| csXML_INTO
	| csXML_SAX
	| csZ_ADD
	| csZ_SUB
	|(operation=CS_OperationAndExtender
	operationExtender=cs_operationExtender?
	cspec_fixed_standard_parts);
cspec_fixed_standard_parts: 
	factor2=factor
	result=resultType 
	len=CS_FieldLength 
	decimalPositions=CS_DecimalPositions 
	hi=resultIndicator 
	lo=resultIndicator
	eq=resultIndicator 
	cs_fixed_comments? (EOL|EOF);	

/*
 * Fixed op codes 
 */	
csACQ:
	operation=OP_ACQ
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;	
csADD:
	operation=OP_ADD
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;	
csADDDUR:
	operation=OP_ADDDUR
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csALLOC:
	operation=OP_ALLOC
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csANDEQ:
	operation=OP_ANDEQ
	cspec_fixed_standard_parts;
csANDNE:
	operation=OP_ANDNE
	cspec_fixed_standard_parts;
csANDLE:
	operation=OP_ANDLE
	cspec_fixed_standard_parts;
csANDLT:
	operation=OP_ANDLT
	cspec_fixed_standard_parts;
csANDGE:
	operation=OP_ANDGE
	cspec_fixed_standard_parts;
csANDGT:
	operation=OP_ANDGT
	cspec_fixed_standard_parts;
//csBEGSR:
//	operation=OP_BEGSR
//	cspec_fixed_standard_parts;
csBITOFF:
	operation=OP_BITOFF
	cspec_fixed_standard_parts;
csBITON:
	operation=OP_BITON
	cspec_fixed_standard_parts;
csCABxx:
	operation=OP_CABxx
	cspec_fixed_standard_parts;
csCABEQ:
	operation=OP_CABEQ
	cspec_fixed_standard_parts;
csCABNE:
	operation=OP_CABNE
	cspec_fixed_standard_parts;
csCABLE:
	operation=OP_CABLE
	cspec_fixed_standard_parts;
csCABLT:
	operation=OP_CABLT
	cspec_fixed_standard_parts;
csCABGE:
	operation=OP_CABGE
	cspec_fixed_standard_parts;
csCABGT:
	operation=OP_CABGT
	cspec_fixed_standard_parts;
csCALL:
	operation=OP_CALL
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts
	csPARM*;
csCALLB:
	operation=OP_CALLB
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts
	csPARM*;
csCALLP:
	operation=OP_CALLP
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csCASEQ:
	operation=OP_CASEQ
	cspec_fixed_standard_parts;
csCASNE:
	operation=OP_CASNE
	cspec_fixed_standard_parts;
csCASLE:
	operation=OP_CASLE
	cspec_fixed_standard_parts;
csCASLT:
	operation=OP_CASLT
	cspec_fixed_standard_parts;
csCASGE:
	operation=OP_CASGE
	cspec_fixed_standard_parts;
csCASGT:
	operation=OP_CASGT
	cspec_fixed_standard_parts;
csCAT:
	operation=OP_CAT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csCHAIN:
	operation=OP_CHAIN
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csCHECK:
	operation=OP_CHECK
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csCHECKR:
	operation=OP_CHECKR
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csCLEAR:
	operation=OP_CLEAR
	cspec_fixed_standard_parts;
csCLOSE:
	operation=OP_CLOSE
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csCOMMIT:
	operation=OP_COMMIT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csCOMP:
	operation=OP_COMP
	cspec_fixed_standard_parts;
csDEALLOC:
	operation=OP_DEALLOC
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csDEFINE:
	operation=OP_DEFINE
	cspec_fixed_standard_parts;
csDELETE:
	operation=OP_DELETE
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csDIV:
	operation=OP_DIV
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts
	csMVR?;
csDO:
	operation=OP_DO
	cspec_fixed_standard_parts;
csDOU:
	operation=OP_DOU
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csDOUEQ:
	operation=OP_DOUEQ
	cspec_fixed_standard_parts;
csDOUNE:
	operation=OP_DOUNE
	cspec_fixed_standard_parts;
csDOULE:
	operation=OP_DOULE
	cspec_fixed_standard_parts;
csDOULT:
	operation=OP_DOULT
	cspec_fixed_standard_parts;
csDOUGE:
	operation=OP_DOUGE
	cspec_fixed_standard_parts;
csDOUGT:
	operation=OP_DOUGT
	cspec_fixed_standard_parts;
csDOW:
	operation=OP_DOW
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csDOWEQ:
	operation=OP_DOWEQ
	cspec_fixed_standard_parts;
csDOWNE:
	operation=OP_DOWNE
	cspec_fixed_standard_parts;
csDOWLE:
	operation=OP_DOWLE
	cspec_fixed_standard_parts;
csDOWLT:
	operation=OP_DOWLT
	cspec_fixed_standard_parts;
csDOWGE:
	operation=OP_DOWGE
	cspec_fixed_standard_parts;
csDOWGT:
	operation=OP_DOWGT
	cspec_fixed_standard_parts;
csDSPLY:
	operation=OP_DSPLY
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csDUMP:
	operation=OP_DUMP
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csELSE:
	operation=OP_ELSE
	cspec_fixed_standard_parts;
csELSEIF:
	operation=OP_ELSEIF
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csEND:
	operation=OP_END
	cspec_fixed_standard_parts;
csENDCS:
	operation=OP_ENDCS
	cspec_fixed_standard_parts;
csENDDO:
	operation=OP_ENDDO
	cspec_fixed_standard_parts;
csENDFOR:
	operation=OP_ENDFOR
	cspec_fixed_standard_parts;
csENDIF:
	operation=OP_ENDIF
	cspec_fixed_standard_parts;
csENDMON:
	operation=OP_ENDMON
	cspec_fixed_standard_parts;
csENDSL:
	operation=OP_ENDSL
	cspec_fixed_standard_parts;
//csENDSR:
//	operation=OP_ENDSR
//	cspec_fixed_standard_parts;
csEVAL:
	operation=OP_EVAL
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csEVAL_CORR:
	operation=OP_EVAL_CORR
	fixedexpression=c_free C_FREE_NEWLINE;
csEVALR:
	operation=OP_EVALR
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csEXCEPT:
	operation=OP_EXCEPT
	cspec_fixed_standard_parts;
csEXFMT:
	operation=OP_EXFMT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csEXSR:
	operation=OP_EXSR
	cspec_fixed_standard_parts;
csEXTRCT:
	operation=OP_EXTRCT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csFEOD:
	operation=OP_FEOD
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csFOR:
	operation=OP_FOR operationExtender=cs_operationExtender? expression   //For(E) I
    (EQUAL expression )? // = 1
    (FREE_BY expression )?    // By 1
    ((FREE_TO | FREE_DOWNTO) expression )? C_FREE_NEWLINE; // TO 10 ;
csFORCE:
	operation=OP_FORCE
	cspec_fixed_standard_parts;
csGOTO:
	operation=OP_GOTO
	cspec_fixed_standard_parts;
csIF:
	operation=OP_IF
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csIFEQ:
	operation=OP_IFEQ
	cspec_fixed_standard_parts;
csIFNE:
	operation=OP_IFNE
	cspec_fixed_standard_parts;
csIFLE:
	operation=OP_IFLE
	cspec_fixed_standard_parts;
csIFLT:
	operation=OP_IFLT
	cspec_fixed_standard_parts;
csIFGE:
	operation=OP_IFGE
	cspec_fixed_standard_parts;
csIFGT:
	operation=OP_IFGT
	cspec_fixed_standard_parts;
csIN:
	operation=OP_IN
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csITER:
	operation=OP_ITER
	cspec_fixed_standard_parts;
csKLIST:
	operation=OP_KLIST
	cspec_fixed_standard_parts
	csKFLD*;
csKFLD:
	CS_FIXED
	BlankIndicator
	BlankFlag 
	BlankIndicator
	CS_BlankFactor
	operation=OP_KFLD
	cspec_fixed_standard_parts;
csLEAVE:
	operation=OP_LEAVE
	cspec_fixed_standard_parts;
csLEAVESR:
	operation=OP_LEAVESR
	cspec_fixed_standard_parts;
csLOOKUP:
	operation=OP_LOOKUP
	cspec_fixed_standard_parts;
csMHHZO:
	operation=OP_MHHZO
	cspec_fixed_standard_parts;
csMHLZO:
	operation=OP_MHLZO
	cspec_fixed_standard_parts;
csMLHZO:
	operation=OP_MLHZO
	cspec_fixed_standard_parts;
csMLLZO:
	operation=OP_MLLZO
	cspec_fixed_standard_parts;
csMONITOR:
	operation=OP_MONITOR
	cspec_fixed_standard_parts;
csMOVE:
	operation=OP_MOVE
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csMOVEA:
	operation=OP_MOVEA
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csMOVEL:
	operation=OP_MOVEL
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csMULT:
	operation=OP_MULT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csMVR:
	CS_FIXED
	BlankIndicator
	BlankFlag 
	BlankIndicator
	CS_BlankFactor
	operation=OP_MVR
	cspec_fixed_standard_parts;
csNEXT:
	operation=OP_NEXT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csOCCUR:
	operation=OP_OCCUR
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csON_ERROR:
	operation=OP_ON_ERROR
	fixedexpression=c_free C_FREE_NEWLINE;
csOPEN:
	operation=OP_OPEN
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csOREQ:
	operation=OP_OREQ
	cspec_fixed_standard_parts;
csORNE:
	operation=OP_ORNE
	cspec_fixed_standard_parts;
csORLE:
	operation=OP_ORLE
	cspec_fixed_standard_parts;
csORLT:
	operation=OP_ORLT
	cspec_fixed_standard_parts;
csORGE:
	operation=OP_ORGE
	cspec_fixed_standard_parts;
csORGT:
	operation=OP_ORGT
	cspec_fixed_standard_parts;
csOTHER:
	operation=OP_OTHER
	cspec_fixed_standard_parts;
csOUT:
	operation=OP_OUT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csPARM:
	CS_FIXED
	BlankIndicator
	BlankFlag 
	BlankIndicator
	factor1=factor
	operation=OP_PARM
	cspec_fixed_standard_parts;
csPLIST:
	operation=OP_PLIST
	cspec_fixed_standard_parts
	csPARM*;
csPOST:
	operation=OP_POST
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csREAD:
	operation=OP_READ
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csREADC:
	operation=OP_READC
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csREADE:
	operation=OP_READE
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csREADP:
	operation=OP_READP
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csREADPE:
	operation=OP_READPE
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csREALLOC:
	operation=OP_REALLOC
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csREL:
	operation=OP_REL
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csRESET:
	operation=OP_RESET
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csRETURN:
	operation=OP_RETURN
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free? C_FREE_NEWLINE;
csROLBK:
	operation=OP_ROLBK
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csSCAN:
	operation=OP_SCAN
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csSELECT:
	operation=OP_SELECT
	cspec_fixed_standard_parts;
csSETGT:
	operation=OP_SETGT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csSETLL:
	operation=OP_SETLL
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csSETOFF:
	operation=OP_SETOFF
	cspec_fixed_standard_parts;
csSETON:
	operation=OP_SETON
	cspec_fixed_standard_parts;
csSHTDN:
	operation=OP_SHTDN
	cspec_fixed_standard_parts;
csSORTA:
	operation=OP_SORTA
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csSQRT:
	operation=OP_SQRT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csSUB:
	operation=OP_SUB
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csSUBDUR:
	operation=OP_SUBDUR
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csSUBST:
	operation=OP_SUBST
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csTAG:
	operation=OP_TAG
	cspec_fixed_standard_parts;
csTEST:
	operation=OP_TEST
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csTESTB:
	operation=OP_TESTB
	cspec_fixed_standard_parts;
csTESTN:
	operation=OP_TESTN
	cspec_fixed_standard_parts;
csTESTZ:
	operation=OP_TESTZ
	cspec_fixed_standard_parts;
csTIME:
	operation=OP_TIME
	cspec_fixed_standard_parts;
csUNLOCK:
	operation=OP_UNLOCK
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csUPDATE:
	operation=OP_UPDATE
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csWHEN:
	OP_WHEN 
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csWHENEQ:
	operation=OP_WHENEQ
	cspec_fixed_standard_parts;
csWHENNE:
	operation=OP_WHENNE
	cspec_fixed_standard_parts;
csWHENLE:
	operation=OP_WHENLE
	cspec_fixed_standard_parts;
csWHENLT:
	operation=OP_WHENLT
	cspec_fixed_standard_parts;
csWHENGE:
	operation=OP_WHENGE
	cspec_fixed_standard_parts;
csWHENGT:
	operation=OP_WHENGT
	cspec_fixed_standard_parts;
csWRITE:
	operation=OP_WRITE
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csXFOOT:
	operation=OP_XFOOT
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csXLATE:
	operation=OP_XLATE
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csXML_INTO:
	operation=OP_XML_INTO
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csXML_SAX:
	operation=OP_XML_SAX
	operationExtender=cs_operationExtender? 
	fixedexpression=c_free C_FREE_NEWLINE;
csZ_ADD:
	operation=OP_Z_ADD
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csZ_SUB:
	operation=OP_Z_SUB
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
			
cs_operationExtender:
  OPEN_PAREN
  extender=CS_OperationAndExtender
  extender2=CS_OperationAndExtender?
  extender3=CS_OperationAndExtender?
  extender4=CS_OperationAndExtender?
  CLOSE_PAREN;	
factor:
   content=factorContent (COLON (content2=factorContent | constant2=symbolicConstants))? | CS_BlankFactor | constant=symbolicConstants literal?;
   
factorContent:
CS_FactorContent | literal;

resultType:	
   CS_FactorContent | CS_BlankFactor;
cs_fixed_comments:CS_Comments;		
//cs_fixed_x2: CS_OperationAndExtendedFactor2 C2_FACTOR2_CONT* C2_FACTOR2 C_EOL;
cspec_fixed_x2: csOperationAndExtendedFactor2 fixedexpression=c_free C_FREE_NEWLINE;

csOperationAndExtendedFactor2:
	operation=OP_EVAL
	//|operation=OP_IF
	|operation=OP_CALLP operationExtender=cs_operationExtender?
	|operation=OP_DOW
	|operation=OP_ELSEIF;

ispec_fixed: IS_FIXED 
	((IS_FileName
	//IS_LogicalRelationship
		(is_external_rec
		|is_rec)
		(EOL|EOF)
	)
	| (is_external_field
		(EOL|EOF)
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
		(EOL|EOF)
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
	(EOL|EOF);
hs_expression: (ID (OPEN_PAREN (hs_parm (COLON hs_parm)*)? CLOSE_PAREN)?);
hs_parm: ID | hs_string;
hs_string: StringLiteralStart (StringContent | StringEscapedQuote )* StringLiteralEnd;
blank_line: BLANK_LINE;
directive: DIRECTIVE 
		( beginfree_directive
		| endfree_directive
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
	(EOL|EOF);
space_directive: DIR_SPACE (WS NUMBER)?;
beginfree_directive: DIR_FREE;
endfree_directive: DIR_ENDFREE;
trailing_ws: DIR_FREE_OTHER_TEXT;
//title_directive: DIR_TITLE WS title_text WS*;
//title_directive: DIR_TITLE (WS* DIR_OtherText)?;
title_directive: DIR_TITLE WS title_text*;
//title_text: (WS | NUMBER | DIR_OtherText) (NUMBER | DIR_OtherText);
title_text: (WS | NUMBER | DIR_OtherText);
 
//------- Auto from here
op: op_acq 
//	| op_begsr 
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
	//| op_endif 
	| op_endmon 
	| op_endsl 
	//| op_endsr 
	| op_eval 
	| op_evalr
	| op_eval_corr 
	| op_except 
	| op_exfmt 
	| op_exsr 
	| op_feod 
	| op_for 
	| op_force 
	//| op_if 
	| op_in 
	| op_iter 
	| op_leave 
	| op_leavesr 
	| op_monitor 
	| op_next 
	| op_on_error 
	| op_open 
	//| op_other 
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
	//| op_select 
	| op_setgt 
	| op_setll 
	| op_sorta 
	| op_test 
	| op_unlock 
	| op_update 
	//| op_when 
	| op_write 
	| op_xml_into 
	| op_xml_sax;
op_acq: OP_ACQ cs_operationExtender? identifier identifier ;
//op_begsr: OP_BEGSR identifier ;
op_callp: (OP_CALLP cs_operationExtender? )? identifier OPEN_PAREN (expression (COLON expression )* )? CLOSE_PAREN ;
op_chain: OP_CHAIN cs_operationExtender? search_arg identifier (identifier )? ;
op_clear: OP_CLEAR (identifier )? (identifier )? expression ;
op_close: OP_CLOSE cs_operationExtender? identifier ;
op_commit: OP_COMMIT cs_operationExtender? (identifier )? ;
op_dealloc: OP_DEALLOC cs_operationExtender? identifier ;
op_delete: OP_DELETE cs_operationExtender? (search_arg )? identifier ;
op_dou: OP_DOU cs_operationExtender? indicator_expr ;
op_dow: OP_DOW cs_operationExtender? indicator_expr ;
op_dsply: OP_DSPLY cs_operationExtender? (expression (expression (expression )? )? )? ;
op_dump: OP_DUMP cs_operationExtender? (identifier )? ;
op_else: OP_ELSE ;
op_elseif: OP_ELSEIF cs_operationExtender? indicator_expr ;
op_enddo: OP_ENDDO ;
op_endfor: OP_ENDFOR ;
op_endif: OP_ENDIF ;
op_endmon: OP_ENDMON ;
op_endsl: OP_ENDSL ;
//op_endsr: OP_ENDSR (identifier )? ;
op_eval: (OP_EVAL cs_operationExtender? )? assignmentExpression ;
op_evalr: OP_EVALR cs_operationExtender? assignmentExpression ;
op_eval_corr: OP_EVAL_CORR cs_operationExtender? assignmentExpression ;
op_except: OP_EXCEPT (identifier )? ;
op_exfmt: OP_EXFMT cs_operationExtender? identifier (identifier )? ;
op_exsr: OP_EXSR identifier ;
op_feod: OP_FEOD cs_operationExtender? identifier ;
op_for: OP_FOR cs_operationExtender? expression   //For(E) I
	(EQUAL expression )? // = 1
	(FREE_BY expression )?    // By 1
	((FREE_TO | FREE_DOWNTO) expression )?; // TO 10 ;
op_force: OP_FORCE identifier ;
op_if: OP_IF cs_operationExtender? expression ;
op_in: OP_IN cs_operationExtender? (identifier )? identifier ;
op_iter: OP_ITER ;
op_leave: OP_LEAVE ;
op_leavesr: OP_LEAVESR ;
op_monitor: OP_MONITOR ;
op_next: OP_NEXT cs_operationExtender? (literal | identifier) identifier ;
op_on_error: OP_ON_ERROR (identifier (COLON identifier )* )? ;
op_open: OP_OPEN cs_operationExtender? identifier ;
op_other: OP_OTHER ;
op_out: OP_OUT cs_operationExtender? (identifier )? identifier ;
op_post: OP_POST cs_operationExtender? (literal | identifier)? identifier ;
op_read: OP_READ cs_operationExtender? identifier (identifier )? ;
op_readc: OP_READC cs_operationExtender? identifier (identifier )? ;
op_reade: OP_READE cs_operationExtender? search_arg identifier (identifier )? ;
op_readp: OP_READP cs_operationExtender? identifier (identifier )? ;
op_readpe: OP_READPE cs_operationExtender? search_arg identifier (identifier )? ;
op_rel: OP_REL cs_operationExtender? (literal | identifier) identifier ;
op_reset2: OP_RESET cs_operationExtender? identifier  OPEN_PAREN MULT_NOSPACE?  CLOSE_PAREN ;
op_reset: OP_RESET cs_operationExtender?  (identifier)? (identifier )? identifier ;
op_return: OP_RETURN cs_operationExtender? expression? ;
op_rolbk: OP_ROLBK cs_operationExtender? ;
op_select: OP_SELECT ;
op_setgt: OP_SETGT cs_operationExtender? search_arg identifier ;
op_setll: OP_SETLL cs_operationExtender? search_arg identifier ;
op_sorta: OP_SORTA cs_operationExtender? (identifier | bif_subarr);
op_test: OP_TEST cs_operationExtender? (identifier )? identifier ;
op_unlock: OP_UNLOCK cs_operationExtender? identifier ;
op_update: OP_UPDATE cs_operationExtender? identifier (identifier | bif_fields )? ;
op_when: OP_WHEN cs_operationExtender? indicator_expr ;
op_write: OP_WRITE cs_operationExtender? identifier (identifier )? ;
op_xml_into: OP_XML_INTO cs_operationExtender? identifier expression ;
op_xml_sax: OP_XML_SAX cs_operationExtender? identifier identifier ;
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

bif: bif_abs
      | bif_addr
      | bif_alloc
      | bif_bitand
      | bif_bitnot
      | bif_bitor
      | bif_bitxor
      | bif_char
      | bif_check
      | bif_checkr
      | bif_date
      | bif_days
      | bif_dec
      | bif_dech
      | bif_decpos
      | bif_diff
      | bif_div
      | bif_editc
      | bif_editflt
      | bif_editw
      | bif_elem
      | bif_eof
      | bif_equal
      | bif_error
      | bif_fields
      | bif_float
      | bif_found
      | bif_graph
      | bif_handler
      | bif_hours
      | bif_int
      | bif_inth
      | bif_kds
      | bif_len
      | bif_lookup
      | bif_lookuplt
      | bif_lookuple
      | bif_lookupgt
      | bif_lookupge
      | bif_minutes
      | bif_months
      | bif_mseconds
      | bif_nullind
      | bif_occur
      | bif_open
      | bif_paddr
      | bif_parms
      | bif_parmnum
      | bif_realloc
      | bif_rem
      | bif_replace
      | bif_scan
      | bif_scanrpl
      | bif_seconds
      | bif_shtdn
      | bif_size
      | bif_sqrt
      | bif_status
      | bif_str
      | bif_subarr
      | bif_subdt
      | bif_subst
      | bif_this
      | bif_time
      | bif_timestamp
      | bif_tlookup
      | bif_tlookuplt
      | bif_tlookuple
      | bif_tlookupgt
      | bif_tlookupge
      | bif_trim
      | bif_triml
      | bif_trimr
      | bif_ucs2
      | bif_uns
      | bif_unsh
      | bif_xfoot
      | bif_xlate
      | bif_xml
      | bif_years;
      
      
optargs: (OPEN_PAREN (expression (COLON expression)*)? CLOSE_PAREN)?;

// TODO 
bif_charformat: symbolicConstants; //SPLAT_JOBRUN | SPLAT_HMS | SPLAT_ISO | SPLAT_EUR | SPLAT_DMY ..
bif_dateformat: symbolicConstants;
bif_timeformat: symbolicConstants;
bif_diffformat: symbolicConstants;
bif_editccurrency : SPLAT_ASTFILL | SPLAT_CURSYM | literal;
bif_lookupargs: OPEN_PAREN arg=expression (COLON array=expression)? (COLON startindex=expression)? (COLON numberelements=expression)? CLOSE_PAREN;
bif_subdtargs: symbolicConstants;
bif_timestampargs : SPLAT_ISO | SPLAT_ISO0 ;
bif_tlookupargs: OPEN_PAREN arg=expression COLON searchtable=expression (COLON alttable=expression)? CLOSE_PAREN;


bif_abs: BIF_ABS OPEN_PAREN numericexpression=expression CLOSE_PAREN;
bif_addr: BIF_ADDR OPEN_PAREN variable=expression (COLON stardata=SPLAT_DATA)? CLOSE_PAREN;
bif_alloc: BIF_ALLOC OPEN_PAREN num=expression CLOSE_PAREN;
bif_bitand: BIF_BITAND OPEN_PAREN expression COLON expression (COLON expression)* CLOSE_PAREN;
bif_bitnot: BIF_BITNOT OPEN_PAREN expression CLOSE_PAREN;
bif_bitor: BIF_BITOR OPEN_PAREN expression COLON expression (COLON expression)* CLOSE_PAREN;
bif_bitxor: BIF_BITXOR OPEN_PAREN expression COLON expression  CLOSE_PAREN;
bif_char: BIF_CHAR OPEN_PAREN expression (COLON format=bif_charformat)? CLOSE_PAREN;
bif_check: BIF_CHECK OPEN_PAREN comparator=expression COLON base=expression (COLON start=expression)? CLOSE_PAREN;
bif_checkr: BIF_CHECKR OPEN_PAREN comparator=expression COLON base=expression (COLON start=expression)? CLOSE_PAREN;
bif_date: BIF_DATE OPEN_PAREN expression (COLON dateformat=bif_dateformat)? CLOSE_PAREN;
bif_days: BIF_DAYS OPEN_PAREN numberexpression=expression CLOSE_PAREN;
bif_dec: BIF_DEC OPEN_PAREN expression (COLON expression)? (COLON expression)? CLOSE_PAREN;
bif_dech: BIF_DECH OPEN_PAREN expression COLON expression COLON expression CLOSE_PAREN;
bif_decpos: BIF_DECPOS OPEN_PAREN numericexpression=expression CLOSE_PAREN;
bif_diff: BIF_DIFF OPEN_PAREN op1=expression COLON op2=expression COLON format=bif_diffformat CLOSE_PAREN;
bif_div: BIF_DIV OPEN_PAREN numerator=expression COLON denominator=expression CLOSE_PAREN;
bif_editc: BIF_EDITC OPEN_PAREN numeric=expression COLON editcode=expression (COLON currency=bif_editccurrency)? CLOSE_PAREN;
bif_editflt: BIF_EDITFLT OPEN_PAREN numericexpression=expression CLOSE_PAREN;
bif_editw: BIF_EDITW OPEN_PAREN numeric=expression COLON editword=expression CLOSE_PAREN;
bif_elem: BIF_ELEM OPEN_PAREN expression CLOSE_PAREN;
bif_eof: BIF_EOF (OPEN_PAREN filenameident=identifier CLOSE_PAREN)?;
bif_equal: BIF_EQUAL (OPEN_PAREN filenameident=identifier CLOSE_PAREN)?;
bif_error: BIF_ERROR (OPEN_PAREN CLOSE_PAREN)?;
bif_fields: BIF_FIELDS OPEN_PAREN identifier (COLON identifier)* CLOSE_PAREN;
bif_float: BIF_FLOAT OPEN_PAREN expression CLOSE_PAREN;
bif_found: BIF_FOUND (OPEN_PAREN filenameident=identifier CLOSE_PAREN)?;
bif_graph: BIF_GRAPH OPEN_PAREN expression (COLON identifier)? CLOSE_PAREN;
bif_handler: BIF_HANDLER OPEN_PAREN handlingprocedure=expression COLON communicationarea=expression CLOSE_PAREN;
bif_hours: BIF_HOURS OPEN_PAREN numberexpression=expression CLOSE_PAREN;
bif_int: BIF_INT OPEN_PAREN expression CLOSE_PAREN;
bif_inth: BIF_INTH OPEN_PAREN expression CLOSE_PAREN;
bif_kds: BIF_KDS OPEN_PAREN datastructure=expression (COLON numkeys=expression)? CLOSE_PAREN;
bif_len: BIF_LEN OPEN_PAREN expression (COLON starmax=SPLAT_MAX)? CLOSE_PAREN;
bif_lookup: BIF_LOOKUP bif_lookupargs;
bif_lookuplt: BIF_LOOKUPLT bif_lookupargs;
bif_lookuple: BIF_LOOKUPLE bif_lookupargs;
bif_lookupgt: BIF_LOOKUPGT bif_lookupargs;
bif_lookupge: BIF_LOOKUPGE bif_lookupargs;
bif_minutes: BIF_MINUTES OPEN_PAREN minutes=expression CLOSE_PAREN;
bif_months: BIF_MONTHS OPEN_PAREN numberexpression=expression CLOSE_PAREN;
bif_mseconds: BIF_MSECONDS OPEN_PAREN numberexpression=expression CLOSE_PAREN;
bif_nullind: BIF_NULLIND OPEN_PAREN fieldname=identifier CLOSE_PAREN;
bif_occur: BIF_OCCUR OPEN_PAREN dsnname=identifier CLOSE_PAREN;
bif_open: BIF_OPEN OPEN_PAREN filenameident=identifier CLOSE_PAREN;
bif_paddr: BIF_PADDR OPEN_PAREN identifier CLOSE_PAREN;
bif_parms: BIF_PARMS;
bif_parmnum: BIF_PARMNUM OPEN_PAREN identifier CLOSE_PAREN;
bif_realloc: BIF_REALLOC OPEN_PAREN ptr=identifier COLON num=expression CLOSE_PAREN;
bif_rem: BIF_REM OPEN_PAREN numerator=expression COLON denominator=expression CLOSE_PAREN;
bif_replace: BIF_REPLACE OPEN_PAREN replacement=expression COLON source=expression (COLON start=expression (COLON length=expression)? )? CLOSE_PAREN;
bif_scan: BIF_SCAN OPEN_PAREN searcharg=expression COLON source=expression (COLON start=expression )? CLOSE_PAREN;
bif_scanrpl: BIF_SCANRPL OPEN_PAREN scanstring=expression COLON replacement=expression COLON source=expression (COLON start=expression (COLON length=expression)? )? CLOSE_PAREN;
bif_seconds: BIF_SECONDS OPEN_PAREN numberexpression=expression CLOSE_PAREN;
bif_shtdn: BIF_SHTDN;
bif_size: BIF_SIZE OPEN_PAREN expression (COLON SPLAT_ALL)? CLOSE_PAREN;
bif_sqrt: BIF_SQRT OPEN_PAREN numeric=expression CLOSE_PAREN;
bif_status: BIF_STATUS (OPEN_PAREN filenameident=identifier CLOSE_PAREN)?;
bif_str: BIF_STR OPEN_PAREN basingpointer=expression (COLON maxlength=expression)? CLOSE_PAREN;
bif_subarr: BIF_SUBARR OPEN_PAREN array=expression COLON start=expression (COLON numberelements=expression)? CLOSE_PAREN;
bif_subdt: BIF_SUBDT OPEN_PAREN value=expression COLON format=bif_subdtargs CLOSE_PAREN;
bif_subst: BIF_SUBST OPEN_PAREN string=expression COLON start=expression (COLON length=expression )? CLOSE_PAREN;
bif_this: BIF_THIS;
bif_time: BIF_TIME (OPEN_PAREN expression (COLON timeformat=bif_timeformat)? CLOSE_PAREN)?;
bif_timestamp: BIF_TIMESTAMP (OPEN_PAREN expression (COLON format=bif_timestampargs)? CLOSE_PAREN)?;
bif_tlookup: BIF_TLOOKUP bif_tlookupargs;
bif_tlookuplt: BIF_TLOOKUPLT bif_tlookupargs;
bif_tlookuple: BIF_TLOOKUPLE bif_tlookupargs;
bif_tlookupgt: BIF_TLOOKUPGT bif_tlookupargs;
bif_tlookupge: BIF_TLOOKUPGE bif_tlookupargs;
bif_trim: BIF_TRIM OPEN_PAREN string=expression (COLON trimcharacters=expression)? CLOSE_PAREN;
bif_triml: BIF_TRIML OPEN_PAREN string=expression (COLON trimcharacters=expression)? CLOSE_PAREN;
bif_trimr: BIF_TRIMR OPEN_PAREN string=expression (COLON trimcharacters=expression)? CLOSE_PAREN;
bif_ucs2: BIF_UCS2 OPEN_PAREN expression CLOSE_PAREN;
bif_uns: BIF_UNS OPEN_PAREN expression CLOSE_PAREN;
bif_unsh: BIF_UNSH OPEN_PAREN expression CLOSE_PAREN;
bif_xfoot: BIF_XFOOT OPEN_PAREN arrayexpression=expression CLOSE_PAREN;
bif_xlate: BIF_XLATE OPEN_PAREN from=expression COLON to=expression COLON string=expression (COLON startpos=expression)? CLOSE_PAREN;
bif_xml: BIF_XML OPEN_PAREN xmldocument=expression (COLON options=expression)? CLOSE_PAREN;
bif_years: BIF_YEARS OPEN_PAREN numberexpression=expression CLOSE_PAREN;

bif_code: BIF_ABS
	  | BIF_ADDR
	  | BIF_ALLOC
	  | BIF_BITAND
	  | BIF_BITNOT
	  | BIF_BITOR
	  | BIF_BITXOR
	  | BIF_CHAR
	  | BIF_CHECK
	  | BIF_CHECKR
	  | BIF_DATE
	  | BIF_DAYS
	  | BIF_DEC
	  | BIF_DECH
	  | BIF_DECPOS
	  | BIF_DIFF
	  | BIF_DIV
	  | BIF_EDITC
	  | BIF_EDITFLT
	  | BIF_EDITW
	  | BIF_ELEM
	  | BIF_EOF
	  | BIF_EQUAL
	  | BIF_ERROR
	  | BIF_FIELDS
	  | BIF_FLOAT
	  | BIF_FOUND
	  | BIF_GRAPH
	  | BIF_HANDLER
	  | BIF_HOURS
	  | BIF_INT
	  | BIF_INTH
	  | BIF_KDS
	  | BIF_LEN
	  | BIF_LOOKUP
	  | BIF_LOOKUPLT
	  | BIF_LOOKUPLE
	  | BIF_LOOKUPGT
	  | BIF_LOOKUPGE
	  | BIF_MINUTES
	  | BIF_MONTHS
	  | BIF_MSECONDS
	  | BIF_NULLIND
	  | BIF_OCCUR
	  | BIF_OPEN
	  | BIF_PADDR
	  | BIF_PARMS
	  | BIF_PARMNUM
	  | BIF_REALLOC
	  | BIF_REM
	  | BIF_REPLACE
	  | BIF_SCAN
	  | BIF_SCANRPL
	  | BIF_SECONDS
	  | BIF_SHTDN
	  | BIF_SIZE
	  | BIF_SQRT
	  | BIF_STATUS
	  | BIF_STR
	  | BIF_SUBARR
	  | BIF_SUBDT
	  | BIF_SUBST
	  | BIF_THIS
	  | BIF_TIME
	  | BIF_TIMESTAMP
	  | BIF_TLOOKUP
	  | BIF_TLOOKUPLT
	  | BIF_TLOOKUPLE
	  | BIF_TLOOKUPGT
	  | BIF_TLOOKUPGE
	  | BIF_TRIM
	  | BIF_TRIML
	  | BIF_TRIMR
	  | BIF_UCS2
	  | BIF_UNS
	  | BIF_UNSH
	  | BIF_XFOOT
	  | BIF_XLATE
	  | BIF_XML
	  | BIF_YEARS;



free: ((baseExpression FREE_SEMI free_linecomments? ) | exec_sql); // (NEWLINE |COMMENTS_EOL);
c_free: (((expression) free_linecomments? ) | exec_sql);

control: opCode indicator_expr;
exec_sql: EXEC_SQL WORDS+ SEMI ;
//sql_quoted: SINGLE_QTE (WORDS | SEMI | DOUBLE_QTE) * SINGLE_QTE ;
//sql_quoted2: DOUBLE_QTE (WORDS | SEMI |SINGLE_QTE)* DOUBLE_QTE ;

//---------------  
baseExpression: op | expression;
assignmentExpression: expression EQUAL expression;
expression: 
	// op | // should drop op I think?
	function 
	| identifier 
	| number 
	| literal  
	| bif
	| NOT expression
	| OPEN_PAREN expression CLOSE_PAREN
	| expression (assignmentOperator | comparisonOperator | EQUAL) expression
    |<assoc=right> expression EXP expression
    | expression (MULT | MULT_NOSPACE) expression
    | expression DIV expression
    | expression PLUS expression
    | expression MINUS expression
    // expression arithmeticalOperator expression	
	| expression EQUAL expression	
	| expression AND expression	
	| expression OR expression	
	;
indicator_expr: expression;
function: functionName args;
//arithmeticalOperator:PLUS | MINUS | EXP | MULT | MULT_NOSPACE | DIV;
comparisonOperator: GT | LT | GE | LE | NE;
assignmentOperator: CPLUS | CMINUS | CMULT | CDIV | CEXP ;

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
	content=(StringContent | StringEscapedQuote | PlusOrMinus)* StringLiteralEnd;
identifier: free_identifier (FREE_CONT free_identifier)? |multipart_identifier | all;
all: symbolicConstants literal?;
//assignIdentifier: multipart_identifier;
functionName: free_identifier;
multipart_identifier: (free_identifier | indexed_identifier) (FREE_DOT (free_identifier | indexed_identifier))*;
indexed_identifier: free_identifier OPEN_PAREN (expression | ARRAY_REPEAT) CLOSE_PAREN;
opCode: free_identifier;
number: MINUS? NUMBER ;
free_identifier: (continuedIdentifier | MULT_NOSPACE? ID | NOT | FREE_BY | FREE_TO | FREE_DOWNTO |op_code);//OP_E?
//free_identifier: (continuedIdentifier | ID | NOT | FREE_BY | FREE_TO | FREE_DOWNTO |op_code) OP_E?;
continuedIdentifier: ID CONTINUATION ID ;
		
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
   | SPLAT_ISO0
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
   | SPLAT_DATA
   | SPLAT_ASTFILL
   | SPLAT_CURSYM
   | SPLAT_MAX
   | SPLAT_LOCK
   | SPLAT_PROGRAM
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
