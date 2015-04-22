package examples.fixed2free;

public class Scope {
	private String key;
	private String description;
	public Scope(String scopeIdentifier) {
		this.key = scopeIdentifier;
		// TODO Auto-generated constructor stub
	}
	public String getKey() {
		return key;
	}
	/**
	 * The key is the scope identifier there is a special constant for the global scope
	 * Typically the scope name would be the name of the procedure or GLOBAL, however,
	 * the name of the scope is a string so you can put anything in there you like
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static final String GLOBAL = "GLOBAL";

}
