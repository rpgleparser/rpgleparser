fixed_inputSpec() ::= <<
IS_BLANK_SPEC : {getCharPositionInLine()==6}? 
    '                                                                           ' -> type(BLANK_SPEC);
IS_FileName: {getCharPositionInLine()==6}? WORD5_WCOLON WORD5_WCOLON ;
IS_FieldReserved: {getCharPositionInLine()==6}? '                        ' -> pushMode(FIXED_I_FIELD_SPEC),skip ;
IS_ExtFieldReserved: {getCharPositionInLine()==6}? '              ' -> pushMode(FIXED_I_EXT_FIELD_SPEC),skip ;
IS_LogicalRelationship : {getCharPositionInLine()==15}? ('AND' | 'OR '| ' OR') ;
IS_ExtRecordReserved : {getCharPositionInLine()==16}? '    '  -> pushMode(FIXED_I_EXT_REC_SPEC),pushMode(IndicatorMode) ;
IS_Sequence : {getCharPositionInLine()==16}? WORD_WCOLON WORD_WCOLON ;
IS_Number : {getCharPositionInLine()==18}? [ 1nN] ;
IS_Option: {getCharPositionInLine()==19}? [ oO] -> pushMode(IndicatorMode);
//IS_RecordIdIndicator: {getCharPositionInLine()==20}? IND_FRAG | '**';
IS_RecordIdCode: {getCharPositionInLine()==22}?  WORD5_WCOLON WORD5_WCOLON WORD5_WCOLON WORD5_WCOLON
		WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON; //TODO better lexing
IS_WS : {getCharPositionInLine()>=46}? [ \t]+ -> type(WS),skip  ; // skip spaces, tabs
IS_COMMENTS : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines
IS_EOL : NEWLINE -> type(EOL),popMode; 

mode FIXED_I_EXT_FIELD_SPEC;
IF_Name: {getCharPositionInLine()==20}? WORD5_WCOLON WORD5_WCOLON ;
IF_Reserved: {getCharPositionInLine()==30}? '                  ' -> skip;
IF_FieldName: {getCharPositionInLine()==48}? WORD5_WCOLON WORD5_WCOLON WORD_WCOLON WORD_WCOLON
	WORD_WCOLON WORD_WCOLON->pushMode(IndicatorMode),pushMode(IndicatorMode);
//IF_ControlLevel : {getCharPositionInLine()==62}? ('L'[0-9] | '  ') ;	
//IF_MatchingFields: {getCharPositionInLine()==64}? ('M'[0-9] | '  ') ;
IF_Reserved2: {getCharPositionInLine()==66}? '  ' ->pushMode(IndicatorMode),pushMode(IndicatorMode),pushMode(IndicatorMode),skip; // 3 Indicators in a row
IF_WS : {getCharPositionInLine()>=74}? [ \t]+ -> type(WS),popMode,skip  ; // skip spaces, tabs

mode FIXED_I_EXT_REC_SPEC;
//IR_ID_IND : {getCharPositionInLine()==20}? IND_FRAG ;
IR_WS : {getCharPositionInLine()>=22}? [ \t]+ -> type(WS),popMode  ; // skip spaces, tabs

mode FIXED_I_FIELD_SPEC;
IFD_DATA_ATTR: {getCharPositionInLine()==30}?  WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
IFD_DATETIME_SEP: {getCharPositionInLine()==34}?  ~[\r\n];
IFD_DATA_FORMAT: {getCharPositionInLine()==35}?  [A-Z ];
IFD_FIELD_LOCATION: {getCharPositionInLine()==36}?  WORD5_WCOLON WORD5_WCOLON;
IFD_DECIMAL_POSITIONS: {getCharPositionInLine()==46}?  [ 0-9][ 0-9];
IFD_FIELD_NAME: {getCharPositionInLine()==48}?  WORD5_WCOLON WORD5_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON WORD_WCOLON;
IFD_CONTROL_LEVEL : {getCharPositionInLine()==62}? ('L'[0-9] | '  ') ;
IFD_MATCHING_FIELDS: {getCharPositionInLine()==64}? ('M'[0-9] | '  ') ->pushMode(IndicatorMode),pushMode(IndicatorMode),
	pushMode(IndicatorMode),pushMode(IndicatorMode);
//IFD_FIELD_RELATION: {getCharPositionInLine()==66}? IND_FRAG ;
//IFD_FIELD_IND: {getCharPositionInLine()==68}? IND_FRAG IND_FRAG IND_FRAG ;
IFD_BLANKS: {getCharPositionInLine()==74}? '      ' -> skip;
IFD_COMMENTS : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines
IFD_EOL : NEWLINE -> type(EOL),popMode,popMode;
>>