sqlMode() ::= <<
SQL_WS: [ \t\r\n]+ -> skip;
//SINGLE_QTE: ['];
//DOUBLE_QTE: ["];
SEMI_COLON: SEMI -> type(SEMI),popMode, popMode;
WORDS: ~[ ;\r\n] (~[;\r\n]+ ~[ ;\r\n])?; 
>>
