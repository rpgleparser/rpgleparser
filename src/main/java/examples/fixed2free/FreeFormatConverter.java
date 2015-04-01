package examples.fixed2free;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser.Cspec_fixedContext;
import org.rpgleparser.RpgParser.Cspec_fixed_x2Context;
import org.rpgleparser.RpgParserBaseListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreeFormatConverter extends RpgParserBaseListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FreeFormatConverter.class);

	public static void fillTokenList(ParseTree parseTree, List<CommonToken> tokenList) {
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            ParseTree payload = parseTree.getChild(i);

            if (payload.getPayload() instanceof CommonToken) {
                tokenList.add((CommonToken) payload.getPayload());
            } else {
                fillTokenList(payload, tokenList);
            }

        }
    }
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
			doPLIST(myList, opCodeIndex);
		} else if (opCode.equals("PARM")) {
			doPARM(myList, opCodeIndex);
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
		} else if (opCode.equals("Z-ADD")) {
			doZ_ADD(factor1, factor2, result);
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
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent)
				+ "ACQ " + factor1.getText() + " " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doADD(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos)
			throws RPGFormatException {
		if (length.getText().trim().length() != 0) {
			workString = RPGSpecs.formatDSpec(result.getText().trim(), " ",
					" ", "S", " ", length.getText().trim(), " ", decpos
							.getText().trim(), " ", "Created by conversion");
			dspecs.add(workString);
		}
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent)
				+ result.getText().trim() + " = " + factor1.getText() + " + "
				+ factor2.getText() + ";";
		cspecs.add(workString);

	}

	private void doADDDUR(CommonToken factor1, CommonToken factor2,
			CommonToken result) {
		// TODO Auto-generated method stub

	}

	private void doALLOC(CommonToken factor2, CommonToken result) {
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + result.getText().trim() + " = %ALLOC(" + factor2.getText().trim() + ");";
		cspecs.add(workString);

	}

	private void doANDEQ(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "AND " + factor1.getText() + " = " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDGE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "AND " + factor1.getText() + " >= " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDGT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "AND " + factor1.getText() + " > " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDLE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "AND " + factor1.getText() + " <= " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDLT(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "AND " + factor1.getText() + " < " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doANDNE(CommonToken factor1, CommonToken factor2) {
		workString = StringUtils.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "AND " + factor1.getText() + " <> " + factor2.getText() + ";";
		cspecs.add(workString);
	}

	private void doBEGSR(CommonToken factor1) {
		workString = " BEGSR " + factor1.getText().trim() + ";";
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
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCABGE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCABGT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCABLE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCABLT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCABNE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	private void doCASGE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCASGT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCASLE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCASLT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doCASNE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal) {
		// TODO Auto-generated method stub

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
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + opCode + " " + factor1.getText().trim() + " " + factor2.getText().trim() + " " + result.getText().trim() + ";";
		cspecs.add(workString);
		if (NR){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF %FOUND = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + high.getText() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
		}
		if (ER){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF %ERROR = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
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
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "CLEAR ";
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
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + opCode + " " + factor2.getText().trim() + ";";
		cspecs.add(workString);
		if (ER){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF %ERROR;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
		}

	}

	private void doCOMMIT(CommonToken factor1, CommonToken low) {
		String opCode = "COMMIT";
		boolean ER = (low.getText().trim().length() > 0);
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + opCode + " " + factor1.getText().trim() + ";";
		cspecs.add(workString);

	}
	
	

	private void doCOMP(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken equal) {
		if (high.getText().trim().length() > 0){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF " + factor1.getText().trim() + " > " + factor2.getText().trim() + ";" ;
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + high.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + high.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
		}
		if (low.getText().trim().length() > 0){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF " + factor1.getText().trim() + " < " + factor2.getText().trim() + ";" ;
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
		}
		if (equal.getText().trim().length() > 0){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF " + factor1.getText().trim() + " = " + factor2.getText().trim() + ";" ;
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + equal.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + equal.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
		}

	}

	private void doDEALLOC(CommonToken result, CommonToken low) {
		boolean ER = low.getText().trim().length() > 0;
		String opCode = "DEALLOC";
		if (ER){
			opCode += "(E)";
		}
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + opCode + result.getText().trim() + ";";
		cspecs.add(workString);
		if (ER){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF %ERROR;" ;
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
		}
	}

	private void doDEFINE(CommonToken factor1, CommonToken factor2,
			CommonToken result) throws RPGFormatException {
		if (factor1.getType() == RpgLexer.SPLAT_LIKE) {
			workString = RPGSpecs.formatDSpec(result.getText(), " ", " ", "S", " ", " ", " ", " ", "LIKE(" +factor2.getText().trim() + ")", "From a define statement");
			dspecs.add(workString);
		} else if (factor1.getType() == RpgLexer.SPLAT_DTAARA){
			workString = RPGSpecs.formatDSpec(result.getText(), " ", " ", "DS", " ", " ", " ", " ", "DTAARA(" +factor2.getText().trim() + ")", "From a define statement");
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
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + opCode + " " + factor1.getText().trim() + " " + factor2.getText().trim() + ";";
		cspecs.add(workString);
		if (ER){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF %ERROR;" ;
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
		}

		if (NR){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF %FOUND;" ;
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
			cspecs.add(workString);
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
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + result.getText().trim() + " = " + factor1.getText().trim() + " / " + factor2.getText().trim() + ";";
		} else {
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + result.getText().trim() + " = " + result.getText().trim() + " / " + factor2.getText().trim() + ";";
		}
		cspecs.add(workString);
		if (POS){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF " + result.getText().trim() + " > 0;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + high.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + high.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
		}
		if (NEG){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF " + result.getText().trim() + " < 0;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + low.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
		}
		if (ZERO){
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "IF " + result.getText().trim() + " = 0;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + equal.getText().trim() + " = *ON;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ELSE;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel + 1 * spacesToIndent) + "*IN" + equal.getText().trim() + " = *OFF;";
			cspecs.add(workString);
			workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "ENDIF;";
		}
	}

	private void doDO(CommonToken factor1, CommonToken factor2,
			CommonToken result) {
		workString = StringUtils.repeat(' ', 7 + indentLevel * spacesToIndent) + "FOR " + result.getText().trim() 
				+ " = " + factor1.getText().trim() + " TO "+ factor2.getText().trim() + ";";
		setIndentLevel(indentLevel++);
		cspecs.add(workString);
	}

	private void doDOU(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOUEQ(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOUGE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOUGT(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOULE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOULT(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOUNE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOW(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOWEQ(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOWGE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOWGT(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOWLE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOWLT(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDOWNE(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doDSPLY(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doDUMP(CommonToken factor1) {
		// TODO Auto-generated method stub

	}

	private void doELSE() {
		workString =StringUtils.repeat(" ", 7 + indentLevel * spacesToIndent) + "ELSE;";
		cspecs.add(workString);
	}

	private void doELSEIF(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doEND(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doENDCS() {
		// TODO Auto-generated method stub

	}

	private void doENDDO(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doENDFOR() {
		// TODO Auto-generated method stub

	}

	private void doENDIF() {
		workString = StringUtils.repeat(" ", 7 + indentLevel * spacesToIndent) + "END;";
		cspecs.add(workString);
		setIndentLevel(indentLevel--);
	}

	private void doENDMON() {
		// TODO Auto-generated method stub

	}

	private void doENDSL() {
		// TODO Auto-generated method stub

	}

	private void doENDSR(CommonToken factor1, CommonToken factor2) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	private void doEVALR(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doEXCEPT(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doEXFMT(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decPos, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doEXSR(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doEXTRCT(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doFEOD(CommonToken factor2, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doFOR(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doFORCE(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doGOTO(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doIF(CommonToken factor2) {
		// TODO Auto-generated method stub

	}

	private void doIFEQ(CommonToken factor1, CommonToken factor2) {
		setIndentLevel(indentLevel++);
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "IF "
				+ factor1.getText() + " = " + factor2.getText() + ";");
	}

	private void doIFGE(CommonToken factor1, CommonToken factor2) {
		setIndentLevel(indentLevel++);
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "IF "
				+ factor1.getText() + " >= " + factor2.getText() + ";");
	}

	private void doIFGT(CommonToken factor1, CommonToken factor2) {
		setIndentLevel(indentLevel++);
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "IF "
				+ factor1.getText() + " > " + factor2.getText() + ";");
	}

	private void doIFLE(CommonToken factor1, CommonToken factor2) {
		setIndentLevel(indentLevel++);
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "IF "
				+ factor1.getText() + " <= " + factor2.getText() + ";");
	}

	private void doIFLT(CommonToken factor1, CommonToken factor2) {
		setIndentLevel(indentLevel++);
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "IF "
				+ factor1.getText() + " < " + factor2.getText() + ";");
	}

	private void doIFNE(CommonToken factor1, CommonToken factor2) {
		setIndentLevel(indentLevel++);
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "IF "
				+ factor1.getText() + " <> " + factor2.getText() + ";");
	}

	private void doIN(CommonToken factor1, CommonToken factor2, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doITER() {
		// TODO Auto-generated method stub

	}

	private void doKFLD(CommonToken result, int opCodeIndex) {
		System.out.println("     D  " + result.getText());
	}

	private void doKLIST(CommonToken factor1, int opCodeIndex) {
		inKList = true;
		String klistName = factor1.getText().trim();
		System.out.println("     D " + klistName + "   DS");
	}

	private void doLEAVE() {
		// TODO Auto-generated method stub

	}

	private void doLEAVESR() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "OR "
				+ factor1.getText() + " = " + factor2.getText() + ";");
	}

	private void doORGE(CommonToken factor1, CommonToken factor2) {
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "OR "
				+ factor1.getText() + " >= " + factor2.getText() + ";");
	}

	private void doORGT(CommonToken factor1, CommonToken factor2) {
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "OR "
				+ factor1.getText() + " > " + factor2.getText() + ";");
	}

	private void doORLE(CommonToken factor1, CommonToken factor2) {
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "OR "
				+ factor1.getText() + " <= " + factor2.getText() + ";");
	}

	private void doORLT(CommonToken factor1, CommonToken factor2) {
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "OR "
				+ factor1.getText() + " < " + factor2.getText() + ";");
	}

	private void doORNE(CommonToken factor1, CommonToken factor2) {
		System.out.println(StringUtils
				.repeat(" ", 7 + indentLevel * spacesToIndent)
				+ "OR "
				+ factor1.getText() + " <> " + factor2.getText() + ";");
	}

	private void doOTHER() {
		// TODO Auto-generated method stub

	}

	private void doOUT(CommonToken factor1, CommonToken factor2, CommonToken low) {
		// TODO Auto-generated method stub

	}

	private void doPARM(List<CommonToken> myList, int opCodeIndex) {
		System.out.println("     D  " + myList.get(opCodeIndex + 2).getText());
	}

	private void doPLIST(List<CommonToken> myList, int opCodeIndex) {
		inPList = true;
		String plistName = myList.get(opCodeIndex - 1).getText().trim();
		System.out.println("     D " + plistName + "   PI");
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

	private void doZ_ADD(CommonToken factor1, CommonToken factor2,
			CommonToken result) {
		String factor1s = factor1.getText().trim();
		String factor2s = factor2.getText().trim();
		if (factor1s.length() == 0) {
			if (factor2s.length() == 0) {
				// Oops we should not be here!!!
			} else {
				System.out.println(StringUtils.repeat(" ", indentLevel
						* spacesToIndent)
						+ result.getText() + " = " + factor2s + ";");
			}
		} else {
			if (factor2s.length() == 0) {
				System.out.println(StringUtils.repeat(" ", indentLevel
						* spacesToIndent)
						+ result.getText() + " = " + factor1s + ";");
			} else {
				System.out.println(StringUtils.repeat(" ", indentLevel
						* spacesToIndent)
						+ result.getText()
						+ " = "
						+ factor1s
						+ " + "
						+ factor2s + ";");
			}
		}
	}

	private void doZ_ADD(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	private void doZ_SUB(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitCspec_fixed(Cspec_fixedContext ctx) {
		if (logger.isDebugEnabled()) {
			//			logger.debug("exitCspec_fixed(Cspec_fixedContext) - start"); //$NON-NLS-1$
			logger.debug(ctx.getText());
		}
		List<CommonToken> myList = new ArrayList<CommonToken>();
		fillTokenList(ctx, myList);
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

		super.exitCspec_fixed(ctx);

	}

	@Override
	public void exitCspec_fixed_x2(Cspec_fixed_x2Context ctx) {
		if (logger.isDebugEnabled()) {
			logger.debug("exitCspec_fixed_x2(Cspec_fixed_x2Context) - start"); //$NON-NLS-1$
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
			logger.debug("exitCspec_fixed_x2(Cspec_fixed_x2Context) - end"); //$NON-NLS-1$
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
    public void setSpacesToIndent(int spacesToIndent) {
		this.spacesToIndent = spacesToIndent;
	}


}
