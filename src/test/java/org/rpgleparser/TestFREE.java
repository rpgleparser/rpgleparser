package org.rpgleparser;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.rpgleparser.utils.TestUtils.expectTokensForFreeSnippet;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestFREE {

    @Test
    public void testReset() {
        String inputString =
                "         reset itemPromptBlock(*);";
        expectTokensForSourceLines(inputString,
                "reset", "itemPromptBlock", "(", "*", ")", ";");
    }

    @Test
    public void testAssignment() {
        String inputString =
                "             cmdlength = 123;     \r\n";
        expectTokensForSourceLines(inputString,
                "cmdlength", "=", "123", ";");
    }

    @Test
    public void testExecSQL() {
        String inputString =
                "         Exec SQL\r\n"
                        + "         set :FieldAll =coalesce( getProcenter(:PolicyNbr,\r\n"
                        + "         :DateCode, ''),'');\r\n";
        expectTokensForSourceLines(inputString,
                "Exec SQL",
                "set :FieldAll =coalesce( getProcenter(:PolicyNbr,",
                ":DateCode, ''),'')", ";");
    }

    @Test
    public void testExecSQL1() {
        String inputString =
                "         Exec SQL x=1;\r\n";
        expectTokensForSourceLines(inputString,
                "Exec SQL", "x=1", ";");
    }

    @Test
    public void testStringAssignment() {
        String inputString =
                "             cmdlength = '123';     \r\n";
        expectTokensForSourceLines(inputString,
                "cmdlength", "=", "'", "123", "'", ";");
    }

    @Test
    public void testBasicFunction() {
        String inputString =
                "             cmdlength = %len(123);     \r\n";
        expectTokensForSourceLines(inputString,
                "cmdlength", "=", "%len", "(", "123", ")", ";");
    }

    @Test
    public void testBasicFunctionParms() {
        String inputString =
                "             cmdlength = %len(123:456);     \r\n";
        expectTokensForSourceLines(inputString,
                "cmdlength", "=", "%len", "(", "123", ":",
                "456", ")", ";");
    }

    @Test
    public void testNestedFunction() {
        String inputString =
                "             cmdlength = %len(%trim(cmdstring));     \r\n";
        expectTokensForSourceLines(inputString,
                "cmdlength", "=", "%len", "(", "%trim", "(",
                "cmdstring", ")", ")", ";");

    }

    @Test
    public void testMultiLine_1() {
        String inputString =
                "             cmdlength = \r\n"
                        + "               123;     \r\n";
        expectTokensForSourceLines(inputString,
                "cmdlength", "=", "123", ";");
    }

    @Test
    public void testMultiLine() {
        String inputString =
                "             cmdstring = \r\n"
                        + "              'CRTDTAARA DTAARA(QTEMP/MYTWENTY1) TYPE(*CHAR) LEN(21)';\r\n";
        expectTokensForSourceLines(inputString,
                "cmdstring", "=", "'",
                "CRTDTAARA DTAARA(QTEMP/MYTWENTY1) TYPE(*CHAR) LEN(21)", "'",
                ";");
    }

    @Test
    public void testON_ERROR() {
        String inputString =
                "             on-error; \r\n";
        expectTokensForSourceLines(inputString,
                "on-error", ";");
    }

    @Test
    public void testComparison() {
        String inputString =
                "         if %parms > 2;";
        expectTokensForSourceLines(inputString,
                "if", "%parms", ">", "2",";");
    }

    @Test
    public void testQualified() {
        String inputString =
                "        Destination = PCINFO.Destination;";
        expectTokensForSourceLines(inputString,
                "Destination", "=", "PCINFO", ".",
                "Destination",";");
    }

    @Test
    public void testIndAndBIF() {
        String inputString =
                "        *IN99 = NOT %FOUND;";
        expectTokensForSourceLines(inputString,
                "*IN99", "=", "NOT", "%FOUND",";");
    }

    @Test
    public void testCompoundIf() {
        String inputString =
                "       IF ARNTCT = 'B  '\r\n"
                        + "       OR ARNTCT = 'C  '\r\n" + "       OR ARNTCT = 'D  ';";
        expectTokensForSourceLines(inputString,
                "IF", "ARNTCT", "=", "'", "B", "'", "OR",
                "ARNTCT", "=", "'", "C", "'", "OR", "ARNTCT", "=", "'", "D",
                "'", ";");
    }

    @Test
    public void testCompoundIf2() {
        String inputString =
                "       IF ARNTCT = 'B' OR ARNTCT = 'C  ' OR ARNTCT = 'D  '\r\n"
                        + "          ;";
        expectTokensForSourceLines(inputString,
                "IF", "ARNTCT", "=", "'", "B", "'", "OR",
                "ARNTCT", "=", "'", "C", "'", "OR", "ARNTCT", "=", "'", "D",
                "'", ";");
    }

    @Test
    public void testSubst() {
        String inputString =
                "       %subst(MARK3: 1: 3) = '613';";
        expectTokensForSourceLines(inputString,
                "%subst", "(", "MARK3", ":", "1", ":", "3",
                ")", "=", "'", "613", "'", ";");

    }

    @Test
    public void testStringconcat() {
        String inputString =
                "       DATA ='Loan No:  ' + %TRIM(LOANNO);";
        expectTokensForSourceLines(inputString,
                "DATA", "=", "'", "Loan No:", "'", "+",
                "%TRIM", "(", "LOANNO", ")", ";");
    }

    @Test
    public void testMultiplyDecimal() {
        String inputString =
                "InstPct = A1IPCT * .01 * I;";
        expectTokensForFreeSnippet(inputString,
                "InstPct", "=", "A1IPCT", "*", ".01", "*", "I",
                ";");
    }

    @Test
    public void testNestedArgsExpr() {
        String inputString =
                "Temp60 = %INT((TermPrem * InstPct) + .5);";
        expectTokensForFreeSnippet(inputString,
                "Temp60", "=", "%INT", "(", "(", "TermPrem",
                "*", "InstPct", ")", "+", ".5", ")", ";");
    }

    @Test
    public void testSimpleBareBIF() {
        String inputString =
                "%INT(1.5);";
        expectTokensForFreeSnippet(inputString,
                "%INT", "(", "1.5", ")", ";");
    }

    @Test
    public void testSimpleNestedBIF() {
        String inputString =
                "%INT((1.5));";
        expectTokensForFreeSnippet(inputString,
                "%INT", "(", "(", "1.5", ")", ")", ";");
    }

    @Test
    public void testAlgebraExpr1() {
        String inputString =
                "InstallPrem = (TermPrem * InstPct) - PremApplied;";
        expectTokensForFreeSnippet(inputString,
                "InstallPrem", "=", "(", "TermPrem", "*",
                "InstPct", ")", "-", "PremApplied", ";");
    }

    @Test
    public void testAlgebraExpr2() {
        String inputString =
                "Mths2Add = Months * (I - 1);";
        expectTokensForFreeSnippet(inputString,
                "Mths2Add", "=", "Months", "*", "(", "I", "-",
                "1", ")", ";");
    }

    @Test
    public void testFreeNameContinuation() {
        String inputString =
                "dcl-s continued...\r\n"
                        + "name char(10);";
        expectTokensForFreeSnippet(inputString,
                "dcl-s", "continued", "...", "name", "char",
                "(", "10", ")", ";");
    }

    @Test
    public void testFreeNameAtSign() {
        String inputString =
                "CvtDate(ProcessDate: @DTE);";
        expectTokensForFreeSnippet(inputString,
                "CvtDate", "(", "ProcessDate", ":", "@DTE",
                ")", ";");
    }

    @Test
    public void testFreeNameLBSign() {
        String inputString =
                "#1F2CD = PolicyType;";
        expectTokensForFreeSnippet(inputString,
                "#1F2CD", "=", "PolicyType", ";");
    }

    @Test
    public void testChain() {
        String inputString =
                "chain (PolicyNumber) AR1203B;";
        expectTokensForFreeSnippet(inputString,
                "chain", "(", "PolicyNumber", ")", "AR1203B",";");
    }

    @Test
    public void testChainLiteral() {
        String inputString =
                "chain 'PolicyNumber' AR1203B;";
        expectTokensForFreeSnippet(inputString,
                "chain", "'", "PolicyNumber", "'", "AR1203B", ";");
    }

    @Test
    public void testMultNegative() {
        String inputString =
                "Days2Add = LeadDays * -1;";
        expectTokensForFreeSnippet(inputString,
                "Days2Add", "=", "LeadDays", "*", "-", "1", ";");
    }

    @Test
    public void testKeywordID() {
        String inputString =
                "IF DELETE = ' ';";
        expectTokensForFreeSnippet(inputString,
                "IF", "DELETE", "=", "'", "", "'", ";");
    }

    @Test
    public void testKeywordID2() {
        String inputString =
                "EVAL IN = INSTAL;";
        expectTokensForFreeSnippet(inputString,
                "EVAL", "IN", "=", "INSTAL", ";");
    }

    @Test
    public void testKeywordID3() {
        String inputString =
                "TEMP14 = ZLNG(IN);";
        expectTokensForFreeSnippet(inputString,
                "TEMP14", "=", "ZLNG", "(", "IN", ")", ";");
    }

    @Test
    public void testLongLineIssue() {
        String inputString =
                "       DAYS = 5;                                                               // EFT DATE";
        expectTokensForFreeSnippet(inputString,
                "DAYS", "=", "5", ";");
    }

    @Test
    public void testINDExpression() {
        String inputString =
                "       IF *IN78 = *OFF                                                          // RECORD EXISTS\r\n"
                        + "          AND MHTRPR = 'N';";
        expectTokensForFreeSnippet(inputString,
                "IF", "*IN78", "=", "*OFF", "AND", "MHTRPR",
                "=", "'", "N", "'", ";");
    }

    @Test
    public void testFreeWithTailComment() {
        String inputString =
                "if CovName = 'DWELLING';//CP & CF";
        expectTokensForFreeSnippet(inputString,
                "if", "CovName", "=", "'", "DWELLING", "'", ";");
    }

    @Test
    public void testFreeExprMixedOpsAndLiterals() {
        String inputString =
                "EVAL TEST = 'N';";
        expectTokensForFreeSnippet(inputString,
                "EVAL", "TEST", "=", "'", "N", "'", ";");
    }

    @Test
    public void testFreeLeftHandSideAssignment() {
        String inputString =
                "%SUBST(COPYTO:2:18) = ZCPY(A);";
        expectTokensForFreeSnippet(inputString,
                "%SUBST", "(", "COPYTO", ":", "2", ":", "18",
                ")", "=", "ZCPY", "(A)", ";");
    }

    @Test
    public void testFreeStringConcat() {
        String inputString =
                "DS1802 = %TRIMR(DS1802) + '-';";
        expectTokensForFreeSnippet(inputString,
                "DS1802", "=", "%TRIMR", "(", "DS1802", ")",
                "+", "'", "-", "'", ";");
    }

    @Test
    public void testFreeAssignment() {
        String inputString =
                "B1DQNB = -.25;";
        expectTokensForFreeSnippet(inputString,
                "B1DQNB", "=", "-", ".25", ";");
    }

    @Test
    public void testFreeArray() {
        String inputString =
                "ZCPY(A);";
        expectTokensForFreeSnippet(inputString,
                "ZCPY", "(A)", ";");
    }

    // TODO this should not be an OP_E
    @Test
    public void testSplatAll() {
        String inputString =
                "*in = *all'0' ;";
        expectTokensForFreeSnippet(inputString,
                "*in", "=", "*all", "'", "0", "'", ";");
    }

    @Test
    public void testForBy() {
        String inputString =
                "FOR X = 100 BY -1 TO 100;";
        expectTokensForFreeSnippet(inputString,
                "FOR", "X", "=", "100", "BY", "-", "1", "TO",
                "100", ";");
    }

    @Test
    public void testDclDs1() {
        String inputString =
                "       DCL-DS *N;\r\n"
                        + "       END-DS;";
        expectTokensForFreeSnippet(inputString,
                "DCL-DS", "*", "N", ";", "END-DS", ";");
    }

    @Test
    public void testDclDs2() {
        String inputString =
                "       DCL-DS *N;\r\n"
                        + "         ABDTFL ZONED(7);\r\n"
                        + "         ABDTFL ZONED(7);\r\n" + "       END-DS;";
        expectTokensForFreeSnippet(inputString,
                "DCL-DS", "*", "N", ";", "ABDTFL", "ZONED",
                "(", "7", ")", ";", "ABDTFL", "ZONED", "(", "7", ")", ";",
                "END-DS", ";");
    }

    @Test
    public void testDclDsShort() {
        String inputString =
                "       DCL-DS prtDs LEN(132) END-DS;";
        expectTokensForFreeSnippet(inputString,
                "DCL-DS", "prtDs", "LEN", "(", "132", ")",
                "END-DS", ";");
    }

    @Test
    public void testPlusEquals() {
        String inputString =
                "B1SQ## += 1;";
        expectTokensForFreeSnippet(inputString,
                "B1SQ##", "+=", "1", ";");
    }

    @Test
    public void testTest() {
        String inputString =
                "TEST(De) *iso0 LicIssueDate ;";
        expectTokensForFreeSnippet(inputString,
                "TEST", "(De)", "*iso0", "LicIssueDate", ";");
    }

    @Test
    public void testXMLId() {
        String inputString =
                "EndNbr = Endorsements.Endorsement(EndCnt).EndorsementNumber;";
        expectTokensForFreeSnippet(inputString,
                "EndNbr", "=", "Endorsements", ".",
                "Endorsement", "(", "EndCnt", ")", ".", "EndorsementNumber",
                ";");
    }

    @Test
    public void testWithCrazyComment() {
        String inputString =
                "           IF SCTRAN = '1'\r\n"
                        + " 1  �        //DLT       SCTRAN    OREQ '3'\r\n"
                        + "             OR SCTRAN = '6';";
        expectTokensForSourceLines(inputString,
                "IF", "SCTRAN", "=", "'", "1", "'", "OR",
                "SCTRAN", "=", "'", "6", "'", ";");
    }

    @Test
    public void testPR() {
        String inputString =
                "    DCL-PR CLR014 EXTPGM;\r\n"
                        + "    TRNSDT_ LIKE(TRNSDT);\r\n"
                        + "    END-PR;";
        expectTokensForFreeSnippet(inputString,
                "DCL-PR", "CLR014", "EXTPGM", ";", "TRNSDT_",
                "LIKE", "(", "TRNSDT", ")", ";", "END-PR", ";");
    }

    @Test
    public void testPI() {
        String inputString =
                "    DCL-PI CLR014;\r\n"
                        + "TRNSDT PACKED(7);\r\n"
                        + "END-PI;";
        expectTokensForFreeSnippet(inputString,
                "DCL-PI", "CLR014", ";", "TRNSDT", "PACKED",
                "(", "7", ")", ";", "END-PI", ";");
    }

    @Test
    public void testConstant() {
        String inputString =
                "DCL-C CRLF CONST(X'0d25');";
        expectTokensForFreeSnippet(inputString,
                "DCL-C", "CRLF", "CONST", "(", "X'", "0d25",
                "'", ")", ";");
    }

    @Test
    public void testExpressionAsterisk() {
        String inputString =
                "FROMcymd = ((Year)*10000);";
        expectTokensForFreeSnippet(inputString,
                "FROMcymd", "=", "(", "(", "Year", ")", "*",
                "10000", ")", ";");
    }

    @Test
    public void testDateLiteral() {
        String inputString =
                "IF OVTRDZ = d'0001-01-01';   // ACTIVE";
        expectTokensForFreeSnippet(inputString,
                "IF", "OVTRDZ", "=", "d'", "0001-01-01", "'",
                ";");
    }

    @Test
    public void testOpt() {
        String inputString =
                " CTL-OPT\r\n" + "DATEDIT(*YMD);";
        expectTokensForFreeSnippet(inputString,
                "CTL-OPT", "DATEDIT", "(", "*YMD", ")", ";");
    }

    @Test
    public void testDCL_DS() {
        String inputString =
                " DCL-DS *N;\r\n" + "END-DS;";
        expectTokensForFreeSnippet(inputString,
                "DCL-DS", "*", "N", ";", "END-DS", ";");
    }

    @Test
    public void testDCL_DS_Err() {
        String inputString = " DCL-DS * N;\r\n" + "END-DS;";
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.parseInput(inputString, errors);
        assertTrue("Errors expected", errors.size() > 0);

    }

    @Test
    public void testMultiply() {
        String inputString = " 1 * N;";
        expectTokensForFreeSnippet(inputString, "1", "*", "N", ";");
    }

    @Test
    public void testMultiply2() {
        String inputString = " 1*N;";
        expectTokensForFreeSnippet(inputString, "1", "*", "N", ";");
    }

    @Test
    public void testEvalFree_OptionalContinuation() {
        String inputString =
                "if BDEBRYR = *zero and ADEBRYR <> *zero or \r\n"
                        + "BDEBMTH = *zero and ADEBMTH <> *zero or \r\n"
                        + "BDEBDAY = *zero and ADEBDAY <> *zero;";
        expectTokensForFreeSnippet(inputString,
                "if", "BDEBRYR", "=", "*zero", "and",
                "ADEBRYR", "<>", "*zero", "or", "BDEBMTH", "=", "*zero", "and",
                "ADEBMTH", "<>", "*zero", "or", "BDEBDAY", "=", "*zero", "and",
                "ADEBDAY", "<>", "*zero", ";");
    }

    @Test
    public void testEvalFree1() {
        String inputString =
                "DCL-PI PPR751G;\r\n"
                        + "PAR_ENDMTH PACKED(7);                                                  //            ER\r\n"
                        + "TOADDR_PARM LIKE(TOADDR);\r\n" + "\r\n" + "END-PI;";
        expectTokensForFreeSnippet(inputString,
                "DCL-PI", "PPR751G", ";", "PAR_ENDMTH",
                "PACKED", "(", "7", ")", ";", "TOADDR_PARM", "LIKE", "(",
                "TOADDR", ")", ";", "END-PI", ";");
    }

}