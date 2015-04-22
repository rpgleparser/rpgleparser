package examples.fixed2free;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
	private Map<String, Scope>scopes = new HashMap<String, Scope>();
	private Map<Scope, Map<String, Symbol>>symbolTable = new HashMap<Scope, Map<String, Symbol>>();

	/**
	 * Get a scope instance for a given scope identifier
	 * The Global scope should us the Scope.GLOBAL constant as it's identifer
	 * If the scope does not exist then one will be created and that will be returned
	 * @param scopeIdentifier
	 * @return 
	 */
	public Scope getAScope(String scopeIdentifer){
		Scope result = null;
		result = scopes.get(scopeIdentifer);
		if (result == null){
			result = new Scope(scopeIdentifer);
			scopes.put(scopeIdentifer, result);
			symbolTable.put(result, new HashMap<String, Symbol>());
		}
		return result;
	}
	public Symbol getSymbolFromScope(Scope aScope, String symbolName){
		Map<String, Symbol> temp = symbolTable.get(aScope);
		Symbol result = temp.get(symbolName);
		return result;
	}
	public void addSymbolToScope(Scope aScope, Symbol aSymbol){
		Map<String, Symbol>temp = symbolTable.get(aScope);
		if (temp == null){
			// throw somesort of exception?
		} else {
			if (temp.containsKey(aSymbol.getName())){
				// Do nothing
			} else {
				temp.put(aSymbol.getName(), aSymbol);
			}
		}
	}
}
