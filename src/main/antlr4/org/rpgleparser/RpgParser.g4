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
cspec_fixed_standard: 
	csACQ
	| csADD
	| csADDDUR
	| csALLOC
	| csANDxx
	| csANDEQ
	| csANDNE
	| csANDLE
	| csANDLT
	| csANDGE
	| csANDGT
	| csBEGSR
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
	| csDOUEQ
	| csDOUNE
	| csDOULE
	| csDOULT
	| csDOUGE
	| csDOUGT
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
	| csENDFOR
	| csENDIF
	| csENDMON
	| csENDSL
	| csENDSR
	| csEVAL
	| csEVAL_CORR
	| csEVALR
	| csEXCEPT
	| csEXFMT
	| csEXSR
	| csEXTRCT
	| csFEOD
	| csFOR
	| csFORCE
	| csGOTO
	| csIF
	| csIFEQ
	| csIFNE
	| csIFLE
	| csIFLT
	| csIFGE
	| csIFGT
	| csIN
	| csITER
	| csKFLD
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
	| csMVR
	| csNEXT
	| csOCCUR
	| csON_ERROR
	| csOPEN
	| csOREQ
	| csORNE
	| csORLE
	| csORLT
	| csORGE
	| csORGT
	| csOTHER
	| csOUT
	| csPARM
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
	| csSELECT
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
	| csWHEN
	| csWHENEQ
	| csWHENNE
	| csWHENLE
	| csWHENLT
	| csWHENGE
	| csWHENGT
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
	cs_fixed_comments? EOL;	

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
csANDxx:
	operation=OP_ANDxx
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
csBEGSR:
	operation=OP_BEGSR
	cspec_fixed_standard_parts;
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
	cspec_fixed_standard_parts;
csCALLB:
	operation=OP_CALLB
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csCALLP:
	operation=OP_CALLP
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
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
	cspec_fixed_standard_parts;
csDO:
	operation=OP_DO
	cspec_fixed_standard_parts;
csDOU:
	operation=OP_DOU
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
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
	cspec_fixed_standard_parts;
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
csENDSR:
	operation=OP_ENDSR
	cspec_fixed_standard_parts;
csEVAL:
	operation=OP_EVAL
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csEVAL_CORR:
	operation=OP_EVAL_CORR
	cspec_fixed_standard_parts;
csEVALR:
	operation=OP_EVALR
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
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
	operation=OP_FOR
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
csFORCE:
	operation=OP_FORCE
	cspec_fixed_standard_parts;
csGOTO:
	operation=OP_GOTO
	cspec_fixed_standard_parts;
csIF:
	operation=OP_IF
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
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
csKFLD:
	operation=OP_KFLD
	cspec_fixed_standard_parts;
csKLIST:
	operation=OP_KLIST
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
	cspec_fixed_standard_parts;
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
	operation=OP_PARM
	cspec_fixed_standard_parts;
csPLIST:
	operation=OP_PLIST
	cspec_fixed_standard_parts;
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
	cspec_fixed_standard_parts;
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
	cspec_fixed_standard_parts;
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
	operation=OP_WHEN
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
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
	cspec_fixed_standard_parts;
csXML_SAX:
	operation=OP_XML_SAX
	operationExtender=cs_operationExtender? 
	cspec_fixed_standard_parts;
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
  CLOSE_PAREN;	
factor:
   content=factorContent (COLON (content2=factorContent | constant2=symbolicConstants))? | CS_BlankFactor | constant=symbolicConstants;
   
factorContent:
CS_FactorContent | CS_FactorContentLiteral;

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
