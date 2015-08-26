fixed_outputSpec() ::= <<
OS_BLANK_SPEC : {getCharPositionInLine()==6}? 
    '                                                                           ' -> type(BLANK_SPEC);
OS_RecordName : {getCharPositionInLine()==6}? WORD5 WORD5;
OS_AndOr: {getCharPositionInLine()==6}? '         ' ([aA][nN][dD] | [oO][rR] ' ') '  ' -> 
	pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode);
OS_FieldReserved: {getCharPositionInLine()==6}? '              ' -> pushMode(FIXED_OutputSpec_PGMFIELD),
	pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode);
OS_Type: {getCharPositionInLine()==16}? [a-zA-Z ];
OS_AddDelete: {getCharPositionInLine()==17}? ([aA][dD][dD] | [dD][eE][lL])  -> pushMode(FIXED_OutputSpec_PGM1),
	pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode); 
OS_FetchOverflow: {getCharPositionInLine()==17}? (' ' | [fFrR]) '  ' -> pushMode(OnOffIndicatorMode),
	pushMode(OnOffIndicatorMode),pushMode(OnOffIndicatorMode);
//OS_OutputCondition: {getCharPositionInLine()==20 || getCharPositionInLine()==23
//		|| getCharPositionInLine()==26}? [N ] IND_FRAG ; 
OS_ExceptName: {getCharPositionInLine()==29}? WORD5 WORD5;
OS_Space3: {getCharPositionInLine()==39 || getCharPositionInLine()==42 
	|| getCharPositionInLine()==45 || getCharPositionInLine()==48}? [ 0-9][ 0-9][ 0-9];
OS_RemainingSpace: {getCharPositionInLine()==51}? '                             ';
OS_Comments : CS_Comments -> channel(HIDDEN); // skip comments after 80
OS_WS : {getCharPositionInLine()>=80}? [ \t]+ -> type(WS),skip  ; // skip spaces, tabs, newlines
OS_EOL : NEWLINE -> type(EOL),popMode;//,skip;

mode FIXED_OutputSpec_PGM1;
//O1_OutputCondition: {getCharPositionInLine()==20 || getCharPositionInLine()==23
//		|| getCharPositionInLine()==26}? [N ] IND_FRAG -> type(OS_OutputCondition);
O1_ExceptName: {getCharPositionInLine()==29}? WORD5 WORD5 -> type(OS_ExceptName);
O1_RemainingSpace: {getCharPositionInLine()==39}? '                                         ' -> type(OS_RemainingSpace),popMode;	
 
mode FIXED_OutputSpec_PGMFIELD;
//OF_OutputCondition: {getCharPositionInLine()==20 || getCharPositionInLine()==23
//		|| getCharPositionInLine()==26}? [Nn ] IND_FRAG -> type(OS_OutputCondition);
OS_FieldName: {getCharPositionInLine()==29}? WORD5 WORD5 ~[\r\n] ~[\r\n] ~[\r\n] ~[\r\n];
OS_EditNames: {getCharPositionInLine()==43}? [ 0-9A-Za-z];
OS_BlankAfter: {getCharPositionInLine()==44}? [ bB];
OS_Reserved1: {getCharPositionInLine()==45}? [ ] -> skip;
OS_EndPosition: {getCharPositionInLine()==46}? WORD5;
OS_DataFormat: {getCharPositionInLine()==51}? [ 0-9A-Za-z] -> pushMode(FREE);
//OS_Words: {getCharPositionInLine()==52}? WORD5 WORD5 WORD5 WORD5 WORD5 ~[\r\n] ~[\r\n] ~[\r\n] -> popMode;
OS_Any: -> popMode;

>>