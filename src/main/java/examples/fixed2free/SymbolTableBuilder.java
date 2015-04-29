package examples.fixed2free;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgLexer;
// Import the contexts related to operators that can have a result field with length & dec pos
import org.rpgleparser.RpgParser.CsADDContext;
import org.rpgleparser.RpgParser.CsBITOFFContext;
import org.rpgleparser.RpgParser.CsBITONContext;
import org.rpgleparser.RpgParser.CsCATContext;
import org.rpgleparser.RpgParser.CsCHECKContext;
import org.rpgleparser.RpgParser.CsCHECKRContext;
import org.rpgleparser.RpgParser.CsCLEARContext;
import org.rpgleparser.RpgParser.CsDEFINEContext;
import org.rpgleparser.RpgParser.CsDIVContext;
import org.rpgleparser.RpgParser.CsDOContext;
import org.rpgleparser.RpgParser.CsDSPLYContext;
import org.rpgleparser.RpgParser.CsEXTRCTContext;
import org.rpgleparser.RpgParser.CsKFLDContext;
import org.rpgleparser.RpgParser.CsMHHZOContext;
import org.rpgleparser.RpgParser.CsMHLZOContext;
import org.rpgleparser.RpgParser.CsMLHZOContext;
import org.rpgleparser.RpgParser.CsMLLZOContext;
import org.rpgleparser.RpgParser.CsMOVEContext;
import org.rpgleparser.RpgParser.CsMOVELContext;
import org.rpgleparser.RpgParser.CsMULTContext;
import org.rpgleparser.RpgParser.CsMVRContext;
import org.rpgleparser.RpgParser.CsOCCURContext;
import org.rpgleparser.RpgParser.CsPARMContext;
import org.rpgleparser.RpgParser.CsRESETContext;
import org.rpgleparser.RpgParser.CsSCANContext;
import org.rpgleparser.RpgParser.CsSQRTContext;
import org.rpgleparser.RpgParser.CsSUBContext;
import org.rpgleparser.RpgParser.CsSUBSTContext;
import org.rpgleparser.RpgParser.CsXFOOTContext;
import org.rpgleparser.RpgParser.CsXLATEContext;
import org.rpgleparser.RpgParser.CsZ_ADDContext;
import org.rpgleparser.RpgParser.CsZ_SUBContext;
import org.rpgleparser.RpgParser.Cspec_fixedContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;
import org.rpgleparser.RpgParser.DirectiveContext;
import org.rpgleparser.RpgParser.DspecContext;
import org.rpgleparser.RpgParser.Dspec_fixedContext;
import org.rpgleparser.RpgParser.Fspec_fixedContext;
import org.rpgleparser.RpgParser.Hspec_fixedContext;
import org.rpgleparser.RpgParser.Ispec_fixedContext;
import org.rpgleparser.RpgParser.Ospec_fixedContext;
import org.rpgleparser.RpgParser.ProcedureContext;
import org.rpgleparser.RpgParser.ResultTypeContext;

import examples.fixed2free.integration.ColumnInfo;
import examples.fixed2free.integration.MockTableInfoProvider;
import examples.fixed2free.integration.TableInfoProvider;
import examples.loggingListener.LoggingListener;

public class SymbolTableBuilder extends LoggingListener {
	private Scope currentScope;
	private Scope global;
	private String lastSpec = "";
	private SymbolTable st;
	private CommonTokenStream ts;
	private Vocabulary voc;
	private TableInfoProvider tip;
	
	public SymbolTableBuilder(RpgLexer lex, CommonTokenStream toks) {
		voc = lex.getVocabulary();
		ts = toks;
		st = new SymbolTable();
		global = st.getAScope(Scope.GLOBAL);
		currentScope = global;
		tip = new MockTableInfoProvider();
	}

	public List<String> collectOutput() {
		ArrayList<String> result = new ArrayList<String>();
		List<Scope> temp2 = st.getAllScopes();
		for (Scope sc : temp2){
			result.add("Scope " + sc.getKey());
			Collection<Symbol> c = st.getAllSymbolsFromScope(sc);
			for (Symbol s : c){
				result.add(s.toString());
			}
		}
		return result;
	}

	private void debugContext(ParserRuleContext ctx) {
		List<CommonToken> myList = getTheTokens(ctx);
		for (CommonToken ct : myList) {
			System.err.println(ct.getTokenIndex() + "\t"
					+ voc.getDisplayName(ct.getType()).trim() // + "\t" +
																// ct.getText()
					+ "\t @ " + ct.getCharPositionInLine());
		}
	}

