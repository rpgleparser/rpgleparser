package examples.fixed2free.integration;

import java.math.BigDecimal;

/**
 * This class is a pojo that exposes the data that is contained in the
 * SYSCOLUMNS catalog table on an iSeries (V7.2)
 * 
 * @author doulos1_2000
 *
 */
public class ColumnInfo {
	public static final String BIGINT = "BIGINT";
	public static final String BINARY = "BINARY";
	public static final String BLOB = "BLOB";
	public static final String CHAR = "CHAR";
	public static final String CLOB = "CLOB";
	public static final String DATALINK = "DATALINK";
	public static final String DATE = "DATE";
	public static final String DBCLOB = "DBCLOB";
	public static final String DECFLOAT = "DECFLOAT";
	public static final String DECIMAL = "DECIMAL";
	public static final String DISTINCT = "DISTINCT";
	public static final String DOUBLE_PRECISION = "DOUBLE PRECISION";
	public static final String FLOAT = "FLOAT";
	public static final String GRAPHIC = "GRAPHIC";
	public static final String INTEGER = "INTEGER";
	public static final String NUMERIC = "NUMERIC";
	public static final String REAL = "REAL";
	public static final String ROWID = "ROWID";
	public static final String SMALLINT = "SMALLINT";
	public static final String TIME = "TIME";
	public static final String TIMESTMP = "TIMESTMP";
	public static final String VARBIN = "VARBIN";
	public static final String VARCHAR = "VARCHAR";
	public static final String VARG = "VARG";
	public static final String XML = "XML";

	public static String getBigint() {
		return BIGINT;
	}

	public static String getBinary() {
		return BINARY;
	}

	public static String getBlob() {
		return BLOB;
	}

	public static String getChar() {
		return CHAR;
	}

	public static String getClob() {
		return CLOB;
	}

	public static String getDatalink() {
		return DATALINK;
	}

	public static String getDate() {
		return DATE;
	}

	public static String getDbclob() {
		return DBCLOB;
	}

	public static String getDecfloat() {
		return DECFLOAT;
	}

	public static String getDecimal() {
		return DECIMAL;
	}

	public static String getDistinct() {
		return DISTINCT;
	}

	public static String getDoublePrecision() {
		return DOUBLE_PRECISION;
	}

	public static String getFloat() {
		return FLOAT;
	}

	public static String getGraphic() {
		return GRAPHIC;
	}

	public static String getInteger() {
		return INTEGER;
	}

	public static String getNumeric() {
		return NUMERIC;
	}

	public static String getReal() {
		return REAL;
	}

	public static String getRowid() {
		return ROWID;
	}

	public static String getSmallint() {
		return SMALLINT;
	}

	public static String getTime() {
		return TIME;
	}

	public static String getTimestmp() {
		return TIMESTMP;
	}

	public static String getVarbin() {
		return VARBIN;
	}

	public static String getVarchar() {
		return VARCHAR;
	}

	public static String getVarg() {
		return VARG;
	}

	public static String getXml() {
		return XML;
	}

	private Integer CCSID;
	private Integer characterMaximumLength;
	private Integer characterOctetLength;
	private String columnDefault;
	private String columnExpression;
	private String columnHeading;
	private String columnName;
	private String columnText;
	private String dataType;
	private Integer dateTimePrecision;
	private char hasDefault;
	private char hasFieldProc;
	private char hidden;
	private Integer identityCache;
	private String identityCycle;
	private String identityGeneration;
	private BigDecimal identityIncrement;
	private BigDecimal identityMaximum;
	private BigDecimal identityMinimum;
	private String identityOrder;
	private BigDecimal identityStart;
	private String isIdentity;
	private char isNullable;
	private char isUpdateable;
	private int length;
	private String longComment;
	private Integer numericPrecision;
	private Integer numericPrecisionRadix;
	private Integer numericScale;
	private int ordinalPosition;
	private int storage;
	private String systemColumnName;
	private String systemTableSchema;
	private String systemTableName;
	private String tableName;
	private String tableOwner;
	private String tableSchema;
	private String userDefinedTypeName;
	private String userDefinedTypeSchema;

	public Integer getCCSID() {
		return CCSID;
	}

	public Integer getCharacterMaximumLength() {
		return characterMaximumLength;
	}

