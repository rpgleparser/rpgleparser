package org.rpgleparser;

import static org.junit.Assert.assertTrue;
import static org.rpgleparser.utils.TestUtils.expectTokensForFreeSnippet;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

public class TestFREE {

    @Test
    public void testAlgebraExpr1() {
        final String inputString = "InstallPrem = (TermPrem * InstPct) - PremApplied;";
        expectTokensForFreeSnippet(inputString, "InstallPrem", "=", "(", "TermPrem", "*", "InstPct", ")", "-",
                "PremApplied", ";");
    }

    @Test
    public void testAlgebraExpr2() {
        final String inputString = "Mths2Add = Months * (I - 1);";
        expectTokensForFreeSnippet(inputString, "Mths2Add", "=", "Months", "*", "(", "I", "-", "1", ")", ";");
    }

    @Test
    public void testAssignment() {
        final String inputString = "             cmdlength = 123;     \r\n";
        expectTokensForSourceLines(inputString, "cmdlength", "=", "123", ";");
    }

    @Test
    public void testBasicFunction() {
        final String inputString = "             cmdlength = %len(123);     \r\n";
        expectTokensForSourceLines(inputString, "cmdlength", "=", "%len", "(", "123", ")", ";");
    }

    @Test
    public void testBasicFunctionParms() {
        final String inputString = "             cmdlength = %len(123:*MAX);     \r\n";
        expectTokensForSourceLines(inputString, "cmdlength", "=", "%len", "(", "123", ":", "*MAX", ")", ";");
    }

    @Test
    public void testChain() {
        final String inputString = "chain (PolicyNumber) AR1203B;";
        expectTokensForFreeSnippet(inputString, "chain", "(", "PolicyNumber", ")", "AR1203B", ";");
    }

    @Test
    public void testChainLiteral() {
        final String inputString = "chain 'PolicyNumber' AR1203B;";
        expectTokensForFreeSnippet(inputString, "chain", "'", "PolicyNumber", "'", "AR1203B", ";");
    }

    @Test
    public void testComparison() {
        final String inputString = "         if %parms > 2;\r\n" + "        endif;";
        expectTokensForSourceLines(inputString, "if", "%parms", ">", "2", ";", "endif", ";");
    }

    @Test
    public void testCompoundIf() {
        final String inputString = "       IF ARNTCT = 'B  '\r\n" + "       OR ARNTCT = 'C  '\r\n"
                + "       OR ARNTCT = 'D  ';\r\n" + "       endif;";
        expectTokensForSourceLines(inputString, "IF", "ARNTCT", "=", "'", "B", "'", "OR", "ARNTCT", "=", "'", "C", "'",
                "OR", "ARNTCT", "=", "'", "D", "'", ";", "endif", ";");
    }

    @Test
    public void testCompoundIf2() {
        final String inputString = "       IF ARNTCT = 'B' OR ARNTCT = 'C  ' OR ARNTCT = 'D  ';\r\n"
                + "          ENDIF;";
        expectTokensForSourceLines(inputString, "IF", "ARNTCT", "=", "'", "B", "'", "OR", "ARNTCT", "=", "'", "C", "'",
                "OR", "ARNTCT", "=", "'", "D", "'", ";", "ENDIF", ";");
    }

    // @Test
    // public void testON_ERROR() {
    // String inputString =
    // " on-error; \r\n";
    // expectTokensForSourceLines(inputString,
    // "on-error", ";");
    // }

    @Test
    public void testConstant() {
        final String inputString = "DCL-C CRLF CONST(X'0d25');";
        expectTokensForFreeSnippet(inputString, "DCL-C", "CRLF", "CONST", "(", "X'", "0d25", "'", ")", ";");
    }

    @Test
    public void testDateLiteral() {
        final String inputString = "IF OVTRDZ = d'0001-01-01';   // ACTIVE\r\n" + "endif;";
        expectTokensForFreeSnippet(inputString, "IF", "OVTRDZ", "=", "d'", "0001-01-01", "'", ";", "endif", ";");
    }

    @Test
    public void testDCL_DS() {
        final String inputString = " DCL-DS *N;\r\n" + "END-DS;";
        expectTokensForFreeSnippet(inputString, "DCL-DS", "*", "N", ";", "END-DS", ";");
    }

    @Test
    public void testDCL_DS_Err() {
        final String inputString = " DCL-DS * N;\r\n" + "END-DS;";
        final List<String> errors = new ArrayList<>();
        TestUtils.getParsedTokens(inputString, errors);
        assertTrue("Errors expected", errors.size() > 0);
    }

