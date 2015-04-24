package examples.fixed2free.integration;

import java.util.List;

public interface TableInfoProvider {
	List<ColumnInfo> getColumns(String tableName);
	List<ColumnInfo> getColumns(String tableName, String schemaName);
}
