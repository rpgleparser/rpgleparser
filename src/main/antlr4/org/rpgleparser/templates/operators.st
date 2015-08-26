operators() ::= <<
// 
AMPERSAND: '&';

// Boolean operators
AND : [aA] [nN] [dD] ;
OR : [oO] [rR] ;
NOT : [nN] [oO] [tT] ;

// Arithmetical Operators
PLUS : '+' ;
MINUS : '-' ;
EXP : '**' ;
//MULT : '*' ;
ARRAY_REPEAT: {_input.LA(2) == ')' && _input.LA(-1) == '('}? '*' ;
MULT_NOSPACE: {_input.LA(2) != 32}? '*';
MULT: {_input.LA(2) == 32}? '*' ;
DIV : '/' ;

// Assignment Operators
CPLUS : '+=' ;
CMINUS : '-=' ;
CMULT : '*=' ;
CDIV : '/=' ;
CEXP : '**=' ;

// Comparison Operators
GT : '>' ;
LT : '\<' ;
GE : '>=' ;
LE : '\<=' ;
NE : '\<>' ;
>>