    @Test
    public void testDclDs1() {
        final String inputString = "       DCL-DS *N;\r\n" + "       END-DS;";
        expectTokensForFreeSnippet(inputString, "DCL-DS", "*", "N", ";", "END-DS", ";");
    }

    @Test
    public void testDclDs2() {
        final String inputString = "       DCL-DS *N;\r\n" + "         ABDTFL ZONED(7);\r\n"
                + "         ABDTFL ZONED(7);\r\n" + "       END-DS;";
        expectTokensForFreeSnippet(inputString, "DCL-DS", "*", "N", ";", "ABDTFL", "ZONED", "(", "7", ")", ";",
                "ABDTFL", "ZONED", "(", "7", ")", ";", "END-DS", ";");
    }

    @Test
    public void testDclDsShort() {
        final String inputString = "       DCL-DS prtDs LEN(132) END-DS;";
        expectTokensForFreeSnippet(inputString, "DCL-DS", "prtDs", "LEN", "(", "132", ")", "END-DS", ";");
    }

    @Test
    public void testEvalFree_OptionalContinuation() {
        final String inputString = "if BDEBRYR = *zero and ADEBRYR <> *zero or \r\n"
                + "BDEBMTH = *zero and ADEBMTH <> *zero or \r\n" + "BDEBDAY = *zero and ADEBDAY <> *zero;\r\n"
                + "endif;";
        expectTokensForFreeSnippet(inputString, "if", "BDEBRYR", "=", "*zero", "and", "ADEBRYR", "<>", "*zero", "or",
                "BDEBMTH", "=", "*zero", "and", "ADEBMTH", "<>", "*zero", "or", "BDEBDAY", "=", "*zero", "and",
                "ADEBDAY", "<>", "*zero", ";", "endif", ";");
    }

    @Test
    public void testEvalFree1() {
        final String inputString = "DCL-PI PPR751G;\r\n"
                + "PAR_ENDMTH PACKED(7);                                                  //            ER\r\n"
                + "TOADDR_PARM LIKE(TOADDR);\r\n" + "\r\n" + "END-PI;";
        expectTokensForFreeSnippet(inputString, "DCL-PI", "PPR751G", ";", "PAR_ENDMTH", "PACKED", "(", "7", ")", ";",
                "TOADDR_PARM", "LIKE", "(", "TOADDR", ")", ";", "END-PI", ";");
    }

    @Test
    public void testExecSQL() {
        final String inputString = "         Exec SQL\r\n"
                + "         set :FieldAll =coalesce( getProcenter(:PolicyNbr,\r\n" + "         :DateCode, ''),'');\r\n";
        expectTokensForSourceLines(inputString, "Exec SQL", "set :FieldAll =coalesce( getProcenter(:PolicyNbr,",
                ":DateCode, ''),'')", ";");
    }

    @Test
    public void testExecSQL1() {
        final String inputString = "         Exec SQL x=1;\r\n";
        expectTokensForSourceLines(inputString, "Exec SQL", "x=1", ";");
    }

    @Test
    public void testExpressionAsterisk() {
        final String inputString = "FROMcymd = ((Year)*10000);";
        expectTokensForFreeSnippet(inputString, "FROMcymd", "=", "(", "(", "Year", ")", "*", "10000", ")", ";");
    }

    @Test
    public void testForBy() {
        final String inputString = "FOR X = 100 BY -1 TO 100;\r\n ENDFOR;";
        expectTokensForFreeSnippet(inputString, "FOR", "X", "=", "100", "BY", "-", "1", "TO", "100", ";", "ENDFOR",
                ";");
    }

    @Test
    public void testFreeArray() {
        final String inputString = "ZCPY(A);";
        expectTokensForFreeSnippet(inputString, "ZCPY", "(", "A", ")", ";");
    }

    @Test
    public void testFreeAssignment() {
        final String inputString = "B1DQNB = -.25;";
        expectTokensForFreeSnippet(inputString, "B1DQNB", "=", "-", ".25", ";");
    }

    @Test
    public void testFreeExprMixedOpsAndLiterals() {
        final String inputString = "EVAL TEST = 'N';";
        expectTokensForFreeSnippet(inputString, "EVAL", "TEST", "=", "'", "N", "'", ";");
    }

    @Test
    public void testFreeLeftHandSideAssignment() {
        final String inputString = "%SUBST(COPYTO:2:18) = ZCPY(A);";
        expectTokensForFreeSnippet(inputString, "%SUBST", "(", "COPYTO", ":", "2", ":", "18", ")", "=", "ZCPY", "(",
                "A", ")", ";");
    }

