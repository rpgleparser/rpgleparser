     C                   EVAL      MARY='Mary had a little lamb, its +
      * Only a comment or a completely blank line is allowed in here
     C                                fleece was white as snow.'
      *
      *  Arithmetic expressions do not have continuation characters.
      *  The '+' sign below is the addition operator, not a continuation
      *  character.
     C
     C                   EVAL      A = (B*D)/ C +
     C                             24
      *  The first use of '+' in this example is the concatenation
      *  operator.  The second use is the character literal continuation.
     C                   EVAL      ERRMSG = NAME +
     C                                      ' was not found +
     C                                      in the file.'