package examples.fixed2free;

/**
 * This is a compile unit javadoc comment
 */
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;


/**
 * This is a class javadoc comment
 * @author Eric Wilson
 *
 */
public class Fixed2FreeTest {
	/**
	 * This is a constructor javadoc comment
	 */
	public Fixed2FreeTest(){
		// Default constructor
	}
	/**
	 * This is a method javadoc comment
	 * @param args
	 */
	public static  void main(String[] args){
		try {
			FileReader fr = new FileReader("/Users/doulos1_2000/Documents/rpgleparser/src/test/resources/org/rpgleparser/integration/INR034.RPGLE");
			ANTLRInputStream aStream = new ANTLRInputStream(fr);
			RpgLexer lexer = new RpgLexer(aStream);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			RpgParser parser = new RpgParser(tokens);
			ParserRuleContext tree = parser.r(); // parse
			 
			ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
			FreeFormatConverter extractor = new FreeFormatConverter(lexer);
			walker.walk(extractor, tree); // initiate walk of tree with listener
			List<String> results =extractor.collectOutput();
			for (String a : results){
				System.out.println(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getSomeText(HashMap<String, Token> theMap, int[] theArray){
		String result = "";
		
		return result;
	}

}
