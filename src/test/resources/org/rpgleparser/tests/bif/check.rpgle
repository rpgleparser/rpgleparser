     C                   EVAL     val1 = %CHECK(FLD1:FLD2)
     C                   EVAL     val2 = %CHECK('delim':'mystring')
     C                   EVAL     val3 = %check(delim:'thestirng')
     C                   EVAL     val4 = %check(%str(fld):mystringfld:5)
      /free
         val1 = %check(fld1:FLD2); // comment 1
         val2 = %CHECK(',;':'dfsdf,;'); // comment 2
         val3 = %Check(delim:'thersting'); // comment 3
         val4 = %Check(%str(fld):mystringfld:5); // comment 4
      /end-free 
                 