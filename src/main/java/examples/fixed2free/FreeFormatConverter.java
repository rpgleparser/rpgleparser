package examples.fixed2free;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.rpgleparser.*;
import org.rpgleparser.RpgParser.CsACQContext;
import org.rpgleparser.RpgParser.CsADDContext;
import org.rpgleparser.RpgParser.CsADDDURContext;
import org.rpgleparser.RpgParser.CsALLOCContext;
import org.rpgleparser.RpgParser.CsANDEQContext;
import org.rpgleparser.RpgParser.CsANDGEContext;
import org.rpgleparser.RpgParser.CsANDGTContext;
import org.rpgleparser.RpgParser.CsANDLEContext;
import org.rpgleparser.RpgParser.CsANDLTContext;
import org.rpgleparser.RpgParser.CsANDNEContext;
import org.rpgleparser.RpgParser.CsANDxxContext;
import org.rpgleparser.RpgParser.CsBEGSRContext;
import org.rpgleparser.RpgParser.CsBITOFFContext;
import org.rpgleparser.RpgParser.CsBITONContext;
import org.rpgleparser.RpgParser.CsCABEQContext;
import org.rpgleparser.RpgParser.CsCABGEContext;
import org.rpgleparser.RpgParser.CsCABGTContext;
import org.rpgleparser.RpgParser.CsCABLEContext;
import org.rpgleparser.RpgParser.CsCABLTContext;
import org.rpgleparser.RpgParser.CsCABNEContext;
import org.rpgleparser.RpgParser.CsCABxxContext;
import org.rpgleparser.RpgParser.CsCALLBContext;
import org.rpgleparser.RpgParser.CsCALLContext;
import org.rpgleparser.RpgParser.CsCASEQContext;
import org.rpgleparser.RpgParser.CsCASGEContext;
import org.rpgleparser.RpgParser.CsCASGTContext;
import org.rpgleparser.RpgParser.CsCASLEContext;
import org.rpgleparser.RpgParser.CsCASLTContext;
import org.rpgleparser.RpgParser.CsCASNEContext;
import org.rpgleparser.RpgParser.CsCATContext;
import org.rpgleparser.RpgParser.CsCHAINContext;
import org.rpgleparser.RpgParser.CsCHECKContext;
import org.rpgleparser.RpgParser.CsCHECKRContext;
import org.rpgleparser.RpgParser.CsCLEARContext;
import org.rpgleparser.RpgParser.CsCLOSEContext;
import org.rpgleparser.RpgParser.CsCOMMITContext;
import org.rpgleparser.RpgParser.CsCOMPContext;
import org.rpgleparser.RpgParser.CsDEALLOCContext;
import org.rpgleparser.RpgParser.CsDEFINEContext;
import org.rpgleparser.RpgParser.CsDELETEContext;
import org.rpgleparser.RpgParser.CsDIVContext;
import org.rpgleparser.RpgParser.CsDOContext;
import org.rpgleparser.RpgParser.CsDOUContext;
import org.rpgleparser.RpgParser.CsDOUEQContext;
import org.rpgleparser.RpgParser.CsDOUGEContext;
import org.rpgleparser.RpgParser.CsDOUGTContext;
import org.rpgleparser.RpgParser.CsDOULEContext;
import org.rpgleparser.RpgParser.CsDOULTContext;
import org.rpgleparser.RpgParser.CsDOUNEContext;
import org.rpgleparser.RpgParser.CsDOWContext;
import org.rpgleparser.RpgParser.CsDOWEQContext;
import org.rpgleparser.RpgParser.CsDOWGEContext;
import org.rpgleparser.RpgParser.CsDOWGTContext;
import org.rpgleparser.RpgParser.CsDOWLEContext;
import org.rpgleparser.RpgParser.CsDOWLTContext;
import org.rpgleparser.RpgParser.CsDOWNEContext;
import org.rpgleparser.RpgParser.CsDSPLYContext;
import org.rpgleparser.RpgParser.CsDUMPContext;
import org.rpgleparser.RpgParser.CsELSEContext;
import org.rpgleparser.RpgParser.CsELSEIFContext;
import org.rpgleparser.RpgParser.CsENDCSContext;
import org.rpgleparser.RpgParser.CsENDContext;
import org.rpgleparser.RpgParser.CsENDDOContext;
import org.rpgleparser.RpgParser.CsENDFORContext;
import org.rpgleparser.RpgParser.CsENDIFContext;
import org.rpgleparser.RpgParser.CsENDMONContext;
import org.rpgleparser.RpgParser.CsENDSLContext;
import org.rpgleparser.RpgParser.CsENDSRContext;
import org.rpgleparser.RpgParser.CsEVALContext;
import org.rpgleparser.RpgParser.CsEVALRContext;
import org.rpgleparser.RpgParser.CsEVAL_CORRContext;
import org.rpgleparser.RpgParser.CsEXCEPTContext;
import org.rpgleparser.RpgParser.CsEXFMTContext;
import org.rpgleparser.RpgParser.CsEXSRContext;
import org.rpgleparser.RpgParser.CsEXTRCTContext;
import org.rpgleparser.RpgParser.CsFEODContext;
import org.rpgleparser.RpgParser.CsFORCEContext;
import org.rpgleparser.RpgParser.CsFORContext;
import org.rpgleparser.RpgParser.CsGOTOContext;
import org.rpgleparser.RpgParser.CsIFContext;
import org.rpgleparser.RpgParser.CsIFEQContext;
import org.rpgleparser.RpgParser.CsIFGEContext;
import org.rpgleparser.RpgParser.CsIFGTContext;
import org.rpgleparser.RpgParser.CsIFLEContext;
import org.rpgleparser.RpgParser.CsIFLTContext;
import org.rpgleparser.RpgParser.CsIFNEContext;
import org.rpgleparser.RpgParser.CsINContext;
import org.rpgleparser.RpgParser.CsITERContext;
import org.rpgleparser.RpgParser.CsKFLDContext;
import org.rpgleparser.RpgParser.CsKLISTContext;
import org.rpgleparser.RpgParser.CsLEAVEContext;
import org.rpgleparser.RpgParser.CsLEAVESRContext;
import org.rpgleparser.RpgParser.CsLOOKUPContext;
import org.rpgleparser.RpgParser.CsMHHZOContext;
import org.rpgleparser.RpgParser.CsMHLZOContext;
import org.rpgleparser.RpgParser.CsMLHZOContext;
import org.rpgleparser.RpgParser.CsMLLZOContext;
import org.rpgleparser.RpgParser.CsMONITORContext;
import org.rpgleparser.RpgParser.CsMOVEAContext;
import org.rpgleparser.RpgParser.CsMOVEContext;
import org.rpgleparser.RpgParser.CsMOVELContext;
import org.rpgleparser.RpgParser.CsMULTContext;
import org.rpgleparser.RpgParser.CsMVRContext;
import org.rpgleparser.RpgParser.CsNEXTContext;
import org.rpgleparser.RpgParser.CsOCCURContext;
import org.rpgleparser.RpgParser.CsON_ERRORContext;
import org.rpgleparser.RpgParser.CsOPENContext;
import org.rpgleparser.RpgParser.CsOREQContext;
import org.rpgleparser.RpgParser.CsORGEContext;
import org.rpgleparser.RpgParser.CsORGTContext;
import org.rpgleparser.RpgParser.CsORLEContext;
import org.rpgleparser.RpgParser.CsORLTContext;
import org.rpgleparser.RpgParser.CsORNEContext;
import org.rpgleparser.RpgParser.CsOTHERContext;
import org.rpgleparser.RpgParser.CsOUTContext;
import org.rpgleparser.RpgParser.CsPARMContext;
import org.rpgleparser.RpgParser.CsPLISTContext;
import org.rpgleparser.RpgParser.CsPOSTContext;
import org.rpgleparser.RpgParser.CsREADCContext;
import org.rpgleparser.RpgParser.CsREADContext;
import org.rpgleparser.RpgParser.CsREADEContext;
import org.rpgleparser.RpgParser.CsREADPContext;
import org.rpgleparser.RpgParser.CsREADPEContext;
import org.rpgleparser.RpgParser.CsREALLOCContext;
import org.rpgleparser.RpgParser.CsRELContext;
import org.rpgleparser.RpgParser.CsRESETContext;
import org.rpgleparser.RpgParser.CsRETURNContext;
import org.rpgleparser.RpgParser.CsROLBKContext;
import org.rpgleparser.RpgParser.CsSCANContext;
import org.rpgleparser.RpgParser.CsSELECTContext;
import org.rpgleparser.RpgParser.CsSETGTContext;
import org.rpgleparser.RpgParser.CsSETLLContext;
import org.rpgleparser.RpgParser.CsSETOFFContext;
import org.rpgleparser.RpgParser.CsSETONContext;
import org.rpgleparser.RpgParser.CsSHTDNContext;
import org.rpgleparser.RpgParser.CsSORTAContext;
import org.rpgleparser.RpgParser.CsSQRTContext;
import org.rpgleparser.RpgParser.CsSUBContext;
import org.rpgleparser.RpgParser.CsSUBDURContext;
import org.rpgleparser.RpgParser.CsSUBSTContext;
import org.rpgleparser.RpgParser.CsTAGContext;
import org.rpgleparser.RpgParser.CsTESTBContext;
import org.rpgleparser.RpgParser.CsTESTContext;
import org.rpgleparser.RpgParser.CsTESTNContext;
import org.rpgleparser.RpgParser.CsTESTZContext;
import org.rpgleparser.RpgParser.CsTIMEContext;
import org.rpgleparser.RpgParser.CsUNLOCKContext;
import org.rpgleparser.RpgParser.CsUPDATEContext;
import org.rpgleparser.RpgParser.CsWHENContext;
import org.rpgleparser.RpgParser.CsWHENEQContext;
import org.rpgleparser.RpgParser.CsWHENGEContext;
import org.rpgleparser.RpgParser.CsWHENGTContext;
import org.rpgleparser.RpgParser.CsWHENLEContext;
import org.rpgleparser.RpgParser.CsWHENLTContext;
import org.rpgleparser.RpgParser.CsWHENNEContext;
import org.rpgleparser.RpgParser.CsWRITEContext;
import org.rpgleparser.RpgParser.CsXFOOTContext;
import org.rpgleparser.RpgParser.CsXLATEContext;
import org.rpgleparser.RpgParser.CsXML_INTOContext;
import org.rpgleparser.RpgParser.CsXML_SAXContext;
import org.rpgleparser.RpgParser.CsZ_ADDContext;
import org.rpgleparser.RpgParser.CsZ_SUBContext;
import org.rpgleparser.RpgParser.Cspec_fixedContext;
import org.rpgleparser.RpgParser.Cspec_fixed_sqlContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standardContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;
import org.rpgleparser.RpgParser.Cspec_fixed_x2Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import examples.loggingListener.LoggingListener;