    @Test
    public void testFreeNameAtSign() {
        final String inputString = "CvtDate(ProcessDate: @DTE);";
        expectTokensForFreeSnippet(inputString, "CvtDate", "(", "ProcessDate", ":", "@DTE", ")", ";");
    }

    @Test
    public void testFreeNameContinuation() {
        final String inputString = "dcl-s continued...\r\n" + "name char(10);";
        expectTokensForFreeSnippet(inputString, "dcl-s", "continued", "...", "name", "char", "(", "10", ")", ";");
    }

    @Test
    public void testFreeNameLBSign() {
        final String inputString = "#1F2CD = PolicyType;";
        expectTokensForFreeSnippet(inputString, "#1F2CD", "=", "PolicyType", ";");
    }

    @Test
    public void testFreeStringConcat() {
        final String inputString = "DS1802 = %TRIMR(DS1802) + '-';";
        expectTokensForFreeSnippet(inputString, "DS1802", "=", "%TRIMR", "(", "DS1802", ")", "+", "'", "-", "'", ";");
    }

    @Test
    public void testFreeWithTailComment() {
        final String inputString = "if CovName = 'DWELLING';//CP & CF\r\n" + "endif;";
        expectTokensForFreeSnippet(inputString, "if", "CovName", "=", "'", "DWELLING", "'", ";", "endif", ";");
    }

    @Test
    public void testIndAndBIF() {
        final String inputString = "        *IN99 = NOT %FOUND;";
        expectTokensForSourceLines(inputString, "*IN99", "=", "NOT", "%FOUND", ";");
    }

    @Test
    public void testINDExpression() {
        final String inputString = "       IF *IN78 = *OFF                                                          // RECORD EXISTS\r\n"
                + "          AND MHTRPR = 'N';\r\n" + "          endif;";
        expectTokensForFreeSnippet(inputString, "IF", "*IN78", "=", "*OFF", "AND", "MHTRPR", "=", "'", "N", "'", ";",
                "endif", ";");
    }

    @Test
    public void testKeywordID() {
        final String inputString = "IF DELETE = ' ';\r\n" + "endif;";
        expectTokensForFreeSnippet(inputString, "IF", "DELETE", "=", "'", "", "'", ";", "endif", ";");
    }

    @Test
    public void testKeywordID2() {
        final String inputString = "EVAL IN = INSTAL;";
        expectTokensForFreeSnippet(inputString, "EVAL", "IN", "=", "INSTAL", ";");
    }

    @Test
    public void testKeywordID3() {
        final String inputString = "TEMP14 = ZLNG(IN);";
        expectTokensForFreeSnippet(inputString, "TEMP14", "=", "ZLNG", "(", "IN", ")", ";");
    }

    @Test
    public void testLongLineIssue() {
        final String inputString = "       DAYS = 5;                                                               // EFT DATE";
        expectTokensForFreeSnippet(inputString, "DAYS", "=", "5", ";");
    }

    @Test
    public void testMultiLine() {
        final String inputString = "             cmdstring = \r\n"
                + "              'CRTDTAARA DTAARA(QTEMP/MYTWENTY1) TYPE(*CHAR) LEN(21)';\r\n";
        expectTokensForSourceLines(inputString, "cmdstring", "=", "'",
                "CRTDTAARA DTAARA(QTEMP/MYTWENTY1) TYPE(*CHAR) LEN(21)", "'", ";");
    }

    @Test
    public void testMultiLine_1() {
        final String inputString = "             cmdlength = \r\n" + "               123;     \r\n";
        expectTokensForSourceLines(inputString, "cmdlength", "=", "123", ";");
    }

    @Test
    public void testMultiply() {
        final String inputString = " 1 * N;";
        expectTokensForFreeSnippet(inputString, "1", "*", "N", ";");
    }

    @Test
    public void testMultiply2() {
        final String inputString = " 1*N;";
        expectTokensForFreeSnippet(inputString, "1", "*", "N", ";");
    }

    @Test
    public void testMultiplyDecimal() {
        final String inputString = "InstPct = A1IPCT * .01 * I;";
        expectTokensForFreeSnippet(inputString, "InstPct", "=", "A1IPCT", "*", ".01", "*", "I", ";");
    }

    @Test
    public void testMultNegative() {
        final String inputString = "Days2Add = LeadDays * -1;";
        expectTokensForFreeSnippet(inputString, "Days2Add", "=", "LeadDays", "*", "-", "1", ";");
    }

