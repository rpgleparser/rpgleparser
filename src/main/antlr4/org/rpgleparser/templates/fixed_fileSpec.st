fixed_fileSpec() ::= <<
FS_BLANK_SPEC : {getCharPositionInLine()==6}? 
    '                                                                           ' -> type(BLANK_SPEC);
FS_RecordName : {getCharPositionInLine()==6}? WORD5 WORD5;
FS_Type: {getCharPositionInLine()==16}? [a-zA-Z ];
FS_Designation: {getCharPositionInLine()==17}? [a-zA-Z ];
FS_EndOfFile: {getCharPositionInLine()==18}? [eE ];
FS_Addution: {getCharPositionInLine()==19}? [aA ];
FS_Sequence: {getCharPositionInLine()==20}? [aAdD ];
FS_Format: {getCharPositionInLine()==21}? [eEfF ];
FS_RecordLength: {getCharPositionInLine()==22}? WORD5;
FS_Limits: {getCharPositionInLine()==27}? [lL ];
FS_LengthOfKey: {getCharPositionInLine()==28}? [0-9 ][0-9 ][0-9 ][0-9 ][0-9 ];
FS_RecordAddressType: {getCharPositionInLine()==33}? [a-zA-Z ];
FS_Organization: {getCharPositionInLine()==34}? [a-zA-Z ];
FS_Device: {getCharPositionInLine()==35}? WORD5 [a-zA-Z ][a-zA-Z ];
FS_Reserved: {getCharPositionInLine()==42}? [ ] -> pushMode(FREE);
//FS_Keywords : {getCharPositionInLine()==43}? ~[\r\n]+;
FS_WhiteSpace : {getCharPositionInLine()>=80}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
>>