package org.rpgleparser;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.rpgleparser.utils.TestUtils.assertTokens;

public class TestFREE {

	@Test
	public void testReset() {
		String inputstr = "         reset itemPromptBlock(*);";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "reset", "itemPromptBlock", "(", "*", ")", ";");
	}

	@Test
	public void testAssignment() {
		String inputstr = "             cmdlength = 123;     \r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "cmdlength", "=", "123", ";", "");
	}

	@Test
	public void testExecSQL() {
		String inputstr = "         Exec SQL\r\n"
				+ "         set :FieldAll =coalesce( getProcenter(:PolicyNbr,\r\n"
				+ "         :DateCode, ''),'');\r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "Exec SQL",
				"set :FieldAll =coalesce( getProcenter(:PolicyNbr,",
				":DateCode, ''),'')", ";");
	}

	@Test
	public void testExecSQL1() {
		String inputstr = "         Exec SQL x=1;\r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "Exec SQL", "x=1", ";", "");
	}

	@Test
	public void testStringAssignment() {
		String inputstr = "             cmdlength = '123';     \r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "cmdlength", "=", "'", "123", "'", ";", "");
	}

	@Test
	public void testBasicFunction() {
		String inputstr = "             cmdlength = %len(123);     \r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "cmdlength", "=", "%len", "(", "123", ")", ";");
	}

	@Test
	public void testBasicFunctionParms() {
		String inputstr = "             cmdlength = %len(123:456);     \r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "cmdlength", "=", "%len", "(", "123", ":",
				"456", ")", ";");
	}

	@Test
	public void testNestedFunction() {
		String inputstr = "             cmdlength = %len(%trim(cmdstring));     \r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "cmdlength", "=", "%len", "(", "%trim", "(",
				"cmdstring", ")", ")", ";");

	}

	@Test
	public void testMultiLine_1() {
		String inputstr = "             cmdlength = \r\n"
				+ "               123;     \r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "cmdlength", "=", "123", ";");
	}

	@Test
	public void testMultiLine() {
		String inputstr = "             cmdstring = \r\n"
				+ "              'CRTDTAARA DTAARA(QTEMP/MYTWENTY1) TYPE(*CHAR) LEN(21)';\r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "cmdstring", "=", "'",
				"CRTDTAARA DTAARA(QTEMP/MYTWENTY1) TYPE(*CHAR) LEN(21)", "'",
				";", "");
	}

	@Test
	public void testON_ERROR() {
		String inputstr = "             on-error; \r\n";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "on-error", ";");
	}

	@Test
	public void testComparision() {
		String inputstr = "         if %parms > 2;";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "if", "%parms", ">", "2");
	}

	@Test
	public void testQualified() {
		String inputstr = "        Destination = PCINFO.Destination;";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "Destination", "=", "PCINFO", ".",
				"Destination");
	}

	@Test
	public void testIndAndBIF() {
		String inputstr = "        *IN99 = NOT %FOUND;";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "*IN99", "=", "NOT", "%FOUND");
	}

	@Test
	public void testCompoundIf() throws InterruptedException {
		String inputstr = "       IF ARNTCT = 'B  '\r\n"
				+ "       OR ARNTCT = 'C  '\r\n" + "       OR ARNTCT = 'D  ';";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "IF", "ARNTCT", "=", "'", "B", "'", "OR",
				"ARNTCT", "=", "'", "C", "'", "OR", "ARNTCT", "=", "'", "D",
				"'", ";", "");
	}

	@Test
	public void testCompoundIf2() {
		String inputstr = "       IF ARNTCT = 'B' OR ARNTCT = 'C  ' OR ARNTCT = 'D  '\r\n"
				+ "          ;";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "IF", "ARNTCT", "=", "'", "B", "'", "OR",
				"ARNTCT", "=", "'", "C", "'", "OR", "ARNTCT", "=", "'", "D",
				"'", ";", "");
	}

	@Test
	public void testSubst() {
		String inputstr = "       %subst(MARK3: 1: 3) = '613';";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "%subst", "(", "MARK3", ":", "1", ":", "3",
				")", "=", "'", "613", "'", ";", "");

	}

	@Test
	public void testStringconcat() {
		String inputstr = "       DATA ='Loan No:  ' + %TRIM(LOANNO);";
		inputstr = TestUtils.pad280(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "DATA", "=", "'", "Loan No:", "'", "+",
				"%TRIM", "(", "LOANNO", ")", ";", "");
	}

	@Test
	public void testMultiplyDecimal() {
		String inputstr = TestUtils.pad280Free("InstPct = A1IPCT * .01 * I;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "InstPct", "=", "A1IPCT", "*", ".01", "*", "I",
				";", "");
	}

	@Test
	public void testNestedArgsExpr() {
		String inputstr = TestUtils
				.pad280Free("Temp60 = %INT((TermPrem * InstPct) + .5);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "Temp60", "=", "%INT", "(", "(", "TermPrem",
				"*", "InstPct", ")", "+", ".5", ")", ";", "");
	}

	@Test
	public void testSimpleBareBIF() {
		String inputstr = TestUtils.pad280Free("%INT(1.5);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "%INT", "(", "1.5", ")", ";", "");
	}

	@Test
	public void testSimpleNestedBIF() {
		String inputstr = TestUtils.pad280Free("%INT((1.5));");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "%INT", "(", "(", "1.5", ")", ")", ";");
	}

	@Test
	public void testAlgebraExpr1() {
		String inputstr = TestUtils
				.pad280Free("InstallPrem = (TermPrem * InstPct) - PremApplied;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "InstallPrem", "=", "(", "TermPrem", "*",
				"InstPct", ")", "-", "PremApplied", ";", "");
	}

	@Test
	public void testAlgebraExpr2() {
		String inputstr = TestUtils.pad280Free("Mths2Add = Months * (I - 1);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "Mths2Add", "=", "Months", "*", "(", "I", "-",
				"1", ")", ";", "");
	}

	@Test
	public void testFreeNameContinuation() {
		String inputstr = TestUtils.pad280Free("dcl-s continued...\r\n"
				+ "name char(10);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "dcl-s", "continued", "...", "name", "char",
				"(", "10", ")", ";");
	}

	@Test
	public void testFreeNameAtSign() {
		String inputstr = TestUtils.pad280Free("CvtDate(ProcessDate: @DTE);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "CvtDate", "(", "ProcessDate", ":", "@DTE",
				")", ";", "");
	}

	@Test
	public void testFreeNameLBSign() {
		String inputstr = TestUtils.pad280Free("#1F2CD = PolicyType;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "#1F2CD", "=", "PolicyType", ";", "");
	}

	@Test
	public void testChain() {
		String inputstr = TestUtils.pad280Free("chain (PolicyNumber) AR1203B;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "chain", "(", "PolicyNumber", ")", "AR1203B");
	}

	@Test
	public void testChainLiteral() {
		String inputstr = TestUtils.pad280Free("chain 'PolicyNumber' AR1203B;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "chain", "'", "PolicyNumber", "'", "AR1203B",
				";", "");
	}

	@Test
	public void testMultNegative() {
		String inputstr = TestUtils.pad280Free("Days2Add = LeadDays * -1;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "Days2Add", "=", "LeadDays", "*", "-", "1", ";");
	}

	@Test
	public void testKeywordID() {
		String inputstr = TestUtils.pad280Free("IF DELETE = ' ';");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "IF", "DELETE", "=", "'", "", "'", ";", "");
	}

	@Test
	public void testKeywordID2() {
		String inputstr = TestUtils.pad280Free("EVAL IN = INSTAL;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "EVAL", "IN", "=", "INSTAL", ";", "");
	}

	@Test
	public void testKeywordID3() {
		String inputstr = TestUtils.pad280Free("TEMP14 = ZLNG(IN);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "TEMP14", "=", "ZLNG", "(", "IN", ")", ";", "");
	}

	@Test
	public void testLongLineIssue() {
		String inputstr = TestUtils
				.pad280("       DAYS = 5;                                                               // EFT DATE");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "DAYS", "=", "5", ";", "//", "EFT DATE", "");
	}

	@Test
	public void testINDExpression() {
		String inputstr = TestUtils
				.pad280Free("       IF *IN78 = *OFF                                                          // RECORD EXISTS\r\n"
						+ "          AND MHTRPR = 'N';");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "IF", "*IN78", "=", "*OFF", "AND", "MHTRPR",
				"=", "'", "N", "'", ";", "");
	}

	@Test
	public void testFreeWithTailComment() {
		String inputstr = TestUtils
				.pad280Free("if CovName = 'DWELLING';//CP & CF");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "if", "CovName", "=", "'", "DWELLING", "'", ";");
	}

	@Test
	public void testFreeExprMixedOpsAndLiterals() {
		String inputstr = TestUtils.pad280Free("EVAL TEST = 'N';");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "EVAL", "TEST", "=", "'", "N", "'", ";", "");
	}

	@Test
	public void testFreeLeftHandSideAssignment() {
		String inputstr = TestUtils
				.pad280Free("%SUBST(COPYTO:2:18) = ZCPY(A);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "%SUBST", "(", "COPYTO", ":", "2", ":", "18",
				")", "=", "ZCPY", "(A)", ";", "");
	}

	@Test
	public void testFreeStringConcat() {
		String inputstr = TestUtils
				.pad280Free("DS1802 = %TRIMR(DS1802) + '-';");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "DS1802", "=", "%TRIMR", "(", "DS1802", ")",
				"+", "'", "-", "'", ";", "");
	}

	@Test
	public void testFreeAssignment() {
		String inputstr = TestUtils.pad280Free("B1DQNB = -.25;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "B1DQNB", "=", "-", ".25", ";");
	}

	@Test
	public void testFreeArray() {
		String inputstr = TestUtils.pad280Free("ZCPY(A);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "ZCPY", "(A)", ";");
	}

	// TODO this should not be an OP_E

	@Test
	public void testSplatAll() {
		String inputstr = TestUtils.pad280Free("*in = *all'0' ;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "*in", "=", "*all", "'", "0", "'", ";");
	}

	@Test
	public void testForBy() {
		String inputstr = TestUtils.pad280Free("FOR X = 100 BY -1 TO 100;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "FOR", "X", "=", "100", "BY", "-", "1", "TO",
				"100", ";");
	}

	@Test
	public void testDclDs1() {
		String inputstr = TestUtils.pad280Free("       DCL-DS *N;\r\n"
				+ "       END-DS;");

		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "DCL-DS", "*", "N", ";", "END-DS", ";");
	}

	@Test
	public void testDclDs2() {
		String inputstr = TestUtils.pad280Free("       DCL-DS *N;\r\n"
				+ "         ABDTFL ZONED(7);\r\n"
				+ "         ABDTFL ZONED(7);\r\n" + "       END-DS;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "DCL-DS", "*", "N", ";", "ABDTFL", "ZONED",
				"(", "7", ")", ";", "ABDTFL", "ZONED", "(", "7", ")", ";",
				"END-DS", ";");
	}

	@Test
	public void testDclDsShort() {
		String inputstr = TestUtils
				.pad280Free("       DCL-DS prtDs LEN(132) END-DS;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "DCL-DS", "prtDs", "LEN", "(", "132", ")",
				"END-DS", ";");
	}

	@Test
	public void testPlusEquals() {
		String inputstr = TestUtils.pad280Free("B1SQ## += 1;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "B1SQ##", "+=", "1", ";");
	}

	@Test
	public void testTest() {
		String inputstr = TestUtils.pad280Free("TEST(De) *iso0 LicIssueDate ;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "TEST", "(De)", "*iso0", "LicIssueDate", ";");
	}

	@Test
	public void testXMLId() {
		String inputstr = TestUtils
				.pad280Free("EndNbr = Endorsements.Endorsement(EndCnt).EndorsementNumber;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "EndNbr", "=", "Endorsements", ".",
				"Endorsement", "(", "EndCnt", ")", ".", "EndorsementNumber",
				";");
	}

	@Test
	public void testWithCrazyComment() {
		String inputstr = TestUtils.pad280("           IF SCTRAN = '1'\r\n"
				+ " 1  �        //DLT       SCTRAN    OREQ '3'\r\n"
				+ "             OR SCTRAN = '6';");

		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "IF", "SCTRAN", "=", "'", "1", "'", "OR",
				"SCTRAN", "=", "'", "6", "'", ";");
	}

	@Test
	public void testPR() {
		String inputstr = TestUtils.pad280Free("    DCL-PR CLR014 EXTPGM;\r\n"
				+ "    TRNSDT_ LIKE(TRNSDT);\r\n" + "    END-PR;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "DCL-PR", "CLR014", "EXTPGM", ";", "TRNSDT_",
				"LIKE", "(", "TRNSDT", ")", ";", "END-PR", ";");
	}

	@Test
	public void testPI() {
		String inputstr = TestUtils.pad280Free("    DCL-PI CLR014;\r\n"
				+ "TRNSDT PACKED(7);\r\n" + "END-PI;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "DCL-PI", "CLR014", ";", "TRNSDT", "PACKED",
				"(", "7", ")", ";", "END-PI", ";");
	}

	@Test
	public void testConstant() {
		String inputstr = TestUtils.pad280Free("DCL-C CRLF CONST(X'0d25');");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "DCL-C", "CRLF", "CONST", "(", "X'", "0d25",
				"'", ")", ";");
	}

	@Test
	public void testExpressionAsterisk() {
		String inputstr = TestUtils.pad280Free("FROMcymd = ((Year)*10000);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "FROMcymd", "=", "(", "(", "Year", ")", "*",
				"10000", ")", ";");
	}

	@Test
	public void testDateLiteral() {
		String inputstr = TestUtils
				.pad280Free("IF OVTRDZ = d'0001-01-01';   // ACTIVE");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "IF", "OVTRDZ", "=", "d'", "0001-01-01", "'",
				";");
	}

	@Test
	public void testOpt() {
		String inputstr = TestUtils.pad280Free(" CTL-OPT\r\n"
				+ "DATEDIT(*YMD);");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "CTL-OPT", "DATEDIT", "(", "*YMD", ")", ";");
	}

	@Test
	public void testDCL_DS() {
		String inputstr = TestUtils.pad280Free(" DCL-DS *N;\r\n" + "END-DS;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "DCL-DS", "*", "N", ";", "END-DS", ";");
	}

	@Test
	public void testDCL_DS_Err() {
		//System.out.println((int) ' ');
		String inputstr = TestUtils.pad280Free(" DCL-DS * N;\r\n" + "END-DS;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertTrue("Errors expected", errors.size() > 0);

	}

	@Test
	public void testMultiply() {
		String inputstr = TestUtils.pad280Free(" 1 * N;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "1", "*", "N", ";");
	}

	@Test
	public void testMultiply2() {
		String inputstr = TestUtils.pad280Free(" 1*N;");
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		// TestUtils.showToks(tokenList);
		assertTokens(tokenList, "1", "*", "N", ";");
	}

	@Test
	public void testEvalFree_OptionalContinuation() {
		String inputstr = "if BDEBRYR = *zero and ADEBRYR <> *zero or \r\n"
				+ "BDEBMTH = *zero and ADEBMTH <> *zero or \r\n"
				+ "BDEBDAY = *zero and ADEBDAY <> *zero;";
		inputstr = TestUtils.pad280Free(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "if", "BDEBRYR", "=", "*zero", "and",
				"ADEBRYR", "<>", "*zero", "or", "BDEBMTH", "=", "*zero", "and",
				"ADEBMTH", "<>", "*zero", "or", "BDEBDAY", "=", "*zero", "and",
				"ADEBDAY", "<>", "*zero", ";");
	}

	@Test
	public void testEvalFree1() {
		String inputstr = "DCL-PI PPR751G;\r\n"
				+ "PAR_ENDMTH PACKED(7);                                                  //            ER\r\n"
				+ "TOADDR_PARM LIKE(TOADDR);\r\n" + "\r\n" + "END-PI;";
		inputstr = TestUtils.pad280Free(inputstr);
		List<String> errors = new ArrayList<String>();
		List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
		assertThat(errors, is(empty()));
		assertTokens(tokenList, "DCL-PI", "PPR751G", ";", "PAR_ENDMTH",
				"PACKED", "(", "7", ")", ";", "TOADDR_PARM", "LIKE", "(",
				"TOADDR", ")", ";", "END-PI", ";");
	}

}