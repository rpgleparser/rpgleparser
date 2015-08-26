eatCommentLinesMode() ::= <<
EatCommentLines_WhiteSpace: ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]{getCharPositionInLine()==6}?[ ]* NEWLINE -> skip;
EatCommentLines_StarComment: 
   ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n]{getCharPositionInLine()==6}? [*] ~[\r\n]* NEWLINE -> skip;
FIXED_FREE_STRING_CONTINUATION_Part2:  
   (
     ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 
     ( [cC] {_modeStack.contains(FIXED_CalcSpec)}?
      | [dD] {_modeStack.contains(FIXED_DefSpec)}? 
      | [oO] {_modeStack.contains(FIXED_OutputSpec)}? 
     ) 
     ~[*] 
     ( '                            ' {_modeStack.contains(FIXED_CalcSpec)}?
       | '                                    ' {_modeStack.contains(FIXED_DefSpec)}?
       | '                                             ' {_modeStack.contains(FIXED_OutputSpec)}?
     ) 
     ([ ]* {_modeStack.peek() == EatCommentLinesPlus}?
      | 
     )  // If it plus continuation eat whitespace.
   ) 
   -> type(CONTINUATION),skip ;
//FIXED_FREE_STRING_CONTINUATION_Minus_Part2:  
//   (
//     ~[\r\n]~[\r\n]~[\r\n]~[\r\n]~[\r\n] 
//     ( [cC] {_modeStack.contains(FIXED_CalcSpec)}?
//      | [dD] {_modeStack.contains(FIXED_DefSpec)}? 
//     ) 
//     ~[*] '                            ' 
//   ) 
//   -> type(CONTINUATION),skip ;
//Deliberate match no char, pop out again
EatCommentLines_NothingLeft: -> popMode,skip;
>>