     C                   EVAL     val1 = %CHECKR(FLD1:FLD2)
     C                   EVAL     val2 = %CHECKR('delim':'mystring')
     C                   EVAL     val3 = %checkr(delim:'thestirng')
     C                   EVAL     val4 = %checkr(%str(fld):mystringfld:5)
      /free
         val1 = %checkr(fld1:FLD2); // comment 1
         val2 = %CHECKR(',;':'dfsdf,;'); // comment 2
         val3 = %Checkr(delim:'thersting'); // comment 3
         val4 = %Checkr(%str(fld):mystringfld:5); // comment 4
      /end-free 
                 