public class FreeFormatConverter extends LoggingListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FreeFormatConverter.class);

	private ArrayList<String> cspecs = new ArrayList<String>();
	private ArrayList<String> dspecs = new ArrayList<String>();
	private ArrayList<String> fspecs = new ArrayList<String>();
	private ArrayList<String> hspecs = new ArrayList<String>();
	private int indentLevel = 0;
	private boolean inKList = false;
	private boolean inPList = false;
	private ArrayList<String> ispecs = new ArrayList<String>();
	private ArrayList<String> ospecs = new ArrayList<String>();
	private int spacesToIndent = 3;
	private Vocabulary voc;
	private String workString;
	
	public FreeFormatConverter(RpgLexer lex){
		voc = lex.getVocabulary();
	}
	
	protected void checkOpCodeStuff(CommonToken ct, List<CommonToken> myList,
			int opCodeIndex) throws RPGFormatException {
		if (opCodeIndex == 0)
			return;
		int maxIndex = myList.size();
		CommonToken factor1 = null;
		CommonToken factor2 = null;
		CommonToken result = null;
		CommonToken length = null;
		CommonToken decpos = null;
		CommonToken high = null;
		CommonToken low = null;
		CommonToken equal = null;

		String opCode = ct.getText().toUpperCase().trim();
		factor1 = myList.get(opCodeIndex - 1);
		if (opCodeIndex + 1 <= maxIndex) {
			factor2 = myList.get(opCodeIndex + 1);
		}
		if (opCodeIndex + 2 <= maxIndex) {
			result = myList.get(opCodeIndex + 2);
		}
		if (opCodeIndex + 3 <= maxIndex) {
			length = myList.get(opCodeIndex + 3);
		}
		if (opCodeIndex + 4 <= maxIndex) {
			decpos = myList.get(opCodeIndex + 4);
		}
		if (opCodeIndex + 5 <= maxIndex) {
			high = myList.get(opCodeIndex + 5);
		}
		if (opCodeIndex + 6 <= maxIndex) {
			low = myList.get(opCodeIndex + 6);
		}
		if (opCodeIndex + 2 <= maxIndex) {
			equal = myList.get(opCodeIndex + 7);
		}

		// Multi-line op codes
		if (inKList && !(opCode.equals("KLIST") || opCode.equals("KFLD"))) {
			inKList = false;
		}
		if (inPList && !(opCode.equals("PLIST") || opCode.equals("PARM"))) {
			inPList = false;
		}
		if (opCode.equals("KLIST")) {
			doKLIST(factor1, opCodeIndex);
		} else if (opCode.equals("KFLD")) {
			doKFLD(result, opCodeIndex);
		} else if (opCode.equals("PLIST")) {
			doPLIST(factor1, opCodeIndex);
		} else if (opCode.equals("PARM")) {
			doPARM(factor1, opCodeIndex);
		} else if (opCode.equals("DEFINE")) {
			doDEFINE(factor1, factor2, result);
		} else if (opCode.equals("EVAL")) {
			doEVAL(myList, opCodeIndex);
		} else if (opCode.equals("IFEQ")) {
			doIFEQ(factor1, factor2);
		} else if (opCode.equals("IFNE")) {
			doIFNE(factor1, factor2);
		} else if (opCode.equals("IFLE")) {
			doIFLE(factor1, factor2);
		} else if (opCode.equals("IFGE")) {
			doIFGE(factor1, factor2);
		} else if (opCode.equals("IFLT")) {
			doIFLT(factor1, factor2);
		} else if (opCode.equals("IFGT")) {
			doIFGT(factor1, factor2);
		} else if (opCode.equals("ENDIF")) {
			doENDIF();
		} else if (opCode.equals("END")) {
			doENDIF();
		} else if (opCode.equals("ELSE")) {
			doELSE();
		} else if (opCode.equals("ANDEQ")) {
			doANDEQ(factor1, factor2);
		} else if (opCode.equals("ANDNE")) {
			doANDNE(factor1, factor2);
		} else if (opCode.equals("ANDGE")) {
			doANDGE(factor1, factor2);
		} else if (opCode.equals("ANDLE")) {
			doANDLE(factor1, factor2);
		} else if (opCode.equals("ANDGT")) {
			doANDGT(factor1, factor2);
		} else if (opCode.equals("ANDLT")) {
			doANDLT(factor1, factor2);
		} else if (opCode.equals("OREQ")) {
			doOREQ(factor1, factor2);
		} else if (opCode.equals("ORNE")) {
			doORNE(factor1, factor2);
		} else if (opCode.equals("ORGE")) {
			doORGE(factor1, factor2);
		} else if (opCode.equals("ORLE")) {
			doORLE(factor1, factor2);
		} else if (opCode.equals("ORGT")) {
			doORGT(factor1, factor2);
		} else if (opCode.equals("ORLT")) {
			doORLT(factor1, factor2);
		} else if (opCode.equals("ACQ")) {
			doACQ(factor1, factor2);
		} else if (opCode.equals("ADD")) {
			doADD(factor1, factor2, result, length, decpos);
		} else if (opCode.equals("ADDDUR")) {
			doADDDUR(factor1, factor2, result);
		} else if (opCode.equals("ALLOC")) {
			doALLOC(factor2, result);
		} else if (opCode.equals("BEGSR")) {
			doBEGSR(factor1);
		} else if (opCode.equals("BITOFF")) {
			doBITOFF(factor2, result);
		} else if (opCode.equals("BITON")) {
			doBITON(factor2, result);
		} else if (opCode.equals("CABEQ")) {
			doCABEQ(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CABNE")) {
			doCABNE(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CABLT")) {
			doCABLT(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CABLE")) {
			doCABLE(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CABGT")) {
			doCABGT(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CABGE")) {
			doCABGE(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CALL")) {
			doCALL(factor2, result, high, equal);
		} else if (opCode.equals("CALLP")) {
			doCALLP(factor2);// TODO this is likely wrong as it is an extended
								// factor 2 opcode.
		} else if (opCode.equals("CALLB")) {
			doCALLB(factor2, result, high, equal);
		} else if (opCode.equals("CASEQ")) {
			doCASEQ(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CASNE")) {
			doCASNE(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CASLT")) {
			doCASLT(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CASLE")) {
			doCASLE(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CASGT")) {
			doCASGT(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CASGE")) {
			doCASGE(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("CAT")) {
			doCAT(factor1, factor2, result);
		} else if (opCode.equals("CHAIN")) {
			doCHAIN(factor1, factor2, result, high, low);
		} else if (opCode.equals("CHECK")) {
			doCHECK(factor1, factor2, result, low, equal);
		} else if (opCode.equals("CHECKR")) {
			doCHECKR(factor1, factor2, result, low, equal);
		} else if (opCode.equals("CLEAR")) {
			doCLEAR(factor1, factor2, result);
		} else if (opCode.equals("CLOSE")) {
			doCLOSE(factor2, low);
		} else if (opCode.equals("COMMIT")) {
			doCOMMIT(factor1, low);
		} else if (opCode.equals("COMP")) {
			doCOMP(factor1, factor2, high, low, equal);
		} else if (opCode.equals("DEALLOC")) {
			doDEALLOC(result, low);
		} else if (opCode.equals("DELETE")) {
			doDELETE(factor1, factor2, high, low);
		} else if (opCode.equals("DIV")) {
			doDIV(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("DO")) {
			doDO(factor1, factor2, result);
		} else if (opCode.equals("DOU")) {
			doDOU(factor2); // TODO Probably not right since it is extended
							// factor 2
		} else if (opCode.equals("DOUEQ")) {
			doDOUEQ(factor1, factor2);
		} else if (opCode.equals("DOUNE")) {
			doDOUNE(factor1, factor2);
		} else if (opCode.equals("DOULT")) {
			doDOULT(factor1, factor2);
		} else if (opCode.equals("DOULE")) {
			doDOULE(factor1, factor2);
		} else if (opCode.equals("DOUGT")) {
			doDOUGT(factor1, factor2);
		} else if (opCode.equals("DOUGE")) {
			doDOUGE(factor1, factor2);
		} else if (opCode.equals("DOW")) {
			doDOW(factor2); // TODO Probably not right since it is extended
							// factor 2
		} else if (opCode.equals("DOWEQ")) {
			doDOWEQ(factor1, factor2);
		} else if (opCode.equals("DOWNE")) {
			doDOWNE(factor1, factor2);
		} else if (opCode.equals("DOWLT")) {
			doDOWLT(factor1, factor2);
		} else if (opCode.equals("DOWLE")) {
			doDOWLE(factor1, factor2);
		} else if (opCode.equals("DOWGT")) {
			doDOWGT(factor1, factor2);
		} else if (opCode.equals("DOWGE")) {
			doDOWGE(factor1, factor2);
		} else if (opCode.equals("DSPLY")) {
			doDSPLY(factor1, factor2, result, low);
		} else if (opCode.equals("DUMP")) {
			doDUMP(factor1);
		} else if (opCode.equals("ELSEIF")) {
			doELSEIF(factor2); // TODO Probably not right since it is extended
								// factor 2
		} else if (opCode.equals("END")) {
			doEND(factor2); // TODO Probably not right since it is extended
							// factor 2
		} else if (opCode.equals("ENDCS")) {
			doENDCS();
		} else if (opCode.equals("ENDDO")) {
			doENDDO(factor2);
		} else if (opCode.equals("ENDFOR")) {
			doENDFOR();
		} else if (opCode.equals("ENDMON")) {
			doENDMON();
		} else if (opCode.equals("ENDSL")) {
			doENDSL();
		} else if (opCode.equals("ENDSR")) {
			doENDSR(factor1, factor2);
		} else if (opCode.equals("EVALR")) {
			doEVALR(factor2);
		} else if (opCode.equals("EVAL-CORR")) {
			doEVAL_CORR(factor2); // TODO Probably not right since it is
									// extended factor 2
		} else if (opCode.equals("EXCEPT")) {
			doEXCEPT(factor2);
		} else if (opCode.equals("EXFMT")) {
			doEXFMT(factor2, result, length, decpos, low);
		} else if (opCode.equals("EXSR")) {
			doEXSR(factor2);
		} else if (opCode.equals("EXTRCT")) {
			doEXTRCT(factor2); // TODO Probably not right since it is extended
								// factor 2
		} else if (opCode.equals("FEOD")) {
			doFEOD(factor2, low);
		} else if (opCode.equals("FOR")) {
			doFOR(factor2); // TODO Probably not right since it is extended
							// factor 2
		} else if (opCode.equals("FORCE")) {
			doFORCE(factor2);
		} else if (opCode.equals("GOTO")) {
			doGOTO(factor2);
		} else if (opCode.equals("IF")) {
			doIF(factor2); // TODO Probably not right since it is extended
							// factor 2
		} else if (opCode.equals("IN")) {
			doIN(factor1, factor2, low);
		} else if (opCode.equals("ITER")) {
			doITER();
		} else if (opCode.equals("LEAVE")) {
			doLEAVE();
		} else if (opCode.equals("LEAVESR")) {
			doLEAVESR();
		} else if (opCode.equals("LOOKUP")) {
			doLOOKUP(factor1, factor2, high, low, equal);
		} else if (opCode.equals("MHHZO")) {
			doMHHZO(factor2, result, length, decpos);
		} else if (opCode.equals("MHLZO")) {
			doMHLZO(factor2, result, length, decpos);
		} else if (opCode.equals("MLHZO")) {
			doMLHZO(factor2, result, length, decpos);
		} else if (opCode.equals("MLLZO")) {
			doMLLZO(factor2, result, length, decpos);
		} else if (opCode.equals("MONITOR")) {
			doMONITOR();
		} else if (opCode.equals("MOVE")) {
			doMOVE(factor1, factor2, result, high, low, equal);
		} else if (opCode.equals("MOVEA")) {
			doMOVEA(factor2, result, length, decpos, high, low, equal);
		} else if (opCode.equals("MOVEL")) {
			doMOVEL(factor1, factor2, result, length, decpos, high, low, equal);
		} else if (opCode.equals("MULT")) {
			doMULT(factor1, factor2, result, length, decpos, high, low, equal);
		} else if (opCode.equals("MVR")) {
			doMVR(result, length, decpos, high, low, equal);
		} else if (opCode.equals("NEXT")) {
			doNEXT(factor1, factor2, low);
		} else if (opCode.equals("OCCUR")) {
			doOCCUR(factor1, factor2, result, low);
		} else if (opCode.equals("ON-ERROR")) {
			doON_ERROR(factor2); // TODO Probably not right since it is extended
									// factor 2
		} else if (opCode.equals("OPEN")) {
			doOPEN(factor2, low);
		} else if (opCode.equals("OTHER")) {
			doOTHER();
		} else if (opCode.equals("OUT")) {
			doOUT(factor1, factor2, low);
		} else if (opCode.equals("POST")) {
			doPOST(factor1, factor2, result, low);
		} else if (opCode.equals("READ")) {
			doREAD(factor2, result, low, equal);
		} else if (opCode.equals("READC")) {
			doREADC(factor2, result, low, equal);
		} else if (opCode.equals("READE")) {
			doREADE(factor1, factor2, result, low, equal);
		} else if (opCode.equals("READP")) {
			doREADP(factor2, result, low, equal);
		} else if (opCode.equals("READPE")) {
			doREADPE(factor1, factor2, result, low, equal);
		} else if (opCode.equals("REALLOC")) {
			doREALLOC(factor2, result, low);
		} else if (opCode.equals("REL")) {
			doREL(factor1, factor2, low);
		} else if (opCode.equals("RESET")) {
			doRESET(factor1, factor2, result, low);
		} else if (opCode.equals("RETURN")) {
			doRETURN(factor2);// TODO Probably not right since it is extended
								// factor 2
		} else if (opCode.equals("ROLBK")) {
			doROLBK(low);
		} else if (opCode.equals("SCAN")) {
			doSCAN(factor1, factor2, result, length, decpos, low, equal);
		} else if (opCode.equals("SELECT")) {
			doSELECT();
		} else if (opCode.equals("SETGT")) {
			doSETGT(factor1, factor2, high, low);
		} else if (opCode.equals("SETLL")) {
			doSETLL(factor1, factor2, high, low, equal);
		} else if (opCode.equals("SETOFF")) {
			doSETOFF(high, low, equal);
		} else if (opCode.equals("SETON")) {
			doSETON(high, low, equal);
		} else if (opCode.equals("SHTDN")) {
			doSHTDN(high);
		} else if (opCode.equals("SORTA")) {
			doSORTA(factor2);
		} else if (opCode.equals("SQRT")) {
			doSQRT(factor2, result, length, decpos);
		} else if (opCode.equals("SUB")) {
			doSUB(factor1, factor2, result, length, decpos, high, low, equal);
		} else if (opCode.equals("SUBDUR")) {
			doSUBDUR(factor1, factor2, result, length, decpos);
		} else if (opCode.equals("SUBST")) {
			doSUBST(factor1, factor2, result, length, decpos, low);
		} else if (opCode.equals("TAG")) {
			doTAG(factor1);
		} else if (opCode.equals("TEST")) {
			doTEST(factor1, result, low);
		} else if (opCode.equals("TESTB")) {
			doTESTB(factor2, result, high, low, equal);
		} else if (opCode.equals("TESTN")) {
			doTESTN(result, high, low, equal);
		} else if (opCode.equals("TESTZ")) {
			doTESTZ(result, high, low, equal);
		} else if (opCode.equals("TIME")) {
			doTIME(result, length, decpos);
		} else if (opCode.equals("UNLOCK")) {
			doUNLOCK(factor2, low);
		} else if (opCode.equals("UPDATE")) {
			doUPDATE(factor2, result, low);
		} else if (opCode.equals("WHEN")) {
			doWHEN(factor2);
		} else if (opCode.equals("WHENEQ")) {
			doWHENEQ(factor1, factor2);
		} else if (opCode.equals("WHENNE")) {
			doWHENNE(factor1, factor2);
		} else if (opCode.equals("WHENLT")) {
			doWHENLT(factor1, factor2);
		} else if (opCode.equals("WHENLE")) {
			doWHENLE(factor1, factor2);
		} else if (opCode.equals("WHENGT")) {
			doWHENGT(factor1, factor2);
		} else if (opCode.equals("WHENGE")) {
			doWHENGE(factor1, factor2);
		} else if (opCode.equals("WRITE")) {
			doWRITE(factor2, result, low, equal);
		} else if (opCode.equals("XFOOT")) {
			doXFOOT(factor2, result, length, decpos, high, low, equal);
		} else if (opCode.equals("XLATE")) {
			doXLATE(factor1, factor2, result, length, decpos, low);
		} else if (opCode.equals("XML-INTO")) {
			doXML_INTO(factor2);
		} else if (opCode.equals("XML-SAX")) {
			doXML_SAX(factor2);
		} else if (opCode.equals("Z-ADD")) {
			doZ_ADD(factor2, result, length, decpos, high, low, equal);
		} else if (opCode.equals("Z-SUB")) {
			doZ_SUB(factor2, result, length, decpos, high, low, equal);
		}
	}

	public List<String> collectOutput(){
		ArrayList<String> result = new ArrayList<String>(hspecs.size() + fspecs.size() + ispecs.size() + dspecs.size() + cspecs.size() +ospecs.size()); 
			result.addAll(hspecs);
			result.addAll(fspecs);
			result.addAll(ispecs);
			result.addAll(dspecs);
			result.addAll(cspecs);
			result.addAll(ospecs);
			return result;
	}

	private void doACQ(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "ACQ " + factor1.getText() + " " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doADD(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos)
			throws RPGFormatException {
		doResultCheck(result, length, decpos);
		if (factor1.getText().trim().length() > 0){
			workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent))
					+ result.getText().trim() + " = " + factor1.getText() + " + "
					+ factor2.getText() + ";";
			cspecs.add(workString);
		} else {
			workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent))
					+ result.getText().trim() + " += " + factor2.getText() + ";";
			cspecs.add(workString);
		}

	}

	private void doADDDUR(CommonToken factor1, CommonToken factor2,
			CommonToken result) {
		// TODO Auto-generated method stub

	}

	private void doALLOC(CommonToken factor2, CommonToken result) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + result.getText().trim() + " = %ALLOC(" + factor2.getText().trim() + ");";
		cspecs.add(workString);

	}

	private void doANDEQ(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND " + factor1.getText() + " = " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDGE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND " + factor1.getText() + " >= " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDGT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND " + factor1.getText() + " > " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDLE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND " + factor1.getText() + " <= " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDLT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND " + factor1.getText() + " < " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDNE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND " + factor1.getText() + " <> " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doBEGSR(CommonToken factor1) {
		setIndentLevel(0);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "BEGSR " + factor1.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doBITOFF(CommonToken factor2, CommonToken result) {
		// TODO Auto-generated method stub

	}

	private void doBITON(CommonToken factor2, CommonToken result) {
		// TODO Auto-generated method stub

	}

	private void doCABEQ(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) throws RPGFormatException {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " = " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		doGOTO(result);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);

	}

	private void doCABGE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) throws RPGFormatException {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " >= " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		doGOTO(result);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCABGT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) throws RPGFormatException {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " > " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		doGOTO(result);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCABLE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) throws RPGFormatException {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " <= " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		doGOTO(result);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);

	}

	private void doCABLT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) throws RPGFormatException {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " < " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		doGOTO(result);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCABNE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) throws RPGFormatException {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " <> " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		doGOTO(result);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);

	}

	private void doCALL(CommonToken factor2, CommonToken result,
			CommonToken high, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCALLB(CommonToken factor2, CommonToken result,
			CommonToken high, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCALLP(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doCASEQ(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " = " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + ((indentLevel + 1) * spacesToIndent)) + "EXSR " + result.getText().trim()  +  "; // From CASEQ statement";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
}

	private void doCASGE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " >= " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + ((indentLevel + 1) * spacesToIndent)) + "EXSR " + result.getText().trim()  +  "; // From CASGE statement";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASGT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " > " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + ((indentLevel + 1) * spacesToIndent)) + "EXSR " + result.getText().trim()  +  "; // From CASGT statement";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASLE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " <= " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + ((indentLevel + 1) * spacesToIndent)) + "EXSR " + result.getText().trim()  +  "; // From CASLE statement";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASLT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " < " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + ((indentLevel + 1) * spacesToIndent)) + "EXSR " + result.getText().trim()  +  "; // From CASLT statement";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASNE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		boolean HI = high.getText().trim().length() > 0;
		boolean LO = low.getText().trim().length() > 0;
		boolean EQ = equal.getText().trim().length() > 0;
		if (HI){
			setResultingIndicator(high, "IF " +factor1.getText().trim() + " > " + factor2.getText() + ";");
		}
		if (LO){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " < " + factor2.getText() + ";");
		}
		if (EQ){
			setResultingIndicator(low, "IF " +factor1.getText().trim() + " = " + factor2.getText() + ";");
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "IF " + factor1.getText().trim() + " <> " + factor2.getText().trim() +  ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + ((indentLevel + 1) * spacesToIndent)) + "EXSR " + result.getText().trim()  +  "; // From CASNE statement";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCAT(CommonToken factor1, CommonToken factor2,
			CommonToken result) {
		// TODO Auto-generated method stub

	}

	private void doCHAIN(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low) {
		boolean NR =  (high.getText().trim().length() > 0);
		boolean ER = (low.getText().trim().length() > 0);
		String opCode = "CHAIN";
		if (NR && ER){
			opCode += "(NE)";
		} else if (NR){
			opCode += "(N)";
		} else if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + opCode + " " + factor1.getText().trim() + " " + factor2.getText().trim() + " " + result.getText().trim() + ";";
		cspecs.add(workString);
		if (NR){
			setResultingIndicator(high, "IF %FOUND = *OFF;");
		}
		if (ER){
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}

	}

	private void doCHECK(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}
	
	private void doCHECKR(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCLEAR(CommonToken factor1, CommonToken factor2,
			CommonToken result) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "CLEAR ";
		if (factor1.getText().trim().length() > 0){
			workString += "*NOKEY ";
		}
		if (factor2.getText().trim().length() > 0){
			workString += "*ALL ";
		}
		workString += result.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doCLOSE(CommonToken factor2, CommonToken low) {
		String opCode = "CLOSE";
		boolean ER = (low.getText().trim().length() > 0);
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + opCode + " " + factor2.getText().trim() + ";";
		cspecs.add(workString);
		if (ER){
			setResultingIndicator(low, "IF %ERRROR = *ON;");
		}

	}

	private void doCOMMIT(CommonToken factor1, CommonToken low) {
		String opCode = "COMMIT";
		boolean ER = (low.getText().trim().length() > 0);
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + opCode + " " + factor1.getText().trim() + ";";
		cspecs.add(workString);

	}

	private void doCOMP(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken equal) {
		if (high.getText().trim().length() > 0){
			setResultingIndicator(high, "IF "+ factor1.getText().trim() + " > " + factor2.getText().trim() + ";");
		}
		if (low.getText().trim().length() > 0){
			setResultingIndicator(low, "IF "+ factor1.getText().trim() + " < " + factor2.getText().trim() + ";");
		}
		if (equal.getText().trim().length() > 0){
			setResultingIndicator(equal, "IF "+ factor1.getText().trim() + " = " + factor2.getText().trim() + ";");
		}

	}

	private void doDEALLOC(CommonToken result, CommonToken low) {
		boolean ER = low.getText().trim().length() > 0;
		String opCode = "DEALLOC";
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + opCode + result.getText().trim() + ";";
		cspecs.add(workString);
		if (ER){
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}
	}

	private void doDEFINE(CommonToken factor1, CommonToken factor2,
			CommonToken result) throws RPGFormatException {
		if (factor1.getType() == RpgLexer.SPLAT_LIKE) {
			workString = RPGSpecs.formatDSpec(' ' + result.getText(), " ", " ", "S", " ", " ", " ", " ", "LIKE(" +factor2.getText().trim() + ")", "From a define statement");
			dspecs.add(workString);
		} else if (factor1.getType() == RpgLexer.SPLAT_DTAARA){
			workString = RPGSpecs.formatDSpec(' ' + result.getText(), " ", " ", "DS", " ", " ", " ", " ", "DTAARA(" +factor2.getText().trim() + ")", "From a define statement");
			dspecs.add(workString);
		}
	}

	private void doDELETE(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low) {
		boolean NR = high.getText().trim().length() > 0;
		boolean ER = low.getText().trim().length() > 0;
		String opCode = "DELETE";
		if (NR && ER){
			opCode += "(NE)";
		} else if (NR){
			opCode += "(N)";
		} else if (ER){
			opCode += ("E");
		}
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + opCode + " " + factor1.getText().trim() + " " + factor2.getText().trim() + ";";
		cspecs.add(workString);
		if (ER){
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}

		if (NR){
			setResultingIndicator(high, "IF %FOUND = *OFF");
		}
	}

	private void doDIV(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		boolean F1F = factor1.getText().trim().length() > 0;
		boolean POS = high.getText().trim().length() > 0;
		boolean NEG = low.getText().trim().length() > 0;
		boolean ZERO = equal.getText().trim().length() > 0;
		if (F1F){
			workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + result.getText().trim() + " = " + factor1.getText().trim() + " / " + factor2.getText().trim() + ";";
		} else {
			workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + result.getText().trim() + " = " + result.getText().trim() + " / " + factor2.getText().trim() + ";";
		}
		cspecs.add(workString);
		if (POS){
			setResultingIndicator(high, "IF " + result.getText().trim() + " > 0;");
		}
		if (NEG){
			setResultingIndicator(low, "IF " + result.getText().trim() + " < 0;");
		}
		if (ZERO){
			setResultingIndicator(equal, "IF " + result.getText().trim() + " = 0;");
		}
	}

	private void doDO(CommonToken factor1, CommonToken factor2,
			CommonToken result) {
		String factor1s;
		if (factor1.getText().trim().length() == 0){
			factor1s = "1";
		} else {
			factor1s = factor1.getText().trim();
		}
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "FOR " + result.getText().trim() 
				+ " = " + factor1s + " TO "+ factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOU(CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOU " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOUEQ(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOU " + factor1.getText().trim() + " = " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOUGE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOU " + factor1.getText().trim() + " >= " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOUGT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOU " + factor1.getText().trim() + " > " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOULE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOU " + factor1.getText().trim() + " <= " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOULT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOU " + factor1.getText().trim() + " < " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOUNE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOU " + factor1.getText().trim() + " <> " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOW(CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOW " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWEQ(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOW " + factor1.getText().trim() + " = " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWGE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOW " + factor1.getText().trim() + " >= " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWGT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOW " + factor1.getText().trim() + " > " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWLE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOW " + factor1.getText().trim() + " <= " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWLT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOW " + factor1.getText().trim() + " < " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWNE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DOW " + factor1.getText().trim() + " <> " + factor2.getText().trim() + ";";
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDSPLY(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low) {
		boolean ER = low.getText().trim().length() > 0;
		String opCode = "DSPLY";
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + opCode + " " + factor1.getText().trim() + " " + factor2.getText().trim() + result.getText().trim() +";";
		cspecs.add(workString);
		if (ER){
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}
	}

	private void doDUMP(CommonToken factor1) {
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "DUMP " + factor1.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doELSE() {
		workString =StringUtils.repeat(" ", 7 + ((indentLevel-1) * spacesToIndent)) + "ELSE;";
		cspecs.add(workString);
	}

	private void doELSEIF(CommonToken factor2) {
		workString = StringUtils.repeat(' ', 7 + ((indentLevel-1) * spacesToIndent)) + "ELSEIF " + factor2.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doEND(CommonToken factor2) {
		// TODO Got to keep a stack of structured operations and then emit the right ENDxx opcode

	}

	private void doENDCS() {
		// Safely ignoring this as the CASxx methods terminate the individual CAS groups

	}

	private void doENDDO(CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDDO;";
		cspecs.add(workString);
		setIndentLevel(--indentLevel);

	}

	private void doENDFOR() {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDFOR;";
		cspecs.add(workString);
		setIndentLevel(--indentLevel);
	}

	private void doENDIF() {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
		setIndentLevel(--indentLevel);
	}

	private void doENDMON() {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDMON;";
		cspecs.add(workString);
		setIndentLevel(--indentLevel);
	}

	private void doENDSL() {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDSL;";
		cspecs.add(workString);
		setIndentLevel(--indentLevel);

	}

	private void doENDSR(CommonToken factor1, CommonToken factor2) {
		// If there is a label then emit a tag
		if (factor1.getText().trim().length() > 0){
			doTAG(factor1);
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDSR " + factor2.getText().trim() + ";";
		cspecs.add(workString);
		setIndentLevel(--indentLevel);

	}

	private void doEVAL(List<CommonToken> myList, int opCodeIndex) {
		boolean eolComment = false;
		workString = "";
		for (int i = opCodeIndex + 1; i < myList.size() - 1; i++) {
			CommonToken cta = myList.get(i);

			if (cta.getType() == RpgLexer.CE_COMMENTS80) {
				workString += "; // " + cta.getText().trim();
				eolComment = true;
			} else if (cta.getType() == RpgLexer.C_FREE_NEWLINE) {
				// Ignore the newlines
			} else {
				workString += cta.getText().trim() + ' ';
			}
		}
		if (!eolComment) {
			workString += ";";
		}
		cspecs.add(workString);
	}

	private void doEVAL_CORR(CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "EVAL-CORR " + factor2.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doEVALR(CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "EVALR " + factor2.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doEXCEPT(CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "EXCEPT " + factor2.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doEXFMT(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decPos, CommonToken low) {
		boolean ER = low.getText().trim().length() > 0;
		String opCode = "EXFMT";
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + opCode + factor2.getText().trim() + " " + result.getText().trim().length() + ";";
		if (ER){
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}
	}

	private void doEXSR(CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "EXSR " + factor2.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doEXTRCT(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doFEOD(CommonToken factor2, CommonToken low) {
		boolean ER = low.getText().trim().length() > 0;
		String opCode = "FEOD";
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + opCode + factor2.getText().trim() +";";
		cspecs.add(workString);

	}

	private void doFOR(CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "FOR " + factor2.getText().trim() +";";
		cspecs.add(workString);
		setIndentLevel(++ indentLevel );
	}

	private void doFORCE(CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "FORCE " + factor2.getText().trim() +";";
		cspecs.add(workString);

	}

	private void doGOTO(CommonToken factor2) throws RPGFormatException {
		cspecs.add("       /END-FREE");
		cspecs.add(RPGSpecs.formatCSpec(" ", " ", " ", " ", "GOTO", " ", factor2.getText().trim(), " ", " ", " ", " ", " ", "From a GOTO or CABxx statement"));
		cspecs.add("       /FREE");

	}

	private void doIF(CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				 + factor2.getText() + ";";
		cspecs.add(workString);
		setIndentLevel(++indentLevel);
	}

	private void doIFEQ(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText() + " = " + factor2.getText() + ";";
		cspecs.add(workString);
		setIndentLevel(++indentLevel);
	}

	private void doIFGE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText() + " >= " + factor2.getText() + ";";
		cspecs.add(workString);
		setIndentLevel(++indentLevel);
	}

	private void doIFGT(CommonToken factor1, CommonToken factor2) {
		workString =StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText() + " > " + factor2.getText() + ";";
		cspecs.add(workString);
		setIndentLevel(++indentLevel);
	}

	private void doIFLE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText() + " <= " + factor2.getText() + ";";
		cspecs.add(workString);
		setIndentLevel(++indentLevel);
	}

	private void doIFLT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText() + " < " + factor2.getText() + ";";
		setIndentLevel(++indentLevel);
	}

	private void doIFNE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText() + " <> " + factor2.getText() + ";";
		cspecs.add(workString);
		setIndentLevel(++indentLevel);
	}

	private void doIN(CommonToken factor1, CommonToken factor2, CommonToken low) {
		boolean ER = low.getText().trim().length() > 0;
		String opCode = "IN";
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ factor1.getText().trim() + " " + factor2.getText().trim() + ";";
		cspecs.add(workString);
	}

	private void doITER() {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ITER;";
		cspecs.add(workString);
	}

	private void doKFLD(CommonToken result, int opCodeIndex) throws RPGFormatException {
		workString = RPGSpecs.formatDSpec(' ' + result.getText().trim(), " ", " ", " ", " ", " ", " ", " ", " ", "From a KLIST KLFD");
		dspecs.add(workString);
	}

	private void doKLIST(CommonToken factor1, int opCodeIndex) throws RPGFormatException {
		inKList = true;
		workString = RPGSpecs.formatDSpec(' ' + factor1.getText().trim(), " ", " ", "DS", " ", " ", " ", " ", " ", "From a KLIST");
		dspecs.add("");
		dspecs.add(workString);
	}

	private void doLEAVE() {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "LEAVE;";
		cspecs.add(workString);
	}

	private void doLEAVESR() {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "LEAVESR;";
		cspecs.add(workString);
	}

	private void doLOOKUP(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doMHHZO(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decPos) {
		// TODO Auto-generated method stub

	}

	private void doMHLZO(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos) {
		// TODO Auto-generated method stub

	}

	private void doMLHZO(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos) {
		// TODO Auto-generated method stub

	}

	private void doMLLZO(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos) {
		// TODO Auto-generated method stub

	}

	private void doMONITOR() {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "MONITOR;";
		cspecs.add(workString);
		setIndentLevel(++indentLevel);
	}

	private void doMOVE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doMOVEA(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doMOVEL(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken high, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doMULT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken high, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doMVR(CommonToken result, CommonToken length,
			CommonToken decpos, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doNEXT(CommonToken factor1, CommonToken factor2,
			CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doOCCUR(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doON_ERROR(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doOPEN(CommonToken factor2, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doOREQ(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText() + " = " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doORGE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText() + " >= " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doORGT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText() + " > " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doORLE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText() + " <= " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doORLT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText() + " < " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doORNE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText() + " <> " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doOTHER() {
		// TODO Auto-generated method stub

	}

	private void doOUT(CommonToken factor1, CommonToken factor2, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doPARM(CommonToken result, int opCodeIndex) throws RPGFormatException {
		workString = RPGSpecs.formatDSpec(' ' + result.getText().trim(), " ", " ", " ", " ", " ", " ", " ", " ", "From PLIST PARM");
		dspecs.add(workString);
	}

	private void doPLIST(CommonToken factor1, int opCodeIndex) throws RPGFormatException {
		inPList = true;
		workString = RPGSpecs.formatDSpec(' ' + factor1.getText().trim(), " ", " ", "PI", " ", " ", " ", " ", " ", "From PLIST");
		dspecs.add("");
		dspecs.add(workString);
	}

	private void doPOST(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doREAD(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doREADC(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doREADE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doREADP(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doREADPE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doREALLOC(CommonToken factor2, CommonToken result,
			CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doREL(CommonToken factor1, CommonToken factor2, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doRESET(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doResultCheck(CommonToken result, CommonToken length,
			CommonToken decpos) throws RPGFormatException {
		boolean lengthFound = length.getText().trim().length() >0;
		String lengths = length.getText().trim();
		boolean decimalsFound = decpos.getText().trim().length() > 0;
		String decposs = decpos.getText().trim();
		
		if (lengthFound){
			if (decimalsFound){
				workString = RPGSpecs.formatDSpec(' ' + result.getText().trim(), " ", " ", "S", " ", lengths, " ", decposs, " ", "From conversion of result field");
				dspecs.add(workString);
			} else {
				workString = RPGSpecs.formatDSpec(' ' + result.getText().trim(), " ", " ", "S", " ", lengths, " ", " ", " ", "From conversion of result field");
				dspecs.add(workString);
			}
			
		}
		// TODO Auto-generated method stub
		
	}

	private void doRETURN(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doROLBK(CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doSCAN(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doSELECT() {
		// TODO Auto-generated method stub

	}

	private void doSETGT(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doSETLL(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doSETOFF(CommonToken high, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doSETON(CommonToken high, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doSHTDN(CommonToken high) {
		// TODO Auto-generated method stub

	}

	private void doSORTA(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doSQRT(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos) {
		// TODO Auto-generated method stub

	}

	private void doSUB(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken high, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doSUBDUR(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos) {
		// TODO Auto-generated method stub

	}

	private void doSUBST(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doTAG(CommonToken factor1) {
		// TODO Auto-generated method stub

	}

	private void doTEST(CommonToken factor1, CommonToken result, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doTESTB(CommonToken factor2, CommonToken result,
			CommonToken high, CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doTESTN(CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doTESTZ(CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doTIME(CommonToken result, CommonToken length,
			CommonToken decpos) {
		// TODO Auto-generated method stub

	}

	private void doUNLOCK(CommonToken factor2, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doUPDATE(CommonToken factor2, CommonToken result,
			CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doWHEN(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doWHENEQ(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doWHENGE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doWHENGT(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doWHENLE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doWHENLT(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doWHENNE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doWRITE(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doXFOOT(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doXLATE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doXML_INTO(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doXML_SAX(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doZ_ADD(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal) throws RPGFormatException {
		workString = StringUtils.repeat(" ", 7 + (indentLevel
				* spacesToIndent)) + result.getText().trim() + " = " + factor2.getText().trim() + ";";
		cspecs.add(workString);
		doResultCheck(result, length, decpos);
		if (high.getText().trim().length()> 0){
			setResultingIndicator(high, "IF " + result.getText().trim() + " >  0;");
		}
		if (low.getText().trim().length()> 0){
			setResultingIndicator(low, "IF " + result.getText().trim() + " <  0;");
		}
		if (equal.getText().trim().length()> 0){
			setResultingIndicator(equal, "IF " + result.getText().trim() + " =  0;");
		}
	}


	private void doZ_SUB(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal) throws RPGFormatException {
		workString = StringUtils.repeat(" ", 7 + (indentLevel
				* spacesToIndent)) + result.getText().trim() + " = " + factor2.getText().trim() + " * -1;";
		cspecs.add(workString);
		doResultCheck(result, length, decpos);
		if (high.getText().trim().length()> 0){
			setResultingIndicator(high, "IF " + result.getText().trim() + " >  0;");
		}
		if (low.getText().trim().length()> 0){
			setResultingIndicator(low, "IF " + result.getText().trim() + " <  0;");
		}
		if (equal.getText().trim().length()> 0){
			setResultingIndicator(equal, "IF " + result.getText().trim() + " =  0;");
		}
	}

	@Override
	public void exitCspec_fixed(Cspec_fixedContext ctx) {
		if (logger.isDebugEnabled()) {
			logger.debug("exitOp_pec_fixed(Cspec_fixedContext) - start"); //$NON-NLS-1$
			logger.debug(ctx.getText());
		}
		debugContext(ctx);

		super.exitCspec_fixed(ctx);

	}

	private void debugContext(ParserRuleContext ctx) {
		List<CommonToken> myList = getTheTokens(ctx);
		for (int i = 0; i < myList.size(); i++) {
			CommonToken ct = myList.get(i);
			if (ct.getType() == RpgLexer.CS_OperationAndExtender) {

				// Begin big ole switch statement
				try {
					checkOpCodeStuff(ct, myList, i);
				} catch (RPGFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (CommonToken ct : myList) {
			System.err.println(voc.getDisplayName(ct.getType()) + "\t"
					+ ct.getText());
		}
	}

	private List<CommonToken> getTheTokens(ParserRuleContext ctx) {
		List<CommonToken> myList = new ArrayList<CommonToken>();
		fillTokenList(ctx, myList);
		return myList;
	}

	@Override
	public void exitCspec_fixed_x2(Cspec_fixed_x2Context ctx) {
		if (logger.isDebugEnabled()) {
			logger.debug("exitOp_pec_fixed_x2(Cspec_fixed_x2Context) - start"); //$NON-NLS-1$
			logger.debug(ctx.getText());
		}

		List<CommonToken> myList = new ArrayList<CommonToken>();
		fillTokenList(ctx, myList);
		for (int i = 0; i < myList.size(); i++) {
			CommonToken ct = myList.get(i);
			if (ct.getType() == RpgLexer.CS_OperationAndExtendedFactor2) {

				// Begin big ole switch statement
				try {
					checkOpCodeStuff(ct, myList, i);
				} catch (RPGFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		for (CommonToken ct : myList) {
			System.err.println(voc.getDisplayName(ct.getType()) + "\t"
					+ ct.getText());
		}

		// TODO Auto-generated method stub
		super.exitCspec_fixed_x2(ctx);

		if (logger.isDebugEnabled()) {
			logger.debug("exitOp_pec_fixed_x2(Cspec_fixed_x2Context) - end"); //$NON-NLS-1$
		}
	}

	private void fillTokenList(ParseTree parseTree, List<CommonToken> tokenList) {
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            ParseTree payload = parseTree.getChild(i);

            if (payload.getPayload() instanceof CommonToken) {
                tokenList.add((CommonToken) payload.getPayload());
            } else {
                fillTokenList(payload, tokenList);
            }

        }
    }

	public int getIndentLevel() {
		return indentLevel;
	}

	public int getSpacesToIndent() {
		return spacesToIndent;
	}

	public void setIndentLevel(int indentLevel) {
		if (indentLevel < 0){
			this.indentLevel = 0;
		} else {
			this.indentLevel = indentLevel;
		}
	}

	private void setResultingIndicator(CommonToken indicator, String condition){
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + condition;
		cspecs.add(workString);
		workString = StringUtils.repeat(' ', 7 + ((indentLevel + 1) * spacesToIndent)) + "*IN" + indicator.getText().trim() + " = *ON;";
		cspecs.add(workString);
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "ELSE;";
		cspecs.add(workString);
		workString = StringUtils.repeat(' ', 7 + ((indentLevel + 1) * spacesToIndent)) + "*IN" + indicator.getText().trim() + " = *OFF;";
		cspecs.add(workString);
		workString = StringUtils.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}
    public void setSpacesToIndent(int spacesToIndent) {
		this.spacesToIndent = spacesToIndent;
	}
    
    private int getOpCodeIndex(List<? extends CommonToken> myList){
    	int result = 0;
		for (int i = 0; i < myList.size(); i++) {
			CommonToken ct = myList.get(i);
			if (ct.getType() == RpgLexer.CS_OperationAndExtender) {
				result = i;
			}
		}
		return result;
    }

	@Override
	public void exitCsZ_ADD(CsZ_ADDContext ctx) {
		if (logger.isDebugEnabled()) {
			logger.debug("exitCsZ_ADD(CsZ_ADDContext) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		super.exitCsZ_ADD(ctx);
		ParserRuleContext pctx = ctx.getParent();
		List<CommonToken> myList = getTheTokens(pctx);
		int opCodeIndex = getOpCodeIndex(myList);
		CommonToken factor2 = myList.get(opCodeIndex + 1);
		CommonToken result = myList.get(opCodeIndex + 2);
		doZ_ADD(factor2, result, length, decpos, high, low, equal);
		debugContext(pctx);

		if (logger.isDebugEnabled()) {
			logger.debug("exitCsZ_ADD(CsZ_ADDContext) - end"); //$NON-NLS-1$
		}
	}

}
