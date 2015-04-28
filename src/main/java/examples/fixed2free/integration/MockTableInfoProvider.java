package examples.fixed2free.integration;

import java.util.ArrayList;
import java.util.List;

public class MockTableInfoProvider implements TableInfoProvider {
	
	private List<ColumnInfo> doIncmpyp(String tableName, String schemaName) {
		ArrayList<ColumnInfo> result = new ArrayList<ColumnInfo>();
		result.add(new ColumnInfo("CYCMPY", tableName, "DFTOWNER", 1,
				"NUMERIC", 1, new Integer(0), 'N', 'Y', null, 'Y', "CMPY", 1,
				new Integer(1), null, schemaName, "0", null, null, new Integer(
						10), null, "COMPANY NUMBER", "CYCMPY", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYNAME", tableName, "DFTOWNER", 2, "CHAR",
				25, null, 'N', 'Y', null, 'Y', "NAME", 25, null,
				new Integer(37), schemaName, " ", new Integer(25), new Integer(
						25), null, null, "NAME", "CYNAME", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYSNAM", tableName, "DFTOWNER", 3, "CHAR",
				15, null, 'N', 'Y', null, 'Y', "SNAM", 15, null,
				new Integer(37), schemaName, " ", new Integer(15), new Integer(
						15), null, null, "SHORT WAREHOUSE NAME", "CYSNAM",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYADR1", tableName, "DFTOWNER", 4, "CHAR",
				25, null, 'N', 'Y', null, 'Y', "ADDRESS", 25, null,
				new Integer(37), schemaName, " ", new Integer(25), new Integer(
						25), null, null, "ADDRESS LINE 1", "CYADR1", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYADR2", tableName, "DFTOWNER", 5, "CHAR",
				25, null, 'N', 'Y', null, 'Y', "ADR2", 25, null,
				new Integer(37), schemaName, " ", new Integer(25), new Integer(
						25), null, null, "ADDRESS LINE 2", "CYADR2", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYCITY", tableName, "DFTOWNER", 6, "CHAR",
				15, null, 'N', 'Y', null, 'Y', "CITY", 15, null,
				new Integer(37), schemaName, " ", new Integer(15), new Integer(
						15), null, null, "CITY", "CYCITY", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYSTA", tableName, "DFTOWNER", 7, "CHAR", 2,
				null, 'N', 'Y', null, 'Y', "STA", 2, null, new Integer(37),
				schemaName, " ", new Integer(2), new Integer(2), null, null,
				"STATE", "CYSTA", tableName, schemaName, null, null, "NO",
				null, null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYCTRY", tableName, "DFTOWNER", 8, "CHAR",
				2, null, 'N', 'Y', null, 'Y', "CTRY", 2, null, new Integer(37),
				schemaName, " ", new Integer(2), new Integer(2), null, null,
				"COUNTRY", "CYCTRY", tableName, schemaName, null, null, "NO",
				null, null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYZIP", tableName, "DFTOWNER", 9, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "ZIP", 10, null,
				new Integer(37), schemaName, " ", new Integer(10), new Integer(
						10), null, null, "ZIP CODE", "CYZIP", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYAREA", tableName, "DFTOWNER", 10,
				"DECIMAL", 3, new Integer(0), 'N', 'Y', null, 'Y', "AREA", 2,
				new Integer(3), null, schemaName, "0", null, null, new Integer(
						10), null, "AREA CODE", "CYAREA", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYPHON", tableName, "DFTOWNER", 11,
				"DECIMAL", 7, new Integer(0), 'N', 'Y', null, 'Y', "PHON", 4,
				new Integer(7), null, schemaName, "0", null, null, new Integer(
						10), null, "TELEPHONE", "CYPHON", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYOUTQ", tableName, "DFTOWNER", 12, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "OUTQ", 10, null,
				new Integer(37), schemaName, " ", new Integer(10), new Integer(
						10), null, null, "PRINT QUEUE NAME", "CYOUTQ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("CYFLG1", tableName, "DFTOWNER", 13, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG1", 1, null, new Integer(37),
				schemaName, " ", new Integer(1), new Integer(1), null, null,
				"MISCELLANEOUS FLAG	", "CYFLG1", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("CYFLG2", tableName, "DFTOWNER", 14, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG2", 1, null, new Integer(37),
				schemaName, " ", new Integer(1), new Integer(1), null, null,
				"MISCELLANEOUS FLAG	", "CYFLG2", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("CYFLG3", tableName, "DFTOWNER", 15, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG3", 1, null, new Integer(37),
				schemaName, " ", new Integer(1), new Integer(1), null, null,
				"MISCELLANEOUS FLAG	", "CYFLG3", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("CYFLG4", tableName, "DFTOWNER", 16, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG4", 1, null, new Integer(37),
				schemaName, " ", new Integer(1), new Integer(1), null, null,
				"MISCELLANEOUS FLAG	", "CYFLG4", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("CYFLG5", tableName, "DFTOWNER", 17, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG5", 1, null, new Integer(37),
				schemaName, " ", new Integer(1), new Integer(1), null, null,
				"MISCELLANEOUS FLAG	", "CYFLG5", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("CYOTQ2", tableName, "DFTOWNER", 18, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "OUTQ", 10, null,
				new Integer(37), schemaName, " ", new Integer(10), new Integer(
						10), null, null, "PRINT QUEUE NAME", "CYOTQ2",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		return result;
	}

	private List<ColumnInfo> doIndatep(String tableName, String schema) {
		ArrayList<ColumnInfo> result = new ArrayList<ColumnInfo>();
		result.add(doINDATEPColumn1(result, tableName, schema));
		result.add(doINDATEPColumn2(result, tableName, schema));
		result.add(doINDATEPColumn3(result, tableName, schema));
		result.add(doINDATEPColumn4(result, tableName, schema));
		result.add(doINDATEPColumn5(result, tableName, schema));
		result.add(doINDATEPColumn6(result, tableName, schema));
		result.add(doINDATEPColumn7(result, tableName, schema));
		result.add(doINDATEPColumn8(result, tableName, schema));
		result.add(doINDATEPColumn9(result, tableName, schema));
		result.add(doINDATEPColumn10(result, tableName, schema));
		result.add(doINDATEPColumn11(result, tableName, schema));
		result.add(doINDATEPColumn12(result, tableName, schema));
		result.add(doINDATEPColumn13(result, tableName, schema));
		result.add(doINDATEPColumn14(result, tableName, schema));
		result.add(doINDATEPColumn15(result, tableName, schema));
		return result;
	}

	private ColumnInfo doINDATEPColumn1(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAWEEK");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(1);
		temp.setDataType(ColumnInfo.NUMERIC);
		temp.setLength(1);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("WEEK");
		temp.setStorage(1);
		temp.setNumericPrecision(new Integer(1));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("WEEK OF PERIOD");
		temp.setSystemColumnName("DAWEEK");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn10(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DASNDT");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(10);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(7);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("SNDT");
		temp.setStorage(4);
		temp.setNumericPrecision(new Integer(7));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("LAST SUNDAY");
		temp.setSystemColumnName("DASNDT");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn11(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAPCDT");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(11);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(7);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("PCDT");
		temp.setStorage(4);
		temp.setNumericPrecision(new Integer(7));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("PRICE CHANGE DATE");
		temp.setSystemColumnName("DAPCDT");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn12(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAFLG1");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(12);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG1");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("Y=PERIOD 13 HAS A WEEK 5");
		temp.setSystemColumnName("DAFLG1");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn13(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAFLG2");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(13);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG2");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG");
		temp.setSystemColumnName("DAFLG2");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn14(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAFLG3");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(14);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG3");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG");
		temp.setSystemColumnName("DAFLG3");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn15(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAFSYR");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(15);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(5);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FIS-YR          _CCYY");
		temp.setStorage(3);
		temp.setNumericPrecision(new Integer(5));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(new Integer(10));
		temp.setColumnText("FISCAL YEAR _CCYY");
		temp.setSystemColumnName("DAFSYR");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn2(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAPER");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(2);
		temp.setDataType(ColumnInfo.NUMERIC);
		temp.setLength(2);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("PERIOD");
		temp.setStorage(2);
		temp.setNumericPrecision(new Integer(2));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("PERIOD");
		temp.setSystemColumnName("DAPER");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn3(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DADAY");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(3);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(1);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("DAY");
		temp.setStorage(1);
		temp.setNumericPrecision(new Integer(1));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("DAY OF WEEK MONDAY=1");
		temp.setSystemColumnName("DADAY");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn4(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAFYR");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(4);
		temp.setDataType(ColumnInfo.NUMERIC);
		temp.setLength(2);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FY");
		temp.setStorage(2);
		temp.setNumericPrecision(new Integer(2));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("FISCAL YEAR");
		temp.setSystemColumnName("DAFYR");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn5(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAWDT1");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(5);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(7);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("WDT1");
		temp.setStorage(4);
		temp.setNumericPrecision(new Integer(7));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("WK1 ENDING DATE");
		temp.setSystemColumnName("DAWDT1");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn6(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAWDT2");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(6);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(7);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("WDT2");
		temp.setStorage(4);
		temp.setNumericPrecision(new Integer(7));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("WK2 ENDING DATE");
		temp.setSystemColumnName("DAWDT2");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn7(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAWDT3");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(7);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(7);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("WDT3");
		temp.setStorage(4);
		temp.setNumericPrecision(new Integer(7));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("WK3 ENDING DATE");
		temp.setSystemColumnName("DAWDT3");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn8(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAWDT4");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(8);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(7);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("WDT4");
		temp.setStorage(4);
		temp.setNumericPrecision(new Integer(7));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("WK4 ENDING DATE");
		temp.setSystemColumnName("DAWDT4");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDATEPColumn9(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DAFYDT");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(9);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(7);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FYDT");
		temp.setStorage(4);
		temp.setNumericPrecision(new Integer(7));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("FISCAL YEAR END DATE");
		temp.setSystemColumnName("DAFYDT");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private List<ColumnInfo> doInddesp(String tableName, String schema) {
		ArrayList<ColumnInfo> result = new ArrayList<ColumnInfo>();
		result.add(doINDDESPColumn1(result, tableName, schema));
		result.add(doINDDESPColumn2(result, tableName, schema));
		result.add(doINDDESPColumn3(result, tableName, schema));
		result.add(doINDDESPColumn4(result, tableName, schema));
		result.add(doINDDESPColumn5(result, tableName, schema));
		result.add(doINDDESPColumn6(result, tableName, schema));
		result.add(doINDDESPColumn7(result, tableName, schema));
		result.add(doINDDESPColumn8(result, tableName, schema));
		result.add(doINDDESPColumn9(result, tableName, schema));
		result.add(doINDDESPColumn10(result, tableName, schema));
		result.add(doINDDESPColumn11(result, tableName, schema));
		result.add(doINDDESPColumn12(result, tableName, schema));
		result.add(doINDDESPColumn13(result, tableName, schema));
		result.add(doINDDESPColumn14(result, tableName, schema));
		result.add(doINDDESPColumn15(result, tableName, schema));
		result.add(doINDDESPColumn16(result, tableName, schema));
		result.add(doINDDESPColumn17(result, tableName, schema));
		result.add(doINDDESPColumn18(result, tableName, schema));
		result.add(doINDDESPColumn19(result, tableName, schema));
		result.add(doINDDESPColumn20(result, tableName, schema));
		return result;
	}

	private ColumnInfo doINDDESPColumn1(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDDEPT");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(1);
		temp.setDataType(ColumnInfo.NUMERIC);
		temp.setLength(2);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("DEPT");
		temp.setStorage(2);
		temp.setNumericPrecision(new Integer(2));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("DEPARTMENT NUMBER");
		temp.setSystemColumnName("DDDEPT");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn10(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG5");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(7);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG5");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 5");
		temp.setSystemColumnName("DDFLG5");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn11(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDUPCD");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(11);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(2);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("UPC DEFAULT LENGTH");
		temp.setStorage(2);
		temp.setNumericPrecision(new Integer(2));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("UPC DEFAULT LENGTH");
		temp.setSystemColumnName("DDUPCD");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn12(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG6");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(12);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG6");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 6");
		temp.setSystemColumnName("DDFLG6");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn13(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG7");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(13);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG7");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 7");
		temp.setSystemColumnName("DDFLG7");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn14(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG8");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(14);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG8");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 8");
		temp.setSystemColumnName("DDFLG8");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn15(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG9");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(15);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG9");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 9");
		temp.setSystemColumnName("DDFLG9");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn16(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG0");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(16);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG0");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 0");
		temp.setSystemColumnName("DDFLG0");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn17(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDLCCD");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(17);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("MANDATORY      PO DISTRO");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MANDATORY PO DISTRIBUTION");
		temp.setSystemColumnName("DDLCCD");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn18(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDHZCD");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(18);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLAG");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG");
		temp.setSystemColumnName("DDHZCD");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn19(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDBARCODE");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(19);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("BARCODE FLAG");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("BARCODE FLAG");
		temp.setSystemColumnName("DDBARCODE");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn2(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDDESL");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(2);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(20);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("DESL");
		temp.setStorage(20);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(20));
		temp.setCharacterOctetLength(new Integer(20));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("LONG DEPT DESC");
		temp.setSystemColumnName("DDDESL");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn20(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDADAY");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(20);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(3);
		temp.setNumericScale(new Integer(0));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("AUTO       DIST        DAYS");
		temp.setStorage(2);
		temp.setNumericPrecision(new Integer(0));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("AUTO-DISTRIBUTION DAYS OUT");
		temp.setSystemColumnName("DDADAY");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn3(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDDESS");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(3);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(9);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("DESS");
		temp.setStorage(9);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(9));
		temp.setCharacterOctetLength(new Integer(9));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("SHORT DEPT DESC");
		temp.setSystemColumnName("DDDESS");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn4(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDLOSB");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(4);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(5);
		temp.setNumericScale(new Integer(2));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("LOW%");
		temp.setStorage(3);
		temp.setNumericPrecision(new Integer(5));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("LOW% FOR SB CALC");
		temp.setSystemColumnName("DDLOSB");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn5(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDHISB");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(5);
		temp.setDataType(ColumnInfo.DECIMAL);
		temp.setLength(5);
		temp.setNumericScale(new Integer(2));
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("HIGH %");
		temp.setStorage(3);
		temp.setNumericPrecision(new Integer(5));
		temp.setCCSID(null);
		temp.setTableSchema(schema);
		temp.setColumnDefault("0");
		temp.setCharacterMaximumLength(null);
		temp.setCharacterOctetLength(null);
		temp.setNumericPrecisionRadix(new Integer(10));
		temp.setDateTimePrecision(null);
		temp.setColumnText("HIGH % FOR SB CALC");
		temp.setSystemColumnName("DDHISB");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn6(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG1");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(6);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG1");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 1");
		temp.setSystemColumnName("DDFLG1");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn7(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG2");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(7);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG2");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 2");
		temp.setSystemColumnName("DDFLG2");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn8(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG3");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(7);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG3");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 3");
		temp.setSystemColumnName("DDFLG3");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private ColumnInfo doINDDESPColumn9(ArrayList<ColumnInfo> result,
			String tableName, String schema) {
		ColumnInfo temp;
		temp = new ColumnInfo();
		temp.setColumnName("DDFLG4");
		temp.setTableName(tableName);
		temp.setTableOwner("DFTOWNER");
		temp.setOrdinalPosition(7);
		temp.setDataType(ColumnInfo.CHAR);
		temp.setLength(1);
		temp.setNumericScale(null);
		temp.setIsNullable('N');
		temp.setIsUpdateable('Y');
		temp.setLongComment(null);
		temp.setHasDefault('Y');
		temp.setColumnHeading("FLG4");
		temp.setStorage(1);
		temp.setNumericPrecision(null);
		temp.setCCSID(new Integer(37));
		temp.setTableSchema(schema);
		temp.setColumnDefault(" ");
		temp.setCharacterMaximumLength(new Integer(1));
		temp.setCharacterOctetLength(new Integer(1));
		temp.setNumericPrecisionRadix(null);
		temp.setDateTimePrecision(null);
		temp.setColumnText("MISCELLANEOUS FLAG 4");
		temp.setSystemColumnName("DDFLG4");
		temp.setSystemTableName(tableName);
		temp.setSystemSchemaName(schema);
		temp.setUserDefinedTypeSchema(null);
		temp.setUserDefinedTypeName(null);
		temp.setIsIdentity("NO");
		temp.setIdentityGeneration(null);
		temp.setIdentityStart(null);
		temp.setIdentityIncrement(null);
		temp.setIdentityMinimum(null);
		temp.setIdentityMaximum(null);
		temp.setIdentityCache(null);
		temp.setIdentityOrder(null);
		temp.setColumnExpression(null);
		temp.setHidden('N');
		temp.setHasFieldProc('N');
		return temp;
	}

	private List<ColumnInfo> doInpslip(String tableName, String schemaName) {
		ArrayList<ColumnInfo> result = new ArrayList<ColumnInfo>();
		result.add(new ColumnInfo("SICMPY", tableName, "DFTOWNER", 1,
				"NUMERIC", 1, 0, 'N', 'Y', null, 'N', "CMPY", 1, 1, null,
				schemaName, null, null, null, 10, null, "COMPANY NUMBER",
				"SICMPY    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIFYR", tableName, "DFTOWNER", 2, "NUMERIC",
				2, 0, 'N', 'Y', null, 'N', "FY", 2, 2, null, schemaName, null,
				null, null, 10, null, "FISCAL YEAR", "SIFYR     ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIPER", tableName, "DFTOWNER", 3, "NUMERIC",
				2, 0, 'N', 'Y', null, 'N', "PER", 2, 2, null, schemaName, null,
				null, null, 10, null, "PER", "SIPER     ", "INPSLI30  ",
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIREGN", tableName, "DFTOWNER", 4, "CHAR",
				2, null, 'N', 'Y', null, 'N', "REGN", 2, null, 37, schemaName,
				null, 2, 2, null, null, "REGION CODE", "SIREGN    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIDEPT", tableName, "DFTOWNER", 5,
				"NUMERIC", 2, 0, 'N', 'Y', null, 'N', "DEPT", 2, 2, null,
				schemaName, null, null, null, 10, null, "DEPARTMENT NUMBER",
				"SIDEPT    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIPSAL", tableName, "DFTOWNER", 6,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" PERIOD             PLAN SALES $", 5, 9, null, schemaName,
				null, null, null, 10, null, " PERIOD  PLAN SALES $",
				"SIPSAL    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIPINV", tableName, "DFTOWNER", 7,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" PERIOD             PLAN INV.$", 5, 9, null, schemaName, null,
				null, null, 10, null, " PERIOD  PLAN INV.$", "SIPINV    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIASAL", tableName, "DFTOWNER", 8,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" PERIOD             ACT SALES $", 5, 9, null, schemaName,
				null, null, null, 10, null, " PERIOD  ACT SALES $",
				"SIASAL    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIASL1", tableName, "DFTOWNER", 9,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             SALES WK1", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  SALES WK1", "SIASL1    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIASL2", tableName, "DFTOWNER", 10,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             SALES WK2", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  SALES WK2", "SIASL2    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIASL3", tableName, "DFTOWNER", 11,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             SALES WK3", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  SALES WK3", "SIASL3    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIASL4", tableName, "DFTOWNER", 12,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             SALES WK4", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  SALES WK4", "SIASL4    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIAYTD", tableName, "DFTOWNER", 13,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             YTD SALES", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  YTD SALES", "SIAYTD    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIAINV", tableName, "DFTOWNER", 14,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" PERIOD             ACT INV.$", 5, 9, null, schemaName, null,
				null, null, 10, null, " PERIOD  ACT INV.$", "SIAINV    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIAIN1", tableName, "DFTOWNER", 15,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             INV. WK1", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  INV. WK1", "SIAIN1    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIAIN2", tableName, "DFTOWNER", 16,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             INV. WK2", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  INV. WK2", "SIAIN2    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIAIN3", tableName, "DFTOWNER", 17,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             INV. WK3", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  INV. WK3", "SIAIN3    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIAIN4", tableName, "DFTOWNER", 18,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				" ACTUAL             INV. WK4", 5, 9, null, schemaName, null,
				null, null, 10, null, " ACTUAL  INV. WK4", "SIAIN4    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIFLG1", tableName, "DFTOWNER", 19, "CHAR",
				1, null, 'N', 'Y', null, 'N', "FLG1", 1, null, 37, schemaName,
				null, 1, 1, null, null, "MISCELLANEOUS FLAG", "SIFLG1    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIFLG2", tableName, "DFTOWNER", 20, "CHAR",
				1, null, 'N', 'Y', null, 'N', "FLG2", 1, null, 37, schemaName,
				null, 1, 1, null, null, "MISCELLANEOUS FLAG", "SIFLG2    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIFLG3", tableName, "DFTOWNER", 21, "CHAR",
				1, null, 'N', 'Y', null, 'N', "FLG3", 1, null, 37, schemaName,
				null, 1, 1, null, null, "MISCELLANEOUS FLAG", "SIFLG3    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIFLG4", tableName, "DFTOWNER", 22, "CHAR",
				1, null, 'N', 'Y', null, 'N', "FLG4", 1, null, 37, schemaName,
				null, 1, 1, null, null, "MISCELLANEOUS FLAG", "SIFLG4    ",
				"INPSLI30  ", schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIPMAR", tableName, "DFTOWNER", 23,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N', "PMAR", 5, 9, null,
				schemaName, null, null, null, 10, null, "PLAN MARGIN DOLLARS",
				"SIPMAR    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIPMPC", tableName, "DFTOWNER", 24,
				"DECIMAL", 3, 3, 'N', 'Y', null, 'N', "PMPC", 2, 3, null,
				schemaName, null, null, null, 10, null, "PLAN MARGIN PERCENT",
				"SIPMPC    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIMD$", tableName, "DFTOWNER", 25,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N', "MD$", 5, 9, null,
				schemaName, null, null, null, 10, null,
				"PLAN MARKDOWNS DOLLARS", "SIMD$     ", "INPSLI30  ",
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIMDP", tableName, "DFTOWNER", 26,
				"DECIMAL", 5, 5, 'N', 'Y', null, 'N', "MDP", 3, 5, null,
				schemaName, null, null, null, 10, null,
				"PLAN MARKDOWNS PERCENT", "SIMDP     ", "INPSLI30  ",
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIDD$", tableName, "DFTOWNER", 27,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N', "DD$", 5, 9, null,
				schemaName, null, null, null, 10, null, "DAM / DEST DOLLARS",
				"SIDD$     ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIDDP", tableName, "DFTOWNER", 28,
				"DECIMAL", 5, 5, 'N', 'Y', null, 'N', "DDP", 3, 5, null,
				schemaName, null, null, null, 10, null, "DAM / DEST PERCENT",
				"SIDDP     ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SISHK$", tableName, "DFTOWNER", 29,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N', "SHK$", 5, 9, null,
				schemaName, null, null, null, 10, null, "SHRINK DOLLARS",
				"SISHK$    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SISHKP", tableName, "DFTOWNER", 30,
				"DECIMAL", 5, 5, 'N', 'Y', null, 'N', "SHKP", 3, 5, null,
				schemaName, null, null, null, 10, null, "SHRINK PERCENT",
				"SISHKP    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIMU$", tableName, "DFTOWNER", 31,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N', "MU$", 5, 9, null,
				schemaName, null, null, null, 10, null, "PLAN MARKUP DOLLARS",
				"SIMU$     ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIMUP", tableName, "DFTOWNER", 32,
				"DECIMAL", 5, 5, 'N', 'Y', null, 'N', "MUP", 3, 5, null,
				schemaName, null, null, null, 10, null, "PLAN MARKUP PERCENT",
				"SIMUP     ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIDAIN", tableName, "DFTOWNER", 33,
				"DECIMAL", 9, 0, 'N', 'Y', null, 'N',
				"DAILY               AVG.                INV.$", 5, 9, null,
				schemaName, null, null, null, 10, null, "DAILY AVG. INV.$",
				"SIDAIN    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("SIWHS5", tableName, "DFTOWNER", 34,
				"DECIMAL", 5, 0, 'N', 'Y', null, 'N', "WHS5", 3, 5, null,
				schemaName, null, null, null, 10, null, "WAREHOUSE NUMBER",
				"SIWHS5    ", "INPSLI30  ", schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		return result;
	}

	private List<ColumnInfo> doIntslip(String tableName, String schemaName) {
		ArrayList<ColumnInfo> result = new ArrayList<ColumnInfo>();
		result.add(new ColumnInfo("TSCMPY", tableName, "DFTOWNER", 1,
				"NUMERIC", 1, 0, 'N', 'Y', null, 'Y', "CMPY", 1, 1, null,
				schemaName, "0", null, null, 10, null, "COMPANY NUMBER",
				"TSCMPY    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSFYR", tableName, "DFTOWNER", 2, "NUMERIC",
				2, 0, 'N', 'Y', null, 'Y', "FY", 2, 2, null, schemaName, "0",
				null, null, 10, null, "FISCAL YEAR", "TSFYR     ", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSPER", tableName, "DFTOWNER", 3, "NUMERIC",
				2, 0, 'N', 'Y', null, 'Y', "PERIOD", 2, 2, null, schemaName,
				"0", null, null, 10, null, "PERIOD", "TSPER     ", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSREGN", tableName, "DFTOWNER", 4, "CHAR",
				2, null, 'N', 'Y', null, 'Y', "REGN", 2, null, 37, schemaName,
				" ", 2, 2, null, null, "REGION CODE", "TSREGN    ", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSDEPT", tableName, "DFTOWNER", 5,
				"NUMERIC", 2, 0, 'N', 'Y', null, 'Y', "DEPT", 2, 2, null,
				schemaName, "0", null, null, 10, null, "DEPARTMENT NUMBER",
				"TSDEPT    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSPSAL", tableName, "DFTOWNER", 6,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "PLAN SALES $", 6, 11,
				null, schemaName, "0", null, null, 10, null, "PLAN SALES $",
				"TSPSAL    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSPINV", tableName, "DFTOWNER", 7,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "PLAN INV $", 6, 11,
				null, schemaName, "0", null, null, 10, null, "PLAN INV $",
				"TSPINV    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSASL1", tableName, "DFTOWNER", 8,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "ACT SALES $ WK 1", 6,
				11, null, schemaName, "0", null, null, 10, null,
				"ACT SALES $ WK 1", "TSASL1    ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("TSASL2", tableName, "DFTOWNER", 9,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "ACT SALES $ WK 2", 6,
				11, null, schemaName, "0", null, null, 10, null,
				"ACT SALES $ WK 2", "TSASL2    ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("TSASL3", tableName, "DFTOWNER", 10,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "ACT SALES $ WK 3", 6,
				11, null, schemaName, "0", null, null, 10, null,
				"ACT SALES $ WK 3", "TSASL3    ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("TSASL4", tableName, "DFTOWNER", 11,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "ACT SALES $ WK 4", 6,
				11, null, schemaName, "0", null, null, 10, null,
				"ACT SALES $ WK 4", "TSASL4    ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("TSAIN1", tableName, "DFTOWNER", 12,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "ACT INV $ WK 1", 6, 11,
				null, schemaName, "0", null, null, 10, null, "ACT INV $ WK 1",
				"TSAIN1    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSAIN2", tableName, "DFTOWNER", 13,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "ACT INV $ WK 2", 6, 11,
				null, schemaName, "0", null, null, 10, null, "ACT INV $ WK 2",
				"TSAIN2    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSAIN3", tableName, "DFTOWNER", 14,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "ACT INV $ WK 3", 6, 11,
				null, schemaName, "0", null, null, 10, null, "ACT INV $ WK 3",
				"TSAIN3    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSAIN4", tableName, "DFTOWNER", 15,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "ACT INV $ WK 4", 6, 11,
				null, schemaName, "0", null, null, 10, null, "ACT INV $ WK 4",
				"TSAIN4    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSFLG1", tableName, "DFTOWNER", 16, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG1", 1, null, 37, schemaName,
				" ", 1, 1, null, null, "MISCELLANEOUS FLAG", "TSFLG1    ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSFLG2", tableName, "DFTOWNER", 17, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG2", 1, null, 37, schemaName,
				" ", 1, 1, null, null, "MISCELLANEOUS FLAG", "TSFLG2    ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSFLG3", tableName, "DFTOWNER", 18, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG3", 1, null, 37, schemaName,
				" ", 1, 1, null, null, "MISCELLANEOUS FLAG", "TSFLG3    ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSFLG4", tableName, "DFTOWNER", 19, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG4", 1, null, 37, schemaName,
				" ", 1, 1, null, null, "MISCELLANEOUS FLAG", "TSFLG4    ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSPMAR", tableName, "DFTOWNER", 20,
				"DECIMAL", 11, 0, 'N', 'Y', null, 'Y', "PLAN MARGIN $", 6, 11,
				null, schemaName, "0", null, null, 10, null, "PLAN MARGIN $",
				"TSPMAR    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("TSPMPC", tableName, "DFTOWNER", 21,
				"DECIMAL", 3, 3, 'N', 'Y', null, 'Y', "PMPC", 2, 3, null,
				schemaName, "0", null, null, 10, null, "PLAN MARGIN PERCENT",
				"TSPMPC    ", tableName, schemaName, null, null, "NO", null,
				null, null, null, null, null, null, null, null, 'N', 'N'));

		return result;
	}

	private List<ColumnInfo> doInwctlp(String tableName, String schemaName) {
		ArrayList<ColumnInfo> result = new ArrayList<ColumnInfo>();
		result.add(new ColumnInfo("WCREGN", tableName, "DFTOWNER", 1, "CHAR",
				2, null, 'N', 'Y', null, 'Y', "REGN", 2, null, new Integer(37),
				schemaName, " ", new Integer(2), new Integer(2), null, null,
				"REGION CODE", "WCREGN ", tableName, schemaName, null, null,
				"NO", null, null, null, null, null, null, null, null, null,
				'N', 'N'));
		result.add(new ColumnInfo("WCCMPY", tableName, "DFTOWNER", 2,
				"NUMERIC", 1, new Integer(0), 'N', 'Y', null, 'Y', "CMPY", 1,
				new Integer(1), null, schemaName, " ", new Integer(0), null,
				null, new Integer(10), "COMPANY NUMBER", "WCCMPY ", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCNAME", tableName, "DFTOWNER", 3, "CHAR",
				25, null, 'N', 'Y', null, 'Y', "NAME", 25, null,
				new Integer(37), schemaName, " ", new Integer(25), new Integer(
						25), null, null, "NAME", "WCNAME ", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCSNAM", tableName, "DFTOWNER", 4, "CHAR",
				15, null, 'N', 'Y', null, 'Y', "SNAM", 15, null,
				new Integer(37), schemaName, " ", new Integer(15), new Integer(
						15), null, null, "SHORT WAREHOUSE NAME", "WCSNAM ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCADR1", tableName, "DFTOWNER", 5, "CHAR",
				25, null, 'N', 'Y', null, 'Y', "ADDRESS", 25, null,
				new Integer(37), schemaName, " ", new Integer(25), new Integer(
						25), null, null, "ADDRESS LINE 1", "WCADR1 ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCADR2", tableName, "DFTOWNER", 6, "CHAR",
				25, null, 'N', 'Y', null, 'Y', "ADR2", 25, null,
				new Integer(37), schemaName, " ", 25, 25, null, null,
				"ADDRESS LINE 2", "WCADR2 ", tableName, schemaName, null, null,
				"NO", null, null, null, null, null, null, null, null, null,
				'N', 'N'));
		result.add(new ColumnInfo("WCCITY", tableName, "DFTOWNER", 7, "CHAR",
				15, null, 'N', 'Y', null, 'Y', "CITY", 15, null,
				new Integer(37), schemaName, " ", 15, 15, null, null, "CITY",
				"WCCITY ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCSTA", tableName, "DFTOWNER", 8, "CHAR", 2,
				null, 'N', 'Y', null, 'Y', "STA", 2, null, new Integer(37),
				schemaName, " ", 2, 2, null, null, "STATE", "WCSTA ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCCTRY", tableName, "DFTOWNER", 9, "CHAR",
				2, null, 'N', 'Y', null, 'Y', "CTRY", 2, null, new Integer(37),
				schemaName, " ", 2, 2, null, null, "COUNTRY", "WCCTRY ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCZIP", tableName, "DFTOWNER", 10, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "ZIP", 10, null,
				new Integer(37), schemaName, " ", 10, 10, null, null,
				"ZIP CODE", "WCZIP ", tableName, schemaName, null, null, "NO",
				null, null, null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCAREA", tableName, "DFTOWNER", 11,
				"DECIMAL", 3, new Integer(0), 'N', 'Y', null, 'Y', "AREA", 2,
				new Integer(3), null, schemaName, "0", null, null, 10, null,
				"AREA CODE", "WCAREA ", tableName, schemaName, null, null,
				"NO", null, null, null, null, null, null, null, null, null,
				'N', 'N'));
		result.add(new ColumnInfo("WCPHON", tableName, "DFTOWNER", 12,
				"DECIMAL", 7, new Integer(0), 'N', 'Y', null, 'Y', "PHON", 4,
				new Integer(7), null, schemaName, "0", null, null, 10, null,
				"TELEPHONE", "WCPHON ", tableName, schemaName, null, null,
				"NO", null, null, null, null, null, null, null, null, null,
				'N', 'N'));
		result.add(new ColumnInfo("WCRCDT", tableName, "DFTOWNER", 13,
				"DECIMAL", 7, new Integer(0), 'N', 'Y', null, 'Y', "RCDT", 4,
				new Integer(7), null, schemaName, "0", null, null, 10, null,
				"LAST RECEIVED DATE", "WCRCDT ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCSLDT", tableName, "DFTOWNER", 14,
				"DECIMAL", 7, new Integer(0), 'N', 'Y', null, 'Y', "SLDT", 4,
				new Integer(7), null, schemaName, "0", null, null, 10, null,
				"LAST SALES DATE", "WCSLDT ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCRBAY", tableName, "DFTOWNER", 15,
				"DECIMAL", 3, new Integer(0), 'N', 'Y', null, 'Y', "RBAY", 2,
				new Integer(3), null, schemaName, "0", null, null, 10, null,
				"NUMBER OF RECEIVING BAYS", "WCRBAY ", tableName, schemaName,
				null, null, "NO", null, null, null, null, null, null, null,
				null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCPHDT", tableName, "DFTOWNER", 16,
				"DECIMAL", 7, new Integer(0), 'N', 'Y', null, 'Y', "PHDT", 4,
				new Integer(7), null, schemaName, "0", null, null, 10, null,
				"PHYSICAL INVENTORY DATE", "WCPHDT ", tableName, schemaName,
				null, null, "NO", null, null, null, null, null, null, null,
				null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCRECT", tableName, "DFTOWNER", 17,
				"NUMERIC", 4, new Integer(0), 'N', 'Y', null, 'Y', "RECT", 4,
				new Integer(4), null, schemaName, "0", null, null, 10, null,
				"RECEIVING START TIME", "WCRECT ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCBPDT", tableName, "DFTOWNER", 18,
				"DECIMAL", 7, new Integer(0), 'N', 'Y', null, 'Y', "BPDT", 4,
				new Integer(7), null, schemaName, "0", null, null, 10, null,
				"RA PRINT START DATE", "WCBPDT ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCEPDT", tableName, "DFTOWNER", 19,
				"DECIMAL", 7, new Integer(0), 'N', 'Y', null, 'Y', "EPDT", 4,
				new Integer(7), null, schemaName, "0", null, null, 10, null,
				"RA PRINT END DATE", "WCEPDT ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCPODT", tableName, "DFTOWNER", 20,
				"NUMERIC", 4, new Integer(0), 'N', 'Y', null, 'Y', "PODT", 4,
				new Integer(4), null, schemaName, "0", null, null, 10, null,
				"DATE IN PO #--MM DD", "WCPODT ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCPOSQ", tableName, "DFTOWNER", 21,
				"DECIMAL", 3, new Integer(0), 'N', 'Y', null, 'Y', "POSQ", 2,
				new Integer(3), null, schemaName, "0", null, null, 10, null,
				"PO SEQ #-LAST 3 DIGITS", "WCPOSQ ", tableName, schemaName,
				null, null, "NO", null, null, null, null, null, null, null,
				null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCSTAT", tableName, "DFTOWNER", 22, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "STATUS", 1, null,
				new Integer(37), schemaName, " ", 1, 1, null, null,
				"VENDOR STATUS= ACT,INACT,DIS", "WCSTAT ", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCOUTQ", tableName, "DFTOWNER", 23, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "OUTQ", 10, null,
				new Integer(37), schemaName, " ", 10, 10, null, null,
				"PRINT QUEUE NAME", "WCOUTQ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG1", tableName, "DFTOWNER", 24, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG1", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG1 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG2", tableName, "DFTOWNER", 25, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG2", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG2 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG3", tableName, "DFTOWNER", 26, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG3", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG3 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG4", tableName, "DFTOWNER", 27, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG4", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG4 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG5", tableName, "DFTOWNER", 28, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG5", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG5 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG6", tableName, "DFTOWNER", 29, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG6", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG6 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG7", tableName, "DFTOWNER", 30, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG7", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG7 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG8", tableName, "DFTOWNER", 31, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG8", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG8 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG9", tableName, "DFTOWNER", 32, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG9", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG9 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLG0", tableName, "DFTOWNER", 33, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLG0", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLG0 ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGA", tableName, "DFTOWNER", 34, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGA", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGA ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGB", tableName, "DFTOWNER", 35, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGB", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGB ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGC", tableName, "DFTOWNER", 36, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGC", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGC ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGD", tableName, "DFTOWNER", 37, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGD", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGD ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGE", tableName, "DFTOWNER", 38, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGE", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGE ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGF", tableName, "DFTOWNER", 39, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGF", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGF ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGG", tableName, "DFTOWNER", 40, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGG", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGG ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGH", tableName, "DFTOWNER", 41, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGH", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGH ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGI", tableName, "DFTOWNER", 42, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGI", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGI ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGJ", tableName, "DFTOWNER", 43, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGJ", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGJ ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGK", tableName, "DFTOWNER", 44, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGK", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGK ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGL", tableName, "DFTOWNER", 45, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGL", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGL ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGM", tableName, "DFTOWNER", 46, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGM", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGM ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGN", tableName, "DFTOWNER", 47, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGN", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGN ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGO", tableName, "DFTOWNER", 48, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGO", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "MISCELLANEOUS FLAG",
				"WCFLGO ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCLSEQ", tableName, "DFTOWNER", 49,
				"DECIMAL", 5, new Integer(0), 'N', 'Y', null, 'Y',
				"LOAD # SEQ", 3, new Integer(5), null, schemaName, "0", null,
				null, 10, null, "LOAD # SEQ", "WCLSEQ ", tableName, schemaName,
				null, null, "NO", null, null, null, null, null, null, null,
				null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCMSGQ", tableName, "DFTOWNER", 50, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "MSGQ", 10, null,
				new Integer(37), schemaName, " ", 10, 10, null, null, "MSGQ",
				"WCMSGQ ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLD1", tableName, "DFTOWNER", 51,
				"DECIMAL", 9, new Integer(0), 'N', 'Y', null, 'Y', "MISC", 5,
				new Integer(9), null, schemaName, "0", null, null, 10, null,
				"MISC FIELD - NOT USED", "WCFLD1 ", tableName, schemaName,
				null, null, "NO", null, null, null, null, null, null, null,
				null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLD2", tableName, "DFTOWNER", 52,
				"DECIMAL", 9, new Integer(0), 'N', 'Y', null, 'Y', "MISC", 5,
				new Integer(9), null, schemaName, "0", null, null, 10, null,
				"MISC FIELD - NOT USED", "WCFLD2 ", tableName, schemaName,
				null, null, "NO", null, null, null, null, null, null, null,
				null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLD3", tableName, "DFTOWNER", 53, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "MISC", 10, null,
				new Integer(37), schemaName, " ", 10, 10, null, null,
				"MISC FIELD - NOT USED", "WCFLD3 ", tableName, schemaName,
				null, null, "NO", null, null, null, null, null, null, null,
				null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLD4", tableName, "DFTOWNER", 54, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "MISC", 10, null,
				new Integer(37), schemaName, " ", 10, 10, null, null,
				"MISC FIELD - NOT USED", "WCFLD4 ", tableName, schemaName,
				null, null, "NO", null, null, null, null, null, null, null,
				null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCCSEQ", tableName, "DFTOWNER", 55, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "CMPY SEQ", 1, null, new Integer(
						37), schemaName, " ", 1, 1, null, null,
				"COMPANY SEQUENCE", "WCCSEQ ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCDSEQ", tableName, "DFTOWNER", 56, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "DIV SEQ", 1, null, new Integer(
						37), schemaName, " ", 1, 1, null, null,
				"DIVISION SEQUENCE", "WCDSEQ ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCDIV", tableName, "DFTOWNER", 57, "CHAR",
				2, null, 'N', 'Y', null, 'Y', "DIV", 2, null, new Integer(37),
				schemaName, " ", 2, 2, null, null, "DIVISION", "WCDIV ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCRSEQ", tableName, "DFTOWNER", 58, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "RGN SEQ", 1, null, new Integer(
						37), schemaName, " ", 1, 1, null, null,
				"REGION SEQUENCE", "WCRSEQ ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCSSEQ", tableName, "DFTOWNER", 59, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "SUB-RGN SEQ", 1, null,
				new Integer(37), schemaName, " ", 1, 1, null, null,
				"SUB-REGION SEQUENCE", "WCSSEQ ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCSRGN", tableName, "DFTOWNER", 60, "CHAR",
				2, null, 'N', 'Y', null, 'Y', "SUB-RGN", 2, null, new Integer(
						37), schemaName, " ", 2, 2, null, null, "SUB-REGION",
				"WCSRGN ", tableName, schemaName, null, null, "NO", null, null,
				null, null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCWRGN", tableName, "DFTOWNER", 61, "CHAR",
				2, null, 'N', 'Y', null, 'Y', "WHSE/RPT REGN", 2, null,
				new Integer(37), schemaName, " ", 2, 2, null, null,
				"WHSE/REPORT REGION", "WCWRGN ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGP", tableName, "DFTOWNER", 62, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "PRICE/ COSTCO", 1, null,
				new Integer(37), schemaName, " ", 1, 1, null, null,
				"PRICE/COSTCO", "WCFLGP ", tableName, schemaName, null, null,
				"NO", null, null, null, null, null, null, null, null, null,
				'N', 'N'));
		result.add(new ColumnInfo("WCSNM2", tableName, "DFTOWNER", 63, "CHAR",
				10, null, 'N', 'Y', null, 'Y', "SHORT WHSE NAME #2", 10, null,
				new Integer(37), schemaName, " ", 10, 10, null, null,
				"SHORT WHSE NAME #2", "WCSNM2 ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGQ", tableName, "DFTOWNER", 64, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "WHSE/RPT REGN SEQ", 1, null,
				new Integer(37), schemaName, " ", 1, 1, null, null,
				"WHSE/REPORT REGION SEQUENCE", "WCFLGQ ", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGR", tableName, "DFTOWNER", 65, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGR", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGR", "WCFLGR ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGS", tableName, "DFTOWNER", 66, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGS", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGS", "WCFLGS ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGT", tableName, "DFTOWNER", 67, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGT", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGT", "WCFLGT ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGU", tableName, "DFTOWNER", 68, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGU", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGU", "WCFLGU ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGV", tableName, "DFTOWNER", 69, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGV", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGV", "WCFLGV ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGW", tableName, "DFTOWNER", 70, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGW", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGW", "WCFLGW ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGX", tableName, "DFTOWNER", 71, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGX", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGX", "WCFLGX ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGY", tableName, "DFTOWNER", 72, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGY", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGY", "WCFLGY ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCFLGZ", tableName, "DFTOWNER", 73, "CHAR",
				1, null, 'N', 'Y', null, 'Y', "FLGZ", 1, null, new Integer(37),
				schemaName, " ", 1, 1, null, null, "FLGZ", "WCFLGZ ",
				tableName, schemaName, null, null, "NO", null, null, null,
				null, null, null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCWHSQ", tableName, "DFTOWNER", 74, "CHAR",
				3, null, 'N', 'Y', null, 'Y', "SEQ", 3, null, new Integer(37),
				schemaName, " ", 3, 3, null, null, "SEQ", "WCWHSQ ", tableName,
				schemaName, null, null, "NO", null, null, null, null, null,
				null, null, null, null, 'N', 'N'));
		result.add(new ColumnInfo("WCWHS5", tableName, "DFTOWNER", 75,
				"DECIMAL", 5, new Integer(0), 'N', 'Y', null, 'Y', "WHS5", 3,
				new Integer(5), null, schemaName, "0", null, null, 10, null,
				"WAREHOUSE NUMBER", "WCWHS5 ", tableName, schemaName, null,
				null, "NO", null, null, null, null, null, null, null, null,
				null, 'N', 'N'));
		return result;

	}

	@Override
	public List<ColumnInfo> getColumns(String tableName) {
		return getColumns(tableName, "THELIB");
	}

	@Override
	public List<ColumnInfo> getColumns(String tableName, String schemaName) {
		List<ColumnInfo> result = null;
		if (tableName.equalsIgnoreCase("INDATEP")) {
			result = doIndatep(tableName, schemaName);
		} else if (tableName.equalsIgnoreCase("INWCTLP")
				|| tableName.equalsIgnoreCase("INWCTL32")
				|| tableName.equalsIgnoreCase("INWCTL34")
				|| tableName.equalsIgnoreCase("INWCTL35")) {
			result = doInwctlp(tableName, schemaName);
		} else if (tableName.equalsIgnoreCase("INDDESP")) {
			result = doInddesp(tableName, schemaName);
		} else if (tableName.equalsIgnoreCase("INCMPYP")
				|| tableName.equalsIgnoreCase("INCMPY01")) {
			result = doIncmpyp(tableName, schemaName);
		} else if (tableName.equalsIgnoreCase("INPSLI30")) {
			result = doInpslip(tableName, schemaName);
		} else if (tableName.equalsIgnoreCase("INTSLIP")){
			result = doIntslip(tableName, schemaName);
		}
		return result;
	}

}