	@Override
	public void enterCspec_fixed(Cspec_fixedContext ctx) {
		super.enterCspec_fixed(ctx);
		lastSpec = "C";
	}

	@Override
	public void enterDirective(DirectiveContext ctx) {
		super.enterDirective(ctx);
	}

	
	@Override
	public void enterDspec_fixed(Dspec_fixedContext ctx) {
		super.enterDspec_fixed(ctx);
		lastSpec = "D";
		String dsType = ctx.DATA_STRUCTURE_TYPE().getText().trim();
		String rpgDataType = ctx.DATA_TYPE().getText().trim();
		String decimalPositions = ctx.DECIMAL_POSITIONS().getText().trim();
		String defType = ctx.DEF_TYPE().getText().trim().trim();
		String dataStructureName = ctx.ds_name().getText().trim();
		String externalDescription = ctx.EXTERNAL_DESCRIPTION().getText().trim();
		String fromPosition = ctx.FROM_POSITION().getText().trim();
		String keywords = null;//ctx.KEYWORDS().getText().trim(); //todo
		String toPosition = ctx.TO_POSITION().getText().trim();
		Symbol theSym = new Symbol();
		// Definition type
		setDefinitionType(defType, keywords, theSym);
		theSym.setName(dataStructureName);
		theSym.addAttribute(Symbol.CAT_DECIMAL_POSITIONS, decimalPositions);
		theSym.addAttribute(Symbol.CAT_SYMBOL_ORIGIN, Symbol.SO_D_SPECS);
		setLength(fromPosition, toPosition, theSym);
		setDataType(rpgDataType, theSym, keywords);
		st.addSymbolToScope(currentScope, theSym);
		
	}

	@Override
	public void enterFspec_fixed(Fspec_fixedContext ctx) {
		super.enterFspec_fixed(ctx);
		lastSpec = "F";
		if (ctx.FS_Format().getText().trim().equalsIgnoreCase("E")){
			String fileName = ctx.FS_RecordName().getText().trim();
			List<ColumnInfo> temp = tip.getColumns(fileName, "*LIBL");
			//TODO
			String keywords = ""; //ctx.FS_Keywords().getText().toLowerCase();
			if (keywords.contains("rename(")){
				int startpos = keywords.indexOf("rename(");
				int endpos = keywords.indexOf(')', startpos);
				String tempx = keywords.substring(startpos, endpos);
				String[] parts = tempx.split("[(:)]");
			}
			if (temp != null){
				for (ColumnInfo ci : temp){
					Symbol theSym = new Symbol();
					// Definition type
					theSym.setName(ci.getColumnName());
					Symbol.sqlAttr2rpg(ci, theSym);
					theSym.addAttribute(Symbol.CAT_SYMBOL_ORIGIN, Symbol.SO_EXTERNAL_FILE_DESCRIPTION);
					theSym.addAttribute(Symbol.CAT_TABLE_NAME, fileName);
					st.addSymbolToScope(currentScope, theSym);
				}
				
			}
		}
		
	}

	@Override
	public void enterHspec_fixed(Hspec_fixedContext ctx) {
		super.enterHspec_fixed(ctx);
		lastSpec = "H";
	}

	@Override
	public void enterIspec_fixed(Ispec_fixedContext ctx) {
		super.enterIspec_fixed(ctx);
		lastSpec = "I";
		//TODO fill in the variables from i-Specs
		
	}

	@Override
	public void enterOspec_fixed(Ospec_fixedContext ctx) {
		super.enterOspec_fixed(ctx);
		lastSpec = "O";
	}

	@Override
	public void enterProcedure(ProcedureContext ctx) {
		super.enterProcedure(ctx);
		currentScope = st.getAScope( ctx.beginProcedure().freeBeginProcedure().DS_ProcedureStart().getText());
//		debugContext(ctx);
	}

