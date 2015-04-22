package examples.fixed2free;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;

public class SymbolTableTest {
	public static  void main(String[] args){
		try {
			FileReader fr = new FileReader("/Users/doulos1_2000/Documents/rpgleparser/src/test/resources/org/rpgleparser/integration/INR034.RPGLE");
			ANTLRInputStream aStream = new ANTLRInputStream(fr);
			RpgLexer lexer = new RpgLexer(aStream);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			RpgParser parser = new RpgParser(tokens);
			ParserRuleContext tree = parser.r(); // parse
			 
			ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
			SymbolTableBuilder extractor = new SymbolTableBuilder(lexer, tokens);
			walker.walk(extractor, tree); // initiate walk of tree with listener
			List<String> results = extractor.collectOutput();
			if (results != null){
				for (String a : results){
					System.out.println(a);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
