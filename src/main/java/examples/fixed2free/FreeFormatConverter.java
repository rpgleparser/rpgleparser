package examples.fixed2free;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.rpgleparser.RpgParser.CsCALLPContext;
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
	private static final String AND_NOT = "AndNot";

	private static final String COMMENT = "Comment";

	private static final String CONDITIONING_INDICATOR = "ConditioningIndicator";

	private static final String CONTROL_LEVEL = "ControlLevel";

	private static final String DESC_POS = "DescPos";

	private static final String EQUAL = "Equal";

	private static final String EXT_FACTOR1 = "ExtFactor1";

	private static final String EXT_FACTOR2 = "ExtFactor2";

	private static final String EXT_OP_CODE = "ExtOpCode";

	private static final String EXT_RESULT = "ExtResult";

	private static final String FACTOR1 = "Factor1";

	private static final String FACTOR2 = "Factor2";

	private static final String HIGH = "High";

	private static final String LENGTH = "Length";

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FreeFormatConverter.class);

	private static final String LOW = "Low";

	private static final String OP_CODE = "OpCode";

	private static final String RESULT2 = "Result";

	private ArrayList<String> cspecs = new ArrayList<String>();
	private ArrayList<String> dspecs = new ArrayList<String>();
	private ArrayList<String> fspecs = new ArrayList<String>();
	private ArrayList<String> hspecs = new ArrayList<String>();
	private int indentLevel = 0;
	private ArrayList<String> ispecs = new ArrayList<String>();
	private ArrayList<String> ospecs = new ArrayList<String>();
	private int spacesToIndent = 3;
	private Vocabulary voc;
	private String workString;
	
	public FreeFormatConverter(RpgLexer lex){
		voc = lex.getVocabulary();
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

	private void debugContext(ParserRuleContext ctx) {
		List<CommonToken> myList = getTheTokens(ctx);
		for (CommonToken ct : myList) {
			System.err.println(ct.getTokenIndex() + "\t"+ voc.getDisplayName(ct.getType()) + "\t"
					+ ct.getText() + "\t @ " + ct.getCharPositionInLine());
		}
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

	private void doEVAL(CommonToken factor2) {
		boolean eolComment = false;
		workString = factor2.getText();
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

	private void doKFLD(CommonToken result) {
		// TODO Auto-generated method stub
		
	}

	private void doKFLD(CommonToken result, int opCodeIndex) throws RPGFormatException {
		workString = RPGSpecs.formatDSpec(' ' + result.getText().trim(), " ", " ", " ", " ", " ", " ", " ", " ", "From a KLIST KLFD");
		dspecs.add(workString);
	}

	private void doKLIST(CommonToken factor12) {
		// TODO Auto-generated method stub
		
	}

	private void doKLIST(CommonToken factor1, int opCodeIndex) throws RPGFormatException {
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

	private void doPARM(CommonToken result) {
		// TODO Auto-generated method stub
		
	}

	private void doPARM(CommonToken result, int opCodeIndex) throws RPGFormatException {
		workString = RPGSpecs.formatDSpec(' ' + result.getText().trim(), " ", " ", " ", " ", " ", " ", " ", " ", "From PLIST PARM");
		dspecs.add(workString);
	}

	private void doPLIST(CommonToken factor1) {
		try {
			workString = RPGSpecs.formatDSpec(' ' + factor1.getText().trim(), " ", " ", "PI", " ", " ", " ", " ", " ", "From PLIST");
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
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
	public void exitCsACQ(CsACQContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsACQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		List<CommonToken> myList = getTheTokens(pctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doACQ(factor1, factor2);
	}

	@Override
	public void exitCsADD(CsADDContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsADD(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		try {
			doADD(factor1, factor2, result, length, decpos);
		} catch (RPGFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsADDDUR(CsADDDURContext ctx) {
		super.exitCsADDDUR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		doADDDUR(factor1, factor2, result);
	}

	@Override
	public void exitCsALLOC(CsALLOCContext ctx) {
		super.exitCsALLOC(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		doALLOC(factor2, result);
	}
    @Override
	public void exitCsANDEQ(CsANDEQContext ctx) {
		super.exitCsANDEQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doANDEQ(factor1, factor2);
	}
    
    @Override
	public void exitCsANDGE(CsANDGEContext ctx) {
		super.exitCsANDGE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doANDGE(factor1, factor2);
	}

    @Override
	public void exitCsANDGT(CsANDGTContext ctx) {
		super.exitCsANDGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doANDGT(factor1, factor2);
	}
	@Override
	public void exitCsANDLE(CsANDLEContext ctx) {
		super.exitCsANDLE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doANDLE(factor1, factor2);
	}

	@Override
	public void exitCsANDLT(CsANDLTContext ctx) {
		super.exitCsANDLT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doANDLT(factor1, factor2);
	}

	@Override
	public void exitCsANDNE(CsANDNEContext ctx) {
		super.exitCsANDNE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doANDNE(factor1, factor2);
	}

	@Override
	public void exitCsBEGSR(CsBEGSRContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsBEGSR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		doBEGSR(factor1);
	}

	@Override
	public void exitCsBITOFF(CsBITOFFContext ctx) {
		super.exitCsBITOFF(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		doBITOFF(factor2, result);
	}


	@Override
	public void exitCsBITON(CsBITONContext ctx) {
		super.exitCsBITON(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		doBITON(factor2, result);
	}

	@Override
	public void exitCsCABEQ(CsCABEQContext ctx) {
		super.exitCsCABEQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		try {
			doCABEQ(factor1, factor2, result, high, low, equal);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABGE(CsCABGEContext ctx) {
		super.exitCsCABGE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		try {
			doCABGE(factor1, factor2, result, high, low, equal);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABGT(CsCABGTContext ctx) {
		super.exitCsCABGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		try {
			doCABGT(factor1, factor2, result, high, low, equal);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABLE(CsCABLEContext ctx) {
		super.exitCsCABLE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		try {
			doCABLE(factor1, factor2, result, high, low, equal);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABLT(CsCABLTContext ctx) {
		super.exitCsCABLT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		try {
			doCABLT(factor1, factor2, result, high, low, equal);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABNE(CsCABNEContext ctx) {
		super.exitCsCABNE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		try {
			doCABNE(factor1, factor2, result, high, low, equal);
		} catch (RPGFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCALL(CsCALLContext ctx) {
		super.exitCsCALL(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCALL(factor2, result, high, equal);
	}

	@Override
	public void exitCsCALLB(CsCALLBContext ctx) {
		super.exitCsCALLB(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken equal = temp.get(EQUAL);
		doCALLB(factor2, result, high, equal);
	}

	@Override
	public void exitCsCALLP(CsCALLPContext ctx) {
		super.exitCsCALLP(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doCALLP(factor2);
	}

	@Override
	public void exitCsCASEQ(CsCASEQContext ctx) {
		super.exitCsCASEQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCASEQ(factor1, factor2, result, high, low, equal);
	}

	@Override
	public void exitCsCASGE(CsCASGEContext ctx) {
		super.exitCsCASGE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCASGE(factor1, factor2, result, high, low, equal);
	}

	@Override
	public void exitCsCASGT(CsCASGTContext ctx) {
		super.exitCsCASGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCASGT(factor1, factor2, result, high, low, equal);
	}

	@Override
	public void exitCsCASLE(CsCASLEContext ctx) {
		super.exitCsCASLE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCASLE(factor1, factor2, result, high, low, equal);
	}

	@Override
	public void exitCsCASLT(CsCASLTContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsCASLT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCASLT(factor1, factor2, result, high, low, equal);
	}

	@Override
	public void exitCsCASNE(CsCASNEContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsCASNE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCASNE(factor1, factor2, result, high, low, equal);
	}

	@Override
	public void exitCsCAT(CsCATContext ctx) {
		super.exitCsCAT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		doCAT(factor1, factor2, result);
	}

	@Override
	public void exitCsCHAIN(CsCHAINContext ctx) {
		super.exitCsCHAIN(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		doCHAIN(factor1, factor2, result, high, low);
	}

	@Override
	public void exitCsCHECK(CsCHECKContext ctx) {
		super.exitCsCHECK(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCHECK(factor1, factor2, result, low, equal);
	}

	@Override
	public void exitCsCHECKR(CsCHECKRContext ctx) {
		super.exitCsCHECKR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCHECKR(factor1, factor2, result, low, equal);
	}

	@Override
	public void exitCsCLEAR(CsCLEARContext ctx) {
		super.exitCsCLEAR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		doCLEAR(factor1, factor2, result);
	}

	@Override
	public void exitCsCLOSE(CsCLOSEContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsCLOSE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		doCLOSE(factor2, low);
	}

	@Override
	public void exitCsCOMMIT(CsCOMMITContext ctx) {
		super.exitCsCOMMIT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken low = temp.get(LOW);
		doCOMMIT(factor1, low);
	}

	@Override
	public void exitCsCOMP(CsCOMPContext ctx) {
		super.exitCsCOMP(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doCOMP(factor1, factor2, high, low, equal);
	}

	@Override
	public void exitCsDEALLOC(CsDEALLOCContext ctx) {
		super.exitCsDEALLOC(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		doDEALLOC(result, low);
	}

	@Override
	public void exitCsDEFINE(CsDEFINEContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsDEFINE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		try {
			doDEFINE(factor1, factor2, result);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsDELETE(CsDELETEContext ctx) {
		super.exitCsDELETE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		doDELETE(factor1, factor2, high, low);
	}

	@Override
	public void exitCsDIV(CsDIVContext ctx) {
		super.exitCsDIV(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doDIV(factor1, factor2, result, high, low, equal);
	}

	@Override
	public void exitCsDO(CsDOContext ctx) {
		super.exitCsDO(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		doDO(factor1, factor2, result);
	}

	@Override
	public void exitCsDOU(CsDOUContext ctx) {
		super.exitCsDOU(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOU(factor2);
	}

	@Override
	public void exitCsDOUEQ(CsDOUEQContext ctx) {
		super.exitCsDOUEQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOUEQ(factor1, factor2);
	}

	@Override
	public void exitCsDOUGE(CsDOUGEContext ctx) {
		super.exitCsDOUGE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOUGE(factor1, factor2);
	}

	@Override
	public void exitCsDOUGT(CsDOUGTContext ctx) {
		super.exitCsDOUGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOUGT(factor1, factor2);
	}

	@Override
	public void exitCsDOULE(CsDOULEContext ctx) {
		super.exitCsDOULE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOULE(factor1, factor2);
	}

	@Override
	public void exitCsDOULT(CsDOULTContext ctx) {
		super.exitCsDOULT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOULT(factor1, factor2);
	}

	@Override
	public void exitCsDOUNE(CsDOUNEContext ctx) {
		super.exitCsDOUNE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOUNE(factor1, factor2);
	}

	@Override
	public void exitCsDOW(CsDOWContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsDOW(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOW(factor2);
	}

	@Override
	public void exitCsDOWEQ(CsDOWEQContext ctx) {
		super.exitCsDOWEQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOWEQ(factor1, factor2);
	}

	@Override
	public void exitCsDOWGE(CsDOWGEContext ctx) {
		super.exitCsDOWGE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOWGE(factor1, factor2);
	}

	@Override
	public void exitCsDOWGT(CsDOWGTContext ctx) {
		super.exitCsDOWGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOWGT(factor1, factor2);
	}

	@Override
	public void exitCsDOWLE(CsDOWLEContext ctx) {
		super.exitCsDOWLE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOWLE(factor1, factor2);
	}

	@Override
	public void exitCsDOWLT(CsDOWLTContext ctx) {
		super.exitCsDOWLT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOWLT(factor1, factor2);
	}

	@Override
	public void exitCsDOWNE(CsDOWNEContext ctx) {
		super.exitCsDOWNE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		ParserRuleContext gpctx = pctx.getParent();
		Map<String, CommonToken> temp = getFields(gpctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doDOWNE(factor1, factor2);
	}

	@Override
	public void exitCsDSPLY(CsDSPLYContext ctx) {
		super.exitCsDSPLY(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		doDSPLY(factor1, factor2, result, low);
	}

	@Override
	public void exitCsDUMP(CsDUMPContext ctx) {
		super.exitCsDUMP(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		doDUMP(factor1);
	}

	@Override
	public void exitCsELSE(CsELSEContext ctx) {
		super.exitCsELSE(ctx);
		doELSE();
	}

	@Override
	public void exitCsELSEIF(CsELSEIFContext ctx) {
		super.exitCsELSEIF(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doELSEIF(factor2);
	}

	@Override
	public void exitCsEND(CsENDContext ctx) {
		super.exitCsEND(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doEND(factor2);
	}

	@Override
	public void exitCsENDCS(CsENDCSContext ctx) {
		super.exitCsENDCS(ctx);
		doENDCS();
	}

	@Override
	public void exitCsENDDO(CsENDDOContext ctx) {
		super.exitCsENDDO(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doENDDO(factor2);
	}

	@Override
	public void exitCsENDFOR(CsENDFORContext ctx) {
		super.exitCsENDFOR(ctx);
		doENDFOR();
	}

	@Override
	public void exitCsENDIF(CsENDIFContext ctx) {
		super.exitCsENDIF(ctx);
		doENDIF();
	}

	@Override
	public void exitCsENDMON(CsENDMONContext ctx) {
		super.exitCsENDMON(ctx);
		doENDMON();
	}

	@Override
	public void exitCsENDSL(CsENDSLContext ctx) {
		super.exitCsENDSL(ctx);
		doENDSL();
	}

	@Override
	public void exitCsENDSR(CsENDSRContext ctx) {
		super.exitCsENDSR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doENDSR(factor1, factor2);
	}

	@Override
	public void exitCsEVAL(CsEVALContext ctx) {
		super.exitCsEVAL(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doEVAL(factor2);
	}

	@Override
	public void exitCsEVAL_CORR(CsEVAL_CORRContext ctx) {
		super.exitCsEVAL_CORR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doEVAL_CORR(factor2);
	}

	@Override
	public void exitCsEVALR(CsEVALRContext ctx) {
		super.exitCsEVALR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doEVALR(factor2);
	}

	@Override
	public void exitCsEXCEPT(CsEXCEPTContext ctx) {
		super.exitCsEXCEPT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doEXCEPT(factor2);
	}

	@Override
	public void exitCsEXFMT(CsEXFMTContext ctx) {
		super.exitCsEXFMT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken low = temp.get(LOW);
		doEXFMT(factor2, result, length, decpos, low);
	}

	@Override
	public void exitCsEXSR(CsEXSRContext ctx) {
		super.exitCsEXSR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doEXSR(factor2);
	}

	@Override
	public void exitCsEXTRCT(CsEXTRCTContext ctx) {
		super.exitCsEXTRCT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doEXTRCT(factor2);
	}

	@Override
	public void exitCsFEOD(CsFEODContext ctx) {
		super.exitCsFEOD(ctx);
		ParserRuleContext pctx = ctx.getParent();
		List<CommonToken> myList = getTheTokens(pctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		doFEOD(factor2, low);
	}

	@Override
	public void exitCsFOR(CsFORContext ctx) {
		super.exitCsFOR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doFOR(factor2);
	}

	@Override
	public void exitCsFORCE(CsFORCEContext ctx) {
		super.exitCsFORCE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doFORCE(factor2);
	}

	@Override
	public void exitCsGOTO(CsGOTOContext ctx) {
		super.exitCsGOTO(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		try {
			doGOTO(factor2);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsIF(CsIFContext ctx) {
		super.exitCsIF(ctx);
		doENDIF();
	}

	@Override
	public void exitCsIFEQ(CsIFEQContext ctx) {
		super.exitCsIFEQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doIFEQ(factor1, factor2);
	}

	@Override
	public void exitCsIFGE(CsIFGEContext ctx) {
		super.exitCsIFGE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doIFGE(factor1, factor2);
	}

	@Override
	public void exitCsIFGT(CsIFGTContext ctx) {
		super.exitCsIFGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doIFGT(factor1, factor2);
	}

	@Override
	public void exitCsIFLE(CsIFLEContext ctx) {
		super.exitCsIFLE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doIFLE(factor1, factor2);
	}

	@Override
	public void exitCsIFLT(CsIFLTContext ctx) {
		super.exitCsIFLT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doIFLT(factor1, factor2);
	}

	@Override
	public void exitCsIFNE(CsIFNEContext ctx) {
		super.exitCsIFNE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doIFNE(factor1, factor2);
	}

	@Override
	public void exitCsIN(CsINContext ctx) {
		super.exitCsIN(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		doIN(factor1, factor2, low);
	}

	@Override
	public void exitCsITER(CsITERContext ctx) {
		super.exitCsITER(ctx);
		doITER();
	}

	@Override
	public void exitCsKFLD(CsKFLDContext ctx) {
		super.exitCsKFLD(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		doKFLD(result);
	}

	@Override
	public void exitCsKLIST(CsKLISTContext ctx) {
		super.exitCsKLIST(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		doKLIST(factor1);
	}

	@Override
	public void exitCsLEAVE(CsLEAVEContext ctx) {
		super.exitCsLEAVE(ctx);
		doLEAVE();
	}

	@Override
	public void exitCsLEAVESR(CsLEAVESRContext ctx) {
		super.exitCsLEAVESR(ctx);
		doLEAVESR();
	}

	@Override
	public void exitCsLOOKUP(CsLOOKUPContext ctx) {
		super.exitCsLOOKUP(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doLOOKUP(factor1, factor2, high, low, equal);
	}

	@Override
	public void exitCsMHHZO(CsMHHZOContext ctx) {
		super.exitCsMHHZO(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		doMHHZO(factor2, result, length, decpos);
	}

	@Override
	public void exitCsMHLZO(CsMHLZOContext ctx) {
		super.exitCsMHLZO(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		doMHLZO(factor2, result, length, decpos);
	}

	@Override
	public void exitCsMLHZO(CsMLHZOContext ctx) {
		super.exitCsMLHZO(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		doMLHZO(factor2, result, length, decpos);
	}

	@Override
	public void exitCsMLLZO(CsMLLZOContext ctx) {
		super.exitCsMLLZO(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		doMLLZO(factor2, result, length, decpos);
	}

	@Override
	public void exitCsMONITOR(CsMONITORContext ctx) {
		super.exitCsMONITOR(ctx);
		doMONITOR();
	}

	@Override
	public void exitCsMOVE(CsMOVEContext ctx) {
		super.exitCsMOVE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doMOVE(factor1, factor2, result, high, low, equal);
	}

	@Override
	public void exitCsMOVEA(CsMOVEAContext ctx) {
		super.exitCsMOVEA(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doMOVEA(factor2, result, length, decpos, high, low, equal);
	}

	@Override
	public void exitCsMOVEL(CsMOVELContext ctx) {
		super.exitCsMOVEL(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doMOVEL(factor1, factor2, result, length, decpos, high, low, equal);
	}

	@Override
	public void exitCsMULT(CsMULTContext ctx) {
		super.exitCsMULT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doMULT(factor1, factor2, result, length, decpos, high, low, equal);
	}

	@Override
	public void exitCsMVR(CsMVRContext ctx) {
		super.exitCsMVR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doMVR(result, length, decpos, high, low, equal);
	}

	@Override
	public void exitCsNEXT(CsNEXTContext ctx) {
		super.exitCsNEXT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		doNEXT(factor1, factor2, low);
	}

	@Override
	public void exitCsOCCUR(CsOCCURContext ctx) {
		super.exitCsOCCUR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		doOCCUR(factor1, factor2, result, low);
	}

	@Override
	public void exitCsON_ERROR(CsON_ERRORContext ctx) {
		super.exitCsON_ERROR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doON_ERROR(factor2);
	}

	@Override
	public void exitCsOPEN(CsOPENContext ctx) {
		super.exitCsOPEN(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		doOPEN(factor2, low);
	}

	@Override
	public void exitCsOREQ(CsOREQContext ctx) {
		super.exitCsOREQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doOREQ(factor1, factor2);
	}

	@Override
	public void exitCsORGE(CsORGEContext ctx) {
		super.exitCsORGE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doORGE(factor1, factor2);
	}

	@Override
	public void exitCsORGT(CsORGTContext ctx) {
		super.exitCsORGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doORGT(factor1, factor2);
	}

	@Override
	public void exitCsORLE(CsORLEContext ctx) {
		super.exitCsORLE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doORLE(factor1, factor2);
	}

	@Override
	public void exitCsORLT(CsORLTContext ctx) {
		super.exitCsORLT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doORLT(factor1, factor2);
	}

	@Override
	public void exitCsORNE(CsORNEContext ctx) {
		super.exitCsORNE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doORNE(factor1, factor2);
	}

	@Override
	public void exitCsOTHER(CsOTHERContext ctx) {
		super.exitCsOTHER(ctx);
		doOTHER();
	}

	@Override
	public void exitCsOUT(CsOUTContext ctx) {
		super.exitCsOUT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		doOUT(factor1, factor2, low);
	}

	@Override
	public void exitCsPARM(CsPARMContext ctx) {
		super.exitCsPARM(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		doPARM(result);
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


	@Override
	public void exitCsPLIST(CsPLISTContext ctx) {
		super.exitCsPLIST(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		doPLIST(factor1);
	}

	@Override
	public void exitCsPOST(CsPOSTContext ctx) {
		super.exitCsPOST(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		doPOST(factor1, factor2, result, low);
	}

	@Override
	public void exitCsREAD(CsREADContext ctx) {
		super.exitCsREAD(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doREAD(factor2, result, low, equal);
	}

	@Override
	public void exitCsREADC(CsREADCContext ctx) {
		super.exitCsREADC(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doREADC(factor2, result, low, equal);
	}

	@Override
	public void exitCsREADE(CsREADEContext ctx) {
		super.exitCsREADE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doREADE(factor1, factor2, result, low, equal);
	}

	@Override
	public void exitCsREADP(CsREADPContext ctx) {
		super.exitCsREADP(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doREADP(factor2, result, low, equal);
	}

	@Override
	public void exitCsREADPE(CsREADPEContext ctx) {
		super.exitCsREADPE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doREADPE(factor1, factor2, result, low, equal);
	}

	@Override
	public void exitCsREALLOC(CsREALLOCContext ctx) {
		super.exitCsREALLOC(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		doREALLOC(factor2, result, low);
	}

	@Override
	public void exitCsREL(CsRELContext ctx) {
		super.exitCsREL(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		doREL(factor1, factor2, low);
	}

	@Override
	public void exitCsRESET(CsRESETContext ctx) {
		super.exitCsRESET(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		doRESET(factor1, factor2, result, low);
	}

	@Override
	public void exitCsRETURN(CsRETURNContext ctx) {
		super.exitCsRETURN(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doRETURN(factor2);
	}

	@Override
	public void exitCsROLBK(CsROLBKContext ctx) {
		super.exitCsROLBK(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken low = temp.get(LOW);
		doROLBK(low);
	}

	@Override
	public void exitCsSCAN(CsSCANContext ctx) {
		super.exitCsSCAN(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doSCAN(factor1, factor2, result, length, decpos, low, equal);
	}

	@Override
	public void exitCsSELECT(CsSELECTContext ctx) {
		super.exitCsSELECT(ctx);
		doSELECT();
	}

	@Override
	public void exitCsSETGT(CsSETGTContext ctx) {
		super.exitCsSETGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		doSETGT(factor1, factor2, high, low);
	}

	@Override
	public void exitCsSETLL(CsSETLLContext ctx) {
		super.exitCsSETLL(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doSETLL(factor1, factor2, high, low, equal);
	}

	@Override
	public void exitCsSETOFF(CsSETOFFContext ctx) {
		super.exitCsSETOFF(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doSETOFF(high, low, equal);
	}

	@Override
	public void exitCsSETON(CsSETONContext ctx) {
		super.exitCsSETON(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doSETON(high, low, equal);
	}

	@Override
	public void exitCsSHTDN(CsSHTDNContext ctx) {
		super.exitCsSHTDN(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken high = temp.get(HIGH);
		doSHTDN(high);
	}

	@Override
	public void exitCsSORTA(CsSORTAContext ctx) {
		super.exitCsSORTA(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doSORTA(factor2);
	}

	@Override
	public void exitCsSQRT(CsSQRTContext ctx) {
		super.exitCsSQRT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		doSQRT(factor2, result, length, decpos);
	}

	@Override
	public void exitCsSUB(CsSUBContext ctx) {
		super.exitCsSUB(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doSUB(factor1, factor2, result, length, decpos, high, low, equal);
	}

	@Override
	public void exitCsSUBDUR(CsSUBDURContext ctx) {
		super.exitCsSUBDUR(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		doSUBDUR(factor1, factor2, result, length, decpos);
	}

	@Override
	public void exitCsSUBST(CsSUBSTContext ctx) {
		super.exitCsSUBST(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken low = temp.get(LOW);
		doSUBST(factor1, factor2, result, length, decpos, low);
	}

	@Override
	public void exitCsTAG(CsTAGContext ctx) {
		super.exitCsTAG(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		doTAG(factor1);
	}

	@Override
	public void exitCsTEST(CsTESTContext ctx) {
		super.exitCsTEST(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		doTEST(factor1, result, low);
	}

	@Override
	public void exitCsTESTB(CsTESTBContext ctx) {
		super.exitCsTESTB(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doTESTB(factor2, result, high, low, equal);
	}

	@Override
	public void exitCsTESTN(CsTESTNContext ctx) {
		super.exitCsTESTN(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doTESTN(result, high, low, equal);
	}

	@Override
	public void exitCsTESTZ(CsTESTZContext ctx) {
		super.exitCsTESTZ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doTESTZ(result, high, low, equal);
	}

	@Override
	public void exitCsTIME(CsTIMEContext ctx) {
		super.exitCsTIME(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		doTIME(result, length, decpos);
	}

	@Override
	public void exitCsUNLOCK(CsUNLOCKContext ctx) {
		super.exitCsUNLOCK(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		doUNLOCK(factor2, low);
	}

	@Override
	public void exitCsUPDATE(CsUPDATEContext ctx) {
		super.exitCsUPDATE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		doUPDATE(factor2, result, low);
	}

	@Override
	public void exitCsWHEN(CsWHENContext ctx) {
		super.exitCsWHEN(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doWHEN(factor2);
	}

	@Override
	public void exitCsWHENEQ(CsWHENEQContext ctx) {
		super.exitCsWHENEQ(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doWHENEQ(factor1, factor2);
	}

	@Override
	public void exitCsWHENGE(CsWHENGEContext ctx) {
		super.exitCsWHENGE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doWHENGE(factor1, factor2);
	}

	@Override
	public void exitCsWHENGT(CsWHENGTContext ctx) {
		super.exitCsWHENGT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doWHENGT(factor1, factor2);
	}

	@Override
	public void exitCsWHENLE(CsWHENLEContext ctx) {
		super.exitCsWHENLE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doWHENLE(factor1, factor2);
	}

	@Override
	public void exitCsWHENLT(CsWHENLTContext ctx) {
		super.exitCsWHENLT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doWHENLT(factor1, factor2);
	}

	@Override
	public void exitCsWHENNE(CsWHENNEContext ctx) {
		super.exitCsWHENNE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doWHENNE(factor1, factor2);
	}

	@Override
	public void exitCsWRITE(CsWRITEContext ctx) {
		super.exitCsWRITE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doWRITE(factor2, result, low, equal);
	}

	@Override
	public void exitCsXFOOT(CsXFOOTContext ctx) {
		super.exitCsXFOOT(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		doXFOOT(factor2, result, length, decpos, high, low, equal);
	}

	@Override
	public void exitCsXLATE(CsXLATEContext ctx) {
		super.exitCsXLATE(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken low = temp.get(LOW);
		doXLATE(factor1, factor2, result, length, decpos, low);
	}

	@Override
	public void exitCsXML_INTO(CsXML_INTOContext ctx) {
		super.exitCsXML_INTO(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doXML_INTO(factor2);
	}

	@Override
	public void exitCsXML_SAX(CsXML_SAXContext ctx) {
		super.exitCsXML_SAX(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		doXML_SAX(factor2);
	}

	@Override
	public void exitCsZ_ADD(CsZ_ADDContext ctx) {
		if (logger.isDebugEnabled()) {
			logger.debug("exitCsZ_ADD(CsZ_ADDContext) - start"); //$NON-NLS-1$
		}

		super.exitCsZ_ADD(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		try {
			doZ_ADD(factor2, result, length, decpos, high, low, equal);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("exitCsZ_ADD(CsZ_ADDContext) - end"); //$NON-NLS-1$
		}
	}

	@Override
	public void exitCsZ_SUB(CsZ_SUBContext ctx) {
		super.exitCsZ_SUB(ctx);
		ParserRuleContext pctx = ctx.getParent();
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		try {
			doZ_SUB(factor2, result, length, decpos, high, low, equal);
		} catch (RPGFormatException e) {
			e.printStackTrace();
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

	private Map<String, CommonToken> getFields(ParserRuleContext ctx){
    	HashMap<String, CommonToken> result = new HashMap<String, CommonToken>();
    	ArrayList<CommonToken> myList = new ArrayList<CommonToken>();
    	fillTokenList(ctx, myList); 
    	String lastTokenType = "";
    	String ExtFactor1 = "";
    	String ExtOpCode = "";
    	String ExtFactor2 = "";
    	String ExtResult = "";
    	for (int i = 0; i < myList.size() ; i++){
    		CommonToken ct = myList.get(i);
    		int thePos = ct.getCharPositionInLine();
    		if (thePos == 5){
    			lastTokenType =voc.getDisplayName( ct.getType());
    			result.put(lastTokenType, ct);
    		}else  if (thePos == 6){
    			lastTokenType = CONTROL_LEVEL;
    			result.put(CONTROL_LEVEL, ct);
    		} else if (thePos == 8){
    			lastTokenType = AND_NOT;
    			result.put(AND_NOT, ct);
    		} else if (thePos == 9){
    			lastTokenType = CONDITIONING_INDICATOR;
    			result.put(CONDITIONING_INDICATOR, ct);
    		} else if (thePos == 11){
    			lastTokenType = FACTOR1;
    			result.put(FACTOR1, ct);
    			ExtFactor1 = ct.getText().trim();
    		} else if (thePos > 11 && thePos < 25){
    			ExtFactor1 += ct.getText().trim();
    		} else if (thePos == 25){
       		    // First put the extended factor1 into the map
    			CommonToken work = new CommonToken(RpgLexer.CS_FactorContent, ExtFactor1);
    			result.put(EXT_FACTOR1, work);
    			//Now reset the factor1 String
    			ExtFactor1 = "";

    			//Now put the opCode in
    			lastTokenType = OP_CODE;
    			ExtOpCode = ct.getText().trim();
    			result.put(OP_CODE, ct);
    			// Prepare to accumulate OpCode stuff
    		} else if (thePos > 25 && thePos < 35){
    			ExtOpCode += ct.getText().trim();
    		} else if (thePos == 35){
       		    // First put the extended opcode into the map
    			CommonToken work = new CommonToken(RpgLexer.CS_OperationAndExtender, ExtOpCode);
    			result.put(EXT_OP_CODE, work);
    			//Now reset the opCode String
    			ExtOpCode = "";

    			//Now put Factor2 stuff in
    			ExtFactor2 = ct.getText();
    			lastTokenType = FACTOR2;
    			result.put(FACTOR2, ct);
    		} else if (thePos > 35 && thePos < 49){
    			ExtFactor2 += ct.getText().trim();
    		} else if (thePos == 49){
       		    // First put the extended factor2 into the map
    			CommonToken work = new CommonToken(RpgLexer.CS_FactorContent, ExtFactor2);
    			result.put(EXT_FACTOR2, work);
    			//Now reset the factor2 String
    			ExtFactor2 = "";
    			
    			lastTokenType = RESULT2;
    			result.put(RESULT2, ct);
    			ExtResult = ct.getText().trim();
    		} else if (thePos > 49 && thePos < 63){
    			ExtResult += ct.getText().trim();
    		} else if (thePos == 63){
    			//First put the ExtResult in the map
    			CommonToken work = new CommonToken(RpgLexer.CS_FactorContent, ExtResult);
    			result.put(EXT_RESULT, work);
    			//Now reset the result String
    			ExtResult = "";
    			
    			result.put(LENGTH, ct);
    		} else if (thePos == 68){
    			result.put(DESC_POS, ct);
    		} else if (thePos == 70){
    			result.put(HIGH, ct);
    		} else if (thePos == 72){
    			result.put(LOW, ct);
    		} else if (thePos == 74){
    			result.put(EQUAL, ct);
    		} else if (thePos == 80){
    			result.put(COMMENT, ct);
    		} else {
    			result.put(voc.getDisplayName(ct.getType()), ct);
    		}
    	}
    	
    	return result;
    }

	public int getIndentLevel() {
		return indentLevel;
	}

	public int getSpacesToIndent() {
		return spacesToIndent;
	}

	private List<CommonToken> getTheTokens(ParserRuleContext ctx) {
		List<CommonToken> myList = new ArrayList<CommonToken>();
		fillTokenList(ctx, myList);
		return myList;
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

}
