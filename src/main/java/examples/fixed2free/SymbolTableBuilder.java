package examples.fixed2free;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser.Cspec_fixedContext;
import org.rpgleparser.RpgParser.DirectiveContext;
import org.rpgleparser.RpgParser.DspecContext;
import org.rpgleparser.RpgParser.Dspec_fixedContext;
import org.rpgleparser.RpgParser.Fspec_fixedContext;
import org.rpgleparser.RpgParser.Hspec_fixedContext;
import org.rpgleparser.RpgParser.Ispec_fixedContext;
import org.rpgleparser.RpgParser.Ospec_fixedContext;
import org.rpgleparser.RpgParser.ProcedureContext;

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
			if (temp != null){
				for (ColumnInfo ci : temp){
					Symbol theSym = new Symbol();
					// Definition type
					theSym.setName(ci.getColumnName());
					Symbol.sqlAttr2rpg(ci, theSym);
					theSym.addAttribute(Symbol.CAT_SYMBOL_ORIGIN, Symbol.SO_EXTERNAL_FILE_DESCRIPTION);
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
	}

	@Override
	public void enterOspec_fixed(Ospec_fixedContext ctx) {
		super.enterOspec_fixed(ctx);
		lastSpec = "O";
	}

	@Override
	public void enterProcedure(ProcedureContext ctx) {
		super.enterProcedure(ctx);
		debugContext(ctx);
	}

	@Override
	public void exitProcedure(ProcedureContext ctx) {
		// TODO Auto-generated method stub
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
}
