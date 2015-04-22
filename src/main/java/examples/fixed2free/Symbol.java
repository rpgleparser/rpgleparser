package examples.fixed2free;

import java.util.HashMap;
import java.util.Map;

public class Symbol {
	/** I am using static strings rather than enums because I want the user to be able to 
	 * extend what is held in the list of symbol attributes.
	 * The list of symbols that are predefined start with DT_
	 * 
	 */
	public static final String CAT_DATA_TYPE="DATA TYPE";
	/**
	 * CAT_DECIMAL_POSITIONS Is an optional attribute that pertains only to Packed and Zoned fields
	 */
	public static final String CAT_DECIMAL_POSITIONS = "DEC_POS";
	/**
	 * CAT_LENGTH Is an optional attribute that records the length of a field
	 */
	public static final String CAT_LENGTH = "LENGTH";
	public static final String CAT_VARYING_LENGTH = "VARYING";
	public static final String DT_ARRAY="ARRAY";
	public static final String DT_DATA_STRUCTURE = "DATASTRUCTURE";
	public static final String DT_DATE="DATE";
	public static final String DT_DOUBLE="DOUBLE";
	public static final String DT_FLOAT="FLOAT";
	public static final String DT_INDICATOR="INDICATOR";
	public static final String DT_INTEGER="INTEGER";
	public static final String DT_KLIST="KLIST";
	public static final String DT_PACKED="PACKED";
	public static final String DT_PLIST="PLIST";
	public static final String DT_POINTER="POINTER";
	public static final String DT_PROC_POINTER= "PROC_PTR";
	public static final String DT_STRING = "STRING";
	public static final String DT_TABLE="TABLE";
	public static final String DT_TIME="TIME";
	public static final String DT_TIMESTAMP="TIMESTAMP";
	public static final String DT_ZONED="ZONED";
	
	private Map<String, String>attributes = new HashMap<String, String>();
	private String name;
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
