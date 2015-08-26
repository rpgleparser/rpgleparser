fixed_defSpec() ::= <<
BLANK_SPEC : {getCharPositionInLine()==6}? 
    '                                                                           ';
CONTINUATION_NAME : {getCharPositionInLine()\<21}? [ ]* ~[\r\n ]+ CONTINUATION {setText(getText().substring(0,getText().length()-3).trim());} -> pushMode(CONTINUATION_ELIPSIS) ;
CONTINUATION : '...' ;
NAME : {getCharPositionInLine()==6}? NAME5 NAME5 NAME5 {setText(getText().trim());};
EXTERNAL_DESCRIPTION: {getCharPositionInLine()==21}? [eE ];
DATA_STRUCTURE_TYPE: {getCharPositionInLine()==22}? [sSuU ];
DEF_TYPE_C: {getCharPositionInLine()==23}? [cC][ ];
DEF_TYPE_PI: {getCharPositionInLine()==23}? [pP][iI];
DEF_TYPE_PR: {getCharPositionInLine()==23}? [pP][rR];
DEF_TYPE_DS: {getCharPositionInLine()==23}? [dD][sS];
DEF_TYPE_S: {getCharPositionInLine()==23}? [sS][ ];
DEF_TYPE_BLANK: {getCharPositionInLine()==23}? [ ][ ];
DEF_TYPE: {getCharPositionInLine()==23}? [a-zA-Z0-9 ][a-zA-Z0-9 ];
FROM_POSITION: {getCharPositionInLine()==25}? WORD5 [a-zA-Z0-9\+\- ][a-zA-Z0-9 ];
TO_POSITION: {getCharPositionInLine()==32}? WORD5[a-zA-Z0-9\+\- ][a-zA-Z0-9 ];
DATA_TYPE: {getCharPositionInLine()==39}? [a-zA-Z\* ];
DECIMAL_POSITIONS: {getCharPositionInLine()==40}? [0-9\+\- ][0-9 ];
RESERVED : {getCharPositionInLine()==42}? ' ' -> pushMode(FREE);
//KEYWORDS : {getCharPositionInLine()==43}? ~[\r\n]+ ;
D_WS : {getCharPositionInLine()>=80}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
D_COMMENTS80 : {getCharPositionInLine()>=80}? ~[\r\n]+ -> channel(HIDDEN); // skip comments after 80
>>