    @Test
    public void testNestedArgsExpr() {
        final String inputString = "Temp60 = %INT((TermPrem * InstPct) + .5);";
        expectTokensForFreeSnippet(inputString, "Temp60", "=", "%INT", "(", "(", "TermPrem", "*", "InstPct", ")", "+",
                ".5", ")", ";");
    }

    @Test
    public void testNestedFunction() {
        final String inputString = "             cmdlength = %len(%trim(cmdstring));     \r\n";
        expectTokensForSourceLines(inputString, "cmdlength", "=", "%len", "(", "%trim", "(", "cmdstring", ")", ")",
                ";");

    }

    @Test
    public void testOpt() {
        final String inputString = " CTL-OPT\r\n" + "DATEDIT(*YMD);";
        expectTokensForFreeSnippet(inputString, "CTL-OPT", "DATEDIT", "(", "*YMD", ")", ";");
    }

    @Test
    public void testPlusEquals() {
        final String inputString = "B1SQ## += 1;";
        expectTokensForFreeSnippet(inputString, "B1SQ##", "+=", "1", ";");
    }

    @Test
    public void testQualified() {
        final String inputString = "        Destination = PCINFO.Destination;";
        expectTokensForSourceLines(inputString, "Destination", "=", "PCINFO", ".", "Destination", ";");
    }

    @Test
    public void testReset() {
        final String inputString = "         reset itemPromptBlock(*);";
        expectTokensForSourceLines(inputString, "reset", "itemPromptBlock", "(", "*", ")", ";");
    }

    @Test
    public void testSimpleBareBIF() {
        final String inputString = "%INT(1.5);";
        expectTokensForFreeSnippet(inputString, "%INT", "(", "1.5", ")", ";");
    }

    @Test
    public void testSimpleNestedBIF() {
        final String inputString = "%INT((1.5));";
        expectTokensForFreeSnippet(inputString, "%INT", "(", "(", "1.5", ")", ")", ";");
    }

    // TODO this should not be an OP_E
    @Test
    public void testSplatAll() {
        final String inputString = "*in = *all'0' ;";
        expectTokensForFreeSnippet(inputString, "*in", "=", "*all", "'", "0", "'", ";");
    }

    @Test
    public void testStringAssignment() {
        final String inputString = "             cmdlength = '123';     \r\n";
        expectTokensForSourceLines(inputString, "cmdlength", "=", "'", "123", "'", ";");
    }

    @Test
    public void testStringconcat() {
        final String inputString = "       DATA ='Loan No:  ' + %TRIM(LOANNO);";
        expectTokensForSourceLines(inputString, "DATA", "=", "'", "Loan No:", "'", "+", "%TRIM", "(", "LOANNO", ")",
                ";");
    }

    @Test
    public void testSubst() {
        final String inputString = "       %subst(MARK3: 1: 3) = '613';";
        expectTokensForSourceLines(inputString, "%subst", "(", "MARK3", ":", "1", ":", "3", ")", "=", "'", "613", "'",
                ";");

    }

    @Test
    public void testTest() {
        final String inputString = "TEST(De) *iso0 LicIssueDate ;";
        expectTokensForFreeSnippet(inputString, "TEST", "(", "D", "e", ")", "*iso0", "LicIssueDate", ";");
    }

    @Test
    public void testWithCrazyComment() {
        final String inputString = "           IF SCTRAN = '1'\r\n" + " 1  �        //DLT       SCTRAN    OREQ '3'\r\n"
                + "             OR SCTRAN = '6';\r\n" + "       endif;";
        expectTokensForSourceLines(inputString, "IF", "SCTRAN", "=", "'", "1", "'", "OR", "SCTRAN", "=", "'", "6", "'",
                ";", "endif", ";");
    }

    @Test
    public void testXMLId() {
        final String inputString = "EndNbr = Endorsements.Endorsement(EndCnt).EndorsementNumber;";
        expectTokensForFreeSnippet(inputString, "EndNbr", "=", "Endorsements", ".", "Endorsement", "(", "EndCnt", ")",
                ".", "EndorsementNumber", ";");
    }

    // @Test
    // public void testArithmeticTree() {
    // String inputString =
    // "i = 1 + 2;";
    // expectTreeForFreeSnippet(inputString,
    // "(r (statement (free (baseExpression (op (op_eval (assignmentExpression
    // (expression (identifier (free_identifier i))) = (expression (expression
    // (number 1)) (arithmeticalOperator +) (expression (number 2))))))) ;)))");
    // }
}