	public Integer getCharacterOctetLength() {
		return characterOctetLength;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public String getColumnExpression() {
		return columnExpression;
	}

	public String getColumnHeading() {
		return columnHeading;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getColumnText() {
		return columnText;
	}

	public String getDataType() {
		return dataType;
	}

	public Integer getDateTimePrecision() {
		return dateTimePrecision;
	}

	public char getHasDefault() {
		return hasDefault;
	}

	public char getHasFieldProc() {
		return hasFieldProc;
	}

	public char getHidden() {
		return hidden;
	}

	public Integer getIdentityCache() {
		return identityCache;
	}

	public String getIdentityCycle() {
		return identityCycle;
	}

	public String getIdentityGeneration() {
		return identityGeneration;
	}

	public BigDecimal getIdentityIncrement() {
		return identityIncrement;
	}

	public BigDecimal getIdentityMaximum() {
		return identityMaximum;
	}

	public BigDecimal getIdentityMinimum() {
		return identityMinimum;
	}

	public String getIdentityOrder() {
		return identityOrder;
	}

	public BigDecimal getIdentityStart() {
		return identityStart;
	}

	public String getIsIdentity() {
		return isIdentity;
	}

	public char getIsNullable() {
		return isNullable;
	}

	public char getIsUpdateable() {
		return isUpdateable;
	}

	public int getLength() {
		return length;
	}

	public String getLongComment() {
		return longComment;
	}

	public Integer getNumericPrecision() {
		return numericPrecision;
	}

	public Integer getNumericPrecisionRadix() {
		return numericPrecisionRadix;
	}

	public Integer getNumericScale() {
		return numericScale;
	}

	public int getOrdinalPosition() {
		return ordinalPosition;
	}

	public int getStorage() {
		return storage;
	}

	public String getSystemColumnName() {
		return systemColumnName;
	}

	public String getSystemSchemaName() {
		return systemTableSchema;
	}

	public String getSystemTableName() {
		return systemTableName;
	}

	public String getTableName() {
		return tableName;
	}

	public String getTableOwner() {
		return tableOwner;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public String getUserDefinedTypeName() {
		return userDefinedTypeName;
	}

	public String getUserDefinedTypeSchema() {
		return userDefinedTypeSchema;
	}

	public void setCCSID(Integer cCSID) {
		CCSID = cCSID;
	}

	public void setCharacterMaximumLength(Integer characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}

	public void setCharacterOctetLength(Integer characterOctetLength) {
		this.characterOctetLength = characterOctetLength;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public void setColumnExpression(String columnExpression) {
		this.columnExpression = columnExpression;
	}

	public void setColumnHeading(String columnHeading) {
		this.columnHeading = columnHeading;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setColumnText(String columnText) {
		this.columnText = columnText;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setDateTimePrecision(Integer dateTimePrecision) {
		this.dateTimePrecision = dateTimePrecision;
	}

	public void setHasDefault(char hasDefault) {
		this.hasDefault = hasDefault;
	}

	public void setHasFieldProc(char hasFieldProc) {
		this.hasFieldProc = hasFieldProc;
	}

	public void setHidden(char hidden) {
		this.hidden = hidden;
	}

	public void setIdentityCache(Integer identityCache) {
		this.identityCache = identityCache;
	}

	public void setIdentityCycle(String identityCycle) {
		this.identityCycle = identityCycle;
	}

	public void setIdentityGeneration(String identityGeneration) {
		this.identityGeneration = identityGeneration;
	}

	public void setIdentityIncrement(BigDecimal identityIncrement) {
		this.identityIncrement = identityIncrement;
	}

	public void setIdentityMaximum(BigDecimal identityMaximum) {
		this.identityMaximum = identityMaximum;
	}

	public void setIdentityMinimum(BigDecimal identityMinimum) {
		this.identityMinimum = identityMinimum;
	}

	public void setIdentityOrder(String identityOrder) {
		this.identityOrder = identityOrder;
	}

	public void setIdentityStart(BigDecimal identityStart) {
		this.identityStart = identityStart;
	}

	public void setIsIdentity(String isIdentity) {
		this.isIdentity = isIdentity;
	}

	public void setIsNullable(char isNullable) {
		this.isNullable = isNullable;
	}

	public void setIsUpdateable(char isUpdateable) {
		this.isUpdateable = isUpdateable;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setLongComment(String longComment) {
		this.longComment = longComment;
	}

	public void setNumericPrecision(Integer numericPrecision) {
		this.numericPrecision = numericPrecision;
	}

	public void setNumericPrecisionRadix(Integer numericPrecisionRadix) {
		this.numericPrecisionRadix = numericPrecisionRadix;
	}

	public void setNumericScale(Integer numericScale) {
		this.numericScale = numericScale;
	}

	public void setOrdinalPosition(int ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public void setSystemColumnName(String systemColumnName) {
		this.systemColumnName = systemColumnName;
	}

	public void setSystemSchemaName(String systemSchemaName) {
		this.systemTableSchema = systemSchemaName;
	}

	public void setSystemTableName(String systemTableName) {
		this.systemTableName = systemTableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setTableOwner(String tableOwner) {
		this.tableOwner = tableOwner;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public void setUserDefinedTypeName(String userDefinedTypeName) {
		this.userDefinedTypeName = userDefinedTypeName;
	}

	public void setUserDefinedTypeSchema(String userDefinedTypeSchema) {
		this.userDefinedTypeSchema = userDefinedTypeSchema;
	}

	public ColumnInfo(){
		// Surface the default no-args constructor since we want to also have a
		// Convenience constructor with all the args
	}
	public ColumnInfo(
			String colName, 
			String tableName, 
			String tableOwner,
			int ordinalPosition, 
			String datatype, 
			int length,
			Integer numericScale, 
			char isNullable, 
			char isUpdateable,
			String longComment, 
			char hasDefault, 
			String columnHeading,
			int storage, 
			Integer numericPrecision, 
			Integer CCSID,
			String schema, 
			String columnDefault, 
			Integer characterMaxLen,
			Integer characterOctetLen, 
			Integer numericPrecisionRadix,
			Integer dateTimePrecision, 
			String columnText,
			String systemColumnName, 
			String systemTableName,
			String systemSchemaName, 
			String userDefinedTypeSchema,
			String userDefinedTypeName, 
			String isIdentity,
			String identityGeneration, 
			BigDecimal identityStart,
			BigDecimal identityIncrement, 
			BigDecimal identityMinimum,
			BigDecimal identityMaximum, 
			String identityCycle,
			Integer identityCache,
			String identityOrder, 
			String columnExpression, 
			char isHidden,
			char hasFieldProc
			) {
		this.setColumnName(colName);
		this.setTableName(tableName);
		this.setTableOwner(tableOwner);
		this.setOrdinalPosition(ordinalPosition);
		this.setDataType(datatype);
		this.setLength(length);
		this.setNumericScale(numericScale);
		this.setIsNullable(isNullable);
		this.setIsUpdateable(isUpdateable);
		this.setLongComment(longComment);
		this.setHasDefault(hasDefault);
		this.setColumnHeading(columnHeading);
		this.setStorage(storage);
		this.setNumericPrecision(numericPrecision);
		this.setCCSID(CCSID);
		this.setTableSchema(schema);
		this.setColumnDefault(columnDefault);
		this.setCharacterMaximumLength(characterMaxLen);
		this.setCharacterOctetLength(characterOctetLen);
		this.setNumericPrecisionRadix(numericPrecisionRadix);
		this.setDateTimePrecision(dateTimePrecision);
		this.setColumnText(columnText);
		this.setSystemColumnName(systemColumnName);
		this.setSystemTableName(systemTableName);
		this.setSystemSchemaName(systemSchemaName);
		this.setUserDefinedTypeSchema(userDefinedTypeSchema);
		this.setUserDefinedTypeName(userDefinedTypeName);
		this.setIsIdentity(isIdentity);
		this.setIdentityGeneration(identityGeneration);
		this.setIdentityStart(identityStart);
		this.setIdentityIncrement(identityIncrement);
		this.setIdentityMinimum(identityMinimum);
		this.setIdentityMaximum(identityMaximum);
		this.setIdentityCycle(identityCycle);
		this.setIdentityCache(identityCache);
		this.setIdentityOrder(identityOrder);
		this.setColumnExpression(columnExpression);
		this.setHidden(isHidden);
		this.setHasFieldProc(hasFieldProc);

	}

	public ColumnInfo(
			String string, 
			String string2, 
			String string3, 
			int i,
			String string4, 
			int j, 
			Object object, 
			char c, 
			char d,
			Object object2, 
			char e, 
			String string5, 
			int k, 
			Object object3,
			Integer integer2, 
			String string6, 
			char f, 
			Integer integer3,
			Integer integer4, 
			Object object4, 
			Object object5, 
			String string7,
			String string8, 
			String string9, 
			String string10, 
			Object object6,
			Object object7, 
			String string11, 
			Object object8, 
			Object object9,
			Object object10, 
			Object object11, 
			Object object12, 
			Object object13,
			Object object14, 
			Object object15, 
			char g, 
			char h) {
		// TODO Auto-generated constructor stub
	}

}
