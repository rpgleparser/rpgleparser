package examples.fixed2free;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.rpgleparser.*;
import org.rpgleparser.RpgParser.CsENDSRContext;
import org.rpgleparser.RpgParser.EndsrContext;
import org.rpgleparser.RpgParser.*;
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
	private Stack<String> structuredOps = new Stack<String>();
	private CommonTokenStream ts;
	private String currentSpec = "H";

	public FreeFormatConverter(RpgLexer lex, CommonTokenStream toks) {
		voc = lex.getVocabulary();
		ts = toks;
	}

	public List<String> collectOutput() {
		ArrayList<String> result = new ArrayList<String>(hspecs.size()
				+ fspecs.size() + ispecs.size() + dspecs.size() + cspecs.size()
				+ ospecs.size());
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
			System.err.println(ct.getTokenIndex() + "\t"
					+ voc.getDisplayName(ct.getType()).trim() //+ "\t" + ct.getText()
					+ "\t @ " + ct.getCharPositionInLine());
		}
	}

	private void doACQ(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "ACQ "
				+ factor1.getText()
				+ " "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doADD(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken comment) throws RPGFormatException {
		doResultCheck(result, length, decpos);
		if (factor1.getType() != RpgLexer.CS_BlankFactor
				&& !factor1.getText().trim().isEmpty()) {
			workString = StringUtils.repeat(' ',
					7 + (indentLevel * spacesToIndent))
					+ result.getText().trim()
					+ " = "
					+ factor1.getText()
					+ " + " + factor2.getText() + doEOLComment(comment);
			cspecs.add(workString);
		} else {
			workString = StringUtils.repeat(' ',
					7 + (indentLevel * spacesToIndent))
					+ result.getText().trim()
					+ " += "
					+ factor2.getText()
					+ doEOLComment(comment);
			cspecs.add(workString);
		}

	}

	private void doADDDUR(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken comment) {
		String fullFactor2 = factor2.getText();
		String factor1s = factor1.getText().trim();
		String[] factor2Parts = fullFactor2.split(":");
		boolean ER = low.getText().trim().length() > 0;
		String duration;
		String durCode;
		String bif = null;
		if (factor2Parts.length == 2) {
			duration = factor2Parts[0];
			durCode = factor2Parts[1];
			if (durCode.equalsIgnoreCase("*D")
					|| durCode.equalsIgnoreCase("*DAYS")) {
				bif = "%DAYS";
			} else if (durCode.equalsIgnoreCase("*M")
					|| durCode.equalsIgnoreCase("*MONTHS")) {
				bif = "%MONTHS";
			} else if (durCode.equalsIgnoreCase("*Y")
					|| durCode.equalsIgnoreCase("*YEARS")) {
				bif = "%YEARS";
			} else if (durCode.equalsIgnoreCase("*H")
					|| durCode.equalsIgnoreCase("*HOURS")) {
				bif = "%HOURS";
			} else if (durCode.equalsIgnoreCase("*MN")
					|| durCode.equalsIgnoreCase("*MINUTES")) {
				bif = "%MINUTES";
			} else if (durCode.equalsIgnoreCase("*S")
					|| durCode.equalsIgnoreCase("*SECONDS")) {
				bif = "%SECONDS";
			} else if (durCode.equalsIgnoreCase("*MS")
					|| durCode.equalsIgnoreCase("*MSECONDS")) {
				bif = "%MSECONDS";
			}

			if (bif != null) {
				// Use a monitor group if an error indicator was used
				if (ER) {
					workString = StringUtils.repeat(' ',
							7 + (indentLevel * spacesToIndent)) + "MONITOR;";
					cspecs.add(workString);
					workString = StringUtils.repeat(' ',
							7 + ((indentLevel + 1) * spacesToIndent))
							+ "*IN"
							+ low.getText().trim() + " = *OFF;";
					cspecs.add(workString);
				}
				if (factor1s.length() == 0) {
					workString = StringUtils.repeat(' ',
							7 + ((indentLevel + 1) * spacesToIndent))
							+ result.getText().trim()
							+ " += "
							+ bif
							+ "("
							+ duration + ")" + doEOLComment(comment);
					cspecs.add(workString);
				} else {
					workString = StringUtils.repeat(' ',
							7 + ((indentLevel + 1) * spacesToIndent))
							+ result.getText().trim()
							+ " = "
							+ factor1.getText().trim()
							+ " + "
							+ bif
							+ "("
							+ duration + ")" + doEOLComment(comment);
				}

				if (ER) {
					workString = StringUtils.repeat(' ',
							7 + (indentLevel * spacesToIndent)) + "ON-ERROR;";
					cspecs.add(workString);
					workString = StringUtils.repeat(' ',
							7 + ((indentLevel + 1) * spacesToIndent))
							+ "*IN"
							+ low.getText().trim() + " = *ON;";
					cspecs.add(workString);
					workString = StringUtils.repeat(' ',
							7 + (indentLevel * spacesToIndent)) + "ENDMON;";
					cspecs.add(workString);
				}

			}
		}

	}

	private void doALLOC(CommonToken factor2, CommonToken result,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ result.getText().trim()
				+ " = %ALLOC("
				+ factor2.getText().trim() + ");";
		cspecs.add(workString);

	}

	private void doANDEQ(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND "
				+ factor1.getText()
				+ " = "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doANDGE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND "
				+ factor1.getText()
				+ " >= "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doANDGT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND "
				+ factor1.getText()
				+ " > "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doANDLE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND "
				+ factor1.getText()
				+ " <= "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doANDLT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND "
				+ factor1.getText()
				+ " < "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doANDNE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "AND "
				+ factor1.getText()
				+ " <> "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doBEGSR(CommonToken factor1, CommonToken comment) {
		setIndentLevel(0);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "BEGSR "
				+ factor1.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doBITOFF(CommonToken factor2, CommonToken result,
			CommonToken comment) {
		byte bitmask = (byte) 255;
		String inputBits = factor2.getText();

		if (inputBits.contains("0")) {
			bitmask -= 128;
		}
		if (inputBits.contains("1")) {
			bitmask -= 64;
		}
		if (inputBits.contains("2")) {
			bitmask -= 32;
		}
		if (inputBits.contains("3")) {
			bitmask -= 16;
		}
		if (inputBits.contains("4")) {
			bitmask -= 8;
		}
		if (inputBits.contains("5")) {
			bitmask -= 4;
		}
		if (inputBits.contains("6")) {
			bitmask -= 2;
		}
		if (inputBits.contains("7")) {
			bitmask -= 1;
		}
		String hexChar = String.format("x", bitmask);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "%BITAND("
				+ factor2.getText().trim()
				+ " : x'"
				+ hexChar
				+ "') "
				+ doEOLComment(comment);
	}

	private void doBITON(CommonToken factor2, CommonToken result,
			CommonToken comment) {
		byte bitmask = 0;
		String inputBits = factor2.getText();

		if (inputBits.contains("0")) {
			bitmask += 128;
		}
		if (inputBits.contains("1")) {
			bitmask += 64;
		}
		if (inputBits.contains("2")) {
			bitmask += 32;
		}
		if (inputBits.contains("3")) {
			bitmask += 16;
		}
		if (inputBits.contains("4")) {
			bitmask += 8;
		}
		if (inputBits.contains("5")) {
			bitmask += 4;
		}
		if (inputBits.contains("6")) {
			bitmask += 2;
		}
		if (inputBits.contains("7")) {
			bitmask += 1;
		}
		String hexChar = String.format("x", bitmask);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "%BITOR("
				+ factor2.getText().trim()
				+ " : x'"
				+ hexChar
				+ "') "
				+ doEOLComment(comment);
	}

	private void doCABEQ(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) throws RPGFormatException {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " = "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		doGOTO(result, comment);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);

	}

	private void doCABGE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) throws RPGFormatException {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " >= "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		doGOTO(result, comment);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCABGT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) throws RPGFormatException {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " > "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		doGOTO(result, comment);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCABLE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) throws RPGFormatException {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " <= "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		doGOTO(result, comment);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);

	}

	private void doCABLT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) throws RPGFormatException {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " < "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		doGOTO(result, comment);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCABNE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) throws RPGFormatException {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " <> "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		doGOTO(result, comment);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);

	}

	private void doCALL(CommonToken factor2, CommonToken result,
			CommonToken high, CommonToken equal, CommonToken comment) {
		// TODO First find out if a prototype exists for this
		// TODO Create an external program prototype if it does not exist
		// TODO Then convert this a procedure invocation
		// TODO Then put the variables in the argument lists
		// TODO Do the parameter movement as per the PLIST PARMS stuff
		// TODO Move Factor2 value into result prior to call
		// TODO move Result to Factor1 after the call
	}

	private void doCALLB(CommonToken factor2, CommonToken result,
			CommonToken high, CommonToken equal, CommonToken comment) {
		// TODO First find out if a prototype exists for this
		// TODO Create an procedure prototype if it does not exist
		// TODO Then convert this a procedure invocation
		// TODO Then put the variables in the argument lists
		// TODO Do the parameter movement as per the PLIST PARMS stuff
		// TODO Move Factor2 value into result prior to call
		// TODO move Result to Factor1 after the call
	}

	private void doCALLP(CommonToken factor2, CommonToken comment) {
		// TODO get rid of the callp op code and write the Factor2 content

	}

	private void doCASEQ(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " = "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ",
				7 + ((indentLevel + 1) * spacesToIndent))
				+ "EXSR "
				+ result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASGE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " >= "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ",
				7 + ((indentLevel + 1) * spacesToIndent))
				+ "EXSR "
				+ result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASGT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " > "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ",
				7 + ((indentLevel + 1) * spacesToIndent))
				+ "EXSR "
				+ result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASLE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " <= "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ",
				7 + ((indentLevel + 1) * spacesToIndent))
				+ "EXSR "
				+ result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASLT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " < "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ",
				7 + ((indentLevel + 1) * spacesToIndent))
				+ "EXSR "
				+ result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCASNE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		boolean HI = high.getType() != RpgLexer.BlankIndicator;
		boolean LO = low.getType() != RpgLexer.BlankIndicator;
		boolean EQ = equal.getType() != RpgLexer.BlankIndicator;
		if (HI) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText() + ";");
		}
		if (LO) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText() + ";");
		}
		if (EQ) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " = "
					+ factor2.getText() + ";");
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText().trim()
				+ " <> "
				+ factor2.getText().trim()
				+ ";";
		cspecs.add(workString);
		workString = StringUtils.repeat(" ",
				7 + ((indentLevel + 1) * spacesToIndent))
				+ "EXSR "
				+ result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	private void doCAT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken comment) {
		String factor2s = factor2.getText().trim();
		boolean F1F = factor1.getText().trim().length() > 0;
		int spacesToPad = 0;
		if (factor2s.contains(":")) {
			String[] parts = factor2s.split(":");
			if (parts.length == 2) {
				spacesToPad = Integer.parseInt(parts[1]);
				factor2s = parts[0];
			}
		}

		if (F1F) {
			workString = StringUtils.repeat(" ",
					7 + ((indentLevel + 1) * spacesToIndent))
					+ result.getText().trim()
					+ " = "
					+ factor1.getText().trim() + " + ";
			if (spacesToPad > 0) {
				workString += "\"" + StringUtils.repeat(' ', spacesToPad)
						+ "\"";
			}
			workString += factor2.getText().trim() + doEOLComment(comment);
		} else {
			workString = StringUtils.repeat(" ",
					7 + ((indentLevel + 1) * spacesToIndent))
					+ result.getText().trim()
					+ " += "
					+ factor2.getText().trim();
			if (spacesToPad > 0) {
				workString += "+ \"" + StringUtils.repeat(' ', spacesToPad)
						+ "\"";
			}
			workString += doEOLComment(comment);
		}

	}

	private void doCHAIN(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken comment) {
		boolean NR = (high.getType() != RpgLexer.BlankIndicator);
		boolean ER = (low.getType() != RpgLexer.BlankIndicator);
		String opCode = "CHAIN";
		if (NR && ER) {
			opCode += "(NE)";
		} else if (NR) {
			opCode += "(N)";
		} else if (ER) {
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ " "
				+ factor1.getText().trim()
				+ " "
				+ factor2.getText().trim()
				+ " " + result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		if (NR) {
			setResultingIndicator(high, "IF %FOUND = *OFF;");
		}
		if (ER) {
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}

	}

	private void doCHECK(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken equal,
			CommonToken comment) {
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		boolean FD = equal.getType() != RpgLexer.BlankIndicator;
		String comparitor = factor1.getText().trim();
		int start = 0;
		String variable = "";
		if (factor2.getText().contains(":")) {
			String[] temp = factor2.getText().split(":");
			if (temp.length == 2) {
				variable = temp[0];
				start = Integer.parseInt(temp[1]);
			} else {
				start = 1;
				variable = temp[0];
			}
		} else {
			start = 1;
			variable = factor2.getText().trim();
		}
		if (start != 1) {
			workString = StringUtils.repeat(' ',
					7 + (indentLevel * spacesToIndent))
					+ result.getText().trim()
					+ "= %CHECK("
					+ comparitor
					+ ':'
					+ variable + ':' + start + ")" + doEOLComment(comment);
		} else {
			workString = StringUtils.repeat(' ',
					7 + (indentLevel * spacesToIndent))
					+ result.getText().trim()
					+ "= %CHECK("
					+ comparitor
					+ ':'
					+ variable + ")" + doEOLComment(comment);
		}
		cspecs.add(workString);
		if (FD){
			setResultingIndicator(equal, "IF %FOUND = *ON;");
		}
		if (ER){
			setResultingIndicator(equal, "IF %ERROR = *ON;");
		}

	}

	private void doCHECKR(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken equal,
			CommonToken comment) {
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		boolean FD = equal.getType() != RpgLexer.BlankIndicator;
		String comparitor = factor1.getText().trim();
		int start = 0;
		String variable = "";
		if (factor2.getText().contains(":")) {
			String[] temp = factor2.getText().split(":");
			if (temp.length == 2) {
				variable = temp[0];
				start = Integer.parseInt(temp[1]);
			} else {
				start = 1;
				variable = temp[0];
			}
		} else {
			start = 1;
			variable = factor2.getText().trim();
		}
		if (start != 1) {
			workString = StringUtils.repeat(' ',
					7 + (indentLevel * spacesToIndent))
					+ result.getText().trim()
					+ "= %CHECKR("
					+ comparitor
					+ ':'
					+ variable + ':' + start + ")" + doEOLComment(comment);
		} else {
			workString = StringUtils.repeat(' ',
					7 + (indentLevel * spacesToIndent))
					+ result.getText().trim()
					+ "= %CHECKR("
					+ comparitor
					+ ':'
					+ variable + ")" + doEOLComment(comment);
		}
		cspecs.add(workString);
		if (FD){
			setResultingIndicator(equal, "IF %FOUND = *ON;");
		}
		if (ER){
			setResultingIndicator(equal, "IF %ERROR = *ON;");
		}
	}

	private void doCLEAR(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "CLEAR ";
		if (factor1.getType() != RpgLexer.CS_BlankFactor) {
			workString += "*NOKEY ";
		}
		if (!factor2.getText().trim().isEmpty()) {
			workString += "*ALL ";
		}
		workString += result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doCLOSE(CommonToken factor2, CommonToken low,
			CommonToken comment) {
		String opCode = "CLOSE";
		boolean ER = (low.getType() != RpgLexer.BlankIndicator);
		if (ER) {
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ " "
				+ factor2.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		if (ER) {
			setResultingIndicator(low, "IF %ERRROR = *ON;");
		}

	}

	private void doCOMMIT(CommonToken factor1, CommonToken low,
			CommonToken comment) {
		String opCode = "COMMIT";
		boolean ER = (low.getType() != RpgLexer.BlankIndicator);
		if (ER) {
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ " "
				+ factor1.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);

	}

	private void doCOMP(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		if (high.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(high, "IF " + factor1.getText().trim()
					+ " > " + factor2.getText().trim() + ";");
		}
		if (low.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(low, "IF " + factor1.getText().trim() + " < "
					+ factor2.getText().trim() + ";");
		}
		if (equal.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(equal, "IF " + factor1.getText().trim()
					+ " = " + factor2.getText().trim() + ";");
		}

	}

	private void doDEALLOC(CommonToken result, CommonToken low,
			CommonToken comment) {
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		String opCode = "DEALLOC";
		if (ER) {
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ result.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
		if (ER) {
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}
	}

	private void doDEFINE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken comment) throws RPGFormatException {
		if (factor1.getType() == RpgLexer.SPLAT_LIKE) {
			workString = RPGSpecs.formatDSpec(' ' + result.getText(), " ", " ",
					"S", " ", " ", " ", " ", "LIKE(" + factor2.getText().trim()
							+ ")", "From a define statement");
			dspecs.add(workString);
		} else if (factor1.getType() == RpgLexer.SPLAT_DTAARA) {
			workString = RPGSpecs.formatDSpec(' ' + result.getText(), " ", " ",
					"DS", " ", " ", " ", " ", "DTAARA("
							+ factor2.getText().trim() + ")",
					"From a define statement");
			dspecs.add(workString);
		}
	}

	private void doDELETE(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken comment) {
		boolean NR = high.getType() != RpgLexer.BlankIndicator;
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		String opCode = "DELETE";
		if (NR && ER) {
			opCode += "(NE)";
		} else if (NR) {
			opCode += "(N)";
		} else if (ER) {
			opCode += ("E");
		}
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ " "
				+ factor1.getText().trim()
				+ " "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
		if (ER) {
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}

		if (NR) {
			setResultingIndicator(high, "IF %FOUND = *OFF");
		}
	}

	private void doDIV(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		boolean F1F = factor1.getType() != RpgLexer.CS_BlankFactor;
		boolean POS = high.getType() != RpgLexer.BlankIndicator;
		boolean NEG = low.getType() != RpgLexer.BlankIndicator;
		boolean ZERO = equal.getType() != RpgLexer.BlankIndicator;
		if (F1F) {
			workString = StringUtils.repeat(' ',
					7 + (indentLevel * spacesToIndent))
					+ result.getText().trim()
					+ " = "
					+ factor1.getText().trim()
					+ " / "
					+ factor2.getText().trim() + doEOLComment(comment);
		} else {
			workString = StringUtils.repeat(' ',
					7 + (indentLevel * spacesToIndent))
					+ result.getText().trim()
					+ " = "
					+ result.getText().trim()
					+ " / " + factor2.getText().trim() + doEOLComment(comment);
		}
		cspecs.add(workString);
		if (POS) {
			setResultingIndicator(high, "IF " + result.getText().trim()
					+ " > 0;");
		}
		if (NEG) {
			setResultingIndicator(low, "IF " + result.getText().trim()
					+ " < 0;");
		}
		if (ZERO) {
			setResultingIndicator(equal, "IF " + result.getText().trim()
					+ " = 0;");
		}
	}

	private void doDO(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken comment) {
		String factor1s;
		if (factor1.getText().trim().length() == 0) {
			factor1s = "1";
		} else {
			factor1s = factor1.getText().trim();
		}
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "FOR "
				+ result.getText().trim()
				+ " = "
				+ factor1s
				+ " TO "
				+ factor2.getText().trim() + doEOLComment(comment);
		structuredOps.push("FOR");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOU(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOU "
				+ factor2.getText().trim() + doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOUEQ(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOU "
				+ factor1.getText().trim()
				+ " = "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOUGE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOU "
				+ factor1.getText().trim()
				+ " >= "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOUGT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOU "
				+ factor1.getText().trim()
				+ " > "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOULE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOU "
				+ factor1.getText().trim()
				+ " <= "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOULT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOU "
				+ factor1.getText().trim()
				+ " < "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOUNE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOU "
				+ factor1.getText().trim()
				+ " <> "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOW(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOW "
				+ factor2.getText().trim() + doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWEQ(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOW "
				+ factor1.getText().trim()
				+ " = "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWGE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOW "
				+ factor1.getText().trim()
				+ " >= "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWGT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOW "
				+ factor1.getText().trim()
				+ " > "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWLE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOW "
				+ factor1.getText().trim()
				+ " <= "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWLT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOW "
				+ factor1.getText().trim()
				+ " < "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDOWNE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DOW "
				+ factor1.getText().trim()
				+ " <> "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		structuredOps.push("DO");
		setIndentLevel(++indentLevel);
		cspecs.add(workString);
	}

	private void doDSPLY(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken comment) {
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		String opCode = "DSPLY";
		if (ER) {
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ " "
				+ factor1.getText().trim()
				+ " "
				+ factor2.getText().trim()
				+ result.getText().trim() + ";";
		cspecs.add(workString);
		if (ER) {
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}
	}

	private void doDUMP(CommonToken factor1, CommonToken comment) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent))
				+ "DUMP "
				+ factor1.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doELSE(CommonToken comment) {
		workString = StringUtils.repeat(" ",
				7 + ((indentLevel - 1) * spacesToIndent)) + "ELSE;";
		cspecs.add(workString);
	}

	private void doELSEIF(CommonToken factor2, CommonToken comment) {
		workString = StringUtils.repeat(' ',
				7 + ((indentLevel - 1) * spacesToIndent))
				+ "ELSEIF "
				+ factor2.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doEND(CommonToken factor2, CommonToken comment) {
		String theOp = structuredOps.peek();
		if (theOp.equalsIgnoreCase("DO")) {
			doENDDO(factor2, comment);
		} else if (theOp.equalsIgnoreCase("FOR")) {
			doENDFOR(comment);
		} else if (theOp.equalsIgnoreCase("IF")) {
			doENDIF(comment);
		} else if (theOp.equalsIgnoreCase("MONITOR")) {
			doENDMON(comment);
		} else if (theOp.equalsIgnoreCase("SELECT")) {
			doENDSL(comment);
		}
	}

	private void doENDCS() {
		// Safely ignoring this as the CASxx methods terminate the individual
		// CAS groups

	}

	private void doENDDO(CommonToken factor2, CommonToken comment) {
		setIndentLevel(--indentLevel);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDDO;";
		cspecs.add(workString);
		structuredOps.pop();

	}

	private void doENDFOR(CommonToken comment) {
		setIndentLevel(--indentLevel);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDFOR;";
		cspecs.add(workString);
		structuredOps.pop();
	}

	private void doENDIF(CommonToken comment) {
		setIndentLevel(--indentLevel);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
		structuredOps.pop();
	}

	private void doENDMON(CommonToken comment) {
		setIndentLevel(--indentLevel);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDMON;";
		cspecs.add(workString);
		structuredOps.pop();
	}

	private void doENDSL(CommonToken comment) {
		setIndentLevel(--indentLevel);
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ENDSL;";
		cspecs.add(workString);
		structuredOps.pop();

	}

	private void doENDSR(CommonToken factor1, CommonToken factor2,
			CommonToken comment) throws RPGFormatException {
		// If there is a label then emit a tag
		if (factor1.getType() != RpgLexer.CS_BlankFactor
				&& !factor1.getText().trim().isEmpty()) {
			doTAG(factor1, comment);
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "ENDSR "
				+ factor2.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doEVAL(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ factor2.getText() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doEVAL_CORR(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "EVAL-CORR "
				+ factor2.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private String doEOLComment(CommonToken comment) {
		String result = "";
		if (comment != null && !comment.getText().trim().isEmpty()) {
			result = "; \\\\" + comment.getText().trim();
		} else {
			result = ";";
		}

		return result;
	}

	private void doEVALR(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "EVALR "
				+ factor2.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doEXCEPT(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "EXCEPT "
				+ factor2.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doEXFMT(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decPos, CommonToken low,
			CommonToken comment) {
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		String opCode = "EXFMT";
		if (ER) {
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ factor2.getText().trim()
				+ " "
				+ result.getText().trim()
				+ doEOLComment(comment);
		if (ER) {
			setResultingIndicator(low, "IF %ERROR = *ON;");
		}
	}

	private void doEXSR(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "EXSR "
				+ factor2.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doEXTRCT(CommonToken factor2, CommonToken result, CommonToken low, CommonToken comment) {
		String results = result.getText().trim();
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) 
				+ results + " = %SUBDT("+ factor2.getText().trim() + ")" + doEOLComment(comment);
		cspecs.add(workString);
		setResultingIndicator(low, "IF %ERROR = *ON");
	}

	private void doFEOD(CommonToken factor2, CommonToken low,
			CommonToken comment) {
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		String opCode = "FEOD";
		if (ER) {
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ factor2.getText().trim() + ";";
		cspecs.add(workString);

	}

	private void doFOR(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "FOR "
				+ factor2.getText().trim() + ";";
		cspecs.add(workString);
		structuredOps.push("FOR");
		setIndentLevel(++indentLevel);
	}

	private void doFORCE(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "FORCE "
				+ factor2.getText().trim() + ";";
		cspecs.add(workString);

	}

	private void doGOTO(CommonToken factor2, CommonToken comment)
			throws RPGFormatException {
		cspecs.add("       /END-FREE");
		cspecs.add(RPGSpecs.formatCSpec(" ", " ", " ", " ", "GOTO", " ",
				factor2.getText().trim(), " ", " ", " ", " ", " ",
				"From a GOTO or CABxx statement"));
		cspecs.add("       /FREE");

	}

	private void doIF(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor2.getText() + doEOLComment(comment);
		cspecs.add(workString);
		structuredOps.push("IF");
		setIndentLevel(++indentLevel);
	}

	private void doIFEQ(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText()
				+ " = "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
		structuredOps.push("IF");
		setIndentLevel(++indentLevel);
	}

	private void doIFGE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText()
				+ " >= "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
		structuredOps.push("IF");
		setIndentLevel(++indentLevel);
	}

	private void doIFGT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText()
				+ " > "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
		structuredOps.push("IF");
		setIndentLevel(++indentLevel);
	}

	private void doIFLE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText()
				+ " <= "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
		structuredOps.push("IF");
		setIndentLevel(++indentLevel);
	}

	private void doIFLT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText()
				+ " < "
				+ factor2.getText()
				+ doEOLComment(comment);
		structuredOps.push("IF");
		setIndentLevel(++indentLevel);
	}

	private void doIFNE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "IF "
				+ factor1.getText()
				+ " <> "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
		structuredOps.push("IF");
		setIndentLevel(++indentLevel);
	}

	private void doIN(CommonToken factor1, CommonToken factor2,
			CommonToken low, CommonToken comment) {
		boolean ER = low.getType() != RpgLexer.BlankIndicator;
		String opCode = "IN";
		if (ER) {
			opCode += "(E)";
		}
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ opCode
				+ factor1.getText().trim()
				+ " "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doITER(CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "ITER;";
		cspecs.add(workString);
	}

	private void doKFLD(CommonToken result, CommonToken comment)
			throws RPGFormatException {
		workString = RPGSpecs.formatDSpec(' ' + result.getText().trim(), " ",
				" ", " ", " ", " ", " ", " ", " ", "From a KLIST KLFD");
		dspecs.add(workString);
	}

	private void doKLIST(CommonToken factor1, CommonToken comment)
			throws RPGFormatException {
		workString = RPGSpecs.formatDSpec(' ' + factor1.getText().trim(), " ",
				" ", "DS", " ", " ", " ", " ", " ", "From a KLIST");
		dspecs.add("");
		dspecs.add(workString);
	}

	private void doLEAVE(CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "LEAVE;";
		cspecs.add(workString);
	}

	private void doLEAVESR(CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "LEAVESR;";
		cspecs.add(workString);
	}

	private void doLOOKUP(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMHHZO(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decPos, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMHLZO(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMLHZO(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMLLZO(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMONITOR(CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent)) + "MONITOR;";
		cspecs.add(workString);
		structuredOps.push("MONITOR");
		setIndentLevel(++indentLevel);
	}

	private void doMOVE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMOVEA(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMOVEL(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMULT(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doMVR(CommonToken result, CommonToken length,
			CommonToken decpos, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doNEXT(CommonToken factor1, CommonToken factor2,
			CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doOCCUR(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doON_ERROR(CommonToken factor2, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doOPEN(CommonToken factor2, CommonToken low,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doOREQ(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText()
				+ " = "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doORGE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText()
				+ " >= "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doORGT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText()
				+ " > "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doORLE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText()
				+ " <= "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doORLT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText()
				+ " < "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doORNE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "OR "
				+ factor1.getText()
				+ " <> "
				+ factor2.getText()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doOTHER(CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doOUT(CommonToken factor1, CommonToken factor2,
			CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doPARM(CommonToken result, CommonToken comment)
			throws RPGFormatException {
		workString = RPGSpecs.formatDSpec(' ' + result.getText().trim(), " ",
				" ", " ", " ", " ", " ", " ", " ", "From PLIST PARM");
		dspecs.add(workString);

	}

	private void doPLIST(CommonToken factor1, CommonToken comment) {
		try {
			workString = RPGSpecs.formatDSpec(' ' + factor1.getText().trim(),
					" ", " ", "PI", " ", " ", " ", " ", " ", "From PLIST");
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
		dspecs.add("");
		dspecs.add(workString);

	}

	private void doPOST(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doREAD(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doREADC(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doREADE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken equal,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doREADP(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doREADPE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken equal,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doREALLOC(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doREL(CommonToken factor1, CommonToken factor2,
			CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doRESET(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doResultCheck(CommonToken result, CommonToken length,
			CommonToken decpos) throws RPGFormatException {
		boolean lengthFound = !length.getText().trim().isEmpty();
		String lengths = length.getText().trim();
		boolean decimalsFound = !decpos.getText().trim().isEmpty();
		String decposs = decpos.getText().trim();

		if (lengthFound) {
			if (decimalsFound) {
				workString = RPGSpecs.formatDSpec(
						' ' + result.getText().trim(), " ", " ", "S", " ",
						lengths, " ", decposs, " ",
						"From conversion of result field");
				dspecs.add(workString);
			} else {
				workString = RPGSpecs.formatDSpec(
						' ' + result.getText().trim(), " ", " ", "S", " ",
						lengths, " ", " ", " ",
						"From conversion of result field");
				dspecs.add(workString);
			}
		}
	}

	private void doRETURN(CommonToken factor2, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doROLBK(CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doSCAN(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken low, CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doSELECT(CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "SELECT"
				+ doEOLComment(comment);
		cspecs.add(workString);
		structuredOps.push("SELECT");
		setIndentLevel(++indentLevel);
	}

	private void doSETGT(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doSETLL(CommonToken factor1, CommonToken factor2,
			CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doSETOFF(CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		if (high.getType() != RpgLexer.BlankIndicator) {
			workString = StringUtils.repeat(" ",
					7 + (indentLevel * spacesToIndent))
					+ "*IN"
					+ high.getText().trim() + " = *OFF" + doEOLComment(comment);
			cspecs.add(workString);
		}
		if (low.getType() != RpgLexer.BlankIndicator) {
			workString = StringUtils.repeat(" ",
					7 + (indentLevel * spacesToIndent))
					+ "*IN"
					+ low.getText().trim() + " = *OFF" + doEOLComment(comment);
			cspecs.add(workString);
		}
		if (equal.getType() != RpgLexer.BlankIndicator) {
			workString = StringUtils.repeat(" ",
					7 + (indentLevel * spacesToIndent))
					+ "*IN"
					+ equal.getText().trim()
					+ " = *OFF"
					+ doEOLComment(comment);
			cspecs.add(workString);
		}
	}

	private void doSETON(CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		if (high.getType() != RpgLexer.BlankIndicator) {
			workString = StringUtils.repeat(" ",
					7 + (indentLevel * spacesToIndent))
					+ "*IN"
					+ high.getText().trim() + " = *ON" + doEOLComment(comment);
			cspecs.add(workString);
		}
		if (low.getType() != RpgLexer.BlankIndicator) {
			workString = StringUtils.repeat(" ",
					7 + (indentLevel * spacesToIndent))
					+ "*IN"
					+ low.getText().trim() + " = *ON" + doEOLComment(comment);
			cspecs.add(workString);
		}
		if (equal.getType() != RpgLexer.BlankIndicator) {
			workString = StringUtils.repeat(" ",
					7 + (indentLevel * spacesToIndent))
					+ "*IN"
					+ equal.getText().trim() + " = *ON" + doEOLComment(comment);
			cspecs.add(workString);
		}
	}

	private void doSHTDN(CommonToken high, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doSORTA(CommonToken factor2, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doSQRT(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doSUB(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doSUBDUR(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken low, CommonToken comment) {
		String fullFactor2 = factor2.getText();
		String factor1s = factor1.getText().trim();
		String[] factor2Parts = fullFactor2.split(":");
		boolean ER = low.getText().trim().length() > 0;
		String duration;
		String durCode;
		String bif = null;
		if (factor2Parts.length == 2) {
			duration = factor2Parts[0];
			durCode = factor2Parts[1];
			if (durCode.equalsIgnoreCase("*D")
					|| durCode.equalsIgnoreCase("*DAYS")) {
				bif = "%DAYS";
			} else if (durCode.equalsIgnoreCase("*M")
					|| durCode.equalsIgnoreCase("*MONTHS")) {
				bif = "%MONTHS";
			} else if (durCode.equalsIgnoreCase("*Y")
					|| durCode.equalsIgnoreCase("*YEARS")) {
				bif = "%YEARS";
			} else if (durCode.equalsIgnoreCase("*H")
					|| durCode.equalsIgnoreCase("*HOURS")) {
				bif = "%HOURS";
			} else if (durCode.equalsIgnoreCase("*MN")
					|| durCode.equalsIgnoreCase("*MINUTES")) {
				bif = "%MINUTES";
			} else if (durCode.equalsIgnoreCase("*S")
					|| durCode.equalsIgnoreCase("*SECONDS")) {
				bif = "%SECONDS";
			} else if (durCode.equalsIgnoreCase("*MS")
					|| durCode.equalsIgnoreCase("*MSECONDS")) {
				bif = "%MSECONDS";
			}

			if (bif != null) {
				// Use a monitor group if an error indicator was used
				if (ER) {
					workString = StringUtils.repeat(' ',
							7 + (indentLevel * spacesToIndent)) + "MONITOR;";
					cspecs.add(workString);
					workString = StringUtils.repeat(' ',
							7 + ((indentLevel + 1) * spacesToIndent))
							+ "*IN"
							+ low.getText().trim() + " = *OFF;";
					cspecs.add(workString);
				}
				if (factor1s.length() == 0) {
					workString = StringUtils.repeat(' ',
							7 + ((indentLevel + 1) * spacesToIndent))
							+ result.getText().trim()
							+ " += "
							+ bif
							+ "("
							+ duration + ")" + doEOLComment(comment);
					cspecs.add(workString);
				} else {
					workString = StringUtils.repeat(' ',
							7 + ((indentLevel + 1) * spacesToIndent))
							+ result.getText().trim()
							+ " = "
							+ factor1.getText().trim()
							+ " + "
							+ bif
							+ "("
							+ duration + ")" + doEOLComment(comment);
				}

				if (ER) {
					workString = StringUtils.repeat(' ',
							7 + (indentLevel * spacesToIndent)) + "ON-ERROR;";
					cspecs.add(workString);
					workString = StringUtils.repeat(' ',
							7 + ((indentLevel + 1) * spacesToIndent))
							+ "*IN"
							+ low.getText().trim() + " = *ON;";
					cspecs.add(workString);
					workString = StringUtils.repeat(' ',
							7 + (indentLevel * spacesToIndent)) + "ENDMON;";
					cspecs.add(workString);
				}

			}
		}

	}

	private void doSUBST(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doTAG(CommonToken factor1, CommonToken comment)
			throws RPGFormatException {
		cspecs.add("       /END-FREE");
		String eol = "";
		if (comment != null &&  !comment.getText().trim().isEmpty()) {
			eol = comment.getText().trim();
		} else {
			eol = "From a GOTO or CABxx statement";
		}
		cspecs.add(RPGSpecs.formatCSpec(" ", " ", " ",
				factor1.getText().trim(), "TAG", " ", "", " ", " ", " ", " ",
				" ", eol));
		cspecs.add("       /FREE");
	}

	private void doTEST(CommonToken factor1, CommonToken result,
			CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doTESTB(CommonToken factor2, CommonToken result,
			CommonToken high, CommonToken low, CommonToken equal,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doTESTN(CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doTESTZ(CommonToken result, CommonToken high, CommonToken low,
			CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doTIME(CommonToken result, CommonToken length,
			CommonToken decpos, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doUNLOCK(CommonToken factor2, CommonToken low,
			CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doUPDATE(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doWHEN(CommonToken factor2, CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "WHEN "
				+ factor2.getText().trim() + doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doWHENEQ(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "WHEN "
				+ factor1.getText().trim()
				+ " = "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doWHENGE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "WHEN "
				+ factor1.getText().trim()
				+ " >= "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doWHENGT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "WHEN "
				+ factor1.getText().trim()
				+ " > "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doWHENLE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "WHEN "
				+ factor1.getText().trim()
				+ " <= "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doWHENLT(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "WHEN "
				+ factor1.getText().trim()
				+ " < "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doWHENNE(CommonToken factor1, CommonToken factor2,
			CommonToken comment) {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ "WHEN "
				+ factor1.getText().trim()
				+ " <> "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
	}

	private void doWRITE(CommonToken factor2, CommonToken result,
			CommonToken low, CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doXFOOT(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doXLATE(CommonToken factor1, CommonToken factor2,
			CommonToken result, CommonToken length, CommonToken decpos,
			CommonToken low, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doXML_INTO(CommonToken factor2, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doXML_SAX(CommonToken factor2, CommonToken comment) {
		// TODO Auto-generated method stub

	}

	private void doZ_ADD(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal, CommonToken comment)
			throws RPGFormatException {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ result.getText().trim()
				+ " = "
				+ factor2.getText().trim()
				+ doEOLComment(comment);
		cspecs.add(workString);
		doResultCheck(result, length, decpos);
		if (high.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(high, "IF " + result.getText().trim()
					+ " >  0;");
		}
		if (low.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(low, "IF " + result.getText().trim()
					+ " <  0;");
		}
		if (equal.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(equal, "IF " + result.getText().trim()
					+ " =  0;");
		}
	}

	private void doZ_SUB(CommonToken factor2, CommonToken result,
			CommonToken length, CommonToken decpos, CommonToken high,
			CommonToken low, CommonToken equal, CommonToken comment)
			throws RPGFormatException {
		workString = StringUtils
				.repeat(" ", 7 + (indentLevel * spacesToIndent))
				+ result.getText().trim()
				+ " = "
				+ factor2.getText().trim()
				+ " * -1" + doEOLComment(comment);
		cspecs.add(workString);
		doResultCheck(result, length, decpos);
		if (high.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(high, "IF " + result.getText().trim()
					+ " >  0;");
		}
		if (low.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(low, "IF " + result.getText().trim()
					+ " <  0;");
		}
		if (equal.getType() != RpgLexer.BlankIndicator) {
			setResultingIndicator(equal, "IF " + result.getText().trim()
					+ " =  0;");
		}
	}

	@Override
	public void exitCsACQ(CsACQContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsACQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doACQ(factor1, factor2, comment);
	}

	@Override
	public void exitCsADD(CsADDContext ctx) {
		// TODO Auto-generated method stub
		super.exitCsADD(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken comment = temp.get(COMMENT);
		try {
			doADD(factor1, factor2, result, length, decpos, comment);
		} catch (RPGFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsADDDUR(CsADDDURContext ctx) {
		super.exitCsADDDUR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doADDDUR(factor1, factor2, result, low, comment);
	}

	@Override
	public void exitCsALLOC(CsALLOCContext ctx) {
		super.exitCsALLOC(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken comment = temp.get(COMMENT);
		doALLOC(factor2, result, comment);
	}

	@Override
	public void exitCsANDEQ(CsANDEQContext ctx) {
		super.exitCsANDEQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doANDEQ(factor1, factor2, comment);
	}

	@Override
	public void exitCsANDGE(CsANDGEContext ctx) {
		super.exitCsANDGE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doANDGE(factor1, factor2, comment);
	}

	@Override
	public void exitCsANDGT(CsANDGTContext ctx) {
		super.exitCsANDGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doANDGT(factor1, factor2, comment);
	}

	@Override
	public void exitCsANDLE(CsANDLEContext ctx) {
		super.exitCsANDLE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doANDLE(factor1, factor2, comment);
	}

	@Override
	public void exitCsANDLT(CsANDLTContext ctx) {
		super.exitCsANDLT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doANDLT(factor1, factor2, comment);
	}

	@Override
	public void exitCsANDNE(CsANDNEContext ctx) {
		super.exitCsANDNE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doANDNE(factor1, factor2, comment);
	}

	@Override
	public void exitCsBEGSR(CsBEGSRContext ctx) {
		super.exitCsBEGSR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken comment = temp.get(COMMENT);
		doBEGSR(factor1, comment);
	}

	@Override
	public void exitCsBITOFF(CsBITOFFContext ctx) {
		super.exitCsBITOFF(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken comment = temp.get(COMMENT);
		doBITOFF(factor2, result, comment);
	}

	@Override
	public void exitCsBITON(CsBITONContext ctx) {
		super.exitCsBITON(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken comment = temp.get(COMMENT);
		doBITON(factor2, result, comment);
	}

	@Override
	public void exitCsCABEQ(CsCABEQContext ctx) {
		super.exitCsCABEQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		try {
			doCABEQ(factor1, factor2, result, high, low, equal, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABGE(CsCABGEContext ctx) {
		super.exitCsCABGE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		try {
			doCABGE(factor1, factor2, result, high, low, equal, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABGT(CsCABGTContext ctx) {
		super.exitCsCABGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		try {
			doCABGT(factor1, factor2, result, high, low, equal, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABLE(CsCABLEContext ctx) {
		super.exitCsCABLE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		try {
			doCABLE(factor1, factor2, result, high, low, equal, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABLT(CsCABLTContext ctx) {
		super.exitCsCABLT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		try {
			doCABLT(factor1, factor2, result, high, low, equal, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCABNE(CsCABNEContext ctx) {
		super.exitCsCABNE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		try {
			doCABNE(factor1, factor2, result, high, low, equal, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsCALL(CsCALLContext ctx) {
		super.exitCsCALL(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCALL(factor2, result, high, equal, comment);
	}

	@Override
	public void exitCsCALLB(CsCALLBContext ctx) {
		super.exitCsCALLB(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCALLB(factor2, result, high, equal, comment);
	}

	@Override
	public void exitCsCALLP(CsCALLPContext ctx) {
		super.exitCsCALLP(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doCALLP(factor2, comment);
	}

	@Override
	public void exitCsCASEQ(CsCASEQContext ctx) {
		super.exitCsCASEQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCASEQ(factor1, factor2, result, high, low, equal, comment);
	}

	@Override
	public void exitCsCASGE(CsCASGEContext ctx) {
		super.exitCsCASGE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCASGE(factor1, factor2, result, high, low, equal, comment);
	}

	@Override
	public void exitCsCASGT(CsCASGTContext ctx) {
		super.exitCsCASGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCASGT(factor1, factor2, result, high, low, equal, comment);
	}

	@Override
	public void exitCsCASLE(CsCASLEContext ctx) {
		super.exitCsCASLE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCASLE(factor1, factor2, result, high, low, equal, comment);
	}

	@Override
	public void exitCsCASLT(CsCASLTContext ctx) {
		super.exitCsCASLT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCASLT(factor1, factor2, result, high, low, equal, comment);
	}

	@Override
	public void exitCsCASNE(CsCASNEContext ctx) {
		super.exitCsCASNE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCASNE(factor1, factor2, result, high, low, equal, comment);
	}

	private ParserRuleContext getCSpec(ParserRuleContext ctx) {
		System.err.println("*!*!*! " + ctx.getClass().getName() /* + " - " + ctx.getText() */);
		ParserRuleContext result = ctx.getParent();
		Class<?> temp = result.getClass();
		if (!(temp == RpgParser.Cspec_fixedContext.class || temp == RpgParser.StatementContext.class ||temp == RpgParser.SubroutineContext.class)) {
			// recursively call ourselves
			result = getCSpec(result);
		}
		return result;
	}
	
	private ParserRuleContext getParentSpec(ParserRuleContext ctx,
			Class<? extends ParserRuleContext> stopClass) {
		System.err.println("*!*!*! " + ctx.getClass().getName() /* + " - " + ctx.getText() */);
		ParserRuleContext result = ctx.getParent();
		Class<?> temp = result.getClass();
		if (!(temp == stopClass )) {
			// recursively call ourselves
			result = getParentSpec(result, stopClass);
		}
		return result;
	}


	@Override
	public void exitCsCAT(CsCATContext ctx) {
		super.exitCsCAT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken comment = temp.get(COMMENT);
		doCAT(factor1, factor2, result, comment);
	}

	@Override
	public void exitCsCHAIN(CsCHAINContext ctx) {
		super.exitCsCHAIN(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doCHAIN(factor1, factor2, result, high, low, comment);
	}

	@Override
	public void exitCsCHECK(CsCHECKContext ctx) {
		super.exitCsCHECK(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCHECK(factor1, factor2, result, low, equal, comment);
	}

	@Override
	public void exitCsCHECKR(CsCHECKRContext ctx) {
		super.exitCsCHECKR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCHECKR(factor1, factor2, result, low, equal, comment);
	}

	@Override
	public void exitCsCLEAR(CsCLEARContext ctx) {
		super.exitCsCLEAR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken comment = temp.get(COMMENT);
		doCLEAR(factor1, factor2, result, comment);
	}

	@Override
	public void exitCsCLOSE(CsCLOSEContext ctx) {
		super.exitCsCLOSE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doCLOSE(factor2, low, comment);
	}

	@Override
	public void exitCsCOMMIT(CsCOMMITContext ctx) {
		super.exitCsCOMMIT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doCOMMIT(factor1, low, comment);
	}

	@Override
	public void exitCsCOMP(CsCOMPContext ctx) {
		super.exitCsCOMP(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doCOMP(factor1, factor2, high, low, equal, comment);
	}

	@Override
	public void exitCsDEALLOC(CsDEALLOCContext ctx) {
		super.exitCsDEALLOC(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doDEALLOC(result, low, comment);
	}

	@Override
	public void exitCsDEFINE(CsDEFINEContext ctx) {
		super.exitCsDEFINE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken comment = temp.get(COMMENT);
		try {
			doDEFINE(factor1, factor2, result, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsDELETE(CsDELETEContext ctx) {
		super.exitCsDELETE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doDELETE(factor1, factor2, high, low, comment);
	}

	@Override
	public void exitCsDIV(CsDIVContext ctx) {
		super.exitCsDIV(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doDIV(factor1, factor2, result, high, low, equal, comment);
	}

	@Override
	public void exitCsDO(CsDOContext ctx) {
		super.exitCsDO(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken comment = temp.get(COMMENT);
		doDO(factor1, factor2, result, comment);
	}

	@Override
	public void exitCsDOU(CsDOUContext ctx) {
		super.exitCsDOU(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOU(factor2, comment);
	}

	@Override
	public void exitCsDOUEQ(CsDOUEQContext ctx) {
		super.exitCsDOUEQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOUEQ(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOUGE(CsDOUGEContext ctx) {
		super.exitCsDOUGE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOUGE(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOUGT(CsDOUGTContext ctx) {
		super.exitCsDOUGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOUGT(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOULE(CsDOULEContext ctx) {
		super.exitCsDOULE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOULE(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOULT(CsDOULTContext ctx) {
		super.exitCsDOULT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOULT(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOUNE(CsDOUNEContext ctx) {
		super.exitCsDOUNE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOUNE(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOW(CsDOWContext ctx) {
		super.exitCsDOW(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOW(factor2, comment);
	}

	@Override
	public void exitCsDOWEQ(CsDOWEQContext ctx) {
		super.exitCsDOWEQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOWEQ(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOWGE(CsDOWGEContext ctx) {
		super.exitCsDOWGE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOWGE(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOWGT(CsDOWGTContext ctx) {
		super.exitCsDOWGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOWGT(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOWLE(CsDOWLEContext ctx) {
		super.exitCsDOWLE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOWLE(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOWLT(CsDOWLTContext ctx) {
		super.exitCsDOWLT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOWLT(factor1, factor2, comment);
	}

	@Override
	public void exitCsDOWNE(CsDOWNEContext ctx) {
		super.exitCsDOWNE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doDOWNE(factor1, factor2, comment);
	}

	@Override
	public void exitCsDSPLY(CsDSPLYContext ctx) {
		super.exitCsDSPLY(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doDSPLY(factor1, factor2, result, low, comment);
	}

	@Override
	public void exitCsDUMP(CsDUMPContext ctx) {
		super.exitCsDUMP(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken comment = temp.get(COMMENT);
		doDUMP(factor1, comment);
	}

	@Override
	public void exitCsELSE(CsELSEContext ctx) {
		super.exitCsELSE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doELSE(comment);
	}

	@Override
	public void exitCsELSEIF(CsELSEIFContext ctx) {
		super.exitCsELSEIF(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doELSEIF(factor2, comment);
	}

	@Override
	public void exitCsEND(CsENDContext ctx) {
		super.exitCsEND(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doEND(factor2, comment);
	}

	@Override
	public void exitCsENDCS(CsENDCSContext ctx) {
		super.exitCsENDCS(ctx);
		doENDCS();
	}

	@Override
	public void exitCsENDDO(CsENDDOContext ctx) {
		super.exitCsENDDO(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doENDDO(factor2, comment);
	}

	@Override
	public void exitCsENDFOR(CsENDFORContext ctx) {
		super.exitCsENDFOR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doENDFOR(comment);
	}

	@Override
	public void exitCsENDIF(CsENDIFContext ctx) {
		super.exitCsENDIF(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doENDIF(comment);
	}

	@Override
	public void exitCsENDMON(CsENDMONContext ctx) {
		super.exitCsENDMON(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doENDMON(comment);
	}

	@Override
	public void exitCsENDSL(CsENDSLContext ctx) {
		super.exitCsENDSL(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doENDSL(comment);
	}

	@Override
	public void exitCsENDSR(CsENDSRContext ctx) {
		super.exitCsENDSR(ctx);
		ParserRuleContext pctx = getParentSpec(ctx, RpgParser.EndsrContext.class);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		try {
			doENDSR(factor1, factor2, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void exitCsEVAL(CsEVALContext ctx) {
		super.exitCsEVAL(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doEVAL(factor2, comment);
	}

	@Override
	public void exitCsEVAL_CORR(CsEVAL_CORRContext ctx) {
		super.exitCsEVAL_CORR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doEVAL_CORR(factor2, comment);
	}

	@Override
	public void exitCsEVALR(CsEVALRContext ctx) {
		super.exitCsEVALR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doEVALR(factor2, comment);
	}

	@Override
	public void exitCsEXCEPT(CsEXCEPTContext ctx) {
		super.exitCsEXCEPT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doEXCEPT(factor2, comment);
	}

	@Override
	public void exitCsEXFMT(CsEXFMTContext ctx) {
		super.exitCsEXFMT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doEXFMT(factor2, result, length, decpos, low, comment);
	}

	@Override
	public void exitCsEXSR(CsEXSRContext ctx) {
		super.exitCsEXSR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doEXSR(factor2, comment);
	}

	@Override
	public void exitCsEXTRCT(CsEXTRCTContext ctx) {
		super.exitCsEXTRCT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(RESULT2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doEXTRCT(factor2, result, low, comment);
	}

	@Override
	public void exitCsFEOD(CsFEODContext ctx) {
		super.exitCsFEOD(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doFEOD(factor2, low, comment);
	}

	@Override
	public void exitCsFOR(CsFORContext ctx) {
		super.exitCsFOR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doFOR(factor2, comment);
	}

	@Override
	public void exitCsFORCE(CsFORCEContext ctx) {
		super.exitCsFORCE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doFORCE(factor2, comment);
	}

	@Override
	public void exitCsGOTO(CsGOTOContext ctx) {
		super.exitCsGOTO(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		try {
			doGOTO(factor2, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsIF(CsIFContext ctx) {
		super.exitCsIF(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doIF(factor2, comment);
	}

	@Override
	public void exitCsIFEQ(CsIFEQContext ctx) {
		super.exitCsIFEQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doIFEQ(factor1, factor2, comment);
	}

	@Override
	public void exitCsIFGE(CsIFGEContext ctx) {
		super.exitCsIFGE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doIFGE(factor1, factor2, comment);
	}

	@Override
	public void exitCsIFGT(CsIFGTContext ctx) {
		super.exitCsIFGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doIFGT(factor1, factor2, comment);
	}

	@Override
	public void exitCsIFLE(CsIFLEContext ctx) {
		super.exitCsIFLE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doIFLE(factor1, factor2, comment);
	}

	@Override
	public void exitCsIFLT(CsIFLTContext ctx) {
		super.exitCsIFLT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doIFLT(factor1, factor2, comment);
	}

	@Override
	public void exitCsIFNE(CsIFNEContext ctx) {
		super.exitCsIFNE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doIFNE(factor1, factor2, comment);
	}

	@Override
	public void exitCsIN(CsINContext ctx) {
		super.exitCsIN(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doIN(factor1, factor2, low, comment);
	}

	@Override
	public void exitCsITER(CsITERContext ctx) {
		super.exitCsITER(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doITER(comment);
	}

	@Override
	public void exitCsKFLD(CsKFLDContext ctx) {
		super.exitCsKFLD(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken comment = temp.get(COMMENT);
		try {
			doKFLD(result, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsKLIST(CsKLISTContext ctx) {
		super.exitCsKLIST(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken comment = temp.get(COMMENT);
		try {
			doKLIST(factor1, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsLEAVE(CsLEAVEContext ctx) {
		super.exitCsLEAVE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doLEAVE(comment);
	}

	@Override
	public void exitCsLEAVESR(CsLEAVESRContext ctx) {
		super.exitCsLEAVESR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doLEAVESR(comment);
	}

	@Override
	public void exitCsLOOKUP(CsLOOKUPContext ctx) {
		super.exitCsLOOKUP(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doLOOKUP(factor1, factor2, high, low, equal, comment);
	}

	@Override
	public void exitCsMHHZO(CsMHHZOContext ctx) {
		super.exitCsMHHZO(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken comment = temp.get(COMMENT);
		doMHHZO(factor2, result, length, decpos, comment);
	}

	@Override
	public void exitCsMHLZO(CsMHLZOContext ctx) {
		super.exitCsMHLZO(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken comment = temp.get(COMMENT);
		doMHLZO(factor2, result, length, decpos, comment);
	}

	@Override
	public void exitCsMLHZO(CsMLHZOContext ctx) {
		super.exitCsMLHZO(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken comment = temp.get(COMMENT);
		doMLHZO(factor2, result, length, decpos, comment);
	}

	@Override
	public void exitCsMLLZO(CsMLLZOContext ctx) {
		super.exitCsMLLZO(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken comment = temp.get(COMMENT);
		doMLLZO(factor2, result, length, decpos, comment);
	}

	@Override
	public void exitCsMONITOR(CsMONITORContext ctx) {
		super.exitCsMONITOR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doMONITOR(comment);
	}

	@Override
	public void exitCsMOVE(CsMOVEContext ctx) {
		super.exitCsMOVE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doMOVE(factor1, factor2, result, high, low, equal, comment);
	}

	@Override
	public void exitCsMOVEA(CsMOVEAContext ctx) {
		super.exitCsMOVEA(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doMOVEA(factor2, result, length, decpos, high, low, equal, comment);
	}

	@Override
	public void exitCsMOVEL(CsMOVELContext ctx) {
		super.exitCsMOVEL(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doMOVEL(factor1, factor2, result, length, decpos, high, low, equal,
				comment);
	}

	@Override
	public void exitCsMULT(CsMULTContext ctx) {
		super.exitCsMULT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doMULT(factor1, factor2, result, length, decpos, high, low, equal,
				comment);
	}

	@Override
	public void exitCsMVR(CsMVRContext ctx) {
		super.exitCsMVR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doMVR(result, length, decpos, high, low, equal, comment);
	}

	@Override
	public void exitCsNEXT(CsNEXTContext ctx) {
		super.exitCsNEXT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doNEXT(factor1, factor2, low, comment);
	}

	@Override
	public void exitCsOCCUR(CsOCCURContext ctx) {
		super.exitCsOCCUR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doOCCUR(factor1, factor2, result, low, comment);
	}

	@Override
	public void exitCsON_ERROR(CsON_ERRORContext ctx) {
		super.exitCsON_ERROR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doON_ERROR(factor2, comment);
	}

	@Override
	public void exitCsOPEN(CsOPENContext ctx) {
		super.exitCsOPEN(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doOPEN(factor2, low, comment);
	}

	@Override
	public void exitCsOREQ(CsOREQContext ctx) {
		super.exitCsOREQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doOREQ(factor1, factor2, comment);
	}

	@Override
	public void exitCsORGE(CsORGEContext ctx) {
		super.exitCsORGE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doORGE(factor1, factor2, comment);
	}

	@Override
	public void exitCsORGT(CsORGTContext ctx) {
		super.exitCsORGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doORGT(factor1, factor2, comment);
	}

	@Override
	public void exitCsORLE(CsORLEContext ctx) {
		super.exitCsORLE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doORLE(factor1, factor2, comment);
	}

	@Override
	public void exitCsORLT(CsORLTContext ctx) {
		super.exitCsORLT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doORLT(factor1, factor2, comment);
	}

	@Override
	public void exitCsORNE(CsORNEContext ctx) {
		super.exitCsORNE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doORNE(factor1, factor2, comment);
	}

	@Override
	public void exitCsOTHER(CsOTHERContext ctx) {
		super.exitCsOTHER(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doOTHER(comment);
	}

	@Override
	public void exitCsOUT(CsOUTContext ctx) {
		super.exitCsOUT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doOUT(factor1, factor2, low, comment);
	}

	@Override
	public void exitCsPARM(CsPARMContext ctx) {
		super.exitCsPARM(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		CommonToken result = temp.get(EXT_RESULT);
		try {
			doPARM(result, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCspec_fixed(Cspec_fixedContext ctx) {
		if (logger.isDebugEnabled()) {
			logger.debug("exitOp_pec_fixed(Cspec_fixedContext) - start"); //$NON-NLS-1$
			logger.debug(ctx.getText());
		}
		currentSpec = "C";
		debugContext(ctx);

		super.exitCspec_fixed(ctx);

	}

	@Override
	public void exitCsPLIST(CsPLISTContext ctx) {
		super.exitCsPLIST(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken comment = temp.get(COMMENT);
		doPLIST(factor1, comment);
	}

	@Override
	public void exitCsPOST(CsPOSTContext ctx) {
		super.exitCsPOST(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doPOST(factor1, factor2, result, low, comment);
	}

	@Override
	public void exitCsREAD(CsREADContext ctx) {
		super.exitCsREAD(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doREAD(factor2, result, low, equal, comment);
	}

	@Override
	public void exitCsREADC(CsREADCContext ctx) {
		super.exitCsREADC(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doREADC(factor2, result, low, equal, comment);
	}

	@Override
	public void exitCsREADE(CsREADEContext ctx) {
		super.exitCsREADE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doREADE(factor1, factor2, result, low, equal, comment);
	}

	@Override
	public void exitCsREADP(CsREADPContext ctx) {
		super.exitCsREADP(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doREADP(factor2, result, low, equal, comment);
	}

	@Override
	public void exitCsREADPE(CsREADPEContext ctx) {
		super.exitCsREADPE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doREADPE(factor1, factor2, result, low, equal, comment);
	}

	@Override
	public void exitCsREALLOC(CsREALLOCContext ctx) {
		super.exitCsREALLOC(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doREALLOC(factor2, result, low, comment);
	}

	@Override
	public void exitCsREL(CsRELContext ctx) {
		super.exitCsREL(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doREL(factor1, factor2, low, comment);
	}

	@Override
	public void exitCsRESET(CsRESETContext ctx) {
		super.exitCsRESET(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doRESET(factor1, factor2, result, low, comment);
	}

	@Override
	public void exitCsRETURN(CsRETURNContext ctx) {
		super.exitCsRETURN(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doRETURN(factor2, comment);
	}

	@Override
	public void exitCsROLBK(CsROLBKContext ctx) {
		super.exitCsROLBK(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doROLBK(low, comment);
	}

	@Override
	public void exitCsSCAN(CsSCANContext ctx) {
		super.exitCsSCAN(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doSCAN(factor1, factor2, result, length, decpos, low, equal, comment);
	}

	@Override
	public void exitCsSELECT(CsSELECTContext ctx) {
		super.exitCsSELECT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken comment = temp.get(COMMENT);
		doSELECT(comment);
	}

	@Override
	public void exitCsSETGT(CsSETGTContext ctx) {
		super.exitCsSETGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doSETGT(factor1, factor2, high, low, comment);
	}

	@Override
	public void exitCsSETLL(CsSETLLContext ctx) {
		super.exitCsSETLL(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doSETLL(factor1, factor2, high, low, equal, comment);
	}

	@Override
	public void exitCsSETOFF(CsSETOFFContext ctx) {
		super.exitCsSETOFF(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doSETOFF(high, low, equal, comment);
	}

	@Override
	public void exitCsSETON(CsSETONContext ctx) {
		super.exitCsSETON(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doSETON(high, low, equal, comment);
	}

	@Override
	public void exitCsSHTDN(CsSHTDNContext ctx) {
		super.exitCsSHTDN(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken high = temp.get(HIGH);
		CommonToken comment = temp.get(COMMENT);
		doSHTDN(high, comment);
	}

	@Override
	public void exitCsSORTA(CsSORTAContext ctx) {
		super.exitCsSORTA(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doSORTA(factor2, comment);
	}

	@Override
	public void exitCsSQRT(CsSQRTContext ctx) {
		super.exitCsSQRT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken comment = temp.get(COMMENT);
		doSQRT(factor2, result, length, decpos, comment);
	}

	@Override
	public void exitCsSUB(CsSUBContext ctx) {
		super.exitCsSUB(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doSUB(factor1, factor2, result, length, decpos, high, low, equal,
				comment);
	}

	@Override
	public void exitCsSUBDUR(CsSUBDURContext ctx) {
		super.exitCsSUBDUR(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doSUBDUR(factor1, factor2, result, low, comment);
	}

	@Override
	public void exitCsSUBST(CsSUBSTContext ctx) {
		super.exitCsSUBST(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doSUBST(factor1, factor2, result, length, decpos, low, comment);
	}

	@Override
	public void exitCsTAG(CsTAGContext ctx) {
		super.exitCsTAG(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken comment = temp.get(COMMENT);
		try {
			doTAG(factor1, comment);
		} catch (RPGFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitCsTEST(CsTESTContext ctx) {
		super.exitCsTEST(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doTEST(factor1, result, low, comment);
	}

	@Override
	public void exitCsTESTB(CsTESTBContext ctx) {
		super.exitCsTESTB(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doTESTB(factor2, result, high, low, equal, comment);
	}

	@Override
	public void exitCsTESTN(CsTESTNContext ctx) {
		super.exitCsTESTN(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doTESTN(result, high, low, equal, comment);
	}

	@Override
	public void exitCsTESTZ(CsTESTZContext ctx) {
		super.exitCsTESTZ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doTESTZ(result, high, low, equal, comment);
	}

	@Override
	public void exitCsTIME(CsTIMEContext ctx) {
		super.exitCsTIME(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken comment = temp.get(COMMENT);
		doTIME(result, length, decpos, comment);
	}

	@Override
	public void exitCsUNLOCK(CsUNLOCKContext ctx) {
		super.exitCsUNLOCK(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doUNLOCK(factor2, low, comment);
	}

	@Override
	public void exitCsUPDATE(CsUPDATEContext ctx) {
		super.exitCsUPDATE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doUPDATE(factor2, result, low, comment);
	}

	@Override
	public void exitCsWHEN(CsWHENContext ctx) {
		super.exitCsWHEN(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doWHEN(factor2, comment);
	}

	@Override
	public void exitCsWHENEQ(CsWHENEQContext ctx) {
		super.exitCsWHENEQ(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doWHENEQ(factor1, factor2, comment);
	}

	@Override
	public void exitCsWHENGE(CsWHENGEContext ctx) {
		super.exitCsWHENGE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doWHENGE(factor1, factor2, comment);
	}

	@Override
	public void exitCsWHENGT(CsWHENGTContext ctx) {
		super.exitCsWHENGT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doWHENGT(factor1, factor2, comment);
	}

	@Override
	public void exitCsWHENLE(CsWHENLEContext ctx) {
		super.exitCsWHENLE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doWHENLE(factor1, factor2, comment);
	}

	@Override
	public void exitCsWHENLT(CsWHENLTContext ctx) {
		super.exitCsWHENLT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doWHENLT(factor1, factor2, comment);
	}

	@Override
	public void exitCsWHENNE(CsWHENNEContext ctx) {
		super.exitCsWHENNE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doWHENNE(factor1, factor2, comment);
	}

	@Override
	public void exitCsWRITE(CsWRITEContext ctx) {
		super.exitCsWRITE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doWRITE(factor2, result, low, equal, comment);
	}

	@Override
	public void exitCsXFOOT(CsXFOOTContext ctx) {
		super.exitCsXFOOT(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		doXFOOT(factor2, result, length, decpos, high, low, equal, comment);
	}

	@Override
	public void exitCsXLATE(CsXLATEContext ctx) {
		super.exitCsXLATE(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor1 = temp.get(EXT_FACTOR1);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken low = temp.get(LOW);
		CommonToken comment = temp.get(COMMENT);
		doXLATE(factor1, factor2, result, length, decpos, low, comment);
	}

	@Override
	public void exitCsXML_INTO(CsXML_INTOContext ctx) {
		super.exitCsXML_INTO(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doXML_INTO(factor2, comment);
	}

	@Override
	public void exitCsXML_SAX(CsXML_SAXContext ctx) {
		super.exitCsXML_SAX(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken comment = temp.get(COMMENT);
		doXML_SAX(factor2, comment);
	}

	@Override
	public void exitCsZ_ADD(CsZ_ADDContext ctx) {
		if (logger.isDebugEnabled()) {
			logger.debug("exitCsZ_ADD(CsZ_ADDContext) - start"); //$NON-NLS-1$
		}

		super.exitCsZ_ADD(ctx);
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		try {
			doZ_ADD(factor2, result, length, decpos, high, low, equal, comment);
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
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFields(pctx);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		CommonToken result = temp.get(EXT_RESULT);
		CommonToken length = temp.get(LENGTH);
		CommonToken decpos = temp.get(DESC_POS);
		CommonToken high = temp.get(HIGH);
		CommonToken low = temp.get(LOW);
		CommonToken equal = temp.get(EQUAL);
		CommonToken comment = temp.get(COMMENT);
		try {
			doZ_SUB(factor2, result, length, decpos, high, low, equal, comment);
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

	private Map<String, CommonToken> getFields(ParserRuleContext ctx) {
		HashMap<String, CommonToken> result = new HashMap<String, CommonToken>();
		ArrayList<CommonToken> myList = new ArrayList<CommonToken>();
		fillTokenList(ctx, myList);
		String lastTokenType = "";
		String ExtFactor1 = "";
		String ExtOpCode = "";
		String ExtFactor2 = "";
		String ExtResult = "";
		for (int i = 0; i < myList.size(); i++) {
			CommonToken ct = myList.get(i);
			int thePos = ct.getCharPositionInLine();
			if (ct.getType() == RpgLexer.EOL){
				break;
			} else if (thePos == 5) {
				lastTokenType = voc.getDisplayName(ct.getType());
				result.put(lastTokenType, ct);
			} else if (thePos == 6) {
				lastTokenType = CONTROL_LEVEL;
				result.put(CONTROL_LEVEL, ct);
			} else if (thePos == 8) {
				lastTokenType = AND_NOT;
				result.put(AND_NOT, ct);
			} else if (thePos == 9) {
				lastTokenType = CONDITIONING_INDICATOR;
				result.put(CONDITIONING_INDICATOR, ct);
			} else if (thePos == 11) {
				lastTokenType = FACTOR1;
				result.put(FACTOR1, ct);
				ExtFactor1 = ct.getText().trim();
			} else if (thePos > 11 && thePos < 25) {
				ExtFactor1 += ct.getText().trim();
			} else if (thePos == 25) {
				// First put the extended factor1 into the map
				CommonToken work = new CommonToken(RpgLexer.CS_FactorContent,
						ExtFactor1);
				result.put(EXT_FACTOR1, work);
				// Now reset the factor1 String
				ExtFactor1 = "";

				// Now put the opCode in
				lastTokenType = OP_CODE;
				ExtOpCode = ct.getText().trim();
				result.put(OP_CODE, ct);
				// Prepare to accumulate OpCode stuff
			} else if (thePos > 25 && thePos < 35) {
				ExtOpCode += ct.getText().trim();
			} else if (thePos == 35) {
				// First put the extended opcode into the map
				CommonToken work = new CommonToken(
						RpgLexer.CS_OperationAndExtender, ExtOpCode);
				result.put(EXT_OP_CODE, work);
				// Now reset the opCode String
				ExtOpCode = "";

				// Now put Factor2 stuff in
				ExtFactor2 = ct.getText();
				lastTokenType = FACTOR2;
				result.put(FACTOR2, ct);
			} else if (thePos > 35 && thePos < 49) {
				ExtFactor2 += ct.getText().trim();
			} else if (thePos == 49) {
				// First put the extended factor2 into the map
				CommonToken work = new CommonToken(RpgLexer.CS_FactorContent,
						ExtFactor2);
				result.put(EXT_FACTOR2, work);
				// Now reset the factor2 String
				ExtFactor2 = "";

				lastTokenType = RESULT2;
				result.put(RESULT2, ct);
				ExtResult = ct.getText().trim();
			} else if (thePos > 49 && thePos < 63) {
				ExtResult += ct.getText().trim();
			} else if (thePos == 63) {
				// First put the ExtResult in the map
				CommonToken work = new CommonToken(RpgLexer.CS_FactorContent,
						ExtResult);
				result.put(EXT_RESULT, work);
				// Now reset the result String
				ExtResult = "";

				result.put(LENGTH, ct);
			} else if (thePos == 68) {
				result.put(DESC_POS, ct);
			} else if (thePos == 70) {
				result.put(HIGH, ct);
			} else if (thePos == 72) {
				result.put(LOW, ct);
			} else if (thePos == 74) {
				result.put(EQUAL, ct);
			} else if (thePos == 80) {
				result.put(COMMENT, ct);
			} else {
				result.put(voc.getDisplayName(ct.getType()), ct);
			}
		}

		return result;
	}

	private Map<String, CommonToken> getFieldsX2(ParserRuleContext ctx) {
		HashMap<String, CommonToken> result = new HashMap<String, CommonToken>();
		ArrayList<CommonToken> myList = new ArrayList<CommonToken>();
		fillTokenList(ctx, myList);
		String lastTokenType = "";
		String ExtFactor1 = "";
		String ExtOpCode = "";
		String ExtFactor2 = "";
		String ExtResult = "";
		for (int i = 0; i < myList.size(); i++) {
			CommonToken ct = myList.get(i);
			int thePos = ct.getCharPositionInLine();
			if (ct.getType() == RpgLexer.EOL){
				break;
			} else if (thePos == 5) {
				lastTokenType = voc.getDisplayName(ct.getType());
				result.put(lastTokenType, ct);
			} else if (thePos == 6) {
				lastTokenType = CONTROL_LEVEL;
				result.put(CONTROL_LEVEL, ct);
			} else if (thePos == 8) {
				lastTokenType = AND_NOT;
				result.put(AND_NOT, ct);
			} else if (thePos == 9) {
				lastTokenType = CONDITIONING_INDICATOR;
				result.put(CONDITIONING_INDICATOR, ct);
			} else if (thePos == 11) {
				lastTokenType = FACTOR1;
				result.put(FACTOR1, ct);
				ExtFactor1 = ct.getText().trim();
			} else if (thePos > 11 && thePos < 25) {
				ExtFactor1 += ct.getText().trim();
			} else if (thePos == 25) {
				// First put the extended factor1 into the map
				CommonToken work = new CommonToken(RpgLexer.CS_FactorContent,
						ExtFactor1);
				result.put(EXT_FACTOR1, work);
				// Now reset the factor1 String
				ExtFactor1 = "";

				// Now put the opCode in
				lastTokenType = OP_CODE;
				ExtOpCode = ct.getText().trim();
				result.put(OP_CODE, ct);
				// Prepare to accumulate OpCode stuff
			} else if (thePos > 25 && thePos < 35) {
				ExtOpCode += ct.getText().trim();
			} else if (thePos >= 35 && thePos < 80) {
				// First put the extended opcode into the map
				if (!ExtOpCode.isEmpty()) {
					CommonToken work = new CommonToken(
							RpgLexer.CS_OperationAndExtender, ExtOpCode);
					result.put(EXT_OP_CODE, work);
					// Now reset the opCode String
					ExtOpCode = "";
					// Truncate the string on the first pass
					ExtFactor2 = "";
				}

				// Accumulate the text from the extended factor2
				ExtFactor2 += ct.getText().trim() + " ";
			} else {
				result.put(voc.getDisplayName(ct.getType()), ct);
			}
		}
		if (!ExtFactor2.isEmpty()) {
			CommonToken work = new CommonToken(
					RpgLexer.CS_OperationAndExtender, ExtFactor2);
			result.put(EXT_FACTOR2, work);
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
		if (indentLevel < 0) {
			this.indentLevel = 0;
		} else {
			this.indentLevel = indentLevel;
		}
	}

	private void setResultingIndicator(CommonToken indicator, String condition) {
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent)) + condition;
		cspecs.add(workString);
		workString = StringUtils.repeat(' ',
				7 + ((indentLevel + 1) * spacesToIndent))
				+ "*IN"
				+ indicator.getText().trim() + " = *ON;";
		cspecs.add(workString);
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "ELSE;";
		cspecs.add(workString);
		workString = StringUtils.repeat(' ',
				7 + ((indentLevel + 1) * spacesToIndent))
				+ "*IN"
				+ indicator.getText().trim() + " = *OFF;";
		cspecs.add(workString);
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent)) + "ENDIF;";
		cspecs.add(workString);
	}

	public void setSpacesToIndent(int spacesToIndent) {
		this.spacesToIndent = spacesToIndent;
	}

	@Override
	public void exitFree(FreeContext ctx) {
		super.exitFree(ctx);
		debugContext(ctx);
	}

	@Override
	public void exitStar_comments(Star_commentsContext ctx) {
		super.exitStar_comments(ctx);
		int start = ctx.getStart().getTokenIndex();
		List<Token> theList = ts.getHiddenTokensToRight(start);
		String prependStuff = StringUtils.repeat(' ', ctx.getStart()
				.getCharPositionInLine());
		workString = prependStuff;
		workString += ctx.getText();
		for (Token ct : theList) {
			workString += ct.getText();
		}
		if (currentSpec.equals("H")) {
			hspecs.add(workString);
		} else if (currentSpec.equals("F")) {
			fspecs.add(workString);
		} else if (currentSpec.equals("D")) {
			dspecs.add(workString);
		} else if (currentSpec.equals("C") || currentSpec.equals("P")) {
			cspecs.add(workString);
		} else if (currentSpec.equals("O")) {
			ospecs.add(workString);
		}
	}

	@Override
	public void exitCspec_fixed_x2(Cspec_fixed_x2Context ctx) {
		super.exitCspec_fixed_x2(ctx);
		debugContext(ctx);
		currentSpec = "C";
		ParserRuleContext pctx = getCSpec(ctx);
		Map<String, CommonToken> temp = getFieldsX2(pctx);
		CommonToken opCode = temp.get(EXT_OP_CODE);
		CommonToken factor2 = temp.get(EXT_FACTOR2);
		String curOpCode = opCode.getText();

		if (curOpCode.equalsIgnoreCase("IF")) {
			doIF(factor2, null);
		} else if (curOpCode.equalsIgnoreCase("DOW")) {
			doDOW(factor2, null);
		} else if (curOpCode.equalsIgnoreCase("DOU")) {
			doDOU(factor2, null);
		} else if (curOpCode.equalsIgnoreCase("EVAL")) {
			doEVAL(factor2, null);
		} else if (curOpCode.equalsIgnoreCase("EVALR")) {
			doEVALR(factor2, null);
		} else if (curOpCode.equalsIgnoreCase("EVAL_CORR")) {
			doEVAL_CORR(factor2, null);
		}

	}

	@Override
	public void exitDspec_fixed(Dspec_fixedContext ctx) {
		super.exitDspec_fixed(ctx);
		dspecs.add("     " + ctx.getText());

		currentSpec = "D";
	}

	@Override
	public void exitFspec_fixed(Fspec_fixedContext ctx) {
		super.exitFspec_fixed(ctx);
		fspecs.add("     " + ctx.getText());

		currentSpec = "F";
	}

	@Override
	public void exitHspec_fixed(Hspec_fixedContext ctx) {
		super.exitHspec_fixed(ctx);
		hspecs.add("     " + ctx.getText());

		currentSpec = "H";
	}

	@Override
	public void exitIspec_fixed(Ispec_fixedContext ctx) {
		super.exitIspec_fixed(ctx);
		ispecs.add("     " + ctx.getText());
		currentSpec = "I";

	}

	@Override
	public void exitOspec_fixed(Ospec_fixedContext ctx) {
		super.exitOspec_fixed(ctx);
		ospecs.add("     " + ctx.getText());
		currentSpec = "O";

	}

	@Override
	public void exitPsBegin(PsBeginContext ctx){
		super.exitPsBegin(ctx);
		cspecs.add("     " + ctx.getText());
		currentSpec = "P";

	}

	@Override
	public void exitPsEnd(PsEndContext ctx){
		super.exitPsEnd(ctx);
		cspecs.add("     " + ctx.getText());
		currentSpec = "P";

	}

	@Override
	public void exitBeginif(BeginifContext ctx) {
		// TODO Auto-generated method stub
		super.exitBeginif(ctx);
		//ParserRuleContext pctx = getParentSpec(ctx, RpgParser.IfstatementContext.class);
		List<CommonToken> myList = getTheTokens(ctx);
		doFreeIF(myList);
	}

	@Override
	public void exitBeginProcedure(BeginProcedureContext ctx) {
		// TODO Auto-generated method stub
		super.exitBeginProcedure(ctx);
		debugContext(ctx);
	}

	@Override
	public void exitBeginselect(BeginselectContext ctx) {
		// TODO Auto-generated method stub
		super.exitBeginselect(ctx);
		debugContext(ctx);
	}

	@Override
	public void exitBegsr(BegsrContext ctx) {
		// TODO Auto-generated method stub
		super.exitBegsr(ctx);
		debugContext(ctx);
	}

	@Override
	public void exitC_free(C_freeContext ctx) {
		super.exitC_free(ctx);
		System.err.println("***exitC_free**************************");
		debugContext(ctx);
	}

	private void doFreeFOR(List<CommonToken> myList) {
		setIndentLevel(++indentLevel);
		structuredOps.push("FOR");
	}

	private void doFreeDOW(List<CommonToken> myList) {
		setIndentLevel(++indentLevel);
		structuredOps.push("DO");
	}

	private void doFreeDOU(List<CommonToken> myList) {
		setIndentLevel(++indentLevel);
		structuredOps.push("DO");
	}

	private void doFreeIF(List<CommonToken> inList ) {
		boolean emit = false;
		structuredOps.push("IF");
		workString = StringUtils
				.repeat(' ', 7 + (indentLevel * spacesToIndent));
		for (CommonToken ct : inList){
			if (voc.getDisplayName( ct.getType()).startsWith("OP")){
				emit = true;
			}
			if (emit){
				workString += ct.getText() + " ";
			}
			
		}
		cspecs.add(workString);
		setIndentLevel(++indentLevel);
	}

	@Override
	public void exitEndif(EndifContext ctx) {
		// TODO Auto-generated method stub
		super.exitEndif(ctx);
		System.err.println("***exitEndif**************************");
		debugContext(ctx);
	}

	@Override
	public void exitEndProcedure(EndProcedureContext ctx) {
		// TODO Auto-generated method stub
		super.exitEndProcedure(ctx);
		System.err.println("***exitEndProcedure**************************");
		debugContext(ctx);
	}

	@Override
	public void exitEndselect(EndselectContext ctx) {
		// TODO Auto-generated method stub
		super.exitEndselect(ctx);
		System.err.println("***exitEndSelect**************************");
		debugContext(ctx);
	}

	@Override
	public void exitEndsr(EndsrContext ctx) {
		// TODO Auto-generated method stub
		super.exitEndsr(ctx);
		System.err.println("***exitEndsr**************************");
		debugContext(ctx);
	}

}
