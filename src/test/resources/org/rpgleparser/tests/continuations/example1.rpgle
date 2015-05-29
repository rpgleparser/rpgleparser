      * Define a 10 character field with a long name.
      * The second definition is a pointer initialized to the address
      * of the variable with the long name.
     D QuiteLongFieldNameThatCannotAlwaysFitInOneLine...
     D                 S             10A
     D Ptr             S               *   inz(%addr(QuiteLongFieldName...
     D                                               ThatCannotAlways...
     D                                               FitInOneLine))
     D ShorterName     S              5A
     
      *.. 1 ...+... 2 ...+... 3 ...+... 4 ...+... 5 ...+... 6 ...+... 7 ...+... 8
      * Use the long name in an expression
      * Note that you can split the name wherever it is convenient.
     C                   EVAL          QuiteLongFieldName...
     C                                 ThatCannotAlwaysFitInOneLine = 'abc'
     
      * You can split any name this way
     C                   EVAL          P...
     C                                 tr = %addr(Shorter...
     C                                 Name)