	@Override
	public void exitProcedure(ProcedureContext ctx) {
		super.exitProcedure(ctx);
		
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
	public SymbolTable getSymbolTable() {
		return st;
	}

	private List<CommonToken> getTheTokens(ParserRuleContext ctx) {
		List<CommonToken> myList = new ArrayList<CommonToken>();
		fillTokenList(ctx, myList);
		return myList;
	}

	private void setDataType(String rpgDataType, Symbol theSym, String keywords) {
		if (rpgDataType.equalsIgnoreCase("A")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_ALPHANUM);
		} else if (rpgDataType.equalsIgnoreCase("B")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_BINARY);
		} else if (rpgDataType.equalsIgnoreCase("C")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_UCS2);
		} else if (rpgDataType.equalsIgnoreCase("D")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_DATE);
		} else if (rpgDataType.equalsIgnoreCase("F")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_FLOAT);
		} else if (rpgDataType.equalsIgnoreCase("G")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_GRAPHIC);
		} else if (rpgDataType.equalsIgnoreCase("I")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_INTEGER);
		} else if (rpgDataType.equalsIgnoreCase("N")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_INDICATOR);
		} else if (rpgDataType.equalsIgnoreCase("O")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_OBJECT);
		} else if (rpgDataType.equalsIgnoreCase("P")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_PACKED);
		} else if (rpgDataType.equalsIgnoreCase("S")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_ZONED);
		} else if (rpgDataType.equalsIgnoreCase("T")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_TIME);
		} else if (rpgDataType.equalsIgnoreCase("U")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_UNSIGNED);
		} else if (rpgDataType.equalsIgnoreCase("Z")){
			theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_TIMESTAMP);
		} else if (rpgDataType.equalsIgnoreCase("*")){
			if (keywords.toUpperCase().contains("%PADDR")){
				theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_PROC_POINTER);
			} else {
				theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_POINTER);
			}
		} else {
			// If we get here then the type is not specified and we need to use 
			// the logic from the manual
			String deftype = theSym.getAnAttribute(Symbol.CAT_DEFINITION_TYPE);
			String decimals = theSym.getAnAttribute(Symbol.CAT_DECIMAL_POSITIONS);
			if (deftype.equals(Symbol.DF_SUBFIELD)){
				if (decimals == null || decimals.isEmpty()){
					theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_ALPHANUM);
				} else {
					theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_ZONED);
				}
			} else {
				if (decimals == null || decimals.isEmpty()){
					theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_ALPHANUM);
				} else {
					theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_PACKED);
				}
				
			}
		}

	}

	private void setDefinitionType(String defType, String keywords,
			Symbol theSym) {
		if (defType.equalsIgnoreCase("S")){
			if (keywords.contains("DIM(")){
				theSym.addAttribute(Symbol.CAT_DEFINITION_TYPE, Symbol.DF_ARRAY);
			} else {
				theSym.addAttribute(Symbol.CAT_DEFINITION_TYPE, Symbol.DF_STANDALONE);
			}
			
		} else if (defType.equalsIgnoreCase("DS")){
			if (keywords.contains("DIM(")){
				theSym.addAttribute(Symbol.CAT_DEFINITION_TYPE, Symbol.DF_MULTIPLE_OCCURANCE_DATA_STRUCTURE);
			} else {
				theSym.addAttribute(Symbol.CAT_DEFINITION_TYPE, Symbol.DF_DATA_STRUCTURE);
			}
		} else {
			// Not a standalone field and not a datastructure so must be a 
			// data structure subfield
			theSym.addAttribute(Symbol.CAT_DEFINITION_TYPE, Symbol.DF_SUBFIELD);
		}
	}

	private void setLength(String fromPosition, String toPosition, Symbol theSym) {
		if (fromPosition != null && fromPosition.length() > 0){
			int fromInt = Integer.parseInt(fromPosition);
			int toInt = Integer.parseInt(toPosition);
			int totalLength = toInt - fromInt + 1;
			String tl = Integer.toString(totalLength);
			theSym.addAttribute(Symbol.CAT_LENGTH, tl);
		} else {
			theSym.addAttribute(Symbol.CAT_LENGTH, toPosition);
		}
	}

	@Override
	public void enterDspec(DspecContext ctx) {
		super.enterDspec(ctx);
		lastSpec = "D";
		String dsType = ctx.DATA_STRUCTURE_TYPE().getText().trim();
		String rpgDataType = ctx.DATA_TYPE().getText().trim();
		String decimalPositions = ctx.DECIMAL_POSITIONS().getText().trim();
		String defType = ctx.DEF_TYPE_S().getText().trim();
		String dataStructureName = ctx.ds_name().getText().trim();
		String externalDescription = ctx.EXTERNAL_DESCRIPTION().getText().trim();
		String fromPosition = ctx.FROM_POSITION().getText().trim();
		String keywords = ctx.keyword().toString();//ctx.KEYWORDS().getText().trim(); //todo
		String toPosition = ctx.TO_POSITION().getText().trim();
		Symbol theSym = new Symbol();
		// Definition type
		setDefinitionType(defType, keywords, theSym);
		theSym.setName(dataStructureName);
		theSym.addAttribute(Symbol.CAT_DECIMAL_POSITIONS, decimalPositions);
		theSym.addAttribute(Symbol.CAT_SYMBOL_ORIGIN, Symbol.SO_D_SPECS);
		setLength(fromPosition, toPosition, theSym);
		setDataType(rpgDataType, theSym, keywords);
		st.addSymbolToScope(currentScope, theSym);

	}

	@Override
	public void enterCsADD(CsADDContext ctx) {
		super.enterCsADD(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsBITOFF(CsBITOFFContext ctx) {
		super.enterCsBITOFF(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsBITON(CsBITONContext ctx) {
		super.enterCsBITON(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsCAT(CsCATContext ctx) {
		super.enterCsCAT(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsCHECK(CsCHECKContext ctx) {
		super.enterCsCHECK(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsCHECKR(CsCHECKRContext ctx) {
		super.enterCsCHECKR(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsCLEAR(CsCLEARContext ctx) {
		super.enterCsCLEAR(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsDEFINE(CsDEFINEContext ctx) {
		super.enterCsDEFINE(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsDIV(CsDIVContext ctx) {
		super.enterCsDIV(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsDO(CsDOContext ctx) {
		super.enterCsDO(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsDSPLY(CsDSPLYContext ctx) {
		super.enterCsDSPLY(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsEXTRCT(CsEXTRCTContext ctx) {
		super.enterCsEXTRCT(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsKFLD(CsKFLDContext ctx) {
		super.enterCsKFLD(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsMHHZO(CsMHHZOContext ctx) {
		super.enterCsMHHZO(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsMHLZO(CsMHLZOContext ctx) {
		super.enterCsMHLZO(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsMLHZO(CsMLHZOContext ctx) {
		super.enterCsMLHZO(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsMLLZO(CsMLLZOContext ctx) {
		super.enterCsMLLZO(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsMOVE(CsMOVEContext ctx) {
		super.enterCsMOVE(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsMOVEL(CsMOVELContext ctx) {
		super.enterCsMOVEL(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsMULT(CsMULTContext ctx) {
		super.enterCsMULT(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsMVR(CsMVRContext ctx) {
		super.enterCsMVR(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsOCCUR(CsOCCURContext ctx) {
		super.enterCsOCCUR(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsPARM(CsPARMContext ctx) {
		super.enterCsPARM(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsRESET(CsRESETContext ctx) {
		super.enterCsRESET(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsSCAN(CsSCANContext ctx) {
		super.enterCsSCAN(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsSQRT(CsSQRTContext ctx) {
		super.enterCsSQRT(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsSUB(CsSUBContext ctx) {
		super.enterCsSUB(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsSUBST(CsSUBSTContext ctx) {
		super.enterCsSUBST(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsXFOOT(CsXFOOTContext ctx) {
		super.enterCsXFOOT(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsXLATE(CsXLATEContext ctx) {
		// TODO Auto-generated method stub
		super.enterCsXLATE(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsZ_ADD(CsZ_ADDContext ctx) {
		super.enterCsZ_ADD(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	@Override
	public void enterCsZ_SUB(CsZ_SUBContext ctx) {
		super.enterCsZ_SUB(ctx);
		checkResult(ctx.cspec_fixed_standard_parts());
	}

	private void checkResult(Cspec_fixed_standard_partsContext parts) {
		if (parts != null){
			ResultTypeContext result = parts.result;
			Token length = parts.len;
			Token decpos = parts.decimalPositions;
			doResultCheck(result, length, decpos);
		}
	}
	
	private void doResultCheck(ResultTypeContext result, Token length,
			Token decpos) {
		boolean lengthFound = !length.getText().trim().isEmpty();
		String lengths = length.getText().trim();
		boolean decimalsFound = !decpos.getText().trim().isEmpty();
		String decposs = decpos.getText().trim();

		if (lengthFound) {
			Symbol theSym = new Symbol();
			theSym.setName(result.getText());
			theSym.addAttribute(Symbol.CAT_LENGTH, lengths);
			theSym.addAttribute(Symbol.CAT_SYMBOL_ORIGIN, Symbol.SO_C_SPECS);
			if (decimalsFound) {
				theSym.addAttribute(Symbol.CAT_DECIMAL_POSITIONS, decposs);
				theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_PACKED);
			} else {
				theSym.addAttribute(Symbol.CAT_DATA_TYPE, Symbol.DT_ALPHANUM);
			}
			st.addSymbolToScope(currentScope, theSym);
		}
	}

}
