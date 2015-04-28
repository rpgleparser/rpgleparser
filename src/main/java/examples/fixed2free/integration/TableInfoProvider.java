package examples.fixed2free.integration;

import java.util.List;

public interface TableInfoProvider {
	// DB2i SQL Datatypes
	public static final String SQL_BIGINT = "BIGINT";
	public static final String SQL_INTEGER = "INTEGER";
	public static final String SQL_SMALLINT = "SMALLINT";
	public static final String SQL_DECIMAL = "DECIMAL";
	public static final String SQL_NUMERIC = "NUMERIC";
	public static final String SQL_FLOAT = "FLOAT";
	public static final String SQL_DECFLOAT = "DECFLOAT";
	public static final String SQL_CHAR = "CHAR";
	public static final String SQL_VARCHAR = "VARCHAR";
	public static final String SQL_CLOB = "CLOB";
	public static final String SQL_GRAPHIC = "GRAPHIC";
	public static final String SQL_VARG = "VARG";
	public static final String SQL_DBCLOB = "DBCLOB";
	public static final String SQL_BINARY = "BINARY";
	public static final String SQL_VARBIN = "VARBIN";
	public static final String SQL_BLOB = "BLOB";
	public static final String SQL_DATE = "DATE";
	public static final String SQL_TIME = "TIME";
	public static final String SQL_TIMESTMP = "TIMESTMP";
	public static final String SQL_DATALINK = "DATALINK";
	public static final String SQL_ROWID = "ROWID";
	public static final String SQL_DOUBLE = "DOUBLE PRECISION";
	public static final String SQL_REAL = "REAL";

	List<ColumnInfo> getColumns(String tableName);
	List<ColumnInfo> getColumns(String tableName, String schemaName);
}
